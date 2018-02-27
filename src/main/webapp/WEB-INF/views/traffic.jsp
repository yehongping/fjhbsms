<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../static/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>宇顺短信查询系统</title>
    <link rel="stylesheet" href="${ctxStatic}/css/base.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/info-mgt.css"/>
    <link rel="stylesheet" href="${ctxStatic}/css/WdatePicker.css"/>
</head>
<body>
<div class="title"><h2>信息管理>联诚端口发送统计</h2></div>
<div class="query">
    <div class="query-conditions ue-clear">
        <div class="conditions time ue-clear">
            <label>日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期：</label>
            <div class="time-select">
                <input type="text" id="getdate"
                       onfocus="WdatePicker({$dpPath:'${ctxStatic}/js/',$dateFmt:'yyyy-MM-dd'})"
                       placeholder="统计日期" value="${date}">
                <i class="icon"></i>
            </div>
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
            <th class="name">发送类型</th>
            <th class="name">频道号</th>
            <th class="name">类型</th>
            <th class="node">登录ID</th>
            <th class="time">企业ID</th>
            <th class="time">发送数量</th>
            <th class="time">计费数量</th>
            <th class="time">成功数量</th>
            <th class="time">失败数量</th>
            <th class="operate">统计日期</th>
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
            <th class="name"><c:if test="${info.chtype eq 0}">短信</c:if><c:if test="${info.chtype eq 1}">彩信</c:if><c:if test="${info.chtype eq 2}">国际短信</c:if><c:if test="${info.chtype eq 3}">其他未定义</c:if></th>
            <td class="name">${info.channelid}</td>
            <td class="process"><c:if test="${info.type eq 1}">联通</c:if><c:if test="${info.type eq 2}">电信</c:if><c:if test="${info.type eq 3}">全网</c:if></td>
            <td class="node">${info.loginid}</td>
            <td class="time">${info.corpid}</td>
            <td class="node">${info.mtnum}</td>
            <td class="time">${info.feenum}</td>
            <td class="node">${info.feesuccess}</td>
            <td class="time">${info.feefailure}</td>
            <td class="operate">${info.statdate}</td>
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
            location = "traffic?page=" + page + "&date=${date}&pageSize=${pageSize}";
        }
    });

    $("tbody").find("tr:odd").css("backgroundColor", "#eff6fa");

    showRemind('input[type=text], textarea', 'placeholder');
    function searchA() {
        var date = document.getElementById("getdate").value;
        location = "traffic?date=" + date + "&pageSize=${pageSize}";
    }

    function clearA() {
       document.getElementById("getdate").value = "";
    }
</script>
</html>