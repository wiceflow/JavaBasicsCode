# SQL中的条件语句
MySql 中关键字 case when then else end 的用法
看例子：
```sql
  SELECT
        case                   -------------如果
        when sex='1' then '男' -------------sex='1'，则返回值'男'
        when sex='2' then '女' -------------sex='2'，则返回值'女'
        else '其他'                 -------------其他的返回'其他’
        end                    -------------结束
        from   sys_user            --------整体理解： 在sys_user表中如果sex='1'，则返回值'男'如果sex='2'，则返回值'女' 否则返回'其他’
```

* 用法 一
  ```sql
    SELECT
    CASE WHEN STATE = '1' THEN '成功'
    WHEN STATE = '2' THEN '失败'
    ELSE '其他' END
    FROM  SYS_SCHEDULER
  ```

* 用法 二
  ```sql
    SELECT STATE
      CASE WHEN '1' THEN '成功'
        WHEN '2' THEN '失败'
      ELSE '其他' END
      FROM  SYS_SCHEDULER
  ```

通常不建议在`sql`中写太多的业务关系，这样会把压力堆积到数据库中。
