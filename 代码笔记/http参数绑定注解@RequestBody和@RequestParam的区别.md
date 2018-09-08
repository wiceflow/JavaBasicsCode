# @RequestParam  
用来处理`Content-Type:` 为 `application/x-www-form-urlencoded`编码的内容。（`Http`协议中，如果不指定`Content-Type`，则默认传递的参数就是`application/x-www-form-urlencoded`类型）

>`@RequestParam`可以接受简单类型的属性，也可以接受对象类型。

实质是将`Request.getParameter() `中的`Key-Value`参数`Map`利用`Spring`的转化机制`ConversionService`配置，转化成参数接收对象或字段。

## 提示
在`Content-Type: application/x-www-form-urlencoded`的请求中， `get` 方式中`queryString`的值，和`post`方式中 `body data`的值都会被`Servlet`接受到并转化到`Request.getParameter()`参数集中，所以`@RequestParam`可以获取的到。其实相当于:
```java
  String str = request.getParameter("name");
  @RequestParam("name")String name;
```
# @RequestBody
处理`HttpEntity`传递过来的数据，一般用来处理非`Content-Type: application/x-www-form-urlencoded`编码格式的数据。
- GET请求中，因为没有`HttpEntity`，所以`@RequestBody`并不适用。
- POST请求中，通过`HttpEntity`传递的参数，必须要在请求头中声明数据的类型`Content-Type`，`SpringMVC`通过使用`HandlerAdapter`配置的`HttpMessageConverters`来解析`HttpEntity`中的数据，然后绑定到相应的`bean`上。
  - 若是利用`Ajax`请求，则在`Ajax`的`ContentType`要设置成`application/x-www-form-urlencoded`。特别注意，若`Ajax`传的是`json`数据，就算属性名和后台用来接收数据的`bean`名都对上了，还是要加上`@RequestBody`

# 总结
- 在GET请求中，不能使用@RequestBody。
- 在POST请求，可以使用@RequestBody和@RequestParam，但是如果使用@RequestBody，对于参数转化的配置必须统一。

举个例子，在`SpringMVC`配置了`HttpMessageConverters`处理栈中，指定`json`转化的格式，如`Date`转成`‘yyyy-MM-dd’`,则参数接收对象包含的字段如果是`Date`类型，就只能让客户端传递年月日的格式，不能传时分秒。因为不同的接口，它的参数可能对时间参数有不同的格式要求，所以这样做会让客户端调用同事对参数的格式有点困惑，所以说扩展性不高。
如果使用`@RequestParam`来接受参数，可以在接受参数的`model`中设置`@DateFormat`指定所需要接受时间参数的格式。
另外，使用`@RequestBody`接受的参数是不会被`Servlet`转化统一放在`request`对象的`Param`参数集中，`@RequestParam`是可以的。

>综上所述，一般情况下，推荐使用`@RequestParam`注解来接受`Http`请求参数。

## 对于post请求传参
若使用`Ajax`传递参数且请求为`POST`，那么推荐用`@RequestParam`，若使用到了对象嵌套，可以在前台将数据转成字符串，不要以`json`数据传回去。若以`json`数据传回后台，在`springmvc`中要指明接收数据的对象使用`@RequestBody`属性。因为这两个注解使用的`Content-Type`是不一样的，所以一旦控制层中使用了`@RequestBody`的话就要改变`Ajax`的传参格式。这时候如果控制层同时还用了`@RequestParam`接收参数，就会失效了。
<font color=red>解决方法！！！ 当控制层中对于同一个`url`同时使用了`@RequestBody`和`@RequestParam`属性，则可以将`@RequestParam`的属性绑定在`url`上传递，即变为`get`方式，不与`Ajax`中的`date`属性一起传
eg：  `www.wiceflow.com/bolg?username=wbf`
