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

import common.entity.inventorysheet.InventorySheetDetail;
import common.entity.sales.Sales;

public class ISRowPanel extends JPanel {

	private String[] moneyString = { "1000", "500", "200", "100", "50", "20", "coins" };
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

		switch (table) {

		case Values.PRODUCTS:
			drawProductInventory();
			// System.out.println("ROW_HEIGHT: "+ROW_HEIGHT);
			setBounds(0, y, InventorySheetForm.PRODUCT_ROW_WIDTH, ROW_HEIGHT);
			break;

		case Values.SALES:
			drawSalesRow();
			// System.out.println("ROW_HEIGHT: "+ROW_HEIGHT);
			setBounds(0, y, InventorySheetForm.TRANSACTIONS_ROW_WIDTH, ROW_HEIGHT);
			break;

		default:
			drawCashBreakDown();
			setBounds(0, y, panel.getWidth(), ROW_HEIGHT);
			break;
		}

		// System.out.println("y: "+y+ " panel width: "+panel.getWidth());
	}

	private void drawProductInventory() {
		ROW_HEIGHT = 35;
		formField.add(new ViewFormField("PELLETS"));
		formField.get(formField.size() - 1).setBounds(0, 0, InventorySheetForm.PRODUCT_LABEL_WIDTH, ROW_HEIGHT);
		for (int i = 1, x = formField.get(0).getX() + formField.get(0).getWidth(); i < InventorySheetForm.TOTAL_INVENTORY_LABEL - 3; i++, x += InventorySheetForm.SACK_LABEL_WIDTH) {
			formField.add(new ViewFormField("0.0"));
			formField.get(i).setBounds(0 + x, 1, InventorySheetForm.SACK_LABEL_WIDTH, ROW_HEIGHT);
		}
		for (int i = InventorySheetForm.TOTAL_INVENTORY_LABEL - 3, x = 0; i < InventorySheetForm.TOTAL_INVENTORY_LABEL + 1; i++, x += InventorySheetForm.SALES_KG_WIDTH) {
			formField.add(new ViewFormField("0.0"));
			formField.get(i).setBounds(999 + x, 1, InventorySheetForm.SALES_KG_WIDTH, ROW_HEIGHT);
		}

		InventorySheetDetail isd = (InventorySheetDetail) object;
		formField.get(0).setText(isd.getProduct().getName());
		formField.get(1).setText(String.format("%.2f", isd.getBeginningInventoryInSack()));
		formField.get(2).setText(String.format("%.2f", isd.getBeginningInventoryInKilo()));
		formField.get(3).setText(String.format("%.2f", isd.getOnDisplayInSack()));
		formField.get(4).setText(String.format("%.2f", isd.getOnDisplayInKilo()));
		formField.get(5).setText(String.format("%.2f", isd.getDeliveriesInSack()));
		formField.get(6).setText(String.format("%.2f", isd.getDeliveriesInKilo()));
		formField.get(7).setText(String.format("%.2f", isd.getPullOutsInSack()));
		formField.get(8).setText(String.format("%.2f", isd.getPullOutsInKilo()));
		formField.get(9).setText(String.format("%.2f", isd.getEndingInventoryInSack()));
		formField.get(10).setText(String.format("%.2f", isd.getEndingInventoryInKilo()));
		formField.get(11).setText(String.format("%.2f", isd.getOffTakeInSack()));
		formField.get(12).setText(String.format("%.2f", isd.getOffTakeInKilo()));
		formField.get(13).setText(String.format("%.2f", isd.getPricePerSack()));
		formField.get(14).setText(String.format("%.2f", isd.getPricePerKilo()));
		formField.get(15).setText(String.format("%.2f", isd.getSalesAmountForSack()));
		formField.get(16).setText(String.format("%.2f", isd.getSalesAmountForKilo()));

		for (ViewFormField vff : formField) {
			row.add(vff);
		}

		for(int i = 0; i < formField.size(); i++)
			formField.get(i).setFont(new Font("Arial Narrow", Font.PLAIN, 11));
		
		row.setOpaque(true);
		// row.setBorder(BorderFactory.createEtchedBorder());
		row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		add(row);

	}

	private void drawSalesRow() {

		Sales sales = (Sales) object;
		formField.add(new ViewFormField(DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(sales.getDate())));
		formField.get(formField.size() - 1).setBounds(0, 0, InventorySheetForm.DATE_LABEL_WIDTH, ROW_HEIGHT);
		formField.add(new ViewFormField(sales.getCashier().getFirstPlusLastName()));
		formField.get(formField.size() - 1).setBounds(InventorySheetForm.DATE_LABEL_WIDTH, 0, InventorySheetForm.ISSUED_BY_LABEL_WIDTH, ROW_HEIGHT);
		formField.add(new ViewFormField(String.format("%.2f",sales.getSalesAmount())));
		formField.get(formField.size() - 1).setBounds(InventorySheetForm.DATE_LABEL_WIDTH + InventorySheetForm.ISSUED_BY_LABEL_WIDTH, 0,
				InventorySheetForm.GROSS_LABEL_WIDTH, ROW_HEIGHT);

		for (int i = 0; i < formField.size(); i++) {
			// System.out.println("formfield size: "+formField.size()+
			// " formField.get(i).getX(): "+formField.get(i).getX());
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
						totalLabel.setText("" + Integer.parseInt(field.getText()) * Integer.parseInt(moneyString[componentCount]));
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
