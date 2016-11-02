<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>文件管理</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="container">
    <h1>文件管理</h1>
    <hr/>
    <h3>所有文件</h3>
    <%--<a href="#">--%>
        <%--<input type="file" class="btn btn-primary btn-sm" value="添加">--%>
    <%--</a>--%>
    <form action="/file/manage.do" method="post" commandName="fileAdd" role="form" enctype="multipart/form-data">
        <div style="width:400px;float: left">
        <input type="file" id="fileAdd" name="fileAdd">
        </div>
        <div style="width:90px;float:left">
        <button type="submit" class="btn btn-primary btn-sm">添加新文件</button>
        </div>
        <div style="width:600px;float: left">
            <font color="red">${fileName}${uploadResult}</font>
        </div>
    </form>
    <form action="/file/manage.action" method="post" commandName = "indexRecreate" role="form">
        <div style="width:70px;float:right">
            <button type="submit" class="btn btn-primary btn-sm" onclick="prompt()">更新索引</button>
        </div>
    </form>

    <!-- 如果文件列表非空 -->
    <%--<c:if test="${!empty searchFileInfo}">--%>
        <table class="table table-bordered table-striped" align="center">
            <tr align="center">
                <th style="text-align:center;">ID</th>
                <th style="text-align:center;">文件名</th>
                <th style="text-align:center;">文件路径</th>
                <th style="text-align:center;">操作</th>
            </tr>

            <c:forEach items="${filesInfo}" var="fileInfo">
                <tr >
                        <%--<td><input type = "text" name = "FID" value="${fileInfo.fID}"></td>--%>
                    <td align="center" name = "fID">${fileInfo.fID}</td>
                    <td align="center" name = "fname">${fileInfo.fname}</td>
                        <%--<td><input type = "hidden" align="center" name = "fname" value="${fileInfo.fname}"></td>--%>
                    <td align="center" name = "fpath">${fileInfo.fpath}</td>
                        <%--<input type="text" id="fname" name="fname" value="${fileInfo.fname}"/>--%>
                    <td align="center">
                        <a href="/search/files/show/${fileInfo.fID}" type="commit" class="btn btn-sm btn-success">详情</a>
                    </td>
                </tr>

            </c:forEach>
        </table>
    <%--</c:if>--%>

</div>

<script language="JavaScript">
    function prompt(){
        alert("索引重新创建成功");
    }
</script>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>