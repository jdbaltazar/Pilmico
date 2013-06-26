package gui.list;

import gui.list.util.ItemTags;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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


public class CategoryList extends RoundedPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel categoryPanel, p1, p2, p3;
	private JLabel label;
	private JScrollPane pane;
	private Icon icon;

	private int ROW_HEIGHT = 100;

	public CategoryList() {
		init();
		addComponents();

		Values.categoryList = this;
	}

	private void init() {
		// TODO Auto-generated method stub
		setLayout(new BorderLayout());
		setBackground(Color.ORANGE);

		pane = new JScrollPane();

		icon = new ImageIcon("images/item_category_icon.png");

		p1 = new JPanel();

		p2 = new JPanel();

		p3 = new JPanel();

		// setPreferredSize(new Dimension(150, 200));

		label = new JLabel("Item Category", icon, SwingConstants.LEFT);

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

		categoryPanel = new JPanel();
		categoryPanel.setLayout(null);

		categoryPanel.setOpaque(false);
		categoryPanel.setPreferredSize(new Dimension(150, 125));

		categoryPanel.add(new ItemTags("Add new item category", "Description",
				Values.CATEGORY, false, categoryPanel.getComponentCount()
						* ROW_HEIGHT, null));

		/*try {
			List<Category> itemCategories = Manager.itemManager.getCategories();
			for (Category category : itemCategories) {
				categoryPanel.add(new ItemTags(category.getName(), category
						.getDescription(), Values.UNITS, true, categoryPanel
						.getComponentCount() * ROW_HEIGHT, category));
				categoryPanel.setPreferredSize(new Dimension(10, categoryPanel
						.getComponentCount() * ROW_HEIGHT));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		pane.setViewportView(categoryPanel);

		if (refresh) {
			add(pane, BorderLayout.CENTER);
			updateUI();
			revalidate();
		}
	}

}
