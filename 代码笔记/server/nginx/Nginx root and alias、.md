# Nginx中 root 与 alias 的区别  

`nginx`指定文件路径有两种方式`root`和`alias`，指令的使用方法和作用域：  

- root  

  语法：`root path`
  默认值：`root html`
  配置段：`http、server、location、if  `

- alias  

  语法：`alias` `path`
  配置段：`location  `

`root`与`alias`主要区别在于`nginx`如何解释`location`后面的`uri`，这会使两者分别以不同的方式将请求映射到服务器文件上。

- root的处理结果是：root路径＋location路径
- alias的处理结果是：使用alias路径替换location路径

alias是一个目录别名的定义，root则是最上层目录的定义。



## root 实例  

```shell
location ^~ /test/{
    root /data/;
}
```

如果一个请求是 `localhost/test/iceflow/index.html` ，web服务器将会返回服务器上的`/data/test/iceflow/index.html`文件  

## alias 实例  

```shell
location ^~ /test/{
    alias /data/;
}
```

如果一个请求是`localhost/test/iceflow/index.html`，web服务器将会返回服务器上的`/data/iceflow/index.html`文件，匹配路径中的 `test`是不会被写入匹配路径的。  



> 注意：   alias在使用正则匹配时，必须捕捉要匹配的内容并在指定的内容处使用。
>
> ​               alias只能位于location块中。（root可以不放在location中）