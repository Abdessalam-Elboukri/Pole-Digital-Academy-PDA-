<%@ page import="com.example.pole_digital_academy.utils.Constants" %>
<%@ page import="com.example.pole_digital_academy.Entities.Activity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Stream" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../includes.jsp"%>
<html>
<head>
    <title>activities list</title>
    <script src="https://cdn.tailwindcss.com"></script>

</head>
<body>
<div class="w-full h-full">
    <div class="flex flex-no-wrap">
        <%@ include file="../layout/components/sidebar.jsp"%>
        <div class="w-full h-full rounded ">
    <% String msg=request.getParameter("message");
        if(msg!=null){
        out.print("<p>"+msg+"</p>");
    };%>
    <h3 >${!is_searching?"No":""} Filter Applied</h3>
    <form class="mt-10 flex items-center justify-around gap-8">
        <div class="flex flex-column ">
            <label for="${Constants.KEY_ACTIVITY_SEARCH_START_DATE}">Start date : </label>
            <input type="date" class="bg-white border shadow-sm border-slate-300 placeholder-slate-400 focus:outline-none focus:border-sky-500 focus:ring-sky-500 block w-full rounded-md sm:text-sm focus:ring-1" name="${Constants.KEY_ACTIVITY_SEARCH_START_DATE}" <%= request.getParameter(Constants.KEY_ACTIVITY_SEARCH_START_DATE)!=null
            ?"value='"+request.getParameter(Constants.KEY_ACTIVITY_SEARCH_START_DATE)+"'":""
    %> id="${Constants.KEY_ACTIVITY_SEARCH_START_DATE}" >
        </div>
        <div class="flex flex-column ">
                <label for="${Constants.KEY_ACTIVITY_SEARCH_END_DATE}">End date : </label>
                <input class="bg-white border shadow-sm border-slate-300 placeholder-slate-400 focus:outline-none focus:border-sky-500 focus:ring-sky-500 block w-full rounded-md sm:text-sm focus:ring-1" <%= request.getParameter(Constants.KEY_ACTIVITY_SEARCH_END_DATE)!=null
            ?"value='"+request.getParameter(Constants.KEY_ACTIVITY_SEARCH_END_DATE)+"'":""
    %> type="date" name="${Constants.KEY_ACTIVITY_SEARCH_END_DATE}" id="${Constants.KEY_ACTIVITY_SEARCH_END_DATE}" >
            </div>
        <div class="flex flex-column mr-6" >
            <!-- change this for activity type -->
                <label for="${Constants.KEY_ACTIVITY_SEARCH_ACTIVITY_TYPE}">activity type : </label>
            <select class="bg-white border shadow-sm border-slate-300 placeholder-slate-400 focus:outline-none focus:border-sky-500 focus:ring-sky-500 block w-full rounded-md sm:text-sm focus:ring-1" name="${Constants.KEY_ACTIVITY_SEARCH_ACTIVITY_TYPE}" id="${Constants.KEY_ACTIVITY_SEARCH_ACTIVITY_TYPE}" >
                <% String activityTypeFromReq=request.getParameter(Constants.KEY_ACTIVITY_SEARCH_ACTIVITY_TYPE);%>
                <%=
                Stream.of(Activity.ActivityTypeEnum.values()).map(at->
                        String.format("<option value='%d' %s>%s</option>",at.ordinal(),at.equals(activityTypeFromReq)?"selected":"",at.toString())
                ).collect(Collectors.joining(""))%>
            </select>

        </div>
        <input type="submit" value="Search" class="px-1 py-2 bg-green-400 text-white  text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out">

    </form>
    <div class="flex mx-3 flex-col items-center">
    <h1 class="font-bold self-start">activities list: </h1>
    <% List<Activity> activities=((List<Activity>)request.getAttribute(Constants.KEY_ACTIVITIES_LIST)); %>
    <div class="flex justify-start self-start mr-4 mt-2">
        <a  href="${URI}/add" class="inline-block px-6 py-2.5 bg-blue-600 text-white font-medium text-xs leading-tight uppercase rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out">Add</a>
    </div>

    <div class="-mx-4 sm:-mx-8 px-4 sm:px-8 py-4 overflow-x-auto">
        <div class="inline-block min-w-full shadow rounded-lg overflow-hidden">
            <table class="min-w-full leading-normal overflow-x-auto">
                <thead>
                <tr>
                    <th
                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                        Name
                    </th>
                    <th
                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                        Descripion
                    </th>
                    <th
                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                        Srtart date
                    </th>

                    <th
                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                        End date
                    </th>
                    <th
                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                        Status
                    </th>
                    <th
                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                        Type
                    </th>
                    <th
                            class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                        Responsible
                    </th>
                    <th class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                        Operations ...
                    </th>
                </tr>
                </thead>

                <tbody>
                <% for(int i=0;i<activities.size();i++){
                    Activity a=activities.get(i);
                %>
                <tr>
                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-xs">
                        <p class="text-gray-900 whitespace-no-wrap ">
                            <%= a.getTitle()%>
                        </p>
                    </td>
                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-xs">
                        <p class="text-gray-900 whitespace-no-wrap">
                            <%= a.getDescription()%>
                        </p>
                    </td>
                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-xs">
                        <p class="text-gray-900 whitespace-no-wrap">
                            <%= a.getStartDate()%>
                        </p>
                    </td>
                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-xs">
                        <p class="text-gray-900 whitespace-no-wrap">
                            <%= a.getEndDate()%>
                        </p>
                    </td>
                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-xs">
                        <p class="text-gray-900 whitespace-no-wrap">
                            <%= a.getStatus()%>
                        </p>
                    </td>
                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-xs">
                        <p class="text-gray-900 whitespace-no-wrap">
                            <%= a.getActivityType()%>
                        </p>
                    </td>
                    <td class="px-5 py-3 border-b border-gray-200 bg-white text-xs">
                        <p class="text-gray-900 whitespace-no-wrap">
                            <%= a.getResponsible()==null?"Not Set":a.getResponsible().getFirstName()%>
                        </p>
                    </td>

                    <td>
                        <div class="">
                            <a  class="inline-block px-1 py-2 bg-green-400 text-white  text-xs  rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out" href="${CONTEXT_PATH}/exercices?activity_id=<%= a.getId()%>">Exrs (<%= a.getExercices().size()%>)</a>
                            <a  class="inline-block px-1 py-2 bg-blue-400 text-white  text-xs  rounded shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out" href="${URI}/edit?id=<%= a.getId()%>">View/Edit</a>
                            <a  class="inline-block px-1 py-2 bg-red-300 text-white  text-xs  rounded shadow-md hover:bg-red-700 hover:shadow-lg focus:bg-red-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-red-800 active:shadow-lg transition duration-150 ease-in-out" href="${URI}/delete?id=<%= a.getId()%>">Delete</a>
                        </div>
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
</div>
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


