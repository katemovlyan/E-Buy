<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ankys
  Date: 12.02.2017
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-6">
        <ul class="pagination">
            <li class="${empty param.pageNumber or param.pageNumber le 1 ?'disabled':''}">
                <a href="./">&laquo;</a>
            </li>
            <c:forEach var="i" begin="1" end="${numberOfPages}">
                <li>
                    <a href="./list?pageNumber=${i}&amountPerPage=20">${i}</a>
                </li>
            </c:forEach>
            <li class="${param.pageNumber ge numberOfPages ? 'disabled': ''}">
                <a href="./list?pageNumber=${numberOfPages}&amountPerPage=20" >&raquo;</a>
            </li>
        </ul>
    </div>
    <div class="col-md-2"></div>
</div>
