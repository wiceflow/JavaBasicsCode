# Nginx 实时生成缩略图  

## 一、加载图片模块  

​        缩略图的生成有多种方式，如使用`java`生成缩略图，也可以使用`nginx+lua`实现，下面我们讲解一下使用`nginx`自带的模块生成缩略图，模块：`-with-http_image_filter_module`。  

​        这里默认大家已经装好了`nginx`，如果没有装好可以看我的另外一篇文章`Centos7 install ngxin`  

将`-with-http_image_filter_module`模块添加进`nginx`中（有一篇笔记专门讲如何往已经存在的`nginx`添加模块）  

## 二、配置  

生成缩略是个消耗`cpu`的操作，如果访问量比较大的站点，最好考虑使用程序生成缩略图到硬盘上，或者在前端加上`cache`或者使用`CDN`。这个适合小数据量的请求  

进入`nginx conf`目录 

```shell
cd /usr/local/nginx/conf
vim nginx.conf
```

往配置文件中加入以下信息（后面慢慢讲作用）  

```shell
location ~* ^/images {
            root /data/file/;
            if ( $request_uri ~* images/(\S+)\?width=(\d+)&height=(\d+) ){
                set $image_path $1;
                set $width $2;
                set $height $3;
                proxy_pass http://localhost/thumbnail/$image_path?width=$width&height=$height;
                break;
            }

            if ( $request_uri ~* images/(\S+)\?width=(\d+)$ ){
                set $image_path $1;
                set $width $2;
                proxy_pass http://localhost/thumbnailwidth/$image_path?width=$width;
                break;
            }

            if ( $request_uri ~* images/(\S+)\?height=(\d+)$ ){
                set $image_path $1;
                set $height $2;
                proxy_pass http://localhost/thumbnailheight/$image_path?height=$height;
                break;
            }
        }

        location /thumbnail {
            alias /data/file/;
            image_filter resize $arg_width $arg_height; #指令根据height和width参数生成相应缩略图
            image_filter_jpeg_quality 75;
            image_filter_buffer 10m;
            access_log off;
        }
location /thumbnailwidth{
            alias /data/file/;
            image_filter resize $arg_width -; #指令根据width生成缩约图
            image_filter_jpeg_quality 75;
            image_filter_buffer 10m;
            access_log off;
        }

        location /thumbnailheight{
            alias /data/file/;
            image_filter resize - $arg_height; #指令根据height按照比例生成缩略图
            image_filter_jpeg_quality 75;
            image_filter_buffer 10m;
            access_log off;
        }

```

上面的配置大致意思是：  

当一个url访问了 localhost/images/test/1.png  

那么首先会被 第一个正则`location`所拦截  并进行下面的匹对，若无匹对则将图片原图返回。  

主要的参数信息：  

- image_filter resize  图片的大小  宽度和高度  若只想设置其一，则另外一个参数用 `-`表示  
- image_filter_jpeg_quality  图像的质量，这个值越高对CPU的消耗越大  
- image_filter_buffer  设置读取图像缓冲的最大大小  若图片大小超过了 则会报415错误
- image_filter_size  输出图片信息  `{ "img" : { "width": 100, "height": 100, "type": "gif" } }`出错输出为`{}`  
- image_filter rotate 90 | 180 | 270  旋转  只能是这三个值   不然`nginx`启动会报错  

## 三、启动测试  

先在`/data/file/`目录下添加几张照片，然后用浏览器访问  

`localhost/images/1.jpg`  因为后面没有带参数，则浏览器会将原图返回  



参考文章：https://blog.csdn.net/zhu_tianwei/article/details/44886927  

