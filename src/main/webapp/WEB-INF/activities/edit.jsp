<%@ page import="java.util.stream.Stream" %>
<%@ page import="com.example.pole_digital_academy.Entities.Activity" %>
<%@ page import="com.example.pole_digital_academy.Entities.Responsible" %>
<%@ page import="com.example.pole_digital_academy.utils.Constants" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %><%--
  Created by IntelliJ IDEA.
  User: Youcode
  Date: 03/11/2022
  Time: 09:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit an activity</title>
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

<c:set var="activity" value="${requestScope.get(Constants.KEY_ACTIVITY_TO_EDIT)}"/>
<c:choose >
        <c:when test="${ activity==null}">
            <c:out value="No activity selected"></c:out>
        </c:when>
    <c:otherwise>
        <h2>Edit activity : "${activity.title}" </h2>
        <form action="" method="post" class="w-2/3 items-center">
            <input type="hidden" name="${Activity.KEY_ID}" value="${activity.id}">
            <div class="flex flex-row gap-1 items center">
                <label for="${Activity.KEY_TITLE}" class="w-1/2">Activity name:</label>
                <input type="text" class="w-1/2" name="${Activity.KEY_TITLE}" id="${Activity.KEY_TITLE}" placeholder="activity title"
                       value="${activity.title}"
                >
            </div>
            <div class="flex flex-row gap-1 items center">
                <label for="${Activity.KEY_DESCRIPTION}" class="w-1/2">Activity description:</label>
                <input type="text" class="w-1/2" name="${Activity.KEY_DESCRIPTION}" id="${Activity.KEY_DESCRIPTION}" placeholder="activity description"
                       value="${activity.description}"
                >
            </div>
            <div class="flex flex-row gap-1 items center">
                <label for="${Activity.KEY_START_DATE}" class="w-1/2">Start date:</label>
                <input type="date" class="w-1/2" name="${Activity.KEY_START_DATE}" id="${Activity.KEY_START_DATE}"
                       value="${activity.startDate}"
                >
            </div>
            <div class="flex flex-row gap-1 items center">
                <label for="${Activity.KEY_END_DATE}" class="w-1/2">End date:</label>
                <input type="date" class="w-1/2" name="${Activity.KEY_END_DATE}" id="${Activity.KEY_END_DATE}"
                       value="${activity.endDate}"
                >
            </div>
            <div class="flex flex-row gap-1 items center">
                <label for="${Activity.KEY_ACTIVITY_TYPE}" class="w-1/2">Activity type:</label>
                <select class="w-1/2" name="${Activity.KEY_ACTIVITY_TYPE}" title="activity type" id="${Activity.KEY_ACTIVITY_TYPE}"  data-selected-value="${activity.activityType.ordinal()}">
                    <%= Stream.of(Activity.ActivityTypeEnum.values()).map(t->"<option value=\""+t.ordinal()+"\">"+t.toString()+"</option>").collect(Collectors.joining("")) %>
                </select>
            </div>
            <div class="flex flex-row gap-1 items center">
                <label for="${Activity.KEY_ACTIVITY_STATUS}" class="w-1/2">Activity status:</label>
                <select class="w-1/2" name="${Activity.KEY_ACTIVITY_STATUS}" title="activity status" id="${Activity.KEY_ACTIVITY_STATUS}"  data-selected-value="${activity.status.ordinal()}">
                    <%= Stream.of(Activity.ActivityStatusEnum.values()).map(t->"<option value=\""+t.ordinal()+"\">"+t.toString()+"</option>").collect(Collectors.joining("")) %>
                </select>
            </div>

            <div class="flex flex-row gap-1 items center">
                <label for="${Activity.KEY_RESPONSIBLE_ID}" class="w-1/2">Responsible for this activity:</label>
                <select class="w-1/2" name="${Activity.KEY_RESPONSIBLE_ID}" title="activity type" id="${Activity.KEY_RESPONSIBLE_ID}" data-selected-value="${activity.responsible.id}">
                    <%= ((List<Responsible>)request.getAttribute(Constants.KEY_RESPONSIBLES)).stream().map(r->"<option value=\""+r.getId()+"\">"+r.getLastName()+" "+r.getFirstName()+"</option>").collect(Collectors.joining("")) %>
                </select>
            </div>
            <input type="submit" value="send">

        </form>
    </c:otherwise>
</c:choose>
<script>
    //TODO finsih setting select element's value with js
    document.querySelectorAll("select[data-selected-value]").forEach(e=>{
        let selectValue=e.dataset.selectedValue;
        e.querySelectorAll("option").forEach(o=>{
            if(o.value===selectValue)
                o.setAttribute("selected","selected");
        })

    })</script>


</body>
</html>
