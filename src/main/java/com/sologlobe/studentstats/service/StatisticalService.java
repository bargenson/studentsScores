package com.sologlobe.studentstats.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.sologlobe.studentstats.dao.DaoFactory;
import com.sologlobe.studentstats.dao.StudentDao;
import com.sologlobe.studentstats.model.Student;

public class StatisticalService {

	private StudentDao studentDao;

	public StatisticalService() {
		this.studentDao = DaoFactory.getInstance().getStudentDao();
	}

	public BigDecimal getMedian() {
		final List<Student> students = studentDao.getAllStudents();
		Collections.sort(students, new Comparator<Student>() {

			public int compare(Student o1, Student o2) {
				return o1.getScore().compareTo(o2.getScore());
			}
		});
		return students.get(students.size() / 2).getScore();
	}

	public BigDecimal getMode() {
		final List<Student> students = studentDao.getAllStudents();

		final Map<BigDecimal, Integer> distribution = new HashMap<BigDecimal, Integer>();
		for (Student student : students) {
			BigDecimal score = student.getScore();
			if (distribution.containsKey(score)) {
				distribution.put(score, distribution.get(score) + 1);
			} else {
				distribution.put(score, 1);
			}
		}

		return getKeyWithMaxValue(distribution);
	}

	private BigDecimal getKeyWithMaxValue(final Map<BigDecimal, Integer> distribution) {
		final TreeMap<BigDecimal, Integer> sortedDistribution = new TreeMap<BigDecimal, Integer>(
				new Comparator<BigDecimal>() {

					@Override
					public int compare(BigDecimal o1, BigDecimal o2) {
						return -1 * distribution.get(o1).compareTo(distribution.get(o2));
					}

				});
		sortedDistribution.putAll(distribution);

		return sortedDistribution.firstKey();
	}

	public BigDecimal getStandardDeviation() {
		final List<Student> students = studentDao.getAllStudents();
		
		BigDecimal variance = getVariance(students);
		return BigDecimal.valueOf(Math.sqrt(variance.doubleValue()));
	}

	private BigDecimal getVariance(List<Student> students) {
		return null;
	}
	
	public BigDecimal getAverage(List<Student> students) {
		BigDecimal sum = BigDecimal.ZERO;
		for (Student student : students) {
			sum = sum.add(student.getScore());
		}
		return sum.divide(BigDecimal.valueOf(students.size()));
	}

}
