<%@ page import="com.example.pole_digital_academy.utils.Constants" %>
<%@ page import="com.example.pole_digital_academy.Entities.Activity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../includes.jsp"%>
<html>
<head>
    <title>activities list</title>
    <style>
        table{
            border-collapse: collapse;
        }
        table td,th{
            border: black solid 2px;
        }
    </style>
</head>
<body>
<%@ include file="../layout/header.jsp"%>
<c:if test="${!empty requestScope.message}">
    <p>
        <c:out value="${requestScope.message}"></c:out>
    </p>
</c:if>
<h1 class="font-bold">activities list</h1>
<% List<Activity> activities=((List<Activity>)request.getAttribute(Constants.KEY_ACTIVITIES_LIST)); %>
<div><a href="${URI}/add">add </a></div>
<table>
    <tr>
        <th>#</th>
        <th>activity name</th>
        <th>description</th>
        <th>start date</th>
        <th>end date</th>
        <th>status</th>
        <th>type</th>
        <th>responsible</th>
    </tr>
    <% for(int i=0;i<activities.size();i++){
        Activity a=activities.get(i);
    %>
        <tr>
            <td><%= a.getId()%></td>
            <td><%= a.getTitle() %></td>
            <td><%= a.getDescription() %></td>
            <td><%= a.getStartDate() %></td>
            <td><%= a.getEndDate() %></td>
            <td><%= a.getStatus().toString() %></td>
            <td><%= a.getActivityType().toString() %></td>
            <td><%= a.getResponsible().getFirstName()+" "+ a.getResponsible().getLastName()%></td>
            <td><a href="${URI}/edit?id=<%= a.getId()%>">edit</a></td>
            <td><a href="${URI}/delete?id=<%= a.getId()%>">delete</a></td>
        </tr>

    <% }%>


</table>
</body>
</html>
