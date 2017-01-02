<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SN2OV
  Date: 2016/12/26
  Time: 11:30
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
                <li><a href="/admin/stations">车站管理</a></li>
                <li class="active"><a href="/admin/ReadMe">使用须知</a></li>
            </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">使用须知</h2>
            <h3>一、用户管理</h3>
            <ol>
                1.组长角色登录进入“用户管理”模块，点击右上角的新增按钮，可对调查员、组长、管理员、督导等角色用户进行新增。
            </ol>
            <ol>
                2.“用户管理”模块界面，展示系统当前所有用户。表格末端可对当前用户进行修改、删除操作。
            </ol>
            <h3>二、线路管理</h3>
            <ol>
                1.组长角色登录进入“线路管理”模块，点击右上角的新增按钮，跳转到“新路新增”页面，可对列车线路进行新增。
            </ol>
            <ol>
                2.“线路管理”模块界面，展示系统当前线路，包括线路名称、类型、包含车站等信息。表格末端可对当前用户进行修改、删除操作。
            </ol>
            <ol>
                3.用户点击车站数量，可跳转查询当前线路包含车站，并对其进行管理。
            </ol>
            <h3>三、车站管理</h3>
            <ol>
                1.组长角色登录进入“车站管理”模块，点击右上角的新增按钮，跳转到“新路车站”页面，可对车站进行新增。
            </ol>
            <ol>
                2.“车站管理”模块界面，展示系统当前线路，包括车站名称、类型、所属线路等信息。表格末端可对当前车站进行修改、删除操作。
            </ol>
            <ol>
                3.用户点击所属线路，可跳转查询当前车站所在线路，并对其进行管理。
            </ol>
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
