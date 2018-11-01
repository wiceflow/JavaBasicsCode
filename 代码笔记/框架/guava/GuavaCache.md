# Guava Cache 简单原理以及封装的工具类  
本文主要围绕一个抽象类来简述Guava Cache的用法，不会过多的深究其原理。
## Guava cache 简介  
先说说一般的cache都会实现的基础功能包括：  
* 提供一个存储缓存的容器，该容器实现了存放（Put）和读取（Get）缓存的接口供外部调用。 缓存通常以<key,value>的形式存在，通过key来从缓存中获取value。当然容器的大小往往是有限的（受限于内存大小），需要为它设置清除缓存的策略。  
* 在GuavaCache中缓存的容器被定义为接口Cache<K, V>的实现类，这些实现类都是线程安全的，因此通常定义为一个单例。  
* 并且接口Cache是泛型，很好的支持了不同类型的key和value。  

## 介绍三种不同的缓存过期策略  
摘自：  https://blog.csdn.net/u012859681/article/details/75220605
### 1.简单使用：定时过期  
```java  
LoadingCache<String, Object> caches = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String key) throws Exception {
                        return generateValueByKey(key);
                    }
                });
try {
    System.out.println(caches.get("key-zorro"));
} catch (ExecutionException e) {
    e.printStackTrace();
}  
```
如代码所示新建了名为caches的一个缓存对象，maximumSize定义了缓存的容量大小，当缓存数量即将到达容量上线时，则会进行缓存回收，回收最近没有使用或总体上很少使用的缓存项。需要注意的是在接近这个容量上限时就会发生，所以在定义这个值的时候需要视情况适量地增大一点。
另外通过expireAfterWrite这个方法定义了缓存的过期时间，写入十分钟之后过期。
在build方法里，传入了一个CacheLoader对象，重写了其中的load方法。当获取的缓存值不存在或已过期时，则会调用此load方法，进行缓存值的计算。
这就是最简单也是我们平常最常用的一种使用方法。定义了缓存大小、过期时间及缓存值生成方法。

如果用其他的缓存方式，如redis，我们知道上面这种“如果有缓存则返回；否则运算、缓存、然后返回”的缓存模式是有很大弊端的。当高并发条件下同时进行get操作，而此时缓存值已过期时，会导致大量线程都调用生成缓存值的方法，比如从数据库读取。这时候就容易造成数据库雪崩。这也就是我们常说的“缓存穿透”。
而Guava cache则对此种情况有一定控制。当大量线程用相同的key获取缓存值时，只会有一个线程进入load方法，而其他线程则等待，直到缓存值被生成。这样也就避免了缓存穿透的危险。  
### 2.进阶使用：定时刷新  
&nbsp;&nbsp;&nbsp;&nbsp; 如上的使用方法，虽然不会有缓存穿透的情况，但是每当某个缓存值过期时，老是会导致大量的请求线程被阻塞。而Guava则提供了另一种缓存策略，缓存值定时刷新：更新线程调用load方法更新该缓存，其他请求线程返回该缓存的旧值。这样对于某个key的缓存来说，只会有一个线程被阻塞，用来生成缓存值，而其他的线程都返回旧的缓存值，不会被阻塞。
这里就需要用到Guava cache的refreshAfterWrite方法。如下所示：  

```java  
LoadingCache<String, Object> caches = CacheBuilder.newBuilder()
                .maximumSize(100)
                .refreshAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String key) throws Exception {
                        return generateValueByKey(key);
                    }
                });
		try {
   			System.out.println(caches.get("key-zorro"));
		} catch (ExecutionException e) {
    		e.printStackTrace();
		}  
```
如代码所示，每隔十分钟缓存值则会被刷新。  

此外需要注意一个点，这里的定时并不是真正意义上的定时。Guava cache的刷新需要依靠用户请求线程，让该线程去进行load方法的调用，所以如果一直没有用户尝试获取该缓存值，则该缓存也并不会刷新。  
### 3.进阶使用：异步刷新  
如2中的使用方法，解决了同一个key的缓存过期时会让多个线程阻塞的问题，只会让用来执行刷新缓存操作的一个用户线程会被阻塞。由此可以想到另一个问题，当缓存的key很多时，高并发条件下大量线程同时获取不同key对应的缓存，此时依然会造成大量线程阻塞，并且给数据库带来很大压力。这个问题的解决办法就是将刷新缓存值的任务交给后台线程，所有的用户请求线程均返回旧的缓存值，这样就不会有用户线程被阻塞了。
详细做法如下： 



                    ListeningExecutorService backgroundRefreshPools =
                  MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(20));
          LoadingCache<String, Object> caches = CacheBuilder.newBuilder()
                  .maximumSize(100)
                  .refreshAfterWrite(10, TimeUnit.MINUTES)
                  .build(new CacheLoader<String, Object>() {
                      @Override
                      public Object load(String key) throws Exception {
                          return generateValueByKey(key);
                     }
                    @Override
                    public ListenableFuture<Object> reload(String key,
                            Object oldValue) throws Exception {
                        return backgroundRefreshPools.submit(new Callable<Object>() {
    
                            @Override
                            public Object call() throws Exception {
                                return generateValueByKey(key);
                            }
                        });
                    }
                });
                try {
        			System.out.println(caches.get("key-zorro"));
    			} catch (ExecutionException e) {
      				e.printStackTrace();
    


```  
在上面的代码中，我们新建了一个线程池，用来执行缓存刷新任务。并且重写了CacheLoader的reload方法，在该方法中建立缓存刷新的任务并提交到线程池。
注意此时缓存的刷新依然需要靠用户线程来驱动，只不过和2不同之处在于该用户线程触发刷新操作之后，会立马返回旧的缓存值。  
### TIPS  
* 可以看到防缓存穿透和防用户线程阻塞都是依靠返回旧值来完成的。所以如果没有旧值，同样会全部阻塞，因此应视情况尽量在系统启动时将缓存内容加载到内存中。  
* 在刷新缓存时，如果generateValueByKey方法出现异常或者返回了null，此时旧值不会更新。  
* 题外话：在使用内存缓存时，切记拿到缓存值之后不要在业务代码中对缓存直接做修改，因为此时拿到的对象引用是指向缓存真正的内容的。如果需要直接在该对象上进行修改，则在获取到缓存值后拷贝一份副本，然后传递该副本，进行修改操作。（我曾经就犯过这个低级错误 - -！）  
## 抽象类封装（基于第三种缓存刷新方法 - 异步刷新策略）  

​```java
package com.wiceflow.cache.guava;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
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
 */
public abstract class AbstractGuavaCacheService<K, V> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 缓存自动刷新周期
     */
    protected int refreshDuration = 10;
    /**
     * 缓存刷新周期时间格式
     */
    protected TimeUnit refreshTimeUnit = TimeUnit.MINUTES;
    /**
     * 缓存过期时间（可选择）  -1 为缓存时间不过期
     */
    protected int expireDuration = -1;
    /**
     * 缓存过期时间格式
     */
    protected TimeUnit expireTimeUnit = TimeUnit.HOURS;
    /**
     * 缓存最大容量
     * 这个数值建议设置大一点，因为当缓存超过容量后便会对cache进行删除重新更新操作，而且并不是当容量到达设置的值才开始，
     * 而是容量接近设置的数值后变开始了
     */
    protected int maxSize = 10;
    /**
     * 数据刷新线程池   自定义线程池
     **/
    protected static ListeningExecutorService refreshPool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(20));
    /**
     * 缓存数据块
     */
    private LoadingCache<K, V> cache = null;

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
     * 用于初始化缓存值（某些场景下使用，例如系统启动检测缓存加载是否正常）
     */
    public abstract void loadValueWhenStarted();

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
                        cacheBuilder = cacheBuilder.refreshAfterWrite(refreshDuration, refreshTimeUnit);
                    }
                    // 默认永不过期
                    if (expireDuration > 0) {
                        cacheBuilder = cacheBuilder.expireAfterWrite(expireDuration, expireTimeUnit);
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
                                    return getValueWhenExpired(key);
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
    public V getValue(K key) throws Exception {
        try {
            return getCache().get(key);
        } catch (Exception e) {
            logger.error("从内存缓存中获取内容时发生异常，key: " + key, e);
            throw e;
        }
    }

    /**
     * 从cache中拿出数据操作
     *
     * @param key 泛型 若不存在改值则返回默认值
     */
    public V getValueOrDefault(K key, V defaultValue) {
        try {
            return getCache().get(key);
        } catch (Exception e) {
            logger.error("从内存缓存中获取内容时发生异常，key: " + key, e);
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
        this.cache.put(key, value);
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
     * @return
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

    @Override
    public String toString() {
        return "GuavaCache";
    }
}
```
工具类的使用方法，编写自己的缓存服务类继承他，重写`getValueWhenExpired`方法，在里面定义你获取缓存的方法。需要使用时，利用`java`的多态性，`AbstractGuavaCacheService.getValue()`即可从缓存中获取数据。
