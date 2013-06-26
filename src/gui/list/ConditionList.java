package gui.list;

import gui.list.util.ItemTags;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import util.Values;
import util.soy.RoundedPanel;

public class ConditionList extends RoundedPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4339958879683811678L;

	private JLabel label;

	private JPanel conditionPanel, p1, p2, p3;
	private JScrollPane pane;
	private Icon icon;

	private int ROW_HEIGHT = 100;

	public ConditionList() {
		init();
		addComponents();
		
		Values.conditionList = this;
	}

	private void init() {
		// TODO Auto-generated method stub
		setLayout(new BorderLayout());
		setBackground(Color.green);

		pane = new JScrollPane();

		icon = new ImageIcon("images/item_condition_icon.png");

		p1 = new JPanel();

		p2 = new JPanel();

		p3 = new JPanel();

		// setPreferredSize(new Dimension(150, 200));

		label = new JLabel("Item Condition", icon, SwingConstants.LEFT);

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

		conditionPanel = new JPanel();
		conditionPanel.setLayout(null);

		conditionPanel.setOpaque(false);

		conditionPanel.setPreferredSize(new Dimension(150, 125));

		conditionPanel.add(new ItemTags("Add new item condition",
				"Description", Values.CONDITION, false, conditionPanel
						.getComponentCount() * ROW_HEIGHT, null));

		/*List<ItemCondition> itemConditions = new ArrayList<ItemCondition>();
		try {
			itemConditions = Manager.itemManager.getConditions();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ItemCondition itemCondition : itemConditions) {
			conditionPanel.add(new ItemTags(itemCondition.getName(),
					itemCondition.getDescription(), Values.CONDITION, true,
					conditionPanel.getComponentCount() * ROW_HEIGHT,
					itemCondition));
			conditionPanel.setPreferredSize(new Dimension(10, conditionPanel
					.getComponentCount() * ROW_HEIGHT));
		}*/

		pane.setViewportView(conditionPanel);

		if (refresh) {
			add(pane, BorderLayout.CENTER);
			updateUI();
			revalidate();
		}
	}

}
