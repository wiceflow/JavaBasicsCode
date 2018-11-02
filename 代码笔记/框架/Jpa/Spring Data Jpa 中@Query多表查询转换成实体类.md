# 来讨论一下 Jpa 中 @Query 多表查询的返回结果  

在 Spring data jpa 中利用 @Query 查询返回结果集。  

### Hibernate 的底层实现语句

我们知道，在 Spring Data Jpa 中，Jpa 可以说是更好的封装的 Hibernate 的子集，那么我们在 Hibernate 中设置 HQL 或者 SQL 查询时，是可以设置返回结果集的，我们来看一下下面的代码：  

```java
// 定义查询语句
Query query = entityManager.createNativeQuery("select id, name, age from user");
// 设置Query查询返回结果集类型
query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
List list = query.getResultList();
```

我们知道，当 `query.getResultList()` 没有设置返回类型时，它是默认返回一个 `List<Object[]>` 数组的，若我们有对应的实体类，例如 `User` 那么我们也可以这么写 `List<User> list = query.getResultList();` ，这样 `Hibernate` 会自动的帮我们将数据库的字段映射到实体类中。那么假如我现在是进行了多表查询某些字段呢？  

也就是说，没有了一个实体类可以和查询语句关联了，该怎么办？  

其实就像上面的例子一样，设置 Query 返回类型  

```java
query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
```

这样它便会返回一个以数据库字段为 key，值为 value 的 Map，这样虽然不如实体类来的直观，不过我们也可以同个 `get(String key)` 来获取对应的值，也是一种很方便的方法。  

>  不过这个特性在 Jpa 中就没有了    

### JPA 查询无实体类对应语句  

我们先来看一个简单的 JPA 例子：  

```java
@Query(value="select t.uname,t.age,c.cname form user t,class c where t.class_id = c.id")
List<Object[]> getUserAndClassName();
```

我们可以看出，就算我们有两个实体类 `User` 和 `Class` 但却无法对应上面的语句，那么这时候我们利用默认的 `List<Object[]>` 数组来接收返回的数据，相当于一个 `Object[]` 中包含了一条记录。我们会看出，当取出字段少的时候，还是很容易解决的，写个 for 循环遍历赋值就好了，那么当你是 `select *` 的时候，且联袂的表有上百个字段的时候该怎么办？  

> 难道你还要一个一个的 Debug 赋值吗？



大家可能会想到，可以自己定义一个接受数据的 VO 用来存储返回的数据。把 `List<Object[]` 改成 `List<DTO>` 这样做是不行的，在 JPA 中这样子的 Query 查询时无法赋值进去的  

我们可以看到会出现以下异常  
<img src="img/ListDTO.png">

找不到一个这样的转换器  

这里网上有很多个奇葩的方案，我记得看到了一个解决方法是 `select new map()` 强制其返回一个 `map` 数组，而这样的的结果是 `map` 里面的 key 是 `1,2,3` ，所以说这种方法并不适用。  

#### 解决方案  

我们先创建一个需要接收的数据的实体类，也是一个 DTO ，不过我们并不打算在 @Query 查询中返回这个格式，在 JPA 查询中我们还是返回一个 `LIst<Object>` 这是不需要改变的，重点是我们 DTO 的设计，字段属性的顺序（一定要和查询的顺序相同）如果是多表查询而且用到了 `select * ` 那么就按照选择的表的字段顺序创建实体类就好了，因为在 `select *`  中，返回的数据结构顺序是有序的且和数据库中字段顺序一样。  



我们将多个表需要查出的字段组成一个新的 DTO 后，利用反射原理，取出构造器，然后将 DTO 填值就好了。  

我们来看一下怎么做    以上面的例子，我们先创建一个 `UserAndClassDTO` 

```java
public class UserAndClassDTO{
    private String uname;
    private String age;
    private String cname;
    // 默认构造器
    public UserAndClassDTO(){
        
    }
    
    public UserAndClassDTO(String uname,String age,String cname){
        this.uname = uname;
        this.age = age;
        this.cname = cname;
    }
 	// ....省略 get set 方法
}
```

 上面的 DTO 中我们必须保持属性顺序与查询语句 `select` 中一致，而且需要提供一个包含所有参数的构造器和一个默认构造器。  

接下来看一下反射的代码  

```java
public <T> List<T> trunToDTO(List<Object[]> list, Class<T> clazz) throws Exception {
        List<T> returnList = new ArrayList<T>();
        // 获取对象属性
        Field[] fields = clazz.getDeclaredFields();
        // 这个 Class 数组按顺序保存着属性的类型
        Class[] c2 = new Class[fields.length];
        // 确定构造方法
        if (fields != null) {
            int length = fields.length;
            for (int i = 0; i < length; i++) {
                c2[i] = fields[i].getType();
            }
        }
        for (Object[] o : list) {
            // 调用对应的构造器
            Constructor<T> constructor = clazz.getConstructor(c2);
            // 构建 DTO
            returnList.add(constructor.newInstance(o));
        }
        return returnList;
    }
```



我们来分析一下这通用的方法 。 

* 先是获取用来存数据的 DTO 的所有属性 保存在 `Field[]` 中  
* 根据 `Field[]` 数组获取所有属性的类型，并保存到 `Class[]` 数组中  
* `Constructor<T> constructor = clazz.getConstructor(c2);` 反射获取对应的构造器  
* `constructor.newInstance(o)` 通过构造器并传入值来构建对象  

至此一个 DTO 被填充数据并保存到 List 中。  



### 不足点  

这个方法的 DTO 字段属性必须要和数据库中的一致，当数据库新增或删除字段时，对应的 DTO 也要修改，不过对于一个成熟的系统的话，数据库是不会轻易修改的~  笔芯

