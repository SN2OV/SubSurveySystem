<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: SN2OV
  Date: 2017/1/4
  Time: 14:11
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
                <li class="active"><a href="/captain/transfer/show/${teamTask.teamTaskId}">任务详情(${teamTask.taskName})</a></li>
                <li class="active"><a href="/captain/transfer/show/${teamTask.teamTaskId}/subtask">子任务</a> </li>

                <a href="/captain/transfer/show/${teamTask.teamTaskId}/subtask/add" class="mynavbar-right">
                    <span class="glyphicon glyphicon-plus"></span>
                </a>
            </ol>

            <h2 class="sub-header">${teamTask.taskName}子任务列表</h2>

            <!-- 如果用户列表为空 -->
            <c:if test="${empty subTransferList}">
                <div class="alert alert-warning" role="alert">
                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>系统暂无换乘量调查子任务
                </div>
            </c:if>

            <div class="table-responsive">
                <c:if test="${!empty subTransferList}">
                    <table class="table table-striped table-bordered">
                        <thead>
                        <tr>
                            <th class="tableHeadStyle">序号</th>
                            <th class="tableHeadStyle">任务名称</th>
                            <th class="tableHeadStyle">调查员</th>
                            <th class="tableHeadStyle">调查点位</th>
                            <th class="tableHeadStyle">位置</th>
                            <th class="tableHeadStyle">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${subTransferList}" var="subTransfer">
                            <tr>
                                <td>${subTransferList.indexOf(subTransfer)+1}</td>
                                <td>${subTransfer.name}</td>
                                <td>
                                    <%--TODO 这里indexOf就是好的啊--%>
                                        <%--${subTransferList.indexOf(subTransfer)}--%>
                                    <c:set var = "userList" value="${userListArr.get(subTransferList.indexOf(subTransfer))}"></c:set>
                                    <c:forEach items="${userList}" var="user">
                                        ${user.realName}
                                        <c:if test="${userList.indexOf(user)!=userList.size()-1}">
                                            ,
                                        </c:if>
                                    </c:forEach>

                                </td>
                                <td>${subTransfer.pointLocation}</td>
                                <td>${subTransfer.position}</td>
                                <td>
                                    <a href="/captain/transfer/show/${teamTask.teamTaskId}/subtask/show/${subTransfer.tid}" title="view">
                                        <span class="glyphicon glyphicon-eye-open"></span>
                                    </a>
                                    <a href="/captain/transfer/show/${teamTask.teamTaskId}/subtask/update/${subTransfer.tid}" title="view">
                                        <span class="glyphicon glyphicon-pencil"></span>
                                    </a>
                                    <a href="/captain/transfer/show/${teamTask.teamTaskId}/subtask/delete/${subTransfer.tid}" title="view">
                                        <span class="glyphicon glyphicon-trash"></span>
                                    </a>
                                    <c:set var="pertaskNo" value="${subTransferList.indexOf(subTransfer)}"></c:set>
                                    <a name = "addUserForPerTask" index = "${pertaskNo}">
                                        <span class="glyphicon glyphicon-plus"></span>
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

    <%--&lt;%&ndash;添加子任务-调查员的对话框&ndash;%&gt;--%>
    <%--<div id="addPersonDiv" class="mydiv" style="display:none;">--%>
        <%--&lt;%&ndash;commandName和@ModelAttrobute对应&ndash;%&gt;--%>
        <%--<form:form action="addP/${line.lid}" method="post" commandName="perTaskUser">--%>
            <%--<label for="perTaskUserAdd" style="font-size: 20px">选择调查员</label>--%>
            <%--<select class="form-control" id="perTaskUserAdd" index = 0 name="perTaskUserAdd" >--%>
                <%--&lt;%&ndash;<c:set var = "indexa" value=""></c:set>&ndash;%&gt;--%>
                <%--<c:set var="unAllocatedUserList" value="${unAllotedUserListArr.get(pertaskNo)}"></c:set>--%>
                <%--<c:forEach items="${unAllocatedUserList}" var="unAllocatedUser">--%>
                    <%--<option>${unAllocatedUser.realName}</option>--%>
                <%--</c:forEach>--%>
            <%--</select>--%>

            <%--<div class="form-group" style="float: right">--%>
                <%--<button type="submit" class="btn btn-sm btn-success">提交</button>--%>
                <%--<button type="button" class="btn btn-sm btn-default" onclick="closeDiv()">取消</button>--%>
            <%--</div>--%>
        <%--</form:form>--%>
    <%--</div>--%>

    <c:forEach items="${unAllotedUserListArr}" var="unAllocatedUserList">
        <%--index =    ${unAllotedUserListArr.indexOf(unAllocatedUserList)}--%>
        <div name="addPersonDiv" class="mydiv" style="display:none;" index = ${unAllotedUserListArr.indexOf(unAllocatedUserList)}>
                <%--commandName和@ModelAttrobute对应--%>
            <form:form action="subtask/addPerson/${unAllotedUserListArr.indexOf(unAllocatedUserList)}" method="post" commandName="perTaskUser">
                <label for="perTaskUserAdd" style="font-size: 20px" index = ${unAllotedUserListArr.indexOf(unAllocatedUserList)}>选择调查员</label>
                <select class="form-control" id="perTaskUserAdd" name="perTaskUserAdd" >
                    <c:set var="unAllocatedUserList" value="${unAllocatedUserList}"></c:set>
                    <c:forEach items="${unAllocatedUserList}" var="unAllocatedUser">
                        <option>${unAllocatedUser.realName}</option>
                    </c:forEach>
                </select>

                <div class="form-group" style="float: right">
                    <button type="submit" class="btn btn-sm btn-success">提交</button>
                    <button type="button" class="btn btn-sm btn-default" onclick="closeDiv()">取消</button>
                </div>
            </form:form>
        </div>
    </c:forEach>

    <%--<c:set var="unAllocatedUserList" value="${unAllotedUserListArr.get(pertaskNo)}"></c:set>--%>
    <%--<c:forEach items="${unAllocatedUserList}" var="unAllocatedUser">--%>
        <%--<option>${unAllocatedUser.realName}</option>--%>
    <%--</c:forEach>--%>



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

    <%--$("#addUserForPerTask").click(function () {--%>
        <%--alert(${pertaskNo});--%>
    <%--});--%>

    $("a[name='addUserForPerTask']").click(function () {
        //修改index属性
//        $("#perTaskUserAdd").attr('index',$(this).attr("index"));
        var indexa = $(this).attr("index");
        //显示index对应的div
        $("div[name = 'addPersonDiv'][index= "+ indexa +"]").css('display','block');

        document.getElementById('bg').style.display='block';
    });

//        function showDiv(){
//            document.getElementById('addPersonDiv').style.display='block';
//            document.getElementById('bg').style.display='block';
//        }

        function closeDiv(){
            $("div[name = 'addPersonDiv']").css('display','none');
            document.getElementById('bg').style.display='none';
        }

</script>

</body>
</html>
