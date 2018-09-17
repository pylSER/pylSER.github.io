---
layout: post
title: Recognition System 1
categories: RecognitionSystem
description: Recognition System1
keywords: Recognition System
---

# Recognition System

intensity 亮度

image intensity表示单通道图像像素的强度（值的大小）。

在灰度图像中，它是图像的灰度。

在RGB颜色空间中，可以理解把它为是R通道的像素灰度值，G通道的像素灰度值，或是B通道的像素灰度值，也就是RGB中含三个image intensity。

## Image model

$f(x,y)=i(x,y)*r(x,y)$

i(x,y) -- 发光源

r(x,y) -- 物体自己的反射光  0=完全吸收光线 1=完全反射光线

f(x,y) -- 看见的图像

## Pixelation

像素化 

降低图片的分辨率

可以用 **Multi-Resolution** 方法加速图像识别

- 先匹配低像素的图，获得初步的结果
- 将初步结果和较高像素的图对比，逐步获得识别结果
- 速度快

## 将 Intensity 离散化

Intensity quantization

A\B=inv(A)*B



