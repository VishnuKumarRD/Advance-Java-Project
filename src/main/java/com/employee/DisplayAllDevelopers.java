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

@WebServlet("/viewDeveloper")
public class DisplayAllDevelopers extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String url="jdbc:postgresql://localhost:5432/employee-management?user=postgres&password=root";
			Class.forName("org.postgresql.Driver");
			Connection connection=DriverManager.getConnection(url);
			String query="SELECT * FROM employee WHERE employee_role='developer';";
			PreparedStatement preparedStatement=connection.prepareStatement(query);


			ResultSet resultSet=preparedStatement.executeQuery();
			PrintWriter printWriter=resp.getWriter();

			req.setAttribute("resultSet",resultSet);
			RequestDispatcher requestDispatcher=req.getRequestDispatcher("displayDeveloper.jsp");
			requestDispatcher.forward(req, resp);


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
