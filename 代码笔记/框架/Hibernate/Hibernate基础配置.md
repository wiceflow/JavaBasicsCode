# Hibernate的配置
* `hibernate.cfg.xml` 一般推荐去官方查文档引入
* 先建表再建类---一些关于数据库的优化`hibernate`无法帮我们自动生成

## 配置日志系统
* `hibernate`自带了一个日志系统接口`slf`与它的一个实现`slf4j`,一般使用`log4j`,而且用`log4j`的时候还是用到了`slf`接口,利用了适配器模式,将`slf`的日志输出转成了`log4j`的
* 需要导入`log4j`包和转换包`slf4j-log4j`

## 注解
* 在使用注解对实体类进行映射的时候,可以将注解写在字段上,一般推荐写在字段的`get()`方法上


* 默认会在所有`get()`方法中加上`@Basic`注解 表示该字段是数据库里面的字段


* 若有些字段不想存入数据库,需要加上`@Transient`注解


* 对于`Date`字段,若不加上特殊注解,会被解析为对应的`DateTme`类型,加上`@TemporalType`后可以调整记录的类型

  * `Date`只记录年月日,对应的数据库类型就会转为`Date`类型

  * `Time`只记录时间,不记录日期,这个具体没试过数据库会变成什么类型

  * `TIMESTAMP`默认是这个,记录的是时间和日期,数据库对应的就是`DateTme`类型

## ID生成策略
* `identity` 对DB2,MySQL, MS SQL Server, Sybase和HypersonicSQL的内置标识字段提供支持。 返回的标识符是long, short 或者int类型的。

* `sequence` 在DB2,PostgreSQL, Oracle, SAP DB, McKoi中使用序列（sequence)， 而在Interbase中使用生成器(generator)。返回的标识符是long, short或者 int类型的。(亲测 如果在Postgresql中单独使用@GeneratedValue(strategy = GenerationType.IDENTITY)会报找不到序列的错误！不知道是不是我的使用方法有问题---要配合`@sequencegenerator`注解查询)

* `uuid` 利用一套独特的算法整出的ID，全宇宙唯一，例如两个网卡上的Mac地址 永远不会相同。

* `native` 根据底层数据库的能力选择identity, sequence 或者hilo中的一个。
* 使用注解的话 有`AUTO`|`IDENTITY`|`SEQUENCE`|`TABLE`

* 复合主键
  #### xml方式：
  ```xml
  <!-- 复合主键使用composite-id标签 -->
     <composite-id>
         <!-- key-property标签表示哪一些属性对应复合主键 -->
         <key-property name="id" column="id" type="string"></key-property>
         <key-property name="name" column="name" type="string"></key-property>
     </composite-id>
  ```
  - 新建一个主键类，包含要复合的主键，提供`get(),set()`方法
  - 实体类中删除被复合的两个属性
  - 主键类中重写`equals`方法和`hashCode`方法，数据库中判定两个主键是否相同就是看有无重复
  - 并且必须要实现`Serialzable`接口，实现序列化方便转移数据

  #### 注解方式：
  * 将组件类注解为`@Embeddable`，并在PO类中将他的属性注解为`@Id`
  * 直接将组件在PO类里面的属性注解为`@EmbeddedId`
  * 将类注解为`@IdClass`，并将实体中所有属于主键的属性注解为`@Id`

  * 具体代码看`Hibernate`相关的的代码笔记
  *
