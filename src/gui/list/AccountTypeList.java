package gui.list;

import gui.list.util.ItemTags;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import util.Values;
import util.soy.RoundedPanel;

public class AccountTypeList extends RoundedPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -886696782349218678L;
	private JPanel accountTypePanel, p1, p2, p3;
	private JLabel label;
	private JScrollPane pane;
	private Icon icon;

	public AccountTypeList() {
		init();
		addComponents();

		Values.accountTypeList = this;
	}

	private void init() {
		// TODO Auto-generated method stub
		setLayout(new BorderLayout());
		setBackground(Color.MAGENTA);

		pane = new JScrollPane();

		icon = new ImageIcon("images/account_icon.png");

		p1 = new JPanel();

		p2 = new JPanel();

		p3 = new JPanel();

		// setPreferredSize(new Dimension(150, 200));

		label = new JLabel("Account Type", icon, SwingConstants.LEFT);

	}

	private void addComponents() {
		// TODO Auto-generated method stub
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Harabara", Font.PLAIN, 16));

		pane.setOpaque(false);
		pane.setBorder(BorderFactory.createEmptyBorder());

		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);

		fillPanel(false);

		add(label, BorderLayout.PAGE_START);
		add(p1, BorderLayout.LINE_START);
		add(p2, BorderLayout.LINE_END);
		add(p3, BorderLayout.PAGE_END);

		add(pane, BorderLayout.CENTER);
	}

	public void fillPanel(boolean refresh) {
		if (refresh)
			remove(getComponent(getComponentCount() - 1));

		accountTypePanel = new JPanel(new GridLayout(0, 1, 0, 0));
		accountTypePanel.setOpaque(false);

//		if (Manager.loggedInAccount.getAccountType().getName()
//				.equals(AccountType.manager))
			accountTypePanel.add(new ItemTags("Add new type of account",
					Values.ACCOUNT_TYPE, false, null));

	/*	List<AccountType> accountTypes = new ArrayList<AccountType>();
		try {
			accountTypes = Manager.accountManager.getAccountTypes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (AccountType accountType : accountTypes) {
			accountTypePanel.add(new ItemTags(accountType.getName(),
					Values.ACCOUNT_TYPE, true, accountType));
		}

		try {
			accountTypePanel
					.setPreferredSize(new Dimension(
							150,
							(Manager.accountManager.getAccountTypes().size() * 25) + 50));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		pane.setViewportView(accountTypePanel);

		if (refresh) {
			add(pane, BorderLayout.CENTER);
			updateUI();
			revalidate();
		}

	}

}
