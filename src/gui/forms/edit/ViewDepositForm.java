package gui.forms.edit;

import gui.forms.util.RemarksLabel;
import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;
import gui.popup.SuccessPopup;
import gui.popup.UtilityPopup;

import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
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

import common.entity.deposit.Deposit;
import common.manager.Manager;

public class ViewDepositForm extends EditFormPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private SoyButton clear, save;
	private int initY = 32;
	private ViewFormLabel dateLabel, issuedByLabel, depositorLabel, bankLabel, bankAcctLabel, amountLabel;
	private ViewFormField depositor, bank, bankAcct, issuedBy, date, amount;

	private ErrorLabel error;

	private final int num = Tables.depositFormLabel.length;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JLabel status;
	private ImageIcon icon;

	private SBButton voidBtn;

	private Deposit deposit;

	public ViewDepositForm(Deposit deposit) {
		super("View Deposit");
		this.deposit = deposit;
		addComponents();
		colorTable();
		fillEntries();
	}
	
	private void colorTable(){

		String s = "";
		if (deposit.getInventorySheetData() != null) {
			icon = new ImageIcon("images/accounted.png");
			s = "ACCOUNTED";
			status.setForeground(Color.GREEN.darker());
			remarks.setForeground(Color.GREEN.darker());
			scrollPane.setBorder(new ViewFormBorder(Values.ACCOUNTED_COLOR));
		} else {
			if (deposit.isValid()) {
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

	private void fillEntries() {

		voidBtn.setVisible(deposit.getInventorySheetData() != null ? false : deposit.isValid());

		date.setToolTip(date, DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(deposit.getDate()));
		issuedBy.setToolTip(issuedBy, deposit.getIssuedBy().getFirstPlusLastName());
		depositor.setToolTip(depositor, deposit.getDepositor().getFirstPlusLastName());
		bank.setToolTip(bank, deposit.getBankAccount().getBank()+"");
		bankAcct.setToolTip(bankAcct, deposit.getBankAccount().getAccountNo());
		amount.setToolTip(amount, String.format("%.2f", deposit.getAmount()));
		
		if(deposit.getRemarks() != null)
			remarks.setToolTip(remarks, "-"+deposit.getRemarks());

	}

	private void addComponents() {
		// TODO Auto-generated method stub
		icon = new ImageIcon("images/pending.png");

		status = new JLabel("PENDING", icon, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));
		status.setForeground(Color.orange);
		
		remarks = new RemarksLabel("");

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		error = new ErrorLabel();

		dateLabel = new ViewFormLabel("Date:");
		issuedByLabel = new ViewFormLabel("Issued by:");
		depositorLabel = new ViewFormLabel("Depositor:");
		bankLabel = new ViewFormLabel("Bank:");
		bankAcctLabel = new ViewFormLabel("Bank Acct.:");
		amountLabel = new ViewFormLabel("Amount:");

		issuedBy = new ViewFormField("");
		date = new ViewFormField("");
		bank = new ViewFormField("");
		bankAcct = new ViewFormField("");
		depositor = new ViewFormField("");
		amount = new ViewFormField("");

		for (int i = 0, y = 0, x1 = 20; i < num; i++, y += 43) {

			if (i == 0) {
				dateLabel.setBounds(x1, initY + y - 7, 60, 11);
				date.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}
			if (i == 1) {
				issuedByLabel.setBounds(x1, initY + y - 7, 60, 11);
				issuedBy.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}

			if (i == 2) {
				depositorLabel.setBounds(x1, initY + y - 7, 60, 11);
				depositor.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}
			
			if (i == 3) {
				bankLabel.setBounds(x1, initY + y - 7, 60, 11);
				bank.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}

			if (i == 4) {
				bankAcctLabel.setBounds(x1 - 10, initY + y - 7, 70, 11);
				bankAcct.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}

			if (i == 5) {
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

		voidBtn = new SBButton("invalidate.png", "invalidate2.png", "Void");
		voidBtn.setBounds(Values.WIDTH - 28, 9, 16, 16);
		voidBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();
				UtilityPopup uP = new UtilityPopup(b, Values.INVALIDATE);
				uP.setVisible(true);
				
				if (!uP.getInput().equals("")) {
					deposit.setValid(false);
					deposit.setRemarks(uP.getInput());

					try {
						Manager.depositManager
								.updateDeposit(deposit);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					Values.editPanel.startAnimation();
					new SuccessPopup("Invalidation").setVisible(true);
					Values.centerPanel.changeTable(Values.DEPOSITS);
				}
			}
		});

		add(voidBtn);

		panel.add(issuedBy);
		panel.add(date);

		panel.add(dateLabel);
		panel.add(issuedByLabel);

		panel.add(depositor);
		panel.add(depositorLabel);

		panel.add(bank);
		panel.add(bankLabel);
		
		panel.add(bankAcct);
		panel.add(bankAcctLabel);

		panel.add(amount);
		panel.add(amountLabel);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));

		scrollPane.setBounds(245, 63, 300, 275);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 150, 20);
		remarks.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 2, scrollPane.getWidth(), 20);

		add(scrollPane);
		add(status);
		add(remarks);

	}

}
