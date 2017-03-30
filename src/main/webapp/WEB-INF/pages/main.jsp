<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Lucene全文检索</title>

    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via dataFile:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <h1>全文检索</h1>
    <hr/>
    <div class="form-group">
        <form action="/search/item" method="post" commandName="search" role="form">
            <%--spring security加的--%>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <label for="serarchItem">索引项:</label>
            <%--input name要和UserEntity对应--%>
            <input type="text" class="form-control" id="serarchItem" name="serarchItem" placeholder="输入要查询的字段:"/>
            <br>
            <div class="form-group">
                <button type="submit" class="btn btn-sm btn-success">提交</button>
            </div>
        </form>

        <br>

        <form action="/files/" method="get" commandName="fileInfo" role="form">
            <!-- 如果文件列表非空 -->
            <c:if test="${!empty searchFileInfo}">
                <%--spring security加的--%>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <table class="table table-bordered table-striped" align="center">
                    <tr align="center">
                        <th style="text-align:center;">ID</th>
                        <th style="text-align:center;">文件名</th>
                        <th style="text-align:center;">文件路径</th>
                        <th style="text-align:center;">操作</th>
                    </tr>

                    <c:forEach items="${searchFileInfo}" var="fileInfo">
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
            </c:if>
        </form>

    </div>

</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>