<%@ page import="com.example.pole_digital_academy.Entities.Participant" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.pole_digital_academy.utils.Constants" %>
<%@ page import="com.example.pole_digital_academy.Entities.Activity" %>
<%@ page import="com.example.pole_digital_academy.Entities.Participation" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: abdes
  Date: 12/11/2022
  Time: 00:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

    <% List<Participant> participants_out =((List<Participant>) request.getAttribute(Constants.KEY_PARTICIPANTS_OUT_LIST));  %>
    <% Activity a = ((Activity) request.getAttribute(Constants.KEY_ACTIVITY_TO_MANAGE)); %>

<div class="w-full h-full">
    <div class="flex flex-no-wrap">
        <%@ include file="../layout/components/sidebar.jsp"%>
        <div class="w-full h-full rounded ">
            <div class="bg-white p-8 rounded-md w-full">
                <div class=" flex items-center justify-between pb-6">
                    <div>
                        <h2 class="text-gray-600 font-semibold">Manage Participation</h2>
                        <span class="text-xs">PDA academy</span>
                    </div>
                    <div class="flex items-center justify-between">
                        <div class="flex bg-gray-50 gap-2 items-center text-left text-xs font-semibold text-gray-600 uppercase tracking-wider p-2 rounded-md">
                            <div class="p-1">
                                <p class="">TITLE : <%=a.getTitle()%></p>
                            </div>
                            <div class="p-1">
                                <p class="">DESCRIPTION : <%=a.getDescription()%></p>
                            </div>
                            <div class="p-1">
                                <p class="">START DATE : <%=a.getStartDate()%></p>
                            </div>
                            <div class="p-1">
                                <p class="">END DATE : <%=a.getEndDate()%></p>
                            </div>
                            <div class="p-1">
                                <p class="">RESPONSIBLE : <%=a.getResponsible().getFirstName()%></p>
                            </div>
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

                                    <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                        Operations ...
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <% for (Participant p : participants_out) {
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
                                    <td>
                                        <a href="${URI}/add_to_activity?id=<%= a.getId()%>&participant=<%=p.getId()%>" class="ml-3 text-xs inset-0 p-1 bg-purple-200 opacity-50 rounded-full">Add to activity</a>
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
        <% List<Participant> participants_in = ((List<Participant>) request.getAttribute(Constants.KEY_PARTICIPANTS_IN_LIST)); %>
        <div class="w-1/3 h-full rounded mr-3 mt-8">
            <div class=" p-1 rounded-md w-full border-b-2 border-gray-200 bg-gray-100">
                <div class="text-left text-xs font-semibold text-gray-600 uppercase tracking-wider mb-4">
                    Participant already signed this Activity
                </div>
                <% for(Participation p: a.getParticipation()){ %>
                <div class="flex items-center justify-between bg-white shadow p-1 mb-2">
                    <div>
                        <p class="text-sm"><%=p.getParticipant().getFirstName()+ " " +p.getParticipant().getLastName()%></p>
                        <p class="text-sm"><%=p.getParticipant().getPhone()%></p>

                    </div>
                    <div class="my-auto text-xs mr-2">
                           <form class="text-xs" method="post">
                                <select  name="new_status" title="participation type" id="participationType"  class="w-full  border border-slate-200 rounded-lg px-1 focus:outline-none focus:border-slate-500 hover:shadow">
                                    <% for(Participation.ParticipationTypeEnum type : Participation.ParticipationTypeEnum.values()){ %>
                                        <option<% if(Objects.equals(p.getParticipationType(),type)){ %> <%="selected" %> <%}%> id="ptype" value="<%=type.name()%>"><%=type%></option>
                                    <%}%>
                                </select>
                                <input type="hidden" name="participation_type" value="<%=p.getId()%>">
                               <input type="hidden" name="activity" value="<%=a.getId()%>">
                                <input type="submit" class="btn" name="submit" value="edit">
                            </form>

                        </div>
                    </div>
                <%}%>
            </div>
        </div>
    </div>
</div>
<%--
<% List<Participant> participants_in =((List<Participant>) request.getAttribute(Constants.KEY_PARTICIPANTS_IN_LIST));  %>
<% Activity a = ((Activity) request.getAttribute(Constants.KEY_ACTIVITY_TO_MANAGE)); %>
    <div>
        <div class="mb-4">
            <h6><%=a.getTitle()%></h6>
            <h6 class=""><%=a.getDescription()%></h6>
        </div>
        <form method="post">
            <input type="hidden" name="${Activity.KEY_TITLE}" value="<%=a.getId()%>">
            <%  for(Participant p : participants_in) { %>
                <div class="flex ">
                    <label for="<%= p.getFirstName()+p.getId() %>"><%=p.getFirstName()+ "  " + p.getLastName()%></label>
                    <input type="checkbox" class="ms-4" value="<%=p.getId()%>" id="<%= p.getFirstName()+p.getId() %>" name="participant">
                </div>
            <%}%>
            <input type="submit" value="Done" name="submit">
        </form>

    </div>--%>
</body>
</html>
