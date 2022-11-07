<%--
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
    <title>Add participant</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="antialiased bg-slate-200">
<div class="max-w-lg mx-auto my-10 bg-white p-8 rounded-xl shadow shadow-slate-300">
    <h1 class="text-4xl font-medium">Add New Participant</h1>
    <form method="post" class="my-10">
        <div class="flex flex-col space-y-5">
            <label for="firstname">
                <p class="font-medium text-slate-700 pb-2">First Name</p>
                <input id="firstname" name="firstname" type="text" class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter email address">
            </label>
            <label for="lastname">
                <p class="font-medium text-slate-700 pb-2">Last Name</p>
                <input id="lastname" name="lastname" type="text" class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter your password">
            </label>
            <label for="email">
                <p class="font-medium text-slate-700 pb-2">Email address</p>
                <input id="email" name="email" type="email" class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter email address">
            </label>
            <label for="phone">
                <p class="font-medium text-slate-700 pb-2">Number Phone</p>
                <input id="phone" name="phone" type="tel" class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter email address">
            </label>
            <label for="domaine">
                <p class="font-medium text-slate-700 pb-2">Domaine</p>
                <input id="domaine" name="domaine" type="text" class="w-full py-3 border border-slate-200 rounded-lg px-3 focus:outline-none focus:border-slate-500 hover:shadow" placeholder="Enter email address">
            </label>

            <button class="w-25 py-3 font-medium text-white bg-indigo-600 hover:bg-indigo-500 rounded-lg border-indigo-500 hover:shadow inline-flex space-x-2 items-center justify-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1"></path>
                </svg>
                <input type="submit" name="submit" value="Add">
            </button>

        </div>
    </form>
</div>
</body>
</html>

