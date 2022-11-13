<%@ page import="com.example.pole_digital_academy.Entities.Participant" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.pole_digital_academy.utils.Constants" %>
<%@ page import="com.example.pole_digital_academy.Entities.Activity" %><%--
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
</head>
<body>
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
                    <input type="checkbox" value="<%=p.getId()%>" id="<%= p.getFirstName()+p.getId() %>" name="participant">
                </div>
            <%}%>
            <input type="submit" value="Done" name="submit">
        </form>
    </div>
</body>
</html>
