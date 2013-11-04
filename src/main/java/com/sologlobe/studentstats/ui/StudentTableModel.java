package com.sologlobe.studentstats.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.sologlobe.studentstats.model.Student;

public class StudentTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	
	private List<Student> students;

	public StudentTableModel(List<Student> students) {
		this.students = students;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return students.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Student student = students.get(rowIndex);
		switch (columnIndex) {
		case 0: 
			return student.getId();
		case 1: 
			return student.getFirstName();
		case 2: 
			return student.getLastName();
		case 3: 
			return student.getScore();
		default: 
			throw new IllegalArgumentException("Invalid column index.");
		}
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0: 
			return "ID";
		case 1: 
			return "First Name";
		case 2: 
			return "Last Name";
		case 3: 
			return "Score";
		default: 
			throw new IllegalArgumentException("Invalid column index.");
		}
	}

}
