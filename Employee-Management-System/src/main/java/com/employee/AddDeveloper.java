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

@WebServlet("/addDeveloper")
public class AddDeveloper extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int developerId=Integer.parseInt(req.getParameter("developer-id"));
		String developerName=req.getParameter("developer-name");
		String developerEmail=req.getParameter("developer-email");
		String developerPassword=req.getParameter("developer-password");
		String developerPhoneNumber=req.getParameter("developer-phonenumber");
		double developerSalary=Double.parseDouble(req.getParameter("developer-salary"));
		
		//JDBC
				try {
					String url="jdbc:postgresql://localhost:5432/employee-management?user=postgres&password=root";
					Class.forName("org.postgresql.Driver");
					Connection connection=DriverManager.getConnection(url);
					String query="INSERT INTO employee VALUES(?,?,?,?,?,?,?);";
					PreparedStatement preparedStatement=connection.prepareStatement(query);
					preparedStatement.setInt(1, developerId);
					preparedStatement.setString(2, developerName);
					preparedStatement.setString(3, developerEmail);
					preparedStatement.setString(4, "developer");
					preparedStatement.setString(5, developerPhoneNumber);
					preparedStatement.setString(6, developerPassword);
					preparedStatement.setDouble(7, developerSalary);
					
					int rows=preparedStatement.executeUpdate();
					PrintWriter printWriter=resp.getWriter();
					if(rows!=0) {
						printWriter.print("<h1>DEVELOPER DETAILS REGISTERED SUCCESSFULLY!</h1>"); 
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
