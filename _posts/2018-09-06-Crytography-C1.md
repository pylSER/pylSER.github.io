---
layout: post
title: Crytography & Security 1
categories: Crytography
description: Crytography1
keywords: Crytography
---

# Crytography & Security

## Brief 

### Data Confidentiality

prevent unauthorized reading of data(transimission and storage)

Approaches:

- encryption and decryption  = CIPHER



###Data Integrity

prevent being modified and replaced or check if data is modified or replaced.



### Authentication of Sender and Receiver

make sure the send is the REAL sender

make sure the receiver is the REAL receiver



### Nonrepudiation

类似于指纹和手写签名

双方签名后不能反悔

数字签名



### Secret Sharing



### Cryptographic Protocols



## Some Math Foundations

Let x ∈ Zn = {0, 1, · · · , n − 1}. If there is an integer y ∈ Zn such that

$x ⊗n y =: (x × y ) mod n = 1.$

The integer y is called a multiplicative inverse of x, usually denoted x−1 (it is
unique if it exists).

==模的倒数不一定存在==

当 a和n互质时，a有且唯一有一个multiplicative inverse



Apply the Extended Euclidean Algorithm to a
and n to compute the two integers u and v such that 1 = ua + vn. Then
u mod n is the inverse of a modulo n.