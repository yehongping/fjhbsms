<%--
  Created by IntelliJ IDEA.
  User: zhuangying
  Date: 14-9-11
  Time: 下午4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" href="${base}/resources/system/bootstrap/css/bootstrap.min.css">
    <title>提示</title>
    <style>
        html, body {
            margin: 0;
            height: 100%;
        }

        .div1 {
            width: 100%;
            height: 100%;
            background: url("${base}/resources/images/bulid.jpg");
            background-size: cover;
        }
    </style>
</head>
<body style="background: #f0f0f0">
<div class="div1">
</div>
</body>
</html>