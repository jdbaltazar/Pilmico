package gui.forms.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import common.entity.sales.SalesDetail;

import util.Values;

public class EditRowPanel extends JPanel {

	private JPanel row;
	private JPanel panel = new JPanel();
	private ArrayList<ViewFormField> formField = new ArrayList<ViewFormField>();
	private JLabel label, totalLabel;
	private int ROW_WIDTH = 580, ROW_HEIGHT = 35, y, table = 0, componentCount;
	private Object object;

	public EditRowPanel(Object object, JPanel panel, int table) {

		this.object = object;
		this.panel = panel;
		this.table = table;

		init();
	}

	private void init() {
		y = panel.getComponentCount() * ROW_HEIGHT;

		setLayout(new BorderLayout());

		row = new JPanel();
		row.setOpaque(false);
		row.setLayout(null);
		row.setBackground(Color.decode("#FFFFE6"));

		if (table == Values.SALES || table == Values.DELIVERY || table == Values.PULLOUT || table == Values.ACCOUNT_RECEIVABLES) {

			if (table == Values.SALES) {
				SalesDetail sd = (SalesDetail) object;
				drawProductRow(sd.getQuantityInSack(), sd.getQuantityInKilo(), sd.getPricePerSack(), sd.getPricePerKilo(), sd.getProduct().getName());
			}else if (table == Values.DELIVERY) {
				// SalesDetail sd = (SalesDetail) object;
				// drawProductRow(sd.getQuantityInSack(), sd.getQuantityInKilo(), sd.getPricePerSack(), sd.getPricePerKilo(), sd.getProduct().getName());
			}
			
			setBounds(0, y, 580, ROW_HEIGHT);

		}

		// System.out.println("y: "+y+ " panel width: "+panel.getWidth());
	}

	private void drawProductRow(double sack, double kg, double pricePerSack, double pricePerKg, String productName) {

		ROW_HEIGHT = 35;

		formField.add(new ViewFormField(sack + ""));
		formField.get(formField.size() - 1).setBounds(0, 0, 77, ROW_HEIGHT);

		formField.add(new ViewFormField(kg + ""));
		formField.get(formField.size() - 1).setBounds(formField.get(formField.size() - 2).getX() + formField.get(formField.size() - 2).getWidth(), 0,
				77, ROW_HEIGHT);

		formField.add(new ViewFormField(pricePerSack + ""));
		formField.get(formField.size() - 1).setBounds(formField.get(formField.size() - 2).getX() + formField.get(formField.size() - 2).getWidth(), 0,
				85, ROW_HEIGHT);

		formField.add(new ViewFormField(pricePerKg + ""));
		formField.get(formField.size() - 1).setBounds(formField.get(formField.size() - 2).getX() + formField.get(formField.size() - 2).getWidth(), 0,
				77, ROW_HEIGHT);

		formField.add(new ViewFormField(productName));
		formField.get(formField.size() - 1).setBounds(formField.get(formField.size() - 2).getX() + formField.get(formField.size() - 2).getWidth(), 0,
				249, ROW_HEIGHT);

		for (int i = 0; i < formField.size(); i++) {
			formField.get(i).setFont(new Font("Arial Narrow", Font.PLAIN, 12));
			row.add(formField.get(i));
		}

		row.setOpaque(true);
		// row.setBorder(BorderFactory.createEtchedBorder());

		add(row);

	}

	private JLabel getALabel(String str) {
		JLabel label = new JLabel(str);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		return label;
	}

	public JPanel getRow() {
		return row;
	}

}