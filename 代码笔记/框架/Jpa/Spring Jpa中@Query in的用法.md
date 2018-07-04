# spring jpa @Query中使用in  
jpa @Query中使用in,需要注意参数一定要是List<>，不然无法查询出数据。  
```java
  @Query(value = "select * from trade$seek_purchase_offer where sp_id in (:spIds) and of_enuu = :enUu", nativeQuery = true)
  List<SeekPurchaseOffer> getSeekPurchaseOfferList(@Param("spIds") List<Long> spIds, @Param("enUu") Long enUu);
```
