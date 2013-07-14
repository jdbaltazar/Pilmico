package gui.forms.edit;

import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import common.manager.Manager;

import util.EditFormPanel;
import util.ErrorLabel;
import util.SBButton;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class ViewCAForm extends EditFormPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private SoyButton clear, save;
	private DefaultComboBoxModel model;
	private int initY = 32;
	private ViewFormLabel dateLabel, issuedByLabel, issuedForLabel, balanceLabel, amountLabel;
	private ViewFormField issuedFor, balance, issuedBy, date, amount;

	private ErrorLabel error;

	private final int num = 5;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JLabel status;
	private ImageIcon icon;
	private SBButton payBtn;

	public ViewCAForm() {
		super("View Cash Advance");
		addComponents();

	}

	private void addComponents() {
		// TODO Auto-generated method stub
		icon = new ImageIcon("images/pending.png");
		
		status = new JLabel("PENDING", icon, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));
		status.setForeground(Color.orange);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();
		
		payBtn = new SBButton("invalidate.png", "invalidate.png", "Pay");
		
		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		error = new ErrorLabel();

		dateLabel = new ViewFormLabel("Date:");
		issuedByLabel = new ViewFormLabel("Issued by:");
		issuedForLabel = new ViewFormLabel("Issued for:");
		balanceLabel = new ViewFormLabel("Balance:");
		amountLabel = new ViewFormLabel("Amount:");

		issuedBy = new ViewFormField(Manager.loggedInAccount.getFirstPlusLastName());
		date = new ViewFormField("17 Jul 2013 10:15 PM");
		balance = new ViewFormField("1990.00");
		issuedFor = new ViewFormField("John David S. Baltazar");
		amount = new ViewFormField("2500.00");

		for (int i = 0, y = 0, x1 = 20; i < num; i++, y += 53) {

			if (i == 0) {
				dateLabel.setBounds(x1, initY + y - 7, 60, 11);
				date.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}
			if (i == 1) {
				issuedByLabel.setBounds(x1, initY + y - 7, 60, 11);
				issuedBy.setBounds(x1 + 65, initY + y  - 14, 200, 20);
			}

			if (i == 2) {
				issuedForLabel.setBounds(x1 - 10, initY + y - 7, 70, 11);
				issuedFor.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}

			if (i == 3) {
				amountLabel.setBounds(x1, initY + y - 7, 60, 11);
				amount.setBounds(x1 + 65, initY + y - 14, 200, 20);
				payBtn.setBounds(x1+256, initY+ y - 11, 16, 16);
			}

			if (i == 4) {
				balanceLabel.setBounds(x1, initY + y - 7, 60, 11);
				balance.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}
			
		}
		
		payBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.startAnimation();
				Values.addEntryPanel.showPaymentForm(Values.CA_PAYMENTS);
			}
		});

		clear.setBounds(157, 298, 80, 30);
		save.setBounds(48, 298, 80, 30);

		error.setBounds(160, 290, 230, 25);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

		panel.add(issuedBy);
		panel.add(date);

		panel.add(dateLabel);
		panel.add(issuedByLabel);
		
		panel.add(issuedFor);
		panel.add(issuedForLabel);
		
		panel.add(balance);
		panel.add(balanceLabel);
		
		panel.add(amount);
		panel.add(amountLabel);
		panel.add(payBtn);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));	
		
		scrollPane.setBounds(245, 63, 300, 275);
		
		
		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 100, 20);

		add(scrollPane);
		add(status);

	}
}
