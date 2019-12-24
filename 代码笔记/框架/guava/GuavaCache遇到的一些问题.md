# Guava Cache 的一些错误提示  



## 一、key 与 value 是否可以为 null  

在 `GUAVA CACHE` 中，`key` 是不可以为 `null` 的，从源码可以看出，键值为 `null`  时会抛出空指针异常。  

```java
@CanIgnoreReturnValue
  public static <T> T checkNotNull(T reference) {
    if (reference == null) {
      throw new NullPointerException();
    }
    return reference;
  }
```

 `Guava Cache` 中 `VALUE` 也不可以为 `null` 若 直接 `put` 一个 `null` 值进去则会报空指针异常。  

那么 如果直接获取一个不存在的缓存呢，这时候会报出一个 `InvalidCacheLoadException` 异常 



## 二、设计缓存过期刷新与自动刷新

设置两者刷新的时候最好用不同的方法区分开来，在一些需要手动 `put` 的缓存中，过期刷新里面可以直接返回 null ，不做任何处理