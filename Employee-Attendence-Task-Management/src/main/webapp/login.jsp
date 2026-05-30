<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>-LOGIN PAGE-</h2>
<form action="login">
<label for="emailId">ENTER YOUR EMAIL : </label>
<input type="text" id="emailId" placeholder="enter email id" name="email"><br><br>
<label for="pwd">ENTER PASSWORD : </label>
<input type="text" id="pwd" placeholder="enter password" name="password"><br><br>
<input type="submit" value="LOGIN"><br><br>
<h3><a href="changePassword.jsp">Forgot Password ?</a>
<a href="register.jsp">New User ?</a>
</h3>
 
</form>
</body>
</html>