<%--
  Created by IntelliJ IDEA.
  User: 肖中遥
  Date: 2019/12/22
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>资费说明</title>
    <style>
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
        .ul{
            margin:0px;
            padding: 0;
        }
        .htmleaf-header{
            padding: 1em 190px 1em;
            letter-spacing: -1px;
            text-align: center;
        }
        .htmleaf-header h1 {
            color: #fff;
            font-weight: 600;
            font-size: 1.2em;
            line-height: 1;
            margin-bottom: 0;
            font-family: "Segoe UI", "Lucida Grande", Helvetica, Arial, "Microsoft YaHei", FreeSans, Arimo, "Droid Sans", "wenquanyi micro hei", "Hiragino Sans GB", "Hiragino Sans GB W3", "FontAwesome", sans-serif;
        }
        .htmleaf-header h1 span {
            font-family: "Segoe UI", "Lucida Grande", Helvetica, Arial, "Microsoft YaHei", FreeSans, Arimo, "Droid Sans", "wenquanyi micro hei", "Hiragino Sans GB", "Hiragino Sans GB W3", "FontAwesome", sans-serif;
            display: block;
            font-size: 60%;
            font-weight: 400;
            padding: 0.8em 0 0.5em 0;
            color: #c3c8cd;
        }
    </style>
</head>
<body>
<header class="htmleaf-header">
    <h1>资费说明</h1>
</header>
<div style="vertical-align: center" class="htmleaf-container">
    <img src="${pageContext.request.contextPath}/pictures/zifei.png" />
    <br>
    <br>
    <button style="float: none" class = "button bigrounded" type="button" onclick="window.location.href='${pageContext.request.contextPath}/mobileCard/return'">返回</button>
</div>

</body>
</html>
