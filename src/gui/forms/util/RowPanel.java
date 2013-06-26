package gui.forms.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.SBButton;
import util.Values;

public class RowPanel extends JPanel {

	private int y;
	private String command = "";

	private JComboBox itemCombo;
	private int ROW_WIDTH = 350, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 85, UPPER_Y = 55;
	private JTextField itemComboField;
	private NumericTextField quantity;

	private JButton deleteRow, addRow;
	private JPanel row;

	private int quant;
	//private Item item;
	private String[] stocks;
	private int mode;

	/*public RowPanel(int y, String command, Item item, int quant, int mode) {
		this.y = y;
		this.command = command;
	//	this.item = item;
		this.quant = quant;
		this.mode = mode;
		init();
	}*/
	
	public RowPanel(int y, String command, int quant, int mode) {
		this.y = y;
		this.command = command;
	//	this.item = item;
		this.quant = quant;
		this.mode = mode;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
		if(mode == Values.EDIT)
			ROW_WIDTH = 335;

		setLayout(new BorderLayout());

		row = new JPanel();
		//if(mode == Values.EDIT)
			//row.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1,Color.LIGHT_GRAY));
		row.setBackground(new Color(245, 245, 220));
		 //row.setBorder(BorderFactory.createEtchedBorder());

		row.setLayout(null);

		try {
	//		List<Item> items = Manager.itemManager.getItems();

			//System.out.println("items inputted: " + items.size());

			// stocks = new String[items.size()];
			//
			// for (int i = 0; i < items.size(); i++) {
			// stocks[i] = items.get(i).getName();
			// }
			itemCombo = new JComboBox();
		} catch (Exception e) {
			e.printStackTrace();
		}

		itemCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		itemCombo.setEditable(true);
		itemComboField = (JTextField) itemCombo.getEditor().getEditorComponent();
		itemComboField.setText("");
		itemComboField.addKeyListener(new ComboKeyHandler(itemCombo));

		quantity = new NumericTextField("", 6, null, null);
		quantity.setText(quant + "");
		quantity.setHorizontalAlignment(SwingConstants.CENTER);
		quantity.setFont(new Font("Lucida Grande", Font.PLAIN, 11));

		deleteRow = new SBButton("cancel.png", "cancel.png", "Remove");

		deleteRow.setActionCommand(command);
		deleteRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				/*switch(mode){
				
				case Values.ADD:
					if (Values.tableUtilPanel.getLabel().contains("SALES ORDER"))
						Values.salesOrderForm.removeRow(Integer.parseInt(e.getActionCommand()));
					else
						Values.stockPurchasePanel.removeRow(Integer.parseInt(e.getActionCommand()));
					break;
					
				case Values.EDIT:
					if (Values.tableUtilPanel.getLabel().contains("SALES ORDER"))
						Values.editSalesOrder.removeRow(Integer.parseInt(e.getActionCommand()));
					else
						Values.editStockPurchase.removeRow(Integer.parseInt(e.getActionCommand()));
					break;
					
				default:
					break;
				}*/
				
			}
		});

		quantity.setBounds(10, 7, 52, 20);
		
		if(mode == Values.ADD)
			itemCombo.setBounds(85, 7, 190, 20);
		else
			itemCombo.setBounds(87, 7, 235, 20);
		
		deleteRow.setBounds(300, 7, 16, 16);

		
		row.add(quantity);
		row.add(itemCombo);
		
		if(mode!=Values.EDIT)
			row.add(deleteRow);

		itemCombo.setSelectedIndex(-1);

		boolean found = false;
		/*if (item != null) {
			int total = itemCombo.getItemCount();
			while (total > 0) {
				total--;
				Item i = (Item) itemCombo.getItemAt(total);
				System.out.println("item compared to another w/ id: " + i.getId());
				if (item.getId() == i.getId()) {
					itemCombo.setSelectedIndex(total);
					// itemComboField.setText(item.getName());
					System.out.println("found. cbox set to: " + total);
					total = 0;
					found = true;
				}

			}
			if (!found)
				System.out.println("item not found!");
		}*/

		add(row);
		setBounds(0, y, ROW_WIDTH, ROW_HEIGHT);

		// itemsPanel.setPreferredSize(new Dimension(330,
		// itemsPanel.getComponentCount()
		// * ROW_HEIGHT));

	}

	public int getQuantity() {
		return Integer.parseInt(quantity.getText());
	}

	public JButton getDeleteRow() {
		return deleteRow;
	}

	public void setDeleteRow(JButton deleteRow) {
		this.deleteRow = deleteRow;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Object getSelectedItem() {
		return itemCombo.getSelectedItem();
	}

}
