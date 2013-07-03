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

import common.entity.accountreceivable.AccountReceivable;
import common.entity.accountreceivable.AccountReceivableDetail;
import common.entity.dailyexpenses.DailyExpensesDetail;
import common.entity.dailyexpenses.Expense;
import common.entity.product.Product;
import common.entity.profile.Person;
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

public class AccountReceivablesForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel productsPanel;
	private JScrollPane productsPane;
	private JComboBox customerCombo;
	private final int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 115, UPPER_Y = 63, ITEMS_PANE_Y = LABEL_HEIGHT + LABEL_Y; // 125
	private Object[] array = {};
	private JTextField customerComboField;
	private JScrollBar sb;

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private JButton addRow;
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, priceKG, priceSACK, productLabel, deleteLabel;
	private SpinnerDate date;
	private ImageIcon icon;
	private SoyButton save;
	private JLabel issuedBy;
	private MainFormField balance;
	private MainFormLabel issuedByLabel, balanceLabel, dateLabel, customerLabel;

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

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		icon = new ImageIcon("images/util.png");

		date = new SpinnerDate("MMM dd, yyyy hh:mm a");

		issuedByLabel = new MainFormLabel("Issued by:");
		balanceLabel = new MainFormLabel("Balance:");
		dateLabel = new MainFormLabel("Date:");

		customerLabel = new MainFormLabel("Customer:");

		issuedBy = new JLabel(Manager.loggedInAccount.getEmployee().getFirstPlusLastName());
		issuedBy.setOpaque(false);
		issuedBy.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		issuedBy.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		issuedBy.setHorizontalAlignment(JLabel.CENTER);

		balance = new MainFormField(200);

		quantityKGLabel = new TableHeaderLabel("Qtty (kg)");
		productLabel = new TableHeaderLabel("Products");
		quantitySACKlabel = new TableHeaderLabel("Qtty (sack)");
		priceKG = new TableHeaderLabel("Price (kg)");
		priceSACK = new TableHeaderLabel("Price (sack)");
		deleteLabel = new TableHeaderLabel(icon);

		model = new DefaultComboBoxModel(array);
		customerCombo = new JComboBox(model);
		customerCombo.setEditable(true);
		customerCombo.setSelectedIndex(-1);
		customerComboField = (JTextField) customerCombo.getEditor().getEditorComponent();
		customerComboField.setText("");
		customerComboField.setOpaque(false);
		customerComboField.setBorder(BorderFactory.createEmptyBorder());
		customerComboField.addKeyListener(new ComboKeyHandler(customerCombo));

		customerCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		customerCombo.setUI(ColorArrowUI.createUI(this));
		customerCombo.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		customerCombo.setOpaque(false);

		productsPanel = new JPanel();
		productsPanel.setLayout(null);
		productsPanel.setOpaque(false);

		productsPane = new JScrollPane(productsPanel);

		productsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		productsPane.setOpaque(false);
		productsPane.getViewport().setOpaque(false);

		dateLabel.setBounds(40, 50, 40, 20);// 15x,12y
		date.setBounds(85, 50, 150, 20);

		issuedByLabel.setBounds(300, 50, 70, 20);
		issuedBy.setBounds(375, 50, 180, 20);

		customerLabel.setBounds(40, 80, 70, 20);
		customerCombo.setBounds(115, 78, 200, 20);
		fwdCustomer.setBounds(318, 82, 16, 16);

		balanceLabel.setBounds(350, 80, 60, 20);
		balance.setBounds(415, 78, 100, 20);

		addRow.setBounds(32, LABEL_Y + 5, 16, 16);

		quantitySACKlabel.setBounds(50, LABEL_Y, 77, LABEL_HEIGHT);
		quantityKGLabel.setBounds(127, LABEL_Y, 77, LABEL_HEIGHT);
		priceSACK.setBounds(204, LABEL_Y, 85, LABEL_HEIGHT);
		priceKG.setBounds(289, LABEL_Y, 77, LABEL_HEIGHT);
		productLabel.setBounds(366, LABEL_Y, 207, LABEL_HEIGHT);
		deleteLabel.setBounds(573, LABEL_Y, 42, LABEL_HEIGHT);
		// 136
		fwdProduct.setBounds(502, LABEL_Y + 5, 16, 16);

		productsPane.setBounds(51, ITEMS_PANE_Y, ROW_WIDTH, 140);

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

		List<Person> customers = new ArrayList<Person>();
		try {
			customers = Manager.employeePersonManager.getCustomersOnly();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Person p : customers) {
			customerCombo.addItem(p);
		}

		panel.add(dateLabel);
		panel.add(date);

		panel.add(issuedByLabel);
		panel.add(issuedBy);

		panel.add(customerLabel);
		panel.add(customerCombo);

		panel.add(balanceLabel);
		panel.add(balance);

		panel.add(addRow);

		panel.add(fwdProduct);
		panel.add(quantitySACKlabel);
		panel.add(quantityKGLabel);
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
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		save = new SoyButton("Save");

		error = new ErrorLabel();

		save.setBounds(290, 300, 80, 30);

		error.setBounds(305, 340, 200, 30);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				Date d = ((SpinnerDateModel) date.getModel()).getDate();
				AccountReceivable ar = new AccountReceivable(d, (Person) customerCombo.getSelectedItem(), Manager.loggedInAccount);

				for (RowPanel rp : rowPanel) {
					Product p = rp.getSelectedProduct();
					ar.addAccountReceivableDetail(new AccountReceivableDetail(ar, p, p.getPricePerKilo(), p.getPricePerSack(), rp.getQuantityInKilo(), rp
							.getQuantityInSack()));
				}

				try {
					Manager.accountReceivableManager.addAccountReceivable(ar);
					System.out.println("ar saved!");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		panel.add(save);
		add(error);

	}

	private boolean isValidated() {

		if (customerCombo.getModel().getSelectedItem() == null) {

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

		customerCombo.setSelectedIndex(-1);
	}

	public void refreshDate() {
		date.setValue(new Date());
	}

	public void refreshAccount() {

		try {
			// model = new
			// DefaultComboBoxModel(Manager.accountManager.getAccounts().toArray());

			customerCombo.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		customerCombo.setSelectedIndex(-1);
	}

}
