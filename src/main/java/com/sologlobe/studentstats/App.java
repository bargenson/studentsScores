package com.sologlobe.studentstats;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sologlobe.studentstats.dao.DaoFactory;
import com.sologlobe.studentstats.dao.StudentDao;
import com.sologlobe.studentstats.service.StatisticalService;

public class App extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private StudentDao studentDao;
	private StatisticalService statisticalService;
	
	public App() {
		this.studentDao = DaoFactory.getInstance().getStudentDao();
		this.statisticalService = new StatisticalService(studentDao.getAllStudents());
		initFrame();
	}

	private void initFrame() {
		setTitle("Scores Statistics");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setContentPane(new JPanel());
		setBounds(100, 100, 300, 125);
		
		add(getMedianButton());
		add(getModeButton());
		add(getStandardDerivationButton());
		add(getCummulativeFrequency());
	}
	
	private Component getCummulativeFrequency() {
		JButton button = new JButton("Cummulative Frequency");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				Map<BigDecimal, Integer> cumulativeFrequency = statisticalService.computeCumulativeFrequency();
				JOptionPane.showMessageDialog(App.this, "Cummulative Frequency: " + cumulativeFrequency);
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
				JOptionPane.showMessageDialog(App.this, "Standard Derivation: " + standardDerivation);
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
				JOptionPane.showMessageDialog(App.this, "Median: " + median);
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
				JOptionPane.showMessageDialog(App.this, "Mode: " + mode);
			}
		});
		return button;
	}

	public static void main(String... args) {
        new App().setVisible(true);
    }
    
}
