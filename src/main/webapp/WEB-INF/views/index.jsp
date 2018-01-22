<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../static/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>移动办公自动化系统</title>
    <link rel="stylesheet" href="${ctxStatic}/css/base.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/index.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/jquery.dialog.css"/>
</head>
<body>
<div id="container">
    <div id="hd">
        <div class="hd-wrap ue-clear">
            <div class="top-light"></div>
            <h1 class="logo"></h1>
            <div class="login-info ue-clear">
                <div class="welcome ue-clear"><span>欢迎您,</span><a href="javascript:;" class="user-name">Admin</a></div>
            </div>
            <div class="toolbar ue-clear">
                <a href="javascript:;" class="home-btn">首页</a>
                <a href="javascript:;" class="quit-btn exit"></a>
            </div>
        </div>
    </div>
    <div id="bd" style="height: 856px;">
        <div class="wrap ue-clear" style="height: 850px;">
            <div class="sidebar">
                <h2 class="sidebar-header"><p>功能导航</p></h2>
                <ul class="nav" style="min-height: 814px;">
                   <li class="konwledge current hasChild" style="border-color: rgb(219, 233, 241);">
                        <div class="nav-header"><a href="javascript:;" class="ue-clear"><span>信息管理</span><i class="icon"></i></a></div>
                        <ul class="subnav">
                            <li><a href="javascript:;" date-src="summary">表01</a></li>
                            <li><a href="javascript:;" date-src="traffic">表02</a></li>
                            <li><a href="javascript:;" date-src="mtsend">表03</a></li>
                            <li><a href="javascript:;" date-src="ysmt">表04</a></li>
                        </ul>
                    </li>
             </ul>
            </div>
            <div class="content">
                <iframe src="summary" id="iframe" width="100%" height="100%" frameborder="0" style="height: 850px;"></iframe>
            </div>
        </div>
    </div>
    <div id="ft" class="ue-clear">
        <div class="ft-left">
            <span>福建恒邦英才教育有限公司</span>
            <em>后台系统</em>
        </div>
        <div class="ft-right">
            <span>Automation</span>
            <em>V1.0&nbsp;2018</em>
        </div>
    </div>
</div>
<div class="exitDialog">
    <div class="dialog-content">
        <div class="ui-dialog-icon"></div>
        <div class="ui-dialog-text">
            <p class="dialog-content">你确定要退出系统？</p>
            <p class="tips">如果是请点击“确定”，否则点“取消”</p>

            <div class="buttons">
                <input type="button" class="button long2 ok" value="确定" />
                <input type="button" class="button long2 normal" value="取消" />
            </div>
        </div>

    </div>
</div>
</body>
<script type="text/javascript" src="${ctxStatic}/js/jquery.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/common.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/core.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/jquery.dialog.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/index.js"></script>
</html>