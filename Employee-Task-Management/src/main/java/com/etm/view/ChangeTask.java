package com.etm.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.etm.dao.EmployeeDao;
import com.etm.dao.TaskDao;
import com.etm.entity.Employee;
import com.etm.entity.Status;
import com.etm.entity.Task;

@WebServlet("/changeTask")
public class ChangeTask extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int taskId=Integer.parseInt(req.getParameter("taskId"));
		
		HttpSession session=req.getSession();
		Employee dbDeveloper=(Employee)session.getAttribute("developer");
		
//		EmployeeDao employeeDao = new EmployeeDao();
//		Employee dbDeveloper =employeeDao.findEmployeeById(sessionDeveloper.getEmployeeId());
		
		TaskDao taskDao=new TaskDao();
		Task dbTask=taskDao.findTaskById(taskId);
		
		resp.setContentType("text/html");
		
		PrintWriter printWriter=resp.getWriter();
		
		if(dbTask==null) {
			printWriter.print("<h2>Task Not Found!</h2>");
			return;
		}
		
		List<Task> developerTaskList=dbDeveloper.getTaskList();
		
		boolean taskAssigned=false;
		
		if(developerTaskList!=null) {
			for(Task task:developerTaskList) {
				if(task.getTaskId()==taskId) {
					taskAssigned=true;
					break;
				}
			}
		}
		
		if(taskAssigned==true) {
			printWriter.print("<h2>This Task is not Assigned to you!</h2>");
			return;
		}
		dbTask.setStatus(Status.CREATED);
		taskDao.updateTask(dbTask);
		printWriter.print("<h2>TASK UPDATED SUCCESSFULLY!</h2>");
		
	}
}
