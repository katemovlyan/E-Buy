<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.12.2016
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-group">
    <label class="col-md-3 control-label" for="username">Username</label>
    <div class="col-md-6 ${classAdditionForUsername}">
        <input class="form-control" type="text"
               name="username"
               value="${userName}"
               id="username"
               placeholder="username"/>
    </div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label" for="password">Password</label>
    <div class="col-md-6 ${classAdditionForNewPassword}">
        <input class="form-control" type="password"
               name="password"
               id="password"
               placeholder="password"/>
    </div>
</div>


<div class="form-group">
    <label class="col-md-3 control-label" for="confirm_password">Confirm password</label>
    <div class="col-md-6 ${classAdditionForNewPassword}">
        <input class="form-control" type="password"
               name="confirm_password"
               id="confirm_password"
               placeholder="password again"/>
    </div>
</div>
