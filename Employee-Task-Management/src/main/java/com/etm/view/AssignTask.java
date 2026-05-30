package com.etm.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etm.dao.EmployeeDao;
import com.etm.dao.TaskDao;
import com.etm.entity.Employee;
import com.etm.entity.Status;
import com.etm.entity.Task;

@WebServlet("/assignTask")
public class AssignTask extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int developerId=Integer.parseInt(req.getParameter("developerId"));
		int taskId=Integer.parseInt(req.getParameter("taskId"));
		
		EmployeeDao employeeDao=new EmployeeDao();
		Employee dbEmployee=employeeDao.findEmployeeById(developerId);
		
		TaskDao taskDao=new TaskDao();
		Task dbTask=taskDao.findTaskById(taskId);
		
		//set task to employee bcoz one developer had many task 
		 List<Task> taskList = dbEmployee.getTaskList();//null.add(task) -> error to overcome [].add(task) it returns null;
		 if(taskList==null) {
			 taskList=new ArrayList<Task>();// returns empty list[]
		 }
		 
		 
		 taskList.add(dbTask);
		 dbTask.setStatus(Status.ASSIGNED);
		 
		 dbEmployee.setTaskList(taskList);
		 employeeDao.updateEmployee(dbEmployee);
		 
		 PrintWriter printWriter=resp.getWriter();
		 printWriter.print("<h1>TASK ASSIGNED SUCCESSFULLY</h1>");
		
	}
}
