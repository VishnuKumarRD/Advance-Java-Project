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

@WebServlet("/delete")
public class deleteDeveloper extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int developerId=Integer.parseInt(req.getParameter("developer-id"));
		resp.setContentType("text/html");
		try {
			String url="jdbc:postgresql://localhost:5432/employee-management?user=postgres&password=root";
			Class.forName("org.postgresql.Driver");
			Connection connection=DriverManager.getConnection(url);
			String query="DELETE FROM employee WHERE employee_id=?;";
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, developerId);
			int rows=preparedStatement.executeUpdate();
			
			PrintWriter printWriter=resp.getWriter();
			if(rows!=0) {
				printWriter.print("<h2>Deleted Data Successfully!</h2>");
			}
			else {
				printWriter.print("<h2>Data is not Found / Delete Operation is Failed!</h2>");
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
