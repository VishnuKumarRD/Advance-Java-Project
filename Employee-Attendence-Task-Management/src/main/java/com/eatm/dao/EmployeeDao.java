package com.eatm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eatm.entity.Employee;


@Repository
public class EmployeeDao {
	@Autowired
	EntityManager entityManager;
	@Autowired
	EntityTransaction entityTransaction;
	
	public Employee saveEmployee(Employee employee) {
		entityTransaction.begin();
		entityManager.persist(employee);
		entityTransaction.commit();
		return employee;
	}
	
	public Employee updateEmployee(Employee employee) {
		entityTransaction.begin();
		entityManager.merge(employee);
		entityTransaction.commit();
		return employee;
	}
	
	public Employee deleteEmployee(Employee employee) {
		entityTransaction.begin();
		entityManager.remove(employee);
		entityTransaction.commit();
		return employee;
	}
	
	public Employee findEmployeeById(int employeeId) {
		return entityManager.find(Employee.class,employeeId);
	}
	
	public List<Employee> findAllEmployee(){
		Query query=entityManager.createQuery("select e from Employee e");
		return query.getResultList();
	}
	
	public Employee findEmployeeByEmailAndPassword(String email,String password){
		Query query = entityManager.createQuery("select e from Employee e where e.email=?1 and e.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		List<Employee> employeeList = query.getResultList();//in list there is only 1 employee
		if(employeeList.size()>0)
			return employeeList.get(0);
		return null;
	}
	
	
	
	public Employee findEmployeeByEmail(String email) {

	    Query query=entityManager.createQuery("select e from Employee e where e.email=?1");
	    query.setParameter(1,email);
	    List<Employee> employeeList = query.getResultList();//in list there is only 1 employee
		if(employeeList.size()>0)
			return employeeList.get(0);
		return null;
	   
	}
}
