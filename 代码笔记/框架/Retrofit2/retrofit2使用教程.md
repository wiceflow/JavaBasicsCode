# Retrofit2学习笔记
* 主要参考了了 [超级详细的Retrofit2使用教程](http://blog.csdn.net/carson_ho/article/details/73732076)
* 因为这是一个安卓的框架，然而我这里使用的是`JAVA`其实都差不多，一些参数参考上面的文章，我这里介绍怎么使用
* 主要讲如何根据`Post`接口从服务器接收一个`json`数据

## 步骤一 添加依赖
* Java和安卓的引入依赖不同
```JAVA
  <dependency>
    <groupId>com.squareup.retrofit2</groupId>
    <artifactId>retrofit</artifactId>
    <version>2.3.0</version>
  </dependency>
```

## 步骤二 创建用于描述网络请求的接口
* Retrofit将 Http请求 抽象成 Java接口：采用 注解 描述网络请求参数 和配置网络请求参数
* > 用 动态代理 动态 将该接口的注解“翻译”成一个 Http 请求，最后再执行 Http 请求
  >注：接口中的每个方法的参数都需要使用注解标注，否则会报错

`IndexSystemHttpJSON.interface`
```JAVA
  public interface IndexSystemHttpJSON {
    // 这的POST URL会拼上Retrofit中的Url
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    // 这里表示这是一个Form表单的意思 因为这里的例子是翻译，所有要传输一个字段到服务器，用@Field标记
    @FormUrlEncoded
    // 这里的返回值是一个JavaBean类 具体要返回什么类型的在Call中设置泛型
    Call<Translationl> getJSONByForm(@Field("i") String targetSentence);

    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    // 这里返回的是Body结构体，可以取出字符串，例如你不想让retrofit帮你解析JSON字符串，就可以用这个返回值
    // String str = new String (ResponseBody.body.bytes())
    Call<ResponseBody> getJSON(@Field("i") String targetSentence);
}
```
## 步骤三 创建Retrofit实例
```JAVA
  // 创建Retrofit实例
  Retrofit retrofit = new Retrofit.Builder()
    // 设置Url 记住 要已/结尾，表示后面拼上Post里面的Url 不以其结尾也行，具体看文章介绍
    .baseUrl("http://fanyi.youdao.com/")
    // 这个是 如果在接口中定义了返回的是Bean类，则一定要配置解析器！不管你用不用他的GSON解析，都要设置
    // 具体有哪几种解析看文章介绍，如果是设置了ResponseBody则不用设置这一栏
    .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
    .build();
```
## 步骤四 创建 网络请求接口实例
```JAVA
  // 创建 网络请求接口 实例
  IndexSystemHttpJSON ishj = retrofit.create(IndexSystemHttpJSON.class);
  // 对 发送请求 进行封装 这里获取的是接口方法
  Call<Translationl> call = ishj.getJSONByForm("I love you");
```
## 步骤五 发送网络请求（异步/同步）
```JAVA
call.enqueue(new Callback<Translationl>() {
  // 成功函数
  @Override
  public void onResponse(Call<Translationl> call, Response<Translationl> response) {
      // response 就是我们在接口中定义的泛型返回值 如果我们定义了一个bean 则response.body()就表示这个bean
      Translationl t = response.body();
      System.out.println(response.body().toString());

  }
  // 请求失败函数
  @Override
  public void onFailure(Call<Translationl> call, Throwable throwable) {
      if (call.isCanceled()) {
          System.out.println("请求取消");
      } else {
          System.out.println("请求出错");
          System.out.println(throwable.getMessage());
      }
  }
});
// 发送网络请求（同步）
Response<Reception> response = call.execute();
// 显示数据
response.body().show;
```

介绍到这里，更多的请求方法（get，http）详情看头顶的链接文章
