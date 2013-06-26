package gui.forms.add;

import util.SimplePanel;

public class SalesOrderForm extends SimplePanel {

	public SalesOrderForm() {
		super("label");
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	/*private JPanel itemsPanel, row, p;
	private JScrollPane itemsPane;
	private JComboBox itemCombo, accountCombo;
	private final int ROW_WIDTH = 350, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 95, UPPER_Y = 63, ITEMS_PANE_Y = 120;
	private Object[] array = {};
	private JTextField itemComboField, accountComboField;
	private JScrollBar sb;

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private JTextField quantity;
	private JButton deleteRow, addRow;
	private JLabel accountLabel;
	private TableHeaderLabel quantityLabel, itemLabel, deleteLabel;
	private JSpinner date;
	private ImageIcon icon;
	private SoyButton save;
	private DefaultComboBoxModel model;

	private ErrorLabel error;
	private String msg = "";

	public SalesOrderForm() {
		// TODO Auto-generated constructor stub
		super("Add Sales Order Form");
		init();
		addComponents();

		Values.salesOrderForm = this;
	};

	private void init() {

		icon = new ImageIcon("images/util.png");

		date = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(date, "MMM dd, yyyy hh:mm a");
		date.setEditor(timeEditor2);
		date.setValue(new Date());
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
			// TODO Auto-generated catch block
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

		date.setBounds(280, 12, 150, 20);

		accountCombo.setBounds(123, UPPER_Y, 200, 20);
		accountCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		itemsPane = new JScrollPane(itemsPanel);

		itemsPane.setBounds(53, ITEMS_PANE_Y, ROW_WIDTH, 215);
		itemsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		itemsPane.setOpaque(false);
		itemsPane.getViewport().setOpaque(false);

		accountLabel.setBounds(53, UPPER_Y, 80, 20);
		accountLabel.setFont(new Font("Harabara", Font.PLAIN, 16));

		quantityLabel.setBounds(51, LABEL_Y, 77, LABEL_HEIGHT);

		itemLabel.setBounds(128, LABEL_Y, 215, LABEL_HEIGHT);

		deleteLabel.setBounds(343, LABEL_Y, 42, LABEL_HEIGHT);

		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");
		addRow.setBounds(20, 95, 24, 24);

		addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				rowPanel.add(new RowPanel(itemsPanel.getComponentCount() * ROW_HEIGHT, itemsPanel.getComponentCount() + "", null, 1, Values.ADD));
				itemsPanel.add(rowPanel.get(rowPanel.size() - 1));

				itemsPanel.setPreferredSize(new Dimension(330, itemsPanel.getComponentCount() * ROW_HEIGHT));
				itemsPanel.updateUI();
				itemsPanel.revalidate();

				Rectangle rect = new Rectangle(0, (int) itemsPanel.getPreferredSize().getHeight(), 10, 10);
				itemsPanel.scrollRectToVisible(rect);
			}
		});

		int total = accountCombo.getItemCount();
		while (total > 0) {
			total--;
			Account a = (Account) accountCombo.getItemAt(total);
			if (a.getId() == Manager.loggedInAccount.getId()) {
				accountCombo.setSelectedIndex(total);
				total = 0;
			}
		}

		add(date);
		add(accountCombo);
		add(accountLabel);
		add(quantityLabel);
		add(itemLabel);
		add(deleteLabel);
		add(itemsPane);
		add(addRow);
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

		save.setBounds(190, 350, 80, 30);

		error.setBounds(305, 340, 200, 30);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (isValidated()) {
					try {
						Date d = ((SpinnerDateModel) date.getModel()).getDate();
						Account acc = (Account) accountCombo.getSelectedItem();
						SalesOrder so = new SalesOrder(d, acc);

						boolean valid = true;

						// error-trapping here
						int i = 0;
						for (RowPanel sor : rowPanel) {
							i++;
							Item item = (Item) sor.getSelectedItem();
							if (sor.getQuantity() > item.getUnitsOnStock()) {
								Toolkit.getDefaultToolkit().beep();
								
								JOptionPane.showMessageDialog(null,
										"Error in row " + i + ": only " + item.getUnitsOnStock() +" "+ item.getUnit().getName() + " of item \n"
												+ item.getName() + " left. Purchase new stocks\nor update quantity of item",
									    "System Error",
									    JOptionPane.ERROR_MESSAGE);
								
								valid = false;
							}
						}

						if (valid) {

							for (RowPanel sor : rowPanel) {
								so.addSalesOrderDetail(new SalesOrderDetail(so, sor.getQuantity(), (Item) sor.getSelectedItem()));
							}

							Manager.salesOrderManager.addSalesOrder(so);

							// update quantity of items
							Set<SalesOrderDetail> sods = so.getSalesOrderDetails();
							for (SalesOrderDetail sod : sods) {
								Item item = sod.getItem();
								item.setUnitsOnStock(item.getUnitsOnStock() - sod.getQuantity());
								Manager.itemManager.updateItem(item);
							}

							Account ac = Manager.loggedInAccount;
							String str = ac.getAccountType() + " " + ac.getFirstAndLAstName() + " added sales order no " + so.getId() + " for account "
									+ so.getAccount().getId() + ": " + so.getAccount().getFirstAndLAstName() + " with " + so.getSalesOrderDetails().size()
									+ " lines.";

							String str2 = "";
							Set<SalesOrderDetail> ds = so.getSalesOrderDetails();
							if (ds.size() > 0) {
								str2 = " Quantity of item(s): ";
								int total = ds.size();
								for (SalesOrderDetail sod : ds) {
									total--;
									str2 = str2 + sod.getItem().getId();
									if (total > 0) {
										str2 = str2 + ", ";
									} else {
										str2 = str2 + " updated";
									}
								}

							}
							str = str + str2;
							Log log = new Log(str);
							Manager.logManager.addLog(log);

							new SuccessPopup("Add").setVisible(true);
							clearForm();
							Values.centerPanel.changeTable(Values.SALES_ORDER);
							Values.topPanel.refreshStockCost();
							
						} else {
							System.out.println("Cannot add sales order! ");
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					error.setText(msg);
			}
		});

		add(save);
		add(error);

	}

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

	}

	private void clearForm() {
		itemsPanel.removeAll();
		rowPanel.clear();
		refreshDate();

		error.setText("");

		accountCombo.setSelectedIndex(-1);
	}

	public void refreshDate() {
		date.setValue(new Date());
	}

	public void refreshAccount() {

		try {
			model = new DefaultComboBoxModel(Manager.accountManager.getAccounts().toArray());

			accountCombo.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		accountCombo.setSelectedIndex(-1);
	}*/

}
