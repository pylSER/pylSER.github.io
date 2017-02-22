---
layout: post
title: 体系结构 — 风格2
categories: Architecture
description: 体系结构
keywords: 体系结构
---

# 体系结构

> ## 第六章— 风格2



### **进程**级别的风格

1. 如果一个进程知道对方进程的 ip 和 port，那么它们在同一空间。
2. 同一时间

进程通过时间和空间来解耦

![timespace](/Users/peiyulin/Documents/pylSER.github.io/images/posts/com/timespace.png)

### 点到点风格

需要对方进程的位置，和对方同时存在（时间，空间）





### 物理级别的风格

#### Client -Sever

永远是 Client 对 Server 发起请求，不可能 Server 主动

Server 必须是稳定的

#### N-Tier 风格

物理层上面的多层服务器

（分布式）数据库服务器+（分布式）服务器



#### Peer to Peer P2P 风格

Peer 既是Client，也是Server

是 CS 的通用版

适用于单个服务器应对不了的情况

每个Peer 通过心跳算法知道其他人的信息

Monitor 要率先启动，获得大家的地址，再广播给大家



#### 分布式风格

通信的发起者不知道调用的位置

