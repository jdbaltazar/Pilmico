package gui.panels;

import gui.forms.util.FormField;
import gui.graph.LinePlot;
import gui.popup.ProductOnDisplayPopup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import common.entity.inventorysheet.InventorySheetData;
import common.manager.Manager;

import util.SBButton;
import util.TableLink;
import util.Tables;
import util.Values;
import util.soy.SoyPanel;

public class TableUtilPanel extends SoyPanel {

	/**
	 * 
	 */

	private static final long serialVersionUID = 9077704680088367571L;
	private JPanel searchPanel;
	private SBButton addbtn, exportbtn, graphBtn, onDisplayBtn;
	private JLabel search;
	public static FormField searchField;
	private ImageIcon icon;
	private TablePanel tablePanel;
	private String label;
	private JLabel tableLabel;
	private String[] links = { Tables.EXPENSES, Tables.SALARY, Tables.DELIVERIES, Tables.PULLOUTS, Tables.SUPPLIERS, Tables.ACCOUNT_RECEIVABLES,
			Tables.AR_PAYMENTS, Tables.CASH_ADVANCE, Tables.CA_PAYMENTS, Tables.DEPOSITS, Tables.BANK, Tables.CUSTOMERS, Tables.EMPLOYEES,
			Tables.ACCOUNTS };
	private ArrayList<TableLink> labels = new ArrayList<TableLink>();

	public TableUtilPanel(TablePanel tablePanel, String label) {
		this.tablePanel = tablePanel;
		this.label = label;
		init();
		addComponents();

		Values.tableUtilPanel = this;
	}

	private void init() {
		// TODO Auto-generated method stub
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1, 60));

		icon = new ImageIcon("images/search.png");
	}

	private void addComponents() {

		tableLabel = new JLabel(label);

		tableLabel.setFont(new Font("Forte", Font.PLAIN, 18));
		// tableLabel.setForeground(Color.GRAY.darker());
		tableLabel.setForeground(Color.blue.darker());

		// tableLabel.setBounds(70, 12, 200, 20);

		searchPanel = new JPanel();
		searchPanel.setLayout(null);
		searchPanel.setPreferredSize(new Dimension(1, 40));
		// searchPanel.setBackground(Color.white);

		addbtn = new SBButton("add.png", "add2.png", "Add Entry");
		addbtn.setBounds(10, 8, 24, 24);
		addbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.startAnimation();
				Values.addEntryPanel.showComponent();
			}
		});

		exportbtn = new SBButton("export.png", "export2.png", "Export to Excel");
		exportbtn.setBounds(37, 8, 24, 24);
		exportbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		searchField = new FormField("Search table", 500);
		searchField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

				Values.tablePanel.newFilter();
			}

			public void insertUpdate(DocumentEvent e) {

				Values.tablePanel.newFilter();
			}

			public void removeUpdate(DocumentEvent e) {
				Values.tablePanel.newFilter();
			}
		});

		graphBtn = new SBButton("graph.png", "graph2.png", "Show Graph");
		graphBtn.setBounds(100, 11, 20, 20);
		graphBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				List<InventorySheetData> isds = new ArrayList<InventorySheetData>();
				try {
					isds = Manager.inventorySheetDataManager.getInventorySheetsData();
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (isds.size() < 2) {
					JOptionPane.showMessageDialog(Values.mainFrame,
							"Insufficient data! \nAt least two (2) Inventory Sheets needed for graphing.", "Notice",
							JOptionPane.INFORMATION_MESSAGE);
					// Values.centerPanel.changeTable(Values.SALES);
				} else {
					new LinePlot(isds);
					Values.centerPanel.changeTable(Values.SALES_GRAPH);
				}

			}
		});

		onDisplayBtn = new SBButton("on_display.png", "on_display2.png", "Show On-Display Products");
		onDisplayBtn.setBounds(130, 10, 20, 20);

		onDisplayBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Values.mainFrame.dimScreen(true);
				Values.productOnDisplayPopup.fillTable();
				Values.productOnDisplayPopup.setVisible(true);
			}
		});

		search = new JLabel(icon);

		search.setBounds(565, 12, 24, 24);
		searchField.setBounds(593, 9, 200, 25);

		// search.setBounds(38, 12, 24, 24);
		// searchField.setBounds(62, 9, 200, 25);

		if (label.equals(Tables.SALES))
			searchPanel.add(graphBtn);
		
		if(label.equals(Tables.ACCOUNTS))
			if(!Manager.isAuthorized())
				addbtn.setVisible(false);

		if (label.equals(Tables.PRODUCTS))
			searchPanel.add(onDisplayBtn);

		if (!label.equals(Tables.LOGS)) {// && !label.equals("ACCOUNTS")) {
			tableLabel.setBounds(40, 11, 200, 20);
			searchPanel.add(addbtn);
		} /*
			 * else if (Manager.loggedInAccount.getAccountType().getName()
			 * .equals(AccountType.manager) && label.equals("ACCOUNTS")) {
			 * searchPanel.add(addbtn); tableLabel.setBounds(40, 11, 200, 20); }
			 */else
			tableLabel.setBounds(20, 11, 200, 20);

		searchPanel.add(search);
		searchPanel.add(searchField);

		if (!isMultiple())
			searchPanel.add(tableLabel);
		else
			putLinks();

		add(searchPanel, BorderLayout.PAGE_START);
		// add(new TablePanel(data, columnNames), BorderLayout.CENTER);
		add(tablePanel, BorderLayout.CENTER);

	}

	private void putLinks() {
		for (int i = 0; i < links.length; i++) {
			labels.add(new TableLink(links[i], i));

			if (label.equals(links[i]))
				labels.get(i).setForeground(Color.BLUE.darker());
		}

		if (label.equals(Tables.EXPENSES) || label.equals(Tables.SALARY)) {

			JLabel l = new JLabel("->");
			l.setBounds(135, 11, 20, 20);
			labels.get(Values.EXPENSES).setBounds(40, 11, 93, 20);
			labels.get(Values.SALARY).setBounds(153, 11, 68, 20);

			searchPanel.add(labels.get(Values.EXPENSES));
			searchPanel.add(l);
			searchPanel.add(labels.get(Values.SALARY));
		}

		else if (label.equals(Tables.ACCOUNT_RECEIVABLES) || label.equals(Tables.AR_PAYMENTS)) {

			JLabel l = new JLabel("->");
			l.setBounds(247, 11, 20, 20);
			labels.get(Values.ACCOUNT_RECEIVABLES).setBounds(40, 11, 205, 20);
			labels.get(Values.AR_PAYMENTS).setBounds(265, 11, 132, 20);

			searchPanel.add(labels.get(Values.ACCOUNT_RECEIVABLES));
			searchPanel.add(l);
			searchPanel.add(labels.get(Values.AR_PAYMENTS));

			if (label.equals(Tables.AR_PAYMENTS))
				addbtn.setVisible(false);
		}

		else if (label.equals(Tables.DELIVERIES) || label.equals(Tables.SUPPLIERS) || label.equals(Tables.PULLOUTS)) {

			JLabel l = new JLabel("->");
			JLabel l2 = new JLabel("->");
			l.setBounds(147, 11, 20, 20);
			l2.setBounds(245, 11, 20, 20);
			labels.get(Values.DELIVERY).setBounds(40, 11, 105, 20);
			labels.get(Values.PULLOUT).setBounds(165, 11, 78, 20);
			labels.get(Values.SUPPLIERS).setBounds(263, 11, 97, 20);

			searchPanel.add(labels.get(Values.DELIVERY));
			searchPanel.add(l);
			searchPanel.add(labels.get(Values.PULLOUT));
			searchPanel.add(l2);
			searchPanel.add(labels.get(Values.SUPPLIERS));
		}

		else if (label.equals(Tables.CASH_ADVANCE) || label.equals(Tables.CA_PAYMENTS)) {

			JLabel l = new JLabel("->");
			l.setBounds(185, 11, 20, 20);
			labels.get(Values.CASH_ADVANCE).setBounds(40, 11, 143, 20);
			labels.get(Values.CA_PAYMENTS).setBounds(203, 11, 132, 20);

			searchPanel.add(labels.get(Values.CASH_ADVANCE));
			searchPanel.add(l);
			searchPanel.add(labels.get(Values.CA_PAYMENTS));

			if (label.equals(Tables.CA_PAYMENTS))
				addbtn.setVisible(false);
		}

		else if (label.equals(Tables.DEPOSITS) || label.equals(Tables.BANK)) {

			JLabel l = new JLabel("->");
			l.setBounds(128, 11, 20, 20);
			labels.get(Values.DEPOSITS).setBounds(40, 11, 86, 20);
			labels.get(Values.BANK).setBounds(146, 11, 55, 20);

			searchPanel.add(labels.get(Values.DEPOSITS));
			searchPanel.add(l);
			searchPanel.add(labels.get(Values.BANK));
		}

		else if (label.equals(Tables.CUSTOMERS) || label.equals(Tables.EMPLOYEES) || label.equals(Tables.ACCOUNTS)) {

			JLabel l = new JLabel("->");
			JLabel l2 = new JLabel("->");
			l.setBounds(147, 11, 20, 20);
			labels.get(Values.CUSTOMERS).setBounds(40, 11, 105, 20);// 86
			labels.get(Values.EMPLOYEES).setBounds(165, 11, 107, 20);
			l2.setBounds(274, 11, 20, 20);
			labels.get(Values.ACCOUNTS).setBounds(292, 11, 93, 20);

			searchPanel.add(labels.get(Values.CUSTOMERS));
			searchPanel.add(l);
			searchPanel.add(labels.get(Values.EMPLOYEES));
			searchPanel.add(l2);
			searchPanel.add(labels.get(Values.ACCOUNTS));
		}

	}

	private boolean isMultiple() {

		for (int i = 0; i < links.length; i++)
			if (label.equals(links[i])) {
				return true;
			}

		return false;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		gradient = new GradientPaint(0, 0, new Color(30, 30, 30), 0, getHeight(), new Color(5, 5, 5));
		g2.setPaint(gradient);
		g2.fill(g.getClipBounds());

		g2.setPaint(new Color(210, 210, 210));
		g2.fillRect(0, 0, g.getClipBounds().width, 1);

		paintChildren(g2);

		g2.dispose();
		g.dispose();

	}

	public String getLabel() {
		return label;
	}

}
