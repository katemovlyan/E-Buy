<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Katya
  Date: 31.01.2017
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PostData</title>
    <%@include file="/WEB-INF/jsp/common/stylesheet.jsp" %>
</head>
<body>
<%--<script type="text/javascript" src="/res/js/check_product.js"></script>onsubmit="return checkProduct()"--%>
<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
<div class="container">
    <div class="col-md-12">
        <header class="page-header">
            <h1>Edit Product</h1>
        </header>
    </div>

    <%@include file="/WEB-INF/jsp/products/modals.jsp" %>
    <div class="row">

        <div class="col-md-1"></div>

        <div class="col-md-10">

            <form name="edit_form" action="" method="post" enctype="multipart/form-data">

                <div class="form-group row">

                    <label class="col-md-2" style="text-align: right">Photo</label>

                    <div class="col-md-4">
                        <img src="data:image/jpg;base64,${productImage}" alt=""
                             style="height: auto; width: auto; max-width: 200px; max-height: 200px;">
                    </div>

                    <div class="col-md-3">
                        <div class="input-group">
                            <input type="text" class="form-control" readonly>
                            <label class="input-group-btn">
                                <span class="glyphicon glyphicon-search btn btn-primary btn-md">
                                    <input type="file" name="fileUpload" style="display: none;" multiple>
                                </span>
                            </label>
                        </div>
                    </div>
                    <div class="col-md-1">
                    </div>
                </div>

                <div class="form-group row">

                    <label class="col-md-2" style="text-align: right">Type</label>

                    <div class="col-md-7">
                        <form:hidden path="topicalPrice.product.id"/>
                        <form:select class="selectpicker form-control" path="topicalPrice.product.productType.id"
                                     title="${topicalPrice.product.productType.name}" data-live-search="true">
                            <form:options items="${types}" itemValue="id" itemLabel="name"/>
                        </form:select>
                    </div>

                    <div class="col-md-1">
                        <button type="button" class="btn btn-primary btn-md" data-toggle="modal"
                                data-target="#typeModal">
                            <i class="fa fa-plus-square"></i>
                        </button>
                    </div>

                </div>

                <div class="form-group row">

                    <label class="col-md-2" style="text-align: right">Brand</label>

                    <div class="col-md-7">
                        <form:select path="topicalPrice.product.brand.id" class="selectpicker form-control"
                                     title="${topicalPrice.product.brand.name}"
                                     data-live-search="true">
                            <form:options items="${brands}" itemValue="id" itemLabel="name"/>
                        </form:select>
                    </div>

                    <div class="col-md-1">
                        <button type="button" class="btn btn-primary btn-md" style="text-align: left"
                                data-toggle="modal" data-target="#brandModal">
                            <i class="fa fa-plus-square"></i>
                        </button>
                    </div>
                </div>

                <div class=" form-group row">
                    <label class="col-md-2" style="text-align: right" for="model">Model</label>
                    <div class="col-md-7">
                        <form:input path="topicalPrice.product.model" class="form-control" type="text" name="model"
                                    value="${topicalPrice.product.model}" placeholder="Enter model here" id="model"/>
                    </div>
                    <div class="col-md-1"></div>
                </div>

                <div class="form-group row">

                    <form:hidden path="topicalPrice.lastUpdated"/>
                    <form:hidden path="topicalPrice.isTopical"/>
                    <form:hidden path="topicalPrice.id"/>

                    <label class="col-md-2" style="text-align: right">Price</label>

                    <div class="col-md-4">

                        <form:input path="topicalPrice.value" class="form-control" type="text"
                                    value="${topicalPrice.value}"
                                    placeholder="Enter price here" id="price"/>
                    </div>

                    <div class="col-md-3">
                        <div class="form-group">
                            <form:select path="topicalPrice.currency.id" class="selectpicker form-control"
                                         data-live-search="true" name="currency"
                                         title="${topicalPrice.currency.name}">
                                <form:options items="${currencies}" itemValue="id" itemLabel="name"/>
                            </form:select>
                        </div>
                    </div>

                    <div class="col-md-1">
                        <button type="button" class="btn btn-primary btn-md" style="text-align: left"
                                data-toggle="modal" data-target="#currencyModal">
                            <i class="fa fa-plus-square"></i>
                        </button>
                    </div>
                </div>

                <div class="form-group row">

                    <label class="col-md-2" style="text-align: right">Description</label>

                    <div class="col-md-7">
                        <form:textarea path="topicalPrice.product.description" class="form-control" type="text"
                                       name="description" rows="6"
                                       placeholder="Enter description here" id="description"/>
                        <script>
                            document.getElementById("description").defaultValue = "${topicalPrice.product.description}";
                        </script>
                    </div>
                    <div class="col-md-1">
                    </div>
                </div>
                <div class="col-md-2"></div>
                <div class="col-md-7">
                    <div class="pull-left">
                        <c:if test="${topicalPrice.product.id != null}">
                            <a href="/admin/products/delete?productId=${topicalPrice.product.id}"
                               onclick="return confirm('Are you sure want delete ${topicalPrice.product.id} item?');"
                               class="btn btn-lg btn-danger">
                                <i class="fa fa-fw fa-trash"></i>
                            </a>
                        </c:if>
                    </div>
                    <div class="pull-right">
                        <a href="./" class="btn btn-lg btn-info">
                            <i class="fa fa-mail-reply"></i>
                        </a>
                        <button class="btn btn-lg btn-success" type="submit" name="add">
                            <i class="fa fa-check-square-o"></i>
                        </button>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </form>
        </div>
        <div class="col-md-1"></div>
    </div>

    <c:if test="${errors != null}">
        <br>
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <div class="alert alert-danger">${errors}</div>
            </div>
            <div class="col-md-2"></div>
        </div>
    </c:if>
</div>
<%@include file="/WEB-INF/jsp/common/javascript.jsp" %>
<script>
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

<script>
    sayHello = function () {
        $.ajax({
            url: "/productType/addProduct",
            type: "POST",
            data: $("#add_type_form").serialize(),
            success: function (data) {
                alert("Successfully submitted.")
            }
        });
    };
</script>
</body>
</html>
