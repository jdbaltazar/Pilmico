package gui.list;

import gui.list.util.ItemTags;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;


import util.Values;
import util.soy.RoundedPanel;

public class LogTypeList extends RoundedPanel {

	private JPanel logTypePanel, p1, p2, p3;
	private JLabel label;
	private JScrollPane pane;
	private Icon icon;

	public LogTypeList() {
		init();
		addComponents();
	}

	private void init() {
		// TODO Auto-generated method stub
		setLayout(new BorderLayout());
		setBackground(Color.ORANGE);

		pane = new JScrollPane();

		icon = new ImageIcon("images/log_icon.png");

		p1 = new JPanel();

		p2 = new JPanel();

		p3 = new JPanel();

		// setPreferredSize(new Dimension(150, 200));

		label = new JLabel("Log Type", icon, SwingConstants.LEFT);

		logTypePanel = new JPanel(new GridLayout(0, 1, 0, 0));

	}

	private void addComponents() {
		// TODO Auto-generated method stub
		pane.setOpaque(false);
		pane.setBorder(BorderFactory.createEmptyBorder());

		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);

		logTypePanel.setOpaque(false);

		logTypePanel.setPreferredSize(new Dimension(150, 125)); // 125 =
																					// accounttype
																					// size * 25 + 50
																					// (for "ADD"
																					// row)

		logTypePanel.add(new ItemTags("Add new type of log", Values.LOG_TYPE, false, null));
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Harabara", Font.PLAIN, 16));

		/*List<LogType> logTypes = new ArrayList<LogType>();
		try {
			logTypes = Manager.logManager.getLogTypes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (LogType logType : logTypes) {
			logTypePanel.add(new ItemTags(logType.getName(), Values.LOG_TYPE, true, logType));
		}*/

		pane.setViewportView(logTypePanel);

		add(label, BorderLayout.PAGE_START);
		add(p1, BorderLayout.LINE_START);
		add(p2, BorderLayout.LINE_END);
		add(p3, BorderLayout.PAGE_END);
		add(pane, BorderLayout.CENTER);
	}

}
