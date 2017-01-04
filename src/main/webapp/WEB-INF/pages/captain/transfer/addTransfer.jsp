<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SN2OV
  Date: 2017/1/3
  Time: 10:07
  To change this template use File | Settings | File Templates.
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
                <li class="active"><a href="/captain/transfer/add">任务新增</a></li>
            </ol>

            <h2 class="sub-header">换乘量调查任务新增</h2>

            <div>
                <form:form class="contact_form" action="/captain/transfer/addP" method="post" name="contact_form" commandName="transfer" role="form">
                    <ul>
                        <li>
                            <label for="stationName">选择车站</label>
                            <select class="form-control" id="stationName" name="stationName" >
                                <c:forEach items="${stationList}" var="stationA">
                                    <option>${stationA.stationName}</option>
                                </c:forEach>
                            </select>
                        </li>

                        <li>
                            <label for="taskName">任务名称</label>
                            <input id="taskName" type="text" name = "taskName" placeholder="20170101XX站换乘量调查" required/>
                        </li>

                        <li style="height: 55px;">
                            <label for="surveyDate" class="col-md-2 control-label">调查日期</label>
                            <div class="input-group date form_date col-md-5" data-date="" data-date-format="yyyy-MM-dd" data-link-field="surveyDate" data-link-format="yyyy-mm-dd">
                                <input id="surveyDate" name="surveyDate" class="form-control" size="16" type="date" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </li>

                        <li style="height: 55px;">
                            <label for="timeStart" class="col-md-2 control-slabel">开始时间</label>
                            <div class="input-group date form_time col-md-5" data-date="" data-date-format="timeStart" data-link-field="timeStart" data-link-format="hh:ii">
                                <input id="timeStart" name="timeStart" class="form-control" size="16" type="time" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                            </div>
                        </li>

                        <li style="height: 55px;">
                            <label for="timeEnd" class="col-md-2 control-label">结束时间</label>
                            <div class="input-group date form_time col-md-5" data-date="" data-date-format="hh:ii" data-link-field="timeEnd" data-link-format="hh:ii">
                                <input id="timeEnd" name="timeEnd" class="form-control" size="16" type="time" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                            </div>
                        </li>

                        <li>
                            <label for="timePeriod">调查时段</label>
                            <select class="form-control" id="timePeriod" name="timePeriod" >
                                <option selected>早高峰</option>
                                <option>平峰</option>
                                <option>晚高峰</option>
                            </select>
                        </li>
                        <li>
                            <label for="isWeekDay">是否工作日</label>
                            <select class="form-control" id="isWeekDay" name="isWeekDay">
                                <option selected>工作日</option>
                                <option>非工作日</option>
                            </select>
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
