<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 22.12.2016
  Time: 11:15
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
    <label class="col-md-3 control-label" for="email">Email</label>
    <div class="col-md-6">
        <input class="form-control" type="text"
               name="email"
               value="${email}"
               id="email"
               placeholder="email"/>
    </div>
</div>
<!-- ${$CURR_USER.canChangeAccessLvl(userAccessLevel) ? 'disabled' : ''} -->

<div class="form-group">
    <label class="col-md-3 control-label" for="userAccessLevel">Access level</label>
    <div class="col-md-6 ${classAdditionForAccessLevel}">
        <select class="form-control"
               name="userAccessLevel"
                id="userAccessLevel">
            ${userAccessLevels}
        </select>
    </div>
</div>
