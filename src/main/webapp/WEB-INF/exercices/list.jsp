<%@ page import="com.example.pole_digital_academy.utils.Constants" %>
<%@ page import="com.example.pole_digital_academy.Entities.Exercice" %>
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
<% String msg=request.getParameter("message");
    if(msg!=null){
    out.print("<p>"+msg+"</p>");
};%>
<h1 class="font-bold">Exercices list</h1>
<% List<Exercice> exercices =((List<Exercice>)request.getAttribute(Constants.KEY_EXERCICES_LIST)); %>
<div><a href="${URI}/add">add </a></div>
<table>
    <tr>
        <th>#</th>
        <th>Exercice name</th>
        <th>year</th>
        <th>status</th>
        <th>start date</th>
        <th>end date</th>
        <th>activity</th>
    </tr>
    <% for(int i = 0; i< exercices.size(); i++){
        Exercice a=exercices.get(i);
    %>

        <tr>
            <td><%= a.getId()%></td>
            <td><%= a.getTitle() %></td>
            <td><%= a.getYear()%></td>
            <td><%= a.getStartDate() %></td>
            <td><%= a.getEndDate() %></td>
            <td><%= a.getStatus().toString() %></td>
            <td><%= a.getActivity().getTitle() %></td>
            <td><a href="${URI}/edit?id=<%= a.getId()%>">View/Edit</a></td>
            <td><a href="${URI}/delete?id=<%= a.getId()%>">Delete</a></td>
        </tr>

    <% }%>


</table>
</body>
</html>
