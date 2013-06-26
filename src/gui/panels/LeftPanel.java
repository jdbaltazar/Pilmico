package gui.panels;

import gui.forms.add.AccountForm;
import gui.forms.add.ProductForm;
import gui.forms.add.SupplierForm;
import gui.list.AccountTypeList;
import gui.list.CategoryList;
import gui.list.ConditionList;
import gui.list.LogTypeList;
import gui.list.NoteTypeList;
import gui.list.UnitList;
import gui.list.util.ItemTags;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.SBButton;
import util.Values;
import util.soy.FlatButton;
import util.soy.SoyPanel;

public class LeftPanel extends SoyPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton addStock;
	private FlatButton itemTags, account, types, supplier, salesOrder, stockPurchase, stocks;

	private Thread thread;
	private int maxWidth = 800;
	private int minWidth = 150;
	private int currWidth = minWidth;
	private boolean isRunning;
	private AccountTypeList accounts;
	private LogTypeList logtype;
	private NoteTypeList notetype;
	private UnitList units;
	private ConditionList conditions;
	private CategoryList categories;
	private ProductForm addItem;
	private SupplierForm supplierForm;
	private AccountForm accountform;
	
	public LeftPanel() {
		super();

		setLayout(null);
		setPreferredSize(new Dimension(minWidth, 100));
		init();
		
		addComponents();
		addListener();
		
		Values.leftPanel = this;
	}

	private void init() {
		 
		//sales order
		//stock purchase
		addStock = new SBButton("add_stock.png", "add_stock2.png", "Add Stock");
		itemTags = new FlatButton("ITEM TAGS");
		account = new FlatButton("ACCOUNTS");
		types = new FlatButton("TYPES");
		supplier = new FlatButton("SUPPLIERS");
		salesOrder = new FlatButton("SALES ORDER");
		stocks = new FlatButton("STOCKS");
		stockPurchase = new FlatButton("STOCK PURCHASE");
		
		

	}

	private void addComponents() {
		
		addStock.setBounds(30, 205, 95, 60);
		addStock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				maxWidth = 790;
				startAnimation();
				
				addItem.setVisible(true);
			}
		});
		
		stocks.setBounds(10, 22, 130, 30);
		stockPurchase.setBounds(10, 52, 130, 30);
		salesOrder.setBounds(10, 82, 130, 30);
		
		account.setBounds(10, 112, 130, 30);
		supplier.setBounds(10, 142, 130, 30);
		itemTags.setBounds(10, 172, 130, 30);
		types.setBounds(10, 202, 130, 30);
		
		//stockPurchase.setFont(new Font("TimeBurner", Font.BOLD, 14));
		//salesOrder.setFont(new Font("TimeBurner", Font.BOLD, 15));
		/*
		itemTags.setBounds(3, 20, 140, 30);
		account.setBounds(3, 52, 140, 30);
		types.setBounds(3, 82, 140, 30);
		supplier.setBounds(3, 112, 140, 30);
		notes.setBounds(3, 142, 140, 30);*/
		
		stocks.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				maxWidth = 800;
				startAnimation();
				
				addItem.setVisible(true);
			}
		});
		
		supplier.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				maxWidth = 590;
				startAnimation();
				
				supplierForm.setVisible(true);
			}
		});

		itemTags.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				maxWidth = 800;
				startAnimation();
				
				units.setVisible(true);
				conditions.setVisible(true);
				categories.setVisible(true);
			}
		});
		
		types.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				maxWidth = 790;
				startAnimation();
				
				accounts.setVisible(true);
				logtype.setVisible(true);
				notetype.setVisible(true);
			}
		});
		
		accounts = new AccountTypeList();
		accounts.setBounds(380, 15, 200, 160);
		
		account.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				maxWidth = 590;
				startAnimation();
				
				accountform.setVisible(true);
			}
		});
		
		logtype = new LogTypeList();
		logtype.setBounds(270, 220, 200, 160);
		
		notetype = new NoteTypeList();
		notetype.setBounds(560, 210, 200, 160);
		
		
		units = new UnitList();
		units.setBounds(270, 70, 200, 160);
		
		conditions = new ConditionList();
		conditions.setBounds(560, 10, 200, 160);
		
		categories = new CategoryList();
		categories.setBounds(520, 220, 200, 160);
		
		addItem = new ProductForm();
		addItem.setBounds(165, 10, 600, 390);
		
		supplierForm = new SupplierForm();
		supplierForm.setBounds(165, 10, 400, 390);
		/// ADD ITEM - > p2.setBounds(165, 10, 600, 390);
		/// ADD SUPPLIER - > p2.setBounds(165, 10, 400, 390);
		accountform = new AccountForm();
		accountform.setBounds(165, 10, 400, 390);
		
		/*add(p);
		add(p3);
		add(p4);*/
//		add(p2);
		hideRightPanel();
		
		add(accounts);
		add(logtype);
		add(notetype);
		add(units);
		add(conditions);
		add(categories);
		add(addItem);
		add(supplierForm);
		add(accountform);
		
//		add(addStock);
	
		add(stocks);
		add(stockPurchase);
		add(salesOrder);
		add(itemTags);
		add(account);
		add(types);
		add(supplier);
		add(salesOrder);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		gradient = new GradientPaint(0, 0, new Color(169,169,169),0, getHeight(), new Color(40,40,40));
		g2.setPaint(gradient);
		g2.fill(g.getClipBounds());
		
		g2.setColor(new Color(150, 150, 150));
		g2.drawRoundRect(2, 4, getWidth()-5, getHeight() - 8, 20, 20);
		

		g2.setFont(new Font("Trajan Pro", Font.BOLD, 15));
		g2.setColor(Color.white);
		g2.drawString("Total Cost of", 14, 330);
		g2.drawString("Goods in Stock:", 2, 345);
		
		g2.setColor(Color.GREEN.brighter());
		g2.setFont(new Font("Harabara", Font.BOLD, 22));
		g2.drawString("P 125620.50", 14, 375);
		
		
		paintChildren(g2);

		g2.dispose();
		g.dispose();
	}
	
	private void addListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!isRunning) {
					startAnimation();
				}
			}
		});
	}
	
	private void hideRightPanel()
	{
		accounts.setVisible(false);
		logtype.setVisible(false);
		notetype.setVisible(false);
		units.setVisible(false);
		conditions.setVisible(false);
		categories.setVisible(false);
		addItem.setVisible(false);
		supplierForm.setVisible(false);
		accountform.setVisible(false);
	}
	
	public void startAnimation() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		if (currWidth == minWidth)
			showPanel();
		else
			hidePanel();
	}

	private void hidePanel() {
		while (isRunning) {
			currWidth--;
			setPrefSize(currWidth, getHeight());
			updateUI();


			if (currWidth <= minWidth)
				isRunning = false;
		}
		//showAllComponents(true);
		
		hideRightPanel();
	}

	private void showPanel() {
		while (isRunning) {
			currWidth++;
			setPrefSize(currWidth, getHeight());
			updateUI();

			if (currWidth >= maxWidth)
				isRunning = false;
		}
		//showAllComponents(false);
	}

	private void showAllComponents(boolean truth) {
		for (int i = 0; i < getComponentCount(); i++)
			getComponent(i).setVisible(truth);
	}
}
