package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.RowPanel;
import gui.forms.util.FormDropdown.ColorArrowUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

import common.entity.delivery.Delivery;
import common.entity.delivery.DeliveryDetail;
import common.entity.product.Product;
import common.entity.store.Store;
import common.entity.supplier.Supplier;
import common.manager.Manager;

public class DeliveryForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel productsPanel, row, p;
	private JScrollPane productsPane;
	private JComboBox itemCombo, supplierCombo;
	private int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y, ITEMS_PANE_Y = 116; // 125
	private Object[] array = {};
	private JTextField itemComboField, supplierComboField;
	private JScrollBar sb;

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private JButton addRow;
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, priceKG, priceSACK, productLabel, deleteLabel;
	private SpinnerDate date;
	private ImageIcon icon;
	private SoyButton save;
	private JLabel receivedBy;
	private MainFormField terms, po_no, delivery_no;
	private MainFormLabel termsLabel, ponumLabel, deliveryNumLabel, dateLabel, receivedByLabel, supplierLabel;

	private DefaultComboBoxModel model;
	private FormCheckbox showRequired;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";
	private SBButton supplierFwd, productFwd;

	public DeliveryForm() {
		// TODO Auto-generated constructor stub
		super("Add Delivery Form");
		init();
		addComponents();

		Values.deliveryForm = this;
	};

	private void init() {

		supplierFwd = new SBButton("forward.png", "forward.png", "Add new supplier");
		productFwd = new SBButton("forward.png", "forward.png", "Add new product");
		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");
		
		supplierFwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.SUPPLIERS);
			}
		});
		
		productFwd.addActionListener(new ActionListener() {
			
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

		showRequired = new FormCheckbox("Show required fields only");

		icon = new ImageIcon("images/util.png");

		date = new SpinnerDate("MMM dd, yyyy hh:mm a");

		termsLabel = new MainFormLabel("Terms:");
		ponumLabel = new MainFormLabel("PO_No:");
		deliveryNumLabel = new MainFormLabel("Delivery No.:");
		dateLabel = new MainFormLabel("Date:");

		receivedByLabel = new MainFormLabel("Received by:");
		supplierLabel = new MainFormLabel("Supplier:");

		receivedBy = new JLabel(Manager.loggedInAccount.getEmployee().getFirstPlusLastName());
		receivedBy.setOpaque(false);
		receivedBy.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		receivedBy.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		receivedBy.setHorizontalAlignment(JLabel.CENTER);

		terms = new MainFormField(200);
		po_no = new MainFormField(20);
		delivery_no = new MainFormField(30);

		quantityKGLabel = new TableHeaderLabel("Qtty (kg)");
		productLabel = new TableHeaderLabel("Products");
		quantitySACKlabel = new TableHeaderLabel("Qtty (sack)");
		priceKG = new TableHeaderLabel("Price (kg)");
		priceSACK = new TableHeaderLabel("Price (sack)");
		deleteLabel = new TableHeaderLabel(icon);

		model = new DefaultComboBoxModel(array);
		supplierCombo = new JComboBox(model);
		supplierCombo.setEditable(true);
		supplierCombo.setSelectedIndex(-1);
		supplierComboField = (JTextField) supplierCombo.getEditor().getEditorComponent();
		supplierComboField.setText("");
		supplierComboField.setOpaque(false);
		supplierComboField.setBorder(BorderFactory.createEmptyBorder());
		supplierComboField.addKeyListener(new ComboKeyHandler(supplierCombo));

		supplierCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		supplierCombo.setUI(ColorArrowUI.createUI(this));
		supplierCombo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		supplierCombo.setOpaque(false);

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

		dateLabel.setBounds(49, 12, 40, 20); // 15
		date.setBounds(88, 10, 150, 20);

		receivedByLabel.setBounds(305, 12, 85, 20);// 260
		receivedBy.setBounds(378, 10, 200, 20);

		ponumLabel.setBounds(36, 35, 50, 20);
		po_no.setBounds(86, 32, 70, 20);

		deliveryNumLabel.setBounds(304, 35, 100, 20); // 260
		delivery_no.setBounds(386, 32, 140, 20);

		supplierLabel.setBounds(27, 58, 70, 20);
		supplierCombo.setBounds(85, 56, 220, 20);
		supplierFwd.setBounds(308, 58, 16, 16);

		termsLabel.setBounds(335, 58, 50, 20);
		terms.setBounds(385, 56, 210, 20);

		setupTable(50, false);

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

		showUnrequired(false);

		add(showRequired);

		List<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			suppliers = Manager.supplierManager.getSuppliers();
			for (Supplier s : suppliers) {
				supplierCombo.addItem(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		supplierCombo.setSelectedIndex(-1);

		panel.add(dateLabel);
		panel.add(date);

		panel.add(receivedByLabel);
		panel.add(receivedBy);

		panel.add(ponumLabel);
		panel.add(po_no);

		panel.add(deliveryNumLabel);
		panel.add(delivery_no);

		// panel.add(issuedOnLabel);
		// panel.add(issueDate);

		panel.add(supplierLabel);
		panel.add(supplierCombo);
		panel.add(supplierFwd);

		panel.add(termsLabel);
		panel.add(terms);

		panel.add(productFwd);
		panel.add(quantityKGLabel);
		panel.add(quantitySACKlabel);
		panel.add(priceKG);
		panel.add(priceSACK);
		panel.add(productLabel);
		panel.add(deleteLabel);

		panel.add(addRow);

		panel.add(productsPane);

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
		productLabel.setBounds(346, LABEL_Y, 207, LABEL_HEIGHT);
		deleteLabel.setBounds(553, LABEL_Y, 42, LABEL_HEIGHT);

		productFwd.setBounds(482, LABEL_Y + 5, 16, 16);

		if (shownFields) {
			productsPane.setBounds(31, ITEMS_PANE_Y, ROW_WIDTH, 140);
		} else {
			productsPane.setBounds(31, 75, ROW_WIDTH, 175);
		}

	}

	private void showUnrequired(boolean show) {
		supplierLabel.setVisible(show);
		supplierCombo.setVisible(show);

		ponumLabel.setVisible(show);
		po_no.setVisible(show);

		termsLabel.setVisible(show);
		terms.setVisible(show);

		deliveryNumLabel.setVisible(show);
		delivery_no.setVisible(show);

		supplierFwd.setVisible(show);
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
		// TODO Auto-generated method stub
		save = new SoyButton("Save");

		error = new ErrorLabel();

		save.setBounds(275, 275, 80, 30);

		error.setBounds(305, 340, 200, 30);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				try {
					Date d = ((SpinnerDateModel) date.getModel()).getDate();
					Supplier supplier = (Supplier) supplierCombo.getSelectedItem();
					Store store;
					store = Manager.storeManager.getStore();
					Delivery delivery = new Delivery(d, supplier, store, delivery_no.getText(), po_no.getText(), terms.getText(), Manager.loggedInAccount);

					for (RowPanel rp : rowPanel) {
						Product p = rp.getSelectedProduct();
						System.out.println("qty in kilo: " + rp.getQuantityInKilo());
						System.out.println("qty in sack: " + rp.getQuantityInSack());
						delivery.addDeliveryDetail(new DeliveryDetail(delivery, p, p.getPricePerKilo(), p.getPricePerSack(), rp.getQuantityInKilo(), rp
								.getQuantityInSack()));
					}

					Manager.deliveryManager.addDelivery(delivery);
					System.out.println("delivery saved");

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		panel.add(save);
		add(error);

	}

	private boolean isValidated() {

		if (supplierCombo.getModel().getSelectedItem() == null) {

			msg = "Select an account";

			return false;

		}

		if (productsPanel.getComponentCount() == 0) {

			msg = "Put at least one item";

			return false;
		}

		return true;

	}

	private void clearForm() {
		productsPanel.removeAll();
		rowPanel.clear();
		refreshDate();

		error.setText("");

		supplierCombo.setSelectedIndex(-1);
	}

	public void refreshDate() {
		date.setValue(new Date());
	}

	public void refreshAccount() {

		try {
			// model = new
			// DefaultComboBoxModel(Manager.accountManager.getAccounts().toArray());

			supplierCombo.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		supplierCombo.setSelectedIndex(-1);
	}
}
