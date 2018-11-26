# 文件操作
## 先来了解文件的两个常量
  * 路径分隔符 `pathSeparator` 实际上就是一个`;`号
  * 名称分隔符 `separator` \(windows)  /(linux 等)
    ````java
      System.out.System.out.println(File.pathSeparator);
      /*================================================*/
      打印出： ;
      System.out.System.out.println(File.separator);
      /*================================================*/
      打印出： \
    ````
## 相对路径与绝对路径构造 File对象
### 相对路径构建File对象的两个方法
  * `File(String parent, String child) ==>File("E:/test/test","iceflow.jpg")`

  * `File(File parent, String child)
    ==> File(new File("E:/test/test"),"iceflow.jpg")`

### 绝对路径构建File对象的方法
  * `File(String name)`

  ```java
    String parentPath = "E:/test/test";
    String name = "iceflow.jpg";
    //相对路径
    File src = new File(parentPath, name);
    src = new File(new File(parentPath), name);
    //输出
    System.out.println(src.getName());  // iceflow.jpg
    System.out.println(src.getPath());  // E:\test\test\iceflow.jpg
    //绝对路径
    src = new File("E:/xp/test/2.jpg");
    //没有盘符: 以 user.dir构建 当前项目路径
    src = new File("test.txt");
    System.out.println(src.getName()); // test.txt
    System.out.println(src.getPath()); // test.txt
    System.out.println(src.getAbsolutePath()); // 你的项目路径 + test.txt
  ```
  ## 文件对象的常用方法
  ### 文件各种名称
  * `getName()` 文件名、路径名
  * `getPath()` 路径名 <font color=red>如果是绝对路径，返回完整路径，否则相对路径
  * `getAbsoluteFile()` 绝对路径所对应的File对象
  * `getAbsolutePath()` 绝对路径名
  * `getParent()` 父目录 ,相对路径的父目录，可能为null 如. 删除本身后的结果
### 判断信息  （这里的信息指对文件或者文件夹右键 属性的信息）
* `exists()`  文件是否存在，`ture`表示存在
* `canWrite()`  文件是否可写
* `canRead()`  文件是否可读
* `isFile()`  是否为文件
* `isDirectory()`  是否为文件夹
* `isAbsolute()` 消除平台差异，ie以盘符开头，其他以/开头
* `length`  返回的是文件的字节长度
### 创建、删除
* `createNewFile()`  不存在创建新文件,存在返回false
* `delete()`  删除文件
* `static createTempFile`  (前缀3个字节长，后缀默认.temp) 默认临时空间
* `staticcreateTempFile`  (前缀3个字节长，后缀默认.temp,目录)
* `deleteOnExit()`  退出虚拟机删除,常用于删除临时文件
* <font color=red>注意：这里创建文件的时候，如果路径中有文件夹，当使用这个方法创建时，必须确保该文件夹存在，否则会报`java.io.IOException`异常，系统找不到指定路径
### 操作目录 --> 这里方法可以统一创建父级目录
* `mkdir()`  创建目录，必须确保 父目录存在，如果不存在，创建失败
* `mkdirs()` 创建目录，如果父目录链不存在一同创建 <font color=red>返回值是boolean类型，如果目录已经存在，则返回false
<font color=blue>创建文件之前都可以使用这个方法统一先创建一次目录
* `list()` 文件|目录 名字符串形式
* `listFiles()` 文件|目录 文件对象形式
* `static listRoots()`  根路径
* <font color=blue>利用 `listFiles()`和`static listRoots()`这两个方法可以模仿着做一个文件目录！
### 输出子孙级目录|文件名称（绝对路径）
* `listFiles()` 文件|目录 文件对象形式
* 递归

  eg:
  ```java
  /**
  * 输出子孙级目录|文件的名称(绝对路径)
  * 1、listFiles()
  * 2、递归
  * static listRoots() 根路径
  * Created by BF on 2017/9/21.
  */
  public class Demo05 {
    public static void main(String[] args) {
        String path = "E:/xp/test";
        File parent = new File(path);
        //printName(parent);

        File[] roots = File.listRoots();
        System.out.println(Arrays.toString(roots));
        for (File temp : roots) {
            //printName(temp);
        }
    }
  /**
   * 输出路径
   */
    public static void printName(File src) {
        if (null == src || !src.exists()) {
            return;
        }
        System.out.println(src.getAbsolutePath());
        if (src.isDirectory()) { //文件夹
            for (File sub : src.listFiles()) {
                printName(sub);
            }
        }
    }
  }
  ```
# 文件流（IO）
## 主要讲一个文件输入流与文件输出流
### 接口
  * 文件输入流  InputStream

  * 文件输出流  OutputStream FileOutputStream

### 实现类
* FileInputStream
操作： `byte[] car =new byte[1024];`  +`read`+读取大小
* FileOutputStream
  操作  :  `write()` + `flush`

## 我们来写一个关于文件流操作的工具类(文件的拷贝---简化版)来熟悉它们
### 1、建立联系   File对象   源头 目的地
&emsp;&emsp;文件拷贝的时候先考虑一个：拒绝自己拷贝给自己（即同目录下的拷贝会覆盖，这样做没有意义）
因为这是一个工具类，我们定义两个重载方法由外界传两个参数进来
* 其他程序传两个文件路径进来
`public static void copyDir(String srcPath, String destPath)`
  ```java
    public static void copyDir(String srcPath, String destPath){
      File src = new File(srcPath);
      File dest = new File(destPath);
      // 调用自己的重载方法
      copyDir(src,dest);
    }
  ```

* 其他程序传两个File对象进来
`public static void copyDir(File src, File dest)`
  ```java
    public static void copyDir(File src, File dest){
      // 拒绝自己拷贝给自己
        if (src.getAbsolutePath().equals(dest.getAbsolutePath())){
            System.out.println("拒绝自己拷贝给自己");
            return;
        }
        // 文件夹
        if (src.isDirectory()) {
            dest = new File(dest, src.getName());
            if (dest.getAbsolutePath().contains(src.getAbsolutePath())) {
                System.out.println("父目录不能拷贝到子目录中");
                return;
            }
        }
        // 拷贝文件的具体操作
        copyDirDetail(src, dest);
    }
  ```
### 2、操作、拷贝
* 拷贝文件细节--这里用到了递归创建多级文件夹
`public static void copyDirDetail(File src, File dest)`
  ```java
  public static void copyDirDetail(File src, File dest){
      // 如果文件，则进行拷贝操作
      if (src.isFile()) {
          try {
              // 文件拷贝操作
              FileUtil.copyFile(src, dest);
          } catch (FileNotFoundException e) {
              //e.printStackTrace();
              throw e;
          } catch (IOException e) {
              //e.printStackTrace();
              throw e;
          }
        // 如果是文件夹
      } else if (src.isDirectory()) {
          //确保目标文件夹存在  创建所有不存在的文件夹
          dest.mkdirs();
          //获取下一级目录|文件
          for (File sub : src.listFiles()) {
            // 利用递归创建文件夹与文件
            // eg:文件夹a下有一个文件夹b 要把b下面的文件c复制到文件夹d中
            // 第二次递归参数就是 文件夹 a/b  d/b
            copyDirDetail(sub, new File(dest, sub.getName()));
          }
      }
  }
  ```
* 文件的拷贝
`public static void copyFile(String srcPath, String destPath)`
`srcPath`  源文件路径 文件拷贝的时候原文件必须存在 `destPath` 目录文件路径
  ```java
  public static void copyFile(String srcPath, String destPath) {
      //1、建立联系 源(存在且为文件) +目的地(文件可以不存在)
      copyFile(new File(srcPath), new File(destPath));
  }
  ```
* 文件拷贝重载
`public static void copyFile(File src, File dest)`
  ```java
  public static void copyFile(File src, File dest) {
      // 不是文件或者为null
      if (!src.isFile()) {
          System.out.println("只能拷贝文件");
          throw new IOException("只能拷贝文件");
      }
      // dest为已经存在的文件夹，不能建立于文件夹同名的文件 这里指的是创建的文件没有指定后缀的时候
      if (dest.isDirectory()&&dest.getName().equals(src.getName())) {
          System.out.println(dest.getAbsolutePath() + "不能建立于文件夹同名的文件");
          throw new IOException(dest.getAbsolutePath() + "不能建立于文件夹同名的文件");
      }
      // 如果前面没有将生成后的文件名传入生成路径中，则合成
      if (dest.isDirectory()){
          dest = new File(dest ,src.getName());
      }
      System.out.println(src.getAbsolutePath());
      System.out.println(dest.getAbsolutePath());
      //2、选择流
      InputStream is = new BufferedInputStream(new FileInputStream(src));
      OutputStream os = new BufferedOutputStream(new FileOutputStream(dest));
      //3、文件拷贝   循环+读取+写出
      byte[] flush = new byte[1024];
      int len = 0;
      //读取
      while (-1 != (len = is.read(flush))) {
          //写出
          os.write(flush, 0, len);
      }
      os.flush(); //强制刷出

      //关闭流
      os.close();
      is.close();
  }
  ```
