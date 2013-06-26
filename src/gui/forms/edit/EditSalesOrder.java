package gui.forms.edit;

import util.EditFormPanel;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class EditSalesOrder extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EditSalesOrder() {
		// TODO Auto-generated constructor stub
		super("yow");
	}
	/*
	private JPanel itemsPanel;
	private JScrollPane itemsPane;
	private JComboBox accountCombo;
	private final int ROW_WIDTH = 350, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 85, UPPER_Y = 53, ITEMS_PANE_Y = 110;
	private JTextField accountComboField;

	private ArrayList<RowPanel> itemRowPanel = new ArrayList<RowPanel>();
	private JButton addRow;
	@SuppressWarnings("unused")
	private TableHeaderLabel quantityLabel, itemLabel, deleteLabel;
	private JLabel accountLabel;
	private JSpinner date;
	private ImageIcon icon;
	private SoyButton edit;
	private DefaultComboBoxModel model;
	private Object[] array = {};

	private SalesOrder salesOrder;

	private ErrorLabel error;
	
	@SuppressWarnings("unused")
	private String msg = "";

	public EditSalesOrder(SalesOrder salesOrder) {
		super("View Sales Order Form");
		this.salesOrder = salesOrder;
		init();
		addComponents();
		setRequestFocusEnabled(false);

		Values.editSalesOrder = this;
	}

	
	private void init() {
		
		setLayout(null);

		icon = new ImageIcon("images/util.png");
		date = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(date, "MMM dd, yyyy hh:mm a");
		date.setEditor(timeEditor2);
		date.setValue(salesOrder != null ? salesOrder.getDate() : new Date());
		date.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		date.setBorder(BorderFactory.createEmptyBorder());

		JComponent editor = date.getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			JSpinner.DefaultEditor defEditor = (JSpinner.DefaultEditor) editor;
			JFormattedTextField tf = defEditor.getTextField();
			if (tf != null) {
				tf.setForeground(new Color(25, 117, 117));
				tf.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}

		try {
			array = Manager.accountManager.getAccounts().toArray();
		} catch (Exception e) {
			e.printStackTrace();
		}

		model = new DefaultComboBoxModel(array);
		accountCombo = new JComboBox(model);
		accountCombo.setEditable(true);
		accountCombo.setSelectedIndex(-1);
		accountComboField = (JTextField) accountCombo.getEditor().getEditorComponent();
		accountComboField.setText("");
		accountComboField.addKeyListener(new ComboKeyHandler(accountCombo));

		itemsPanel = new JPanel();
		itemsPanel.setLayout(null);
		itemsPanel.setOpaque(false);

		accountLabel = new JLabel("Account:");
		quantityLabel = new TableHeaderLabel("Quantity");
		itemLabel = new TableHeaderLabel("Stocks");
		deleteLabel = new TableHeaderLabel(icon);

		// quantityLabel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		// itemLabel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		// deleteLabel.setBorder(BorderFactory.createRaisedSoftBevelBorder());

		
		 * itemsPanel.add(addRow()); itemsPanel.add(addRow());
		 * itemsPanel.add(addRow());
		 

		
		 * rowPanel2.get(0).updateUI(); rowPanel2.get(0).revalidate();
		 * rowPanel2.get(0).repaint();
		 
		// itemsPanel.updateUI();
		// itemsPanel.revalidate();

		date.setBounds(640, 12, 150, 20);

		accountCombo.setBounds(303, UPPER_Y, 200, 20);
		accountCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		itemsPane = new JScrollPane(itemsPanel);

		itemsPane.setBounds(230, ITEMS_PANE_Y, ROW_WIDTH, 215);
//		itemsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		itemsPane.setOpaque(false);
		itemsPane.getViewport().setOpaque(false);
		
		itemsPane.setBorder(BorderFactory.createEmptyBorder());

		accountLabel.setBounds(233, UPPER_Y, 80, 20);
		accountLabel.setFont(new Font("Harabara", Font.PLAIN, 16));

		quantityLabel.setBounds(231, LABEL_Y, 77, LABEL_HEIGHT);

//		itemLabel.setBounds(308, LABEL_Y, 215, LABEL_HEIGHT);

//		deleteLabel.setBounds(523, LABEL_Y, 42, LABEL_HEIGHT);
		
		itemLabel.setBounds(308, LABEL_Y, 257, LABEL_HEIGHT);

		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");
		addRow.setBounds(200, LABEL_Y, 24, 24);

		addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				itemRowPanel.add(new RowPanel(itemsPanel.getComponentCount() * ROW_HEIGHT, itemsPanel.getComponentCount() + "", null, 1, Values.EDIT));
				itemsPanel.add(itemRowPanel.get(itemRowPanel.size() - 1));

				itemsPanel.setPreferredSize(new Dimension(330, itemsPanel.getComponentCount() * ROW_HEIGHT));
				itemsPanel.updateUI();
				itemsPanel.revalidate();

				Rectangle rect = new Rectangle(0, (int) itemsPanel.getPreferredSize().getHeight(), 10, 10);
				itemsPanel.scrollRectToVisible(rect);
			}
		});

		int total = accountCombo.getItemCount();

		if (salesOrder == null) {
			accountCombo.setSelectedIndex(-1);
		} else {
			while (total > 0) {
				total--;
				Account a = (Account) accountCombo.getItemAt(total);
				if (a.getId() == salesOrder.getAccount().getId()) {
					accountCombo.setSelectedIndex(total);
					break;
				}
			}
		}

		Set<SalesOrderDetail> details = salesOrder.getSalesOrderDetails();

		System.out.println("this was called!!!!!!!!!!!");
		for (SalesOrderDetail sod : details) {

			System.out.println("row for item id: " + sod.getItem().getId());

			itemRowPanel.add(new RowPanel(itemsPanel.getComponentCount() * ROW_HEIGHT, itemsPanel.getComponentCount() + "", sod.getItem(), sod
					.getQuantity(), Values.EDIT));
			itemsPanel.add(itemRowPanel.get(itemRowPanel.size() - 1));

			itemsPanel.setPreferredSize(new Dimension(330, itemsPanel.getComponentCount() * ROW_HEIGHT));
			itemsPanel.updateUI();
			itemsPanel.revalidate();

			Rectangle rect = new Rectangle(0, (int) itemsPanel.getPreferredSize().getHeight(), 10, 10);
			itemsPanel.scrollRectToVisible(rect);
		}

		add(date);

		add(accountCombo);

		add(accountLabel);
		add(quantityLabel);
		add(itemLabel);
//		add(deleteLabel);

		add(itemsPane);

//		add(addRow);
	}

	public void removeRow(int rowNum) {
		System.out.println("pressed row button: " + rowNum);

		itemsPanel.remove(rowNum);
		itemsPanel.updateUI();
		itemsPanel.revalidate();

		itemsPanel.setPreferredSize(new Dimension(330, itemsPanel.getComponentCount() * ROW_HEIGHT));

		updateList(rowNum);
	}

	private void updateList(int removedRow) {

		for (int i = removedRow + 1; i < itemRowPanel.size(); i++) {
			itemRowPanel.get(i).setBounds(0, itemRowPanel.get(i).getY() - ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT);
			itemRowPanel.get(i).setY(itemRowPanel.get(i).getY() - ROW_HEIGHT);
			itemRowPanel.get(i).getDeleteRow().setActionCommand((i - 1) + "");
			itemRowPanel.get(i).updateUI();
			itemRowPanel.get(i).revalidate();
		}

		itemRowPanel.remove(removedRow);

		System.out.println("rowpanel2 size: " + itemRowPanel.size());
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		edit = new SoyButton("Edit");

		edit.setBounds(370, 350, 80, 30);

		error = new ErrorLabel();

		error.setBounds(505, 330, 200, 30);

		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				// if (isValidated()) {
				// try {
				// Date d = ((SpinnerDateModel) date.getModel()).getDate();
				// Account acc = (Account) accountCombo.getSelectedItem();
				// salesOrder.setDate(d);
				// salesOrder.setAccount(acc);
				// salesOrder.removeAllSalesOrderDetails();
				// for (RowPanel sor : itemRowPanel) {
				// salesOrder.addSalesOrderDetail(new SalesOrderDetail(salesOrder,
				// sor.getQuantity(), (Item) sor.getSelectedItem()));
				// }
				//
				// Manager.salesOrderManager.updateSalesOrder(salesOrder);
				//
				// Account ac = Manager.loggedInAccount;
				// String str = ac.getAccountType() + " " + ac.getFirstAndLAstName()
				// + " updated sales order no " + salesOrder.getId()
				// + " for account " + salesOrder.getAccount().getId() + ": " +
				// salesOrder.getAccount().getFirstAndLAstName() + " with "
				// + salesOrder.getSalesOrderDetails().size() + " lines.";
				//
				// String str2 = "";
				// Set<SalesOrderDetail> ds = salesOrder.getSalesOrderDetails();
				// if (ds.size() > 0) {
				// str2 = " Quantity of item(s): ";
				// int total = ds.size();
				// for (SalesOrderDetail sod : ds) {
				// total--;
				// str2 = str2 + sod.getItem().getId();
				// if (total > 0) {
				// str2 = str2 + ", ";
				// } else {
				// str2 = str2 + " updated";
				// }
				// }
				//
				// }
				// str = str + str2;
				// Log log = new Log(str);
				// Manager.logManager.addLog(log);
				//
				// update();
				// } catch (Exception e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
				// } else
				// error.setText(msg);

			}
		});

	//	add(edit);
		add(error);

	}

	
	@SuppressWarnings("unused")
	private void update() {
		Values.editPanel.startAnimation();
		new SuccessPopup("Edit").setVisible(true);
		Values.centerPanel.changeTable(Values.SALES_ORDER);
		Values.topPanel.refreshStockCost();
	}

	@SuppressWarnings("unused")
	private boolean isValidated() {

		if (accountCombo.getModel().getSelectedItem() == null) {

			msg = "Select an account";

			return false;

		}

		if (itemsPanel.getComponentCount() == 0) {

			msg = "Put at least one item";

			return false;
		}

		return true;

	}*/
}
