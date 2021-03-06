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

import common.entity.discountissue.DiscountIssue;
import common.manager.Manager;

public class ViewDiscountForm extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1337878469442864579L;
	private SoyButton clear, save;
	private int initY = 32;
	private ViewFormLabel dateLabel, issuedByLabel, productLabel, customerLabel, amountLabel;
	private ViewFormField product, customer, issuedBy, date, amount;

	private ErrorLabel error;

	private final int num = Tables.discountFormLabel.length;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JLabel status;
	private ImageIcon icon;

	private DiscountIssue discountIssue;

	private SBButton voidBtn;

	public ViewDiscountForm(DiscountIssue discountIssue) {
		super("View Discount");
		this.discountIssue = discountIssue;
		addComponents();
		colorTable();
		fillEntries();
	}

	private void addComponents() {

		voidBtn = new SBButton("invalidate.png", "invalidate2.png", "Void");
		voidBtn.setBounds(Values.WIDTH - 28, 9, 16, 16);
		voidBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();
				UtilityPopup uP = new UtilityPopup(b, Values.INVALIDATE);
				uP.setVisible(true);

				if (!uP.getInput().equals("")) {
					discountIssue.setValid(false);
					discountIssue.setRemarks(uP.getInput());

					try {
						Manager.getInstance().getDiscountIssueManager().updateDiscountIssue(discountIssue);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					Values.editPanel.startAnimation();
					new SuccessPopup("Invalidation").setVisible(true);
					Values.centerPanel.changeTable(Values.DISCOUNTS);
				}
			}
		});

		status = new JLabel("PENDING", null, JLabel.LEADING);
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
		productLabel = new ViewFormLabel("Product:");
		customerLabel = new ViewFormLabel("Customer:");
		amountLabel = new ViewFormLabel("Amount:");

		issuedBy = new ViewFormField(Manager.loggedInAccount.getFirstPlusLastName());
		date = new ViewFormField("17 Jul 2013 10:15 PM");
		customer = new ViewFormField("Juan dela Cruz");
		product = new ViewFormField("PELLETS");
		amount = new ViewFormField("2500.00");

		for (int i = 0, y = 0, x1 = 20; i < num; i++, y += 53) {

			if (i == 0) {
				dateLabel.setBounds(x1, initY + y - 7, 60, 11);
				date.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}
			if (i == 1) {
				issuedByLabel.setBounds(x1, initY + y - 7, 60, 11);
				issuedBy.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}

			if (i == 2) {
				productLabel.setBounds(x1, initY + y - 7, 60, 11);
				product.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}

			if (i == 3) {
				customerLabel.setBounds(x1, initY + y - 7, 60, 11);
				customer.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}

			if (i == 4) {
				amountLabel.setBounds(x1, initY + y - 7, 60, 11);
				amount.setBounds(x1 + 65, initY + y - 14, 200, 20);
			}
		}

		clear.setBounds(157, 298, 80, 30);
		save.setBounds(48, 298, 80, 30);

		error.setBounds(160, 290, 230, 25);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

		// panel.add(clear);
		// panel.add(save);

		panel.add(issuedBy);
		panel.add(date);

		panel.add(dateLabel);
		panel.add(issuedByLabel);

		panel.add(product);
		panel.add(productLabel);

		panel.add(customer);
		panel.add(customerLabel);

		panel.add(amount);
		panel.add(amountLabel);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));

		scrollPane.setBounds(245, 63, 300, 275);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 150, 20);
		remarks.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 2, scrollPane.getWidth(), 20);

		add(voidBtn);
		add(scrollPane);
		add(status);
		add(remarks);

	}

	private void colorTable() {

		String s = "";
		if (discountIssue.getInventorySheetData() != null) {
			icon = new ImageIcon("images/accounted.png");
			s = "ACCOUNTED";
			status.setForeground(Color.GREEN.darker());
			remarks.setForeground(Color.GREEN.darker());
			scrollPane.setBorder(new ViewFormBorder(Values.ACCOUNTED_COLOR));
		} else {
			if (discountIssue.isValid()) {
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

		voidBtn.setVisible(discountIssue.getInventorySheetData() != null ? false : discountIssue.isValid());

		date.setToolTip(date, DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(discountIssue.getDate()));
		issuedBy.setToolTip(issuedBy, Manager.loggedInAccount.getFirstPlusLastName());
		product.setToolTip(product, discountIssue.getProduct() != null ? discountIssue.getProduct().getName() : "");
		customer.setToolTip(customer, discountIssue.getCustomer() != null ? discountIssue.getCustomer().getFirstPlusLastName() : "");
		amount.setToolTip(amount, discountIssue.getAmount() + "");

		if (discountIssue.getRemarks() != null)
			remarks.setToolTip(remarks, "-" + discountIssue.getRemarks());
	}

}
