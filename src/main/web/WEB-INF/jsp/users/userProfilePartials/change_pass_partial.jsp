<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.12.2016
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-group">

    <label class="col-md-3 control-label" for="current_password">
        <mark>
            <strong>${userName} </strong>
        </mark>
        password
    </label>

    <div class="col-md-6 ${classAdditionForCurrentPassword}">
        <input class="form-control" type="password" name="current_password" id="current_password"
               placeholder="current password"/>
    </div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label" for="password">New password</label>
    <div class="col-md-6 ${classAdditionForNewPassword}">
        <input class="form-control" type="password" name="password" id="password"
               placeholder="new password"/>
    </div>
</div>

<div class="form-group">
    <label class="col-md-3 control-label" for="confirm_password">Confirm password</label>
    <div class="col-md-6 ${classAdditionForNewPassword}">
        <input class="form-control" type="password" name="confirm_password" id="confirm_password"
               placeholder="new password again"/>
    </div>
</div>
