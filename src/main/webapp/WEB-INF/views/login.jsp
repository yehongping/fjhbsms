<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../static/include/taglib.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${ctxStatic}/css/base.css" />
    <link rel="stylesheet" href="${ctxStatic}/css/login.css" />
    <title>移动办公自动化系统</title>
</head>
<body>
<div id="container">
    <div id="bd">
        <div class="login">
            <div class="login-top"><h1 class="logo"></h1></div>
            <div class="login-input">
                <p class="user ue-clear">
                    <label>用户名</label>
                    <input type="text" id="user"/>
                </p>
                <p class="password ue-clear">
                    <label>密&nbsp;&nbsp;&nbsp;码</label>
                    <input type="password" id="pwd" />
                </p>
            </div>
            <div class="login-btn ue-clear">
                <a onclick="login()" class="btn">登录</a>
                <div class="remember ue-clear">
                    <input type="checkbox" id="remember" />
                    <em></em>
                    <label for="remember">记住密码</label>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="ft">CopyRight&nbsp;2014&nbsp;&nbsp;版权所有&nbsp;&nbsp;samxinnet.com专注于ui设计&nbsp;&nbsp;皖ICP备09001111号</div>
</body>
<script type="text/javascript" src="${ctxStatic}/js/jquery.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/common.js"></script>
<script type="text/javascript">
    var height = $(window).height();
    $("#container").height(height);
    $("#bd").css("padding-top",height/2 - $("#bd").height()/2);

    $(window).resize(function(){
        var height = $(window).height();
        $("#bd").css("padding-top",$(window).height()/2 - $("#bd").height()/2);
        $("#container").height(height);

    });

    $('#remember').focus(function(){
        $(this).blur();
    });

    $('#remember').click(function(e) {
        checkRemember($(this));
    });

    function checkRemember($this){
        if(!-[1,]){
            if($this.prop("checked")){
                $this.parent().addClass('checked');
            }else{
                $this.parent().removeClass('checked');
            }
        }
    }

    function login() {
        var user = $("#user").val();
        var pwd = $("#pwd").val();

        if(user == "" || pwd == ""){
            alert("用户名和密码不能为空");
            return;
        }
        $.post("login",{user:user,pwd:pwd},function (res) {
            if(res == 0)
                location="index";
            else{
                alert("用户名或密码错误");
                return
            }
        });
    }
</script>
</html>