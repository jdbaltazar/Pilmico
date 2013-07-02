package gui.forms.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import common.entity.product.Product;
import common.manager.Manager;

import util.JNumericField;
import util.SBButton;
import util.Tables;
import util.Values;

public class RowPanel extends JPanel {

	private int y;
	private String command = "";

	private JComboBox productCombo;
	private int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 85, UPPER_Y = 55;
	private JTextField productComboField;
	private NumericTextField quantitySack, quantityKG;
	private JNumericField priceSack, priceKG;

	private JButton deleteRow, addRow;
	private JPanel row;

	private int quant;
	// private Item item;
	private String[] stocks;
	private int mode;

	/*
	 * public RowPanel(int y, String command, Item item, int quant, int mode) {
	 * this.y = y; this.command = command; // this.item = item; this.quant =
	 * quant; this.mode = mode; init(); }
	 */

	public RowPanel(int y, String command, int quant, int mode) {
		this.y = y;
		this.command = command;
		// this.item = item;
		this.quant = quant;
		this.mode = mode;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub

		if (mode == Values.EDIT)
			ROW_WIDTH = 335;

		setLayout(new BorderLayout());

		row = new JPanel();
		deleteRow = new SBButton("cancel.png", "cancel.png", "Remove");
		// if(mode == Values.EDIT)
		// row.setBorder(BorderFactory.createMatteBorder(0, 1, 1,
		// 1,Color.LIGHT_GRAY));

		// row.setBackground(new Color(245, 245, 220));
		row.setBackground(Color.decode("#FFFFE6"));

		// row.setBorder(BorderFactory.createEtchedBorder());

		row.setLayout(null);

		productCombo = new JComboBox();

		productCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		productCombo.setEditable(true);
		productComboField = (JTextField) productCombo.getEditor().getEditorComponent();
		productComboField.setText("");
		productComboField.addKeyListener(new ComboKeyHandler(productCombo));

		quantitySack = new NumericTextField("", 10, null, null);
		quantityKG = new NumericTextField("", 10, null, null);
		priceSack = new JNumericField(10, JNumericField.DECIMAL, true);
		priceKG = new JNumericField(10, JNumericField.DECIMAL, true);

		priceSack.setPrecision(2);
		priceKG.setPrecision(2);

		// quantitySack.setText(quant + "");

		deleteRow.setActionCommand(command);
		deleteRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				switch (mode) {

				case Values.ADD:
					if (Values.tableUtilPanel.getLabel().contains(Tables.SALES))
						Values.salesForm.removeRow(Integer.parseInt(e.getActionCommand()));
					/*
					 * else Values.stockPurchasePanel.removeRow(Integer.parseInt(e.
					 * getActionCommand()));
					 */
					break;

				case Values.EDIT:
					/*
					 * if (Values.tableUtilPanel.getLabel().contains("SALES ORDER"))
					 * Values
					 * .editSalesOrder.removeRow(Integer.parseInt(e.getActionCommand
					 * ())); else
					 * Values.editStockPurchase.removeRow(Integer.parseInt(
					 * e.getActionCommand()));
					 */
					break;

				default:
					break;
				}

			}
		});

		quantitySack.setBounds(10, 7, 52, 20);
		quantityKG.setBounds(87, 7, 52, 20);
		priceSack.setBounds(167, 7, 57, 20);
		priceKG.setBounds(249, 7, 57, 20);

		if (mode == Values.ADD)
			productCombo.setBounds(323, 7, 190, 20);
		else
			productCombo.setBounds(87, 7, 235, 20);

		deleteRow.setBounds(533, 9, 16, 16);

		List<Product> products = new ArrayList<Product>();
		try {
			products = Manager.productManager.getProducts();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		for (Product p : products) {
			productCombo.addItem(p);
		}

		row.add(quantitySack);
		row.add(quantityKG);
		row.add(priceSack);
		row.add(priceKG);
		row.add(productCombo);

		if (mode != Values.EDIT)
			row.add(deleteRow);

		productCombo.setSelectedIndex(-1);

		boolean found = false;
		/*
		 * if (item != null) { int total = itemCombo.getItemCount(); while (total
		 * > 0) { total--; Item i = (Item) itemCombo.getItemAt(total);
		 * System.out.println("item compared to another w/ id: " + i.getId()); if
		 * (item.getId() == i.getId()) { itemCombo.setSelectedIndex(total); //
		 * itemComboField.setText(item.getName());
		 * System.out.println("found. cbox set to: " + total); total = 0; found =
		 * true; }
		 * 
		 * } if (!found) System.out.println("item not found!"); }
		 */

		add(row);
		setBounds(0, y, ROW_WIDTH, ROW_HEIGHT);

		// itemsPanel.setPreferredSize(new Dimension(330,
		// itemsPanel.getComponentCount()
		// * ROW_HEIGHT));

	}

	public int getQuantityInSack() {
		return Integer.parseInt(quantitySack.getText());
	}

	public int getQuantityInKilo() {
		return Integer.parseInt(quantitySack.getText());
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
		return productCombo.getSelectedItem();
	}

}
