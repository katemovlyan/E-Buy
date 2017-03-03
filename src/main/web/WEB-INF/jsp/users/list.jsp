<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ankys
  Date: 11.02.2017
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>

</head>
<body>
<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
<div class="container">
    <header class="page-header">
        <h1>Users</h1>
    </header>
    <div class="row">
        <div class="col-md-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h5>Users` count <span class="label label-default">${totalUsersCount}</span></h5>
                </div>
                <div class="panel-body text-center">
                    <a class="btn btn-success pull-center" href="./new">
                        <i class="fa fa-fw fa-plus"></i>
                    </a>
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <table class="table table-striped">
                <thead>
                <tr>
                    <!--<th style="width: 1%" class="text-right">id</th>
                    <th style="width: 1%" ></th>-->
                    <th>username</th>
                    <th>access level</th>
                    <th>email</th>
                    <!--
                    <th class="text-right">email</th>-->
                    <th style="width: 1%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="item">
                    <tr>
                        <!--<td class="text-right">${item.id}</td>
                        <td></td>-->
                        <td>${item.username}</td>
                        <td>${item.accessLvl}</td>
                        <td>${item.email}</td>
                        <!--
                        <td class="text-right">
                            <div class="input-group">
                            <a class="btn btn-xs btn-primary" href="mailto:${item.email}">${item.email}</a>
                                <span class="btn-addon">@</span></div>
                        </td>-->
                        <td class="text-right" nowrap>
                            <a href="./edit?id=${item.id}"
                               class="btn btn-sm btn-warning">
                                <i class="fa fa-fw fa-wrench"></i>
                            </a>

                            <a href="./delete?id=${item.id}"
                               class="btn btn-sm btn-danger">
                                <i class="fa fa-fw fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-md-2"></div>
    </div>
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-6">
            <ul class="pagination">
                <li class="${empty param.pageNumber or param.pageNumber le 1 ?'disabled':''}">
                    <a href="/users/">&laquo;</a>
                    <%--<input type="button" onclick="getListForPage(1)">--%>
                </li>
                <c:forEach var="i" begin="1" end="${numberOfPages}">
                    <li>
                        <a href="/users/list?pageNumber=${i}&amountPerPage=20">${i}</a>
                            <%--<input type="button" onclick="getListForPage(${i})">--%>
                    </li>
                </c:forEach>
                <li class="${param.pageNumber ge numberOfPages ? 'disabled': ''}">
                    <a href="/users/list?pageNumber=${numberOfPages}&amountPerPage=20" >&raquo;</a>
                    <%--<input type="button" onclick="getListForPage(${numberOfPages})">--%>
                </li>
            </ul>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
