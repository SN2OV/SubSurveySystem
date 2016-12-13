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
                <li class="active"><a href="#">用户管理</a></li>
                <li><a href="#">线路管理</a></li>
                <li><a href="#">车站管理</a></li>
                <li><a href="#">使用须知</a></li>
            </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="/admin/main">用户列表</a></li>
                <li class="active"><a href="#">用户详情(${user.realName})</a></li>
                <%--<li class="active">十一月</li>--%>
            </ol>

            <h2 class="sub-header">用户详情</h2>

            <p>
            <button type="button" onclick="location = '/admin/users/update/${user.uid}'"  class="btn btn-primary">修改用户</button>
            <button type="button" onclick="location = '/admin/users/delete/${user.uid}'" class="btn btn-danger">删除用户</button>
            </p>

            <div class="table-responsive">
                    <table class="table table-striped table-bordered">
                        </tbody>
                        <tr>
                            <th class="tableHeadStyle">ID</th>
                            <td>${user.uid}</td>
                        </tr>
                        <tr>
                            <th class="tableHeadStyle">用户名</th>
                            <td>${user.userName}</td>
                        </tr>
                        <tr>
                            <th class="tableHeadStyle">角色</th>
                            <td>${user.role}</td>
                        </tr>

                        <tr>
                            <th class="tableHeadStyle">姓名</th>
                            <td>${user.realName}</td>
                        </tr>

                        <tr>
                            <th class="tableHeadStyle">电话</th>
                            <td>${user.mobile}</td>
                        </tr>

                        <tr>
                            <th class="tableHeadStyle">身份证号</th>
                            <td>${user.idCard}</td>
                        </tr>

                        <tr>
                            <th class="tableHeadStyle">公交卡号</th>
                            <td>${user.nfc}</td>
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
<%--<script src="../../assets/js/docs.min.js"></script>--%>
</body>
</html>

