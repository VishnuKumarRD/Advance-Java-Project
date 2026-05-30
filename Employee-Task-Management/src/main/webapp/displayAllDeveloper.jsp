<%@page import="com.etm.entity.Task"%>
<%@page import="com.etm.entity.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>EMPLOYEE & TASK DETAILS : </h2>

<%
List<Employee> employeeList =
	(List<Employee>)request.getAttribute("employeeList");

for(Employee employee : employeeList){
%>

<hr>

<h2>Employee Details</h2>

<p>
ID : <%= employee.getEmployeeId() %>
</p>

<p>
NAME : <%= employee.getEmployeeName() %>
</p>

<p>
EMAIL ID : <%= employee.getEmail() %>
</p>

<p>
ROLE : <%= employee.getRole() %>
</p>

<h3>Task Details</h3>

<table border="1" cellpadding="10">

<tr>
	<th>TASK ID</th>
	<th>TASK NAME</th>
	<th>DURATION</th>
	<th>STATUS</th>
</tr>

<%
List<Task> taskList = employee.getTaskList();

if(taskList != null){

	for(Task task : taskList){
%>

<tr>
	<td><%= task.getTaskId() %></td>
	<td><%= task.getTaskName() %></td>
	<td><%= task.getDuration() %></td>
	<td><%= task.getStatus() %></td>
</tr>

<%
	}
}
%>

</table>

<%
}
%>

</body>
</html>