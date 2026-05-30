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

@WebServlet("/deleteEmployee")
public class DeleteDeveloper extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int employeeId=Integer.parseInt(req.getParameter("employeeId"));
		EmployeeDao employeeDao=new EmployeeDao();
		Employee employee=employeeDao.findEmployeeById(employeeId);
		resp.setContentType("text/html");
		PrintWriter printWriter=resp.getWriter();
		
		if(employee!=null) {
			//task list exists remove relation
			employee.setTaskList(null);
			employeeDao.updateEmployee(employee);
			
			employeeDao.deleteEmployee(employee);
			printWriter.print("<h2>EMPLOYEE DELETED SUCCESSFULLY !</h2>");
		}
		else {
			printWriter.print("<h2>INVALID ID !</h2>");
		}
	}
}
