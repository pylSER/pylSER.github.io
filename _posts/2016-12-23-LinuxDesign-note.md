---
layout: post
title: Linux 程序设计(6)
categories: LinuxDesign
description: LinuxDesign
keywords: LinuxDesign
---



# Linux 程序设计(6)

> ## shell 编程
>

不同的 shell 风格不同

利用 echo 和 重定向 创建文件

脚本可以在命令行直接输入，运行

也可以写脚本文件



### 脚本文件

\# —> 注释

exit code 退出码

使用分号或者换行分割命令，结尾没有分号



#### 写好脚本之后，怎么执行脚本呢？

1. sh + 脚本文件名

2. 给脚本设一个执行权限：chomd +x 脚本文件名 (chown/chgrp 可选)

   执行 ./脚本文件名 (这个应该是绝对路径)

3. source 脚本文件名 ／ . 脚本文件名

区别：

方法 1 和 方法 2 ：会开一个子进程执行脚本

方法 3 ：使用当前进程执行脚本

#### 什么情况效果会不一样呢？

在脚本文件里 更改环境变量 export VAR=….

如果采用 法1 和 法2: 当前进程的环境变量不会被改掉

法3 ：会被修改

因为环境变量只会影响当前进程和其子进程

#### 系统会默认执行到的脚本

.bash_profile  登录时执行

.bash_logout 注销的时候执行

.bashrc   新开命令行的时候执行

环境变量设置完关机会消失，把 export 指令放到 .bash_profile 或 .bashrc里，让它每次开启时执行，就可以长久保存了

## 脚本的写法

### 变量：

#### 用户变量：

基本没有类型的概念，可以理解成全部是**字符串**

赋值：var=value 等号前后不能有空格

使用：echo $var



**read** 相当于 scanf   （read var：输入存到var／read 输入存到 REPLY）

read -p  "abc"  先打印abc，在等待输入

read -t  5   最多等待5秒输入

read  -n1  只读入第一个字符

read -s 不显示输入



#### 引号的用法

单引号：不会解释单引号内的内容。 '$PATH' 就是字符串 

\ :   转义  \\$PATH  就是字符串 

双引号：会进行解释，但是只解析 $ ,` ,\\  



#### 环境变量

环境变量相当于一个系统全局变量

$ HOME 当前用户登陆目录路径

$ PS1 :命令行提示符 

$ PS2: 辅助提示符

$ IFS  空格符 通常是空格



#### 参数变量

$ # 获得传入参数的个数

$0 获得脚本程序的名字

$1 获得第一个参数

$* 获得所有参数, trim过的

$@  获得所有参数，未处理的，保留空格和 TAB

*注意 bash 下 $10 不行

**$((表达式)) 会将表达式处理为整数型算数**

**$(命令) 执行该命令，并将结果替换到这个变量**   =  `命令`



### 条件判断

test + 表达式/ [  表达式  ] ：判断表达式的真假

if [] 判断

字符串比较

str1 = str2

str1 != str2

算术比较

expr1 -eq expr2  把expr1 和 expr2 以数字的形式比较



与文件有关的测试：

-e 文件名 文件存在？

-d 文件名   是目录？



逻辑操作：

！expr

expr1 -a expr2       = and

expr1 -o expr2  	   =or

 

case 语句：

```shell
case str in 

	str1|str2) statements;;

	*) statements;;

esac
```

循环：

for ／while／ until ／select

```bash
for

for var in list

do

statements

done
```











