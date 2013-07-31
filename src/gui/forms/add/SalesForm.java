package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.FormDropdown.ColorArrowUI;
import gui.forms.util.DateWithoutTime;
import gui.forms.util.FormField;
import gui.forms.util.IconLabel;
import gui.forms.util.RowPanel;
import gui.popup.SuccessPopup;
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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxUI;

import common.entity.product.Product;
import common.entity.profile.Account;
import common.entity.profile.Employee;
import common.entity.profile.Person;
import common.entity.sales.Sales;
import common.entity.sales.SalesDetail;
import common.manager.Manager;

import util.ErrorLabel;
import util.FormCheckbox;
import util.MainFormField;
import util.MainFormLabel;
import util.SBButton;
import util.SimplePanel;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Values;
import util.soy.SoyButton;

public class SalesForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel productsPanel, row, p;
	private JScrollPane productsPane;
	private JComboBox customerCombo;
	private int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y, ITEMS_PANE_Y = 116; // 125
	private Object[] array = {};
	private JTextField customerComboField;
	private JScrollBar sb;

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private JTextField quantity;
	private JButton deleteRow, addRow;
	private JLabel issuedByLabel;
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, priceKG, priceSACK, productLabel, deleteLabel;
	private SpinnerDate date, issueDate;
	private ImageIcon icon;
	private SoyButton save;
	private JLabel cashier;
	private MainFormField issuedAt, rc_no, receipt_no, remarks;
	private MainFormLabel issuedaTLabel, rcnumLabel, receiptLabel, dateLabel, cashierLabel, customerLabel, issuedOnLabel;

	private DefaultComboBoxModel model;
	private FormCheckbox showRequired;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";
	private SBButton customerFwd, productFwd;

	public SalesForm() {
		// TODO Auto-generated constructor stub
		super("Add Sales Form");
		init();
		addComponents();

		Values.salesForm = this;
	};

	private void init() {

		customerFwd = new SBButton("forward.png", "forward.png", "Add new customer");
		productFwd = new SBButton("forward.png", "forward.png", "Add new product");
		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		showRequired = new FormCheckbox("Show required fields only");

		icon = new ImageIcon("images/util.png");

		date = new SpinnerDate(Values.dateFormat);
		issueDate = new SpinnerDate(Values.dateFormat);
		
		dateStatus = new IconLabel(new ImageIcon("images/valid_date.png"), "This date is valid");

		issuedaTLabel = new MainFormLabel("Issued at:");
		issuedOnLabel = new MainFormLabel("Issued on:");
		rcnumLabel = new MainFormLabel("RC_No:");
		receiptLabel = new MainFormLabel("Receipt No.:");
		dateLabel = new MainFormLabel("Date:");

		cashierLabel = new MainFormLabel("Cashier:");
		customerLabel = new MainFormLabel("Customer:");

		String c = "";
		if (Manager.loggedInAccount != null)
			c = Manager.loggedInAccount.getFirstPlusLastName();
		cashier = new JLabel(c);
		cashier.setOpaque(false);
		cashier.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		cashier.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		cashier.setHorizontalAlignment(JLabel.CENTER);

		issuedAt = new MainFormField(200);
		rc_no = new MainFormField(20);
		receipt_no = new MainFormField(30);
		remarks = new MainFormField(200);

		quantityKGLabel = new TableHeaderLabel("Qtty (kg)");
		productLabel = new TableHeaderLabel("Products");
		quantitySACKlabel = new TableHeaderLabel("Qtty (sack)");
		priceKG = new TableHeaderLabel("Price (kg)");
		priceSACK = new TableHeaderLabel("Price (sack)");
		deleteLabel = new TableHeaderLabel(icon);

		refreshCustomer(false);

		productsPanel = new JPanel();
		productsPanel.setLayout(null);
		productsPanel.setOpaque(false);

		productsPane = new JScrollPane(productsPanel);

		productsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		productsPane.setOpaque(false);
		productsPane.getViewport().setOpaque(false);

		showRequired.setBounds(500, 12, 160, 20);
		showRequired.setSelected(true);
		showRequired.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (showRequired.isSelected()) {
					showUnrequired(false);
					setupTable(50, false);
				} else {
					showUnrequired(true);
					setupTable(91, true);
				}
			}
		});

		dateLabel.setBounds(15, 12, 40, 20);
		date.setBounds(60, 10, 150, 20);
		
		dateStatus.setBounds(215, 12, 16, 16);
		date.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println("Date: "+((SpinnerDateModel) date.getModel()).getDate());
				
				formDate = ((SpinnerDateModel) date.getModel()).getDate();
				
				try {
					
					
					
					
					if (Manager.inventorySheetDataManager
							.getInventorySheetDataWithThisDate(DateWithoutTime
									.getDateWithoutTime(formDate)) != null){
						dateStatus.setIconToolTip(new ImageIcon(
								"images/invalid_date2.png"),
								"An Inventory Sheet with this date already exists", false);
					}
					
					else if(formDate.after(new Date())){
						dateStatus.setIconToolTip(new ImageIcon(
								"images/invalid_date2.png"),
								"Future date not allowed", false);
					}
					
					else
						dateStatus.setIconToolTip(new ImageIcon(
								"images/valid_date.png"), "This date is valid", true);
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				/*validDate = !formDate.after(new Date());
				
				if (validDate)
					dateStatus.setIconToolTip(new ImageIcon(
							"images/valid_date.png"), "This date is valid", true);
				else
					dateStatus.setIconToolTip(new ImageIcon(
							"images/invalid_date2.png"),
							"Future date not allowed", false);*/
			}
		});

		cashierLabel.setBounds(260, 12, 60, 20);
		cashier.setBounds(330, 10, 200, 20);

		rcnumLabel.setBounds(15, 35, 50, 20);
		rc_no.setBounds(65, 32, 70, 20);

		receiptLabel.setBounds(145, 35, 100, 20);
		receipt_no.setBounds(235, 32, 140, 20);

		issuedOnLabel.setBounds(385, 35, 70, 20);
		issueDate.setBounds(460, 32, 150, 20);

		customerLabel.setBounds(15, 58, 70, 20);
		customerFwd.setBounds(308, 60, 16, 16);

		customerFwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.CUSTOMERS);
			}
		});

		issuedaTLabel.setBounds(335, 58, 70, 20);
		issuedAt.setBounds(410, 56, 200, 20);

		setupTable(50, false);
		// issuedByLabel = new JLabel("Issued by:");

		// date.setBounds(510, 12, 150, 20);

		// customerCombo.setBounds(125, UPPER_Y+30, 160, 20);

		// rc_no.setBounds(53, UPPER_Y, 75, 22);
		// receipt_no.setBounds(135, UPPER_Y, 150, 22);

		// issuedByLabel.setBounds(53, UPPER_Y+30, 80, 20);

		// issuedOnLabel.setBounds(380, UPPER_Y, 80, 20);

		// issuedAt.setBounds(380, UPPER_Y+30, 230, 22);

		// issueDate.setBounds(465, UPPER_Y, 150, 20);

		// remarks.setBounds(430, UPPER_Y+245, 200, 22);

		// issuedByLabel.setFont(new Font("Harabara", Font.PLAIN, 16));
		// issuedOnLabel.setFont(new Font("Harabara", Font.PLAIN, 16));

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

				// save.setEnabled(true);
			}
		});

		showUnrequired(false);

		
		add(showRequired);

		panel.add(dateLabel);
		panel.add(date);
		panel.add(dateStatus);

		panel.add(cashierLabel);
		panel.add(cashier);

		panel.add(rcnumLabel);
		panel.add(rc_no);

		panel.add(receiptLabel);
		panel.add(receipt_no);

		panel.add(issuedOnLabel);
		panel.add(issueDate);

		panel.add(customerLabel);
		
		panel.add(customerFwd);

		panel.add(issuedaTLabel);
		panel.add(issuedAt);

		panel.add(productFwd);
		panel.add(quantityKGLabel);
		panel.add(quantitySACKlabel);
		panel.add(priceKG);
		panel.add(priceSACK);
		panel.add(productLabel);
		panel.add(deleteLabel);

		panel.add(addRow);

		panel.add(productsPane);

		/*
		 * panel.add(receipt_no);
		 * 
		 * panel.add(issuedByLabel); panel.add(accountCombo);
		 * 
		 * panel.add(issuedAt);
		 * 
		 * 
		 * panel.add(rc_no); panel.add(remarks);
		 * 
		 * panel.add(issuedOnLabel); panel.add(issueDate);
		 * 
		 * panel.add(quantityKGLabel); panel.add(quantitySACKlabel);
		 * panel.add(priceKG); panel.add(priceSACK); panel.add(productLabel);
		 * panel.add(deleteLabel);
		 * 
		 * panel.add(productsPane);
		 */

		// panel.setBounds(20, 50, 150, 250);

		// panel.setPreferredSize(new Dimension(150, 250));

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(20, 42, 630, 310);

		add(scrollPane);
	}
	
	private void alternateRows() {

		for (int i = 0; i < rowPanel.size(); i++)
			if (i % 2 == 0)
				// rowPanel.get(i).getRow().setBackground(Color.decode("#E6FFFF"));#EBE0FF,F5F0FF
				rowPanel.get(i).getRow().setBackground(Values.row1);
			else
				// rowPanel.get(i).getRow().setBackground(Color.decode("#CCFFFF"));
				// #E0E0FF,#FFFFCC
				rowPanel.get(i).getRow().setBackground(Values.row2);
	}

	private void setupTable(int y, boolean shownFields) {
		LABEL_Y = y;

		addRow.setBounds(12, LABEL_Y + 5, 16, 16);

		quantitySACKlabel.setBounds(30, LABEL_Y, 77, LABEL_HEIGHT);
		quantityKGLabel.setBounds(107, LABEL_Y, 77, LABEL_HEIGHT);
		priceSACK.setBounds(184, LABEL_Y, 85, LABEL_HEIGHT);
		priceKG.setBounds(269, LABEL_Y, 77, LABEL_HEIGHT);
		productLabel.setBounds(346, LABEL_Y, 207, LABEL_HEIGHT);
		deleteLabel.setBounds(553, LABEL_Y, 42, LABEL_HEIGHT);

		productFwd.setBounds(482, LABEL_Y + 5, 16, 16);
		productFwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.PRODUCTS);
			}
		});

		if (shownFields) {
			productsPane.setBounds(31, ITEMS_PANE_Y, ROW_WIDTH, 140);
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

	public void removeRow(int rowNum) {
		System.out.println("pressed row button: " + rowNum);

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

		System.out.println("rowpanel2 size: " + rowPanel.size());
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		save = new SoyButton("Save");

		error = new ErrorLabel();

		save.setBounds(275, 275, 80, 30);
		// save.setEnabled(false);

		error.setBounds(365, 300, 260, 22);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				if (isValidated() && !hasMultipleProduct()
						&& !hasBlankProduct() && !hasZeroQuantity()) {
					
					PointerInfo a = MouseInfo.getPointerInfo();
					Point b = a.getLocation();

					UtilityPopup uP = new UtilityPopup(b, Values.REMARKS);
					uP.setVisible(true);
					
					if (!uP.isClosed()) {

						Date d = ((SpinnerDateModel) date.getModel()).getDate();
						System.out.println("date: " + d.toString());
						Date d2 = ((SpinnerDateModel) issueDate.getModel())
								.getDate();
						Person p = null;
						if (customerCombo.getSelectedItem() != null) {
							p = (Person) customerCombo.getSelectedItem();
						}
						Sales s = new Sales(d, p, rc_no.getText(), issuedAt
								.getText(), d2, receipt_no.getText(),
								Manager.loggedInAccount);

						s.setRemarks(uP.getInput());
						
						for (RowPanel rp : rowPanel) {
							Product product = rp.getSelectedProduct();
							s.addSalesDetail(new SalesDetail(s, product,
									product.getCurrentPricePerKilo(), product
											.getCurrentPricePerSack(), rp
											.getQuantityInKilo(), rp
											.getQuantityInSack()));
						}

						try {
							Manager.salesManager.addSales(s);
							Values.centerPanel.changeTable(Values.SALES);
							new SuccessPopup("Add").setVisible(true);
							clearForm();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else
					error.setText(msg);
			}
		});

		panel.add(save);
		add(error);

	}
	
	public void setErrorText(String msg){
		error.setText(msg);
	}
	
	public boolean hasMultipleProduct(){
		
		for (int i = 0; i < rowPanel.size(); i++) {
			for (int j = i + 1; j < rowPanel.size(); j++) {
				
				if (rowPanel.get(i).getProductCombo().getSelectedIndex() == rowPanel
						.get(j).getProductCombo().getSelectedIndex()) {
					msg = "No multiple product entry allowed ";
					
					return true;
				}
			}
		}
		
		return false;
	}

	private boolean hasBlankProduct(){
		
		for (int i = 0; i < rowPanel.size(); i++) {
			if(rowPanel.get(i).getProductCombo().getSelectedIndex() == -1){
				
				JTextField field = (JTextField) rowPanel.get(i).getProductCombo().getEditor().getEditorComponent();
				System.out.println(field.getText());
				
				if(!field.getText().equals(""))
					msg = "Unknown product found in row "+(i+1)+" ";
				else
					msg = "No product indicated in row "+(i+1)+" ";
				return true;
			}
		}
		
		return false;
	}
	
	private boolean hasZeroQuantity(){
		
		for (int i = 0; i < rowPanel.size(); i++) {
			
			if(rowPanel.get(i).getQuantityInKilo() == 0d && rowPanel.get(i).getQuantityInSack() == 0d){
				msg = "Both quantities should not be 0 in row "+(i+1)+" ";
				
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isValidated() {
		
		if (((SpinnerDateModel) date.getModel()).getDate().after(new Date())
				|| ((SpinnerDateModel) issueDate.getModel()).getDate().after(
						new Date())) {

			msg = "Future date not allowed ";

			return false;
		}
		
		if (productsPanel.getComponentCount() == 0) {

			msg = "Put at least one product ";

			return false;
		}

		return true;

	}

	private void clearForm() {
		productsPanel.removeAll();
		rowPanel.clear();
		refreshDate();

		rc_no.setText("");
		receipt_no.setText("");
		issuedAt.setText("");
		
		error.setText("");

		refreshCustomer(true);
		
		showUnrequired(false);
	}

	public void refreshDate() {
		date.setValue(new Date());
	}

	public void refreshCustomer(boolean remove) {
		
		if(remove)
			panel.remove(customerCombo);
		
		try {
			model = new DefaultComboBoxModel(Manager.employeePersonManager.getCustomersOnly().toArray());
			customerCombo = new JComboBox(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		customerCombo.setUI(ColorArrowUI.createUI(this));
		customerCombo.setEditable(true);
		customerComboField = (JTextField) customerCombo.getEditor().getEditorComponent();
		customerComboField.setText("");
		customerComboField.setOpaque(false);
		customerComboField.setBorder(BorderFactory.createEmptyBorder());
		customerComboField.addKeyListener(new ComboKeyHandler(customerCombo));

		customerCombo.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
		customerCombo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		customerCombo.setOpaque(false);
		
		customerCombo.setSelectedIndex(-1);
		
		customerCombo.setBounds(85, 58, 220, 20);
		
		panel.add(customerCombo);
		
		panel.revalidate();
		panel.updateUI();
	}
}
