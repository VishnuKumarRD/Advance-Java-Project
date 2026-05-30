package com.eatm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eatm.entity.Attendence;


@Repository
public class AttendenceDao {
	@Autowired
	EntityManager entityManager;
	@Autowired
	EntityTransaction entityTransaction;
	
	public Attendence saveAttendence(Attendence attendence) {
		entityTransaction.begin();
		entityManager.persist(attendence);
		entityTransaction.commit();
		return attendence;
	}
	
	public Attendence updateAttendence(Attendence attendence) {
		entityTransaction.begin();
		entityManager.merge(attendence);
		entityTransaction.commit();
		return attendence;
	}
	
	public Attendence deleteAttendence(Attendence attendence) {
		entityTransaction.begin();
		entityManager.remove(attendence);
		entityTransaction.commit();
		return attendence;
	}
	
	public Attendence findAttendenceById(int attendenceId) {
		return entityManager.find(Attendence.class,attendenceId);
	}
	
	public List<Attendence> findAllAttendence(){
		Query query=entityManager.createQuery("select a from Attendence a");
		return query.getResultList();
	}
}
