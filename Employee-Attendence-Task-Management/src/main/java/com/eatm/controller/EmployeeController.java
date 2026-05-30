package com.eatm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eatm.dto.AddressDto;
import com.eatm.dto.EmployeeDto;
import com.eatm.entity.Status;
import com.eatm.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@RequestMapping("/registerManager")
	public String registerManager(@ModelAttribute EmployeeDto employeeDto,@ModelAttribute AddressDto addressDto) {
		return employeeService.registerManager(employeeDto, addressDto);
	}

	@RequestMapping("/login")
	public String loginEmployee(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session) {
		return employeeService.loginEmployee(email, password,session);
	}
	
	
	@RequestMapping("/addDeveloper")
	public String addByDeveloper(@ModelAttribute EmployeeDto employeeDto,@ModelAttribute AddressDto addressDto) {
		return employeeService.addByDeveloper(employeeDto, addressDto);
	}
	
	
	@RequestMapping("/createTask")
	public String createTask(@RequestParam("taskName") String taskName,@RequestParam("duration") int duration ) {
		return employeeService.createTask(taskName, duration);
	}
	
	
	@RequestMapping("/assignTask")
	public String assignTask(@RequestParam("employeeId") int employeeId,@RequestParam("taskId") int taskId ) {
		return employeeService.assignTask(employeeId, taskId);
	}
	
	@RequestMapping("/displayDeveloper")
	public String displayAllDeveloper(HttpSession session) {
		return employeeService.displayAllDeveloper(session);
	}
	
	@RequestMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("employeeId") int employeeId) {
		return employeeService.deleteEmployee(employeeId);
	}
	
	
	@RequestMapping("/logout")
	public String logoutEmployee(HttpSession session) {

	    return employeeService.logoutEmployee(session);
	}
	
	
	@RequestMapping("/viewTask")
	public String viewTaskDetails(HttpSession session) {
		return employeeService.viewTaskDetails(session);
	}
	
	
	@RequestMapping("/changeTask")
	public String changingTask(@RequestParam("taskId") int taskId,HttpSession session) {
		return employeeService.changingTask(taskId,session);
	}
	
	@RequestMapping("/taskUpdate")
	public String taskUpdation(@RequestParam("taskId") int taskId,@RequestParam("status") String status,HttpSession session) {
		return employeeService.taskUpdation(taskId,status,session);
	}
	
	
	@RequestMapping("/changePassword")
	public String changePassword(@RequestParam("email") String email, @RequestParam("password") String password) {
        return employeeService.changePassword(email, password);
    }
}
