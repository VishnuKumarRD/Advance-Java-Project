package com.etm.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.etm.entity.Employee;


@WebServlet("/viewTask")
public class ViewTask extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession=req.getSession();
		Employee developer=(Employee)httpSession.getAttribute("developer");
		req.setAttribute("developer", developer);
		RequestDispatcher requestDispatcher=req.getRequestDispatcher("viewDeveloperTask.jsp");
		requestDispatcher.forward(req, resp);


	}
}
