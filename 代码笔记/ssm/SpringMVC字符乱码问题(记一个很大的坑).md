# springMVC 乱码问题
>一般我们学习`ssm`框架的时候都会看到别人在`web.xml`中加入字符过滤器`filter`，大致如下：

```xml
<!--字符编码过滤器-->
<filter>
    <filter-name>encodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <async-supported>true</async-supported>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
    </init-param>
</filter>
<filter-mapping><!--用来定义filter所对应的URL-->
    <filter-name>encodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```
从传统的`web`项目来讲，这是可以解决字符乱码问题的，在前后端还是在耦合在一起的时候。
然而在前后端分离的项目项目中，这样子只可以解决前端传送数据到后端的乱码问题！

>也就是说这个字符过滤器实际上是用来处理request编码的，response编码并不会处理(配置了其他东西免谈)

## 在讲如何解决问题之前，我们先讨论一个注解--@ResponseBody
我们都知道`@ResponseBody`这个注解是告诉控制器我要返回的数据是一个`json格式`数据，`springMVC`内部会用`jackson`帮我们去解析`bean`从而返回一个解析完成的`json格式`数据给前端。 [来看官方解答]

- @responseBody注解的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML数据

[注意！！] <font color=red>使用了`@responseBody`此注解之后不会再走试图处理器，而是直接将数据写入到输入流中，他的效果等同于通过response对象输出指定格式的数据。</font>

也就是说如果使用了`@responseBody`后返回的数据如果出现了中文字符乱码，这时候无论你是在方法体里面设置`response.setHear()`还是在拦截器中设置拦截也好，他都不回起到任何作用。因为这时候它已经默认不再走任何处理器了。
- 这时候如果想让它返回中文不乱码需要在`Mapping中加入product属性` 如下：
    ```JAVA
        @RequestMapping(value = "testPersonalValidtor.do",produces = "application/json;charset=utf-8")
    ```
    然鹅，这种方式要在所有的`Controller`的每个`url`中都加上，这就显得很鸡肋了。

解决办法：  配置`xml`
```xml
<!-- SpringMVC注解驱动 -->
<mvc:annotation-driven>
  <mvc:message-converters>
      <bean class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter"/>
      <bean class="org.springframework.http.converter.StringHttpMessageConverter">
          <property name="supportedMediaTypes">
              <list>
                  <value>text/plain;charset=utf-8</value>
                  <value>text/html;charset=UTF-8</value>
              </list>
          </property>
      </bean>
  </mvc:message-converters>
</mvc:annotation-driven>
```
注意，`xml`里面的`bean`是写在了`<mvc:annotation-driven>`里面！

<font color=red>这个是新版本的配置，需要去除`xml`中的`RequestMappingHandlerMapping`、`RequestMappingHandlerAdapter`或者`DefaultAnnotationHandlerMapping`、`AnnotationMethodHandlerAdapter`的`Bean`配置


# 若没使用`@ResponseBody`注解直接返回String类型，是会报404的，因为这时候默认进行的是视图渲染。
