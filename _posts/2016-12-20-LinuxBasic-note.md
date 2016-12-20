---
layout: post
title: Linux 系统基础 (3)
categories: LinuxBasic
description: LinuxBasic
keywords: LinuxBasic
---



# Linux 系统基础(3) 

> ## 第四章

### 运行级别

不同的操作系统定义了不同的运行级别，不同的运行级别会允许加载不同的应用程序，（eg. GUI）

更改运行模式：

ln -sf /lib/systemd/system/runlevel3.target   /etc/systemd/system/default.target    把运行级别 3 链接到默认上，重启

centOS 中，运行模式 3 是命令行，运行模式 5 是 GUI 

`#--代表 root `

`$ -- 代表其他用户登录`

pts — 图形环境下的用户文件

tty — 命令行下的用户文件     在 dev/ttyX 下



#### init 命令 — 强大的命令 参数 0～6（运行级别）

runlevel  : 查看运行级别   **N 2  之前没有切换过运行级别  目前是2**

init X — 会调用  /etc/rc.d/rc<X>.d  来初始化

运行级别在 /etc/rc.d/rc<X>.d 目录中来定义

不同发行版 对 X 的定义不同：

centOS：

- 0: 关机
- 6: 重启
- 1: 单用户模式
- 5: 完整的多用户 （GUI）  
- 4: 没有使用
- 3: 完整的的多用户模式 （字符）
- 2: 



#### man 命令

在线文档和帮助

man X a

X — 章节 

a — 命令

不同章节对应内容不同

ctrl + r : 搜索命令

!$ 重复上一个命令的参数

#### help

相对 man 而言，显式的信息较为简略

mkdir —help



***注意：**Linux 参数 是一个字母时用 一个-  是一个单词时用两个- - 

如 -u，--help





#### 使用 “ ; ”

命令1；命令2

先执行命令1，不管命令1是否执行正确，再执行命令2

#### 使用 “&&”

命令2只有在命令1正确的前提下才执行



#### 替换机制

$(pidof less)



#### 命令的别名

alias

#### 管道

命令1 | 命令2 

将命令1的结果传入命令2



#### 重定向

重定向不使用系统默认的标准输入输出接口，而是重定向到其他位置：

- 输出重定向：

  将命令的返回值输出到文件中，如果存在同名文件则替换

  **command > filename**

- 输入重定向

  将文件内容作为 指令输入

  **command < filename**

- 错误重定向

  将命令的出错信息输出到文件中

  **command 2> filename**

- 追加重定向

  将命令的返回值输出到文件中，如果存在同名文件则追加

  **command >> filename**




命令行下终端是 **tty**

GUI 下是 **tts**



#### Whereis

显示命令的文件的目录

如：whereis ls




