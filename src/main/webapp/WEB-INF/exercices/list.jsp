<%@ page import="com.example.pole_digital_academy.utils.Constants" %>
<%@ page import="com.example.pole_digital_academy.Entities.Exercice" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../includes.jsp"%>
<html>
<head>
    <title>activities list</title>
    <script src="https://cdn.tailwindcss.com"></script>
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
<div class="w-full h-full">
    <div class="flex flex-no-wrap">
        <%@ include file="../layout/components/sidebar.jsp"%>
        <div class="w-full h-full rounded ">
            <div class="bg-white p-8 rounded-md w-full">
                <div class=" flex items-center justify-between pb-6">
                    <div>
                        <% String msg=request.getParameter("message");
                            if(msg!=null){
                                out.print("<p>"+msg+"</p>");
                            };%>
                        <c:if test="${!empty exercisesForActivity}">
                            <c:out value="Exercises for activity \"${exercisesForActivity}\""/>
                        </c:if>
                        <h1 class="font-bold">Exercices list</h1>
                        <% List<Exercice> exercices =((List<Exercice>)request.getAttribute(Constants.KEY_EXERCICES_LIST)); %>
                    </div>
                    <div class="flex items-center justify-between">
                        <div class="lg:ml-40 ml-10 space-x-8">
                            <a href="${URI}/add" class="bg-indigo-600 px-4 py-2 rounded-md text-white font-semibold tracking-wide cursor-pointer">Add Excercice</a>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="-mx-4 sm:-mx-8 px-4 sm:px-8 py-4 overflow-x-auto">
                        <div class="inline-block min-w-full shadow rounded-lg overflow-hidden">
                            <table class="min-w-full leading-normal overflow-x-auto">
                                <thead>
                                <tr>
                                    <th
                                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                        Exercice name
                                    </th>
                                    <th
                                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                        year
                                    </th>
                                    <th
                                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                        status
                                    </th>
                                    <th
                                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                        start date
                                    </th>

                                    <th
                                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                        end date
                                    </th>
                                    <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                        activity
                                    </th>
                                    <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                        Operations ...
                                    </th>
                                </tr>
                                </thead>

                                <tbody>
                                <% for(int i = 0; i< exercices.size(); i++){
                                    Exercice a=exercices.get(i);
                                %>
                                <tr>
                                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-sm">
                                        <div class="flex items-center">
                                            <div class="ml-3">
                                                <p class="text-gray-900 whitespace-no-wrap">
                                                    <%= a.getTitle()%>
                                                </p>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-sm">
                                        <p class="text-gray-900 whitespace-no-wrap">
                                            <%= a.getYear()%>
                                        </p>
                                    </td>
                                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-sm">
                                        <p class="text-gray-900 whitespace-no-wrap">
                                            <%= a.getStatus().toString()%>
                                        </p>
                                    </td>
                                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-sm">
                                        <p class="text-gray-900 whitespace-no-wrap">
                                            <%= a.getStartDate()%>
                                        </p>
                                    </td>
                                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-sm">
                                        <p class="text-gray-900 whitespace-no-wrap">
                                            <%= a.getEndDate()%>
                                        </p>
                                    </td>
                                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-sm">
                                        <p class="text-gray-900 whitespace-no-wrap">
                                            <%= a.getActivity().getTitle()%>
                                        </p>
                                    </td>

                                    <td>
                                        <a href="${URI}/edit?id=<%= a.getId()%>">View/Edit</a>
                                        <a href="${URI}/delete?id=<%= a.getId()%>">Delete</a>
                                    </td>
                                </tr>
                                <% }%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>