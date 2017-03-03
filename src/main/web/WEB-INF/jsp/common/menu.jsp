<%--
  Created by IntelliJ IDEA.
  User: ankys
  Date: 12.02.2017
  Time: 3:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar-wrapper">
    <div class="container">

        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <i class="fa fa-fw fa-bars"></i>
                    </button>
                    <a class="navbar-brand" href="/">Ebuy</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li ${page_type.equals('products') ? 'class="active"' : ''}><a href="/index">Products</a></li>
                        <li ${page_type.equals('tiles') ? 'class="active"' : ''}><a href="/products">Tiles</a></li>
                        <li ${page_type.equals('filter') ? 'class="active"' : ''}><a href="/filter">Filter</a></li>
                        <li ${page_type.equals('pages') ? 'class="active"' : ''}><a href="/users/">Users</a></li>
                        <li ${page_type.equals('cart') ? 'class="active"' : ''}>
                            <a class="fa fa-shopping-cart" href="/cart"><span class="badge">${shoppingCart.items.size()}</span></a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <c:choose>
                            <c:when test="${$CURR_USER != null}">
                                <li><a href="/users/profile"><span class="glyphicon glyphicon-user"></span> ${$CURR_USER.username}</a></li>
                                <li><a href="/users/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="/register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                                <li><a href="/auth"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </nav>

    </div>
</div>
