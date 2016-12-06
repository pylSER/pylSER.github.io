---
layout: post
title: Linux 程序设计
categories: LinuxDesign
description: Linux
keywords: Linux Design
---

# Linux 程序设计(1)

邮箱：[eytang@foxmail.com](mailto:eytang@foxmail.com)

- 参考书：Beginning Linux Programming  Neil Mathew，GNU／Linux 环境编程



## 第一章

Linux是一个类 Unix 的操作系统，GNU

特点：开源，流行，支持绝大多数的硬件平台

### 整个硬盘的分区组织方式

引到分区不属于主分区 

主要分为 MBR 和 GPT 两种

- MBR

  对每一块容量存在上限：4T 以内

  整个硬盘只能直接分出4个主分区

  不过，可以对主分区再进行一次分区，这时还能分出4个分区，叫逻辑分区。此时的主分区叫扩展分区。

  ​

- GPT

  支持 EFI

  主分区很多，可以达到 128 个。

  可以对每个分区指定文件系统（eg. ntfs, fat32, ext4, jfs）（存放数据的格式）

关于**文件系统**：

​	fat32: 每个文件不能大于 4g

