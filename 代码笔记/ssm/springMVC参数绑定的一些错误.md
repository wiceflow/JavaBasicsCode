# 先来了解两个参数绑定注解

## @RequestParam
顾名思义，这个相当于`request.getParameter()`，是获取前台传过来的一个参数。
这个参数可以绑定在url中 通过 `?`分割参数形式 例如`www.baidu.com?username=bf`这里的参数`username`就是`bf`

在springmvc中，`@RequestParam`可以不写`value`属性，它会默认和后面的参数名绑定，必须要同名，也可以直接写`bean`，自动和`bean`对象里面同名的参数进行绑定。
>在`@RequestParam`中有一个属性 `@RequestParam(required = false)` 标明这个参数可传可不传

注意： 如果使用了`required`这个属性，则对应的的类型必须为`String`
  eg：`@RequestParam(required = false) String username` 若为`int` `long`则会报错，异常信息为数据格式转换不成功！


## @PathVariable
这个注解的意思是将`url`中用`{}`包裹起来的数据映射到自定义参数中
eg:
```java
  @GetMapping("/delete/{type}")
  public AjaxResult deleteType( @PathVariable(value = "type", required = false) String type)
```
这里面对应的{type}中的值会映射到 `String type`中。

注意：`@PathVariable `没有 `required`属性，也就是说它为必填项，因为`url`一旦没写，就变成了找不到资源！如果要用这个做判断，只能给定默认值进行判断，不能判断是否为空。
