package gui.forms.edit;

import gui.forms.util.EditRowPanel;
import gui.forms.util.FormDropdown;
import gui.forms.util.RemarksLabel;
import gui.forms.util.RowPanel;
import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;
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

	private ArrayList<EditRowPanel> rowPanel = new ArrayList<EditRowPanel>();
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
		colorTable();
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

		remarks = new RemarksLabel("");
		
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
		expensesPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		amountLabel.setBounds(23, LABEL_Y, 77, LABEL_HEIGHT);
		expenseLabel.setBounds(100, LABEL_Y, ROW_WIDTH - amountLabel.getWidth() - 15, LABEL_HEIGHT);

		expensesPane.setBounds(24, ITEMS_PANE_Y, ROW_WIDTH, 140);

		typeLabel.setBounds(310, LABEL_Y + 25, 70, 20);// 15
		type.setBounds(395, LABEL_Y + 25, 160, 20);

		dateLabel.setBounds(310, LABEL_Y + 60, 70, 20);
		date.setBounds(395, LABEL_Y + 60, 160, 20);

		issuedByLabel.setBounds(310, LABEL_Y + 95, 70, 20);// 350
		issuedBy.setBounds(395, LABEL_Y + 95, 160, 20);

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
		remarks.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 2, scrollPane.getWidth(), 20);

		add(scrollPane);
		add(status);
		add(remarks);
	}

	private void colorTable(){

		String s = "";
		if (dailyExpenses.getInventorySheetData() != null) {
			icon = new ImageIcon("images/accounted.png");
			s = "ACCOUNTED";
			status.setForeground(Color.GREEN.darker());
			remarks.setForeground(Color.GREEN.darker());
			scrollPane.setBorder(new ViewFormBorder(Values.ACCOUNTED_COLOR));
		} else {
			if (dailyExpenses.isValid()) {
				icon = new ImageIcon("images/pending.png");
				s = "PENDING";
				status.setForeground(Color.orange);
				remarks.setForeground(Color.orange);
				scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));
			} else {
				icon = new ImageIcon("images/invalidated.png");
				s = "INVALIDATED";
				status.setForeground(Color.RED);
				remarks.setForeground(Color.RED);
				scrollPane.setBorder(new ViewFormBorder(Values.INVALIDATED_COLOR));
			}
		}
		status.setText(s);
		status.setIcon(icon);
		
	}
	
	private void alternateRows() {

		for (int i = 0; i < rowPanel.size(); i++)
			if (i % 2 == 0)
				rowPanel.get(i).getRow().setBackground(Values.row1);
			else
				rowPanel.get(i).getRow().setBackground(Values.row2);
	}

	private void addComponents() {

		voidBtn = new SBButton("invalidate.png", "invalidate2.png", "Void");
		voidBtn.setBounds(Values.WIDTH - 28, 9, 16, 16);
		voidBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();
				UtilityPopup uP = new UtilityPopup(b, Values.REMARKS);
				uP.setVisible(true);
				
				dailyExpenses.setValid(false);
				dailyExpenses.setRemarks(uP.getReason());
				
				try {
					Manager.dailyExpenseManager.updateDailyExpenses(dailyExpenses);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Values.editPanel.startAnimation();
				new SuccessPopup("Invalidation").setVisible(true);
				Values.centerPanel.changeTable(Values.EXPENSES);
			}
		});

		error = new ErrorLabel();

		error.setBounds(305, 340, 200, 30);

		add(voidBtn);
		add(error);

	}

	private void fillEntries() {

		voidBtn.setVisible(dailyExpenses.getInventorySheetData() != null ? false : dailyExpenses.isValid());

		type.setToolTip(type, dailyExpenses.getDailyExpensesType().toString());
		date.setToolTip(date, DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(dailyExpenses.getDate()));
		issuedBy.setToolTip(issuedBy,dailyExpenses.getAccount().getFirstPlusLastName());
		remarks.setToolTip(remarks, "-"+dailyExpenses.getRemarks());

		Set<DailyExpensesDetail> details = dailyExpenses.getDailyExpenseDetails();
		for (DailyExpensesDetail ded : details) {
			 rowPanel.add(new EditRowPanel(ded, expensesPanel, Values.EXPENSES));
			 expensesPanel.add(rowPanel.get(rowPanel.size() - 1));
			 alternateRows();
			
			 expensesPanel.setPreferredSize(new Dimension(ROW_WIDTH, expensesPanel.getComponentCount() * ROW_HEIGHT));
			 expensesPanel.updateUI();
			 expensesPanel.revalidate();
		}
	}
}
