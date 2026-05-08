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
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginManager extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String employeeEmail=req.getParameter("manager-email");
		String employeePassword=req.getParameter("manager-password");

		resp.setContentType("text/html");
		PrintWriter printWriter=resp.getWriter();
		try {
			String url="jdbc:postgresql://localhost:5432/employee-management?user=postgres&password=root";
			Class.forName("org.postgresql.Driver");
			Connection connection=DriverManager.getConnection(url);
			String query="SELECT * FROM employee WHERE employee_email=? AND employee_password=?;";
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, employeeEmail);
			preparedStatement.setString(2, employeePassword);

			ResultSet resultSet=preparedStatement.executeQuery();

			if(resultSet.next()) {
				printWriter.print("<h2>-LOGGEDIN SUCCESSFULLY!-</h2>");
				
				String role=resultSet.getString("employee_role");
				
				if(role.equals("manager")) {
					RequestDispatcher requestDispatcher=req.getRequestDispatcher("manager.html");
					requestDispatcher.include(req, resp);
				}
				else if(role.equals("developer")) {
					HttpSession session=req.getSession();
					session.setAttribute("developer_id",resultSet.getInt("employee_id"));
					
					RequestDispatcher requestDispatcher=req.getRequestDispatcher("developer.html");
					requestDispatcher.include(req, resp);
				}
			}
			else {
				printWriter.print("<h2>-LOGIN FAILURE-</h2>");
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("login.html");
				requestDispatcher.include(req, resp);
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
