package com.etm.dao;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
//dao-data access object
//all db logic is written
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.etm.entity.Employee;

public class EmployeeDao {
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("emp");
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();
	
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
}
