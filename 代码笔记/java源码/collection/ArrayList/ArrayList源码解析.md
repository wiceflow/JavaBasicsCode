# ArrayList源码解析
网上的 ArrayList 源码解析非常多，这里我主要讲常用的讲解。
## 对于ArrayList需要掌握的七点内容
* ArrayList的创建：即构造器
* 往ArrayList中添加对象：即add(E)方法
* 获取ArrayList中的单个对象：即get(int index)方法
* 删除ArrayList中的对象：即remove(E)方法
* 遍历ArrayList中的对象：即iterator，在实际中更常用的是增强型的for循环去做遍历
* List的扩容

### 一丶ArrayList的创建
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
在 ArrayList 中存在一个常量 `DEFAULT_CAPACITY = 10;` 这是 ArrayList 的默认数组大小，在调用无参构造函数 `List` 的默认大小为 10。  

> 用无参构造函数时， `List` 的初始化在用户调用其 `add()` 方法后再初始化，从 JDK 1.8 开始变化。  

ArrayList 的有参构造函数 `new ArrayList(int initialCapacity)` 在构建对象的时候就初始化了  

来看一下 ArrayList 的源码

```java
     // 构造一个指定容量的ArrayList
     public ArrayList(int initialCapacity) {
         if (initialCapacity > 0) {
             this.elementData = new Object[initialCapacity];
         } else if (initialCapacity == 0) {
             this.elementData = EMPTY_ELEMENTDATA;
         } else {
             throw new IllegalArgumentException("Illegal Capacity: "+
                                                initialCapacity);
         }
     }

     // 构造一个默认的空ArrayList，这里并没有初始化，jdk 1.8之后是在进行add操作后初始化  
     // DEFAULTCAPACITY_EMPTY_ELEMENTDATA  是一个静态的空数组
     public ArrayList() {
         this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
     }

     // 构造一个具有指定元素的ArrayList
     public ArrayList(Collection<? extends E> c) {
         elementData = c.toArray();
         if ((size = elementData.length) != 0) {
             // c.toArray might (incorrectly) not return Object[] (see 6260652)
             if (elementData.getClass() != Object[].class)
                 elementData = Arrays.copyOf(elementData, size, Object[].class);
         } else {
             // replace with empty array.
             this.elementData = EMPTY_ELEMENTDATA;
         }
     }
```
### 二丶增加操作

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
增加操作分为在末尾添加元素和插入操作，无论哪个操作都会引起扩容检查 `ensureCapacityInternal(size + 1); `  

>  在扩容检查中，我们会发现当 List 数组为默认数组时，就会进行初始化操作  

<img src="/image/big.jpg">

```java
  // 在数组末尾加上一个元素
  public boolean add(E e) {
      ensureCapacityInternal(size + 1);
      elementData[size++] = e;
      return true;
  }

  /**
   * 在某个位置加入一个元素element 插入操作变相为数组的复制
   * @param index
   * @param element
   */
  public void add(int index, E element) {
      // 检查index是否越界
      rangeCheckForAdd(index);
      // 进行扩容检查
      ensureCapacityInternal(size + 1);  // Increments modCount!!
      // 对数据进行复制操作，空出index位置，并插入element，后移index后面的元素
      System.arraycopy(elementData, index, elementData, index + 1,
                       size - index);
      elementData[index] = element;
      size++;
  }
```
### 三丶GET的方法
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
首先检查GET参数是否越界。越界则出发异常返回
```java
    public E get(int index) {
        rangeCheck(index);
        // 返回当前数组指定下标的值
        return elementData(index);
    }
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
```
### 四丶删除操作
```java
  /**
   * 根据索引删除元素
   * @param index
   * @return
   */
  public E remove(int index) {
      // 数组越界检查
      rangeCheck(index);
      modCount++;
      E oldValue = elementData(index);
      // 计算数组需要复制的数量
      int numMoved = size - index - 1;
      // 将index后的数据都向前移一位
      if (numMoved > 0)
          System.arraycopy(elementData, index+1, elementData, index,
                           numMoved);
      // let GC
      elementData[--size] = null;
      return oldValue;
  }

  /**
   * 根据元素内容匹配并删除，只删除第一个匹配成功的
   * @param o
   * @return
   */
  public boolean remove(Object o) {
      if (o == null) {
          for (int index = 0; index < size; index++)
              if (elementData[index] == null) {
                  fastRemove(index);
                  return true;
              }
      } else {
          for (int index = 0; index < size; index++)
              if (o.equals(elementData[index])) {
                  fastRemove(index);
                  return true;
              }
      }
      return false;
  }

  // 找到对应的元素后，删除。删除元素后的元素都向前移动一位
  private void fastRemove(int index) {
      modCount++;
      int numMoved = size - index - 1;
      if (numMoved > 0)
          System.arraycopy(elementData, index+1, elementData, index,
                           numMoved);
      elementData[--size] = null; // clear to let GC do its work
  }
```
### 五丶ArrayList的遍历

ArrayList 的遍历方式我所了解的有三种，一种是是 `for循环遍历` ，一种是 `iterator()` 迭代器遍历，一种是 `foreach` 遍历，在数据量少的时候这三种遍历的性能都差不多，但是比较推荐的是使用 `for` 循环遍历，而且最好先用一个变量存储 `list` 的大小。  

例如  

```java
List<String> strs = new ArrayList(5); 
int size = strs.size();
for(int i=0 ; i<size; i++){
    String str = strs.get(i);
    System.out.println(str);
}
```

这种遍历方式写起来虽然麻烦，但是在性能效果上确是比另外两种要好。  

需要注意一点的是，在 ArrayList 里遍历删除的时候会出现的问题，详情请看我这篇文章：  





### 六丶扩容方法

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
从JDK1.8开始，ArrayList的扩容变为原来的1.5倍
```java
      /**
       * 这个扩容方法，内部没有调用，判断ArrayList是否为空
       * @param minCapacity
       */
      public void ensureCapacity(int minCapacity) {
          int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
              ? 0
              : DEFAULT_CAPACITY;
          if (minCapacity > minExpand) {
              ensureExplicitCapacity(minCapacity);
          }
      }
      /**
       * 扩容检查
       * @param minCapacity
       */
      private void ensureCapacityInternal(int minCapacity) {
          // 第一次add操作初始化，如果为空ArrayList，那么初始化容量为10
          if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
              minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
          }
          // 判断是否需要扩容
          ensureExplicitCapacity(minCapacity);
      }
      /**
       * 判断是否需要扩容
       * @param minCapacity
       */
      private void ensureExplicitCapacity(int minCapacity) {
          // modCount这个参数运用到了 fail-fast 机制，后面解释
          modCount++;
          //扩容
          if (minCapacity - elementData.length > 0)
              grow(minCapacity);
      }

      // 最大容量
      private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

      /**
       * 将整个数组size扩容为1.5倍
       */
      private void grow(int minCapacity) {
          int oldCapacity = elementData.length;
          // newCapacity为以前的1.5倍
          int newCapacity = oldCapacity + (oldCapacity >> 1);
          if (newCapacity - minCapacity < 0)
              newCapacity = minCapacity;
          // 判断容量是否到达long int 最大临界值
          if (newCapacity - MAX_ARRAY_SIZE > 0)
              newCapacity = hugeCapacity(minCapacity);
          // 对数组进行复制处理
          elementData = Arrays.copyOf(elementData, newCapacity);
      }
      //检查是否超过最大容量 0x7fffffff ，是否抛出异常
      private static int hugeCapacity(int minCapacity) {
          if (minCapacity < 0) // overflow
              throw new OutOfMemoryError();
          return (minCapacity > MAX_ARRAY_SIZE) ?
              Integer.MAX_VALUE :
              MAX_ARRAY_SIZE;
      }
```
## 注意
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
为什么 `ArrayList` 不适合频繁插入和删除操作？就是因为在 `ArrayList` 中我们一直会调用  `System.arraycopy`  (虽然说这个函数执行效率很高，但还是频繁的浪费资源)的操作来复制数组，所以导致  `ArrayList`  在插入和删除操作中效率不高。  

当然不同的机器测试出来的结果也会不一样，毕竟现在的电脑配置有好有坏的，总不能拿一部老爷机和最新 i7 相比吧。  

fail-fast 机制是 java 集合(Collection)中的一种错误机制。当多个线程对同一个集合的内容进行操作时，就可能会产生 fail-fast 事件。例如：当某一个线程 A 通过 iterator 去遍历某集合的过程中，若该集合的内容被其他线程所改变了；那么线程 A 访问集合时，就会抛出 ConcurrentModificationException 异常，产生 fail-fast 事件。（摘自百度，如有兴趣的同学可以去了解下）



说一句面试中面试官都爱问的问题  

>  `ArrayList`的底层是用数组来实现的。而`LinkedList`使用链表实现的。