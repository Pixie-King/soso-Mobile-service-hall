<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 肖中遥
  Date: 2019/12/23
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>消费详情</title>
    <style type="text/css">
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
        .login-panel{
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 300px;
            height: 200px;
            text-align: center;
        }
        body {
            font-family: 'Open Sans', sans-serif;
            font-weight: 300;
            line-height: 1.42em;
            color:#A7A1AE;
            background: url(${pageContext.request.contextPath}/pictures/blueScience.jpg)no-repeat fixed;
            background-size: 100%,100%;
        }

        h1 {
            font-size:3em;
            font-weight: 300;
            line-height:1em;
            text-align: center;
            color: #4DC3FA;
        }

        h2 {
            font-size:1em;
            font-weight: 300;
            text-align: center;
            display: block;
            line-height:1em;
            padding-bottom: 2em;
            color: #FB667A;
        }

        h2 a {
            font-weight: 700;
            text-transform: uppercase;
            color: #FB667A;
            text-decoration: none;
        }

        .blue { color: #185875; }
        .yellow { color: #FFF842; }

        .container th h1 {
            font-weight: bold;
            font-size: 1em;
            text-align: left;
            color: #185875;
        }

        .container td {
            font-weight: normal;
            font-size: 1em;
            -webkit-box-shadow: 0 2px 2px -2px #0E1119;
            -moz-box-shadow: 0 2px 2px -2px #0E1119;
            box-shadow: 0 2px 2px -2px #0E1119;
        }

        .container {
            text-align: left;
            overflow: hidden;
            width: 80%;
            margin: 0 auto;
            display: table;
            padding: 0 0 8em 0;
        }

        .container td, .container th {
            padding-bottom: 2%;
            padding-top: 2%;
            padding-left:2%;
        }

        /* Background-color of the odd rows */
        .container tr:nth-child(odd) {
            background-color: #323C50;
        }

        /* Background-color of the even rows */
        .container tr:nth-child(even) {
            background-color: #2C3446;
        }

        .container th {
            background-color: #1F2739;
        }

        .container td:first-child { color: #FB667A; }

        .container tr:hover {
            background-color: #464A52;
            -webkit-box-shadow: 0 6px 6px -6px #0E1119;
            -moz-box-shadow: 0 6px 6px -6px #0E1119;
            box-shadow: 0 6px 6px -6px #0E1119;
        }

        .container td:hover {
            background-color: #FFF842;
            color: #403E10;
            font-weight: bold;

            box-shadow: #7F7C21 -1px 1px, #7F7C21 2px 2px, #7F7C21 -3px 3px, #7F7C21 -4px 4px, #7F7C21 -5px 5px, #7F7C21 -6px 6px;
            transform: translate3d(6px, -6px, 0);

            transition-delay: 0s;
            transition-duration: 0.4s;
            transition-property: all;
            transition-timing-function:linear;
        }

        @media (max-width: 800px) {
            .container td:nth-child(4),
            .container th:nth-child(4) { display: none; }
        }
    </style>
</head>
<body>
<header class="htmleaf-header">
    <h1>卡号${card}消费记录</h1>
</header>
<div class="htmleaf-container">
    <div class="login-panel">
        <table id="favouredpolicy" class="container">
            <tbody>
            <tr>
                <th>类型</th>
                <th>数据 (通话(分钟)/上网(MB)/短信(条))</th>
            </tr>
            <c:forEach items="${list}" var="li">
                <tr>
                    <td>${li.consumeType}</td>
                    <td>${li.consumeData}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    <button class = "button" type="button" onclick="window.location.href='${pageContext.request.contextPath}/mobileCard/return'">返回</button>
</div>
</div>
</body>
</html>
