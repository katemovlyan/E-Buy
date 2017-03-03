<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.12.2016
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/res/css/user_profile_styles.css"/>
<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-6 col-md-6">
            <div class="well well-sm">
                <div class="row">
                    <div class="col-sm-6 col-md-4">
                        <img src="http://placehold.it/380x500" alt="" class="img-rounded img-responsive"/>
                    </div>
                    <div class="col-sm-6 col-md-8">
                        <h4> ${$USERNAME} </h4>
                        <small>
                            <cite title="UsersAddress">
                                Ukraine <i class="glyphicon glyphicon-map-marker"> </i>
                            </cite>
                        </small>
                        <p>
                            <i class="glyphicon glyphicon-envelope"></i>${$CURR_USER.email}
                            <br/>
                            <i class="glyphicon glyphicon-globe"></i><a href="http://www.jquery2dotnet.com">LinkToWebSite</a>
                            <br/>
                            <i class="glyphicon glyphicon-gift"></i>BirthDay</p>
                        <!-- Split button -->
                        <div class="btn-group">
                            <a href="/admin/users/edit?id=${$CURR_USER.id}"
                               class="btn btn-warning">
                                edit profile
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

