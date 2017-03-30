<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: SN2OV
  Date: 2016/12/23
  Time: 10:13
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
                <li><a href="/loginout">登出（${userName}/管理员）</a></li>
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
                <li><a href="/admin/ReadMe">使用须知</a></li>
            </ul>
        </div>



        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li class="active"><a href="/admin/lines">线路列表</a></li>
                <li class="active"><a href="#">线路车站列表</a></li>
                <a id = "login" class="mynavbar-right" onclick="showDiv()">
                    <span class="glyphicon glyphicon-plus"></span>
                </a>
            </ol>

            <%--<input type="Submit" name="" value="显示层" onclick="javascript:showDiv()" />--%>

            <h2 class="sub-header">线路车站列表</h2>
            <!-- 如果线路车站列表为空 -->
            <c:if test="${empty stationList}">
                <div class="alert alert-warning" role="alert">
                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>系统该线路暂无车站
                </div>
            </c:if>

            <div class="table-responsive">
                <c:if test="${!empty stationList}">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th class="tableHeadStyle">序号</th>
                            <th class="tableHeadStyle">线路名称</th>
                            <th class="tableHeadStyle">车站</th>
                            <th class="tableHeadStyle">车站顺序</th>
                            <th class="tableHeadStyle">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${stationList}" var="station">
                            <tr>
                                <%--序号--%>
                                <td>${stationList.indexOf(station)+1}</td>
                                <%--线路名称--%>
                                <td>${line.lineName}</td>
                                <%--车站--%>
                                <td>${station.stationName}</td>
                                <%--车站顺序--%>
                                <td>${stationOrderArr.get(stationList.indexOf(station))}</td>

                                <td>
                                    <%--TODO 排序--%>
                                    <a href="/admin/lines/line-stations/up/${line.lid}_${station.sid}" title="view">
                                        <span class="glyphicon glyphicon-arrow-up"></span>
                                    </a>
                                    <a href="/admin/lines/line-stations/down/${line.lid}_${station.sid}" title="view">
                                        <span class="glyphicon glyphicon-arrow-down"></span>
                                    </a>
                                    <a href="/admin/lines/line-stations/delete/${line.lid}_${station.sid}" title="view">
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

    <%--添加该线路车站的对话框--%>
    <div id="addStationDiv" class="mydiv" style="display:none;">
        <%--commandName和@ModelAttrobute对应--%>
        <form:form action="addP/${line.lid}" method="post" commandName="stationLine">
            <label for="stationLineAdd" style="font-size: 20px">选择车站</label>
            <select class="form-control" id="stationLineAdd" name="stationLineAdd" >
                <c:forEach items="${stationNotInLineList}" var="stationNotInLine">
                    <option>${stationNotInLine.stationName}</option>
                </c:forEach>
            </select>

        <div class="form-group" style="float: right">
            <button type="submit" class="btn btn-sm btn-success">提交</button>
            <button type="button" class="btn btn-sm btn-default" onclick="closeDiv()">取消</button>
        </div>
        </form:form>
    </div>

    <div id="bg" class="bg" style="display:none;"></div>



    <div style="padding-top: 20px;">
        <input type="Submit" name="" value="显示层" onclick="javascript:showDiv()" />
    </div>



</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script type="text/javascript">

    function showDiv(){
        document.getElementById('addStationDiv').style.display='block';
        document.getElementById('bg').style.display='block';
    }

    function closeDiv(){
        document.getElementById('addStationDiv').style.display='none';
        document.getElementById('bg').style.display='none';
    }


</script>

</body>
</html>
