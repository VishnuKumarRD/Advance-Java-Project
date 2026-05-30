<%@page import="com.etm.entity.Employee"%>
<%@page import="java.util.List"%>
<%@page import="com.etm.entity.Task"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>Task Details</h3>
<hr>
<br>
<%
Employee developer=(Employee)request.getAttribute("developer");
List<Task> taskList=developer.getTaskList();
%>
<table border="1" cellpadding="10">

<tr>
	<th>TASK ID</th>
	<th>TASK NAME</th>
	<th>DURATION</th>
	<th>STATUS</th>
</tr>

<%
if(taskList!=null){
	for(Task task:taskList)
		{
%>
<tr>
<td><%=task.getTaskId()%></td>
<td><%=task.getTaskName() %></td>
<td><%=task.getDuration() %></td>
<td><%=task.getStatus() %></td>
</tr>
<%		}
}
%>
</table>

</body>
</html>