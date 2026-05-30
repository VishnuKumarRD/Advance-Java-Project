package com.etm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


import com.etm.entity.Task;

public class TaskDao {
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("emp");
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();
	
	public Task saveTask(Task task) {
		entityTransaction.begin();
		entityManager.persist(task);
		entityTransaction.commit();
		return task;
	}
	
	public Task updateTask(Task task) {
		entityTransaction.begin();
		entityManager.merge(task);
		entityTransaction.commit();
		return task;
	}
	
	public Task deleteTask(Task task) {
		entityTransaction.begin();
		entityManager.remove(task);
		entityTransaction.commit();
		return task;
	}
	
	public Task findTaskById(int taskId) {
		return entityManager.find(Task.class,taskId);
	}
	
	public List<Task> findAllTask(){
		Query query=entityManager.createQuery("select t from Task t");
		return query.getResultList();
	}
}
