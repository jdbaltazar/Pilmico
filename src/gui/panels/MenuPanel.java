package gui.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import util.MenuButton;
import util.Values;
import util.soy.SoyPanel;

public class MenuPanel extends JPanel implements ActionListener {

	private JPanel menu;
	private ArrayList<MenuButton> options;
	private String[] btn = { "product.png", "sales.png", "expenses.png", "delivery.png",
			"accountreceivable.png",  "cashadvance.png", "discount.png", "deposit.png",
			"superform.png", "person.png", "log.png" };
	
	private String[] btn2 = { "product2.png", "sales2.png", "expenses2.png", "delivery2.png",
			"accountreceivable2.png",  "cashadvance2.png", "discount2.png", "deposit2.png",
			"superform2.png", "person2.png", "log2.png" };
	
	private String[] hover = { "Products", "Sales", "Expenses", "Deliveries",
			"Account Receivables", "Cash Advance", "Discounts", "Deposits","Inventory Sheet",
			"Profiles", "Logs" };
	
	private final int menuOptions = btn.length;

	public MenuPanel() {
		init();
		addComponents();

		Values.menuPanel = this;
	}

	private void init() {
		// TODO Auto-generated method stub
		options = new ArrayList<MenuButton>();

		setLayout(null);
		setPreferredSize(new Dimension(1, 70));
		// setOpaque(false);
		// setBackground(Color.WHITE);
		setBackground(new Color(56, 56, 56, 20));
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		menu = new SoyPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub

				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);

				Color ppColor = new Color(0, 96, 96, 30); // r,g,b,alpha
				g2.setColor(ppColor);
				g2.fillRect(0, 0, getWidth(), getHeight());

				g2.setStroke(new BasicStroke(2.5f));
				g2.setColor(new Color(72, 72, 72));

				// for (int i = 0, x = 0; i < 4; i++, x += 108)
				g2.drawLine(63, 5, 63, 45);
				g2.drawLine(412, 5, 412, 45);
				g2.drawLine(471, 5, 471, 45);
				g2.drawLine(532, 5, 532, 45);
				// g2.drawLine(171, 5, 171, 45);
				// g2.drawLine(279, 5, 279, 45);

				paintChildren(g2);

				g2.dispose();
				g.dispose();

			}
		};

		menu.setLayout(null);

		menu.setBounds(112, 10, 594, 50);//535

		for (int i = 0, x = 14; i < menuOptions; i++) {

			if (i != 0) {
				if (i == 1 || i == 8 || i == 9 || i == 10)
					x += 60;
				else
					x += 48;
			}
			options.add(new MenuButton(btn[i], btn2[i], btn2[i], hover[i]));
			options.get(i).setBounds(x, 5, 40, 40);

			menu.add(options.get(i));
		}

		for (int i = 0; i < options.size(); i++)
			options.get(i).addActionListener(this);

		add(menu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Values.editPanel.setHide(true);
		Values.editPanel.startAnimation();

		if (e.getSource().equals(options.get(0)))
			Values.centerPanel.changeTable(Values.PRODUCTS);

		if (e.getSource().equals(options.get(1)))
			Values.centerPanel.changeTable(Values.SALES);

		if (e.getSource().equals(options.get(2)))
			Values.centerPanel.changeTable(Values.EXPENSES);

		if (e.getSource().equals(options.get(3)))
			Values.centerPanel.changeTable(Values.DELIVERY);

		if (e.getSource().equals(options.get(4)))
			Values.centerPanel.changeTable(Values.ACCOUNT_RECEIVABLES);

		if (e.getSource().equals(options.get(5)))
			Values.centerPanel.changeTable(Values.CASH_ADVANCE);

		if (e.getSource().equals(options.get(6)))
			Values.centerPanel.changeTable(Values.DISCOUNTS);

		if (e.getSource().equals(options.get(7)))
			Values.centerPanel.changeTable(Values.DEPOSITS);

		if (e.getSource().equals(options.get(8))) 
			Values.centerPanel.changeTable(Values.INVENTORY_SHEET);
			
		if (e.getSource().equals(options.get(9)))
			Values.centerPanel.changeTable(Values.PROFILES);
		
		if (e.getSource().equals(options.get(10)))
			Values.centerPanel.changeTable(Values.LOGS);

	}

}
