<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../static/include/taglib.jsp"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${ctxStatic}/css/base.css" />
    <link rel="stylesheet" href="${ctxStatic}/css/login.css" />
    <title>宇顺短信查询系统</title>
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
                    <select id="type">
                        <option value="1">企业用户</option>
                        <option value="0">管理员</option>
                    </select>
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
<div id="ft">CopyRight&nbsp;2018&nbsp;&nbsp;版权所有&nbsp; &nbsp;福建恒邦英才教育科技有限公司&nbsp;&nbsp;</div>
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
        var type = $("#type").val();
        var rem = $("input:checkbox:checked").val();
        if(rem == "on"){
            rem = 1;
        }
        else rem = 0;
        if(user == "" || pwd == ""){
            alert("用户名和密码不能为空");
            return;
        }
        $.post("login",{user:user,pwd:pwd,type:type,remind:rem},function (res) {
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