package gui.forms.edit;

import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import common.entity.cashadvance.CAPayment;
import common.manager.Manager;

import util.EditFormPanel;
import util.ErrorLabel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class ViewCAPaymentForm extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private SoyButton clear, save;
	private DefaultComboBoxModel model;
	private int initY = 32;
	private ViewFormLabel dateLabel, issuedByLabel, caIDLabel, empRepLabel, amountLabel;
	private ViewFormField caID, empRep, issuedBy, date, amount;

	private ErrorLabel error;

	private final int num = Tables.CAPaymentFormLabel.length;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JLabel status;
	private ImageIcon icon;

	private CAPayment caPayment;

	public ViewCAPaymentForm(CAPayment caPayment) {
		super("View CA Payment");
		this.caPayment = caPayment;
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

		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		error = new ErrorLabel();

		dateLabel = new ViewFormLabel("Date:");
		issuedByLabel = new ViewFormLabel("Issued by:");
		caIDLabel = new ViewFormLabel("CA ID:");
		empRepLabel = new ViewFormLabel("Emp. Rep.:");
		amountLabel = new ViewFormLabel("Amount:");

		issuedBy = new ViewFormField(Manager.loggedInAccount.getFirstPlusLastName());
		date = new ViewFormField("17 Jul 2013 10:15 PM");
		empRep = new ViewFormField("Employee E. Mployee");
		caID = new ViewFormField("1");
		amount = new ViewFormField("2500.00");

		for (int i = 0, y = 0, x1 = 20; i < num; i++, y += 53) {

			if (i == 0) {
				caIDLabel.setBounds(x1, initY + y - 7, 60, 11);
				caID.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}

			if (i == 1) {
				dateLabel.setBounds(x1, initY + y - 7, 60, 11);
				date.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}

			if (i == 2) {
				issuedByLabel.setBounds(x1, initY + y - 7, 60, 11);
				issuedBy.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}

			if (i == 3) {
				empRepLabel.setBounds(x1 - 10, initY + y - 7, 70, 11);
				empRep.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}

			if (i == 4) {
				amountLabel.setBounds(x1, initY + y - 7, 60, 11);
				amount.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}
		}

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

		panel.add(caID);
		panel.add(caIDLabel);

		panel.add(empRep);
		panel.add(empRepLabel);

		panel.add(amount);
		panel.add(amountLabel);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));

		scrollPane.setBounds(245, 63, 300, 275);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 100, 20);

		add(scrollPane);
		add(status);

	}
}
