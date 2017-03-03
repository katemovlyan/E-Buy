<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Katya
  Date: 15.02.2017
  Time: 4:52
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PostData</title>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
    <style>
        .pagination {
            display: inline-block;
        }
        .pagination a {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
<div class="container">

    <header class="page-header">
        <h1>Products</h1>
    </header>

    <div class="col-md-12">
        <c:forEach var="i" begin="0" end="4">
            <div class="row">
                <c:forEach var="j" begin="0" end="3">
                    <div class="col-md-3">
                        <div class="panel panel-default" style="height: 300px">
                            <div class="panel-body" style="height: 200px">
                                <a class="fancyimage" data-fancybox-group="group"
                                   href="data:image/jpg;base64,${photo.get(products.get(i * 4 + j))}">
                                    <img src="data:image/jpg;base64,${photo.get(products.get(i * 4 + j))}" alt=""
                                         style="height: auto; width: auto; max-width: 100%; max-height: 100%">
                                </a>
                            </div>
                            <div class="panel-footer" style="height: 100px">
                                <div class="list-group">

                                    <p class="list-group-item-text">
                                            ${products.get(i * 4 + j).productType.name} ${products.get(i * 4 + j).brand.name} ${products.get(i * 4 + j).model}
                                    </p>
                                    <p class="list-group-item-text">
                                            ${products.get(i * 4 + j).findTopicalPrice().value} ${products.get(i * 4 + j).findTopicalPrice().currency.name}
                                    </p>
                                    <p class="list-group-item-text">

                                        <a href="" style="text-align: left">
                                            Read more
                                        </a>
                                        <a href="/cart/add?productId=${products.get(i * 4 + j).id}" style="text-align: right">
                                            Add to cart
                                        </a>
                                    </p>

                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
    <%@include file="/WEB-INF/jsp/common/pagination.jsp" %>
</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
<script>
    getListForPage = function (pageNumber) {
        var amountByPage = 20;

        $.get("/list?pageNumber=" + pageNumber + "&amountPerPage=20", function (data) {
            alert("Data: " + data + "\nStatus: " + status);
        });
    }
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $("a.fancyimage").fancybox();
    });
    $(function () {

// We can attach the `fileselect` event to all file inputs on the page
        $(document).on('change', ':file', function () {
            var input = $(this),
                    numFiles = input.get(0).files ? input.get(0).files.length : 1,
                    label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [numFiles, label]);
        });

// We can watch for our custom `fileselect` event like this
        $(document).ready(function () {
            $(':file').on('fileselect', function (event, numFiles, label) {

                var input = $(this).parents('.input-group').find(':text'),
                        log = numFiles > 1 ? numFiles + ' files selected' : label;

                if (input.length) {
                    input.val(log);
                } else {
                    if (log) alert(log);
                }
            });
        });
    });
</script>
</body>
</html>
