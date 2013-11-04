package com.sologlobe.studentstats.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
		Collections.sort(students, new Comparator<Student>() {

			public int compare(Student o1, Student o2) {
				return o1.getScore().compareTo(o2.getScore());
			}
		});
		
		if(students.size() % 2 == 0) {
			return students.get(students.size() / 2).getScore()
					.add(students.get(students.size() / 2 - 1).getScore())
					.divide(new BigDecimal(2));
		} else {			
			return students.get(students.size() / 2).getScore();
		}
	}

	public BigDecimal computeMode() {
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

	public BigDecimal computeStandardDeviation() {
		final BigDecimal variance = computeVariance();
		return BigDecimal.valueOf(Math.sqrt(variance.doubleValue())).setScale(2, RoundingMode.HALF_EVEN);
	}

	private BigDecimal computeVariance() {
		final BigDecimal average = computeAverage();
		
		BigDecimal squareSum = BigDecimal.ZERO;
		for (Student student : students) {
			squareSum = squareSum.add(average.subtract(student.getScore()).pow(2)); 
		}
		
		return squareSum.divide(BigDecimal.valueOf(students.size()), 2, RoundingMode.HALF_EVEN);
	}
	
	public BigDecimal computeAverage() {
		BigDecimal sum = BigDecimal.ZERO;
		for (Student student : students) {
			sum = sum.add(student.getScore());
		}
		return sum.divide(BigDecimal.valueOf(students.size()), 2, RoundingMode.HALF_EVEN);
	}
	
	public Map<BigDecimal, Integer> computeCumulativeFrequency() {
		final Map<BigDecimal, Integer> result = new TreeMap<BigDecimal, Integer>();
		
		Collections.sort(students, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				return o1.getScore().compareTo(o2.getScore());
			}
			
		});
		
		int cumulativeFrequency = 0;
		for (Student student : students) {
			final BigDecimal currentScore = student.getScore();
			if(result.containsKey(currentScore)) {
				result.put(currentScore, result.get(currentScore) + 1);
				cumulativeFrequency++;
			} else {
				result.put(currentScore, ++cumulativeFrequency);
			}
		}
		
		return result;
	}

}
