---
layout: post
title: 体系结构(3)
categories: Architecture
description: 体系结构
keywords: 体系结构
---



# 体系结构

## 第三章 — 理解 Architecture

建筑是多视图的 — 4+1 视图

建筑风格 — 和设计模式不同，设计模式解决复杂问题

Software Architecture={ Component+connector+configuration }





- Component

  协议状态机

  Name：组件类型

  Properties：

  Port：有接口（方法），有规则，有质量特征

  Component 实现机制可以是：模块，层，文件，数据库，进程，网络节点

- Connector

  应该将做连接的东西单独拿出来

  将软件之中的 interaction 连接起来，将散布的东西集中起来，加以管理

  使用 Role， 将 Role 和 Component 的 port 对应起来。

  它不是(钩子) 连接实例，它应该是告诉你怎么去（钩），连接时注意什么，连接时的 Adapter（转换器），连接的规则 等

  Connector 用于描述无法确定位置，比如一个调用过程。

  有时候实现完之后，代码中是找不到 Connector 的，此时为 Connector 隐式实现方式。

  如果实现时用 Adapter，Delegator，Intermediate，Router，那么该 Connector 的实现是显式的。

  **带有质量约束**

- Configuration

  port 和 role 当 provide数>request数 时，就可以进行连接

  与interface 的区别：不注重参数，不要求名称，只注重逻辑上的两个 Components 的连接

### 系统质量：

- 可靠性：å=avg(worktime)/( avg(worktime)+avg(repairtime) )

- 可修改性：本地化

  防止 Ripple Effects（A 调用 B，B 崩溃，A 也崩溃）

  ​	隐藏信息

  ​	对通信路径的限制

  ​	使用 intermediary（中介）

  延迟绑定

- 性能

- 安全

  抵制攻击

  检测攻击

  从攻击恢复

- 可测试性（可以使用秘籍）

  内部监控

- 可用性

  Undo 需要记录操作

  feedback （反馈）不让用户等待一个永远不会返回的结果

- 约束

  - 员工技能：要考虑团队的技能进行设计
  - 成本：时间越紧张越需要购买，外包会省事省钱
  - 生命周期
  - 收益
  - 目标市场
  - ….

