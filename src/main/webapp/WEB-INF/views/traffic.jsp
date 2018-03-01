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
<div class="title"><h2>信息管理>端口发送统计</h2></div>
<div class="query">
    <div class="query-conditions ue-clear">
        <div class="conditions time ue-clear">
            <label>开始日期：</label>
            <div class="time-select">
                <input type="text" id="getdate"
                       onfocus="WdatePicker({$dpPath:'${ctxStatic}/js/',$dateFmt:'yyyy-MM-dd'})"
                       placeholder="统计日期" value="${date}">
                <i class="icon"></i>
            </div>
            </br>
            <label>结束日期：</label>
            <div class="time-select">
                <input type="text" id="getdate1"
                       onfocus="WdatePicker({$dpPath:'${ctxStatic}/js/',$dateFmt:'yyyy-MM-dd'})"
                       placeholder="统计日期" value="${date1}">
                <i class="icon"></i>
            </div>
        </div>
        <div class="conditions channal">
            <label>运&nbsp;&nbsp;营&nbsp;&nbsp;商：</label>
            <div class="time-select">
                <select id="channal">
                    <option value="">不&nbsp;&nbsp;&nbsp;限</option>
                    <option value="0">移&nbsp;&nbsp;&nbsp;动</option>
                    <option value="1">联&nbsp;&nbsp;&nbsp;通</option>
                    <option value="2">电&nbsp;&nbsp;&nbsp;信</option>
                    <option value="3">全&nbsp;&nbsp;&nbsp;网</option>
                </select>
            </div>
            <label>操作结果：</label>
            <div class="time-select">
                <select id="sufa">
                    <option value="2">不&nbsp;&nbsp;&nbsp;限</option>
                    <option value="0">有失败</option>
                    <option value="1">全成功</option>
                </select>
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
            <th class="name">类型</th>
            <th class="time">发送数量</th>
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
            <td class="process"><c:if test="${info.type eq 0}">移动</c:if><c:if test="${info.type eq 1}">联通</c:if><c:if
                    test="${info.type eq 2}">电信</c:if><c:if test="${info.type eq 3}">全网</c:if></td>
            <td class="node">${info.mtnum}</td>
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
            location = "traffic?page=" + page + "&date=${date}&date1=${date1}&channal=${channal}&sufa=${sufa}&pageSize=${pageSize}";
        }
    });

    $("tbody").find("tr:odd").css("backgroundColor", "#eff6fa");

    showRemind('input[type=text], textarea', 'placeholder');
    $(function () {
        setSelected("channal", "${channal}");
        setSelected("sufa", "${sufa}");

    })
    function searchA() {
        var date = document.getElementById("getdate").value;
        var date1 = document.getElementById("getdate1").value;
        var channal = $("#channal").val();
        var sufa = $("#sufa").val();
        location = "traffic?date=" + date + "&date1=" + date1 + "&channal=" + channal + "&sufa=" + sufa + "&pageSize=${pageSize}";
    }

    function clearA() {
        document.getElementById("getdate").value = "";
        document.getElementById("getdate1").value = "";
        setSelected("channal", "");
        setSelected("sufa", "");
    }

    function setSelected(id, value) {
        var obj = document.getElementById(id);
        for (var i = 0; i < obj.length; i++) {
            if (obj[i].value == value) {
                obj[i].selected = true;
                break;
            }
        }
    }
</script>
</html>