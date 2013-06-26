package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.FormDropdown;
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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import util.ErrorLabel;
import util.SBButton;
import util.SimplePanel;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Values;
import util.soy.SoyButton;

public class ExpensesForm extends SimplePanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel itemsPanel, row, p;
	private JScrollPane itemsPane;
	private JComboBox accountCombo;
	//private final int ROW_WIDTH = 350, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 125, UPPER_Y = 63, ITEMS_PANE_Y = 150;
	private final int ROW_WIDTH = 335, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 55, UPPER_Y = 63, ITEMS_PANE_Y = 80;
	private Object[] array = {};
	private JTextField accountComboField;
	private JScrollBar sb;

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private JTextField quantity;
	private JButton deleteRow, addRow;
	private JLabel issuedByLabel;
	private TableHeaderLabel amountLabel, descriptionLabel, deleteLabel;
	private SpinnerDate date;
	private ImageIcon icon;
	private SoyButton save;
	private FormField remarks;
	private FormDropdown expenseType;
	private DefaultComboBoxModel model;

	private ErrorLabel error;
	private String msg = "";

	public ExpensesForm() {
		// TODO Auto-generated constructor stub
		super("Add Expenses Form");
		init();
		addComponents();

	};

	private void init() {

		icon = new ImageIcon("images/util.png");

		date = new SpinnerDate("MMM dd, yyyy hh:mm a");
		
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
		
		amountLabel = new TableHeaderLabel("Amount");
		descriptionLabel = new TableHeaderLabel("Description");
		deleteLabel = new TableHeaderLabel(icon);

		date.setBounds(440, 12, 150, 20);
		issuedByLabel.setBounds(410, UPPER_Y, 80, 20);
		accountCombo.setBounds(410, UPPER_Y+20, 160, 20);
		remarks.setBounds(410, UPPER_Y+60, 160, 22);

		accountCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		itemsPane = new JScrollPane(itemsPanel);

		itemsPane.setBounds(53, ITEMS_PANE_Y, ROW_WIDTH, 150);
		itemsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		itemsPane.setOpaque(false);
		itemsPane.getViewport().setOpaque(false);
		
		
		
		issuedByLabel.setFont(new Font("Harabara", Font.PLAIN, 16));

		amountLabel.setBounds(51, LABEL_Y, 77, LABEL_HEIGHT);
		descriptionLabel.setBounds(128, LABEL_Y, 200, LABEL_HEIGHT);

		deleteLabel.setBounds(328, LABEL_Y, 42, LABEL_HEIGHT);

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
		
		add(remarks);
		
		add(amountLabel);
		add(descriptionLabel);
		add(deleteLabel);
		
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

		//save.setBounds(200, 315, 80, 30);
		save.setBounds(460, UPPER_Y+130, 80, 30);

		error.setBounds(305, 340, 200, 30);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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
