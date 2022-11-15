<%@ page import="com.example.pole_digital_academy.Entities.Activity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.pole_digital_academy.utils.Constants" %><%--
  Created by IntelliJ IDEA.
  User: abdes
  Date: 11/11/2022
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Participation</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body>
<% List<Activity> activities = ((List<Activity>) request.getAttribute(Constants.KEY_ACTIVITIES_LIST)); %>
<div class="w-full h-full">
    <div class="flex flex-no-wrap">
        <%@ include file="../layout/components/sidebar.jsp"%>
        <div class="flex flex-wrap mx-auto p-4 gap-2 md:gap-4 overflow-y-auto">
            <% for(Activity activity : activities) {%>
            <div style="width: 220px" class="flex justify-center h-fit">
                <div class="rounded-lg shadow-lg bg-white max-w-sm">
                    <a href="${URI}/manage?id=<%= activity.getId()%>">
                        <img class="rounded-t-lg" src="https://mdbootstrap.com/img/new/standard/nature/184.jpg" alt=""/>
                    </a>
                    <div class="p-6">
                        <h6 class="text-gray-900 text-md font-medium mb-2"><%=activity.getTitle()%></h6>
                        <p class="text-gray-900 text-sm font-medium mb-2"><span class="me-3">Start date : <%= activity.getStartDate()%></span><span>End date : <%= activity.getEndDate() %></span></p>
                        <p class="text-gray-700 text-sm mb-4">
                            <%=activity.getDescription()%>
                        </p>
                        <button type="button" class=" inline-block px-6 py-2.5 bg-purple-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out">
                            <a href="${URI}/manage?id=<%= activity.getId()%>">Manage</a>
                        </button>
                    </div>
                </div>
            </div>
            <%}%>
        </div>
    </div>
</div>
</body>
</html>