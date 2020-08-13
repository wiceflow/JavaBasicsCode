package com.wiceflow.cache.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.UncheckedExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;


/**
 * @author BF
 * @date 2018/9/14
 * Guava Cache abstract
 * 定义统一方法
 * 这里使用抽象方法要比接口更方便
 * 利用guava实现的内存缓存。缓存加载之后永不过期，后台线程定时刷新缓存值。刷新失败时将继续返回旧缓存。
 * 在调用getValue之前，需要设置 refreshDuration， refreshTimeunit， maxSize 三个参数
 * 后台刷新线程池为该系统中所有子类共享，大小为20.
 * 参考CSDN博客   https://blog.csdn.net/u012859681/article/details/75220605
 *
 * Abstract 类的继承是单独的引用
 */
public abstract class AbstractGuavaCacheService<K, V> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 缓存自动刷新周期
     */
    private int refreshDuration = -1;
    /**
     * 缓存刷新周期时间格式
     */
    private TimeUnit refreshTimeUnit = TimeUnit.MINUTES;
    /**
     * 缓存过期时间（可选择）  -1 为缓存时间不过期
     */
    private int expireDuration = 10;
    /**
     * 缓存过期时间格式
     */
    private TimeUnit expireTimeUnit = TimeUnit.SECONDS;
    /**
     * 缓存最大容量
     * 这个数值建议设置大一点，因为当缓存超过容量后便会对cache进行删除重新更新操作，而且并不是当容量到达设置的值才开始，
     * 而是容量接近设置的数值后变开始了
     */
    private int maxSize = 20000;
    /**
     * 数据刷新线程池   自定义线程池
     **/
    private ListeningExecutorService refreshPool =
            MoreExecutors.listeningDecorator(new ThreadPoolExecutor(5, 20, 200, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10000),
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            logger.info("Guava 后台自动刷新线程... 正在刷新旧值，该异步线程将阻塞...~");
                            return new Thread(r, "Guava 后台自动刷新线程");
                        }
                    }, new ThreadPoolExecutor.DiscardPolicy()));
    /**
     * 缓存数据块
     */
    protected LoadingCache<K, V> cache = null;

    /**
     * 初始化加载配置
     */
    protected abstract void init();


    /**
     * 用于初始化缓存值（某些场景下使用，例如系统启动检测缓存加载是否正常）
     */
    protected abstract void loadValueWhenStarted();

    /**
     * 定义缓存值的计算方法
     * 新值计算失败时抛出异常，get操作时将继续返回旧的缓存
     *
     * @param key 泛型
     * @return 泛型
     * @throws Exception 可能会获取为null
     */
    protected abstract V getValueWhenExpired(K key) throws Exception;

    /**
     * 用于异步刷新缓存值
     * <P>先定义为必须实现，若启用了异步刷新线程则必须重写这个方法，不然会造成获取值的现场阻塞</P>
     *
     * @param key key
     * @return V
     * @throws Exception 异步刷新缓存
     */
    protected abstract V reloadValue(K key) throws Exception;

    /**
     * 获取cache实例
     */
    private LoadingCache<K, V> getCache() {
        // 懒汉式构造单利  双重检查提高效率
        if (cache == null) {
            synchronized (this) {
                if (cache == null) {
                    CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
                            .maximumSize(maxSize);
                    // 默认的是不会自动刷新的
                    if (refreshDuration > 0) {
                        cacheBuilder.refreshAfterWrite(refreshDuration, refreshTimeUnit);
                    }
                    // 默认永不过期
                    if (expireDuration > 0) {
                        cacheBuilder.expireAfterWrite(expireDuration, expireTimeUnit);
                    }
                    cache = cacheBuilder.build(new CacheLoader<K, V>() {
                        @Override
                        public V load(K key) throws Exception {
                            return getValueWhenExpired(key);
                        }

                        // 异步刷新 缓存的刷新依然需要靠用户线程来驱动  只不过在用户线程触发刷新操作之后，会立马返回旧的缓存值，不会造成数据等待阻塞，除非原值为null
                        @Override
                        public ListenableFuture<V> reload(final K key, V oldValue) throws Exception {
                            return refreshPool.submit(new Callable<V>() {
                                @Override
                                public V call() throws Exception {
                                    return reloadValue(key);
                                }
                            });
                        }
                    });
                }
            }
        }
        return cache;
    }

    /**
     * 从cache中拿出数据操作
     * 当缓存中没有，便会去load 而当数据库中也没有的时候，就回报异常，注意异常的捕获
     * 对应的还会有一个 cache.getUnchecked(K)  缓存和数据库中都没有的时候，不会报异常，
     * 但必须注意，一旦CacheLoader声明了检查异常，就不可以调用getUnchecked(K) 。
     *
     * @param key 泛型
     */
    public V getValue(K key) {
        try {
            V detail = getCache().get(key);
            if (detail == null) {
                logger.info("无缓存值，对应的 key 为：{}", key);
            }
            return detail;
        } catch (CacheLoader.InvalidCacheLoadException exception) {
            logger.debug("传了一个没有对应值的 key 干啥子哟，key 为：{}", key);
            return null;
        } catch (UncheckedExecutionException uncheckedException) {
            logger.debug("无对应缓存值，对应key为：{}", key);
            return null;
        } catch (Exception e) {
            logger.error("获取缓存值失败，对应key为：{} , 异常信息为：{}", key, e);
            return null;
        }
    }

    /**
     * 从cache中拿出数据操作
     *
     * @param key 泛型 若不存在改值则返回默认值
     */
    public V getValueOrDefault(K key, V defaultValue) {
        try {
            V detail = getCache().get(key);
            if (detail == null) {
                logger.info("无缓存值，对应的 key 为：{}", key);
                return defaultValue;
            }
            return detail;
        } catch (CacheLoader.InvalidCacheLoadException exception) {
            logger.debug("传了一个没有对应值的 key 干啥子哟，key 为：{}", key);
            return null;
        } catch (UncheckedExecutionException uncheckedException) {
            logger.debug("无对应缓存值，对应key为：{}", key);
            return defaultValue;
        } catch (Exception e) {
            logger.error("获取缓存值失败，对应key为：{} , 异常信息为：{}", key, e);
            return defaultValue;
        }
    }

    /**
     * 往缓存中插入数据
     *
     * @param key   泛型
     * @param value 泛型
     *              注意，若缓存中存在对应的key，则会覆盖
     */
    public void put(K key, V value) {
        if (cache != null) {
            this.cache.put(key, value);
        } else {
            getCache().put(key, value);
        }
    }

    /**
     * 设置自动刷新周期  -> 建议采用链式编程
     *
     * @param refreshDuration [int] 自动刷新时间
     * @return 返回重新设置的对象
     */
    public AbstractGuavaCacheService<K, V> setRefreshDuration(int refreshDuration) {
        this.refreshDuration = refreshDuration;
        return this;
    }

    /**
     * 设置缓存刷新周期时间格式  -> 建议采用链式编程
     *
     * @param refreshTimeUnit [int] 刷新周期时间格式  TimeUnit.***
     * @return 返回重新设置的对象
     */
    public AbstractGuavaCacheService<K, V> setRefreshTimeUnit(TimeUnit refreshTimeUnit) {
        this.refreshTimeUnit = refreshTimeUnit;
        return this;
    }

    /**
     * 设置缓存过期时间  -> 建议采用链式编程
     *
     * @param expireDuration [int] 缓存过期时间
     * @return 返回重新设置的对象
     */
    public AbstractGuavaCacheService<K, V> setExpireDuration(int expireDuration) {
        this.expireDuration = expireDuration;
        return this;
    }

    /**
     * 设置缓存过期时间格式  -> 建议采用链式编程
     *
     * @param expireTimeUnit [int] 缓存过期时间格式
     * @return 返回重新设置的对象
     */
    public AbstractGuavaCacheService<K, V> setExpireTimeUnit(TimeUnit expireTimeUnit) {
        this.expireTimeUnit = expireTimeUnit;
        return this;
    }

    /**
     * 设置缓存最大容量  -> 建议采用链式编程
     *
     * @param maxSize [int] 缓存最大容量
     * @return 返回重新设置的对象
     */
    public AbstractGuavaCacheService<K, V> setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    /**
     * 清除所有缓存数据，若执行了缓存清除后，下一次请求还是会加载缓存
     */
    public void clearAll() {
        this.getCache().invalidateAll();
    }

    /**
     * 清除指定缓存
     */
    public void clearCacheByKey(K key) {
        this.getCache().invalidate(key);
    }

    /**
     * 输出配置
     *
     * @return String
     */
    public String getConfigString() {
        return "最大容量[maxSize：" + maxSize + "]，过期时间[expireDuration：" + expireDuration + "]，过期格式[expireTimeUnit：" + expireTimeUnit
                + "]，自动刷新时间[refreshDuration：" + refreshDuration + "]，自动刷新格式[refreshTimeUnit：" + refreshTimeUnit + "]";
    }

    @Override
    public String toString() {
        return "GuavaCache";
    }
}
