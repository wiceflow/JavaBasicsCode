# 在 CentOS7 上安装 Tomcat9
本文参考 http://www.cnblogs.com/hapday/p/5616830.html

## Just Do It
- 进入到目录 `/usr/local/` 中 ( 当然这个目录你自己可以随便放,自己记住就好 )：
  `cd /usr/local/`

- 创建目录 `/usr/local/tools`，如果有则忽略：
  `mkdir -p tools`

- 创建 `/usr/local/tomcat` 目录，如果已存在则忽略：
  `mkdir -p tomcat`

- 进入到目录 `/usr/local/tools` 中：
  `cd tools/`

- 下载 `apache-tomcat-9.0.0.M18.tar.gz` 文件：
  `wget http://mirror.bit.edu.cn/apache/tomcat/tomcat-9/v9.0.0.M18/bin/apache-tomcat-9.0.0.M18.tar.gz`
  连接可能失效，具体的包可以百度找找

- 解压缩 `apache-tomcat-9.0.0.M18.tar.gz`：
  `tar -zxvf apache-tomcat-9.0.0.M18.tar.gz`

- 将通过解压得到的 `apache-tomcat-9.0.0.M18` 文件复制到 `/usr/local/tomcat` 目录中：
  `mv apache-tomcat-9.0.0.M18 ../tomcat/`

- 打开文件 `/etc` 目录下的 `profile` 文件：
````
  vim /etc/profile
  将如下代码追加到 profile 文件末尾：
  # idea - tomcat9 config start - 2018-02-28
  # 注意 这里的一定是你设置的目录路径
  CATALINA_HOME=/usr/local/tomcat/apache-tomcat-9.0.0.M18
  CATALINA_BASE=/usr/local/tomcat/apache-tomcat-9.0.0.M18
  PATH=$PATH:$CATALINA_BASE/bin
  export PATH CATALINA_BASE
  # idea - tomcat9 config end - 2018-02-28
  保持并推出:wq!
````

- 修改 `tomcat` 的端口号和字符编码：
  进入到 `/usr/local/tomcat/apache-tomcat-9.0.0.M18/conf` 目录中：
  `cd ../tomcat/apache-tomcat-9.0.0.M4/conf`
  打开 `tomcat` 服务的配置文件 `server.xml`：
  `vi server.xml`
  找到如下代码：
  ````
  <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />
  将其中的 8080 改成 HTTP 协议的默认端口 80，改后的代码如下：
  <Connector port="80" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />
  ````

- 增加 `manager-gui` 图形化管理界面的访问权限（不需要的话，此步骤可忽略）：
  打开 `tomcat` 的用户配置文件 `tomcat-users.xml`：
  `vi tomcat-users.xml`
  在 `</tomcat-users>` 标签前加入如下代码：
  `<user username="tomcat" password="tomcat" roles="manager-gui"/>`
  这里设置的 `username` 和 `password` 都是 `tomcat`，角色为 `manager-gui`  ；
  键入 `Esc` 并输入`:wq!`保持并退出；

- 进入到 `/usr/local/tomcat/apache-tomcat-9.0.0.M18/bin` 目录中：
  `cd ../bin/`

- 打开 `vi catalina.sh` 文件：
  在 `# OS specific support`. 前面加入如下代码
  这里加入的是`JAVA`的环境变量，要加入你们自己的位置
  ````
  JAVA_HOME=/usr/local/jdk/jdk1.8.0_91
  JRE_HOME=$JAVA_HOME/jre
  键入 Esc 并输入:wq!
  ````

- 启动 `tomcat` 服务：
  `./startup.sh`
  打印如下信息则表明启动 `Tomcat` 服务成功：
  ````
  Using CATALINA_BASE:   /usr/local/tomcat/apache-tomcat-9.0.0.M4
  Using CATALINA_HOME:   /usr/local/tomcat/apache-tomcat-9.0.0.M4
  Using CATALINA_TMPDIR: /usr/local/tomcat/apache-tomcat-9.0.0.M4/temp
  Using JRE_HOME:        /usr/local/jdk/jdk1.8.0_91/jre
  Using CLASSPATH:       /usr/local/tomcat/apache-tomcat-9.0.0.M4/bin/bootstrap.jar:/usr/local/tomcat/apache-tomcat-9.0.0.M4/bin/tomcat-juli.jar
  Tomcat started.
  ````
  打开浏览器输入 `IP` 地址看看吧！如果出现了我们的可爱的“小黄猫”则表明我们的 `Tomcat` 已提供服务啦！

- 关闭 `tomcat` 服务
  ` ./shutdown.sh `
  打印如下信息则表明关闭 `Tomcat` 服务成功：
  ````
  Using CATALINA_BASE:   /usr/local/tomcat/apache-tomcat-9.0.0.M4
  Using CATALINA_HOME:   /usr/local/tomcat/apache-tomcat-9.0.0.M4
  Using CATALINA_TMPDIR: /usr/local/tomcat/apache-tomcat-9.0.0.M4/temp
  Using JRE_HOME:        /usr/local/jdk/jdk1.8.0_91/jre
  Using CLASSPATH:       /usr/local/tomcat/apache-tomcat-9.0.0.M4/bin/bootstrap.jar:/usr/local/tomcat/apache-tomcat-9.0.0.M4/bin/tomcat-juli.jar
  ````
  注意：不可连续执行多次 `./shutdown.sh` 命令，多次后报如下错误：
  ````
  SEVERE: Catalina.stop:
  java.net.ConnectException: Connection refused
  at java.net.PlainSocketImpl.socketConnect(Native Method)
  at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:350)
  at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:206)
  at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:188)
  at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
  at java.net.Socket.connect(Socket.java:589)
  at java.net.Socket.connect(Socket.java:538)
  at java.net.Socket.<init>(Socket.java:434)
  at java.net.Socket.<init>(Socket.java:211)
  at org.apache.catalina.startup.Catalina.stopServer(Catalina.java:476)
  at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
  at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
  at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
  at java.lang.reflect.Method.invoke(Method.java:498)
  at org.apache.catalina.startup.Bootstrap.stopServer(Bootstrap.java:408)
  at org.apache.catalina.startup.Bootstrap.main(Bootstrap.java:497)
  ````
- 将 `tomcat9` 加入到系统服务列表中
  进入到 `/etc/init.d` 目录中：
  `cd /etc/init.d`
  创建 `tomcat9` 服务配置文件：  
  ` vi tomcat9`
  将如下代码加入写入到 `tomcat9` 配置文件中：
  ````
  # idea - tomcat config start - 2018-02-28
  # !/bin/bash
  # description: Tomcat Start Stop Restart
  # processname: tomcat
  # chkconfig: 2345 20 80
  JAVA_HOME=/usr/local/jdk/jdk1.8.0_91/
  export JAVA_HOME
  PATH=$JAVA_HOME/bin:$PATH
  export PATH
  CATALINA_HOME=/usr/local/tomcat/apache-tomcat-9.0.0.M18/
  case $1 in
  start)
  sh $CATALINA_HOME/bin/startup.sh
  ;;
  stop)
  sh $CATALINA_HOME/bin/shutdown.sh
  ;;
  restart)
  sh $CATALINA_HOME/bin/shutdown.sh
  sh $CATALINA_HOME/bin/startup.sh
  ;;
  esac
  exit 0
  #chmod 755 tomcat
  #chkconfig --add tomcat
  #chkconfig --level 2345 tomcat on
  #chkconfig --list tomcat
  # idea - tomcat config end - 2018-02-28
  键入 Esc 并输入“:wq!”保持并退出；
  ````
  其中的注意点是将 `JAVA_HOME` 和 `CATALINA_HOME` 变量设置成与我们当前配置相一致的路径；

- 为 `tomcat9` 分配可执行权限：
  `chmod +x tomcat9`

- 将 `tomcat9` 纳入到系统的服务列表中，即添加 `tomcat9` 为系统服务：
  `chkconfig --add tomcat9`

- 查看当前系统服务都有哪些：
  `chkconfig --list`
  也可以查看指定的系统服务，如这里我们指定 `tomcat9` 这个服务：
  `chkconfig --list tomcat9`
  打印如下信息：
  `tomcat9         0:off   1:off   2:on    3:on    4:on    5:on    6:off`
  则表明已将 `tomcat9` 设置为系统服务，2、3、4、5 都为 on 表示可随系统自动启动；

- 我们可以在任意目录下执行关闭、启动、重启 `Tomcat9` 服务啦：
  - 关闭 `tomcat9` 服务：
    `service tomcat9 stop`

  - 启动 `tomcat9` 服务：
    `service tomcat9 start`

  - 重启 `tomcat9` 服务：
    `service tomcat9 restart`
