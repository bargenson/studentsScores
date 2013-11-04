package com.sologlobe.studentstats.ui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.sologlobe.studentstats.dao.DaoFactory;
import com.sologlobe.studentstats.model.Student;
import com.sologlobe.studentstats.service.StatisticalService;
import com.sologlobe.studentstats.ui.StudentTableModel;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private List<Student> students;
	private StatisticalService statisticalService;
	
	public MainFrame() {
		this.students = DaoFactory.getInstance().getStudentDao().getAllStudents();
		this.statisticalService = new StatisticalService(students);
		initFrame();
	}

	private void initFrame() {
		setTitle("Scores Statistics");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setContentPane(new JPanel());
		setBounds(100, 100, 350, 280);
		setResizable(false);
		
		add(getStudentsGrid());
		add(getMedianButton());
		add(getModeButton());
		add(getStandardDerivationButton());
		add(getCummulativeFrequency());
	}
	
	private Component getStudentsGrid() {
		JTable studentsTable = new JTable(getTableModel());
		studentsTable.setPreferredScrollableViewportSize(studentsTable.getPreferredSize());
		return new JScrollPane(studentsTable);
	}

	private TableModel getTableModel() {
		TableModel model = new StudentTableModel(students);
		return model;
	}

	private Component getCummulativeFrequency() {
		JButton button = new JButton("Cummulative Frequency");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				Map<BigDecimal, Integer> cumulativeFrequency = statisticalService.computeCumulativeFrequency();
				JOptionPane.showMessageDialog(MainFrame.this, "Cummulative Frequency: " + cumulativeFrequency);
			}
		});
		return button;
	}

	private Component getStandardDerivationButton() {
		JButton button = new JButton("Standard Derivation");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				BigDecimal standardDerivation = statisticalService.computeStandardDeviation();
				JOptionPane.showMessageDialog(MainFrame.this, "Standard Derivation: " + standardDerivation);
			}
		});
		return button;
	}

	private Component getMedianButton() {
		JButton button = new JButton("Median");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				BigDecimal median = statisticalService.computeMedian();
				JOptionPane.showMessageDialog(MainFrame.this, "Median: " + median);
			}
		});
		return button;
	}
	
	private JButton getModeButton() {
		JButton button = new JButton("Mode");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				BigDecimal mode = statisticalService.computeMode();
				JOptionPane.showMessageDialog(MainFrame.this, "Mode: " + mode);
			}
		});
		return button;
	}
    
}
