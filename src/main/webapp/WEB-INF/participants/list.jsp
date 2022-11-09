
<%@ page import="com.example.pole_digital_academy.utils.Constants" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.pole_digital_academy.Entities.User" %>
<%@ page import="com.example.pole_digital_academy.Entities.Participant" %>
<%@ include file="../includes.jsp"%>

<%--
  Created by IntelliJ IDEA.
  User: abdes
  Date: 09/11/2022
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Participants</title>
    <style>
        p,span,a{
            font-size: 90%;
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
                        <h2 class="text-gray-600 font-semibold">All Partcipants</h2>
                        <span class="text-xs">PDA academy</span>
                    </div>
                    <div class="flex items-center justify-between">
                        <div class="flex bg-gray-50 items-center p-2 rounded-md">
                            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 text-gray-400" viewBox="0 0 20 20"
                                 fill="currentColor">
                                <path fill-rule="evenodd"
                                      d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
                                      clip-rule="evenodd" />
                            </svg>
                            <input class="bg-gray-50 outline-none ml-1 block " type="text" name="" id="" placeholder="search...">
                        </div>
                        <div class="lg:ml-40 ml-10 space-x-8">
                            <button class="bg-indigo-600 px-4 py-2 rounded-md text-white font-semibold tracking-wide cursor-pointer">Create New Resp</button>
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
                                        Full Name
                                    </th>
                                    <th
                                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                        Email
                                    </th>
                                    <th
                                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                        Phone
                                    </th>
                                    <th
                                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                        Domaine
                                    </th>

                                    <th
                                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                        Status
                                    </th>
                                    <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                        Opera...
                                    </th>
                                </tr>
                                </thead>

                                <tbody>
                                <% List<Participant> participantsList = ((List<Participant>) request.getAttribute(Constants.KEY_PARTICIPANTS_LIST)); %>

                                <% for(int i=0;i<participantsList.size();i++){
                                    Participant p=participantsList.get(i);
                                %>
                                <tr>
                                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-sm">
                                        <div class="flex items-center">
                                            <div class="ml-3">
                                                <p class="text-gray-900 whitespace-no-wrap">
                                                    <%= p.getFirstName() + " " + p.getLastName() %>
                                                </p>
                                            </div>
                                        </div>
                                    </td>
                                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-sm">
                                        <p class="text-gray-900 whitespace-no-wrap">
                                            <%= p.getEmail()%>
                                        </p>
                                    </td>
                                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-sm">
                                        <p class="text-gray-900 whitespace-no-wrap">
                                            <%= p.getPhone()%>
                                        </p>
                                    </td>
                                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-sm">
                                        <p class="text-gray-900 whitespace-no-wrap">
                                            <%= p.getDomaine()%>
                                        </p>
                                    </td>
                                    <td class="px-5 py-3 border-b border-gray-200 bg-white ">
                                        <% if(p.getUserStatus()== User.UserStatusEnum.ACTIVE){ %>
                                        <span class="relative inline-block px-3 py-1 font-semibold text-green-900 leading-tight">
                                          <span aria-hidden class="absolute inset-0 bg-green-200 opacity-50 rounded-full"></span>
                                        <span class="relative"><%= p.getUserStatus()%></span>
                                        </span>
                                        <% }else{ %>
                                        <span class="relative inline-block px-3 py-1 font-semibold text-red-900 leading-tight">
                                          <span aria-hidden class="absolute inset-0 bg-red-200 opacity-50 rounded-full"></span>
                                        <span class="relative"><%= p.getUserStatus()%></span>
                                        </span>
                                        <%}%>
                                    </td>
                                    <td><a href="${URI}/edit?id=<%= p.getId()%>">edit</a></td>
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
