

<%--
  Created by IntelliJ IDEA.
  User: abdes
  Date: 21/10/2022
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.stream.Stream" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="com.example.pole_digital_academy.utils.Constants" %>
<%@ page import="com.example.pole_digital_academy.Entities.ResponsibleType" %>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add Responsible</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="antialiased bg-slate-200">
<div class="max-w-lg mx-auto my-10 bg-white p-8 rounded-xl shadow shadow-slate-300">
    <h1 class="text-4xl font-medium">Add New Responsible</h1>
    <form method="post" class="my-10">
        <div class="flex flex-col space-y-5">
            <label for="firstname">
                <p class="font-medium text-slate-700 pb-2">First Name</p>
                <input id="firstname" name="firstname" type="text" class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter your firstname">
            </label>
            <label for="lastname">
                <p class="font-medium text-slate-700 pb-2">Last Name</p>
                <input id="lastname" name="lastname" type="text" class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter your lastname">
            </label>
            <label for="email">
                <p class="font-medium text-slate-700 pb-2">Email address</p>
                <input id="email" name="email" type="email" class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter email address">
            </label>
            <label for="phone">
                <p class="font-medium text-slate-700 pb-2">Number Phone</p>
                <input id="phone" name="phone" type="tel" class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter your phone">
            </label>
            <label for="domaine">
                <p class="font-medium text-slate-700 pb-2">Domaine</p>
                <input id="domaine" name="domaine" type="text" class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter your domaine">
            </label>

         <label for="responsableType" class="font-medium text-slate-700 pb-2">Responsible Type</label>
         <select  name="responsableType" title="responsable type" id="responsableType"  class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow">
             <%= ((List<ResponsibleType>)request.getAttribute(Constants.KEY_RESPONSIBLE_TYPES)).stream().map(resType->"<option value=\""+resType.getId()+"\">"+resType.getName()+"</option>").collect(Collectors.joining("")) %>
         </select>

 <button class="w-25 py-3 font-medium text-white bg-indigo-600 hover:bg-indigo-500 rounded-lg border-indigo-500 hover:shadow inline-flex space-x-2 items-center justify-center">
     <input type="submit" name="submit" value="Add Responsible">
 </button>

</div>
</form>
</div>
</body>
</html>

