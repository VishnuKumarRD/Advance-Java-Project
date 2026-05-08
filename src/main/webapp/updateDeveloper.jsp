<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String updateOption=request.getParameter("update-option");
session.setAttribute("updateOption",updateOption);
if(updateOption.equals("NAME")){
%>
<form action="updateDeveloper">
<label for="name">ENTER NEW NAME : </label>
<input type="text" id="name" name="newName"><br><br>
<input type="submit" value="UPDATE">
</form>
<%}
else if(updateOption.equals("EMAIL")){
%>
<form action="updateDeveloper">
<label for="email">ENTER NEW EMAIL : </label>
<input type="text" id="email" name="newEmail"><br><br>
<input type="submit" value="UPDATE">
</form>
<%}
else if(updateOption.equals("PHONENUMBER")){
%>
<form action="updateDeveloper">
<label for="phno">ENTER NEW PHONE NUMBER : </label>
<input type="text" id="phno" name="newPhoneNumber"><br><br>
<input type="submit" value="UPDATE">
</form>
<%} 
%>


</body>
</html>