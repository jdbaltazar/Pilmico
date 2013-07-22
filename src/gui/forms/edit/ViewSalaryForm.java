package gui.forms.edit;

import gui.forms.util.DefaultEntryLabel;
import gui.forms.util.FormDropdown;
import gui.forms.util.RowPanel;
import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import common.entity.profile.Employee;
import common.manager.Manager;

import util.EditFormPanel;
import util.ErrorLabel;
import util.MainFormField;
import util.MainFormLabel;
import util.SBButton;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class ViewSalaryForm extends EditFormPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel feesPanel;
	private JScrollPane feesPane;
	private final int ROW_WIDTH = 305, ROW_HEIGHT = 35, LABEL_HEIGHT = 20, LABEL_Y = 0, UPPER_Y = 63, ITEMS_PANE_Y = 25;
	private Object[] array = {};

	private ArrayList<RowPanel> feesRowPanel = new ArrayList<RowPanel>();
	private ArrayList<RowPanel> caRowPanel = new ArrayList<RowPanel>();
	private TableHeaderLabel feesLabel, amountLabel;
	private ImageIcon icon;
	private SoyButton save;
	private ViewFormField issuedBy, grossPay, netPay, date, issuedFor;
	private ViewFormLabel issuedByLabel, issuedForLabel, dateLabel, salaryLabel, payLabel;

	private DefaultComboBoxModel model;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";

	public ViewSalaryForm() {
		super("View Salary Release Form");
		init();
		addComponents();

	}

	private void init() {

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		issuedFor = new ViewFormField("");

		icon = new ImageIcon("images/util.png");
		
		status = new JLabel("", null, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));
		
		remarks = new ViewFormLabel("", true);

		date = new ViewFormField("15 Jul 2010 9:15 AM");

		dateLabel = new ViewFormLabel("Date:");
		issuedByLabel = new ViewFormLabel("Issued by:");
		issuedForLabel = new ViewFormLabel("Issued for:");
		salaryLabel = new ViewFormLabel("Gross Pay:");
		payLabel = new ViewFormLabel("Net Pay:");

		netPay = new ViewFormField("");
		grossPay = new ViewFormField("");
		
		issuedBy  = new ViewFormField("");

		feesLabel = new TableHeaderLabel("Fees");
		amountLabel = new TableHeaderLabel("Amount");

		model = new DefaultComboBoxModel(array);

		feesPanel = new JPanel();
		feesPanel.setLayout(null);
		feesPanel.setOpaque(false);

		feesPane = new JScrollPane(feesPanel);
		feesPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		feesPane.setOpaque(false);
		feesPane.getViewport().setOpaque(false);

		dateLabel.setBounds(42, 15, 70, 20);
		date.setBounds(115, 15, 190, 20);

		issuedByLabel.setBounds(42, 55, 70, 20);
		issuedBy.setBounds(115, 55, 190, 20);

		issuedForLabel.setBounds(42, 95, 70, 20);
		issuedFor.setBounds(115, 95, 190, 20);

		salaryLabel.setBounds(42, 135, 70, 20);
		grossPay.setBounds(115, 135, 190, 20);

		payLabel.setBounds(42, 175, 70, 20);
		netPay.setBounds(115, 175, 190, 20);

		feesLabel.setBounds(340, 25, 170, 25);
		amountLabel.setBounds(510, 25, 100, 25);
		feesPane.setBounds(341, 50, 286, 140);

//		fwd.setBounds(300, 130, 16, 16);

		/*addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				feesRowPanel.add(new RowPanel(feesPanel, Tables.SALARY));
				feesPanel.add(feesRowPanel.get(feesRowPanel.size() - 1));
				alternateRows(true);

				feesPanel.setPreferredSize(new Dimension(237, feesPanel.getComponentCount() * ROW_HEIGHT));
				feesPanel.updateUI();
				feesPanel.revalidate();

				Rectangle rect = new Rectangle(0, (int) feesPanel.getPreferredSize().getHeight(), 10, 10);
				feesPanel.scrollRectToVisible(rect);
			}
		});*/

		panel.add(dateLabel);
		panel.add(date);

		panel.add(issuedForLabel);
		panel.add(issuedFor);

		panel.add(issuedByLabel);
		panel.add(issuedBy);

		panel.add(salaryLabel);
		panel.add(grossPay);

		panel.add(payLabel);
		panel.add(netPay);

		/*
		 * panel.add(caDeductions); panel.add(dateHeaderLabel);
		 * panel.add(amountLabel); panel.add(cashAdvancesPane);
		 */

		panel.add(feesLabel);
		panel.add(amountLabel);
		panel.add(feesPane);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));

		scrollPane.setBounds(66, 88, 665, 220);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 150, 20);
		remarks.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 2, scrollPane.getWidth(), 20);

		add(scrollPane);
		add(status);
		add(remarks);
	}

	private void alternateRows(boolean isForFees) {

		if (isForFees) {
			for (int i = 0; i < feesRowPanel.size(); i++)
				if (i % 2 == 0)
					feesRowPanel.get(i).getRow().setBackground(Values.row1);
				else
					feesRowPanel.get(i).getRow().setBackground(Values.row2);
		} else
			for (int i = 0; i < caRowPanel.size(); i++)
				if (i % 2 == 0)
					caRowPanel.get(i).getRow().setBackground(Values.row1);
				else
					caRowPanel.get(i).getRow().setBackground(Values.row2);
	}

	public void removeRow(int rowNum) {
		feesPanel.remove(rowNum);
		feesPanel.updateUI();
		feesPanel.revalidate();

		feesPanel.setPreferredSize(new Dimension(237, feesPanel.getComponentCount() * ROW_HEIGHT));

		updateList(rowNum);

		alternateRows(true);
	}

	private void updateList(int removedRow) {

		for (int i = removedRow + 1; i < feesRowPanel.size(); i++) {
			feesRowPanel.get(i).setBounds(0, feesRowPanel.get(i).getY() - ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT);
			feesRowPanel.get(i).setY(feesRowPanel.get(i).getY() - ROW_HEIGHT);
			// System.out.println("command: "+rowPanel2.get(i).getCommand());
			feesRowPanel.get(i).getDeleteRow().setActionCommand((i - 1) + "");
			feesRowPanel.get(i).updateUI();
			feesRowPanel.get(i).revalidate();
		}

		feesRowPanel.remove(removedRow);
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		save = new SoyButton("Save");

		error = new ErrorLabel();

		save.setBounds(280, LABEL_Y + 270, 80, 30);

		error.setBounds(305, 340, 200, 30);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

//		panel.add(save);
		add(error);

	}

	private boolean isValidated() {

		if (feesPanel.getComponentCount() == 0) {

			msg = "Put at least one item";

			return false;
		}

		return true;

	}

	private void clearForm() {
		feesPanel.removeAll();
		feesRowPanel.clear();

		error.setText("");

	}

	public void refreshAccount() {

	}

}
