package gui.forms.edit;

import gui.forms.util.ComboKeyHandler;
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

import util.EditFormPanel;
import util.ErrorLabel;
import util.FormCheckbox;
import util.MainFormField;
import util.MainFormLabel;
import util.SBButton;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Values;
import util.soy.SoyButton;

public class ViewSalesForm extends EditFormPanel{

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

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private JTextField quantity;
	private JButton deleteRow, addRow;
	private JLabel status;
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, priceKG, priceSACK, productLabel, deleteLabel;
	private ImageIcon icon;
	private SoyButton voidBtn;
	private ViewFormLabel issuedaTLabel, rcnumLabel, receiptLabel, dateLabel, cashierLabel, customerLabel, issuedOnLabel;

	private DefaultComboBoxModel model;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";
	private SBButton customerFwd, productFwd;

	public ViewSalesForm() {
		// TODO Auto-generated constructor stub
		super("View Sales Form");
		init();
		addComponents();
		
	};

	private void init() {

		customerFwd = new SBButton("forward.png", "forward.png", "Add new customer");
		productFwd = new SBButton("forward.png", "forward.png", "Add new product");
		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		icon = new ImageIcon("images/pending.png");
		
		status = new JLabel("PENDING", icon, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));
		status.setForeground(Color.orange);

		date = new ViewFormField(new Date().toString());
		issueDate = new ViewFormField(new Date().toString());

		issuedaTLabel = new ViewFormLabel("Issued at:");
		issuedOnLabel = new ViewFormLabel("Issued on:");
		rcnumLabel = new ViewFormLabel("RC_No:");
		receiptLabel = new ViewFormLabel("Receipt No.:");
		dateLabel = new ViewFormLabel("Date:");

		cashierLabel = new ViewFormLabel("Cashier:");
		customerLabel = new ViewFormLabel("Customer:");

		String c = "";
		if (Manager.loggedInAccount != null)
			c = Manager.loggedInAccount.getEmployee().getFirstPlusLastName();
		cashier = new ViewFormField(c);

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
		customerFwd.setBounds(308, 58, 16, 16);

		issuedaTLabel.setBounds(335, 58, 70, 20);
		issuedAt.setBounds(410, 56, 200, 20);

		setupTable(LABEL_Y, true);
		

		addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				rowPanel.add(new RowPanel(productsPanel, Values.ADD));
				productsPanel.add(rowPanel.get(rowPanel.size() - 1));
				alternateRows();

				productsPanel.setPreferredSize(new Dimension(330, productsPanel.getComponentCount() * ROW_HEIGHT));
				productsPanel.updateUI();
				productsPanel.revalidate();

				Rectangle rect = new Rectangle(0, (int) productsPanel.getPreferredSize().getHeight(), 10, 10);
				productsPanel.scrollRectToVisible(rect);
			}
		});

		showUnrequired(true);

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
//		panel.add(customerFwd);

		panel.add(issuedaTLabel);
		panel.add(issuedAt);

//		panel.add(productFwd);
		panel.add(quantityKGLabel);
		panel.add(quantitySACKlabel);
		panel.add(priceKG);
		panel.add(priceSACK);
		panel.add(productLabel);
		panel.add(deleteLabel);

//		panel.add(addRow);

		panel.add(productsPane);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));

		scrollPane.setBounds(90, 45, 637, 310);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 100, 20);
		
		add(scrollPane);
		add(status);
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

		addRow.setBounds(12, LABEL_Y + 5, 16, 16);

		quantitySACKlabel.setBounds(30, LABEL_Y, 77, LABEL_HEIGHT);
		quantityKGLabel.setBounds(107, LABEL_Y, 77, LABEL_HEIGHT);
		priceSACK.setBounds(184, LABEL_Y, 85, LABEL_HEIGHT);
		priceKG.setBounds(269, LABEL_Y, 77, LABEL_HEIGHT);
		productLabel.setBounds(346, LABEL_Y, 249, LABEL_HEIGHT);

		productFwd.setBounds(482, LABEL_Y + 5, 16, 16);

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

		customerFwd.setVisible(show);
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		voidBtn = new SoyButton("Void", true);
		
		error = new ErrorLabel();

		voidBtn.setBounds(Values.WIDTH - 28, 9, 16, 16);

		error.setBounds(305, 340, 200, 30);

		voidBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();
				
				new UtilityPopup(b, "What's your reason for invalidating this form?", Values.REMARKS).setVisible(true);

			}
		});

		add(voidBtn);
		add(error);

	}
}
