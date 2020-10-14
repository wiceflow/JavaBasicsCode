# HashMap 1.8 源码解析  

看这一篇之前建议先看 `HashMap 1.7源码解析` ，本文接着上文讲，可能一些基本的不会涉及到  

简单提及一下 1.7 和 1.8 对于 `HashMap` 的数据结构  

> 1.7 -> 内部是一个 Entry<>[] 数组，实际上是由数组 + 链表组成的 
>
> 1.8 -> 内部是一个 Node[] 类似于 Entry<>[] 数组的结构，实际上是由数组 + 链表 a



### HashMap 在 1.8 中优化了啥？  

查看 1.7 的源码，可以发现当越来越多的 `Hash`冲突时，会导致链表越来越长。我们知道数组的查询速度是 O(1)，而链表的查询速度是 O(n)，所以当链表越来越长的时候，对应的查询效率也会越来越低。1.8 中主要优化的是 `HashMap` 的扩容机制  

扩容机制包括了两点  

* 扩容时，若链表大于默认值 8，则将对应位置的默认链表转换成红黑树  
* 扩容时，判断 `Hash` 值得变化（不需要重新计算）  

### 源码源码  

先来看看 1.8 中的几个常量  

```java
    //默认数组容量大小：16
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; 
    
    //数组最大容量大小：2 的 30 次方
    static final int MAXIMUM_CAPACITY = 1 << 30;
    
    //默认的加载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    //使用树而不是链表的计数阈值，将元素添加到至少这么多的节点的链表中时，链表将转换为树
    static final int TREEIFY_THRESHOLD = 8;
    
    //可以进行树化的最小数组容量
    static final int MIN_TREEIFY_CAPACITY = 64;
    
    //存储键值对元素的数组，分配后，长度始终是 2 的幂（哈希桶数组）
    transient Node<K,V>[] table;
    
    //此映射中包含的键-值映射数，即当前数组中的元素数量
    transient int size;
    
    //主要用于记录HashMap内部结构发生变化的次数。
    transient int modCount;
    
    //哈希表所能容纳的最大键值对个数，下次扩容的临界值，size>=threshold 数组就会扩容
    int threshold;
    
    //负载因子
    final float loadFactor;
```

我们初始化 `HashMap` 的时候可以设置负载因子 `loadFactor` 的大小，当使用默认的时候负载因子就是 `0.75f`。  

那第一次引起 `HashMap` 扩容的大小`threshold * loadFactor = 12`

#### 来聊聊这个负载因子  

由上面公式可以看出，`HashMap` 的容量大小与这个负载因子有着很大的关系，当这个负载因子设置的很大的时候，`HashMap` 扩容的次数就会减少（扩容次数少了，最终结果是 `HashMap` 的容量不会很大），此时散列表的装填效率就会越高，也就是能容纳更多的元素，而元素多了，链表就会对应的增大，所以此时的索引效率就会降低。反之，如果负载因子过小，可能导致 `HashMap` 进行更多次的扩容，那么 `HashMap` 容量大小实际上会比用上的大（因为每次扩容都是幂次方进行扩容），此时会导致链表中的数据量稀疏，就会造成空间上的浪费，不过此时索引效率高。所以设置一个合理的负载因子很重要，具体要看业务场景实际操作。  



#### 1.8 确定哈希数组的位置  

`HashMap` 中是根据 `Key` 的哈希值来确定键值对在哈希桶数组中的位置的。  

来看看 `hash()` 方法  

```java
    // 经过两步操作最后得到的才是我们用来确定位置的hash值
    static final int hash(Object key) {
        int h;
        // h = key.hashCode() 为第一步 取hashCode值
        // h ^ (h >>> 16)  为第二步 高位参与运算
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    // 确定hash值之后的操作就是确定在哈希桶中的位置了
    // 下面是 put() 方法中的一行代码，n为哈希桶数组长度，hash为前一步确定的hash值
    p = tab[i = (n - 1) & hash]
```

可以看到，在 1.8 中，哈希桶数组的索引位置是由位运算来操作的，下面引用知乎大佬的一个列子[Java 8系列之重新认识HashMap](https://zhuanlan.zhihu.com/p/21673805)  



