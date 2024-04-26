# 实验耗材采购系统--部署文档

## 简介

项目名称为 采购系统，是一个前后端分离项目，前后端分别使用ssm和Vue框架，数据库使用mysql5.8稳定版本。

## 使用工具

- 使用了maven包管理工具
- Mybatis-Generator生成sql代码
- 使用了Element-Ui的组件
- JSON传递接口数据
- 后端tomcat 作为服务器
- 前端使用Node.js

## 项目结构

-- backend 后端源码

-- front 前端源码

-- purchase.sql 数据库源码

## 如何使用

### 数据库

创建数据库purchase，并在数据库中运行purchase.sql 数据库脚本文件

### 后端（JAVA1.8）

1、安装idea

2、安装maven，并将项目导入

3、使用maven安装好所有的包

4、配置好tomcat

5、运行PurchaseApplication启动类

### 前端

1、安装好node.js

2、使用`npm install `安装需要的包

3、使用` npm run dev`运行



