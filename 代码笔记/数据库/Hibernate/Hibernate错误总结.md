# Hibernate学习笔记
## 遇到的错误总结
* 在一对多或者多对多关系中，执行插入操作的时候，每个`Entity`的ID最好设置策略，
否则即使在数据库中设置了主键自增，但在`Hibernate`的存储过程时，其先将对象存储到了
session中，而`Set`中的新对象会从数据库取自增`ID`这时候由于还没进行存储，所以多的一方
取到的`ID`永远都是相同的，导致报异常！

* Multiple representations of the same entity are being merged
这个错误是`Hibernate`自身的一个BUG，已经修复了，要在配置文件中加入
```java
<property name="hibernate.event.merge.entity_copy_observer">allow</property>
```
如果是Spring管理Hibernate，则在Spring的数据源中配置
```java
<prop key="hibernate.event.merge.entity_copy_observer">allow</prop>
```
