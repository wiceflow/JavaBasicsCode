# SQL查询  

## like 模糊查询注意事项  
在程序中，若使用 `where name like '%%'`,而当 `name`字段在数据库中可以是null时，这时候条件就会不起作用，也就是说被忽略掉了。  

* 给它加上空值判断，例如 `PostgreSQL`中的 `COALESCE`函数  
  ```sql
      select COALESCE(b.price, 0) as price like '%%' from fruit_sale b
  ```  
  这是当`price`字段在数据库中是允许为空的时候，`COALESCE`函数会先判断当前条件下的`price`字段是否为空，若为空的话则为0 ， 并且 `0 like '%%'` 是可以使用的
