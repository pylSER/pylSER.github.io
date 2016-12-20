---
layout: post
title: Linux 程序设计(4)
categories: LinuxDesign
description: LinuxDesign
keywords: LinuxDesign
---



# Linux 程序设计（4）

### 接上回文件类型：

#### 权限

r：读权限

w：写权限

x：执行权限

**-rwx rwx rwx** : 分为3个用户

前3个：当前拥有者对文件的权限

中间3个：处理当前用户，用户组里的其他账号

后3个：除以上之外其他账号

root 账号对所有文件都是 **rwx**

eg. drwxr-xr-x            5               peiyulin                   staff                170                     5  4  2016                     tomcat

​       文件权限       硬连接数       拥有者(可以改)      所在用户组       大小(字节)          最近一次修改时间         文件名



#### 修改权限

chmod  a-w a.txt 将权限全部去掉  

​		u - x 拥有者的执行权限去掉

​		g + r 用户组添加读权限

​		o = w 将其他用户的权限设为 写

也可以用数字 chmod 777/555/444/644/765….  二进制映射 rwxrwxrwx

普通文件：644

如果目录的没有执行权限，那么它里面的文件也没有执行权限





### 进程

注意：命令行本身也是一个进程：**shell** 进程

 只有一个进程没有父进程，就是用户态启动的第一个进程：  initrd.img-4.4.0-43-generic（第一个用户态程序启动其他应用程序）

vmlinuz-4.4.0-43-generic 保存内核程序

**ps -A** 查看所有进程

**pstree** 用树的方式查看进程的父子关系 

**ctrl+z** 暂停进程

**jobs** 可以看后台有几个执行的挂起进程

**fg N**: 进程切换到前台 N是 jobs 里的序号

**bg N**：切换到后台运行

**nohup**：会忽略 ctrl+z，ctrl+c

**nice**：更改进程优先级

**renice**

**top：**查看 cpu 占有率最高的进程    



**chown**：更改拥有者

**chgrp**：更改用户组

**find**：查找文件 *

**grep**: 一个文件中的字符串匹配 