<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SN2OV
  Date: 2016/12/28
  Time: 16:35
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
                <%--<li><a href="#">2013</a></li>--%>
                <a href="/captain/transfer/add" class="mynavbar-right">
                    <span class="glyphicon glyphicon-plus"></span>
                </a>
            </ol>

            <h2 class="sub-header">换乘量调查任务列表</h2>

            <!-- 如果用户列表为空 -->
            <c:if test="${empty transferSurveyList}">
                <div class="alert alert-warning" role="alert">
                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>系统暂无换乘量调查任务
                </div>
            </c:if>

            <div class="table-responsive">
                <c:if test="${!empty transferSurveyList}">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th class="tableHeadStyle">序号</th>
                            <th class="tableHeadStyle">车站</th>
                            <th class="tableHeadStyle">任务名称</th>
                            <th class="tableHeadStyle">调查日期</th>
                            <%--<th class="tableHeadStyle">开始时间</th>--%>
                            <%--<th class="tableHeadStyle">结束时间</th>--%>
                            <%--<th class="tableHeadStyle">调查时段</th>--%>
                            <%--<th class="tableHeadStyle">是否工作日</th>--%>
                            <th class="tableHeadStyle">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${transferSurveyList}" var="transferSurvey">
                            <tr>
                                <td>${transferSurveyList.indexOf(transferSurvey)+1}</td>
                                <td>${stationList.get(transferSurveyList.indexOf(transferSurvey)).stationName}</td>
                                <td>${transferSurvey.taskName}</td>
                                <td>${transferSurvey.surveyDate}</td>
                                <%--<td>${transferSurvey.timeStart}</td>--%>
                                <%--<td>${transferSurvey.timeEnd}</td>--%>
                                <%--<td>${transferSurvey.timePeriod}</td>--%>
                                <%--<td>${transferSurvey.isWeekDay}</td>--%>
                                <td>
                                    <a href="/captain/transfer/show/${transferSurvey.teamTaskId}" title="view">
                                            <%--<a class="a_showUsers" index="${userList.indexOf(user)+1}" href="/" title="view">--%>
                                        <span class="glyphicon glyphicon-eye-open"></span>
                                    </a>
                                    <a href="/captain/transfer/update/${transferSurvey.teamTaskId}" title="view">
                                        <span class="glyphicon glyphicon-pencil"></span>
                                    </a>
                                    <a href="/captain/transfer/delete/${transferSurvey.teamTaskId}" title="view">
                                        <span class="glyphicon glyphicon-trash"></span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </c:if>
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

    <%--$(function (){--%>
    <%--$(".a_showUsers").on('click',function(){--%>
    <%--//            alert("ss");--%>
    <%--var index = $(this).attr('index');--%>
    <%--var userDetail = '{"uid":${userList.get(index).uid},"name":"${userList.get(index).userName}","role":"${userList.get(index).role}",'+--%>
    <%--'"realName":"${userList.get(index).realName}","mobile":"${userList.get(index).mobile}","idCard":"${userList.get(index).idCard}",'+--%>
    <%--'"nfc":"${userList.get(index).nfc}"}';--%>

    <%--$.ajax({--%>
    <%--type : 'POST',--%>
    <%--contentType : 'application/json',--%>
    <%--url : "${pageContext.request.contextPath}/restful/admin/users/show/"+index,--%>
    <%--processData : false,--%>
    <%--dataType : 'json',--%>
    <%--data : userDetail,--%>
    <%--success : function(data) {--%>
    <%--alert(userDetail);--%>
    <%--},--%>
    <%--error : function(XMLHttpRequest, textStatus, errorThrown) {--%>
    <%--alert('出错了！');--%>
    <%--}--%>
    <%--});--%>

    <%--})--%>
    <%--})--%>


</script>

</body>
</html>
