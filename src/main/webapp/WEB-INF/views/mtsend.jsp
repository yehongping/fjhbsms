<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../static/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>移动办公自动化系统</title>
    <link rel="stylesheet" href="${ctxStatic}/css/base.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/info-mgt.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/WdatePicker.css"/>
</head>
<body>
<div class="title"><h2>信息管理>联诚侧宇顺联通、移动电信营销端口日发送短信</h2></div>
<div class="query">
    <div class="query-conditions ue-clear">
        <%--<div class="conditions operate-time ue-clear">--%>
        <%--<label>操作时间：</label>--%>
        <%--<div class="select-wrap">--%>
        <%--<div class="select-title ue-clear"><span>大于或等于</span><i class="icon"></i></div>--%>
        <%--<ul class="select-list">--%>
        <%--<li>呵呵</li>--%>
        <%--<li>哈哈</li>--%>
        <%--<li>嘻嘻</li>--%>
        <%--</ul>--%>
        <%--</div>--%>
        <%--<div class="input-box ue-clear">--%>
        <%--<input type="text">--%>
        <%--<span>小时</span>--%>
        <%--</div>--%>
        <%--</div>--%>
        <div class="conditions time ue-clear">
            <label>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：</label>
            <div class="time-select">
                <input type="text" onfocus="WdatePicker({$dpPath:'${ctxStatic}/js/',dateFmt:'yyyy-MM-dd'})" placeholder="开始时间" id="date" value="${date}">
                <i class="icon"></i>
            </div>
        </div>
        <div class="conditions staff ue-clear">
            <label>手机号：</label>
            <input type="text" placeholder="可以直接输入手机号" id="phone" value="${phone}">
            <%--<a href="javascript:;" class="staff-select">选择</a>--%>
        </div>
        <div class="query-btn ue-clear">
            <a onclick="searchA()" class="confirm">查询</a>
            <a onclick="clearA()" class="clear">清空条件</a>
        </div>
    </div>

</div>
<div class="table-box">
    <table>
        <thead>
        <tr>
            <th class="num">序号</th>
            <th class="time">用户</th>
            <th class="name">发送号码</th>
            <th class="process">发送短信</th>
            <th class="node">长号码</th>
            <th class="node">长短信内编号</th>
            <th class="node">长信数量</th>
            <th class="node">发送状态</th>
            <th class="node">状态报告</th>
            <th class="time">发送时间</th>
            <th class="operate">提交时间</th>
            <th class="time">收到短信时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${infoList}" var="info" varStatus="i">
            <c:if test="${i.index%2 eq 0}">
                <tr>
            </c:if>
            <c:if test="${i.index%2 gt 0}">
                <tr style="background-color: rgb(239, 246, 250);">
            </c:if>
            <td class="num">${i.index+1+start}</td>
            <td class="name">${info.loginid}</td>
            <td class="name">${info.phone}</td>
            <td class="process">${info.msgcont}</td>
            <td class="node">${info.uc}</td>
            <td class="node">${info.pknum}</td>
            <td class="node">${info.pktotal}</td>
            <td class="node">${info.rptstate}</td>
            <td class="node">${info.rptinfo}</td>
            <td class="time">${info.timestr1}</td>
            <td class="operate">${info.timestr3}</td>
            <td class="time">${info.timestr2}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="pagination ue-clear">
    <div class="goto"></div>
    <div class="pxofy"></div>
</div>
</body>
<script type="text/javascript" src="${ctxStatic}/js/jquery.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/common.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/WdatePicker.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/jquery.pagination.js"></script>
<script type="text/javascript">
    $(".select-title").on("click", function () {
        $(".select-list").hide();
        $(this).siblings($(".select-list")).show();
        return false;
    })
    $(".select-list").on("click", "li", function () {
        var txt = $(this).text();
        $(this).parent($(".select-list")).siblings($(".select-title")).find("span").text(txt);
    })

    $('.pagination').pagination(${total}, {
        items_per_page:${pageSize},
        display_msg: true,
        setPageNo: true,
        current_page:${page-1},
        callback: function (page) {
            page += 1;
//            alert(page);
            location = "mtsend?page=" + page + "&date=${date}&pageSize=${pageSize}";
        }
    });

    $("tbody").find("tr:odd").css("backgroundColor", "#eff6fa");

    showRemind('input[type=text], textarea', 'placeholder');

    function searchA() {
        var regPhone = /^1[0-9]{10}$/;
        var date = document.getElementById("date").value;
        var phone = document.getElementById("phone").value;
        var ena = true;
        if (phone != "" && !regPhone.test(phone)) {
            ena = false;
            alert("手机号码格式不正常！");
            return;

        }
        if (ena) {
            location = "mtsend?page=${page}&date=" + date + "&phone=" + phone + "&pageSize=${pageSize}";
        }
    }

    function clearA() {
        document.getElementById("date").value = "";
        document.getElementById("phone").value = "";
    }
</script>
</html>