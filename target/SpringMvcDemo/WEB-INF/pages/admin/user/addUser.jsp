<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <link rel="stylesheet" media="screen" href="/resources/css/form-validate.css" >

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
                <li class="active"><a href="/admin/users">用户管理</a></li>
                <li><a href="/admin/lines">线路管理</a></li>
                <li><a href="/admin/stations">车站管理</a></li>
                <li><a href="/admin/ReadMe">使用须知</a></li>
            </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="/admin/main">用户列表</a></li>
                <li class="active"><a href="/admin/users/add">用户新增</a></li>
                <%--<li class="active">十一月</li>--%>
            </ol>

            <h2 class="sub-header">用户新增</h2>

            <%--commandName的user很关键,通过该属性来指定我们将使用Model中的哪个属性作为form需要绑定的command对象--%>
            <form:form action="/admin/users/addP" class="contact_form" method="post" commandName="user" role="form">
                <%--<table>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="userName">用户名</label>--%>
                                    <%--&lt;%&ndash;input name要和UserEntity对应&ndash;%&gt;--%>
                                <%--<input type="text" class="form-control" id="userName" name="userName" placeholder="输入用户名:"/>--%>
                            <%--</div>--%>
                        <%--</td>--%>

                        <%--<td>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="password">密码</label>--%>
                                <%--<input type="password" class="form-control" id="password" name="password" placeholder="输入密码"/>--%>
                            <%--</div>--%>
                        <%--</td>--%>

                       <%--<td>--%>
                           <%--<div class="form-group">--%>
                               <%--<label for="realName">姓名</label>--%>
                               <%--<input type="text" class="form-control" id="realName" name="realName" placeholder="输入姓名"/>--%>
                           <%--</div>--%>
                       <%--</td>--%>

                    <%--</tr>--%>

                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="role">角色</label>--%>
                                    <%--&lt;%&ndash;input name要和UserEntity对应&ndash;%&gt;--%>
                                <%--<input type="text" class="form-control" id="role" name="role" placeholder="输入角色"/>--%>
                            <%--</div>--%>
                        <%--</td>--%>

                        <%--<td>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="mobile">手机号</label>--%>
                                <%--<input type="text" class="form-control" id="mobile" name="mobile" placeholder="输入手机号"/>--%>
                            <%--</div>--%>
                        <%--</td>--%>

                        <%--<td>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="idCard">身份证号</label>--%>
                                <%--<input type="text" class="form-control" id="idCard" name="idCard" placeholder="输入身份证号"/>--%>
                            <%--</div>--%>
                        <%--</td>--%>

                        <%--<td>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="nfc">公交卡号</label>--%>
                                <%--<input type="text" class="form-control" id="nfc" name="nfc" placeholder="输入公交卡号"/>--%>
                            <%--</div>--%>
                        <%--</td>--%>
                    <%--</tr>--%>

                <%--</table>--%>

                <ul>
                    <li>
                        <label for="userName">用户名</label>
                        <input id="userName" type="text" name = "userName"  placeholder="" required/>
                    </li>
                    <li>
                        <label for="password">密码</label>
                        <input id="password" type="password" name = "password"  placeholder="" required/>
                    </li>
                    <li>
                        <label for="realName">姓名</label>
                        <input id="realName" type="text" name = "realName"  placeholder="" required/>
                    </li>
                    <li>
                        <label for="role">角色</label>
                        <%--<input id="role" type="text" name = "role"  placeholder="调查员" required/>--%>
                        <select class="form-control" id="role" name="role" >
                            <option selected>调查员</option>
                            <option>组长</option>
                            <option>督导</option>
                            <option>管理员</option>
                        </select>
                    </li>
                    <li>
                        <label for="mobile">手机号</label>
                        <input  id="mobile" type="text" name="mobile" placeholder="" required pattern="[0-9]+$" />
                        <span class="form_hint">请输入数字</span>
                    </li>
                    <li>
                        <label for="idCard">身份证号</label>
                        <input  id="idCard" type="text" name="idCard" placeholder="" required pattern="[0-9]+$" />
                        <span class="form_hint">请输入数字</span>
                    </li>
                    <li>
                        <label for="nfc">公交卡号</label>
                        <input  id="nfc" type="text" name="nfc" placeholder="" required pattern="[0-9]+$" />
                        <span class="form_hint">请输入数字</span>
                    </li>
                    <%--<li>--%>
                        <%--<label for="type">线路类型:</label>--%>
                        <%--<select class="form-control" id="type" name="isLooper" >--%>
                            <%--<option selected>环线</option>--%>
                            <%--<option>非环线</option>--%>
                        <%--</select>--%>
                    <%--</li>--%>

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
