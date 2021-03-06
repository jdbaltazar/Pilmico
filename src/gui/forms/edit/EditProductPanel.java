package gui.forms.edit;

import gui.forms.util.EditFormField;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormLabel;
import gui.forms.util.HistoryTable;
import gui.forms.util.SimpleNumericField;
import gui.popup.SuccessPopup;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.RoundedBalloonStyle;
import util.DateFormatter;
import util.EditFormPanel;
import util.ErrorLabel;
import util.FormCheckbox;
import util.SBButton;
import util.Tables;
import util.Utility;
import util.Values;
import util.soy.SoyButton;

import common.entity.product.Category;
import common.entity.product.Price;
import common.entity.product.Product;
import common.manager.Manager;

@SuppressWarnings({ "unchecked" })
public class EditProductPanel extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<EditFormField> fields = new ArrayList<EditFormField>();
	private ArrayList<SimpleNumericField> numfields = new ArrayList<SimpleNumericField>();
	private ArrayList<FormLabel> labels = new ArrayList<FormLabel>();
	private SoyButton edit;

	private JPanel panel;
	private JScrollPane scrollpane;
	private SBButton priceHistory;

	private int num = Tables.productFormLabel.length;

	private FormDropdown category;
	private JCheckBox cbox1, cbox2;
	private ErrorLabel error;

	private BalloonTip balloonTip;

	private SBButton sackConvert;

	private int y1 = 35, y2 = 20, y3 = 76;// 62

	private Product product;

	private JPanel formPanel;
	private JScrollPane scrollPane;

	private String msg = "";

	/*
	 * public EditItemPanel(Item item) { super("View / Edit Stock"); this.item =
	 * item; init(); addComponents(); fillValues(); }
	 */

	public EditProductPanel(Product product) {
		super("View / Edit Product");
		this.product = product;
		init();
		addComponents();
		fillEntries();
		Values.editProductPanel = this;
	}

	private void init() {
		// TODO Auto-generated method stub

		setLayout(null);

		formPanel = new JPanel();
		formPanel.setLayout(null);
		formPanel.setOpaque(false);

		scrollPane = new JScrollPane();

		error = new ErrorLabel();

		category = new FormDropdown(true);

		cbox1 = new FormCheckbox("Available?*", true);
		cbox2 = new FormCheckbox("Allow alert?*", true);

		cbox2.setToolTipText("show alert if out of stock?");

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollpane = new JScrollPane(panel);
		scrollpane.setOpaque(false);
		scrollpane.getViewport().setOpaque(false);
		scrollpane.setBorder(BorderFactory.createEmptyBorder());

		priceHistory = new SBButton("pricehistory.png", "pricehistory2.png", "Price History");

		priceHistory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				initBalloonTip();

				balloonTip.setVisible(true);
				priceHistory.setEnabled(false);

			}

		});
	}

	private void addComponents() {

		// sackConvert = new SBButton("transfer.png", "transfer.png",
		// "Display one (1) sack");
		sackConvert = new SBButton("convert.png", "convert.png", "Display one (1) sack");

		edit = new SoyButton("Edit");
		int fieldsCtr = 0, numFieldsCtr = 0, labelsCtr = 0;

		for (int i = 0, x = 5, y = 0; i < num; i++, y += y3) {

			if (i == 3) {// 4
				x += 230;
				y = 0;
			}

			if (i == 7) {
				x += 230;
				y = 0;
			}

			if (i < 2) {
				fields.add(new EditFormField(100));
				labels.add(new FormLabel(Tables.productFormLabel[i]));

				fields.get(fieldsCtr).setBounds(x, y1 + y, 170, 25);
				labels.get(labelsCtr).setBounds(x, y2 + y, 170, 15);

				fieldsCtr++;
				labelsCtr++;

			}

			if (i >= 3 && i <= 7) {
				numfields.add(new SimpleNumericField(10, " "));
				labels.add(new FormLabel(Tables.productFormLabel[i]));

				numfields.get(numFieldsCtr).setBounds(x, y1 + y, 170, 25);
				labels.get(labelsCtr).setBounds(x, y2 + y, 170, 15);

				numFieldsCtr++;
				labelsCtr++;

				if (i == 5) {
					sackConvert.setBounds(x + 130, y2 + y - 3, 16, 16);
				}
			}

			if (i == 2)
				cbox1.setBounds(x, y1 + y, 170, 25);
			if (i == 9)
				cbox2.setBounds(x, y1 + y, 170, 25);

			if (i == 8) {

				category.setBounds(x, y1 + y, 170, 25);

				labels.add(new FormLabel(Tables.productFormLabel[i]));
				labels.get(labelsCtr).setBounds(x, y2 + y, 120, 15);

				labelsCtr++;
			}

			/*
			 * if(i==4 || i == 9){ //x=0; //y+=70; y=0; x+=230; }
			 */
			// 3,4,8,9,11

		}

		edit.setBounds(367, 348, 80, 30);

		error.setBounds(515, 325, 250, 22);

		for (int i = 0; i < numfields.size(); i++)
			formPanel.add(numfields.get(i));

		for (int i = 0; i < fields.size(); i++)
			formPanel.add(fields.get(i));

		for (int i = 0; i < labels.size(); i++) {
			formPanel.add(labels.get(i));
		}

		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (isValidated() && hasValidInputs()) {
					try {

						product.setName(fields.get(0).getText());
						product.setDescription(fields.get(1).getText());
						product.setKilosPerSack(Double.parseDouble(numfields.get(0).getText()));

						product.setQuantity(Double.parseDouble(numfields.get(1).getText()), Double.parseDouble(numfields.get(2).getText()));
						// product.setQuantityOnDisplay(Double.parseDouble(numfields.get(3).getText()),
						// Double.parseDouble(numfields.get(4).getText()));

						double pricePerSack = Double.parseDouble(numfields.get(3).getText());
						double pricePerKilo = Double.parseDouble(numfields.get(4).getText());

						// check if price is the same with old
						if (pricePerSack != product.getCurrentPricePerSack() || pricePerKilo != product.getCurrentPricePerKilo()) {
							product.addPrice(new Price(product, new Date(), pricePerSack, pricePerKilo));
						}

						product.setAvailable(cbox1.isSelected());
						product.setCategory((Category) category.getSelectedItem());

						Manager.getInstance().getProductManager().updateProduct(product);

						update();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else
					error.setToolTip(msg);
			}
		});

		try {
			List<Category> cats = Manager.getInstance().getProductManager().getCategories();

			for (Category cat : cats) {
				category.addItem(cat);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		priceHistory.setBounds(20, 12, 16, 16);

		panel.add(priceHistory);
		scrollpane.setBounds(750, 365, 50, 35);

		add(edit);
		add(scrollpane);

		// formPanel.add(sackConvert);
		formPanel.add(cbox1);
		formPanel.add(cbox2);
		formPanel.add(category);

		add(error);

		scrollPane.setViewportView(formPanel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(80, 20, 650, 310);

		add(scrollPane);
	}

	private void initBalloonTip() {

		String[] priceHistoryheaders = { "Updated on", "Price (sack)", "Price (kg)" };
		List<Price> prices = product.getPriceHistory();

		String[][] entries = new String[prices.size()][priceHistoryheaders.length];
		// String[][] entries = { { "21 Jun 2013 10:47 AM", "101.50", "46.25" } };

		int i = 0;
		for (Price p : prices) {
			entries[i][0] = DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(p.getDateUpdated());
			entries[i][1] = String.format("%.2f", p.getPricePerSack());
			entries[i][2] = String.format("%.2f", p.getPricePerKilo());
			i++;
		}

		balloonTip = new BalloonTip(priceHistory, new HistoryTable(priceHistoryheaders, entries), new RoundedBalloonStyle(7, 7,
				Color.decode("#F5FFFA"), Color.decode("#BDFF59")),// ,
																					// Color.decode("#B2CCCC")),
				BalloonTip.Orientation.RIGHT_ABOVE, BalloonTip.AttachLocation.NORTH, 7, 12, false);
		balloonTip.setPadding(5);
		balloonTip.setVisible(false);
		balloonTip.setCloseButton(BalloonTip.getDefaultCloseButton(), false, false);

		balloonTip.getCloseButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				priceHistory.setEnabled(true);
			}
		});
	}

	private void fillEntries() {

		fields.get(0).setText(product.getName());
		fields.get(1).setText(product.getDescription());
		numfields.get(0).setText(product.getKilosPerSackDescription());
		numfields.get(1).setText(product.getSacksDescription());
		numfields.get(2).setText(product.getKilosOnDisplayDescription());
		// numfields.get(3).setText(String.format("%.2f",
		// product.getQuantityOnDisplayInSack()));
		// numfields.get(4).setText(String.format("%.2f",
		// product.getQuantityOnDisplayInKilo()));
		numfields.get(3).setText(String.format("%.2f", product.getCurrentPricePerSack()));
		numfields.get(3).setToolTipText(
				"Updated on " + DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(product.getCurrentPrice().getDateUpdated()));
		numfields.get(4).setText(String.format("%.2f", product.getCurrentPricePerKilo()));
		numfields.get(4).setToolTipText(
				"Updated on " + DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(product.getCurrentPrice().getDateUpdated()));
		// fields.get(fields.size() - 1).setText(String.format("%.2f",
		// product.getAlertOnQuantity()));

		cbox1.setSelected(product.isAvailable());
		// cbox2.setSelected(product.alertUsingSack());
		// numfields.get(7).setText("0.00");

		int total = category.getItemCount();
		while (total > 0) {
			total--;
			Category c = (Category) category.getItemAt(total);
			if (c.getId() == product.getCategory().getId()) {
				category.setSelectedIndex(total);
				break;
			}
		}
	}

	private void update() {
		Values.editPanel.startAnimation();
		new SuccessPopup("Edit").setVisible(true);
		Values.centerPanel.changeTable(Values.PRODUCTS);
	}

	private boolean hasValidInputs() {

		if (Double.parseDouble(numfields.get(0).getText()) == 0d) {

			msg = "kilos per sack should not be 0 ";

			return false;
		}

		/*
		 * if (product.getQuantityInSack() <
		 * Double.parseDouble(numfields.get(3).getText())) {
		 * 
		 * msg =
		 * "sack on display already exceeds the total quantity in sack ("+product
		 * .getQuantityInSack()+") ";
		 * 
		 * return false; }
		 */

		// on display in kilos
		/*
		 * if (product.getTotalQuantityInKilo() <
		 * Double.parseDouble(numfields.get(2).getText())) {
		 * 
		 * msg = "kilos on display already exceeds the total quantity in kilos ("
		 * + product.getTotalQuantityInKilo() + ") ";
		 * 
		 * return false; }
		 */

		return true;
	}

	private boolean isValidated() {

		for (int i = 0; i < fields.size(); i++)
			if (fields.get(i).getText().equals("") && i != 1) {

				msg = "All fields EXCEPT for Description are required ";
				return false;
			}

		for (int i = 0; i < numfields.size(); i++)
			if (numfields.get(i).getText().equals("")) {

				msg = "All fields EXCEPT for Description are required ";
				return false;
			}

		try {
			if (!product.getName().equals(fields.get(0).getText().trim())) {
				if (Manager.getInstance().getProductManager().productExists(fields.get(0).getText().trim())) {
					msg = "Product \'" + fields.get(0).getText() + "\' already exists";
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public void closeBalloonPanel() {
		if (balloonTip != null)
			balloonTip.setVisible(false);
	}

}
