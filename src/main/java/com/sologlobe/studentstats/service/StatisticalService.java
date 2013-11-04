package com.sologlobe.studentstats.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.sologlobe.studentstats.model.Student;

public class StatisticalService {
	
	private List<Student> students;

	public StatisticalService(List<Student> students) {
		if(students.isEmpty()) {
			throw new RuntimeException("Impossible to compute an empty list.");
		}
		this.students = students;
	}

	public BigDecimal computeMedian() {
		// TODO Team 1
		throw new RuntimeException("Not yet implemented.");
	}

	public BigDecimal computeMode() {
		// TODO Team 2
		throw new RuntimeException("Not yet implemented.");
	}

	@SuppressWarnings("unused")
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

	public BigDecimal computeStandardDeviation() {
		final BigDecimal variance = computeVariance();
		return BigDecimal.valueOf(Math.sqrt(variance.doubleValue())).setScale(2, RoundingMode.HALF_EVEN);
	}

	private BigDecimal computeVariance() {
		// TODO Team 3
		throw new RuntimeException("Not yet implemented.");
	}
	
	public BigDecimal computeAverage() {
		BigDecimal sum = BigDecimal.ZERO;
		for (Student student : students) {
			sum = sum.add(student.getScore());
		}
		return sum.divide(BigDecimal.valueOf(students.size()), 2, RoundingMode.HALF_EVEN);
	}
	
	public Map<BigDecimal, Integer> computeCumulativeFrequency() {		
		Collections.sort(students, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				return o1.getScore().compareTo(o2.getScore());
			}
			
		});
		
		// TODO Team 4
		throw new RuntimeException("Not yet implemented.");
	}

}
