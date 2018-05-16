## JPA

### @Query 注解 时间戳
在 客管局 项目中，因为旧代码累赘尝试进行迁移，遇到了一个时间戳的问题
```sql
@Query(value = "select to_series, case when sum(date_value) is null then 0 else date_value end "
       + "from(select to_char(date(to_series),:unit) to_series from generate_series(to_date(:starttime,:format),"
       + "to_date(:endtime,:format),CAST(:step AS INTERVAL) ) as to_series )p left join "
       + "(select to_time, sum(date_value) date_value from "
       + "(select start_depot_id,sum(times) date_value,to_char(depart_date,:unit) to_time,linekind "
       + "from  T_REPORT_JZDFX where partition_date  >= to_date(:starttime,'yyyy-mm-dd') and partition_date <= to_date(:endtime, 'yyyy-mm-dd') and is_valid=1 "
       + "group by start_depot_id,to_time,linekind "
       + ") t  "
       + "where t.start_depot_id in (select station_code from t_station where is_valid=1 and status like '%使用%' "
       + "and area_id like :startareaId and station_code like :stationId ) and linekind like :toarea "
       + "group by to_time )t on t.to_time=p.to_series "
       + "group by to_series,date_value order by to_series ",nativeQuery = true)
List<Object[]> findTReport1(@Param("starttime")String starttime, @Param("format")String format, @Param("unit")String unit, @Param("endtime")String endtime,
                              @Param("step")Object step, @Param("startareaId")String startareaId, @Param("stationId")String stationId, @Param("toarea")String toarea);
```
上面代码片段中的 `step`字段，因为在`postgresql`中 `generate_series`函数的参数类型为 三个`int ` 或者三个`date`,如果不加上`CAST()`函数，则会报类型错误。而且时间块关键字`INTERVAL`不可在这里面使用 `::INTERVAL`
<font color = "red">这个问题困扰我两天</font>
