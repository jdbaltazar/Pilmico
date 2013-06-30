package gui.forms.add;

import gui.forms.util.FormDropdown;
import gui.forms.util.RowPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

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

import util.ErrorLabel;
import util.MainFormField;
import util.MainFormLabel;
import util.SBButton;
import util.SimplePanel;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.soy.SoyButton;

public class SalaryReleaseForm extends SimplePanel{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel cashAdvancesPanel, feesPanel;
	private JScrollPane cashAdvancesPane, feesPane;
	private final int ROW_WIDTH = 305, ROW_HEIGHT = 35, LABEL_HEIGHT = 20, LABEL_Y = 0, UPPER_Y = 63, ITEMS_PANE_Y = 25;
	private Object[] array = {};
	private JScrollBar sb;

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private JTextField quantity;
	private JButton deleteRow, addRow;
	private TableHeaderLabel dateHeaderLabel, amountLabel, deleteLabel, feesLabel;
	private SpinnerDate date;
	private ImageIcon icon;
	private SoyButton save;
	private JLabel issuedBy, caDeductions;
	private FormDropdown issuedFor;
	private MainFormField grossPay;
	private MainFormLabel issuedByLabel, issuedForLabel, dateLabel, salaryLabel, payLabel;
	
	private DefaultComboBoxModel model;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";
	private SBButton fwd, fwd2;

	public SalaryReleaseForm(){
		super("Add Salary Form");
		init();
		addComponents();
	}
	
	private void init() {
		
		fwd = new SBButton("forward.png", "forward.png", "Add new customer");
		fwd2 = new SBButton("forward.png", "forward.png", "Add new product");
		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		
		scrollPane = new JScrollPane();
		
		issuedFor = new FormDropdown();
		
		icon = new ImageIcon("images/util.png");

		date = new SpinnerDate("MMM dd, yyyy hh:mm a");
		
		dateLabel = new MainFormLabel("Date:");
		issuedByLabel = new MainFormLabel("Issued by:");
		issuedForLabel = new MainFormLabel("Issued for:");
		salaryLabel = new MainFormLabel("Gross Pay:");
		payLabel = new MainFormLabel("Net Pay:");
		
		grossPay = new MainFormField(10);
		
		issuedBy = new JLabel("John David S. Baltazar");
		issuedBy.setOpaque(false);
		issuedBy.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		issuedBy.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		issuedBy.setHorizontalAlignment(JLabel.CENTER);
		
		caDeductions = new JLabel("CASH ADVANCES");
		caDeductions.setOpaque(true);
		caDeductions.setFont(new Font("Tahoma", Font.BOLD, 10));
		caDeductions.setForeground(Color.white);
		caDeductions.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		caDeductions.setBackground(new Color(119,136,153));
		caDeductions.setHorizontalAlignment(JLabel.CENTER);
		
		dateHeaderLabel = new TableHeaderLabel("Date");
		amountLabel = new TableHeaderLabel("Amount");
		feesLabel = new TableHeaderLabel("Fees");
		deleteLabel = new TableHeaderLabel(icon);

		model = new DefaultComboBoxModel(array);
		
		cashAdvancesPanel = new JPanel();
		cashAdvancesPanel.setLayout(null);
		cashAdvancesPanel.setOpaque(false);
		
		cashAdvancesPane = new JScrollPane(cashAdvancesPanel);
		cashAdvancesPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cashAdvancesPane.setOpaque(false);
		cashAdvancesPane.getViewport().setOpaque(false);
		
		feesPanel = new JPanel();
		feesPanel.setLayout(null);
		feesPanel.setOpaque(false);
		
		feesPane = new JScrollPane(feesPanel);
		feesPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		feesPane.setOpaque(false);
		feesPane.getViewport().setOpaque(false);
		
		
		dateLabel.setBounds(40, 50, 40, 20);
		date.setBounds(85, 50, 150, 20);

		issuedForLabel.setBounds(290, 50, 80, 20);
		issuedFor.setBounds(375, 50, 200, 20);
		
		
		issuedByLabel.setBounds(40, 75, 70, 20);
		issuedBy.setBounds(120, 75, 150, 20);
		
		salaryLabel.setBounds(290, 75, 70, 20);
		grossPay.setBounds(365, 76, 65, 20);
		
		payLabel.setBounds(450, 75, 65, 20);
		
		caDeductions.setBounds(40, 110, 260, 15);
		dateHeaderLabel.setBounds(39, 125, 160, LABEL_HEIGHT);
		amountLabel.setBounds(199, 125, 102, LABEL_HEIGHT);
		cashAdvancesPane.setBounds(40, 144, 277, 140);
		
		addRow.setBounds(320, 116, 16, 16);
		
		feesLabel.setBounds(340, 110, 200, 25);
		deleteLabel.setBounds(540, 110, 42, 25);
		feesPane.setBounds(341, 134, 257, 150);
//		issuedBy.setBounds(330, 10, 200, 20);
		
		
				
		fwd.setBounds(308, 58, 16, 16);
		
//		amountLabel.setBounds(95, LABEL_Y, 170, LABEL_HEIGHT);
		
//		cashAdvancesPane.setBounds(19, ITEMS_PANE_Y, ROW_WIDTH, 140);
//		fwd2.setBounds(482, LABEL_Y+5, 16, 16);
		
		

		addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*rowPanel.add(new RowPanel(expensesPanel.getComponentCount() * ROW_HEIGHT, expensesPanel.getComponentCount() + "", 1, Values.ADD));
				expensesPanel.add(rowPanel.get(rowPanel.size() - 1));

				expensesPanel.setPreferredSize(new Dimension(330, expensesPanel.getComponentCount() * ROW_HEIGHT));
				expensesPanel.updateUI();
				expensesPanel.revalidate();

				Rectangle rect = new Rectangle(0, (int) expensesPanel.getPreferredSize().getHeight(), 10, 10);
				expensesPanel.scrollRectToVisible(rect);*/
			}
		});
		
		panel.add(dateLabel);
		panel.add(date);
		
		panel.add(issuedForLabel);
		panel.add(issuedFor);
		
		panel.add(issuedByLabel);
		panel.add(issuedBy);
		
		panel.add(salaryLabel);
		panel.add(grossPay);
		
		panel.add(payLabel);
		
		panel.add(caDeductions);
		panel.add(dateHeaderLabel);
		panel.add(amountLabel);
		panel.add(cashAdvancesPane);
		
		panel.add(addRow);
		
		panel.add(feesLabel);
		panel.add(deleteLabel);
		panel.add(feesPane);
		/*panel.add(addRow);
		
		panel.add(dateHeaderLabel);
		panel.add(amountLabel);
		panel.add(deleteLabel);
		
		panel.add(cashAdvancesPane);
		
		*/
		
		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		scrollPane.setBounds(0, 0, 638, 360);
		
		add(scrollPane);
	}

	public void removeRow(int rowNum) {
		System.out.println("pressed row button: " + rowNum);

		cashAdvancesPanel.remove(rowNum);
		cashAdvancesPanel.updateUI();
		cashAdvancesPanel.revalidate();

		cashAdvancesPanel.setPreferredSize(new Dimension(330, cashAdvancesPanel.getComponentCount() * ROW_HEIGHT));

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

		save.setBounds(280, LABEL_Y + 310, 80, 30);

		error.setBounds(305, 340, 200, 30);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

		panel.add(save);
		add(error);

	}

	private boolean isValidated() {

		if (cashAdvancesPanel.getComponentCount() == 0) {

			msg = "Put at least one item";

			return false;
		}

		return true;

	}

	private void clearForm() {
		cashAdvancesPanel.removeAll();
		rowPanel.clear();
		refreshDate();

		error.setText("");

	}

	public void refreshDate() {
		date.setValue(new Date());
	}

	public void refreshAccount() {

	}
}
