package gui.forms.edit;

import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;
import gui.popup.UtilityPopup;

import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import common.entity.accountreceivable.ARPayment;
import common.manager.Manager;

import util.DateFormatter;
import util.EditFormPanel;
import util.ErrorLabel;
import util.SBButton;
import util.Tables;
import util.Utility;
import util.Values;
import util.soy.SoyButton;

public class ViewARPaymentForm extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private SoyButton clear, save;
	private DefaultComboBoxModel model;
	private int initY = 32;
	private ViewFormLabel dateLabel, issuedByLabel, arIDLabel, custRepLabel, amountLabel, remarks;
	private ViewFormField arID, custRep, issuedBy, date, amount;

	private ErrorLabel error;

	private final int num = Tables.ARPaymentFormLabel.length;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JLabel status;
	private ImageIcon icon;

	private SBButton voidBtn;

	private ARPayment arPayment;

	public ViewARPaymentForm(ARPayment arPayment) {
		super("View AR Payment");
		this.arPayment = arPayment;
		addComponents();
		fillEntries();
	}

	private void addComponents() {

		voidBtn = new SBButton("invalidate.png", "invalidate2.png", "Void");
		voidBtn.setBounds(Values.WIDTH - 28, 9, 16, 16);
		voidBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();
				new UtilityPopup(b, "What's your reason for invalidating this form?", Values.REMARKS, arPayment).setVisible(true);
			}
		});

		status = new JLabel("PENDING", null, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));
		status.setForeground(Color.orange);
		
		remarks = new ViewFormLabel("", true);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		error = new ErrorLabel();

		dateLabel = new ViewFormLabel("Date:");
		issuedByLabel = new ViewFormLabel("Issued by:");
		arIDLabel = new ViewFormLabel("AR ID:");
		custRepLabel = new ViewFormLabel("Cust. Rep.:");
		amountLabel = new ViewFormLabel("Amount:");

		issuedBy = new ViewFormField("");
		date = new ViewFormField("");
		custRep = new ViewFormField("");
		arID = new ViewFormField("");
		amount = new ViewFormField("");

		for (int i = 0, y = 0, x1 = 20; i < num; i++, y += 53) {

			if (i == 0) {
				arIDLabel.setBounds(x1, initY + y - 7, 60, 11);
				arID.setBounds(x1 + 65, initY + y - 14, 200, 20);
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
				custRepLabel.setBounds(x1 - 10, initY + y - 7, 70, 11);
				custRep.setBounds(x1 + 65, initY + y - 14, 200, 20);
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

		panel.add(arID);
		panel.add(arIDLabel);

		panel.add(custRep);
		panel.add(custRepLabel);

		panel.add(amount);
		panel.add(amountLabel);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));

		scrollPane.setBounds(245, 63, 300, 275);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 100, 20);
		remarks.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 2, scrollPane.getWidth(), 20);
		
		
		add(voidBtn);
		add(error);

		add(scrollPane);
		add(status);
		add(remarks);

	}

	private void fillEntries() {

		voidBtn.setVisible(arPayment.getInventorySheetData() != null ? false : arPayment.isValid());

		String s = "";
		if (arPayment.getInventorySheetData() != null) {
			icon = new ImageIcon("images/accounted.png");
			s = "ACCOUNTED";
		} else {
			if (arPayment.isValid()) {
				icon = new ImageIcon("images/pending.png");
				s = "PENDING";
			} else {
				icon = new ImageIcon("images/invalidated.png");
				s = "INVALIDATED";
				remarks.setText(arPayment.getRemarks());
			}
		}

		status.setText(s);
		status.setIcon(icon);

		arID.setText(arPayment.getAccountReceivable().getId() + "");
		date.setText(DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(arPayment.getDate()));
		issuedBy.setText(arPayment.getIssuedBy().getFirstPlusLastName());
		custRep.setText(arPayment.getRepresentative() != null ? arPayment.getRepresentative().getFirstPlusLastName() : "");
		amount.setText(arPayment.getAmount() + "");
	}
}
