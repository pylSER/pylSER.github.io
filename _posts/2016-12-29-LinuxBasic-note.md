---
layout: post
title: Linux 系统基础 (5)
categories: LinuxBasic
description: LinuxBasic
keywords: LinuxBasic
---

# Linux 系统基础(5)

> ## 文件扩展名，类型

文件的属性比文件的扩展名重要。

.lock: 锁文件 判断程序或设备是否被占用



### 硬连接和软连接（符号链接）：

硬连接：相当于别名 ln   z   z_hard   z 和 z_hard 是等同的,相当于可以有多个指针，

 -rw-rw-rw- **2 ** 2:指硬连接的引用次数，次数为 0 时，操作系统会删除真正的 datablock。二者的 inode号相同。

- 硬连接不能夸分区。
- 硬连接不能连接到目录

软连接： 相当于快捷方式  ln  -s    z     z_soft 



![hardlink](/images/posts/linux/hardlink.png)



**dd:**  创建一个有大小的文件（也有备份的功能）

在 ls -l 中 第二列显示的是硬连接数 

 

### 统计文件

**wc** 命令









