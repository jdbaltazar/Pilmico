package gui.forms.edit;

import gui.forms.util.EditFormField;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormLabel;
import gui.popup.SuccessPopup;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import common.entity.product.Category;
import common.entity.product.Price;
import common.entity.product.Product;
import common.manager.Manager;

import util.EditFormPanel;
import util.ErrorLabel;
import util.FormCheckbox;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class EditProductPanel extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<JTextField> fields = new ArrayList<JTextField>();
	private ArrayList<FormLabel> labels = new ArrayList<FormLabel>();
	private SoyButton edit;

	private int num = Tables.productFormLabel.length;

	private FormDropdown unit, category, condition;
	private JCheckBox cbox1, cbox2;
	private ErrorLabel error;

	private int y1 = 50, y2 = 35, y3 = 62;

	private String name, unitSellingPrice, unitPurchasePrice, unitsOnStock, alertOnQuantity;

	private Product product;

	/*
	 * public EditItemPanel(Item item) { super("View / Edit Stock"); this.item =
	 * item; init(); addComponents(); fillValues(); }
	 */

	public EditProductPanel(Product product) {
		super("View / Edit Product");
		this.product = product;
		init();
		addComponents();
		fillValues();
	}

	private void init() {
		// TODO Auto-generated method stub

		setLayout(null);

		error = new ErrorLabel();

		unit = new FormDropdown();
		category = new FormDropdown();
		condition = new FormDropdown();

		cbox1 = new FormCheckbox("Available?*", true);
		cbox2 = new FormCheckbox("Alert using sack?*", true);

		/*
		 * try { List<Unit> units = Manager.itemManager.getUnits(); List<Category>
		 * categories = Manager.itemManager.getCategories(); List<ItemCondition>
		 * conditions = Manager.itemManager .getConditions();
		 * 
		 * unit = new FormDropdown(units.toArray()); category = new
		 * FormDropdown(categories.toArray()); condition = new
		 * FormDropdown(conditions.toArray());
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

	}

	private void addComponents() {

		edit = new SoyButton("Edit");
		int fieldsCtr = 0, labelsCtr = 0;

		for (int i = 0, x = 90, y = 0; i < num; i++, y += y3) {

			if (i == 4) {
				x += 230;
				y = 0;
			}

			if (i == 9) {
				x += 230;
				y = 0;
			}

			if (i != 2 && i != 10 && i != 11) {
				fields.add(new EditFormField(100));
				labels.add(new FormLabel(Tables.productFormLabel[i]));

				fields.get(fieldsCtr).setBounds(x, y1 + y, 170, 25);
				labels.get(labelsCtr).setBounds(x, y2 + y, 120, 15);

				fieldsCtr++;
				labelsCtr++;

			}

			if (i == 2)
				cbox1.setBounds(x, y1 + y, 170, 25);
			if (i == 11)
				cbox2.setBounds(x, y1 + y, 170, 25);

			if (i == 10) {

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

		edit.setBounds(370, 350, 80, 30);

		error.setBounds(550, 320, 230, 25);

		for (int i = 0; i < fields.size(); i++)
			add(fields.get(i));

		for (int i = 0; i < labels.size(); i++) {
			add(labels.get(i));
		}

		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				product.setName(fields.get(0).getText());
				product.setDescription(fields.get(1).getText());
				product.setKilosPerSack(Double.parseDouble(fields.get(2).getText()));
				product.setQuantityInSack(Double.parseDouble(fields.get(3).getText()));
				product.setQuantityInKilo(Double.parseDouble(fields.get(4).getText()));
				product.setDisplayInSack(Double.parseDouble(fields.get(5).getText()));
				product.setDisplayInKilo(Double.parseDouble(fields.get(6).getText()));

				double pricePerSack = Double.parseDouble(fields.get(7).getText());
				double pricePerKilo = Double.parseDouble(fields.get(8).getText());

				// check if price is the same with old
				if (pricePerSack != product.getPricePerSack() || pricePerKilo != product.getPricePerKilo()) {
					product.addPrice(new Price(product, new Date(), pricePerSack, pricePerKilo));
				}

				product.setAlertOnQuantity(Double.parseDouble(fields.get(fields.size() - 1).getText()));
				product.setAvailable(cbox1.isSelected());
				product.setAlertUsingSack(cbox2.isSelected());
				product.setCategory((Category) category.getSelectedItem());

				try {
					Manager.productManager.updateProduct(product);
					
					System.out.println("Edit successful!!");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		try {
			List<Category> cats = Manager.productManager.getCategories();

			for (Category cat : cats) {
				category.addItem(cat);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		add(edit);

		add(cbox1);
		add(cbox2);
		add(condition);
		add(category);
		add(unit);

		add(error);
	}

	private void fillValues() {

		fields.get(0).setText(product.getName());
		fields.get(1).setText(product.getDescription());
		fields.get(2).setText(product.getKilosPerSack() + "");
		fields.get(3).setText(product.getQuantityInSack() + "");
		fields.get(4).setText(product.getQuantityInKilo() + "");
		fields.get(5).setText(product.getDisplayInSack() + "");
		fields.get(6).setText(product.getDisplayInKilo() + "");
		fields.get(7).setText(product.getPricePerSack() + "");
		fields.get(8).setText(product.getPricePerKilo() + "");
		fields.get(fields.size() - 1).setText(product.getAlertOnQuantity() + "");

		cbox1.setSelected(product.isAvailable());
		cbox2.setSelected(product.alertUsingSack());

		int total = category.getItemCount();
		while (total > 0) {
			total--;
			Category c = (Category) category.getItemAt(total);
			if (c.getId() == product.getCategory().getId()) {
				category.setSelectedIndex(total);
				break;
			}
		}
		// fields.get(1).setText(item.getDescription());
		// fields.get(2).setText(item.getBarCode());
		// fields.get(3).setText(String.format("%.2f",
		// item.getUnitSellingPrice()));
		// fields.get(4).setText(String.format("%.2f",
		// item.getUnitPurchasePrice()));
		// fields.get(5).setText(item.getUnitsOnStock() + "");
		// fields.get(6).setText(item.getLocation());
		// fields.get(7).setText(item.getAlertOnQuantity() + "");
		//
		// cbox1.setSelected(item.isForSale());
		// cbox2.setSelected(item.isAllowAllert());

	}

	private void update() {
		Values.editPanel.startAnimation();
		new SuccessPopup("Edit").setVisible(true);
		Values.centerPanel.changeTable(Values.PRODUCTS);
		Values.topPanel.refreshStockCost();
	}

	private boolean isValidated() {
		if (!name.equals("") && !unitSellingPrice.equals("") && !unitPurchasePrice.equals("") && !unitsOnStock.equals("")
				&& !alertOnQuantity.equals(""))
			return true;

		return false;
	}

}
