<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="createTask">
<h2>-TASK CREATION-</h2>
<br>
<hr>
<br>
<label for="name">ENTER TASK NAME : </label>
<input type="text" id="name" placeholder="enter task name" name="taskName"><br><br>

<label for="duration">ENTER TASK DURATION : </label>
<input type="text" id="duration" placeholder="enter task duration" name="duration"><br><br>

<input type="submit" value="CREATE">

</form>

</body>
</html>