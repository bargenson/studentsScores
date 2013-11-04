package com.sologlobe.studentstats.service;

import static org.fest.assertions.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fest.assertions.data.MapEntry;
import org.junit.Before;
import org.junit.Test;

import com.sologlobe.studentstats.model.Student;

public class StatisticalServiceTest {

	private StatisticalService statisticalService;

	@Before
	public void setUp() {
		this.statisticalService = new StatisticalService();
	}

	/*
	 * Team 1
	 */
	@Test
	public void computeMedianWithAnOddListTest() {
		// Given
		final List<Student> students = buildStudentsWithScore(1, 2, 3, 7, 8, 8, 9, 10, 12);

		// When
		final BigDecimal result = statisticalService.computeMedian(students);

		// Then
		assertThat(result).isEqualTo(new BigDecimal(8));
	}

	@Test
	public void computeMedianWithAnEvenListTest() {
		// Given
		final List<Student> students = buildStudentsWithScore(1, 2, 3, 7, 8, 9, 10, 12);

		// When
		final BigDecimal result = statisticalService.computeMedian(students);

		// Then
		assertThat(result).isEqualTo(new BigDecimal("7.5"));
	}

	/*
	 * Team 2
	 */
	@Test
	public void computeModeTest() {
		// Given
		final List<Student> students = buildStudentsWithScore(4, 4, 4, 4, 4, 6, 7, 8, 9, 10);

		// When
		final BigDecimal result = statisticalService.computeMode(students);

		// Then
		assertThat(result).isEqualTo(new BigDecimal("4"));
	}

	
	/*
	 * Team 3
	 */
	@Test
	public void computeStandardDeviationTest() {
		// Given
		final List<Student> students = buildStudentsWithScore(2, 4, 4, 4, 5, 5, 7, 9);

		// When
		final BigDecimal result = statisticalService.computeStandardDeviation(students);

		// Then
		assertThat(result).isEqualByComparingTo(new BigDecimal("2"));
	}

	@Test
	public void computeAverage() {
		// Given
		final List<Student> students = buildStudentsWithScore(1, 2, 8, 9);

		// When
		final BigDecimal result = statisticalService.computeAverage(students);

		// Then
		assertThat(result).isEqualTo(new BigDecimal("5"));
	}

	
	/*
	 * Team 4
	 */
	@Test
	public void computeCumulativeFrequency() {
		// Given
		final List<Student> students = buildStudentsWithScore(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);

		// When
		final Map<BigDecimal, Integer> result = statisticalService
				.computeCumulativeFrequency(students);

		// Then
		assertThat(result).isNotNull().hasSize(4)
				.contains(MapEntry.entry(new BigDecimal(1), 1))
				.contains(MapEntry.entry(new BigDecimal(2), 3))
				.contains(MapEntry.entry(new BigDecimal(3), 6))
				.contains(MapEntry.entry(new BigDecimal(4), 10));
	}

	private List<Student> buildStudentsWithScore(int... scores) {
		final List<Student> students = new ArrayList<Student>();
		for (int i = 0; i < scores.length; i++) {
			students.add(new Student((long) i, i + "", i + "", new BigDecimal(scores[i])));
		}
		return students;
	}

}
