<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SN2OV
  Date: 2016/10/30
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
                <li class="active"><a href="#">线路管理</a></li>
                <li><a href="/admin/stations">车站管理</a></li>
                <li><a href="/admin/ReadMe">使用须知</a></li>
            </ul>
        </div>



        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li class="active"><a href="#">线路列表</a></li>
                <%--<li><a href="#">2013</a></li>--%>
                <a href="/admin/lines/add" class="mynavbar-right">
                    <span class="glyphicon glyphicon-plus"></span>
                </a>
            </ol>

            <h2 class="sub-header">线路列表</h2>
            <!-- 如果线路列表为空 -->
            <c:if test="${empty lineList}">
                <div class="alert alert-warning" role="alert">
                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>系统暂无线路
                </div>
            </c:if>

            <div class="table-responsive">
                <c:if test="${!empty lineList}">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th class="tableHeadStyle">ID</th>
                            <th class="tableHeadStyle">线路名称</th>
                            <th class="tableHeadStyle">顺序</th>
                            <th class="tableHeadStyle">线路类型</th>
                            <th class="tableHeadStyle">车站数量</th>
                            <th class="tableHeadStyle">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <%--<%--%>
                            <%--ArrayList<Integer> linesArr = (ArrayList<Integer>)request.getAttribute("lineCountArr");--%>
                        <%--%>--%>
                        <c:forEach items="${lineList}" var="line">
                            <tr>
                                <td>${lineList.indexOf(line)+1}</td>
                                <td>${line.lineName}</td>
                                <td>${line.lineOrder}</td>

                                <td>${line.isLooper}</td>
                                <td><a href="lines/line-stations/${line.lid}">${lineCountArr.get(lineList.indexOf(line))}</a></td>

                                <td>
                                    <a href="/admin/lines/show/${line.lid}" title="view">
                                            <%--<a class="a_showUsers" index="${userList.indexOf(user)+1}" href="/" title="view">--%>
                                        <span class="glyphicon glyphicon-eye-open"></span>
                                    </a>
                                    <a href="/admin/lines/update/${line.lid}" title="view">
                                        <span class="glyphicon glyphicon-pencil"></span>
                                    </a>
                                    <a href="/admin/lines/delete/${line.lid}" title="view">
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


</script>

</body>
</html>

