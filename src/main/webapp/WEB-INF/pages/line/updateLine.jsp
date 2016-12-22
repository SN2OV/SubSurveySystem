<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: SN2OV
  Date: 2016/12/13
  Time: 23:22
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
                <li><a href="/admin/users">用户管理</a></li>
                <li class="active"><a href="/admin/lines">线路管理</a></li>
                <li><a href="/admin/stations">车站管理</a></li>
                <li><a href="#">使用须知</a></li>
            </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="/admin/lines">线路列表</a></li>
                <li class="active"><a href="/admin/lines/update/${line.lid}">线路修改</a></li>
                <%--<li class="active">十一月</li>--%>
            </ol>

            <h2 class="sub-header">线路修改</h2>

            <%--commandName的line很关键,通过该属性来指定我们将使用Model中的哪个属性作为form需要绑定的command对象--%>
            <%--commandName和Controller中的@ModelAttribute对应--%>
            <%--<form:form action="/admin/lines/updateP" method="post" commandName="line" role="form">--%>
                <%--<table>--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="lineName">线路名称</label>--%>
                                    <%--&lt;%&ndash;input name要和LineEntity对应&ndash;%&gt;--%>
                                <%--<input type="text" class="form-control" id="lineName" name="lineName" placeholder="输入线路名称" value="${line.lineName}"/>--%>
                                <%--&lt;%&ndash;TODO&ndash;%&gt;--%>
                            <%--</div>--%>
                        <%--</td>--%>
                        <%--<div>--%>
                            <%--<form:errors path ="lineName" cssClass="form-error"></form:errors>--%>
                        <%--</div>--%>

                        <%--<td>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="lineOrder">顺序</label>--%>
                                <%--<input type="number" class="form-control" id="lineOrder" name="lineOrder" placeholder="输入线路顺序" value="${line.lineOrder}" />--%>
                            <%--</div>--%>
                            <%--<div>--%>
                                <%--<form:errors path ="lineOrder" cssClass="error"></form:errors>--%>
                            <%--</div>--%>
                        <%--</td>--%>

                        <%--<td>--%>
                            <%--<div class="form-group">--%>
                                <%--<label for="isLooper">线路类型</label>--%>
                                <%--<select class="form-control" id="isLooper" name="isLooper" value="${line.isLooper}" >--%>
                                    <%--<option>非环线</option>--%>
                                    <%--<option>环线</option>--%>
                                <%--</select>--%>
                            <%--</div>--%>

                            <%--<div>--%>
                                <%--<form:errors path ="isLooper" cssClass="error"></form:errors>--%>
                            <%--</div>--%>
                        <%--</td>--%>

                        <%--<td>--%>
                            <%--&lt;%&ndash;必须要有form-group不然提交不出去 name必须和user的成员变量完全一样= = &ndash;%&gt;--%>
                            <%--<div class="form-group">--%>
                                <%--<input type="hidden" id="lid" name = "lid" value="${line.lid}">--%>
                            <%--</div>--%>

                        <%--</td>--%>

                    <%--</tr>--%>

                <%--</table>--%>


                <%--<div class="form-group">--%>
                    <%--<button type="submit" class="btn btn-sm btn-success">提交</button>--%>
                <%--</div>--%>
            <%--</form:form>--%>

            <div>
                <form:form class="contact_form" action="/admin/lines/updateP" method="post" name="contact_form" commandName="line" role="form">
                    <ul>
                        <li>
                            <label for="name">线路名称</label>
                            <input id="name" type="text" name = "lineName"  placeholder="1号线" value="${line.lineName}" required/>
                        </li>
                        <li>
                            <label for="email">顺序</label>
                            <input  id="email" type="text" name="lineOrder" placeholder="1" required pattern="[0-9]+$" value="${line.lineOrder}" />
                            <span class="form_hint">请输入数字</span>
                        </li>
                        <li>
                            <label for="type">线路类型</label>
                            <select class="form-control" id="type" name="isLooper" value="${line.isLooper}" >
                                <c:set var="lineType" scope="session" value="${line.isLooper}"/>
                                <c:if test="${lineType=='环线'}">
                                    <option selected>环线</option>
                                    <option>非环线</option>
                                </c:if>
                                <c:if test="${lineType=='非环线'}">
                                    <option>环线</option>
                                    <option selected>非环线</option>
                                </c:if>

                            </select>
                        </li>

                        <li style="border-bottom: 0px">
                            <input type="hidden" id="lid" name = "lid" value="${line.lid}">
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
<%--<script src="../../assets/js/docs.min.js"></script>--%>
</body>
</html>
