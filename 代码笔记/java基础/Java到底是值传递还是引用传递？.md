# Java里是值传递
* `Java`程序设计语言对对象采用的不是引用调用,实际上,对象引用进行的是值传递
* 一个方法不能修改一个基本数据类型的参数(即数值型和布尔型)
* 一个方法可以改变一个对象参数的状态
* 一个方法不能让对象参数引用一个新的对象

## 一：搞清楚 基本类型 和 引用类型的不同之处
```Java
    int num = 10;
    String str = "hello";
```
<img src="transferimg/transfer1.jpg"/>
　　如图所示，`num`是基本类型，值就直接保存在变量中。而`str`是引用类型，变量中保存的只是实际对象的地址。一般称这种变量为"引用"，引用指向实际对象，实际对象中保存着内容。

## 二：搞清楚赋值运算符（=）的作用
```java
    num = 20;
    str = "java";
```
<img src="transferimg/transfer2.jpg"/>
　　对于基本类型 `num` ，赋值运算符会直接改变变量的值，原来的值被覆盖掉。<font color=red>对于引用类型 str，赋值运算符会改变引用中所保存的地址，原来的地址被覆盖掉。但是原来的对象不会被改变（重要）</font>。如上图所示，`hello` 字符串对象没有被改变。（没有被任何引用所指向的对象是垃圾，会被垃圾回收器回收）
## 三：调用方法时发生了什么？参数传递基本上就是赋值操作。
```java
    第一个例子：基本类型
    void foo(int value) {
        value = 100;
    }
    foo(num); // num 没有被改变

    第二个例子：没有提供改变自身方法的引用类型
    void foo(String text) {
        text = "windows";
    }
    foo(str); // str 也没有被改变

    第三个例子：提供了改变自身方法的引用类型
    StringBuilder sb = new StringBuilder("iphone");
    void foo(StringBuilder builder) {
        builder.append("4");
    }
    foo(sb); // sb 被改变了，变成了"iphone4"。

    第四个例子：提供了改变自身方法的引用类型，但是不使用，而是使用赋值运算符。
    StringBuilder sb = new StringBuilder("iphone");
    void foo(StringBuilder builder) {
        builder = new StringBuilder("ipad");
    }
    foo(sb); // sb 没有被改变，还是 "iphone"。
```

### 重点理解为什么，第三个例子和第四个例子结果不同？
#### 下面是第三个例子的图解：
<img src="transferimg/transfer3.jpg"/>

##### `builder.append("4")`之后
<img src="transferimg/transfer4.jpg"/>

#### 下面是第四个例子的图解：
<img src="transferimg/transfer5.jpg"/>
##### `builder = new StringBuilder("ipad");` 之后
<img src="transferimg/transfer6.jpg"/>

## Java核心技术中的例子
```Java
    Class Employee(){
      private String name;
      public Employee(String name){
        this.name = name;
      }
      public String getName(){
        retrun this.name;
      }
    }
    public void swap(Employee x,Employee y){
      Employee temp = x;
      x= y;
      y = temp;
    }
    public static void main(String[] args){
      Employee a = new Employee("I am a");
      Employee b = new Employee("I am b");
      swap(a,b);
      System.out.println(a.getName());  // I am a
      System.out.println(b.getName());  // I am b
    }

```
　　你会发现,即使两个对象进入了`swap`交换函数，而他们本身却没有交换。可以这么理解，当调用`swap`函数时，传递给其的是`a和b`的两个拷贝，此时`a和x`指向了同一个对象地址，`b和y`指向了同一个对象的地址。而在`swap`函数了发生的交换是交换了`x和y`指向的对象的地址，对于外头的`a和b`没有造成影响。
