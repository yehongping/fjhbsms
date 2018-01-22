<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/9/4
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="base" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <title>提示</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="${base}/resources/system/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${base}/resources/css/common.css">
    <script type="text/javascript" src="${base}/resources/system/jquery/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="${base}/resources/system/bootstrap/js/bootstrap.min.js"></script>
    <style>
        p {
            padding: 0 20px;
        }
    </style>
</head>
<body>
<div class="info-container">
    <div class="dialog-header">提示</div>
    <fieldset class="WF_FormArea">
        <div class="text" >
            <br><br><br>

            <div style="font-size: 11pt;"><p style="text-align: center;">暂时无法访问，请稍后使用</p></div>
            <br><br><br><br>
        </div>
    </fieldset>
</div>


</body>
</html>

