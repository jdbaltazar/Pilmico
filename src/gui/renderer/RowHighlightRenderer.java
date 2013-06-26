package gui.renderer;

import java.awt.Color;
import java.awt.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import util.Values;

public class RowHighlightRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Set<Integer> highlightedRows = new HashSet<Integer>();
	Color selectedColor = new Color(0, 255, 255), backgroundColor1 = new Color(245, 245, 220), backgroundColor2 = new Color(220, 220, 220),
			alertColor = new Color(255, 140, 140);

	private boolean onAlert;
	private int alertOnQuantity, quantity, itemId;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		// column 5 for quantity

		// System.out.println("value at column 0: "
		// + Integer.parseInt(table.getValueAt(row, 0) + ""));

		setHorizontalAlignment(SwingConstants.CENTER);

		if (row % 2 == 0)
			setBackground(backgroundColor1);
		else
			setBackground(backgroundColor2);

		if (isSelected) {
			setBackground(selectedColor);
			setForeground(Color.BLACK);

			// System.out.println(table.getModel().getValueAt(row,
			// table.getModel().getColumnCount()-2));
		}/*
		 * else setForeground(Color.BLACK);
		 */
		/*if (table.getColumnCount() > 7) {

			// JTable target = (JTable) arg0.getSource();
			// int row = target.getSelectedRow();
			// row = target.convertRowIndexToModel(row);
			//
			// getValueAt(convertRowIndexToModel(row), 1)

			// itemId = Integer.parseInt(table.getValueAt(row, 0) + "") - 1;
			// quantity = Integer.parseInt(table.getValueAt(row, 5) + "");

			@SuppressWarnings("unchecked")
			List<Item> items = (List<Item>) Values.tablePanel.getSoyTable().getObjects();

			itemId = Integer.parseInt((String) table.getValueAt(table.convertRowIndexToModel(row), 0));

			try {

				Item item = items.get(table.convertRowIndexToModel(row));
				// Item item = Manager.itemManager.getItem(itemId);
				onAlert = item.isAllowAllert();
				if (onAlert) {
					onAlert = item.getUnitsOnStock() <= item.getAlertOnQuantity();
				}

				
				 * quantity = Manager.itemManager.getItems()
				 * .get(Integer.parseInt(table.getValueAt(row, 0) + "") - 1)
				 * .getUnitsOnStock();
				 

				// alertOnQuantity =
				// Manager.itemManager.getItems().get(itemId).getAlertOnQuantity();
				// onAlert =
				// Manager.itemManager.getItems().get(itemId).isAllowAllert();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (onAlert) {
				setBackground(alertColor);
			}
		}*/

		return this;
	}
}
