<%@ page import="com.buaa.sn2ov.model.Line" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SN2OV
  Date: 2016/12/19
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

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
                <li><a href="/loginout">登出（${userName}/管理员）</a></li>
                <%--<li><a href="#">登出</a></li>--%>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="/admin/users">用户管理</a></li>
                <li><a href="/admin/lines">线路管理</a></li>
                <li class="active"><a href="#">车站管理</a></li>
                <li><a href="/admin/ReadMe">使用须知</a></li>
            </ul>
        </div>


        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="/admin/stations">车站列表</a></li>
                <%--<li><a href="#">2013</a></li>--%>
                <a href="/admin/stations/add" class="mynavbar-right">
                    <span class="glyphicon glyphicon-plus"></span>
                </a>
            </ol>

            <h2 class="sub-header">车站列表</h2>
            <!-- 如果车站列表为空 -->
            <c:if test="${empty stationList}">
                <div class="alert alert-warning" role="alert">
                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>系统暂无车站
                </div>
            </c:if>

            <div class="table-responsive">
                <c:if test="${!empty stationList}">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th class="tableHeadStyle">ID</th>
                            <th class="tableHeadStyle">车站名称</th>
                            <th class="tableHeadStyle">车站类型</th>
                            <th class="tableHeadStyle">所属线路</th>
                            <th class="tableHeadStyle">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${stationList}" var="station">
                            <tr>
                                <td>${stationList.indexOf(station)+1}</td>
                                <td>${station.stationName}</td>
                                <td>${station.stationType}</td>
                                <%
                                    ArrayList<List<Line>> linesArr = (ArrayList<List<Line>>)request.getAttribute("linesArr");
                                %>
                                <c:set var = "lineList" value="${linesArr.get(stationList.indexOf(station))}"></c:set>
                                <td>
                                    <c:forEach items="${lineList}" var="line">
                                        <a href="lines/line-stations/${line.lid}">
                                        ${line.lineName}
                                            <%--""--%>
                                        </a>

                                        <c:if test="${lineList.indexOf(line)!=lineList.size()-1}">
                                            ,
                                        </c:if>


                                    </c:forEach>
                                </td>
                                <td>
                                    <a href="/admin/stations/show/${station.sid}" title="view">
                                            <%--<a class="a_showUsers" index="${userList.indexOf(user)+1}" href="/" title="view">--%>
                                        <span class="glyphicon glyphicon-eye-open"></span>
                                    </a>
                                    <a href="/admin/stations/update/${station.sid}" title="view">
                                        <span class="glyphicon glyphicon-pencil"></span>
                                    </a>
                                    <a href="/admin/stations/delete/${station.sid}" title="view">
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


        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

        </div>

    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<%--<script src="../../assets/js/docs.min.js"></script>--%>
</body>
</html>
