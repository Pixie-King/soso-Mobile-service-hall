<%@ page import="java.sql.*" language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
            float: left;
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
        .small{
            /*定义小按钮样式*/
            font-size: 11px;
            padding: .2em 1em .275em;
        }
        /*设计颜色样式类：黑色风格的按钮*/
        /*通过设计不同颜色样式类，可以设计不同风格的按钮效果*/
        .black{
            /*黑色样式类*/
            color: #d7d7d7;
            border: solid 1px #333;
            background: #333;
            background: -WebKit-gradient(linear,left top,left bottom,from(#666),to(#000));
            background: -moz-linear-gradient(top,#666,#000);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#666666',endColorstr='#000000');
        }
        .black:hover{
            /*黑色光标经过样式类*/
            background: #000;
            background: -WebKit-gradient(linear,left top,left bottom,from(#444),to(#000));
            background: -moz-linear-gradient(top,#444,#000);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#444444',endColorstr='#000000');
        }
        .black:active{
            /*黑色光标激活样式类*/
            color: #666;
            background: -WebKit-gradient(linear,left top,left bottom,from(#000),to(#444));
            background: -moz-linear-gradient(top,#000,#444);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#000000',endColorstr='#444444');
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
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 300px;
            height: 200px;
            text-align: center;
        }
        .form p{
            /*width: 20%;*/
            /*margin: 160px auto;*/
            border-radius: 5px;
            box-shadow: 0 0 5px rgba(0,0,0,0.1), 0 3px 2px rgba(0,0,0,0.1);
        }
        .form p {
            /*width: 20%;*/

            border-radius: 5px;
            border: 1px solid #fff;
        }
        .form input[type=text],
        .form input[type=password] {
            width: 100%;
            height: 50px;
            padding: 0;
            border: none;
            background: rgba(255,255,255,0.2);
            box-shadow: inset 0 0 10px rgba(255,255,255,0.5);
            text-indent: 10px;
            font-size: 16px;
            color:hsla(0,0%,100%,0.9);
            text-shadow: 0 -1px 1px rgba(0,0,0,0.4);
        }

        .form input[type=text] {
            border-bottom: 1px solid rgba(255,255,255,0.7);
            border-radius: 5px 5px 0 0;
        }

        .form input[type=password] {
            border-top: 1px solid rgba(0,0,0,0.1);
            border-radius: 0 0 5px 5px;
        }
        .form input[type=text]:hover,
        .form input[type=password]:hover,
        .form input[type=text]:focus,
        .form input[type=password]:focus {
            background: rgba(255,255,255,0.4);
            outline: none;
        }
        ul{list-style-type: none;}
        li{margin:0 25px;float: left;}
        a{
            text-decoration: none;
            border:solid 1px;
            padding: 0.4em 0.8em;
            color:#444444;
            background: #ff9999;
            border-color: #fff#aaab9c#aaab9c#fff;
            zoom:1;
        }
        a:hover{
            color:#800000;
            background: transparent;
            border-color: #aaab9c#fff#fff#aaab9c;
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>卡号充值</title>
</head>
<body>
<div class ="htmleaf-container"/>
<div class = "login-panel">
    <form class="form" id = "form" action="${pageContext.request.contextPath}/mobileCard/charge">
        <p>
            <input autocomplete=“new-password” type="text" id="login" name="money"  placeholder="金额">
        </p>
    </form>
    <button class = "button" type="button" onclick="document.getElementById('form').submit();">确认</button>
    <button class = "button" type="button" onclick="window.location.href='${pageContext.request.contextPath}/mobileCard/return'">返回</button>
    <br>
    <p align="center">${smoney}</p>
</div>
</div>
</body>
</html>