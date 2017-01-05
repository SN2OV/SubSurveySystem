<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: SN2OV
  Date: 2017/1/4
  Time: 22:45
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
                <li class="active"><a href="/captain/transfer/show/${teamTask.teamTaskId}">${teamTask.taskName}</a></li>
                <li class="active"><a href="/captain/transfer/show/${teamTask.teamTaskId}/subtask">子任务</a> </li>
                <li class="active"><a href="/captain/transfer/show/${teamTask.teamTaskId}/subtask/update/${transfersurvey.tid}">${transfersurvey.name}</a> </li>
            </ol>

            <h2 class="sub-header">换乘量调查子任务修改</h2>

            <div>
                <form:form class="contact_form" action="/captain/transfer/show/${teamTask.teamTaskId}/subtask/updateP" method="post" name="contact_form" commandName="subTransfer" role="form">
                    <ul>
                        <li>
                            <label for="taskName">所属主任务</label>
                            <input id="taskName" type="text" name = "taskName"  value="${teamTask.taskName}" required readonly/>
                        </li>

                        <li>
                            <label for="name">任务名称</label>
                            <input id="name" type="text" name = "name"  value="${transfersurvey.name}" required/>
                        </li>

                        <li>
                            <label for="pointLocation">点位</label>
                            <input id="pointLocation" type="text" name = "pointLocation"  value="${transfersurvey.pointLocation}" pattern="[0-9]+$" required/>
                            <span class="form_hint">请输入数字</span>
                        </li>

                        <li>
                            <label for="position">任务名称</label>
                            <input id="position" type="text" name = "position"  value="${transfersurvey.position}" required/>
                        </li>


                        <li style="border-bottom: 0px">
                            <input type="hidden" id="teamTaskId" name = "teamTaskId" value="${teamTask.teamTaskId}">
                        </li>

                        <li style="border-bottom: 0px">
                            <input type="hidden" id="tid" name = "tid" value="${transfersurvey.tid}">
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
