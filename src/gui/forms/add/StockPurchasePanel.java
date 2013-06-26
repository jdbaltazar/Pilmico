package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.RowPanel;
import gui.popup.SuccessPopup;

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
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import util.ErrorLabel;
import util.SBButton;
import util.SimplePanel;
import util.TableHeaderLabel;
import util.Values;
import util.soy.SoyButton;

public class StockPurchasePanel extends SimplePanel {

	public StockPurchasePanel() {
		super("sdadas");
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7854018613112087282L;
	/*private JPanel itemsPanel, row, p;
	private JScrollPane itemsPane;
	private JComboBox supplierCombo;
	private final int ROW_WIDTH = 350, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 95, UPPER_Y = 63, ITEMS_PANE_Y = 120;
	private JTextField supplierComboField;
	private JScrollBar sb;

	private ArrayList<RowPanel> rowPanel2 = new ArrayList<RowPanel>();
	private JButton addRow;
	private JLabel supplierLabel;
	private TableHeaderLabel quantityLabel, itemLabel, deleteLabel;
	private JSpinner date;
	private ImageIcon icon;
	private SoyButton save;
	private DefaultComboBoxModel model;

	private ErrorLabel error;
	private String msg = "";

	public StockPurchasePanel() {
		super("Add Stock Purchase Form");

		init();
		addComponents();
		setRequestFocusEnabled(false);
		Values.stockPurchasePanel = this;
	}

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
			model = new DefaultComboBoxModel(Manager.supplierManager.getSuppliers().toArray());
			supplierCombo = new JComboBox(model);

			supplierCombo.setSelectedIndex(-1);

			try {
				StockPurchase sp = Manager.stockPurchaseManager.getMostREcentStockPurchase();
				if (sp != null) {
					int total = supplierCombo.getItemCount();
					while (total > 0) {
						total--;
						Supplier s = (Supplier) supplierCombo.getItemAt(total);
						if (s.getId() == sp.getSupplier().getId()) {
							supplierCombo.setSelectedIndex(total);
							total = 0;
							break;
						}

					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
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

		supplierCombo.setBounds(123, UPPER_Y, 260, 20);
		supplierCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		itemsPane = new JScrollPane(itemsPanel);

		itemsPane.setBounds(53, ITEMS_PANE_Y, ROW_WIDTH, 215);
		itemsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		itemsPane.setOpaque(false);
		itemsPane.getViewport().setOpaque(false);

		supplierLabel.setBounds(53, UPPER_Y, 80, 20);
		supplierLabel.setFont(new Font("Harabara", Font.PLAIN, 16));

		quantityLabel.setBounds(51, LABEL_Y, 77, LABEL_HEIGHT);

		itemLabel.setBounds(128, LABEL_Y, 215, LABEL_HEIGHT);

		deleteLabel.setBounds(343, LABEL_Y, 42, LABEL_HEIGHT);

		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");
		addRow.setBounds(20, 95, 24, 24);

		addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				rowPanel2.add(new RowPanel(itemsPanel.getComponentCount() * ROW_HEIGHT, itemsPanel.getComponentCount() + "", null, 1, Values.ADD));
				itemsPanel.add(rowPanel2.get(rowPanel2.size() - 1));

				itemsPanel.setPreferredSize(new Dimension(330, itemsPanel.getComponentCount() * ROW_HEIGHT));
				itemsPanel.updateUI();
				itemsPanel.revalidate();

				Rectangle rect = new Rectangle(0, (int) itemsPanel.getPreferredSize().getHeight(), 10, 10);
				itemsPanel.scrollRectToVisible(rect);
			}
		});

		add(date);

		add(supplierCombo);

		add(supplierLabel);
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

		
		 * for(int i = rowNum+1; i < rowPanel2.size(); i++){
		 * rowPanel2.get(i).setCommand((i-1)+""); rowPanel2.get(i).updateUI();
		 * rowPanel2.get(i).revalidate();
		 * rowPanel2.get(i).getDeleteRow().setActionCommand((i-1)+""); }
		 * 
		 * rowPanel2.remove(rowNum);
		 * 
		 * for(int i = 0; i < rowPanel2.size(); i++)
		 * System.out.println("commands after: "
		 * +rowPanel2.get(i).getDeleteRow().getActionCommand());
		 

		
		 * if(rowNum == 0 && rowPanel2.size() == 1) rowPanel2.remove(rowNum); else
		 
		updateList(rowNum);
	}

	private void updateList(int removedRow) {

		for (int i = removedRow + 1; i < rowPanel2.size(); i++) {
			rowPanel2.get(i).setBounds(0, rowPanel2.get(i).getY() - ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT);
			rowPanel2.get(i).setY(rowPanel2.get(i).getY() - ROW_HEIGHT);
			// System.out.println("command: "+rowPanel2.get(i).getCommand());
			rowPanel2.get(i).getDeleteRow().setActionCommand((i - 1) + "");
			rowPanel2.get(i).updateUI();
			rowPanel2.get(i).revalidate();
		}

		rowPanel2.remove(removedRow);
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
						// Supplier s = (Supplier)
						// supplierCombo.getSelectedItem();
						Supplier s = (Supplier) supplierCombo.getModel().getSelectedItem();
						StoreInfo storeInfo = Manager.storeManager.getStoreInfo();
						System.out.println("d1: " + d.toString());
						StockPurchase sp = new StockPurchase(d, s, storeInfo);

						System.out.println("d2: " + sp.getDate().toString());

						for (RowPanel spr : rowPanel2) {
							sp.addStockPurchaseDetail(new StockPurchaseDetail(sp, (Item) spr.getSelectedItem(), spr.getQuantity()));
						}

						Manager.stockPurchaseManager.addStockPurchase(sp);

						// update the quantity of items
						Set<StockPurchaseDetail> spds = sp.getStockPurchaseDetails();
						for (StockPurchaseDetail spd : spds) {
							Item item = spd.getItem();
							item.setUnitsOnStock(item.getUnitsOnStock() + spd.getQuantity());
							Manager.itemManager.updateItem(item);
						}

						Account ac = Manager.loggedInAccount;
						String str = ac.getAccountType() + " " + ac.getFirstAndLAstName() + " added stock purchase no " + sp.getId() + " from supplier "
								+ sp.getSupplier().getId() + ": " + sp.getSupplier().getName() + " with " + sp.getStockPurchaseDetails().size() + " lines.";

						String str2 = "";
						Set<StockPurchaseDetail> ds = sp.getStockPurchaseDetails();
						if (ds.size() > 0) {
							str2 = " Quantity of item(s): ";
							int total = ds.size();
							for (StockPurchaseDetail spd : ds) {
								total--;
								str2 = str2 + spd.getItem().getId();
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
						Values.centerPanel.changeTable(Values.PURCHASE);
						Values.topPanel.refreshStockCost();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				else
					error.setText(msg);

			}
		});

		add(save);
		add(error);

	}

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

	}

	private void clearForm() {
		itemsPanel.removeAll();
		rowPanel2.clear();
		refreshDate();

		error.setText("");

		supplierCombo.setSelectedIndex(-1);

		try {
			StockPurchase sp = Manager.stockPurchaseManager.getMostREcentStockPurchase();
			if (sp != null) {
				int total = supplierCombo.getItemCount();
				while (total > 0) {
					total--;
					Supplier s = (Supplier) supplierCombo.getItemAt(total);
					if (s.getId() == sp.getSupplier().getId()) {
						supplierCombo.setSelectedIndex(total);
						total = 0;
						break;
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void refreshDate() {
		date.setValue(new Date());
	}

	public void refreshSupplier() {

		try {
			model = new DefaultComboBoxModel(Manager.supplierManager.getSuppliers().toArray());
			supplierCombo.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		supplierCombo.setSelectedIndex(-1);

		try {
			StockPurchase sp = Manager.stockPurchaseManager.getMostREcentStockPurchase();
			if (sp != null) {
				int total = supplierCombo.getItemCount();
				while (total > 0) {
					total--;
					Supplier s = (Supplier) supplierCombo.getItemAt(total);
					if (s.getId() == sp.getSupplier().getId()) {
						supplierCombo.setSelectedIndex(total);
						total = 0;
						break;
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/
}
