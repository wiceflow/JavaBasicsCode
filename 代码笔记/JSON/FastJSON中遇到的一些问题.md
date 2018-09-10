## 记录fastjson使用中遇到的一些问题  

### 一、JSON.parseObject()  

这是一个将`JSON`字符串转换为实体对象的方法  

注意：使用这个方法的时候，如有需要转换的参数，则必须提供带有该参数的构造方法，否则必须提供无参构造方法  

我们来看个例子：    

这是一个`BusFeedbackSituationDTO`类，省略了`get set`方法

```java
public class BusFeedbackSituationDTO {
    private Integer busNum;

    /**'
     * 车牌
     */
    private List<String> busPlate;

    public BusFeedbackSituationDTO(Integer busNum) {
        this.busNum = busNum;
    }
}
```

我们还有如下一串JSON字符串  

```java
String s = "{\"busNum\":7,\"busPlate\":[\"【粤B-123】\",\"【粤B-233】\",\"【粤B-244】\",\"【粤B-123】\",\"【粤B-233】\",\"【粤B-244】\",\"【粤B-134】\"]}";
```

测试用例 ：  

```java
public class Test {

    public static void main(String[] args) {
        String s = "{\"busNum\":7,\"busPlate\":[\"【粤B-123】\",\"【粤B-233】\",\"【粤B-244】\",\"【粤B-123】\",\"【粤B-233】\",\"【粤B-244】\",\"【粤B-134】\"]}";
        System.out.println(s);
        BusFeedbackSituationDTO b = JSON.parseObject(s,BusFeedbackSituationDTO.class);
        System.out.println(b);
    }
}
```

这里就会报错  说没有提供默认的构造方法    

![1535685534576](img\StringTrunObject-1.png)

在自己做了异常处理时，他不会报错，而是转换了过去，不过将`busPlate`里面的内容忽略掉了，也就是会造成``BusFeedbackSituationDTO`中的`busPlate`参数为空！