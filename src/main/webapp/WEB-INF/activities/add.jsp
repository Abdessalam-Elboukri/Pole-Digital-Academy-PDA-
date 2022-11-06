<%--
  Created by IntelliJ IDEA.
  User: Omar Kazoum
  Date: 03/11/2022
  Time: 09:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="com.example.pole_digital_academy.Entities.Activity"  %>
<%@ page import="java.util.stream.Stream" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="com.example.pole_digital_academy.utils.Constants" %>
<%@ page import="com.example.pole_digital_academy.Entities.Responsible" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Add An Activity </title>
</head>
<body>
<%@ include file="../layout/header.jsp"%>
<ul>
<%
    List<String> errors=(List<String>) request.getAttribute(Constants.KEY_VALIDATION_ERRORS);
    if(errors!=null)
        out.println(errors.stream().map(e->"<li>"+e+"</li>").collect(Collectors.joining("")));
%>
</ul>
<%--
<c:set var="errors" value="${requestScope.get(Constants.KEY_VALIDATION_ERRORS)}" scope="request"/>
<c:if test="${errors!=null}">
    <% request.setAttribute("errors",((List<String>)request.getAttribute("errors"))); %>
    <ul>
    <c:forEach step="1" begin="0" end="${errors.size()}" var="i">
        <li><c:out value="${errrors.get(i)}"/></li>
    </c:forEach>
    </ul>
</c:if>
--%>
<form action="" method="post" class="w-2/3 items-center">
    <div class="flex flex-row gap-1 items center">
        <label for="${Activity.KEY_TITLE}" class="w-1/2">Activity name:</label>
        <input type="text" class="w-1/2" name="${Activity.KEY_TITLE}" id="${Activity.KEY_TITLE}" placeholder="activity title">
    </div>
<div class="flex flex-row gap-1 items center">
        <label for="${Activity.KEY_DESCRIPTION}" class="w-1/2">Activity description:</label>
        <input type="text" class="w-1/2" name="${Activity.KEY_DESCRIPTION}" id="${Activity.KEY_DESCRIPTION}" placeholder="activity description">
    </div>
<div class="flex flex-row gap-1 items center">
        <label for="${Activity.KEY_START_DATE}" class="w-1/2">Start date:</label>
        <input type="date" class="w-1/2" name="${Activity.KEY_START_DATE}" id="${Activity.KEY_START_DATE}">
    </div>
<div class="flex flex-row gap-1 items center">
        <label for="${Activity.KEY_END_DATE}" class="w-1/2">End date:</label>
        <input type="date" class="w-1/2" name="${Activity.KEY_END_DATE}" id="${Activity.KEY_END_DATE}">
    </div>
    <!--TODO continue here -->
<div class="flex flex-row gap-1 items center">
    <label for="${Activity.KEY_ACTIVITY_TYPE}" class="w-1/2">Activity type:</label>
    <select class="w-1/2" name="${Activity.KEY_ACTIVITY_TYPE}" title="activity type" id="${Activity.KEY_ACTIVITY_TYPE}">
        <%= Stream.of(Activity.ActivityTypeEnum.values()).map(t->"<option value=\""+t.ordinal()+"\">"+t.toString()+"</option>").collect(Collectors.joining("")) %>
    </select>

    </div>
<div class="flex flex-row gap-1 items center">
    <label for="${Activity.KEY_RESPONSIBLE_ID}" class="w-1/2">Responsible for this activity:</label>
    <select class="w-1/2" name="${Activity.KEY_RESPONSIBLE_ID}" title="activity type" id="${Activity.KEY_RESPONSIBLE_ID}">
        <%= ((List<Responsible>)request.getAttribute(Constants.KEY_RESPONSIBLES)).stream().map(r->"<option value=\""+r.getId()+"\">"+r.getLastName()+" "+r.getFirstName()+"</option>").collect(Collectors.joining("")) %>
    </select>
    <input type="submit" value="send">
    </div>

</form>
</body>
</html>
