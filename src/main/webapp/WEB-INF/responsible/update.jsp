<%--
  Created by IntelliJ IDEA.
  User: abdes
  Date: 10/11/2022
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.pole_digital_academy.Entities.Participant" %>
<%@ page import="com.example.pole_digital_academy.utils.Constants" %>
<%@ page import="com.example.pole_digital_academy.Entities.Responsible" %>
<%@ page import="com.example.pole_digital_academy.Entities.ResponsibleType" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: abdes
  Date: 21/10/2022
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Update participant</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="antialiased bg-slate-200">
<div class="max-w-lg mx-auto my-10 bg-white p-8 rounded-xl shadow shadow-slate-300">
    <h3 class="text-4xl font-medium">Update Responsible</h3>

    <% Responsible r =(Responsible) request.getAttribute(Constants.KEY_RESPONSIBLE_TO_EDIT); %>

    <form method="post" class="my-10">
        <input type="hidden" name="responsibleId" value="<%= r.getId()%>">
        <div class="flex flex-col space-y-5">
            <label for="firstname">
                <p class="w-full font-medium text-slate-700 pb-2">First Name</p>
                <input id="firstname" name="firstname" type="text" value="<%=r.getFirstName()%>" class="w-full py-2 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter your firstname">
            </label>
            <label for="lastname">
                <p class="font-medium text-slate-700 pb-2">Last Name</p>
                <input id="lastname" name="lastname" type="text" value="<%=r.getLastName()%>" class="w-full py-2 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter your lastname">
            </label>
            <label for="email">
                <p class="font-medium text-slate-700 pb-2">Email address</p>
                <input id="email" name="email" type="email" value="<%=r.getEmail()%>" class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter email address">
            </label>
            <label for="phone">
                <p class="font-medium text-slate-700 pb-2">Number Phone</p>
                <input id="phone" name="phone" type="tel" value="<%=r.getPhone()%>" class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter your phone">
            </label>
            <label for="domaine">
                <p class="font-medium text-slate-700 pb-2">Domaine</p>
                <input id="domaine" name="domaine" type="text" value="<%=r.getDomaine()%>" class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter your domaine">
            </label>

            <% List<ResponsibleType> resTypes = ((List<ResponsibleType>)request.getAttribute(Constants.KEY_RESPONSIBLE_TYPES));  %>
            <label for="responsableType" class="font-medium text-slate-700 pb-2">Responsible Type</label>
            <select  name=" responsableType" title="responsable type" id="responsableType"  class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow">
                <% for(ResponsibleType Types : resTypes){ %>
                <option <% if(Objects.equals(r.getRes_type().getName(), Types.getName())){ %> <%="selected" %> <%}%> value="<%=Types.getId()%>"><%= Types.getName()%></option><%}%>
            </select>

            <button class="w-25 py-3 font-medium text-white bg-indigo-600 hover:bg-indigo-500 rounded-lg border-indigo-500 hover:shadow inline-flex space-x-2 items-center justify-center">
                <input type="submit" name="submit" value="update">
            </button>

        </div>
    </form>
</div>
</body>
</html>


</html>
