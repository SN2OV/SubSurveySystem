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
                <li><a href="#">登出（SN2OV/管理员）</a></li>
                <%--<li><a href="#">登出</a></li>--%>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="#">用户管理</a></li>
                <li><a href="#">车站管理</a></li>
                <li><a href="#">线路管理</a></li>
                <li><a href="#">使用须知</a></li>
            </ul>
        </div>

        <!-- 如果用户列表为空 -->
        <c:if test="${empty userList}">
            <div class="alert alert-warning" role="alert">
                <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>User表为空，请<a href="/admin/users/add" type="button" class="btn btn-primary btn-sm">添加</a>
            </div>
        </c:if>

        <!-- 如果用户列表非空 -->

        <%--<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">--%>
            <%--<ol class="breadcrumb">--%>
                <%--<li><a href="#">用户列表</a></li>--%>
            <%--</ol>--%>

            <%--<h2 class="sub-header">用户列表</h2>--%>
            <%--<div class="table-responsive">--%>
                <%--<c:if test="${!empty userList}">--%>
                    <%--<table class="table table-bordered table-striped">--%>
                        <%--<tr>--%>
                            <%--<th class="tableHeadStyle">ID</th>--%>
                            <%--<th class="tableHeadStyle">用户名</th>--%>
                            <%--<th class="tableHeadStyle">角色</th>--%>
                            <%--<th class="tableHeadStyle">姓名</th>--%>
                            <%--<th class="tableHeadStyle">电话</th>--%>
                            <%--<th class="tableHeadStyle">身份证号</th>--%>
                            <%--<th class="tableHeadStyle">公交卡号</th>--%>
                            <%--<th class="tableHeadStyle">操作</th>--%>
                        <%--</tr>--%>

                        <%--<c:forEach items="${userList}" var="user">--%>
                            <%--<tr>--%>
                                <%--<td>${user.uid}</td>--%>
                                <%--<td>${user.userName}</td>--%>
                                <%--<td>${user.role}</td>--%>
                                <%--<td>${user.realName}</td>--%>
                                <%--<td>${user.mobile}</td>--%>
                                <%--<td>${user.idCard}</td>--%>
                                <%--<td>${user.nfc}</td>--%>
                                <%--<td>--%>
                                    <%--<a href="/admin/users/show/${user.id}" type="button" class="btn btn-sm btn-success">详情</a>--%>
                                    <%--<a href="/admin/users/update/${user.id}" type="button" class="btn btn-sm btn-warning">修改</a>--%>
                                    <%--<a href="/admin/users/delete/${user.id}" type="button" class="btn btn-sm btn-danger">删除</a>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                        <%--</c:forEach>--%>
                    <%--</table>--%>
                <%--</c:if>--%>
            <%--</div>--%>
        <%--</div>--%>


        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">用户列表</a></li>
                <%--<li><a href="#">2013</a></li>--%>
                <%--<li class="active">十一月</li>--%>
            </ol>

            <h2 class="sub-header">用户列表</h2>
            <div class="table-responsive">
                <c:if test="${!empty userList}">
                <table class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th class="tableHeadStyle">ID</th>
                        <th class="tableHeadStyle">用户名</th>
                        <th class="tableHeadStyle">角色</th>
                        <th class="tableHeadStyle">姓名</th>
                        <th class="tableHeadStyle">电话</th>
                        <th class="tableHeadStyle">身份证号</th>
                        <th class="tableHeadStyle">公交卡号</th>
                        <th class="tableHeadStyle">操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${userList}" var="user">
                        <tr>
                            <td>${user.uid}</td>
                            <td>${user.userName}</td>
                            <td>${user.role}</td>
                            <td>${user.realName}</td>
                            <td>${user.mobile}</td>
                            <td>${user.idCard}</td>
                            <td>${user.nfc}</td>
                            <td>
                                <a href="" title="view">
                                    <span class="glyphicon glyphicon-eye-open"></span>
                                </a>
                                <a href="" title="view">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </a>
                                <a href="" title="view">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    <%--<tr>--%>
                        <%--<td>1</td>--%>
                        <%--<td>sn2ov</td>--%>
                        <%--<td>管理员</td>--%>
                        <%--<td>刘笑凡</td>--%>
                        <%--<td width="100px">--%>
                            <%--<a href="" title="view">--%>
                                <%--<span class="glyphicon glyphicon-eye-open"></span>--%>
                            <%--</a>--%>
                            <%--<a href="" title="view">--%>
                                <%--<span class="glyphicon glyphicon-pencil"></span>--%>
                            <%--</a>--%>
                            <%--<a href="" title="view">--%>
                                <%--<span class="glyphicon glyphicon-trash"></span>--%>
                            <%--</a>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>2</td>--%>
                        <%--<td>zhangsan</td>--%>
                        <%--<td>组长</td>--%>
                        <%--<td>张三</td>--%>
                        <%--<td width="100px">--%>
                            <%--<a href="" title="view">--%>
                                <%--<span class="glyphicon glyphicon-eye-open"></span>--%>
                            <%--</a>--%>
                            <%--<a href="" title="view">--%>
                                <%--<span class="glyphicon glyphicon-pencil"></span>--%>
                            <%--</a>--%>
                            <%--<a href="" title="view">--%>
                                <%--<span class="glyphicon glyphicon-trash"></span>--%>
                            <%--</a>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>3</td>--%>
                        <%--<td>lisi</td>--%>
                        <%--<td>调查员</td>--%>
                        <%--<td>李四</td>--%>
                        <%--<td width="100px">--%>
                            <%--<a href="" title="view">--%>
                                <%--<span class="glyphicon glyphicon-eye-open"></span>--%>
                            <%--</a>--%>
                            <%--<a href="" title="view">--%>
                                <%--<span class="glyphicon glyphicon-pencil"></span>--%>
                            <%--</a>--%>
                            <%--<a href="" title="view">--%>
                                <%--<span class="glyphicon glyphicon-trash"></span>--%>
                            <%--</a>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>4</td>--%>
                        <%--<td>wangwu</td>--%>
                        <%--<td>督导</td>--%>
                        <%--<td>王五</td>--%>
                        <%--<td width="100px">--%>
                            <%--<a href="" title="view">--%>
                                <%--<span class="glyphicon glyphicon-eye-open"></span>--%>
                            <%--</a>--%>
                            <%--<a href="" title="view">--%>
                                <%--<span class="glyphicon glyphicon-pencil"></span>--%>
                            <%--</a>--%>
                            <%--<a href="" title="view">--%>
                                <%--<span class="glyphicon glyphicon-trash"></span>--%>
                            <%--</a>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
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
<%--<script src="../../assets/js/docs.min.js"></script>--%>
</body>
</html>

