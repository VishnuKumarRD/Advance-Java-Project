package com.etm.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etm.dao.EmployeeDao;
import com.etm.entity.Employee;

@WebServlet("/login")
public class LoginEmployee extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//read data from form
				String employeeEmail = req.getParameter("email");
				String employeePassword = req.getParameter("password");
				
				//check employee is present or not in table based on email and password
				EmployeeDao employeeDao=new EmployeeDao();
				Employee dbEmployee = employeeDao.findEmployeeByEmailAndPassword(employeeEmail, employeePassword);
				resp.setContentType("text/html");
				PrintWriter printWriter=resp.getWriter();
				if(dbEmployee!=null) {
					//login success
					printWriter.print("<h2>Login Success</h2>");
					if(dbEmployee.getRole().equals("manager")) {
						RequestDispatcher requestDispatcher=req.getRequestDispatcher("manager.html");
						requestDispatcher.include(req, resp);
					}
					else if(dbEmployee.getRole().equals("developer")) {
						HttpSession session=req.getSession();
						session.setAttribute("developer",dbEmployee);
						
						RequestDispatcher requestDispatcher=req.getRequestDispatcher("developer.html");
						requestDispatcher.include(req, resp);
					}
				}
				else {
					//login failure
					printWriter.print("<h2>Login Failure</h2>");
					RequestDispatcher requestDispatcher=req.getRequestDispatcher("login.html");
					requestDispatcher.include(req, resp);
				}
	}
}
