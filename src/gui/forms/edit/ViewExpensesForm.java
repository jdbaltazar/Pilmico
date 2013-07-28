package gui.forms.edit;

import gui.forms.util.EditRowPanel;
import gui.forms.util.FormDropdown;
import gui.forms.util.RowPanel;
import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;
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
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;

import common.entity.dailyexpenses.DailyExpenses;
import common.entity.dailyexpenses.DailyExpensesDetail;
import common.entity.dailyexpenses.DailyExpensesType;
import common.entity.dailyexpenses.Expense;
import common.manager.Manager;

import util.DateFormatter;
import util.EditFormPanel;
import util.ErrorLabel;
import util.MainFormLabel;
import util.SBButton;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Tables;
import util.Utility;
import util.Values;
import util.soy.SoyButton;

public class ViewExpensesForm extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel expensesPanel, row, p;
	private JScrollPane expensesPane;
	private final int ROW_WIDTH = 280, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 25, UPPER_Y = 63, ITEMS_PANE_Y = LABEL_HEIGHT + LABEL_Y;
	private Object[] array = {};

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private TableHeaderLabel amountLabel, expenseLabel;
	private ImageIcon icon;
	private ViewFormField type, issuedBy, date;
	private ViewFormLabel issuedByLabel, typeLabel, dateLabel;

	private JPanel panel;
	private JScrollPane scrollPane;
	private JLabel status;

	private SBButton voidBtn;

	private ErrorLabel error;

	private DailyExpenses dailyExpenses;

	public ViewExpensesForm(DailyExpenses dailyExpenses) {
		super("View Daily Expenses Form");
		this.dailyExpenses = dailyExpenses;
		init();
		addComponents();
		fillEntries();

	};

	private void init() {

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		type = new ViewFormField("");
		issuedBy = new ViewFormField("");
		date = new ViewFormField("");

		status = new JLabel("", null, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));
		status.setForeground(Color.green.darker());

		issuedByLabel = new ViewFormLabel("Issued by:");
		typeLabel = new ViewFormLabel("Type:");
		dateLabel = new ViewFormLabel("Date:");

		amountLabel = new TableHeaderLabel("Amount");
		expenseLabel = new TableHeaderLabel("Expenses");

		expensesPanel = new JPanel();
		expensesPanel.setLayout(null);
		expensesPanel.setOpaque(false);

		expensesPane = new JScrollPane(expensesPanel);

		expensesPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		expensesPane.setOpaque(false);
		expensesPane.getViewport().setOpaque(false);

		amountLabel.setBounds(23, LABEL_Y, 77, LABEL_HEIGHT);
		expenseLabel.setBounds(100, LABEL_Y, ROW_WIDTH - amountLabel.getWidth() - 15, LABEL_HEIGHT);

		expensesPane.setBounds(24, ITEMS_PANE_Y, ROW_WIDTH, 140);

		typeLabel.setBounds(310, LABEL_Y + 25, 70, 20);// 15
		type.setBounds(395, LABEL_Y + 25, 160, 20);

		dateLabel.setBounds(310, LABEL_Y + 60, 70, 20);
		date.setBounds(395, LABEL_Y + 60, 160, 20);

		issuedByLabel.setBounds(310, LABEL_Y + 95, 70, 20);// 350
		issuedBy.setBounds(395, LABEL_Y + 95, 160, 20);

		/*
		 * addRow.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) {
		 * 
		 * rowPanel.add(new RowPanel(expensesPanel, Tables.EXPENSES));
		 * expensesPanel.add(rowPanel.get(rowPanel.size() - 1)); alternateRows();
		 * 
		 * expensesPanel.setPreferredSize(new Dimension(ROW_WIDTH - 20,
		 * expensesPanel.getComponentCount() * ROW_HEIGHT));
		 * expensesPanel.updateUI(); expensesPanel.revalidate();
		 * 
		 * Rectangle rect = new Rectangle(0, (int) expensesPanel
		 * .getPreferredSize().getHeight(), 10, 10);
		 * expensesPanel.scrollRectToVisible(rect); } });
		 */

		panel.add(amountLabel);
		panel.add(expenseLabel);
		// panel.add(deleteLabel);

		panel.add(expensesPane);

		panel.add(typeLabel);
		panel.add(type);

		panel.add(dateLabel);
		panel.add(date);

		panel.add(issuedByLabel);
		panel.add(issuedBy);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.ACCOUNTED_COLOR));

		scrollPane.setBounds(115, 95, issuedBy.getX() + issuedBy.getWidth() + amountLabel.getX(), expensesPane.getY() + expensesPane.getHeight()
				+ LABEL_Y + 3);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 150, 20);

		add(scrollPane);
		add(status);
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

		voidBtn = new SBButton("invalidate.png", "invalidate2.png", "Void");
		voidBtn.setBounds(Values.WIDTH - 28, 9, 16, 16);
		voidBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();
				new UtilityPopup(b, "What's your reason for invalidating this form?", Values.REMARKS, dailyExpenses).setVisible(true);
			}
		});

		error = new ErrorLabel();

		error.setBounds(305, 340, 200, 30);

		add(voidBtn);
		add(error);

	}

	private void fillEntries() {

		voidBtn.setVisible(dailyExpenses.getInventorySheetData() != null ? false : dailyExpenses.isValid());

		String s = "";
		if (dailyExpenses.getInventorySheetData() != null) {
			icon = new ImageIcon("images/accounted.png");
			s = "ACCOUNTED";
		} else {
			if (dailyExpenses.isValid()) {
				icon = new ImageIcon("images/pending.png");
				s = "PENDING";
			} else {
				icon = new ImageIcon("images/invalidated.png");
				s = "INVALIDATED";
			}
		}
		status.setText(s);
		status.setIcon(icon);

		type.setText(dailyExpenses.getDailyExpensesType().toString());
		date.setText(DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(dailyExpenses.getDate()));
		issuedBy.setText(dailyExpenses.getAccount().getFirstPlusLastName());

		Set<DailyExpensesDetail> details = dailyExpenses.getDailyExpenseDetails();
		for (DailyExpensesDetail ded : details) {
			// rowPanel.add(new EditRowPanel(ded, expensesPanel, Values.EXPENSES));
			// expensesPanel.add(rowPanel.get(rowPanel.size() - 1));
			// alternateRows();
			//
			// expensesPanel.setPreferredSize(new Dimension(330,
			// expensesPanel.getComponentCount() * ROW_HEIGHT));
			// expensesPanel.updateUI();
			// expensesPanel.revalidate();
		}

	}
}
