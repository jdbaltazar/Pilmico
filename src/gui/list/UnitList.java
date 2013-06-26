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

public class UnitList extends RoundedPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7955574705160786106L;

	private JLabel label;

	private JPanel unitPanel, p1, p2, p3;
	private JScrollPane pane;
	private Icon icon;

	private int ROW_HEIGHT = 100;

	public UnitList() {
		init();
		addComponents();

		Values.unitList = this;
	}

	private void init() {
		// TODO Auto-generated method stub
		setLayout(new BorderLayout());
		setBackground(Color.pink);

		pane = new JScrollPane();

		icon = new ImageIcon("images/item_unit_icon.png");

		p1 = new JPanel();

		p2 = new JPanel();

		p3 = new JPanel();

		// setPreferredSize(new Dimension(150, 200));

		label = new JLabel("Item Unit", icon, SwingConstants.LEFT);

		unitPanel = new JPanel();
		unitPanel.setLayout(null);

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

		unitPanel.setOpaque(false);
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

		unitPanel = new JPanel();
		unitPanel.setLayout(null);

		unitPanel.add(new ItemTags("Add new item unit", "Description",
				Values.UNITS, false,
				unitPanel.getComponentCount() * ROW_HEIGHT, null));

		/*List<Unit> units = new ArrayList<Unit>();
		try {
			units = Manager.itemManager.getUnits();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Unit unit : units) {
			unitPanel.add(new ItemTags(unit.getName(), unit.getDescription(),
					Values.UNITS, true, unitPanel.getComponentCount()
							* ROW_HEIGHT, unit));
			unitPanel.setPreferredSize(new Dimension(10, unitPanel
					.getComponentCount() * ROW_HEIGHT));
		}*/

		pane.setViewportView(unitPanel);

		if (refresh) {
			add(pane, BorderLayout.CENTER);
			updateUI();
			revalidate();
		}
	}
}
