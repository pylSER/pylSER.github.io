---
layout: post
title: React Native FlatList 性能调优
categories: Android
description: 
keywords: Android
---

# React Native FlatList 性能调优

## 背景

FlatList 是 Facebook 团队在 2017 年新推出的一个 List 组件，旨在替代原来的 ListView+DataSource，因为原来的列表显示方法存在大内存占用并存在一些 bug. FlatList 给我们提供了更简单，更灵活的方式来创建一个 List.

## 遇到的问题

在移动教学课程系统中，有一个需求是显示班级的学生。既然是显示的列表，那么我选择了使用上述的 FlatList 作为列表组件。在应对普通需求时，FlatList 表现正常，能够及时的进行渲染，并与用户交互。但是如果学生变多(超过50), FlatList 的性能会急剧下降，主要会出现：

- 加载时间过长， 在 FlatList 加载的时候用户看不到元素
- 用户下滑加载好了的 FlatList 列表至最底部时，FlatList 会继续加载未显示的数据，此时的加载的时间过长，会出现用户等待，此时 UI 交互会卡住，ListItem 未渲染但可以下滑，用户继续下滑会看见有的 ListItem 被渲染出来而有的没有。

虽然 FlatList 对外宣称渲染性能变强，但还是没有达到我们的预期。不过原因之一是每一个 ListItem 中存在一定的复杂性，并非简单的 Text, 此外 Separator (分割线) 由用户自己定义，也存在一定的复杂性。



## 我的解决方案

要解决性能问题



