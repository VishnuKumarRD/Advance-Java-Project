package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/updateDeveloper")
public class UpdateDeveloper extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=req.getSession();
		int developerId=(Integer)session.getAttribute("developer_id");
		String updateOption=(String)session.getAttribute("updateOption");
		
		try {
			String url="jdbc:postgresql://localhost:5432/employee-management?user=postgres&password=root";
			Class.forName("org.postgresql.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=null;
			if(updateOption.equals("NAME")) {
				String newName=req.getParameter("newName");
				String query="UPDATE employee SET employee_name=? WHERE employee_id=?;";
				preparedStatement=connection.prepareStatement(query);
				preparedStatement.setString(1,newName);
				preparedStatement.setInt(2, developerId);
			}
			else if(updateOption.equals("EMAIL")) {
				String newEmail=req.getParameter("newEmail");
				String query="UPDATE employee SET employee_email=? WHERE employee_id=?;";
				preparedStatement=connection.prepareStatement(query);
				preparedStatement.setString(1,newEmail);
				preparedStatement.setInt(2, developerId);
			}
			else if(updateOption.equals("PHONENUMBER")) {
				String newPhoneNumber=req.getParameter("newPhoneNumber");
				String query="UPDATE employee SET employee_PhoneNumber=? WHERE employee_id=?;";
				preparedStatement=connection.prepareStatement(query);
				preparedStatement.setString(1,newPhoneNumber);
				preparedStatement.setInt(2, developerId);
			}
		
			preparedStatement.executeUpdate();
			connection.close();
			PrintWriter printWriter=resp.getWriter();
			printWriter.print("<h1>UPDATION DONE!</h1>");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
