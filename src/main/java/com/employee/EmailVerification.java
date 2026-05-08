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

@WebServlet("/verifyEmail")
public class EmailVerification extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String verifyEmail=req.getParameter("verifyEmail");
		
		try {
			String url="jdbc:postgresql://localhost:5432/employee-management?user=postgres&password=root";
			Class.forName("org.postgresql.Driver");
			Connection connection=DriverManager.getConnection(url);
			String query="SELECT * FROM employee WHERE employee_email=?;";
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1,verifyEmail);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				String fetchEmail=resultSet.getString("employee_email");
				PrintWriter printWriter=resp.getWriter();
				if(fetchEmail.equals(verifyEmail)) {
					RequestDispatcher requestDispatcher=req.getRequestDispatcher("takeNewPassword.html");
					requestDispatcher.forward(req, resp);
				}
				else {
					printWriter.print("<h2>INVALID EMAIL!</h2>");
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
