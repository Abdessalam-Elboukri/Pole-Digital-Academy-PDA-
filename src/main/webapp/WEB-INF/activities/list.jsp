<%@ page import="com.example.pole_digital_academy.utils.Constants" %>
<%@ page import="com.example.pole_digital_academy.Entities.Activity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../includes.jsp"%>
<html>
<head>
    <title>activities list</title>

</head>
<body>
<%@ include file="../layout/header.jsp"%>
<% String msg=request.getParameter("message");
    if(msg!=null){
    out.print("<p>"+msg+"</p>");
};%>
<div class="flex mx-3 flex-col items-center">
<h1 class="font-bold self-start">activities list</h1>
<% List<Activity> activities=((List<Activity>)request.getAttribute(Constants.KEY_ACTIVITIES_LIST)); %>
<div class="flex justify-start self-start mt-2">
    <a  href="${URI}/add" class="inline-block px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out">Add</a>
</div>

<div class="flex flex-col">
    <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
        <div class="py-2 inline-block min-w-full sm:px-6 lg:px-8">
            <div class="overflow-hidden">
<table class="min-h-full ">
    <thead class="border-b">

    <tr>
        <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">#</th>
        <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">activity name</th>
        <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">description</th>
        <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">start date</th>
        <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">end date</th>
        <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">status</th>
        <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">type</th>
        <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">responsible</th>

    </tr>
    </thead>
    <tbody>

    <% for(int i=0;i<activities.size();i++){
        Activity a=activities.get(i);
    %>
    <tr class="border-b bg-white">
            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"><%= a.getId()%></td>
            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap"><%= a.getTitle() %></td>
            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap"><%= a.getDescription() %></td>
            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap"><%= a.getStartDate() %></td>
            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap"><%= a.getEndDate() %></td>
            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap"><%= a.getStatus().toString() %></td>
            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap"><%= a.getActivityType().toString() %></td>
            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap"><%= a.getResponsible().getFirstName()+" "+ a.getResponsible().getLastName()%></td>

            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
                <div class="flex space-x-2 justify-center">

                    <a  class="inline-block px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out" href="/exercices?activity_id=<%= a.getId()%>">exercices (<%= a.getExercices().size()%>)</a>
                    <a  class="inline-block px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out" href="${URI}/edit?id=<%= a.getId()%>">View/Edit</a>
                    <a  class="inline-block px-6 py-2.5 bg-red-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-red-700 hover:shadow-lg focus:bg-red-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-red-800 active:shadow-lg transition duration-150 ease-in-out" href="${URI}/delete?id=<%= a.getId()%>">Delete</a>
                </div>
            <td/>
        </tr>

    <% }%>

    </tbody>
</table>
<div/>
</body>
</html>
