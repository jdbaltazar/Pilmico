package gui.forms.edit;

import gui.forms.util.RemarksLabel;
import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import util.DateFormatter;
import util.EditFormPanel;
import util.ErrorLabel;
import util.SBButton;
import util.Tables;
import util.Utility;
import util.Values;
import util.soy.SoyButton;

import common.entity.cashadvance.CAPayment;

public class ViewCAPaymentForm extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private SoyButton clear, save;
	private int initY = 32;
	private ViewFormLabel dateLabel, issuedByLabel, caIDLabel, empRepLabel, amountLabel;
	private ViewFormField caID, empRep, issuedBy, date, amount;

	private ErrorLabel error;

	private final int num = Tables.CAPaymentFormLabel.length;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JLabel status;
	private ImageIcon icon;

	private SBButton voidBtn;

	private CAPayment caPayment;

	public ViewCAPaymentForm(CAPayment caPayment) {
		super("View CA Payment");
		this.caPayment = caPayment;
		addComponents();
		colorTable();
		fillEntries();
	}

	private void addComponents() {

		voidBtn = new SBButton("invalidate.png", "invalidate2.png", "Void");
		voidBtn.setBounds(Values.WIDTH - 28, 9, 16, 16);
//		voidBtn.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				PointerInfo a = MouseInfo.getPointerInfo();
//				Point b = a.getLocation();
//				UtilityPopup uP = new UtilityPopup(b, Values.REMARKS);
//				uP.setVisible(true);
//
//				if (!uP.getReason().equals("")) {
//					caPayment.setValid(false);
//					caPayment.setRemarks(uP.getReason());
//
//					try {
//						Manager.cashAdvanceManager.updateCAPayment(caPayment);
//						caPayment.getCashAdvance().setBalance(caPayment.getCashAdvance().getBalance() + caPayment.getAmount());
//						Manager.cashAdvanceManager.updateCashAdvance(caPayment.getCashAdvance());
//						if (Values.viewCAForm != null)
//							Values.viewCAForm.fillEntries();
//
//					} catch (Exception e1) {
//						e1.printStackTrace();
//					}
//
//					Values.editPanel.startAnimation();
//					new SuccessPopup("Invalidation").setVisible(true);
//					Values.centerPanel.changeTable(Values.CA_PAYMENTS);
//				}
//			}
//		});

		status = new JLabel("PENDING", null, JLabel.LEADING);
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
		empRepLabel.setToolTipText("Employee Representative");
		amountLabel = new ViewFormLabel("Amount:");

		issuedBy = new ViewFormField("");
		date = new ViewFormField("");
		empRep = new ViewFormField("");
		caID = new ViewFormField("");
		amount = new ViewFormField("");

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

		remarks = new RemarksLabel("");

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
		remarks.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 2, scrollPane.getWidth(), 20);
		
		add(voidBtn);

		add(scrollPane);
		add(status);

		add(remarks);

	}

	private void colorTable() {

		String s = "";
		if (caPayment.getInventorySheetData() != null) {
			icon = new ImageIcon("images/accounted.png");
			s = "ACCOUNTED";
			status.setForeground(Color.GREEN.darker());
			remarks.setForeground(Color.GREEN.darker());
			scrollPane.setBorder(new ViewFormBorder(Values.ACCOUNTED_COLOR));
		} else {
			if (caPayment.isValid()) {
				icon = new ImageIcon("images/pending.png");
				s = "PENDING";
				status.setForeground(Color.orange);
				remarks.setForeground(Color.orange);
				scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));
			} else {
				icon = new ImageIcon("images/invalidated.png");
				s = "INVALIDATED";
				status.setForeground(Color.RED);
				remarks.setForeground(Color.RED);
				scrollPane.setBorder(new ViewFormBorder(Values.INVALIDATED_COLOR));
			}
		}
		status.setText(s);
		status.setIcon(icon);

	}

	public void fillEntries() {

		voidBtn.setVisible(caPayment.getInventorySheetData() != null ? false : caPayment.isValid());

		caID.setToolTip(caID, caPayment.getCashAdvance().getId() + "");
		date.setToolTip(date, DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(caPayment.getDate()));
		issuedBy.setToolTip(issuedBy, caPayment.getIssuedBy().getFirstPlusLastName());
		empRep.setToolTip(empRep, caPayment.getEmployeeRepresentative() != null ? caPayment.getEmployeeRepresentative().getFirstPlusLastName() : "");
		amount.setToolTip(amount, String.format("%.2f", caPayment.getAmount()));

		if (caPayment.getRemarks() != null)
			remarks.setToolTip(remarks, "-" + caPayment.getRemarks());

	}

}
