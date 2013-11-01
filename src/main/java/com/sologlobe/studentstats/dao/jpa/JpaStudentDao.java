package com.sologlobe.studentstats.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.sologlobe.studentstats.dao.StudentDao;
import com.sologlobe.studentstats.model.Student;


public class JpaStudentDao implements StudentDao {

	private EntityManagerFactory emf;

	public JpaStudentDao(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public List<Student> getAllStudents() {
		EntityManager em = emf.createEntityManager();
		try {
			@SuppressWarnings("unchecked")
			List<Student> students = em.createQuery("SELECT s FROM Student s").getResultList();
			return students;
		} finally {
			em.close();
		}
	}
	
}
