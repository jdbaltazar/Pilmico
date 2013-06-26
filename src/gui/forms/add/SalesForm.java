package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.FormField;
import gui.forms.util.RowPanel;

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

import util.ErrorLabel;
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
	private JPanel itemsPanel, row, p;
	private JScrollPane productsPane;
	private JComboBox itemCombo, accountCombo;
	private final int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 125, UPPER_Y = 63, ITEMS_PANE_Y = 150;
	private Object[] array = {};
	private JTextField itemComboField, accountComboField;
	private JScrollBar sb;

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private JTextField quantity;
	private JButton deleteRow, addRow;
	private JLabel issuedByLabel, issuedOnLabel;
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, priceKG, priceSACK, productLabel, deleteLabel;
	private SpinnerDate date, issueDate;
	private ImageIcon icon;
	private SoyButton save;
	private FormField issuedAt, rc_no, receipt_no, remarks;
	private DefaultComboBoxModel model;

	private ErrorLabel error;
	private String msg = "";

	public SalesForm() {
		// TODO Auto-generated constructor stub
		super("Add Sales Form");
		init();
		addComponents();

	};

	private void init() {

		icon = new ImageIcon("images/util.png");

		date = new SpinnerDate("MMM dd, yyyy hh:mm a");
		issueDate = new SpinnerDate("MMM dd, yyyy hh:mm a");
		
		issuedAt = new FormField("Issued at", 100, Color.white, Color.GRAY);
		rc_no = new FormField("RC_No", 100, Color.white, Color.GRAY);
		receipt_no = new FormField("Receipt Number", 100, Color.white, Color.GRAY);
		remarks = new FormField("Remarks", 100, Color.white, Color.GRAY);

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

		issuedByLabel = new JLabel("Issued by:");
		issuedOnLabel = new JLabel("Issued On:");
		
		quantityKGLabel = new TableHeaderLabel("Qtty (kg)");
		productLabel = new TableHeaderLabel("Products");
		quantitySACKlabel = new TableHeaderLabel("Qtty (sack)");
		priceKG = new TableHeaderLabel("Price (kg)");
		priceSACK = new TableHeaderLabel("Price (sack)");
		deleteLabel = new TableHeaderLabel(icon);

		date.setBounds(510, 12, 150, 20);

		accountCombo.setBounds(125, UPPER_Y+30, 160, 20);
		accountCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		productsPane = new JScrollPane(itemsPanel);

		productsPane.setBounds(53, ITEMS_PANE_Y, ROW_WIDTH, 150);
		productsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		productsPane.setOpaque(false);
		productsPane.getViewport().setOpaque(false);
		
		rc_no.setBounds(53, UPPER_Y, 75, 22);
		receipt_no.setBounds(135, UPPER_Y, 150, 22);

		issuedByLabel.setBounds(53, UPPER_Y+30, 80, 20);
		
		issuedOnLabel.setBounds(380, UPPER_Y, 80, 20);
		
		issuedAt.setBounds(380, UPPER_Y+30, 230, 22);
		
		issueDate.setBounds(465, UPPER_Y, 150, 20);
		
		remarks.setBounds(430, UPPER_Y+245, 200, 22);
		
		issuedByLabel.setFont(new Font("Harabara", Font.PLAIN, 16));
		issuedOnLabel.setFont(new Font("Harabara", Font.PLAIN, 16));

		quantityKGLabel.setBounds(51, LABEL_Y, 77, LABEL_HEIGHT);
		quantitySACKlabel.setBounds(128, LABEL_Y, 77, LABEL_HEIGHT);
		priceKG.setBounds(205, LABEL_Y, 77, LABEL_HEIGHT);
		priceSACK.setBounds(282, LABEL_Y, 85, LABEL_HEIGHT);

		productLabel.setBounds(367, LABEL_Y, 207, LABEL_HEIGHT);

		deleteLabel.setBounds(574, LABEL_Y, 42, LABEL_HEIGHT);

		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");
		addRow.setBounds(20, 95, 24, 24);

		addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				rowPanel.add(new RowPanel(itemsPanel.getComponentCount() * ROW_HEIGHT, itemsPanel.getComponentCount() + "", 1, Values.ADD));
				itemsPanel.add(rowPanel.get(rowPanel.size() - 1));

				itemsPanel.setPreferredSize(new Dimension(330, itemsPanel.getComponentCount() * ROW_HEIGHT));
				itemsPanel.updateUI();
				itemsPanel.revalidate();

				Rectangle rect = new Rectangle(0, (int) itemsPanel.getPreferredSize().getHeight(), 10, 10);
				itemsPanel.scrollRectToVisible(rect);
			}
		});

		add(date);
		
		add(issuedByLabel);
		add(accountCombo);
		
		add(issuedAt);
		
		
		add(receipt_no);
		add(rc_no);
		add(remarks);
		
		add(issuedOnLabel);
		add(issueDate);
		
		add(quantityKGLabel);
		add(quantitySACKlabel);
		add(priceKG);
		add(priceSACK);
		add(productLabel);
		add(deleteLabel);
		
		add(productsPane);
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

		save.setBounds(200, 315, 80, 30);

		error.setBounds(305, 340, 200, 30);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				/*if (isValidated()) {
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
					error.setText(msg);*/
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
			//model = new DefaultComboBoxModel(Manager.accountManager.getAccounts().toArray());

			accountCombo.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		accountCombo.setSelectedIndex(-1);
	}
}
