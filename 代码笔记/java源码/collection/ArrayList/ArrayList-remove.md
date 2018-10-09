# ArrayList中元素的删除操作  

在聊 `ArrayList` 的删除删除操作之前，先来说说它的遍历方法。  

一个 `list` 的遍历方法主要有三种：  

- `Iterator` 迭代器遍历  
- 遍历下标 `for` 循环遍历  
- `forEach` 遍历  

对于这三种遍历方法，产生的删除操作 （`remove`） 结果也会不一样。我们主要将 `forEach` 遍历删除会出现的问题。  

我们先来看一段代码  

```java
public static void main(String[] args) {
    List<String> list1 = new ArrayList<>(3);
    list1.add("name");
    list1.add("age");
    list1.add("phone");
    for (String str : list1){
        if ("age".equals(str)){
            list1.remove(str);
        }
    }
}
```

这样子进行删除操作是不会出现问题的，然而当我们把删除的判断条件换成 `phone` 后就会报异常。  

```java
Exception in thread "main" java.util.ConcurrentModificationException
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901)
	at java.util.ArrayList$Itr.next(ArrayList.java:851)
	at com.wiceflow.collection.List.ListRemove.main(ListRemove.java:18)
```

## 分析源码揭开它的秘密  

我们知道，`forEach` 循环其实是走 `list` 的迭代器进行遍历的，我们先看 `ArrayList` 内部的 `forEach` 方法。  

在 `ArrayList` 中有一个内部类 `Itr` 实现了 `Iterator` ，还有一个 `ListItr` 继承了 `Itr` （***这个类初始化的时候会将 ArrayList 对象的 modCount 属性的值赋值给 expectedModCount***）。  

 先看迭代器的 `next` 方法  

```java
public E next() {
    // 这个方法主要是检查光标是否越界的
    checkForComodification();
    int i = cursor;
    if (i >= size)
        throw new NoSuchElementException();
    Object[] elementData = ArrayList.this.elementData;
    if (i >= elementData.length)
        throw new ConcurrentModificationException();
    cursor = i + 1;
    return (E) elementData[lastRet = i];
}  
/**
* 在对一个集合对象进行跌代操作的同时，并不限制对集合对象的元素进行操作
* 这些操作包括一些可能引起跌代错误的add()或remove()等危险操作。
* 在AbstractList中，使用了一个简单的机制来规避这些风险。 
* 这就是modCount和expectedModCount的作用所在
*/
final void checkForComodification() {
    if (modCount != expectedModCount)
         throw new ConcurrentModificationException();
}
```

> 关于 `checkForComodification()` 方法更多的可以看这篇博文：
>
> [Java 集合中常见 checkForComodification()方法的作用](https://blog.csdn.net/weixin_40254498/article/details/81386920)  



我们可以看到，`list` 每次获取下一个对象前都要去检查一下光标是否越界。在 `ArrayList` 的所有涉及结构变化的方法中都增加 `modCount` 的值，包括： `add()、remove()、addAll()、removeRange()` 及 `clear()` 方法。这些方法每调用一次，`modCount` 的值就加 1。而变量 `expectedModCount` 在迭代开始时便会被赋值成 `modCount` 的值。所以在循环遍历中，改变结构变化的方法，例如 `add()、remove()` 都会是 `modCount` 增长 1 ，而 `expectedModCount` 却不会变化。  

> 注意，以上讲的涉及到结构变化的方法是 `ArrayList` 的方法，不是其内部类 `Itr` 的方法。  

来看一下 `ArrayList` 的 `remove` 方法  

```java  
public E remove(int index) {
    rangeCheck(index);
    modCount++;
    E oldValue = elementData(index);
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index,numMoved);
    elementData[--size] = null; // clear to let GC do its work
    return oldValue;
}  

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

private void fastRemove(int index) {
    modCount++;
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index,numMoved);
    elementData[--size] = null; // clear to let GC do its work
}
```

从上面源码中我们不难发现，`ArrayList` 中两个 `remove()` 方法都对 `modCount` 进行了自增，那么我们在用迭代器迭代的时候，若是删除 **末尾** 的元素，则会造成 `modCount` 和 `expectedModCount` 的不一致导致异常抛出。  

**为什么对倒数第二个元素进行删除不会报异常，而对其他位置的删除会报异常？**

我们来看一下 `ArrayList` 中的内部类 `Itr` 。我们在调用迭代器的 `Next()` 方法之前会先调用 `hasNext()` 方法。  

```java
public boolean hasNext() {
    return cursor != size;
}
```

从代码上我们可以看出判断条件是当 `cursor ！= size` 的时候，才会进行下一次循环，而 `cursor` 参数是我们迭代循环的下标，在我们删除倒数第二个元素后，此时 `list` 的大小减了 1，再进入下一次循环后会出现 `cursor == size` ，也就是 `hasNext()` 便会返回 `false` 终止了循环。实际上 `modCount` 的数值也增加了 1，只不过循环没发执行到那里，所以异常也就不会被抛出来了。 

## for 下标遍历删除  

从源码上我们可以看出，在利用 `for` 下标进行遍历的时候，并不会触发 `checkForComodification()` 方法，所以此时只要要删除的位置比列表大小小时都不会出错。  

```java 
public E remove(int index) {
    rangeCheck(index);
    modCount++;
    E oldValue = elementData(index);
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index,numMoved);
    elementData[--size] = null; // clear to let GC do its work
    return oldValue;
} 
```

> 在 ArrayList 源码介绍中，作者是推荐使用 `for ( int i; i < size; i++)` 方式去遍历，而不是 `foreach` 或者迭代，这个主要是因为 `list` 接口实现了 `RandomAccess` 接口。 实现这个接口的集合是随机无序的，所以遍历的时候一般使用上述的 `for`,记住一点就可以了所有实现了 `RandomAccess` 接口的集合都是用一般 `for` 就可以了（可以通过 `api` 查看那些集合实现了 `RandomAccess`）。  



## Iterator 迭代遍历删除  

这里我们将的 `Iterator` 遍历删除调用的方法不是 `ArrayList` 的 `remove` 方法，而是其内部类的 `remove` 方法  

我们看源码不难发现，在 `Itr` 类中，属性 `expectedModCount` 在调用外部的 `remove()` 方法后再次被赋值，此时 `expectedModCount` 是等于 `modCount` 的。

```java
public void remove() {
    if (lastRet < 0)
        throw new IllegalStateException();
    // 这里检查时候还没有进行删除操作
    checkForComodification();

    try {
        ArrayList.this.remove(lastRet);
        cursor = lastRet;
        lastRet = -1;
        // 先进行了 remove 操作后 再重新对 expectedModCount 进行赋值
        expectedModCount = modCount;
    } catch (IndexOutOfBoundsException ex) {
        throw new ConcurrentModificationException();
    }
}
```

所以在使用 `Iterator` 进行遍历删除时不会出现 `ConcurrentModificationException` 异常。  



