
<%--
  Created by IntelliJ IDEA.
  User: 肖中遥
  Date: 2019/12/22
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>账单查询</title>
    <style type="text/css">
        body{
            background: url(${pageContext.request.contextPath}/pictures/blueScience.jpg)no-repeat fixed;
            background-size: 100%,100%;
        }
        .htmleaf-container{
            margin: 0 auto;
            text-align: center;
            overflow: hidden;
        }
        .button{
            /*定义渐变按钮样式*/
            display: inline-block;
            zoom:1;
            /*zoom和*display都是为了兼容IE7*/
            *dispaly:inline;
            vertical-align: baseline;
            margin: 0 25px;
            align-self: center;
            outline: none;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            font: 14px/100% 黑体;
            padding: .5em 2em .55em;
            /*设计按钮圆角，盒子阴影和文本阴影特效*/
            text-shadow: 0 1px 1px rgba(0,0,0,.3);
            -WebKit-border-radius:.5em;
            -moz-border-radius:.5em;
            border-radius: .5em;
            -WebKit-box-shadow:0 1px 2px rgba(0,0,0,.2);
            -moz-box-shadow:0 1px 2px rgba(0,0,0,.2);
            box-shadow: 0 1px 2px rgba(0,0,0,.2);
        }
        .button:hover{text-decoration: none;}
        .button:active{
            postion:relative;
            top: 1px;
        }
        .bigrounded{
            /*定义大圆角样式类*/
            -WebKit-border-radius:2em;
            -moz-border-radius:2em;
            border-radius: 2em;
        }
        .medium{
            /*定义大按钮样式类*/
            font-size: 12px;
            padding: .4em 1.5em .42em;
        }
        html,body{height: 100%}     /*这里很关键*/
        .outer-wrap{
            /*只有同时为html和body设置height: 100%时，这里的height才生效，
            并且随浏览器窗口变化始终保持和浏览器视窗等高*/
            height: 100vh;
            width:100vw;
            position: relative;
            background-color: rgba(0, 0, 0, .5);
        }
        .login-panel{
            position: absolute;
            top: 20%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 300px;
            height: 200px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class = "htmleaf-container">
    <div class = "login-panel">
        <h1>本月账单查询</h1>
        <p align="left">您的卡号:${s},当月账单:</p>
        <p align="left">套餐资费:${p}</p>
        <p align="left">合计:${m}</p>
        <p align="left">账户余额:${r}</p>
        <button class = "button" type="button" onclick="window.location.href='${pageContext.request.contextPath}/mobileCard/return'">返回</button>
    </div>
</div>
</body>
</html>
