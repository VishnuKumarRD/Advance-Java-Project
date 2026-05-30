package com.etm.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etm.dao.EmployeeDao;
import com.etm.entity.Employee;

@WebServlet("/viewDeveloper")
public class DisplayAllDeveloper extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EmployeeDao employeeDao=new EmployeeDao();
		List<Employee> employeeList=employeeDao.findAllEmployee();
		
		req.setAttribute("employeeList", employeeList);
		
		RequestDispatcher requestDispatcher=req.getRequestDispatcher("displayAllDeveloper.jsp");
		requestDispatcher.forward(req, resp);
		
		
	}
}
