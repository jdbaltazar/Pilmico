package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.FormDropdown.ColorArrowUI;
import gui.forms.util.IconLabel;
import gui.forms.util.RowPanel;
import gui.forms.util.SimpleNumericField;
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

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.ErrorLabel;
import util.MainFormLabel;
import util.SBButton;
import util.SimplePanel;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Values;
import util.soy.SoyButton;

import common.entity.accountreceivable.AccountReceivable;
import common.entity.accountreceivable.AccountReceivableDetail;
import common.entity.product.Product;
import common.entity.product.exception.NegativeValueException;
import common.entity.product.exception.NotEnoughQuantityException;
import common.entity.product.exception.ZeroKilosPerSackException;
import common.entity.profile.Person;
import common.manager.Manager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class AccountReceivablesForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel productsPanel;
	private JScrollPane productsPane;
	private JComboBox customerCombo;
	private final int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 115, ITEMS_PANE_Y = LABEL_HEIGHT + LABEL_Y; // 125
	private JTextField customerComboField;

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private JButton addRow;
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, kgpersack, priceKG, priceSACK, productLabel, deleteLabel;
	private SpinnerDate date;
	private ImageIcon icon;
	private SoyButton save;
	private JLabel issuedBy;
	private MainFormLabel issuedByLabel, amountLabel, dateLabel, customerLabel;
	private SimpleNumericField amount;

	private DefaultComboBoxModel model;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";
	private SBButton fwdCustomer, fwdProduct;

	public AccountReceivablesForm() {
		// TODO Auto-generated constructor stub
		super("Add Account Receivables Form");
		init();
		addComponents();

		Values.accountReceivablesForm = this;
	};

	private void init() {

		fwdCustomer = new SBButton("forward.png", "forward.png", "Add new customer");
		fwdProduct = new SBButton("forward.png", "forward.png", "Add new product");
		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");

		fwdCustomer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.CUSTOMERS);
			}
		});

		fwdProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.PRODUCTS);
			}
		});

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		icon = new ImageIcon("images/util.png");

		date = new SpinnerDate(Values.dateFormat);

		error = new ErrorLabel();
		dateStatus = new IconLabel(new ImageIcon("images/valid_date.png"), Values.VALID_DATE);
		determineDateStatus();

		date.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				determineDateStatus();
			}
		});

		issuedByLabel = new MainFormLabel("Issued by:");
		amountLabel = new MainFormLabel("Amount:");
		dateLabel = new MainFormLabel("Date:");

		amount = new SimpleNumericField(10, "");
		amount.setOpaque(false);
		amount.setForeground(Color.black);
		amount.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
		amount.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		amount.setHorizontalAlignment(JLabel.CENTER);

		customerLabel = new MainFormLabel("Customer:");

		issuedBy = new JLabel(Manager.loggedInAccount.getFirstPlusLastName());
		issuedBy.setOpaque(false);
		issuedBy.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		issuedBy.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		issuedBy.setHorizontalAlignment(JLabel.CENTER);

		quantityKGLabel = new TableHeaderLabel("Qtty (kg)");
		productLabel = new TableHeaderLabel("Products");
		quantitySACKlabel = new TableHeaderLabel("Qtty (sack)");
		kgpersack = new TableHeaderLabel("kg / sk");
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

		dateLabel.setBounds(75, 50, 40, 20);// 15x,12y //40
		date.setBounds(115, 50, 180, 20);// 85
		dateStatus.setBounds(300, 52, 16, 16);

		issuedByLabel.setBounds(350, 50, 70, 20);
		issuedBy.setBounds(425, 50, 170, 20);

		customerLabel.setBounds(45, 80, 70, 20);

		fwdCustomer.setBounds(298, 82, 16, 16);

		amountLabel.setBounds(359, 80, 60, 20);
		amount.setBounds(424, 78, 170, 20);

		addRow.setBounds(32, LABEL_Y + 5, 16, 16);

		quantitySACKlabel.setBounds(50, LABEL_Y, 77, LABEL_HEIGHT);
		quantityKGLabel.setBounds(127, LABEL_Y, 77, LABEL_HEIGHT);
		kgpersack.setBounds(204, LABEL_Y, 50, LABEL_HEIGHT);
		priceSACK.setBounds(254, LABEL_Y, 85, LABEL_HEIGHT);
		priceKG.setBounds(339, LABEL_Y, 77, LABEL_HEIGHT);
		productLabel.setBounds(416, LABEL_Y, 167, LABEL_HEIGHT);
		deleteLabel.setBounds(583, LABEL_Y, 32, LABEL_HEIGHT);

		// 136
		fwdProduct.setBounds(532, LABEL_Y + 5, 16, 16);

		productsPane.setBounds(51, ITEMS_PANE_Y, ROW_WIDTH, 140);

		addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				enableAmountField(false);

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

		panel.add(dateLabel);
		panel.add(date);
		panel.add(dateStatus);

		panel.add(issuedByLabel);
		panel.add(issuedBy);

		panel.add(customerLabel);
		panel.add(customerCombo);

		panel.add(amountLabel);
		panel.add(amount);

		panel.add(addRow);

		panel.add(fwdProduct);
		panel.add(quantitySACKlabel);
		panel.add(quantityKGLabel);
		panel.add(kgpersack);
		panel.add(priceKG);
		panel.add(priceSACK);
		panel.add(productLabel);
		panel.add(deleteLabel);

		panel.add(productsPane);

		panel.add(fwdCustomer);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(0, 5, 670, 370);

		add(error);
		add(scrollPane);
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

		if (rowPanel.size() == 0)
			enableAmountField(true);
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		save = new SoyButton("Save");

		save.setBounds(290, 300, 80, 30);

		error.setBounds(375, 290, 250, 22);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isValidated() && !hasMultipleProduct() && !hasBlankProduct() && !hasInvalidQuantity() && !hasZeroQuantity()) {
					// boolean valid = true;
					for (RowPanel rp : rowPanel) {
						Product p = rp.getSelectedProduct();
						try {
							if (!Product.validDecrement(p.getSacks(), p.getKilosOnDisplay(), p.getKilosPerSack(), rp.getQuantityInSack(),
									rp.getQuantityInKilo())) {
								// valid = false;
							}
						} catch (NegativeValueException e1) {
							e1.printStackTrace();
						} catch (NotEnoughQuantityException e1) {
							e1.printStackTrace();
						} catch (ZeroKilosPerSackException e2) {
							e2.printStackTrace();
						}
					}
					PointerInfo a = MouseInfo.getPointerInfo();
					Point b = a.getLocation();

					UtilityPopup uP = new UtilityPopup(b, Values.REMARKS);
					uP.setVisible(true);

					if (!uP.isClosed()) {

						Date d = ((SpinnerDateModel) date.getModel()).getDate();
						AccountReceivable ar = new AccountReceivable(d, (Person) customerCombo.getSelectedItem(), Manager.loggedInAccount);

						// AccountReceivable ar = new AccountReceivable(d,
						// (Person) customerCombo.getSelectedItem(),
						// Manager.loggedInAccount);

						if (rowPanel.size() > 0) {

							for (RowPanel rp : rowPanel) {
								Product p = rp.getSelectedProduct();
								ar.addAccountReceivableDetail(new AccountReceivableDetail(ar, p, p.getCurrentPricePerKilo(), p.getCurrentPricePerSack(), rp
										.getQuantityInKilo(), rp.getQuantityInSack()));
							}
						} else {
							ar.setBalance(Double.parseDouble(amount.getText()));
						}

						ar.setRemarks(uP.getInput());

						try {
							Manager.getInstance().getAccountReceivableManager().addAccountReceivable(ar);

							for (AccountReceivableDetail ard : ar.getAccountReceivableDetails()) {
								Product pd = ard.getProduct();
								pd.decrementQuantity(ard.getQuantityInSack(), ard.getQuantityInKilo());
								Manager.getInstance().getProductManager().updateProduct(pd);
							}

							Values.centerPanel.changeTable(Values.ACCOUNT_RECEIVABLES);
							new SuccessPopup("Add").setVisible(true);
							clearForm();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

				} else
					error.setToolTip(msg);
				/*
				 * else {
				 * 
				 * JOptionPane.showMessageDialog(Values.mainFrame,
				 * "This action will result to NEGATIVE QUANTITY to product/s in this form: "
				 * + "\n In order to proceed: " +
				 * "\n (1) Update the quantity of the affected product/s; or" +
				 * "\n (2) Add a delivery for affected product/s; or" +
				 * "\n (3) Invalidate a Pullout/s and/or Sales", "Not Allowed!",
				 * JOptionPane.WARNING_MESSAGE); }
				 */

			}
		});

		panel.add(save);
	}

	public void setErrorText(String msg) {
		error.setToolTip(msg);
	}

	public boolean hasMultipleProduct() {

		for (int i = 0; i < rowPanel.size(); i++) {
			for (int j = i + 1; j < rowPanel.size(); j++) {

				if (rowPanel.get(i).getProductCombo().getSelectedIndex() == rowPanel.get(j).getProductCombo().getSelectedIndex()) {
					msg = "No multiple product entry allowed ";

					return true;
				}
			}
		}

		return false;
	}

	private boolean hasBlankProduct() {

		for (int i = 0; i < rowPanel.size(); i++) {
			if (rowPanel.get(i).getProductCombo().getSelectedIndex() == -1) {

				JTextField field = (JTextField) rowPanel.get(i).getProductCombo().getEditor().getEditorComponent();
				System.out.println(field.getText());

				if (!field.getText().equals(""))
					msg = "Unknown product found in row " + (i + 1) + " ";
				else
					msg = "No product indicated in row " + (i + 1) + " ";
				return true;
			}
		}

		return false;
	}

	private boolean hasZeroQuantity() {

		for (int i = 0; i < rowPanel.size(); i++) {

			if (rowPanel.get(i).getQuantityInKilo() == 0d && rowPanel.get(i).getQuantityInSack() == 0d) {
				msg = "Both quantities should not be 0 in row " + (i + 1) + " ";

				return true;
			}
		}

		return false;
	}

	private void determineDateStatus() {

		formDate = ((SpinnerDateModel) date.getModel()).getDate();

		try {
			if (!Manager.getInstance().getInventorySheetDataManager().isValidFor(formDate)) {
				String str = Manager.getInstance().getInventorySheetDataManager().getValidityRemarksFor(formDate);
				dateStatus.setIconToolTip(new ImageIcon("images/invalid_date2.png"), str, false);
				error.setToolTip(str);
			}

			else {
				dateStatus.setIconToolTip(new ImageIcon("images/valid_date.png"), Values.VALID_DATE, true);
				error.setToolTip("");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean hasInvalidQuantity() {

		for (int i = 0; i < rowPanel.size(); i++) {

			if (rowPanel.get(i).getQuantityInSack() > rowPanel.get(i).getSelectedProduct().getTotalQuantityInSack()) {

				msg = "Invalid sack qty. Only " + rowPanel.get(i).getSelectedProduct().getTotalQuantityInSack() + " left for product in row " + (i + 1)
						+ ". ";

				return true;
			}

			if (rowPanel.get(i).getQuantityInKilo() > rowPanel.get(i).getSelectedProduct().getTotalQuantityInKilo()) {
				msg = "Invalid sack kg. Only " + rowPanel.get(i).getSelectedProduct().getTotalQuantityInKilo() + " left for product in row " + (i + 1)
						+ ". ";

				return true;
			}

			if (rowPanel.get(i).getQuantityInKilo() + (rowPanel.get(i).getQuantityInSack() * rowPanel.get(i).getSelectedProduct().getKilosPerSack()) > rowPanel
					.get(i).getSelectedProduct().getTotalQuantityInKilo()) {
				msg = "Invalid qty inputs in row " + (i + 1) + ". Total qty would exceed the total qty in kilos ("
						+ rowPanel.get(i).getSelectedProduct().getTotalQuantityInKilo() + ") of the product. ";

				return true;
			}
		}

		return false;
	}

	private boolean isValidated() {

		formDate = ((SpinnerDateModel) date.getModel()).getDate();

		try {
			if (!Manager.getInstance().getInventorySheetDataManager().isValidFor(formDate)) {

				msg = Manager.getInstance().getInventorySheetDataManager().getValidityRemarksFor(formDate);

				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (customerCombo.getModel().getSelectedItem() == null) {

			msg = "Customer is required ";

			return false;

		}

		if (amount.getText().equals("") && productsPanel.getComponentCount() == 0) {

			msg = "Either input an amount OR put at least one product ";

			return false;
		}

		return true;

	}

	private void clearForm() {
		productsPanel.removeAll();
		rowPanel.clear();
		refreshDate();

		error.setToolTip("");

		customerCombo.setSelectedIndex(-1);
	}

	public void refreshDate() {
		date.setValue(new Date());
	}

	public void refreshCustomer(boolean remove) {

		if (remove)
			panel.remove(customerCombo);

		try {
			model = new DefaultComboBoxModel(Manager.getInstance().getEmployeePersonManager().getCustomersOnly().toArray());
			customerCombo = new JComboBox(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		customerCombo.setUI(ColorArrowUI.createUI(this));
		customerCombo.setEditable(true);
		customerCombo.setSelectedIndex(-1);
		customerComboField = (JTextField) customerCombo.getEditor().getEditorComponent();
		customerComboField.setText("");
		customerComboField.setOpaque(false);
		customerComboField.setBorder(BorderFactory.createEmptyBorder());
		customerComboField.addKeyListener(new ComboKeyHandler(customerCombo));

		customerCombo.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
		customerCombo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		customerCombo.setOpaque(false);
		customerCombo.setSelectedIndex(-1);
		customerCombo.setBounds(115, 78, 180, 20);

		panel.add(customerCombo);
	}

	public void setProductQuantities(double qtySack, double qtyKG) {
		this.qtySack = qtySack;
		this.qtyKG = qtyKG;
	}

	private void enableAmountField(boolean val) {
		amount.setEnabled(val);
		amount.setText("");
	}

}
