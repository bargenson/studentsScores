package com.sologlobe.studentstats.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Student {

	@Id @GeneratedValue
	private Long id;
	
	@NotNull @NotEmpty
	@Column(nullable = false)
	private String firstName;
	
	@NotNull
	@Column(nullable = false)
	private String lastName;
	
	@NotNull @NotEmpty
	@Column(nullable = false)
	private BigDecimal score;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BigDecimal getScore() {
		return score;
	}
	
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
}
