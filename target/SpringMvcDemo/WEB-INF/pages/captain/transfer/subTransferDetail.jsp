<%--
  Created by IntelliJ IDEA.
  User: SN2OV
  Date: 2017/1/4
  Time: 15:52
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
                <li class="active"><a href="/captain/transfer/show/${teamTask.teamTaskId}/subtask/show/${transfersurvey.tid}">${transfersurvey.name}</a> </li>
            </ol>

            <h2 class="sub-header">换乘量调查子任务详情</h2>

            <p>
                <button type="button" onclick="location = '/captain/transfer/show/${teamTask.teamTaskId}/subtask/update/${transfersurvey.tid}'"  class="btn btn-primary">修改子任务</button>
                <button type="button" onclick="location = '/captain/transfer/show/${teamTask.teamTaskId}/subtask/delete/${transfersurvey.tid}'" class="btn btn-danger">删除子任务</button>
            </p>

            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    </tbody>
                    <tr>
                        <th class="tableHeadStyle">ID</th>
                        <td>${transfersurvey.tid}</td>
                    </tr>
                    <tr>
                        <th class="tableHeadStyle">所属主任务</th>
                        <td>${teamTask.taskName}</td>
                    </tr>
                    <tr>
                        <th class="tableHeadStyle">任务名称</th>
                        <td>${transfersurvey.name}</td>
                    </tr>
                    <tr>
                        <th class="tableHeadStyle">点位</th>
                        <td>${transfersurvey.pointLocation}</td>
                    </tr>
                    <tr>
                        <th class="tableHeadStyle">调查位置</th>
                        <td>${transfersurvey.position}</td>
                    </tr>

                    </tbody>
                </table>
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
<script type="text/javascript">


</script>

</body>
</html>