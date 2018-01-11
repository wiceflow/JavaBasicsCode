# Hibernate性能优化
## 注意session.clear()的运用，尤其在不断分页的时候
在大数据量的时候，要注意`session`的清除，不然之前的数据占用着内存会导致内存泄漏。

## 1+N问题
### 对于1+N的理解
　　一般而言说n+1意思是，无论在一对多还是多对一当查询出n条数据之后，每条数据会关联的查询1次他的关联对象，这就叫做n+1。
　　但是我的理解是，本来所有信息可以一次性查询出来，也就是简单的连表查询，但是Hibernate会首先查询1次得到当前对象，然后当前对象里面的n个关联对象会再次访问数据库n次，这就是1+n问题。
　　他们二者之间表达的意思其实是一样的，只是描述这个问题的角度不同。不过我认为1+n更准确，因为以前我第一次看到n+1问题的时候就总是在想是不是查n然后多出一次，那其实是没什么影响的，后来才明白。
　　既然出现这个问题，肯定是要避免的，尤其是对于高并发的互联网应用，这种现象是绝对不允许出现的

### 解决方案一： 设置FetchType属性
在多的一方将`fetch`属性设置为`LAZY`，这样在读取的时候若没有使用到多的一方的属性，它是不会加载的，按需而发。前面也提到过，多对一，一对多关系中，一的一方`fetch`属性默认就是`LAZY`。

### 解决方法二： 使用@BatchSize注解
`@BatchSize` 只能使用在类上！ 不能使用在变量上，一般加载多的一方的类名上方，代表每次读取直接读取多少条属性。用法
```java
  @Entity
  @Table(name = "t_group")
  @BatchSize(size = 5) //这个注解要放在类上
  public class Group {
    private int id;
    private String NAME;
    ....
  }
```
不够灵活，设置在类上。没有实际解决问题，还是会发出语句，只是相应的减少了。
### 解决方法三： 使用join fetch
这里先讲一个对比
- `createCriteria(***.Class)`这个方法是默认使用联结查询，在发出一的查询语句时候会用连接关系将N的数据也查出来，并不会发多条语句
- `createQuery(HQL)`使用HQL时候，默认是不会使用联结查询的，需要手写联结语句。

```java
  List<User> users = List<User>session.createQuery("from User u left join fetch u.group").list();
```
## List和Iterate的区别
### 区别一：
`List`在使用的时候直接将整个对象加载进了缓存，`Iterate`会先将所有对象的`ID`加载进缓存，在用到具体对象的时候在根据`ID`去发出`SQL`语句。
```java
  List<Group> groups =
          List<Group>session.createQuery("from Group").list();
  Iterate<Group> lgroups =
          Iterate<Group>session.createQuery("from Group").Iterate();
```
### 区别二
当对相同表进行第二次取值时候
用`list`它会重新发出`sql`语句，而使用`Iterate`会先查`session`缓存
## 一级缓存和二级缓存和查询缓存
- 什么是缓存
- 什么是一级缓存  `session`级别的缓存
- 什么是二级缓存  `sessionFactory`级别的缓存，可以跨越`session`存在
  - 经常被访问
  - 改动不大
  - 数量有限
- 打开二级缓存
  ```xml
  <property name="cache.use_second_level_cache">true</property>
  <property name="hibernate.cache.region.factory_class">
    org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
  ```
  使用注解`@Cache`  跟xml一起用的
- `load`默认使用二级缓存，`iterate`默认使用耳机缓存
- `list`默认往二级缓存加数据，但是查询的时候不会使用，每次`list`查询的条件都会不一样，不能保证缓存的数据就是想要的

  对于在同一`session`中的`load`获取数据，其都只会发出一条`sql`语句。对于在不同`session`中获取数据，就会根据不同的`session`发出`sql`语句。开启了二级缓存后，相当于把所有的`session`缓存都加入到了二级缓存中，在查取数据的时候会先找二级缓存，二级缓存中没有才会去数据库取。  
- 如果要`query`用二级缓存，需打开查询缓存
    - `<property name="cache.use_query_cache">true</property>`
    - 调用`Query`的`setCachable(true)`方法指明使用二级缓存


### 缓存算法(纯粹是为了面试)
当缓存里满的时候，缓存算法是决定了该清楚哪个对象的算法
  - LRU `Least Recently Used`找出最近很少被使用的一个对象
  - LFU `Least Frequently Used` 命中效率高低
  - FIFO `First In First Out`先进先替换
  - 使用方法
  `memoryStoreEvictionPolicy="LRU"(ehcache)`
