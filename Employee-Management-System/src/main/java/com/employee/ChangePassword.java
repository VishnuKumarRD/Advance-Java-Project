package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/changePassword")
public class ChangePassword extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String email=req.getParameter("email");
	String newPassword=req.getParameter("newPassword");
	resp.setContentType("text/html");
	try {
		String url="jdbc:postgresql://localhost:5432/employee-management?user=postgres&password=root";
		Class.forName("org.postgresql.Driver");
		Connection connection=DriverManager.getConnection(url);
		String query="SELECT * FROM employee WHERE employee_email=?;";
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1,email);
		ResultSet resultSet=preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			String fetchEmail=resultSet.getString("employee_email");
			PrintWriter printWriter=resp.getWriter();
			if(fetchEmail.equals(email)) {
				String updateQuery="UPDATE employee SET employee_password=? WHERE employee_email=?";
				PreparedStatement updatePreparedStatement=connection.prepareStatement(updateQuery);
				updatePreparedStatement.setString(1, newPassword);
				updatePreparedStatement.setString(2,email);
				int rows=updatePreparedStatement.executeUpdate();
				if(rows!=0) {
					printWriter.print("<h2>PASSWORD UPDATED SUCCESSFULLY!</h2>");
					RequestDispatcher requestDispatcher=req.getRequestDispatcher("login.html");
					requestDispatcher.include(req, resp);
				}
				else {
					printWriter.print("<h2>PASSWORD UPDATION ID FAILED!</h2>");
				}
			}
			else {
				printWriter.print("<h2>INVALID EMAIL! / REGISTER FIRST!</h2>");
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("takeEmail.html");
				requestDispatcher.include(req, resp);
			}
		}
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
