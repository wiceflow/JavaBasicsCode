# Redis学习笔记

## Redis服务安装与设置
#### redis下载地址
http://redis.io/download









## Redis数据类型指令
### 字符串数据类型
Redis中最简单的数据结构，它既可以存储文字，helloword，又可以存储数字，10086，和浮点数3.14，还可以进行二进制的存储10010100
Redis为这几种类型的值分别设置了相应的操作命令，让用户可以针对不同的值做不同的处理。

- SET key value
将字符串键 key 的值设置为value，命令返回ok表示设置成功
如果字符串键key已经存在，那么用新值覆盖旧值
