<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<br>

<h2>DEVELOPER DETAILS : </h2>
<hr><br>
<table border="1" cellpadding="10" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Password</th>
        <th>Phone</th>
        <th>Salary</th>
        <th>Role</th>
    </tr>

<%
ResultSet resultSet = (ResultSet) request.getAttribute("resultSet");

while(resultSet.next()){
%>
    <tr>
        <td><%= resultSet.getInt("employee_id") %></td>
        <td><%= resultSet.getString("employee_name") %></td>
        <td><%= resultSet.getString("employee_email") %></td>
        <td><%= resultSet.getString("employee_password") %></td>
        <td><%= resultSet.getLong("employee_phonenumber") %></td>
        <td><%= resultSet.getDouble("employee_salary") %></td>
        <td><%= resultSet.getString("employee_role") %></td>
    </tr>
<%
}
%>
</table>
</body>
</html>