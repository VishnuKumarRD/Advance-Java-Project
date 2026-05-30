package com.etm.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etm.dao.EmployeeDao;
import com.etm.entity.Employee;

@WebServlet("/register")
public class RegisterManager extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String managerName=req.getParameter("manager-name");
		String managerEmail=req.getParameter("manager-email");
		String managerPassword=req.getParameter("manager-password");
		Long managerPhoneNumber=Long.parseLong(req.getParameter("manager-phonenumber"));
		double managerSalary=Double.parseDouble(req.getParameter("manager-salary"));
		
		Employee employee=new Employee();
		employee.setEmployeeName(managerName);
		employee.setEmail(managerEmail);
		employee.setPassword(managerPassword);
		employee.setPhoneNumber(managerPhoneNumber);
		employee.setSalary(managerSalary);
		employee.setRole("manager");
		
		EmployeeDao employeeDao=new EmployeeDao();
		employeeDao.saveEmployee(employee);
		resp.setContentType("text/html");
		PrintWriter printWriter=resp.getWriter();
		printWriter.print("<h2>MANAGER DETAILS REGISTERED SUCCESSFULLY!</h2>");
		
	}
}
