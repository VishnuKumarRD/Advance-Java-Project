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

@WebServlet("/register")
public class RegisterManager extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int managerId=Integer.parseInt(req.getParameter("manager-id"));
		String managerName=req.getParameter("manager-name");
		String managerEmail=req.getParameter("manager-email");
		String managerPassword=req.getParameter("manager-password");
		String managerPhoneNumber=req.getParameter("manager-phonenumber");
		double managerSalary=Double.parseDouble(req.getParameter("manager-salary"));
		
		//JDBC
		try {
			String url="jdbc:postgresql://localhost:5432/employee-management?user=postgres&password=root";
			Class.forName("org.postgresql.Driver");
			Connection connection=DriverManager.getConnection(url);
			String query="INSERT INTO employee VALUES(?,?,?,?,?,?,?);";
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, managerId);
			preparedStatement.setString(2, managerName);
			preparedStatement.setString(3, managerEmail);
			preparedStatement.setString(4, "manager");
			preparedStatement.setString(5, managerPhoneNumber);
			preparedStatement.setString(6, managerPassword);
			preparedStatement.setDouble(7, managerSalary);
			
			int rows=preparedStatement.executeUpdate();
			PrintWriter printWriter=resp.getWriter();
			if(rows!=0) {
				printWriter.print("<h1>MANAGER DETAILS REGISTERED SUCCESSFULLY!</h1>"); 
			}
			else {
				printWriter.print("<h1>REGISTRATION FAILED</h1>");
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
