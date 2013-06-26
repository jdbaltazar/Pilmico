package gui.forms.edit;

import util.EditFormPanel;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class EditStockPurchase extends EditFormPanel{

	public EditStockPurchase() {
		super("woohoo");
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;
	private JPanel itemsPanel;
	private JScrollPane itemsPane;
	private JComboBox supplierCombo;
	private final int ROW_WIDTH = 350, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 85, UPPER_Y = 53, ITEMS_PANE_Y = 110;
	private JTextField supplierComboField;

	private ArrayList<RowPanel> rowPanel2 = new ArrayList<RowPanel>();
	private JButton addRow;
	private TableHeaderLabel quantityLabel, itemLabel, deleteLabel;
	private JLabel supplierLabel;
	private JSpinner date;
	private ImageIcon icon;
	private SoyButton edit;
	private DefaultComboBoxModel model;

	private StockPurchase stockPurchase;

	private ErrorLabel error;
	
	@SuppressWarnings("unused")
	private String msg = "";

	public EditStockPurchase(StockPurchase stockPurchase) {
		super("View Stock Purchase Form");
		this.stockPurchase = stockPurchase;
		init();
		addComponents();
		setRequestFocusEnabled(false);

		Values.editStockPurchase = this;
	}

	private void init() {
		
		setLayout(null);

		icon = new ImageIcon("images/util.png");

		date = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(date, "MMM dd, yyyy hh:mm a");
		date.setEditor(timeEditor2);
		date.setValue(stockPurchase.getDate());
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
			model = new DefaultComboBoxModel(Manager.supplierManager.getSuppliers().toArray());
			supplierCombo = new JComboBox(model);
		} catch (Exception e) {
			e.printStackTrace();
		}

		supplierCombo.setEditable(true);
		supplierComboField = (JTextField) supplierCombo.getEditor().getEditorComponent();
		supplierComboField.setText("");
		supplierComboField.addKeyListener(new ComboKeyHandler(supplierCombo));
		supplierCombo.setSelectedIndex(-1);

		itemsPanel = new JPanel();
		itemsPanel.setLayout(null);
		itemsPanel.setOpaque(false);

		supplierLabel = new JLabel("Supplier:");
		quantityLabel = new TableHeaderLabel("Quantity");
		itemLabel = new TableHeaderLabel("Stocks");
		deleteLabel = new TableHeaderLabel(icon);

		date.setBounds(640, 12, 150, 20);

		supplierCombo.setBounds(303, UPPER_Y, 260, 20);
		supplierCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		itemsPane = new JScrollPane(itemsPanel);

		itemsPane.setBounds(230, ITEMS_PANE_Y, ROW_WIDTH, 215);
//		itemsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		itemsPane.setOpaque(false);
		itemsPane.getViewport().setOpaque(false);
		itemsPane.setBorder(BorderFactory.createEmptyBorder());

		supplierLabel.setBounds(233, UPPER_Y, 80, 20);
		supplierLabel.setFont(new Font("Harabara", Font.PLAIN, 16));

		quantityLabel.setBounds(231, LABEL_Y, 77, LABEL_HEIGHT);
		
//		itemLabel.setBounds(308, LABEL_Y, 215, LABEL_HEIGHT);
		
		itemLabel.setBounds(308, LABEL_Y, 257, LABEL_HEIGHT);

		deleteLabel.setBounds(523, LABEL_Y, 42, LABEL_HEIGHT);

		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");
		addRow.setBounds(200, LABEL_Y, 24, 24);

		addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				rowPanel2.add(new RowPanel(itemsPanel.getComponentCount() * ROW_HEIGHT, itemsPanel.getComponentCount() + "", null, 1, Values.EDIT));
				itemsPanel.add(rowPanel2.get(rowPanel2.size() - 1));

				itemsPanel.setPreferredSize(new Dimension(330, itemsPanel.getComponentCount() * ROW_HEIGHT));
				itemsPanel.updateUI();
				itemsPanel.revalidate();

				Rectangle rect = new Rectangle(0, (int) itemsPanel.getPreferredSize().getHeight(), 10, 10);
				itemsPanel.scrollRectToVisible(rect);
			}
		});

		int total = supplierCombo.getItemCount();

		while (total > 0) {
			total--;
			Supplier s = (Supplier) supplierCombo.getItemAt(total);
			if (s.getId() == stockPurchase.getSupplier().getId()) {
				supplierCombo.setSelectedIndex(total);
				break;
			}
		}

		Set<StockPurchaseDetail> details = stockPurchase.getStockPurchaseDetails();

		System.out.println("=================================");
		for (StockPurchaseDetail spd : details) {

			System.out.println("================>row for item id: " + spd.getItem().getId() + ": " + spd.getItem().getName());

			rowPanel2.add(new RowPanel(itemsPanel.getComponentCount() * ROW_HEIGHT, itemsPanel.getComponentCount() + "", spd.getItem(), spd
					.getQuantity(), Values.EDIT));
			itemsPanel.add(rowPanel2.get(rowPanel2.size() - 1));

			itemsPanel.setPreferredSize(new Dimension(330, itemsPanel.getComponentCount() * ROW_HEIGHT));
			itemsPanel.updateUI();
			itemsPanel.revalidate();

			Rectangle rect = new Rectangle(0, (int) itemsPanel.getPreferredSize().getHeight(), 10, 10);
			itemsPanel.scrollRectToVisible(rect);
		}

		add(date);

		add(supplierCombo);

		add(supplierLabel);
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

		for (int i = removedRow + 1; i < rowPanel2.size(); i++) {
			rowPanel2.get(i).setBounds(0, rowPanel2.get(i).getY() - ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT);
			rowPanel2.get(i).setY(rowPanel2.get(i).getY() - ROW_HEIGHT);
			rowPanel2.get(i).getDeleteRow().setActionCommand((i - 1) + "");
			rowPanel2.get(i).updateUI();
			rowPanel2.get(i).revalidate();
		}

		rowPanel2.remove(removedRow);

		System.out.println("rowpanel2 size: " + rowPanel2.size());
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
				// Supplier s = (Supplier)
				// supplierCombo.getModel().getSelectedItem();
				// StoreInfo storeInfo = Manager.storeManager.getStoreInfo();
				// stockPurchase.setDate(d);
				// stockPurchase.setSupplier(s);
				// stockPurchase.setStoreInfo(storeInfo);
				//
				// stockPurchase.removeAllStockPurchaseDetails();
				//
				// for (RowPanel spr : rowPanel2) {
				// stockPurchase.addStockPurchaseDetail(new
				// StockPurchaseDetail(stockPurchase, (Item) spr.getSelectedItem(),
				// spr.getQuantity()));
				// }
				//
				// Manager.stockPurchaseManager.updateStockPurchase(stockPurchase);
				//
				// Account ac = Manager.loggedInAccount;
				// String str = ac.getAccountType() + " " + ac.getFirstAndLAstName()
				// + " updated stock purchase no " + stockPurchase.getId()
				// + " from supplier " + stockPurchase.getSupplier().getId() + ": "
				// + stockPurchase.getSupplier().getName() + " with "
				// + stockPurchase.getStockPurchaseDetails().size() + " lines.";
				//
				// String str2 = "";
				// Set<StockPurchaseDetail> ds =
				// stockPurchase.getStockPurchaseDetails();
				// if (ds.size() > 0) {
				// str2 = " Quantity of item(s): ";
				// int total = ds.size();
				// for (StockPurchaseDetail spd : ds) {
				// total--;
				// str2 = str2 + spd.getItem().getId();
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
				//
				// } catch (Exception e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
				// } else
				// error.setText(msg);
			}
		});

		//add(edit);
		add(error);

	}

	@SuppressWarnings("unused")
	private void update() {
		Values.editPanel.startAnimation();
		new SuccessPopup("Edit").setVisible(true);
		Values.centerPanel.changeTable(Values.PURCHASE);
		Values.topPanel.refreshStockCost();
	}

	@SuppressWarnings("unused")
	private boolean isValidated() {

		if (supplierCombo.getModel().getSelectedItem() == null) {

			msg = "Select a supplier";

			return false;

		}

		if (itemsPanel.getComponentCount() == 0) {

			msg = "Put at least one item";

			return false;
		}

		return true;

	}*/

	// private void fillValues() {
	//
	// rowPanel2.add(new RowPanel(itemsPanel.getComponentCount() * ROW_HEIGHT,
	// itemsPanel.getComponentCount() + "", -1 /* id */, 1 /* quantity */));
	// itemsPanel.add(rowPanel2.get(rowPanel2.size() - 1));
	//
	// itemsPanel.setPreferredSize(new Dimension(330,
	// itemsPanel.getComponentCount() * ROW_HEIGHT));
	// itemsPanel.updateUI();
	// itemsPanel.revalidate();
	//
	// Rectangle rect = new Rectangle(0, (int)
	// itemsPanel.getPreferredSize().getHeight(), 10, 10);
	// itemsPanel.scrollRectToVisible(rect);
	//
	// }

}
