package com.etm.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etm.dao.TaskDao;
import com.etm.entity.Status;
import com.etm.entity.Task;

@WebServlet("/createTask")
public class CreateTask extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String taskName=req.getParameter("taskName");
		int taskDuration=Integer.parseInt(req.getParameter("taskDuration"));
		
		Task task=new Task();
		task.setTaskName(taskName);
		task.setDuration(taskDuration);
		task.setStatus(Status.CREATED);
		
		TaskDao taskDao=new TaskDao();
		taskDao.saveTask(task);
		PrintWriter printWriter=resp.getWriter();
		resp.setContentType("text/html");
		printWriter.print("<h2>TASK CREATED SUCCESSFULLY</h2>");
	}
}
