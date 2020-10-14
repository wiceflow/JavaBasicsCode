# HashMap 源码解析  
简单的一句话说一下 HashMap 的结构：Map 底层实现是数组加链表，是一个 LinkedList 数组  
## 什么是哈希表
在讨论哈希表之前，我们先大概了解下其他数据结构在新增，查找等基础操作执行性能  
* 数组：采用一段连续的存储单元来存储数据。对于指定下标的查找，时间复杂度为 O(1)；通过给定值进行查找，需要遍历数组，逐一比对给定关键字和数组元素，时间复杂度为 O(n)，当然，对于有序数组，则可采用二分查找，插值查找，斐波那契查找（这个我也不会）等方式，可将查找复杂度提高为 O(logn)；对于一般的插入删除操作，涉及到数组元素的移动，其平均复杂度也为 O(n)

* 线性链表：对于链表的新增，删除等操作（在找到指定操作位置后），仅需处理结点间的引用即可，时间复杂度为 O(1)，而查找操作需要遍历链表逐一进行比对，复杂度为 O(n)

* 二叉树：对一棵相对平衡的有序二叉树，对其进行插入，查找，删除等操作，平均复杂度均为 O(logn)。

* 哈希表：相比上述几种数据结构，在哈希表中进行添加，删除，查找等操作，性能十分之高，不考虑哈希冲突的情况下，仅需一次定位即可完成，时间复杂度为 O(1)，接下来我们就来看看哈希表是如何实现达到惊艳的常数阶 O(1) 的。  

我们知道，数据结构的物理存储结构只有两种：顺序存储结构和链式存储结构（像栈，队列，树，图等是从逻辑结构去抽象的，映射到内存中，也这两种物理组织形式），而在上面我们提到过，在数组中根据下标查找某个元素，一次定位就可以达到，哈希表利用了这种特性，哈希表的主干就是数组。比如我们要新增或查找某个元素，我们通过把当前元素的关键字 通过某个函数映射到数组中的某个位置，通过数组下标一次定位就可完成操作。
### 存储位置 = f(关键字)  

其中，这个函数 f 一般称为哈希函数，这个函数的设计好坏会直接影响到哈希表的优劣。举个例子，比如我们要在哈希表中执行插入操作：
<img src="哈希表.png">
查找操作同理，先通过哈希函数计算出实际存储地址，然后从数组中对应地址取出即可。
### 哈希冲突  

然而万事无完美，如果两个不同的元素，通过哈希函数得出的实际存储地址相同怎么办？也就是说，当我们对某个元素进行哈希运算，得到一个存储地址，然后要进行插入的时候，发现已经被其他元素占用了，其实这就是所谓的哈希冲突，也叫哈希碰撞。前面我们提到过，哈希函数的设计至关重要，好的哈希函数会尽可能地保证 计算简单和散列地址分布均匀,但是，我们需要清楚的是，数组是一块连续的固定长度的内存空间，再好的哈希函数也不能保证得到的存储地址绝对不发生冲突。那么哈希冲突如何解决呢？哈希冲突的解决方案有多种:开放定址法（发生冲突，继续寻找下一块未被占用的存储地址），再散列函数法，链地址法，而 HashMap 即是采用了链地址法，也就是数组+链表的方式  
## HashMap实现原理
HashMap 的主干是一个 Entry 数组。Entry 是 HashMap 的基本组成单元，每一个 Entry 包含一个 key-value 键值对。
```java
  // HashMap 的主干数组，可以看到就是一个 Entry 数组，初始值为空数组{}，主干数组的长度一定是 2 的次幂，至于为什么这么做，后面会有详细分析。
  transient Entry<K,V>[] table = (Entry<K,V>[]) EMPTY_TABLE;
```
Entry 是 HashMap 中的一个静态内部类。代码如下
```java
  static class Entry<K,V> implements Map.Entry<K,V> {
      final K key;
      V value;
      Entry<K,V> next;// 存储指向下一个 Entry 的引用，单链表结构
      int hash;// 对key的 hashcode 值进行 hash 运算后得到的值，存储在 Entry，避免重复计算
      /**
        * Creates new entry.
      */
      Entry(int h, K k, V v, Entry<K,V> n) {
          value = v;
          next = n;
          key = k;
          hash = h;
      }
  }
```
> 所以，HashMap的整体结构如下  


<img src="hashmap整体结构.png">
简单来说，HashMap 由数组+链表组成的，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的，如果定位到的数组位置不含链表（当前 entry 的 next 指向 null）,那么对于查找，添加等操作很快，仅需一次寻址即可；如果定位到的数组包含链表，对于添加操作，其时间复杂度依然为 O(1)，因为最新的 Entry 会插入链表头部，急需要简单改变引用链即可，而对于查找操作来讲，此时就需要遍历链表，然后通过 key 对象的 equals 方法逐一比对查找。所以，性能考虑，HashMap 中的链表出现越少，性能才会越好。
## HashMap的构造器  

HashMap 有 4 个构造器，其他构造器如果用户没有传入 initialCapacity  和 loadFactor 这两个参数，会使用默认值
initialCapacity 默认为16，loadFactory 默认为 0.75
我们看下其中一个：
```java
      public HashMap(int initialCapacity, float loadFactor) {
　　　　 // 此处对传入的初始容量进行校验，最大不能超过 MAXIMUM_CAPACITY = 1<<30(230)
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);
        this.loadFactor = loadFactor;
        threshold = initialCapacity;
        init();// init 方法在 HashMap 中没有实际实现，不过在其子类如 linkedHashMap 中就会有对应实现
    }
```
从上面这段代码我们可以看出，在常规构造器中，没有为数组 table 分配内存空间（有一个入参为指定 Map 的构造器例外），而是在执行 put 操作的时候才真正构建 table 数组
```java
    public V put(K key, V value) {
        // 如果 table 数组为空数组{}，进行数组填充（为 table 分配实际内存空间），入参为 threshold，此时 threshold 为 initialCapacity 默认是1<<4(24=16)
        if (table == EMPTY_TABLE) {
            inflateTable(threshold);
        }
       // 如果 key 为null，存储位置为 table[0] 或 table[0] 的冲突链上
        if (key == null)
            return putForNullKey(value);
        int hash = hash(key);// 对 key 的 hashcode 进一步计算，确保散列均匀
        int i = indexFor(hash, table.length);//获取在 table 中的实际位置
        for (Entry<K,V> e = table[i]; e != null; e = e.next) {
        // 如果该对应数据已存在，执行覆盖操作。用新 value 替换旧 value，并返回旧 value
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }
        modCount++;//保证并发访问时，若 HashMap 内部结构发生变化，快速响应失败
        addEntry(hash, key, value, i);//新增一个entry
        return null;
    }
```
通过以上代码能够得知，当发生哈希冲突并且 size 大于阈值的时候，需要进行数组扩容，扩容时，需要新建一个长度为之前数组 2 倍的新的数组，然后将当前的 Entry 数组中的元素全部传输过去，扩容后的新数组长度为之前的 2 倍，所以扩容相对来说是个耗资源的操作。 

### 先来看看inflateTable这个方法  

inflateTable 这个方法用于为主干数组 table 在内存中分配存储空间，通过 roundUpToPowerOf2(toSize) 可以确保 capacity 为大于或等于 toSize 的最接近 toSize 的二次幂，比如 toSize=13 ,则 capacity=16 ;to_size=16,capacity=16;to_size=17,capacity=32.

```java
    private void inflateTable(int toSize) {
        int capacity = roundUpToPowerOf2(toSize);// capacity 一定是2的次幂
        threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1);//此处为 threshold 赋值，取 capacity * loadFactor 和 MAXIMUM_CAPACITY+1 的最小值，capaticy 一定不会超过 MAXIMUM_CAPACITY ，除非 loadFactor 大于1
        table = new Entry[capacity];
        initHashSeedAsNeeded(capacity);
    }
```
inflateTable 这个方法用于为主干数组 table 在内存中分配存储空间，通过 roundUpToPowerOf2(toSize) 可以确保 capacity 为大于或等于 toSize 的最接近 toSize 的二次幂，比如 toSize=13,则capacity=16;to_size=16,capacity=16;to_size=17,capacity=32.
```java
    private static int roundUpToPowerOf2(int number) {
        // assert number >= 0 : "number must be non-negative";
        return number >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }
```
roundUpToPowerOf2 中的这段处理使得数组长度一定为2的次幂，Integer.highestOneBit 是用来获取最左边的 bit（其他bit位为0）所代表的数值.

### hash函数
```java
    // 这是一个神奇的函数，用了很多的异或，移位等运算，对 key 的 hashcode 进一步进行计算以及二进制位的     调整等来保证最终获取的存储位置尽量分布均匀
    final int hash(Object k) {
        int h = hashSeed;
        if (0 != h && k instanceof String) {
            return sun.misc.Hashing.stringHash32((String) k);
        }

        h ^= k.hashCode();

        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
```
以上 hash 函数计算出的值，通过 indexFor 进一步处理来获取实际的存储位置

```java
    /**
     * 返回数组下标
     */
    static int indexFor(int h, int length) {
        return h & (length-1);
    }
```
`h\&（length-1）`保证获取的 index 一定在数组范围内,与运算时二进制运算符

### 再来看看addEntry的实现
```java
    void addEntry(int hash, K key, V value, int bucketIndex) {
        if ((size >= threshold) && (null != table[bucketIndex])) {
            // 当 size 超过临界阈值 threshold，并且即将发生哈希冲突时进行扩容
            resize(2 * table.length); 
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
```
通过以上代码能够得知，当发生哈希冲突并且 size 大于阈值的时候，需要进行数组扩容，扩容时，需要新建一个长度为之前数组 2 倍的新的数组，然后将当前的 Entry 数组中的元素全部传输过去，扩容后的新数组长度为之前的 2 倍，所以扩容相对来说是个耗资源的操作。

## get方法
```java
  public V get(Object key) {
　　　　 // 如果 key 为 null,则直接去 table[0] 处去检索即可。
        if (key == null)
            return getForNullKey();
        Entry<K,V> entry = getEntry(key);
        return null == entry ? null : entry.getValue();
 }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
```
get 方法通过 `key` 值返回对应 `value`，如果`key`为 null，直接去 `table[0]`处检索。我们再看一下 `getEntry `这个方法

```java
    final Entry<K,V> getEntry(Object key) {

        if (size == 0) {
            return null;
        }
        // 通过 key 的 hashcode 值计算 hash 值
        int hash = (key == null) ? 0 : hash(key);
        // indexFor (hash&length-1) 获取最终数组索引，然后遍历链表，通过 equals 方法比对找出对应记录
        for (Entry<K,V> e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash &&
                ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }
```
可以看出，get 方法的实现相对简单，key(hashcode)-->hash-->indexFor--> 最终索引位置，找到对应位置 table[i] ，再查看是否有链表，遍历链表，通过 key 的 equals 方法比对查找对应的记录。要注意的是，有人觉得上面在定位到数组位置之后然后遍历链表的时候，e.hash == hash 这个判断没必要，仅通过 equals 判断就可以。其实不然，试想一下，如果传入的 key 对象重写了 equals 方法却没有重写 hashCode，而恰巧此对象定位到这个数组位置，如果仅仅用 equals 判断可能是相等的，但其 hashCode 和当前对象不一致，这种情况，根据 Object 的 hashCode 的约定，不能返回当前对象，而应该返回 null
##  重写 equals 方法需同时重写 hashCode 方法
各种资料上都会提到，`重写 equals 时也要同时覆盖 hashcode`，我们举个小例子来看看，如果重写了 equals 而不重写 hashcode 会发生什么样的问题
```java
  /**
   * Created by chengxiao on 2016/11/15.
   */
  public class MyTest {
      private static class Person{
          int idCard;
          String name;

          public Person(int idCard, String name) {
              this.idCard = idCard;
              this.name = name;
          }
          @Override
          public boolean equals(Object o) {
              if (this == o) {
                  return true;
              }
              if (o == null || getClass() != o.getClass()){
                  return false;
              }
              Person person = (Person) o;
              // 两个对象是否等值，通过 idCard 来确定
              return this.idCard == person.idCard;
          }

      }
      public static void main(String []args){
          HashMap<Person,String> map = new HashMap<Person, String>();
          Person person = new Person(1234,"乔峰");
          // put 到 hashmap 中去
          map.put(person,"天龙八部");
          // get 取出，从逻辑上讲应该能输出“天龙八部”
          System.out.println("结果:"+map.get(new Person(1234,"萧峰")));
      }
  }
```
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实际输出为 `null`

如果我们已经对 HashMap 的原理有了一定了解，这个结果就不难理解了。尽管我们在进行 get 和 put 操作的时候，使用的 key 从逻辑上讲是等值的（通过 equals 比较是相等的），但由于没有重写 hashCode 方法，所以 put 操作时，key(hashcode1)-->hash-->indexFor--> 最终索引位置 ，而通过 key 取出 value 的时候  key(hashcode1)-->hash-->indexFor--> 最终索引位置，由于 hashcode1 不等于 hashcode2，导致没有定位到一个数组位置而返回逻辑上错误的值 null（也有可能碰巧定位到一个数组位置，但是也会判断其 entry 的 hash 值是否相等，上面 get 方法中有提到。）  

所以，在重写 equals 的方法的时候，必须注意重写 hashCode 方法，同时还要保证通过 equals 判断相等的两个对象，调用 hashCode 方法要返回同样的整数值。而如果 equals 判断不相等的两个对象，其 hashCode 可以相同（只不过会发生哈希冲突，应尽量避免）。