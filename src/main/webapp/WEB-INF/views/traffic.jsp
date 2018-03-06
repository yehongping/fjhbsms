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
    <script src="${ctxStatic}/js/echarts.js"></script>
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
            <%--<label>操作结果：</label>--%>
            <%--<div class="time-select">--%>
            <%--<select id="sufa">--%>
            <%--<option value="2">不&nbsp;&nbsp;&nbsp;限</option>--%>
            <%--<option value="0">有失败</option>--%>
            <%--<option value="1">全成功</option>--%>
            <%--</select>--%>
            <%--</div>--%>
        </div>
        <div class="query-btn ue-clear">
            <a onclick="searchA()" class="confirm">查询</a>
            <a onclick="clearA()" class="clear">清空条件</a>
        </div>
    </div>
</div>
<div id="main" style="width: 100%;height:400%;margin-top: 20px;">

</div>
<div id="main1" style="width: 100%;height:400%;margin-top: 20px;">

</div>
<div id="main2" style="width: 100%;height:400%;margin-top: 20px;">

</div>
<div id="main3" style="width: 100%;height:400%;margin-top: 20px;">
</div>
</body>
<script type="text/javascript" src="${ctxStatic}/js/jquery.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/common.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/WdatePicker.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/jquery.pagination.js"></script>

<script type="text/javascript">
    <%--$(".select-title").on("click", function () {--%>
    <%--$(".select-list").hide();--%>
    <%--$(this).siblings($(".select-list")).show();--%>
    <%--return false;--%>
    <%--})--%>
    <%--$(".select-list").on("click", "li", function () {--%>
    <%--var txt = $(this).text();--%>
    <%--$(this).parent($(".select-list")).siblings($(".select-title")).find("span").text(txt);--%>
    <%--})--%>

    <%--$('.pagination').pagination(${total}, {--%>
    <%--items_per_page:${pageSize},--%>
    <%--display_msg: true,--%>
    <%--setPageNo: true,--%>
    <%--current_page:${page-1},--%>
    <%--callback: function (page) {--%>
    <%--page += 1;--%>
    <%--//            alert(page);--%>
    <%--location = "traffic?page=" + page + "&date=${date}&date1=${date1}&channal=${channal}&sufa=${sufa}&pageSize=${pageSize}";--%>
    <%--}--%>
    <%--});--%>

    <%--$("tbody").find("tr:odd").css("backgroundColor", "#eff6fa");--%>

    showRemind('input[type=text], textarea', 'placeholder');
    $(function () {
        setSelected("channal", "${channal}");
        <%--setSelected("sufa", "${sufa}");--%>
        <c:if test="${not empty ydinfo}">
        var yd = [0], date = [];
        <c:forEach items="${ydinfo}" var="info" varStatus="i">
        yd[${i.index}] =${info.feefailure};
        date[${ydinfo.size()-1-i.index}] = "${info.strdate}";
        </c:forEach>
        displayDate("main", '移动', yd, date);
        </c:if>
        <c:if test="${not empty ltinfo}">
        var lt = [0], ltdate = [];
        <c:forEach items="${ltinfo}" var="info" varStatus="i">
        lt[${i.index}] =${info.feefailure};
        ltdate[${ltinfo.size()-1-i.index}] = "${info.strdate}";
        </c:forEach>
        displayDate("main1", '联通', lt, ltdate);
        </c:if>
        <c:if test="${not empty dxinfo}">
        var dx = [0], dxdate = [];
        <c:forEach items="${dxinfo}" var="info" varStatus="i">
        dx[${i.index}] =${info.feefailure};
        dxdate[${dxinfo.size()-1-i.index}] = "${info.strdate}";
        </c:forEach>
        displayDate("main2", '电信', dx, dxdate);
        </c:if>
        <c:if test="${not empty qwinfo}">
        var qw = [0], qwdate = [];
        <c:forEach items="${qwinfo}" var="info" varStatus="i">
        qw[${i.index}] =${info.feefailure};
        qwdate[${qwinfo.size()-1-i.index}] = "${info.strdate}";
        </c:forEach>
        displayDate("main3", '全网', qw, qwdate);
        </c:if>
    })
    function searchA() {
        var date = document.getElementById("getdate").value;
        var date1 = document.getElementById("getdate1").value;
        var channal = $("#channal").val();
//        var sufa = $("#sufa").val();
        <%--location = "traffic?date=" + date + "&date1=" + date1 + "&channal=" + channal + "&sufa=" + sufa + "&pageSize=${pageSize}";--%>
        location = "traffic?date=" + date + "&date1=" + date1 + "&channal=" + channal + "&pageSize=${pageSize}";
    }

    function clearA() {
        document.getElementById("getdate").value = "";
        document.getElementById("getdate1").value = "";
        setSelected("channal", "");
//        setSelected("sufa", "");
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

    function displayDate(id, text, data, date) {
        var myChart = echarts.init(document.getElementById(id));
        var option = {
            title: {
                text: text + '端口失败统计',
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: [text]
            },
            toolbox: {
                show: true,
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    data: date
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: text,
                    type: 'bar',
                    data: data
                }
            ]
        };
        myChart.setOption(option);
    }
</script>

</html>