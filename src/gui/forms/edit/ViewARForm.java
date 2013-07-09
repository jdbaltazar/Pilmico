package gui.forms.edit;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.RowPanel;
import gui.forms.util.FormDropdown.ColorArrowUI;
import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;

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
import common.entity.product.Product;
import common.entity.profile.Person;
import common.manager.Manager;

import util.EditFormPanel;
import util.ErrorLabel;
import util.MainFormField;
import util.MainFormLabel;
import util.SBButton;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Values;
import util.soy.SoyButton;

public class ViewARForm extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel productsPanel;
	private JScrollPane productsPane;
	private final int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 85, UPPER_Y = 63, ITEMS_PANE_Y = LABEL_HEIGHT + LABEL_Y; // 125
	private Object[] array = {};
	private JTextField customerComboField;

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, priceKG, priceSACK, productLabel, deleteLabel;
	private ImageIcon icon;
	private SoyButton save;
	private ViewFormField balance, issuedBy, date, customer;;
	private ViewFormLabel issuedByLabel, balanceLabel, dateLabel, customerLabel, remarks;

	private DefaultComboBoxModel model;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";

	private JLabel status;

	public ViewARForm() {
		// TODO Auto-generated constructor stub
		super("View Account Receivables Form");
		init();
		addComponents();

	};

	private void init() {

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		icon = new ImageIcon("images/pending.png");

		status = new JLabel("PENDING", icon, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));
		status.setForeground(Color.orange);

		remarks = new ViewFormLabel("", true);

		date = new ViewFormField("");

		issuedByLabel = new ViewFormLabel("Issued by:");
		balanceLabel = new ViewFormLabel("Balance:");
		dateLabel = new ViewFormLabel("Date:");

		customerLabel = new ViewFormLabel("Customer:");

		issuedBy = new ViewFormField("");

		balance = new ViewFormField("");

		quantityKGLabel = new TableHeaderLabel("Qtty (kg)");
		productLabel = new TableHeaderLabel("Products");
		quantitySACKlabel = new TableHeaderLabel("Qtty (sack)");
		priceKG = new TableHeaderLabel("Price (kg)");
		priceSACK = new TableHeaderLabel("Price (sack)");
		deleteLabel = new TableHeaderLabel(icon);

		customer = new ViewFormField("");

		productsPanel = new JPanel();
		productsPanel.setLayout(null);
		productsPanel.setOpaque(false);

		productsPane = new JScrollPane(productsPanel);

		productsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		productsPane.setOpaque(false);
		productsPane.getViewport().setOpaque(false);

		dateLabel.setBounds(20, 20, 70, 20);// 15x,12y//50
		date.setBounds(95, 20, 160, 20);

		issuedByLabel.setBounds(305, 20, 70, 20);
		issuedBy.setBounds(380, 20, 200, 20);

		customerLabel.setBounds(20, 50, 70, 20);
		customer.setBounds(95, 48, 200, 20);

		balanceLabel.setBounds(305, 50, 70, 20);
		balance.setBounds(380, 48, 130, 20);

		quantitySACKlabel.setBounds(30, LABEL_Y, 77, LABEL_HEIGHT);
		quantityKGLabel.setBounds(107, LABEL_Y, 77, LABEL_HEIGHT);
		priceSACK.setBounds(184, LABEL_Y, 85, LABEL_HEIGHT);
		priceKG.setBounds(269, LABEL_Y, 77, LABEL_HEIGHT);
		productLabel.setBounds(346, LABEL_Y, 249, LABEL_HEIGHT);
		// 136

		productsPane.setBounds(31, ITEMS_PANE_Y, ROW_WIDTH, 140);

		/*
		 * addRow.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) {
		 * rowPanel.add(new RowPanel(productsPanel, Values.ADD));
		 * productsPanel.add(rowPanel.get(rowPanel.size() - 1)); alternateRows();
		 * 
		 * productsPanel.setPreferredSize(new Dimension(330,
		 * productsPanel.getComponentCount() * ROW_HEIGHT));
		 * productsPanel.updateUI(); productsPanel.revalidate();
		 * 
		 * Rectangle rect = new Rectangle(0, (int)
		 * productsPanel.getPreferredSize().getHeight(), 10, 10);
		 * productsPanel.scrollRectToVisible(rect); } });
		 */

		panel.add(dateLabel);
		panel.add(date);

		panel.add(issuedByLabel);
		panel.add(issuedBy);

		panel.add(customerLabel);
		panel.add(customer);

		panel.add(balanceLabel);
		panel.add(balance);

		// panel.add(addRow);

		// panel.add(fwdProduct);
		panel.add(quantitySACKlabel);
		panel.add(quantityKGLabel);
		panel.add(priceKG);
		panel.add(priceSACK);
		panel.add(productLabel);
		// panel.add(deleteLabel);

		panel.add(productsPane);

		// panel.add(fwdCustomer);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));
		// scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(83, 63, 638, 280);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 100, 20);
		remarks.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 2, scrollPane.getWidth(), 20);

		add(scrollPane);
		add(status);
		add(remarks);
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

			}
		});

		// panel.add(save);
		add(error);

	}

}
