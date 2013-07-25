package gui.forms.util;

import gui.forms.add.InventorySheetForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.DateFormatter;
import util.Utility;
import util.Values;

import common.entity.delivery.Delivery;
import common.entity.discountissue.DiscountIssue;
import common.entity.inventorysheet.InventorySheetDetail;
import common.entity.sales.Sales;

public class ISRowPanel extends JPanel {

	private String[] moneyString = { "1000", "500", "200", "100", "50", "20",
			"coins" };
	private JTextField field;
	private JPanel row;
	private JPanel panel = new JPanel();
	private ArrayList<ViewFormField> formField = new ArrayList<ViewFormField>();
	private JLabel label, totalLabel;
	private int ROW_WIDTH = 580, ROW_HEIGHT = 35, y, table = 0, componentCount;
	private Object object;
	private Color rowBkgrndColor;

	public ISRowPanel(Object object, JPanel panel, int table) {

		this.object = object;
		this.panel = panel;
		this.table = table;
		init();
	}

	private void init() {
		formField = new ArrayList<ViewFormField>();
		y = panel.getComponentCount() * ROW_HEIGHT;

		setLayout(new BorderLayout());

		row = new JPanel();
		row.setOpaque(false);
		row.setLayout(null);
		row.setBackground(Color.decode("#FFFFE6"));

		selectTable();

		if (table == Values.PRODUCTS)
			setBounds(0, y, InventorySheetForm.PRODUCT_ROW_WIDTH, ROW_HEIGHT);
		else if (table == Values.DISCOUNTS)
			setBounds(0, y, 250, ROW_HEIGHT);
		else if (table == Values.OTHERS)
			setBounds(0, y, panel.getWidth(), ROW_HEIGHT);
		else
			setBounds(0, y, InventorySheetForm.TRANSACTIONS_ROW_WIDTH,
					ROW_HEIGHT);
	}

	private void selectTable() {

		switch (table) {

		case Values.PRODUCTS:
			drawProductInventory();
			break;

		case Values.SALES:
			Sales sales = (Sales) object;
			
			fillRow(sales,
					DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat)
							.format(sales.getDate()), sales.getCashier()
							.getFirstPlusLastName(), sales.getSalesAmount()
							+ "");
			break;
			
		case Values.DELIVERY:
			Delivery del = (Delivery) object;
			
			fillRow(del,
					DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat)
							.format(del.getDate()), del.getReceivedBy()
							.getFirstPlusLastName(), del.getDeliveryAmount()
							+ "");
			break;
			
		case Values.DISCOUNTS:
			drawDiscountRow();
			break;

		default:
			drawCashBreakDown();
			break;
		}

	}

	private void fillRow(Object object, String column1, String column2,
			String column3) {

		formField.add(new ViewFormField(column1));
		formField.get(formField.size() - 1).setBounds(0, 0,
				InventorySheetForm.DATE_LABEL_WIDTH, ROW_HEIGHT);
		formField.add(new ViewFormField(column2));
		formField.get(formField.size() - 1).setBounds(
				InventorySheetForm.DATE_LABEL_WIDTH, 0,
				InventorySheetForm.ISSUED_BY_LABEL_WIDTH, ROW_HEIGHT);

		formField.add(new ViewFormField(column3));
		formField.get(formField.size() - 1).setBounds(
				InventorySheetForm.DATE_LABEL_WIDTH
						+ InventorySheetForm.ISSUED_BY_LABEL_WIDTH, 0,
				InventorySheetForm.GROSS_LABEL_WIDTH, ROW_HEIGHT);

		for (int i = 0; i < formField.size(); i++) {
			formField.get(i).setFont(new Font("Arial Narrow", Font.PLAIN, 11));
			formField.get(i).addMouseListener(new MouseAdapter() {

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					row.setBackground(rowBkgrndColor);
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					rowBkgrndColor = row.getBackground();
					row.setBackground(Color.decode("#B2FFFF"));
				}

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
			row.add(formField.get(i));
		}

		row.setOpaque(true);
		add(row);

	}

	private void drawProductInventory() {
		ROW_HEIGHT = 35;
		formField.add(new ViewFormField("PELLETS"));
		formField.get(formField.size() - 1).setBounds(0, 0,
				InventorySheetForm.PRODUCT_LABEL_WIDTH, ROW_HEIGHT);
		for (int i = 1, x = formField.get(0).getX()
				+ formField.get(0).getWidth(); i < InventorySheetForm.TOTAL_INVENTORY_LABEL - 3; i++, x += InventorySheetForm.SACK_LABEL_WIDTH) {
			formField.add(new ViewFormField("0.0"));
			formField.get(i).setBounds(0 + x, 1,
					InventorySheetForm.SACK_LABEL_WIDTH, ROW_HEIGHT);
		}
		for (int i = InventorySheetForm.TOTAL_INVENTORY_LABEL - 3, x = 0; i < InventorySheetForm.TOTAL_INVENTORY_LABEL + 1; i++, x += InventorySheetForm.SALES_KG_WIDTH) {
			formField.add(new ViewFormField("0.0"));
			formField.get(i).setBounds(999 + x, 1,
					InventorySheetForm.SALES_KG_WIDTH, ROW_HEIGHT);
		}

		InventorySheetDetail isd = (InventorySheetDetail) object;
		formField.get(0).setText(isd.getProduct().getName());
		formField.get(1).setText(isd.getBeginningInventoryInSack() + "");
		formField.get(2).setText(isd.getBeginningInventoryInKilo() + "");
		formField.get(3).setText(isd.getOnDisplayInSack() + "");
		formField.get(4).setText(isd.getOnDisplayInKilo() + "");
		formField.get(5).setText(isd.getDeliveriesInSack() + "");
		formField.get(6).setText(isd.getDeliveriesInKilo() + "");
		formField.get(7).setText(isd.getPullOutsInSack() + "");
		formField.get(8).setText(isd.getPullOutsInKilo() + "");
		formField.get(9).setText(isd.getEndingInventoryInSack() + "");
		formField.get(10).setText(isd.getEndingInventoryInKilo() + "");
		formField.get(11).setText(isd.getOffTakeInSack() + "");
		formField.get(12).setText(isd.getOffTakeInKilo() + "");
		formField.get(13).setText(isd.getPricePerSack() + "");
		formField.get(14).setText(isd.getPricePerKilo() + "");
		formField.get(15).setText(isd.getSalesAmountForSack() + "");
		formField.get(16).setText(isd.getSalesAmountForKilo() + "");

		for (ViewFormField vff : formField) {
			row.add(vff);
		}

		for (int i = 0; i < formField.size(); i++)
			formField.get(i).setFont(new Font("Arial Narrow", Font.PLAIN, 11));

		row.setOpaque(true);
		row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
				Color.LIGHT_GRAY));
		add(row);

	}

	private void drawDiscountRow() {

		DiscountIssue discount = (DiscountIssue) object;
		formField.add(new ViewFormField(DateFormatter.getInstance()
				.getFormat(Utility.DMYHMAFormat).format(discount.getDate())));
		formField.get(formField.size() - 1).setBounds(0, 0,
				InventorySheetForm.DATE_LABEL_WIDTH, ROW_HEIGHT);
		
		formField.add(new ViewFormField(discount.getAmount()+""));
		formField.get(formField.size() - 1).setBounds(
				InventorySheetForm.DATE_LABEL_WIDTH, 0,
				102, ROW_HEIGHT);

		for (int i = 0; i < formField.size(); i++) {
			
			formField.get(i).setFont(new Font("Arial Narrow", Font.PLAIN, 11));
			formField.get(i).addMouseListener(new MouseAdapter() {

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					row.setBackground(rowBkgrndColor);
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					rowBkgrndColor = row.getBackground();
					row.setBackground(Color.decode("#B2FFFF"));
				}

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
			row.add(formField.get(i));
		}

		row.setOpaque(true);
		add(row);

	}

	private void drawCashBreakDown() {

		JLabel money, x, eq;

		field = new JTextField();
		field.setHorizontalAlignment(JTextField.CENTER);

		totalLabel = new JLabel();
		totalLabel.setHorizontalAlignment(JLabel.CENTER);
		totalLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		componentCount = panel.getComponentCount();
		money = getALabel(moneyString[panel.getComponentCount()]);
		x = getALabel("x");
		eq = getALabel("=");

		// sum = Integer.parseInt(field.getText()) *
		// Integer.parseInt(moneyString[panel.getComponentCount()]);

		money.setBounds(2, 7, 50, 15);
		x.setBounds(60, 5, 20, 20);
		field.setBounds(100, 7, 100, 17);
		field.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {

				if (!field.getText().equals("")) {
					if (componentCount != moneyString.length - 1)
						totalLabel.setText(""
								+ Integer.parseInt(field.getText())
								* Integer.parseInt(moneyString[componentCount]));
					else
						totalLabel.setText(field.getText());
				} else
					totalLabel.setText("");
			}

		});
		eq.setBounds(220, 5, 20, 20);
		totalLabel.setBounds(240, 7, 100, 17);

		if (componentCount != moneyString.length - 1)
			row.add(x);

		row.add(eq);
		row.add(money);
		row.add(field);
		row.add(totalLabel);

		row.setOpaque(true);
		row.setBackground(Color.decode("#CCFFB2"));
		row.setBorder(BorderFactory.createEtchedBorder());

		add(row);

	}

	private JLabel getALabel(String str) {
		JLabel label = new JLabel(str);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		return label;
	}

	public void fillEntries() {

		for (int i = 0; i < formField.size(); i++) {
			// System.out.println("formfield size: "+formField.size()+
			// " formField.get(i).getX(): "+formField.get(i).getX());
			formField.get(i).setText("blabla");
			formField.get(i).setFont(new Font("Arial Narrow", Font.PLAIN, 11));
			row.add(formField.get(i));
		}
	}

	public JPanel getRow() {
		return row;
	}

}
