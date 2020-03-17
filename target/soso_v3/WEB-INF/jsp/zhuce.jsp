<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖中遥
  Date: 2019/12/22
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>注册</title>
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

        .related {
            color: #fff;
            background: #333;
            text-align: center;
            font-size: 1.25em;
            padding: 0.5em 0;
            overflow: hidden;
        }

        .related > a {
            vertical-align: top;
            width: calc(100% - 20px);
            max-width: 340px;
            display: inline-block;
            text-align: center;
            margin: 20px 10px;
            padding: 25px;
            font-family: "Segoe UI", "Lucida Grande", Helvetica, Arial, "Microsoft YaHei", FreeSans, Arimo, "Droid Sans", "wenquanyi micro hei", "Hiragino Sans GB", "Hiragino Sans GB W3", "FontAwesome", sans-serif;
        }
        .related a {
            display: inline-block;
            text-align: left;
            margin: 20px auto;
            padding: 10px 20px;
            opacity: 0.8;
            -webkit-transition: opacity 0.3s;
            transition: opacity 0.3s;
            -webkit-backface-visibility: hidden;
        }

        .related a:hover,
        .related a:active {
            opacity: 1;
        }

        .related a img {
            max-width: 100%;
            opacity: 0.8;
            border-radius: 4px;
        }
        .related a:hover img,
        .related a:active img {
            opacity: 1;
        }
        .related h3{font-family: "Microsoft YaHei", sans-serif;font-size: 0.65em;}
        .related a h3 {
            font-weight: 300;
            margin-top: 0.15em;
            color: #fff;
        }
        /* icomoon */
        .icon-htmleaf-home-outline:before {
            content: "\e5000";
        }
        .icon-htmleaf-arrow-forward-outline:before {
            content: "\e5001";
        }
        @media screen and (max-width: 50em) {
            .htmleaf-header {
                padding: 3em 10% 4em;
            }
            .htmleaf-header h1 {
                font-size:2em;
            }
        }
        @media screen and (max-width: 40em) {
            .htmleaf-header h1 {
                font-size: 1.5em;
            }
        }
        @media screen and (max-width: 30em) {
            .htmleaf-header h1 {
                font-size:1.2em;
            }
        }
        body{
            background: url(${pageContext.request.contextPath}/pictures/blueScience.jpg)no-repeat fixed;
            background-size: 100%,100%;
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
    </style>
</head>
<body>
    <header class="htmleaf-header">
        <h1>欢迎使用嗖嗖移动<span>Soso MobileCard</span></h1>
    </header>
    <div class="htmleaf-container">
        <form class="form" id = "form2" action="${pageContext.request.contextPath}/mobileCard/zhuce2">
    <fieldset tabindex="0" class='radioGroup'>
        <legend>请选择卡号:</legend>
            <input id='g1o1' name="group1" value="1" checked type="radio">
            <label style="float: left" id = "g1o11" for='g1o1'>${key1}</label>
            <input  id='g1o2' name="group1" value="2" type="radio">
            <label style="float: left" id = "g1o21" for='g1o2'>${key2}</label>
            <input id='g1o3' name="group1" value="3" type="radio">
            <label style="float: left" id = "g1o31" for='g1o3'>${key3}</label>
        <br>
        <input id='g4' name="group1" value="4" checked type="radio" style="left: 2px">
        <label style="float: left" id = "g1o41" for='g4'>${key4}</label>
        <input  id='g5' name="group1" value="5" type="radio" style="left: 2px">
        <label style="float: left" id = "g1o51" for='g5'>${key5}</label>
        <input id='g6' name="group1" value="6" type="radio" style="left: 2px">
        <label style="float: left" id = "g1o61" for='g6'>${key6}</label>
        <br>
        <input style="padding-left: 20px" id='g7' name="group1" value="7" checked type="radio">
        <label style="float: left" id = "g1o71" for='g7'>${key7}</label>
        <input id='g8' name="group1" value="8" type="radio">
        <label style="float: left" id = "g1o81" for='g8'>${key8}</label>
        <input id='g9' name="group1" value="9" type="radio">
        <label style="float: left" id = "g1o91" for='g9'>${key9}</label>
    </fieldset>
    <br>
    <fieldset tabindex="1" class='radioGroup'>
        <legend>请选择套餐:</legend>
        <input id='g1o4' name="group2" value="1" type="radio">
        <label for='g1o4'>话痨套餐</label>
        <input id='g1o5' name="group2" value="2" checked type="radio">
        <label for='g1o5'>网虫套餐</label>
        <input id='g1o6' name="group2" value="3" type="radio">
        <label for='g1o6'>超人套餐</label>
    </fieldset>
            <div class="login-panel" style="margin-top: 300px">
        <p>
            <input autocomplete="new-password" type="text" id ="usename" name = "name" placeholder="姓名">
            <input autocomplete="new-password" type="password" id="usepwd" name="password" placeholder="密码">
            <input autocomplete=“new-password” type="text" name="money" id="money" placeholder="充值金额">
        </p>
</div>
            <br>
            <br>
    </form>
        <div class="htmleaf-container" style="margin-top: 150px">
        <button class = "button bigrounded" type="button" onclick="document.getElementById('form2').submit();">确认</button>
        <button class = "button bigrounded" type="button" onclick="window.location.href='${pageContext.request.contextPath}/mobileCard/towel'">返回</button>
        <button class = "button bigrounded" type="button" onclick="window.location.href='${pageContext.request.contextPath}/zifei.jsp'">资费说明</button>
        <p>${key}</p>
            </div>
</div>
</body>
<script src="jquery-3.4.1.js">

    $(document).ready(function(){
        document.getElementById('g1o104').innerText = "ids";
    });
</script>
</html>
