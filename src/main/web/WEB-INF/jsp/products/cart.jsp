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
        <h1>Shopping cart</h1>
    </header>
    <div class="row">
        <div class=" col-md-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Description</th>
                    <th>Count</th>
                    <th>Price</th>
                    <th style="width: 1%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${shoppingCart.items}" var="cartItem">

                    <tr>
                        <td>${cartItem.key.id}</td>
                        <td>${cartItem.key.brand.name} ${cartItem.key.productType.name} ${cartItem.key.model}</td>
                        <td>${cartItem.value.amount}</td>
                        <td>${cartItem.value.price}</td>
                        <td nowrap>
                            <a href="/cart/remove?productId=${cartItem.key.id}"
                               onclick="return confirm('Are you sure want delete ${cartItem.key.id} item?');"
                               class="btn btn-md btn-danger">
                                <i class="fa fa-fw fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
            <h3 class="text-right">Total: ${shoppingCart.total}</h3>
            <div class="pull-right">${payBtn}</div>
            <%--<div class="pull-right"><a class="btn btn-warning btn-lg" href="/cart/buy">Buy All</a></div>--%>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
</body>
</html>
