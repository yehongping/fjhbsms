<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../static/include/taglib.jsp"%>
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
<div class="title"><h2>信息管理>表01</h2></div>
<div class="query">
    <div class="query-conditions ue-clear">
        <div class="conditions name ue-clear">
            <label>流程名称：</label>
            <div class="select-wrap">
                <div class="select-title ue-clear"><span>请选择</span><i class="icon"></i></div>
                <ul class="select-list">
                    <li>呵呵</li>
                    <li>哈哈</li>
                    <li>嘻嘻</li>
                </ul>
            </div>
        </div>
        <div class="conditions operate-time ue-clear">
            <label>操作时间：</label>
            <div class="select-wrap">
                <div class="select-title ue-clear"><span>大于或等于</span><i class="icon"></i></div>
                <ul class="select-list">
                    <li>呵呵</li>
                    <li>哈哈</li>
                    <li>嘻嘻</li>
                </ul>
            </div>
            <div class="input-box ue-clear">
                <input type="text">
                <span>小时</span>
            </div>
        </div>
        <div class="conditions time ue-clear">
            <label>时&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：</label>
            <div class="time-select">
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="开始时间">
                <i class="icon"></i>
            </div>
            <span class="line">-</span>
            <div class="time-select">
                <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="开始时间">
                <i class="icon"></i>
            </div>
        </div>
        <div class="conditions staff ue-clear">
            <label>人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;员：</label>
            <input type="text" placeholder="可以直接输入或选择">
            <a href="javascript:;" class="staff-select">选择</a>
        </div>
    </div>
    <div class="query-btn ue-clear">
        <a href="javascript:;" class="confirm">查询</a>
        <a href="javascript:;" class="clear">清空条件</a>
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
    <div class="pagin-list"><span class="current prev">&lt;&nbsp;上一页</span><span class="current">1</span><a
            href="javascript:;">2</a><a href="javascript:;">3</a><a href="javascript:;">4</a><span>...</span><a
            href="javascript:;">9</a><a href="javascript:;">10</a><a href="javascript:;" class="next">下一页&nbsp;&gt;</a>
    </div>
    <div class="pxofy">显示第&nbsp;${start+1}&nbsp;条到&nbsp;${limit}&nbsp;条记录，总共&nbsp;${total}&nbsp;条</div>
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

    <%--$('.pagination').pagination(${total}, {--%>
        <%--callback: function (page) {--%>
            <%--alert(page);--%>
        <%--},--%>
        <%--display_msg: true,--%>
        <%--setPageNo: true--%>
    <%--});--%>

    $("tbody").find("tr:odd").css("backgroundColor", "#eff6fa");

    showRemind('input[type=text], textarea', 'placeholder');
</script>
</html>