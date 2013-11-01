package com.sologlobe.studentstats.dao;

import javax.persistence.EntityManagerFactory;

import com.sologlobe.studentstats.dao.jpa.JpaStudentDao;
import com.sologlobe.studentstats.dao.jpa.PersistenceManager;

public class DaoFactory {
	
	private static final DaoFactory INSTANCE = new DaoFactory();

	private final EntityManagerFactory emf;

	private DaoFactory() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
	}
	
	public static DaoFactory getInstance() {
		return INSTANCE;
	}

	public StudentDao getStudentDao() {
		return new JpaStudentDao(emf);
	}

}
