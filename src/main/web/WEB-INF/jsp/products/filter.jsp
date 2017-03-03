<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: human
  Date: 1/31/17
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
<div class="container">
    <header class="page-header">
        <h1>Products with filter</h1>
    </header>
    <div class="row">
        <div class="col-md-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h5>Products count <span class="label label-default">${totalProductsCount}</span></h5>
                </div>
                <div class="panel-body text-center">
                    <a class="btn btn-success pull-center" href="./new">
                        <i class="fa fa-fw fa-plus"></i>
                    </a>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h5>Products filtered <span class="label label-default">${filteredProductsCount}</span></h5>
                </div>
                <div class="panel-body text-center">
                    <ul class="nav nav-tabs" id="product_types_tabs">
                        <c:forEach items="${product_types}" var="type">
                            <li><a href="${pageContext.servletContext.contextPath.concat('/filter/').concat(type.id)}">${type.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-10">
            <table class="col-md-12 table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Model</th>
                    <th>Product Type</th>
                    <th>Brand</th>
                    <%--<th>Price</th>
                    <th style="width: 16%">Last updated</th>--%>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="prod">

                    <tr>
                        <td>${prod.id}</td>
                        <td>${prod.model}</td>
                        <td>${prod.productType.name}</td>
                        <td>${prod.brand.name}</td>
                            <%--<td>${prod.price.value} ${prod.price.currency.name}</td>
                            <td>${prod.price.lastUpdated}</td>--%>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
