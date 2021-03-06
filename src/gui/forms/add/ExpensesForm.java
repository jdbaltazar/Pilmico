package gui.forms.add;

import gui.forms.util.FormDropdown;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import util.Tables;
import util.Values;
import util.soy.SoyButton;

import common.entity.dailyexpenses.DailyExpenses;
import common.entity.dailyexpenses.DailyExpensesDetail;
import common.entity.dailyexpenses.DailyExpensesType;
import common.entity.dailyexpenses.Expense;
import common.manager.Manager;

@SuppressWarnings({ "unchecked" })
public class ExpensesForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel expensesPanel;
	private JScrollPane expensesPane;
	private final int ROW_WIDTH = 305, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 0, ITEMS_PANE_Y = 25;

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private JButton addRow;
	private TableHeaderLabel amountLabel, expenseLabel, deleteLabel;
	private SpinnerDate date, issueDate;
	private ImageIcon icon;
	private SoyButton save;
	private JLabel issuedBy;
	private FormDropdown type;
	private MainFormLabel issuedByLabel, typeLabel, dateLabel;

	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";
	private SBButton fwd;

	public ExpensesForm() {
		// TODO Auto-generated constructor stub
		super("Add Daily Expenses Form");
		init();
		addComponents();
		Values.expensesForm = this;
	};

	private void init() {

		fwd = new SBButton("forward.png", "forward.png", "Add new customer");
		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		type = new FormDropdown();

		icon = new ImageIcon("images/util.png");
		issueDate = new SpinnerDate(Values.dateFormat);

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
		typeLabel = new MainFormLabel("Type:");
		dateLabel = new MainFormLabel("Date:");

		issuedBy = new JLabel(Manager.loggedInAccount.getFirstPlusLastName());
		issuedBy.setOpaque(false);
		issuedBy.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		issuedBy.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

		amountLabel = new TableHeaderLabel("Amount");
		expenseLabel = new TableHeaderLabel("Expenses");
		deleteLabel = new TableHeaderLabel(icon);

		expensesPanel = new JPanel();
		expensesPanel.setLayout(null);
		expensesPanel.setOpaque(false);

		expensesPane = new JScrollPane(expensesPanel);

		expensesPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		expensesPane.setOpaque(false);
		expensesPane.getViewport().setOpaque(false);

		issuedBy.setBounds(330, 10, 200, 20);

		issueDate.setBounds(460, 32, 150, 20);

		fwd.setBounds(308, 58, 16, 16);

		addRow.setBounds(0, LABEL_Y + 5, 16, 16);

		amountLabel.setBounds(18, LABEL_Y, 77, LABEL_HEIGHT);
		expenseLabel.setBounds(95, LABEL_Y, 170, LABEL_HEIGHT);
		deleteLabel.setBounds(265, LABEL_Y, 42, LABEL_HEIGHT);

		expensesPane.setBounds(19, ITEMS_PANE_Y, ROW_WIDTH, 140);
		// fwd2.setBounds(482, LABEL_Y+5, 16, 16);
		typeLabel.setBounds(350, LABEL_Y + 5, 40, 20);
		type.setBounds(395, LABEL_Y + 5, 150, 20);

		dateLabel.setBounds(350, LABEL_Y + 40, 40, 20);
		date.setBounds(395, LABEL_Y + 40, 150, 20);
		dateStatus.setBounds(550, LABEL_Y + 42, 16, 16);

		issuedByLabel.setBounds(350, LABEL_Y + 75, 70, 20);
		issuedBy.setBounds(430, LABEL_Y + 75, 150, 20);

		addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				rowPanel.add(new RowPanel(expensesPanel, Tables.EXPENSES));
				expensesPanel.add(rowPanel.get(rowPanel.size() - 1));
				alternateRows();

				expensesPanel.setPreferredSize(new Dimension(ROW_WIDTH - 20, expensesPanel.getComponentCount() * ROW_HEIGHT));
				expensesPanel.updateUI();
				expensesPanel.revalidate();

				Rectangle rect = new Rectangle(0, (int) expensesPanel.getPreferredSize().getHeight(), 10, 10);
				expensesPanel.scrollRectToVisible(rect);
			}
		});

		try {
			List<DailyExpensesType> detypes = Manager.getInstance().getDailyExpenseManager().getExpenseTypes();
			for (DailyExpensesType det : detypes) {
				type.addItem(det);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		panel.add(addRow);

		panel.add(amountLabel);
		panel.add(expenseLabel);
		panel.add(deleteLabel);

		panel.add(expensesPane);

		panel.add(typeLabel);
		panel.add(type);

		panel.add(dateLabel);
		panel.add(date);
		panel.add(dateStatus);

		panel.add(issuedByLabel);
		panel.add(issuedBy);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(20, 59, 630, 310);

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
		expensesPanel.remove(rowNum);
		expensesPanel.updateUI();
		expensesPanel.revalidate();

		expensesPanel.setPreferredSize(new Dimension(ROW_WIDTH - 20, expensesPanel.getComponentCount() * ROW_HEIGHT));

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

		save.setBounds(417, 135, 80, 30);

		error.setBounds(345, 105, 220, 22);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (isValidated() && !hasBlankEntry() && !hasZeroQuantity()) {
					PointerInfo a = MouseInfo.getPointerInfo();
					Point b = a.getLocation();

					UtilityPopup uP = new UtilityPopup(b, Values.REMARKS);
					uP.setVisible(true);

					if (!uP.isClosed()) {

						Date d = ((SpinnerDateModel) date.getModel()).getDate();
						DailyExpenses de = new DailyExpenses(d, (DailyExpensesType) (type.getSelectedItem()), Manager.loggedInAccount);

						for (RowPanel rp : rowPanel) {
							String exp = rp.getSelectedExpense();
							Expense expense = null;
							try {
								expense = Manager.getInstance().getDailyExpenseManager().searchExpense(exp);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							if (expense == null) {
								try {
									expense = new Expense(exp);
									Manager.getInstance().getDailyExpenseManager().addExpenses(expense);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
							de.addDailyExpenseDetail(new DailyExpensesDetail(de, expense, rp.getExpenseAmount()));
						}

						de.setRemarks(uP.getInput());

						try {
							Manager.getInstance().getDailyExpenseManager().addDailyExpenses(de);
							Values.centerPanel.changeTable(Values.EXPENSES);
							new SuccessPopup("Add").setVisible(true);
							clearForm();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else
					error.setToolTip(msg);
			}
		});

		panel.add(save);
		panel.add(error);

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

		if (expensesPanel.getComponentCount() == 0) {

			msg = "Put at least one entry ";

			return false;
		}

		return true;

	}

	private boolean hasBlankEntry() {

		for (int i = 0; i < rowPanel.size(); i++) {
			JTextField field = (JTextField) rowPanel.get(i).getExpensesCombo().getEditor().getEditorComponent();

			if (field.getText().equals("")) {
				msg = "Blank entry in row " + (i + 1) + " ";
				return true;
			}
		}

		return false;
	}

	private boolean hasZeroQuantity() {

		for (int i = 0; i < rowPanel.size(); i++) {

			if (rowPanel.get(i).getExpenseAmount() == 0d) {
				msg = "0 amount found in row " + (i + 1) + " ";
				return true;
			}
		}

		return false;
	}

	private void clearForm() {
		expensesPanel.removeAll();
		rowPanel.clear();
		refreshDate();

		error.setToolTip("");

	}

	public void refreshDate() {
		date.setValue(new Date());
	}

	public void refreshAccount() {

	}
}
