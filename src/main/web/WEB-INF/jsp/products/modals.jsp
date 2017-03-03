<%--
  Created by IntelliJ IDEA.
  User: Katya
  Date: 16.02.2017
  Time: 2:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--type modal--%>
<%--<form>--%>
    <form class="modal fade" id="typeModal" tabindex="-1" data-width="760" aria-hidden="true"
          method="post" action="/productDependencies/productType/add">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header form-group row">
                    <div class="col-md-11">
                        <h3 class="modal-title" id="typeModalLabel">Add new type</h3>
                    </div>
                    <div class="col-md-1">
                        <button type="button" class="close form-control" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>

                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12 form-group">
                            <input class="form-control" type="text" name="productTypeName"
                                   placeholder="Enter type here" id="newType"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                    </button>
                    <input type="submit" class="btn btn-primary" title="Add" value="Add"/>
                </div>

            </div>
        </div>
    </form>
<%--</form>--%>

<%--brand modal--%>
<%--<form>--%>
    <form class="modal fade" id="brandModal" tabindex="-1" data-width="760" aria-hidden="true"
          method="post" action="/productDependencies/productBrand/add">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header form-group row">
                    <div class="col-md-11">
                        <h3 class="modal-title" id="brandModalLabel">Add new brand</h3>
                    </div>
                    <div class="col-md-1">
                        <button type="button" class="close form-control" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12 form-group">
                            <input class="form-control" type="text" name="productBrandName"
                                   placeholder="Enter brand here" id="newBrand"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </div>
        </div>
    </form>
<%--</form>--%>

<%--Currency modal--%>
<%--<form>--%>
    <form class="modal fade" id="currencyModal" tabindex="-1" data-width="760" aria-hidden="true"
          method="post" action="/productDependencies/productCurrency/add">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header form-group row">
                    <div class="col-md-11">
                        <h3 class="modal-title" id="currencyModalLabel">Add new currency</h3>
                    </div>
                    <div class="col-md-1">
                        <button type="button" class="close form-control" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12 form-group">
                            <input class="form-control" type="text" name="productCurrencyName"
                                   placeholder="Enter currency here" id="newCurrency"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </div>
        </div>
    </form>
<%--</form>--%>
