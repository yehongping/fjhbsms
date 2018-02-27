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
<div class="title"><h2>信息管理>腾讯端口发送统计</h2></div>
<div class="query">
    <div class="query-conditions ue-clear">
        <div class="conditions time ue-clear">
            <label>日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期：</label>
            <div class="time-select">
                <input type="text" id="getdate"
                       onfocus="WdatePicker({$dpPath:'${ctxStatic}/js/',$dateFmt:'yyyyMMdd'})"
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
            <th class="name">端口</th>
            <th class="process">发送总数</th>
            <th class="node">状态报告总数</th>
            <th class="time">成功状态报告总数</th>
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
            <td class="name">${info.srcPhone}</td>
            <td class="process">${info.num}</td>
            <td class="node">${info.rnum}</td>
            <td class="time">${info.rsnum}</td>
            <td class="operate">${info.sdate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="pagination ue-clear">
    <div class="goto"><span class="text">转到第</span><input type="text"><span class="page">页</span><a href="javascript:;">转</a>
    </div>
    <div class="pagin-list"></div>
    <div class="pxofy"></div>
    <%--<div class="pxofy"><input type="number" id="pageSize" value="${pageSize}"/></div>--%>
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
        items_per_page:${pageSize},
        callback: function (page) {
            page += 1;
//            alert(page);
            location = "summary?page=" + page + "&date=${date}&pageSize=${pageSize}";
        }
    });

    $("tbody").find("tr:odd").css("backgroundColor", "#eff6fa");

    showRemind('input[type=text], textarea', 'placeholder');


    function searchA() {
        var date = document.getElementById("getdate").value;
        location = "summary?page=${page}&date=" + date + "&pageSize=${pageSize}";
    }

    function clearA() {
        document.getElementById("getdate").value = "";
    }

</script>
</html>