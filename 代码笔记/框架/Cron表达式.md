## `Cron` 的使用  

一篇一看就懂的 `cron` 表达式文章  

### `cron` 表达式实际上是一串字符串  

> 【秒】 【分】 【时】 【日】 【月】 【周】 【年】

其中【年】这个占位符为 非必填字段，因此`cron` 表达式通常由六到七部分组成，内容的取值为数字或  `cron` 表达式约定的特殊字符，这些特写字符称为 **通配符** ，每一个通配符分别代指一种值，`cron` 表达式可以用一下几种格式表示  

| 顺序 |    取值范围    | 通配符取值范围 |
| :--: | :------------: | :------------: |
|  秒  |      0~60      |    , - * /     |
|  分  |      0~60      |    , - * /     |
|  时  |      0~23      |    , - * /     |
|  日  |      1-31      |    , - * /     |
|  月  | 1-12 / JAN-DEC | , - * ? / L W  |
|  周  | 1-7 / SUN-SAT  | , - * ? / L #  |
|  年  |   1970-2099    |    , - * /     |

我们以另外一个表格来解释每一个通配符的意思  

| 通配符 |  代表的值  | 解释                                                         |
| :----: | :--------: | :----------------------------------------------------------- |
|   *    |   所有值   | 如：时字段为*，代表每小时都触发                              |
|   ?    |  不指定值  | 如：周字段为?，代表表达式不关心是周几                        |
|   -    |    区间    | 如：时字段设置2-5，代表2，3，4，5点钟时都触发                |
|   ,    |   多个值   | 如：时字段设置2,3,5，代表2，3，5点都会触发                   |
|   /    |   递增值   | 如：时字段设置0/2，代表每两个小时触发，时字段设置 2/5，代表从2时开始每隔5小时触发一次 |
|   L    |   最后值   | 如：日字段设置L，代表本月最后一天                            |
|   W    | 最近工作日 | 如：在日字段设置13W，代表没约13日最近的那个工作日触发一次    |
|   #    |    序号    | 如：在周字段设置5#2，代表每月的第二个周五                    |



最后我们来看一组实例加深理解  

- 每 2 秒执行一次 `0/2 * * * * ?`  
- 每5分钟执行一次 `0 0/5 * * * ?`  
- 1分、12分、45分执行一次 `0 1,12,45 * * * ?`  
- 每天23点59分59秒执行一次 `59 59 23 * * ? `  
- 每月15号凌晨3点执行一次 `0 0 3 15 * ? `  
- 每月最后一天12点执行一次 `0 0 12 L * ? `  