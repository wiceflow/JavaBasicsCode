# PostgreSQL 学习笔记
* 执行语句的时候记得末尾要加`;`
* `PostgreSQL`<font color=red>是大小写敏感的，当在数据库用可视化界面建表和字段时候，`shell`会自动为大写的字段加上`“”`号，所以在代码中如果连接这个表或者字段也要在代码上加`“”`，可以利用转义符达成效果</font>


## 创建一个数据库
`create database testdb;`
### 查看已存在的数据库  `\l` 是英文L
## 删除数据库
`drop database testdb;`
删除数据库失败需要确认数据库是否关闭
## 切换数据库
`\c` + 数据库名字
## 重置数据表序列
```SQL
  ALTER SEQUENCE "AMPMPortSpeed_id_seq" RESTART WITH 1;
```
注意：当表名是大写的时候要加上双引号
### 创建表
    CREATE TABLE public.student2
    (
      id integer NOT NULL,
      name character(100),
      subjects character(1),
      CONSTRAINT student2_pkey PRIMARY KEY (id)
    )
    这时候会提示 `CREATE TABLE`

    ALTER TABLE public.student2
    OWNER TO postgres;
    改变所有折

    COMMENT ON TABLE public.student2
    IS '这是一个学生信息表2';
    给表添加注释
### 删除表
首先要先进入数据库，然后执行`drop table student2`

## 架构
* 模式有助于多用户使用一个数据库，而不会互相干扰。
* 它将数据库对象组织成逻辑组，使其更易于管理。
* 可以将第三方模式放入单独的模式中，以避免与其他对象的名称相冲突。
* 在一个数据库下创建不同的架构，在不通的架构钟创建不同的表。
* 删除架构的时候要先删除里面的内容，或者使用连级删除。
### 创建架构 `CREATE SCHEMA myschema`

## 查询

### 插入语句`INSER`
  在`PostgreSQL`中，`INSER`T查询用于在表中插入新行。 您可以一次插入单行或多行到表中。
```sql
INSERT INTO TABLE_NAME (column1, column2, column3,...columnN)
VALUES (value1, value2, value3,...valueN);
```
当`values`为字符时候记得加上单引号
```sql
INSERT INTO student (id,name,subjects)
VALUES
(1,"wicelfow",3),
(2,"bluesking",5);
```
### 查询语句`select`
在`PostgreSQL`中，`SELECT`语句用于从数据库表中检索数据。 数据以结果表格的形式返回。 这些结果表称为结果集。
```sql
select id,name,subjects from "student";
```
### 更新语句`update`
在`PostgreSQL`中，`UPDATE`语句用于修改表中现有的记录。 要更新所选行，您必须使用`WHERE`子句，否则将更新所有行。
```sql
  update student
  subjects = 9
  where id = 1;
```
### 删除记录`DELETE`
`DELETE`语句用于从表中删除现有记录。 `WHERE`子句用于指定删除所选记录的条件，如是不指定条件则将删除所有记录。
```sql
  delete from student where id = 3;
```
### 排序`ORDER BY`
`PostgreSQL ORDER BY`子句用于按升序或降序对数据进行排序。数据在一列或多列的基础上进行排序。
```sql
  语法：
  SELECT column-list
  FROM table_name
  [WHERE condition]
  [ORDER BY column1, column2, .. columnN] [ASC | DESC];
```
* column_list：它指定要检索的列或计算。
* table_name：它指定要从中检索记录的表。FROM子句中必须至少有一个表。
* WHERE conditions：可选。 它规定必须满足条件才能检索记录。
* ASC：也是可选的。它通过表达式按升序排序结果集(默认，如果没有修饰符是提供者)。
* DESC：也是可选的。 它通过表达式按顺序对结果集进行排序。

### 分组`GROUP BY`
`PostgreSQL GROUP BY`子句用于将具有相同数据的表中的这些行分组在一起。 它与`SELECT`语句一起使用。
`GROUP BY`子句通过多个记录收集数据，并将结果分组到一个或多个列。 它也用于减少输出中的冗余。数据会合并
```sql
  语法:
  SELECT column-list
  FROM table_name
  WHERE [conditions ]
  GROUP BY column1, column2....columnN
  ORDER BY column1, column2....columnN
```
### Having子句
在`PostgreSQL`中，`HAVING`子句与`GROUP BY`子句组合使用，用于选择函数结果满足某些条件的特定行。
```sql
  语法:
  SELECT column1, column2
  FROM table1, table2
  WHERE [ conditions ]
  GROUP BY column1, column2
  HAVING [ conditions ]
  ORDER BY column1, column2
```
### AND 语句
`PostgreSQL AND`条件与`WHERE`子句一起使用，以从表中的多个列中选择唯一的数据。
```sql
  语法:
  SELECT column1, column2, ..... columnN
  FROM table_name
  WHERE [search_condition]
  AND [search_condition];

  SELECT id
  FROM student
  WHERE subjects>7
  AND subjects<10;
```
### OR 语句
`PostgreSQL OR`条件与WHERE子句一起使用，以从表中的一列或多列列中选择唯一数据。`OR`是或者的意思
```sql
  语法:
  SELECT column1, column2, ..... columnN
  FROM table_name
  WHERE [search_condition]
  OR [search_condition];
```
### AND & OR条件
`PostgreSQL AND＆OR`条件在仅一个查询中提供了`AND`和`OR`条件的优点。当查询条件钟出现了`AND`和`OR`的时候 要将`AND`语句用括号括起来。
```sql
  语法:
  SELECT column1, column2, ..... columnN
  FROM table_name
  WHERE ([search_condition]  AND [search_condition])
  OR [search_condition];
```
### `NOT`条件
`PostgreSQL NOT`条件与`WHERE`子句一起使用以否定查询中的条件。
```sql
  语法:
  SELECT column1, column2, ..... columnN
  FROM table_name
  WHERE [search_condition] NOT [condition];

  SELECT * FROM student WHERE subjects IS NOT NULL;
```
### `LIKE` 条件
`PostgreSQL LIKE`条件与`WHERE`子句一起用于从指定条件满足`LIKE`条件的表中获取数据。
```sql
  语法:
  SELECT column1, column2, ..... columnN
  FROM table_name
  WHERE [search_condition] LIKE [condition];
```
`LIKE` 后面一般跟通配符 `%` 用单引号引起来 'M%'
```sql
  SELECT *
  FROM student
  WHERE NAME LIKE 'Ma%';
```
### `IN`语句
`PostgreSQL IN`条件与`WHERE`子句一起使用，从指定条件满足`IN`条件的表中获取数据。
```sql
  语法:
  SELECT column1, column2, ..... columnN
  FROM table_name
  WHERE [search_condition] IN [condition];
```
### `NOT IN`语句
`PostgreSQL NOT IN`条件与`WHERE`子句一起使用，以从指定条件否定`IN`条件的表中获取数据。
```sql
  语法:
  SELECT column1, column2, ..... columnN
  FROM table_name
  WHERE [search_condition] NOT IN [condition];
```
### `BETWEEEN`语句
`PostgreSQL BETWEEN`条件与`WHERE`子句一起使用，以从两个指定条件之间的表中获取数据。
```sql
  语法:
  SELECT column1, column2, ..... columnN
  FROM table_name
  WHERE [search_condition] BETWEEN [condition];

  SELECT *
  FROM EMPLOYEES
  WHERE AGE BETWEEN 24 AND 27;

```
