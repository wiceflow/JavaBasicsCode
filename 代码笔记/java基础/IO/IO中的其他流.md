# IO中的其他流  (建议看这篇文章之前先看另外一篇File)
## 关于文件的读写与字节流在另外一个文件里面
## 字符流  (具体就不贴代码了，在JavaBasicsCode中)
### 字符流:只能处理 纯文本，全部为可见字符  .txt  .html
### 节点流:
&emsp;&emsp;Reader FileReader
&emsp;&emsp;Writer FileWriter

### 纯文本读取
* 1、建立联系
* 2、选择流 `Reader FileReader`
* 3、读取 `char[] flush =new char[1024]`;
* 4、关闭
### 纯文本写出
* 1、建立联系
* 2、选择流   Writer FileWriter
* 3、读取 write(字符数组,0,长度)+flush
	&emsp;&emsp;&emsp;&emsp;write(字符串)
	&emsp;&emsp;&emsp;&emsp;append(字符|字符串)
4、关闭

## 缓冲流(推荐加上，提高性能)
### 字节缓冲流(没有新的方法)
* `BufferedInputStream`
直接加上即可  字节读取流
`InputStream is = new BufferedInputStream(new FileInputStream(src));`

* `BufferedOutputStream`
直接加上即可  字节读取流
`OutputStream os = new BufferedOutputStream(new FileOutputStream(dest));`

### 字符缓冲流(有新的方法)
* `BufferedReader`
新的方法是   &emsp;&emsp;`readLine()` 读取一行数据

* `BufferedWriter`
新的方法是   &emsp;&emsp;` newLine()` 添加换行符，相当于`\r\n`

## 转换流字节流 转为字符流   处理乱码(编码集、解码集)
### 编码与解码概念
*  编码：  字符   ---编码字符集>二进制
*  解码 : 二进制  ---解码字符集->字符

### 乱码
* 编码与解码的字符集不统一
* 字节缺少，长度丢失
### 文件乱码（只能进行二进制解码操作）  
* InputStreamReader(字节输入流,"解码集")
* OutputStreamWriter(字符输出流,"编码集")
