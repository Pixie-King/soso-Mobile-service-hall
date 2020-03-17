<%--
  Created by IntelliJ IDEA.
  User: 肖中遥
  Date: 2019/12/22
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆成功</title>
    <style type="text/css">
        .login-panel{
            float: right;
            width: 300px;
            height: 200px;
            text-align: right;
        }
        .radioGroup {
            -webkit-transform: translateZ(0);
            -ms-transform: translateZ(0);
            -o-transform: translateZ(0);
            transform: translateZ(0);
        }
        .radioGroup > legend {
            color: #79EAC5;
        }
        .radioGroup label {
            position: relative;
            padding-left: 1.3em;
            cursor: pointer;
            -webkit-transition: .2s;
            -o-transition: .2s;
            transition: .2s;
        }
        .radioGroup label:hover {
            color: #FFF;
        }
        .radioGroup label::before, .radioGroup label::after {
            content: '';
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            margin: auto;
            width: 1em;
            height: 1em;
            border-radius: 50%;
        }
        .radioGroup label::before {
            box-shadow: 0 1px 0 rgba(255, 255, 255, 0.25), 0 2px 5px 6px rgba(0, 0, 0, 0.5) inset;
        }
        .radioGroup label::after {
            background: #79EAC5;
            opacity: .2;
            -webkit-transform: scale(0);
            -ms-transform: scale(0);
            -o-transform: scale(0);
            transform: scale(0);
            -webkit-transition: .3s;
            -o-transition: .3s;
            transition: .3s;
        }
        .radioGroup label:hover::after {
            -webkit-transform: scale(0.6);
            -ms-transform: scale(0.6);
            -o-transform: scale(0.6);
            transform: scale(0.6);
            opacity: 1;
            -webkit-transition: 0.2s;
            -o-transition: 0.2s;
            transition: 0.2s;
        }
        .radioGroup input {
            position: absolute;
            left: -24px;
            z-index: -1;
            opacity: 0;
        }
        .radioGroup input:checked + label::after {
            -webkit-transform: scale(0.8);
            -ms-transform: scale(0.8);
            -o-transform: scale(0.8);
            transform: scale(0.8);
            opacity: 1;
            box-shadow: 0 0 15px -1px #79EAC5;
        }

        html {
            height: 100%;
        }

        body {
            height: 100%;
            text-align: center;
            font: 1.5em 'Roboto Condensed', arial;
        }

        fieldset {
            display: inline-block;
            white-space: nowrap;
            color: rgba(255, 255, 255, 0.6);
            padding: 30px 60px 50px;
            border: 4px solid rgba(0, 0, 0, 0.15);
            box-shadow: 0 1px 0 rgba(255, 255, 255, 0.1), 0 -160px 50px -80px rgba(0, 0, 0, 0.1) inset;
            border-radius: 20px;
            margin: 50px auto;
            text-shadow: 1px 3px 1px rgba(0, 0, 0, 0.3);
        }
        fieldset:focus {
            border-color: rgba(0, 0, 0, 0.4);
            outline: none;
        }

        legend {
            padding: 0 20px;
            dislpay: inline-block;
            text-transform: uppercase;
        }

        fieldset label {
            margin: 0 20px;
        }
        @font-face {
            font-family: 'icomoon';
            src:url('${pageContext.request.contextPath}/icons/icomoon.eot?rretjt');
            src:url('${pageContext.request.contextPath}/icons/icomoon.eot?#iefixrretjt') format('embedded-opentype'),
            url('${pageContext.request.contextPath}/icons/icomoon.woff?rretjt') format('woff'),
            url('${pageContext.request.contextPath}/icons/icomoon.ttf?rretjt') format('truetype'),
            url('${pageContext.request.contextPath}/icons/icomoon.svg?rretjt#icomoon') format('svg');
            font-weight: normal;
            font-style: normal;
        }

        [class^="icon-"], [class*=" icon-"] {
            font-family: 'icomoon';
            speak: none;
            font-style: normal;
            font-weight: normal;
            font-variant: normal;
            text-transform: none;
            line-height: 1;

            /* Better Font Rendering =========== */
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        body, html { font-size: 100%; 	padding: 0; margin: 0;}

        /* Reset */
        *,
        *:after,
        *:before {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        /* Clearfix hack by Nicolas Gallagher: http://nicolasgallagher.com/micro-clearfix-hack/ */
        .clearfix:before,
        .clearfix:after {
            content: " ";
            display: table;
        }

        .clearfix:after {
            clear: both;
        }

        body{
            font-family: "Segoe UI", "Lucida Grande", Helvetica, Arial, "Microsoft YaHei", FreeSans, Arimo, "Droid Sans", "wenquanyi micro hei", "Hiragino Sans GB", "Hiragino Sans GB W3", "FontAwesome", sans-serif;
        }
        a{color: #2fa0ec;text-decoration: none;outline: none;}
        a:hover,a:focus{color:#74777b;}

        .htmleaf-container{
            margin: 0 auto;
            text-align: center;
            overflow: hidden;
        }
        .htmleaf-content {
            font-size: 150%;
            padding: 1em 0;
        }

        .htmleaf-content h2 {
            margin: 0 0 2em;
            opacity: 0.1;
        }

        .htmleaf-content p {
            margin: 1em 0;
            padding: 5em 0 0 0;
            font-size: 0.65em;
        }
        .bgcolor-1 { background: #f0efee; }
        .bgcolor-2 { background: #f9f9f9; }
        .bgcolor-3 { background: #e8e8e8; }/*light grey*/
        .bgcolor-4 { background: #2f3238; color: #fff; }/*Dark grey*/
        .bgcolor-5 { background: #df6659; color: #521e18; }/*pink1*/
        .bgcolor-6 { background: #2fa8ec; }/*sky blue*/
        .bgcolor-7 { background: #d0d6d6; }/*White tea*/
        .bgcolor-8 { background: #3d4444; color: #fff; }/*Dark grey2*/
        .bgcolor-9 { background: #ef3f52; color: #fff;}/*pink2*/
        .bgcolor-10{ background: #64448f; color: #fff;}/*Violet*/
        .bgcolor-11{ background: #3755ad; color: #fff;}/*dark blue*/
        .bgcolor-12{ background: #3498DB; color: #fff;}/*light blue*/
        /* Header */
        .htmleaf-header{
            padding: 1em 190px 1em;
            letter-spacing: -1px;
            text-align: center;
        }
        .htmleaf-header h1 {
            color: #fff;
            font-weight: 600;
            font-size: 2.5em;
            line-height: 1;
            margin-bottom: 0;
            font-family: "Segoe UI", "Lucida Grande", Helvetica, Arial, "Microsoft YaHei", FreeSans, Arimo, "Droid Sans", "wenquanyi micro hei", "Hiragino Sans GB", "Hiragino Sans GB W3", "FontAwesome", sans-serif;
        }
        .htmleaf-header h1 span {
            font-family: "Segoe UI", "Lucida Grande", Helvetica, Arial, "Microsoft YaHei", FreeSans, Arimo, "Droid Sans", "wenquanyi micro hei", "Hiragino Sans GB", "Hiragino Sans GB W3", "FontAwesome", sans-serif;
            display: block;
            font-size: 80%;
            font-weight: 400;
            padding: 0.8em 0 0.5em 0;
            color: #c3c8cd;
        }
        /*nav*/
        .htmleaf-demo a{color: #1d7db1;text-decoration: none;}
        .htmleaf-demo{width: 100%;padding-bottom: 1.2em;}
        .htmleaf-demo a{display: inline-block;margin: 0.5em;padding: 0.6em 1em;border: 3px solid #1d7db1;font-weight: 700;}
        .htmleaf-demo a:hover{opacity: 0.6;}
        .htmleaf-demo a.current{background:#1d7db1;color: #fff; }
        /* Top Navigation Style */


        .htmleaf-icon span {
            display: none;
        }
        body{
            background: url(${pageContext.request.contextPath}/pictures/blueScience.jpg)no-repeat fixed;
            background-size: 100%,100%;
        }
        .link2:link {  /*初始样式*/
            color: #376d06;
            text-decoration: underline;
        }
        .link2:hover {   /*鼠标悬浮时*/
            color: #03F;
            text-decoration: none;
            margin-top: 1px;
            margin-left: 1px;
        }
        .link2:active { /*点击中*/
            color: #333;
            text-decoration: none;
            margin-top: 1px;
            margin-left: 1px;
        }
        .link2:visited {  /*点击后*/
            color: #606;
            text-decoration: overline;
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
    </style>
    <link href="${pageContext.request.contextPath}/CSS/Css1.css" rel="stylesheet" type="text/css" />
</head>
<body>

<header class="htmleaf-header">
    <h1>${hello}<span>${date}</span></h1>
</header>

<div class="htmleaf-container">
        <fieldset tabindex="0" class='radioGroup'>
            <legend>大厅业务</legend>
        <button class = "button bigrounded" type="button" onclick="window.location.href='${pageContext.request.contextPath}/mobileCard/chongzhi'">卡号充值</button>
        <button class = "button bigrounded" type="button" onclick="window.location.href='${pageContext.request.contextPath}/mobileCard/chaxun1'">账单查询</button>
        <button class = "button bigrounded" type="button" onclick="window.location.href='${pageContext.request.contextPath}/mobileCard/chaxun2'">余量查询</button>
        <br>
            <br>
        <button class = "button bigrounded" type="button" onclick="window.location.href='${pageContext.request.contextPath}/mobileCard/change0'">套餐变更</button>
        <button class = "button bigrounded" type="button" onclick="window.location.href='${pageContext.request.contextPath}/mobileCard/touse'">使用嗖嗖</button>
        <button class = "button bigrounded" type="button" onclick="window.location.href='${pageContext.request.contextPath}/mobileCard/detail'">消费详情</button>
        <br>
            <br>
        <button class = "button bigrounded" type="button" onclick="window.location.href='${pageContext.request.contextPath}/zifei2.jsp'">资费说明</button>
        <button class = "button bigrounded" type="button" ondblclick="window.location.href='${pageContext.request.contextPath}/mobileCard/zhuxiao'">办理退网</button>
        <button class = "button bigrounded" type="button" onclick="window.location.href='${pageContext.request.contextPath}/mobileCard/towel'">退出登录</button>
        </fieldset>
    </div>

</body>


</html>
