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

@WebServlet("/addDeveloper")
public class AddDeveloper extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String managerName = req.getParameter("developer-name");
		String managerEmail = req.getParameter("developer-email");
		String managerPassword = req.getParameter("developer-password");
		long managerPhoneNumber =Long.parseLong(req.getParameter("developer-phonenumber"));
		double managerSalary = Double.parseDouble(req.getParameter("developer-salary"));

		Employee employee=new Employee();
		employee.setEmployeeName(managerName);
		employee.setEmail(managerEmail);
		employee.setPassword(managerPassword);
		employee.setPhoneNumber(managerPhoneNumber);
		employee.setSalary(managerSalary);
		employee.setRole("developer");
		
		EmployeeDao employeeDao=new EmployeeDao();
		employeeDao.saveEmployee(employee);
		
		PrintWriter printWriter=resp.getWriter();
		printWriter.print("<h1>Developer Registered</h1>");
	}
}
