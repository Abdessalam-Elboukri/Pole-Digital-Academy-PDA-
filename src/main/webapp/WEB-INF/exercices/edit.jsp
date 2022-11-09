<%@ page import="java.util.stream.Stream" %>
<%@ page import="com.example.pole_digital_academy.Entities.Exercice" %>
<%@ page import="com.example.pole_digital_academy.Entities.Responsible" %>
<%@ page import="com.example.pole_digital_academy.utils.Constants" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="javax.swing.*" %>
<%@ page import="com.example.pole_digital_academy.Entities.Activity" %><%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 03/11/2022
  Time: 09:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit an exercice</title>
</head>
<body>
<%@ include file="../layout/header.jsp"%>
<ul>
    <%
        List<String> errors=(List<String>) request.getAttribute(Constants.KEY_VALIDATION_ERRORS);
        if(errors!=null)
            out.print(errors.stream().map(e->"<li>"+e+"</li>").collect(Collectors.joining("")));
    %>
</ul>

<c:set var="exercice" value="${requestScope.get(Constants.KEY_EXERCICE_TO_EDIT)}"/>
<c:choose >
        <c:when test="${ exercice==null}">
            <c:out value="No exercice selected"></c:out>
        </c:when>
    <c:otherwise>
        <h2>Edit Exercice : "${exercice.title}" </h2>
        <form action="" method="post" class="w-2/3 items-center">
            <input type="hidden" name="${Exercice.KEY_ID}" value="${exercice.id}">
            <div class="flex flex-row gap-1 items center">
                <label for="${Exercice.KEY_TITLE}" class="w-1/2">Exercice name:</label>
                <input type="text" class="w-1/2" name="${Exercice.KEY_TITLE}" id="${Exercice.KEY_TITLE}" placeholder="Exercice title"
                       value="${exercice.title}"
                >
            </div>
            <div class="flex flex-row gap-1 items center">
                <label for="${Exercice.KEY_START_DATE}" class="w-1/2">Start date:</label>
                <input type="date" class="w-1/2" name="${Exercice.KEY_START_DATE}" id="${Exercice.KEY_START_DATE}"
                       value="${exercice.startDate}"
                >
            </div>
            <div class="flex flex-row gap-1 items center">
                <label for="${Exercice.KEY_END_DATE}" class="w-1/2">End date:</label>
                <input type="date" class="w-1/2" name="${Exercice.KEY_END_DATE}" id="${Exercice.KEY_END_DATE}"
                       value="${exercice.endDate}"
                >
            </div>
            <div class="flex flex-row gap-1 items center">
                <label for="${Exercice.KEY_YEAR}" class="w-1/2">Year:</label>
                <input type="number" min="1900" max="2099" step="1" class="w-1/2" name="${Exercice.KEY_YEAR}" id="${Exercice.KEY_YEAR}"
                       value="${exercice.year}"
                >
            </div>

            <div class="flex flex-row gap-1 items center">
                <label for="${Exercice.KEY_STATUS}" class="w-1/2">Exercice status:</label>
                <select class="w-1/2" name="${Exercice.KEY_STATUS}" title="Exercice status" id="${Exercice.KEY_STATUS}"  data-selected-value="${exercice.status.ordinal()}">
                    <%= Stream.of(Exercice.ExerciceStatusEnum.values()).map(t->"<option value=\""+t.ordinal()+"\">"+t.toString()+"</option>").collect(Collectors.joining("")) %>
                </select>
            </div>

            <div class="flex flex-row gap-1 items center">
                <label for="${Exercice.KEY_ACTIVITY_ID}" class="w-1/2">Activité mère:</label>
                <select class="w-1/2" name="${Exercice.KEY_ACTIVITY_ID}" title="Exercice type" id="${Exercice.KEY_ACTIVITY_ID}" data-selected-value="${exercice.activity.id}">
                    <%= ((List<Activity>)request.getAttribute(Constants.KEY_ACTIVITIES_LIST)).stream().map(r->"<option value=\""+r.getId()+"\">"+r.getTitle()+"</option>").collect(Collectors.joining("")) %>
                </select>
            </div>
            <input type="submit" value="send">

        </form>
    </c:otherwise>
</c:choose>
<script>
    document.querySelectorAll("select[data-selected-value]").forEach(e=>{
        let selectValue=e.dataset.selectedValue;
        e.querySelectorAll("option").forEach(o=>{
            if(o.value===selectValue)
                o.setAttribute("selected","selected");
        })

    })</script>


</body>
</html>
