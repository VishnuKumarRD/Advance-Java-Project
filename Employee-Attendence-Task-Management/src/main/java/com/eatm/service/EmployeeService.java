package com.eatm.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatm.dao.AddressDao;
import com.eatm.dao.AttendenceDao;
import com.eatm.dao.EmployeeDao;
import com.eatm.dao.TaskDao;
import com.eatm.dto.AddressDto;
import com.eatm.dto.EmployeeDto;
import com.eatm.entity.Address;
import com.eatm.entity.Attendence;
import com.eatm.entity.Employee;
import com.eatm.entity.Status;
import com.eatm.entity.Task;

import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeService {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	AddressDao addressDao;


	public String registerManager(EmployeeDto employeeDto,AddressDto addressDto) {
		//write logic to save address and employee in table
		//we need to convert all dto objects into entity object also transfer data from dto to entity object

		//		Address address=new Address();
		//		address.setCity(addressDto.getCity());
		//		address.setState(addressDto.getState());
		//		address.setCountry(addressDto.getCountry());
		//		address.setHouseNumber(addressDto.getHouseNumber());
		//		address.setPincode(addressDto.getPincode());

		//		instead of providing code we have the annotation called modelMapper

		Address address=modelMapper.map(addressDto, Address.class);
		Employee employee=modelMapper.map(employeeDto, Employee.class);

		//set address to employee
		employee.setAddress(address);
		employee.setRole("manager");
		//save both objects
		addressDao.saveAddress(address);
		employeeDao.saveEmployee(employee);
		return "success.jsp";

	}

	@Autowired
	Attendence attendence;
	@Autowired
	AttendenceDao attendenceDao;

	public String loginEmployee(String email,String password,HttpSession session) {
		Employee dbEmployee=employeeDao.findEmployeeByEmailAndPassword(email,password);
		if(dbEmployee!=null) {
			//loginSuccess - create attendence
			attendenceDao.saveAttendence(attendence);

			//store attendence id
			session.setAttribute("attendenceId",attendence.getAttendenceId());

			//Store employee
			session.setAttribute("employee",dbEmployee);

			//map attendence to employee
			List<Attendence> attendenceList=dbEmployee.getAttendenceList();
			if(attendenceList==null) {
				attendenceList=new ArrayList<Attendence>();
			}
			attendenceList.add(attendence);
			dbEmployee.setAttendenceList(attendenceList);
			employeeDao.updateEmployee(dbEmployee);

			if(dbEmployee.getRole().equals("manager")) {
				return "manager.jsp";
			}
			else if(dbEmployee.getRole().equals("developer")) {
				return "developer.jsp";
			}
			else {
				return "success.jsp";
			}
		}
		else {
			//loginfailure
			return "login.jsp";
		}
	}

	public String addByDeveloper(EmployeeDto employeeDto,AddressDto addressDto) {
		Employee employee=modelMapper.map(employeeDto, Employee.class);
		Address address=modelMapper.map(addressDto,Address.class);

		employee.setAddress(address);
		employee.setRole("developer");

		addressDao.saveAddress(address);//first
		employeeDao.saveEmployee(employee);

		return "success.jsp";
	}

	@Autowired
	TaskDao taskDao;
	public String createTask(String taskName,int duration) {
		Task task=new Task();
		task.setTaskName(taskName);
		task.setDuration(duration);
		task.setStatus(Status.CREATED);

		taskDao.saveTask(task);
		return "success.jsp";
	}


	public String assignTask(int employeeId,int taskId) {
		Employee dbEmployee=employeeDao.findEmployeeById(employeeId);
		Task dbTask=taskDao.findTaskById(taskId);

		if(dbEmployee == null) {
			return "employeeNotFound.jsp";
		}

		if(dbTask == null) {
			return "taskNotFound.jsp";
		}
		List<Task> taskList=dbEmployee.getTaskList();

		if(taskList==null) {
			taskList=new ArrayList<Task>();
		}

		taskList.add(dbTask);
		dbTask.setStatus(Status.ASSIGNED);

		dbEmployee.setTaskList(taskList);

		taskDao.updateTask(dbTask);
		employeeDao.updateEmployee(dbEmployee);
		return "success.jsp";
	}


	public String displayAllDeveloper(HttpSession session) {
		List<Employee> employeeList=employeeDao.findAllEmployee();

		session.setAttribute("employeeList", employeeList);
		return "viewAllDeveloper.jsp";
	}

	public String deleteEmployee(int employeeId) {
		Employee dbEmployee=employeeDao.findEmployeeById(employeeId);
		if(dbEmployee == null) {
			return "employeeNotFound.jsp";
		}

		Address dbaddress=dbEmployee.getAddress();
		employeeDao.deleteEmployee(dbEmployee);//first

		if(dbaddress!=null) {
			addressDao.deleteAddress(dbaddress);//second
		}



		return "success.jsp";
	}



	public String logoutEmployee(HttpSession session) {
		int attendenceId=(Integer)session.getAttribute("attendenceId");

		if(attendenceId!=0) {
			Attendence attendence=attendenceDao.findAttendenceById(attendenceId);
			attendence.setLogoutTime(LocalDateTime.now());
			attendenceDao.updateAttendence(attendence);
		}
		
		session.invalidate();
		return "login.jsp";
	}

	public String viewTaskDetails(HttpSession session) {
		Employee dbEmployee=(Employee)session.getAttribute("employee");
		session.setAttribute("developer", dbEmployee);
		return "viewTask.jsp";
	}


	public String changingTask(int taskId, HttpSession session) {
		Task dbTask=taskDao.findTaskById(taskId);
		Employee dbEmployee=(Employee)session.getAttribute("employee");
		if(dbTask==null) {
			return "taskNotFound.jsp";

		}
		List<Task> developerTaskList=dbEmployee.getTaskList();

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
			return "taskAlreadyAssigned.jsp";
		}
		dbTask.setStatus(Status.ASSIGNED);
		taskDao.updateTask(dbTask);
		return "success.jsp";
	}
	
	
	
	public String taskUpdation(int taskId,String status,HttpSession session) {
		Task dbTask=taskDao.findTaskById(taskId);
		
		Employee dbDeveloper=(Employee)session.getAttribute("employee");
		if(dbTask==null) {
			return "taskNotFound.jsp";
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
		
		if(taskAssigned==false) {
			return "taskNotAssigned.jsp";
		}
		
		dbTask.setStatus(Status.valueOf(status));
		taskDao.updateTask(dbTask);
		session.setAttribute("employee", dbDeveloper);
		return "success.jsp";
	}
	
	
	
	public String changePassword(String email,String password) {
	    Employee dbEmployee = employeeDao.findEmployeeByEmail(email);

	    if(dbEmployee == null) {
	        return "emailNotFound.jsp";
	    }

	    dbEmployee.setPassword(password);

	    employeeDao.updateEmployee(dbEmployee);

	    return "success.jsp";
	}
}
