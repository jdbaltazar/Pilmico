package gui.forms.edit;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.EditRowPanel;
import gui.forms.util.RowPanel;
import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.FormDropdown.ColorArrowUI;
import gui.forms.util.ViewFormLabel;
import gui.popup.UtilityPopup;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.util.Set;

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
import javax.swing.border.AbstractBorder;

import common.entity.product.Product;
import common.entity.profile.Person;
import common.entity.sales.Sales;
import common.entity.sales.SalesDetail;
import common.manager.Manager;

import util.DateFormatter;
import util.EditFormPanel;
import util.ErrorLabel;
import util.FormCheckbox;
import util.MainFormField;
import util.MainFormLabel;
import util.SBButton;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Utility;
import util.Values;
import util.soy.SoyButton;

public class ViewSalesForm extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel productsPanel, row, p;
	private JScrollPane productsPane;
	private ViewFormField customerCombo, date, issueDate, cashier, issuedAt, rc_no, receipt_no;
	private int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 86, ITEMS_PANE_Y = LABEL_Y + LABEL_HEIGHT; // 125
	private Object[] array = {};
	private JScrollBar sb;

	private ArrayList<EditRowPanel> rowPanel = new ArrayList<EditRowPanel>();
	private JTextField quantity;
	private JLabel status;
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, priceKG, priceSACK, productLabel, deleteLabel;
	private ImageIcon icon;
	private ViewFormLabel issuedaTLabel, rcnumLabel, receiptLabel, dateLabel, cashierLabel, customerLabel, issuedOnLabel;

	private DefaultComboBoxModel model;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";
	private SBButton voidBtn;

	private Sales sales;

	public ViewSalesForm(Sales sales) {
		// TODO Auto-generated constructor stub
		super("View Sales Form");
		this.sales = sales;
		init();
		addComponents();
		colorTable();
		fillEntries();
	};

	private void init() {

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		String s = "";

		status = new JLabel(s, null, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));

		date = new ViewFormField("");
		issueDate = new ViewFormField(new Date().toString());

		issuedaTLabel = new ViewFormLabel("Issued at:");
		issuedOnLabel = new ViewFormLabel("Issued on:");
		rcnumLabel = new ViewFormLabel("RC_No:");
		receiptLabel = new ViewFormLabel("Receipt No.:");
		dateLabel = new ViewFormLabel("Date:");

		cashierLabel = new ViewFormLabel("Cashier:");
		customerLabel = new ViewFormLabel("Customer:");

		cashier = new ViewFormField("");

		issuedAt = new ViewFormField("");
		rc_no = new ViewFormField("");
		receipt_no = new ViewFormField("");

		quantityKGLabel = new TableHeaderLabel("Qtty (kg)");
		productLabel = new TableHeaderLabel("Products");
		quantitySACKlabel = new TableHeaderLabel("Qtty (sack)");
		priceKG = new TableHeaderLabel("Price (kg)");
		priceSACK = new TableHeaderLabel("Price (sack)");
		deleteLabel = new TableHeaderLabel(icon);

		model = new DefaultComboBoxModel(array);
		customerCombo = new ViewFormField("");

		productsPanel = new JPanel();
		productsPanel.setLayout(null);
		productsPanel.setOpaque(false);

		productsPane = new JScrollPane(productsPanel);

		productsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		productsPane.setOpaque(false);
		productsPane.getViewport().setOpaque(false);

		dateLabel.setBounds(15, 12, 70, 20);
		date.setBounds(90, 10, 250, 20);

		cashierLabel.setBounds(335, 12, 70, 20);
		cashier.setBounds(410, 10, 200, 20);

		rcnumLabel.setBounds(15, 35, 70, 20);
		rc_no.setBounds(90, 32, 55, 20);

		receiptLabel.setBounds(143, 35, 80, 20);
		receipt_no.setBounds(228, 32, 112, 20);

		issuedOnLabel.setBounds(335, 35, 70, 20);
		issueDate.setBounds(410, 32, 200, 20);

		customerLabel.setBounds(15, 58, 70, 20);
		customerCombo.setBounds(90, 56, 250, 20);

		issuedaTLabel.setBounds(335, 58, 70, 20);
		issuedAt.setBounds(410, 56, 200, 20);

		setupTable(LABEL_Y, true);

		showUnrequired(true);

		// fillTable();
		panel.add(dateLabel);
		panel.add(date);

		panel.add(cashierLabel);
		panel.add(cashier);

		panel.add(rcnumLabel);
		panel.add(rc_no);

		panel.add(receiptLabel);
		panel.add(receipt_no);

		panel.add(issuedOnLabel);
		panel.add(issueDate);

		panel.add(customerLabel);
		panel.add(customerCombo);
		// panel.add(customerFwd);

		panel.add(issuedaTLabel);
		panel.add(issuedAt);

		// panel.add(productFwd);
		panel.add(quantityKGLabel);
		panel.add(quantitySACKlabel);
		panel.add(priceKG);
		panel.add(priceSACK);
		panel.add(productLabel);
		panel.add(deleteLabel);

		// panel.add(addRow);

		panel.add(productsPane);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);

		scrollPane.setBounds(83, 45, 637, 310);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 150, 20);

		add(scrollPane);
		add(status);
	}
	
	private void colorTable(){

		String s = "";
		if (sales.getInventorySheet() != null) {
			icon = new ImageIcon("images/accounted.png");
			s = "ACCOUNTED";
			status.setForeground(Color.GREEN.darker());
			scrollPane.setBorder(new ViewFormBorder(Values.ACCOUNTED_COLOR));
		} else {
			if (sales.isValid()) {
				icon = new ImageIcon("images/pending.png");
				s = "PENDING";
				status.setForeground(Color.orange);
				scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));
			} else {
				icon = new ImageIcon("images/invalidated.png");
				s = "INVALIDATED";
				status.setForeground(Color.RED);
				scrollPane.setBorder(new ViewFormBorder(Values.INVALIDATED_COLOR));
			}
		}
		status.setText(s);
		status.setIcon(icon);

	}


	private void alternateRows() {

		for (int i = 0; i < rowPanel.size(); i++)
			if (i % 2 == 0)
				rowPanel.get(i).getRow().setBackground(Values.row1);
			else
				rowPanel.get(i).getRow().setBackground(Values.row2);
	}

	private void setupTable(int y, boolean shownFields) {
		LABEL_Y = y;

		quantitySACKlabel.setBounds(30, LABEL_Y, 77, LABEL_HEIGHT);
		quantityKGLabel.setBounds(107, LABEL_Y, 77, LABEL_HEIGHT);
		priceSACK.setBounds(184, LABEL_Y, 85, LABEL_HEIGHT);
		priceKG.setBounds(269, LABEL_Y, 77, LABEL_HEIGHT);
		productLabel.setBounds(346, LABEL_Y, 249, LABEL_HEIGHT);

		if (shownFields) {
			productsPane.setBounds(31, ITEMS_PANE_Y, ROW_WIDTH, 175);
		} else {
			productsPane.setBounds(31, 75, ROW_WIDTH, 175);
		}

	}

	private void showUnrequired(boolean show) {
		customerLabel.setVisible(show);
		customerCombo.setVisible(show);

		rcnumLabel.setVisible(show);
		rc_no.setVisible(show);

		issuedaTLabel.setVisible(show);
		issuedAt.setVisible(show);

		issuedOnLabel.setVisible(show);
		issueDate.setVisible(show);

		receiptLabel.setVisible(show);
		receipt_no.setVisible(show);

	}

	private void addComponents() {
		// TODO Auto-generated method stub
		voidBtn = new SBButton("invalidate.png", "invalidate2.png", "Void");

		error = new ErrorLabel();

		voidBtn.setBounds(Values.WIDTH - 28, 9, 16, 16);

		error.setBounds(305, 340, 200, 30);

		voidBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();

				new UtilityPopup(b, "What's your reason for invalidating this form?", Values.REMARKS, sales).setVisible(true);

			}
		});

		add(voidBtn);
		add(error);

	}

	private void fillTable() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 8; i++) {
			rowPanel.add(new EditRowPanel(null, productsPanel, Values.SALES));
			productsPanel.add(rowPanel.get(rowPanel.size() - 1));
			alternateRows();

			productsPanel.setPreferredSize(new Dimension(330, productsPanel.getComponentCount() * ROW_HEIGHT));
			productsPanel.updateUI();
			productsPanel.revalidate();
		}

	}

	private void fillEntries() {

		voidBtn.setVisible(sales.getInventorySheet() != null ? false : sales.isValid());

		date.setText(DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(sales.getDate()));
		cashier.setText(sales.getCashier().getFirstPlusLastName());
		rc_no.setText(sales.getRcNo());
		receipt_no.setText(sales.getReceiptNo());
		issueDate.setText(DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(sales.getIssuedOn()));
		customerCombo.setText(sales.getCustomerFirstPlusLastName());
		issuedAt.setText(sales.getIssuedAt());

		Set<SalesDetail> salesDetails = sales.getSalesDetails();
		for (SalesDetail sd : salesDetails) {
			rowPanel.add(new EditRowPanel(sd, productsPanel, Values.SALES));
			productsPanel.add(rowPanel.get(rowPanel.size() - 1));
			alternateRows();

			productsPanel.setPreferredSize(new Dimension(330, productsPanel.getComponentCount() * ROW_HEIGHT));
			productsPanel.updateUI();
			productsPanel.revalidate();
		}

	}
}
