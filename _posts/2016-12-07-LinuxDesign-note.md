---
layout: post
title: Linux 程序设计(2)
categories: LinuxDesign
description: Linux 程序设计(2)
keywords: LinuxDesign
---

# Linux 程序设计(2)

fat32 ：每个文件夹下放的文件个数是有限的

如果希望硬盘格式为 GPT 的时：

1. 选择较新的操作系统
2. 主板必须支持 GPT （否则启动时，主板会找不到分区）

intel 更新了 主板标准 整个框架 — EFI

3. 所以，主板要支持 EFI

#### Linux 启动流程

电源键—>BIOS（烧在主板）—>boot loader —> load kernel —>init（第一个用户态程序启动其他应用程序） —>system ready



### boot loader （引导程序 eg. GRUB）

引导程序的二进制码不是以文件形式存在硬盘上的，且看不见

提供选择操作系统的菜单

引导程序 可以存到硬盘的第一个扇区（MBR 第一个记录）

​		也可以写到 硬盘的分区上

#### 分区：

**/boot**： 用作引导，保存内核二进制，和内核能交互的用户态程序（用来加载应用程序和各种驱动），grub 的配置

​		在 grub 中分配 Windows ：不必找 Windows 内核，找 Windows 自带的引导程序即可



#### 安装一个应用程序

1. 获得源代码的 tar.gz 

tar — 将多个文件打包

gz — 将一个文件压缩成更小的文件



./configure 预编译，检查编译环境

make 编译连接

su - 申请权限

make install 安装

2. 安装包

ubuntu dvebian 的安装包格式： .deb

redhat 的安装包格式： .rpm

注意：Linux 软件可能会依赖其他的软件

3. apt-get（yum if redhat）

   到对应的服务器上下载依赖的软件（没装的话），安装该软件





命令行：$—普通用户

​		‘#—root 用户

