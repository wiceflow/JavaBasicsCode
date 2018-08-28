# Java 的十个编程技巧
* 转自微信公众号 `Java编程精选`
```
“任何可能出错的事情，最后都会出错。”
这就是人们为什么喜欢进行“防错性程序设计”的原因。偏执的习惯有时很有意义，有时则不够清晰也不够聪明，也许当你想到这样写的人的时候还会觉得有点怪异。
```

## <font color="red">一、把字符串常量放在前面</font>
通过把字符串常量放在比较函数equals()比较项的左侧来防止偶然的 NullPointerException 从来都不是一个坏主意，就像这样：
```java
  // bad
  if(name.equal("superman")){...}
  // good
  if ("superman".equal(name)) {...}
```
更好的是还需要取消`superman`的魔法值

## <font color="red">二、不要相信早期的JDK APIs</font>
Java刚出现的时候，编程一定是件很痛苦的事。那时的API仍然不够成熟，你可能曾经遇到过这样一段代码
```java
  String[] files = file.list;
  // Watch out
  if(files != null){
    for(int i = 0; i < files.length; i++){
      ...
    }
  }
```
看起来很奇怪对吗？也许吧，但是看看这个Javadoc：<font color="greed">“如果抽象路径名表示的不是一个目录，那么这个方法返回。否则返回一个字符串数组，其中每个字符串表示当前目录下的一个文件或目录。”  </font>
是的，最好再加上判空检查，以确保正确：
```java
  if (file.isDirectory) {
    String[] files = file.list;
    // Watch out
    if(files != null){
      for(int i = 0; i < files.length; i++){
        ...
      }
    }
  }
```
## <font color="red">三、不要相信 “-1”</font>
我知道这很偏执，Javadoc中关于 String.indexOf() 的早期描述是这样的:<font color="greed">“字符在字符序列中第一次出现的位置将作为结果[被返回]，如果字符不存在则返回-1。”</font>
所以，-1 就可以理所当然被拿来用，对吗？我说不对，看看这个：
```java
  // Bad
  if (String.indexOf(character) != -1){...}
  // Good
  if (String.indexOf(character) > 0) {...}
```
谁知道呢。也许在某个特定场合下他们将会需要另一种 编码值，如果不区分大小写的话，otherString 就会被包含进去…此时或许可以返回 -2呢？谁知道呢。
毕竟，我们有非常多关于NULL——价值亿万美金的错误的讨论。为什么不开始讨论 -1呢，某种意义上来说 -1 是 null 在int类型下的另一种形式。
## <font color="red">四、避免意外的赋值</font>
是的。即使最优秀的程序员也可能犯这种错误（当然，不包括我。看第七条）。
（假设这是JavaScript，我们暂且偏执地认为是这种语言）
```java
  // Ooops
  if (variable = 5) {...}
  // Better (Because cause an error)
  if (5 = variable) {...}
  // Intent (remember. Paranoid JavaScript: ==)
  if (5 === variable) {...}
```
再说一遍。如果你的表达式中有常量，将它放在等式左边。这样当你打算再添加一个 `=` 时，不容易出错。

## <font color="red">五、检查null和长度</font>
不管什么时候你有一个集合、数组或者其他的，确保它存在并且不为空。
```java
  // Bad
  if (array.length > 0) {...}
  // Good
  if(array != null && array.length > 0){...}
```

## <font color="red">六、所有的方法都用 final 声明</font>
你可以告诉我任何你想要的开闭原则，不过那都是胡说八道。我不相信你（可以正确继承我的类），也不相信我自己（不会意外地继承我的类）。因此除了接口（专门用于继承）都应该是严格的 final。
```java
  // Bad
  public void boom(){...}
  // Good
  public final void boom(){...}
```
是的，写成final。如果这样做对你来说没有意义，你也可以通过修改或重写字节码来改变类和方法，或者发送功能请求。我敢肯定的告诉你重写类/方法并不是一个好主意。

## <font color="red">七、所有的变量和参数都用 final 声明</font>
就像我说的。我不相信自己不会无意间重写了某个值。这么说来，我的确一点都不相信自己。因为：这也是为什么所有的变量和参数都用final声明的原因。
```java
  // Bad
  void input (String test){
    String answer = "...";
    answer = test  = "LOL";
  }
  // Good
  final void input (final String test){
    final String answer = "...";
  }
```
好吧，我承认，这一条我自己也不常用，虽然我应该用。我希望Java能像Scala语言一样，人们在所有地方都直接用 val 来表示变量，甚至都不考虑易变性，除非明确需要的时候他们才用 var 来声明变量，但是这样的机会特别少。
对于一些确认不会改变的变量还是使用final最好。

## <font color="red">八、重载的时候不要相信泛型</font>
是的，这是会发生的。你觉得你写了一个超好的API，它真的是既酷炫又直观；接着就出现了一群用户，他们只是把一切类型生搬硬套进 Object 中 直到那该死的编译器停止工作，然后他们突然链接到了错误的方法，认为这一切都是你的错（事情总是这样）。

思考一下这个：
```java
  // Bad
  <T> void bad(T value){
    bad(Collection.singletonList(value));
  }
  <T> void bad(List<T> values){
    ...
  }
  // Good
  final <T> void good(final T value){
    if(value instanceof List){
      good(List<?> value);
    }else{
      good(Collection.singletonList(value));
    }
  }
  final <T> void good(final List<T> values){...}
```
因为，你知道的…你的用户们，他们就像这样
```java
  // This library sucks
  Object t = (Object) (List) Array.asList("abc");
  bad(t);
```
所以说偏执是有好处的。

## <font color="red">九、总是在switch语句里加上default</font>
Switch…作为最滑稽的表达式之一，我不知道是该心存敬畏还是默默哭泣。不管怎样，我们既然无法摆脱 switch ，在必要的时候我们最好能够正确使用它，例如：
```java
  // Bad
  switch (value) {
      case 1: foo(); break;
      case 2: bad(); break;
  }
  // Good
  switch(value){
      case 1:foo(); break;
      case 2:bar(); break;
      default:
        throw new ThreadDeath("That'all teach them");
  }
```
因为在当 value=3 被引入到软件中的时候，default 就能发挥作用，使其正常运行！别和我提 enum 类型，因为这对 enums 也一样适用。
## <font color="red">十、用大括号隔开 switch 的每一个 case 块</font>
事实上，switch是最坑爹的语句，任何喝醉了或是赌输了的人都可以在某种语言中使用它。看看下面这个例子：
```java
  // Bad,doesn't compile
  switch(value){
    case 1: int j = 1; break;
    case 2: int j = 2; break;
  }
  // Good
  switch(value){
    case 1: {
      final int j = 1;
      break;
    }
    case 2: {
      final int j = 2;
      break;
    }
    // remember
    default:
      throw new ThreadDeath("That'all teach them");
  }
```
在switch语句中，为所有的case都只定义了一个作用域。事实上，这些case不是真正意义上的语句，他们更像是标签，而switch就是指向这些标签的goto语句。事实上，你甚至可以把case语句和 惊人的FORTRAN77项声明 类比，对于FORTRAN，它的神秘已经超越了它的功能。

这意味着变量final int j 可以被任何case访问，不论我们是否有break。看起来并不是很直观。我们可以通过添加简单的花括号为每一个case创建一个新的嵌套的作用域，当然不要忘了在每个 case 的语句块最后加 break。
