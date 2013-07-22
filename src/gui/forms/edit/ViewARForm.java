package gui.forms.edit;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.RowPanel;
import gui.forms.util.FormDropdown.ColorArrowUI;
import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;
import gui.popup.UtilityPopup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;

import common.entity.accountreceivable.AccountReceivable;
import common.entity.accountreceivable.AccountReceivableDetail;
import common.entity.product.Product;
import common.entity.profile.Person;
import common.manager.Manager;

import util.DateFormatter;
import util.EditFormPanel;
import util.ErrorLabel;
import util.MainFormField;
import util.MainFormLabel;
import util.SBButton;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Utility;
import util.Values;
import util.soy.SoyButton;

public class ViewARForm extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel productsPanel;
	private JScrollPane productsPane;
	private final int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 85, UPPER_Y = 63, ITEMS_PANE_Y = LABEL_HEIGHT + LABEL_Y; // 125
	private Object[] array = {};
	private JTextField customerComboField;

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, priceKG, priceSACK, productLabel, deleteLabel;
	private ImageIcon icon;
	private SoyButton save;
	private ViewFormField balance, issuedBy, date, customer;;
	private ViewFormLabel issuedByLabel, balanceLabel, dateLabel, customerLabel, remarks;

	private DefaultComboBoxModel model;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";

	private SBButton voidBtn, payBtn;

	private JLabel status;

	private AccountReceivable accountReceivable;

	public ViewARForm(AccountReceivable accountReceivable) {
		// TODO Auto-generated constructor stub
		super("View Account Receivables Form");
		this.accountReceivable = accountReceivable;
		init();
		addComponents();
		fillEntries();
	};

	private void init() {

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		payBtn = new SBButton("invalidate.png", "invalidate.png", "Pay");

		status = new JLabel("PENDING", null, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));
		status.setForeground(Color.orange);

		remarks = new ViewFormLabel("", true);

		date = new ViewFormField("");

		issuedByLabel = new ViewFormLabel("Issued by:");
		balanceLabel = new ViewFormLabel("Balance:");
		dateLabel = new ViewFormLabel("Date:");

		customerLabel = new ViewFormLabel("Customer:");

		issuedBy = new ViewFormField("");

		balance = new ViewFormField("");

		quantityKGLabel = new TableHeaderLabel("Qtty (kg)");
		productLabel = new TableHeaderLabel("Products");
		quantitySACKlabel = new TableHeaderLabel("Qtty (sack)");
		priceKG = new TableHeaderLabel("Price (kg)");
		priceSACK = new TableHeaderLabel("Price (sack)");
		deleteLabel = new TableHeaderLabel(icon);

		customer = new ViewFormField("");

		productsPanel = new JPanel();
		productsPanel.setLayout(null);
		productsPanel.setOpaque(false);

		productsPane = new JScrollPane(productsPanel);

		productsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		productsPane.setOpaque(false);
		productsPane.getViewport().setOpaque(false);

		dateLabel.setBounds(20, 20, 70, 20);// 15x,12y//50
		date.setBounds(95, 20, 160, 20);

		issuedByLabel.setBounds(305, 20, 70, 20);
		issuedBy.setBounds(380, 20, 200, 20);

		customerLabel.setBounds(20, 50, 70, 20);
		customer.setBounds(95, 48, 200, 20);

		balanceLabel.setBounds(305, 50, 70, 20);
		balance.setBounds(380, 48, 150, 20);

		payBtn.setBounds(535, 51, 16, 16);

		quantitySACKlabel.setBounds(30, LABEL_Y, 77, LABEL_HEIGHT);
		quantityKGLabel.setBounds(107, LABEL_Y, 77, LABEL_HEIGHT);
		priceSACK.setBounds(184, LABEL_Y, 85, LABEL_HEIGHT);
		priceKG.setBounds(269, LABEL_Y, 77, LABEL_HEIGHT);
		productLabel.setBounds(346, LABEL_Y, 249, LABEL_HEIGHT);
		// 136

		productsPane.setBounds(31, ITEMS_PANE_Y, ROW_WIDTH, 140);

		payBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.startAnimation();
				Values.addEntryPanel.showPaymentForm(Values.AR_PAYMENTS, accountReceivable);
			}
		});
		/*
		 * addRow.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) {
		 * rowPanel.add(new RowPanel(productsPanel, Values.ADD));
		 * productsPanel.add(rowPanel.get(rowPanel.size() - 1)); alternateRows();
		 * 
		 * productsPanel.setPreferredSize(new Dimension(330,
		 * productsPanel.getComponentCount() * ROW_HEIGHT));
		 * productsPanel.updateUI(); productsPanel.revalidate();
		 * 
		 * Rectangle rect = new Rectangle(0, (int)
		 * productsPanel.getPreferredSize().getHeight(), 10, 10);
		 * productsPanel.scrollRectToVisible(rect); } });
		 */

		panel.add(dateLabel);
		panel.add(date);

		panel.add(issuedByLabel);
		panel.add(issuedBy);

		panel.add(customerLabel);
		panel.add(customer);

		panel.add(balanceLabel);
		panel.add(balance);

		panel.add(payBtn);
		// panel.add(addRow);

		// panel.add(fwdProduct);
		panel.add(quantitySACKlabel);
		panel.add(quantityKGLabel);
		panel.add(priceKG);
		panel.add(priceSACK);
		panel.add(productLabel);
		// panel.add(deleteLabel);

		panel.add(productsPane);

		// panel.add(fwdCustomer);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));
		// scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(83, 63, 638, 280);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 100, 20);
		remarks.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 2, scrollPane.getWidth(), 20);

		add(scrollPane);
		add(status);
		add(remarks);
	}

	private void alternateRows() {

		for (int i = 0; i < rowPanel.size(); i++)
			if (i % 2 == 0)
				rowPanel.get(i).getRow().setBackground(Values.row1);
			else
				rowPanel.get(i).getRow().setBackground(Values.row2);
	}

	public void removeRow(int rowNum) {
		productsPanel.remove(rowNum);
		productsPanel.updateUI();
		productsPanel.revalidate();

		productsPanel.setPreferredSize(new Dimension(330, productsPanel.getComponentCount() * ROW_HEIGHT));

		updateList(rowNum);

		alternateRows();
	}

	private void updateList(int removedRow) {

		for (int i = removedRow + 1; i < rowPanel.size(); i++) {
			rowPanel.get(i).setBounds(0, rowPanel.get(i).getY() - ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT);
			rowPanel.get(i).setY(rowPanel.get(i).getY() - ROW_HEIGHT);
			// System.out.println("command: "+rowPanel2.get(i).getCommand());
			rowPanel.get(i).getDeleteRow().setActionCommand((i - 1) + "");
			rowPanel.get(i).updateUI();
			rowPanel.get(i).revalidate();
		}

		rowPanel.remove(removedRow);
	}

	private void addComponents() {

		voidBtn = new SBButton("invalidate.png", "invalidate2.png", "Void");
		voidBtn.setBounds(Values.WIDTH - 28, 9, 16, 16);
		voidBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();
				new UtilityPopup(b, "What's your reason for invalidating this form?", Values.REMARKS, accountReceivable).setVisible(true);
			}
		});

		error = new ErrorLabel();
		error.setBounds(305, 340, 200, 30);

		save = new SoyButton("Save");
		save.setBounds(290, 300, 80, 30);
		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

		// panel.add(save);
		add(voidBtn);

		// panel.add(save);
		add(error);

	}

	private void fillEntries() {

		voidBtn.setVisible(accountReceivable.getInventorySheet() != null ? false : accountReceivable.isValid());

		String s = "";
		if (accountReceivable.getInventorySheet() != null) {
			icon = new ImageIcon("images/accounted.png");
			s = "ACCOUNTED";
		} else {
			if (accountReceivable.isValid()) {
				icon = new ImageIcon("images/pending.png");
				s = "PENDING";
			} else {
				icon = new ImageIcon("images/invalidated.png");
				s = "INVALIDATED";
				remarks.setText(accountReceivable.getRemarks());
			}
		}

		status.setText(s);
		status.setIcon(icon);

		date.setText(DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(accountReceivable.getDate()));
		issuedBy.setText(accountReceivable.getIssuedBy().getFirstPlusLastName());
		customer.setText(accountReceivable.getCustomer().getFirstPlusLastName());
		balance.setText(accountReceivable.getBalance() + "");

		payBtn.setVisible(accountReceivable.getBalance() > 0d);

		//
		// Set<DeliveryDetail> deliveryDetails = delivery.getDeliveryDetails();
		// for (DeliveryDetail sd : deliveryDetails) {
		// // rowPanel.add(new EditRowPanel(sd, productsPanel, Values.SALES));
		// // productsPanel.add(rowPanel.get(rowPanel.size() - 1));
		// // alternateRows();
		// //
		// // productsPanel.setPreferredSize(new Dimension(330,
		// // productsPanel.getComponentCount() * ROW_HEIGHT));
		// // productsPanel.updateUI();
		// // productsPanel.revalidate();
		// }

	}

}
