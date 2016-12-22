<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: SN2OV
  Date: 2016/12/20
  Time: 10:12
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
                <li class="active"><a href="/admin/stations">车站管理</a></li>
                <li><a href="#">使用须知</a></li>
            </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="/admin/stations">车站列表</a></li>
                <li class="active"><a href="/admin/stations/add">车站新增</a></li>
                <%--<li class="active">十一月</li>--%>
            </ol>

            <h2 class="sub-header">车站新增</h2>

            <%--commandName的station很关键,通过该属性来指定我们将使用Model中的哪个属性作为form需要绑定的command对象--%>
            <form:form class="contact_form" action="/admin/stations/addP" method="post" commandName="station" role="form">
                <ul>
                    <li>
                        <label for="name">车站名称</label>
                        <input id="name" type="text" name = "stationName"  placeholder="国贸" required/>
                    </li>
                    <li>
                        <label for="stationType">车站类型</label>
                        <select class="form-control" id="stationType" name="stationType" >
                            <option selected>换乘站</option>
                            <option>非换乘站</option>
                        </select>
                    </li>

                </ul>

                <div class="form-group">
                    <button type="submit" class="btn btn-sm btn-success">提交</button>
                </div>
            </form:form>

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
