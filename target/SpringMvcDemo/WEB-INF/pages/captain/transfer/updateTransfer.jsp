<%@ page import="java.sql.Time" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: SN2OV
  Date: 2017/1/2
  Time: 21:03
  To change this template use DataFile | Settings | DataFile Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <%--<link rel="icon" href="../../favicon.ico">--%>

    <title>客流调查系统</title>

    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/css/dashboard.css" rel="stylesheet">

    <link href="/resources/css/my.css" rel="stylesheet">

    <link href="/resources/fonts/glyphicons-halflings-regular.woff" rel="stylesheet">

    <link rel="stylesheet" media="screen" href="/resources/css/form-validate.css" >

    <link href="/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <link href="/resources/js/bootstrap-datetimepicker.js" rel="stylesheet">

</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">客流调查系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/loginout">登出（${userName}/组长）</a></li>
                <%--<li><a href="#">登出</a></li>--%>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="/captain/transfer">换乘量调查</a></li>
                <li><a href="/captain/walk">走行时间调查</a></li>
                <li><a href="/captain/od">OD调查</a></li>
                <li><a href="/captain/stay">留乘调查</a></li>
                <li><a href="/captain/reverse">反向乘车调查</a></li>
            </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li class="active"><a href="/captain/transfer">换乘量调查</a></li>
                <li class="active"><a href="/captain/transfer/update/${teamtask.teamTaskId}">修改任务(${teamtask.taskName})</a></li>
            </ol>

            <h2 class="sub-header">换乘量调查任务修改</h2>

            <div>
                <form:form class="contact_form" action="/captain/transfer/updateP" method="post" name="contact_form" commandName="transfer" role="form">
                    <ul>
                        <%--<li>--%>
                            <%--<label for="name">车站</label>--%>
                            <%--<input id="name" type="text" name = "stationName"  value="${station.stationName}" required/>--%>
                        <%--</li>--%>
                        <li>
                            <label for="stationName">选择车站</label>
                            <select class="form-control" id="stationName" name="stationName" >
                                <c:forEach items="${stationList}" var="stationA">
                                    <c:if test="${station.stationName==stationA.stationName}">
                                        <option selected>${stationA.stationName}</option>
                                    </c:if>
                                    <c:if test="${station.stationName!=stationA.stationName}">
                                        <option>${stationA.stationName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </li>

                        <li>
                            <label for="taskName">任务名称</label>
                            <input id="taskName" type="text" name = "taskName"  value="${teamtask.taskName}" required/>
                        </li>
                        <%--<li>--%>
                            <%--<label for="surveyDate">调查日期</label>--%>
                            <%--<input id="surveyDate" type="DATE" name = "surveyDate"  value="${teamtask.surveyDate}" required/>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<label for="timeStart">开始时间</label>--%>
                            <%--<input id="timeStart" type="time" name = "timeStart"  value="${teamtask.timeStart}" required/>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<label for="timeEnd">结束时间</label>--%>
                            <%--<input id="timeEnd" type="time" name = "timeEnd"  value="${teamtask.timeEnd}" required/>--%>
                        <%--</li>--%>
                        <li style="height: 55px;">
                            <label for="surveyDate" class="col-md-2 control-label">调查日期</label>
                            <div class="input-group date form_date col-md-5"  data-date="" data-date-format="yyyy-MM-dd" data-link-field="surveyDate" data-link-format="yyyy-mm-dd">
                                <input id="surveyDate" name="surveyDate" class="form-control" size="16" type="date" readonly value="${teamtask.surveyDate}" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </li>

                        <li style="height: 55px;">
                            <label for="timeStart" class="col-md-2 control-slabel">开始时间</label>
                            <div class="input-group date form_time col-md-5" data-date="" data-date-format="hh:ii:ss" data-link-field="timeStart" data-link-format="hh:ii">
                                <input id="timeStart" name="timeStart" class="form-control" size="16" type="time" required>
                                <%--value="${teamtask.timeStart}" --%>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                            </div>
                        </li>

                        <li style="height: 55px;">
                            <label for="timeEnd" class="col-md-2 control-label">结束时间</label>
                            <div class="input-group date form_time col-md-5" data-date="" data-date-format="hh:ii" data-link-field="timeEnd" data-link-format="hh:ii" >
                                <input id="timeEnd" name="timeEnd" class="form-control" size="16" type="time" required>
                                <%--value="${teamtask.timeEnd}" --%>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                            </div>
                        </li>

                        <li>
                            <label for="timePeriod">调查时段</label>
                            <select class="form-control" id="timePeriod" name="timePeriod" value="${teamtask.timePeriod}" >
                                <c:set var="timePeriod" scope="session" value="${teamtask.timePeriod}"/>
                                <c:if test="${timePeriod=='早高峰'}">
                                    <option selected>早高峰</option>
                                    <option>平峰</option>
                                    <option>晚高峰</option>
                                </c:if>
                                <c:if test="${timePeriod=='平峰'}">
                                    <option>早高峰</option>
                                    <option selected>平峰</option>
                                    <option>晚高峰</option>
                                </c:if>
                                <c:if test="${timePeriod=='晚高峰'}">
                                    <option>早高峰</option>
                                    <option>平峰</option>
                                    <option selected>晚高峰</option>
                                </c:if>

                            </select>
                        </li>
                        <li>
                            <label for="isWeekDay">是否工作日</label>
                            <select class="form-control" id="isWeekDay" name="isWeekDay" value="${teamtask.isWeekDay}" >
                                <c:set var="isWeekDay" scope="session" value="${teamtask.isWeekDay}"/>
                                <c:if test="${isWeekDay=='工作日'}">
                                    <option selected>工作日</option>
                                    <option>非工作日</option>
                                </c:if>
                                <c:if test="${isWeekDay=='非工作日'}">
                                    <option>工作日</option>
                                    <option selected>非工作日</option>
                                </c:if>
                            </select>
                        </li>

                        <li style="border-bottom: 0px">
                            <input type="hidden" id="teamTaskId" name = "teamTaskId" value="${teamtask.teamTaskId}">
                        </li>

                        <li style="border-bottom: 0px">
                            <input type="hidden" id="surveyType" name = "surveyType" value="换乘量调查">
                        </li>


                    </ul>
                    <div class="form-group">
                            <%--<button class="submit" type="submit">提交</button>--%>
                        <button type="submit" class="btn btn-sm btn-success">提交</button>
                    </div>
                </form:form>

            </div>

        </div>

    </div>

</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='/resources/js/bootstrap-datetimepicker.js'></script>
<script src='/resources/js/bootstrap-datetimepicker.zh-CN.js'></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        //language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('.form_time').datetimepicker({
        language:  'fr',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });

</script>

</body>
</html>
