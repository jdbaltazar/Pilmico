package gui.forms.add;

import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;
import gui.popup.SuccessPopup;
import gui.popup.UtilityPopup;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import util.DropdownLabel;
import util.ErrorLabel;
import util.FormCheckbox;
import util.JNumericField;
import util.SBButton;
import util.SimplePanel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

import common.entity.product.Category;
import common.entity.product.Product;
import common.manager.Manager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProductForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<FormField> fields = new ArrayList<FormField>();
	private ArrayList<JNumericField> numfields = new ArrayList<JNumericField>();
	private SoyButton clear, save;
	private FormDropdown unit, category;
	private JCheckBox cbox1, cbox2;
	private DefaultComboBoxModel model;
	private JNumericField numField;
	private ErrorLabel error;
	private int initY = 25;// 56
	private DropdownLabel dLabel;
	private SBButton fwd;

	private JPanel panel;
	private JScrollPane scrollPane;

	private String msg = "";

	private final int num = Tables.productFormLabel.length;

	public ProductForm() {
		super("Add Product");
		addComponents();

		Values.productForm = this;
	}

	private void addComponents() {
		// TODO Auto-generated method stub

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		fwd = new SBButton("forward.png", "forward.png", "Add new category");
		dLabel = new DropdownLabel("Category*");

		error = new ErrorLabel();
		numField = new JNumericField("Quantity in kg");

		numField.setMaxLength(10); // Set maximum length
		numField.setPrecision(2); // Set precision (1 in your case)
		// numField.setAllowNegative(true); //Set false to disable negatives

		try {
			// String[] temp = new String[units.size()];
			// for (int i = 0; i < units.size(); i++)
			// temp[i] = units.get(i).getName();
			model = new DefaultComboBoxModel();
			unit = new FormDropdown();
			unit.setModel(model);

		} catch (Exception e2) {
			e2.printStackTrace();
		}

		try {
			// String[] temp = new String[categories.size()];
			// for (int i = 0; i < categories.size(); i++)
			// temp[i] = categories.get(i).getName();
			model = new DefaultComboBoxModel();
			category = new FormDropdown();
			category.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		cbox1 = new FormCheckbox("Available?*");
		cbox2 = new FormCheckbox("Allow alert?*");

		cbox1.setSelected(true);
		cbox2.setSelected(true);

		cbox2.setToolTipText("show alert if out of stock?");

		int ctr = 0, ctr2 = 0;

		for (int i = 0, y = 0, x1 = 15; i < num; i++, y += 70) {//y+=54

			if (i == 3) {
				x1 = 215;
				y = 0;
			}

			if (i == 7) {
				x1 = 410;
				y = 0;
			}

			// if (i<=3 && i != 2 && i != 8 && i!=9) {
			if (i < 2) {
				fields.add(new FormField(Tables.productFormLabel[i], 100, Color.white, Color.GRAY));
				fields.get(ctr).setBounds(x1, initY + y, 170, 25);
				panel.add(fields.get(ctr));

				ctr++;
			}

			if (i >= 3 && i <= 7) {
				numfields.add(new JNumericField(Tables.productFormLabel[i]));
				numfields.get(ctr2).setMaxLength(10);
				numfields.get(ctr2).setBounds(x1, initY + y, 170, 25);
				panel.add(numfields.get(ctr2));

				ctr2++;
			}

			if (i == 2)
				cbox1.setBounds(x1, initY + y, 170, 25);
			if (i == 9)
				cbox2.setBounds(x1, initY + y, 170, 25);

			// if (i == 3)
			// unit.setBounds(x1, initY + y, 170, 25);

			// if(i == 7)
			// quantityKG.setBounds(x1, initY + y, 170, 25);

			/*
			 * if (i == 8) condition.setBounds(x1, 60 + y, 170, 25);
			 */
			if (i == 8) {
				fwd.setBounds(x1 + 55, initY + y - 11, 16, 16);
				dLabel.setBounds(x1, initY + y - 7, 100, 11);
				category.setBounds(x1, initY + y + 5, 170, 20);

				List<Category> categories = new ArrayList<Category>();
				try {
					categories = Manager.productManager.getCategories();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (Category c : categories) {
					category.addItem(c);
				}
			}
		}

		clear.setBounds(330, 300, 80, 30);
		save.setBounds(215, 300, 80, 30);

		error.setBounds(335, 270, 240, 22);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clearFields();

			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				/*
				 * System.out.println("Name: " + fields.get(0).getText());
				 * System.out.println("Desc: " + fields.get(1).getText());
				 * System.out.println("kilos/sack: " + numfields.get(0).getText());
				 * System.out.println("qty, sk: " + numfields.get(1).getText());
				 * System.out.println("qty, kg: " + numfields.get(2).getText());
				 * System.out.println("disp, sk: " + numfields.get(3).getText());
				 * System.out.println("disp, kg: " + numfields.get(4).getText());
				 * System.out.println("price, sk: " + numfields.get(5).getText());
				 * System.out.println("price, kg: " + numfields.get(6).getText());
				 * System.out.println("alert, qty: " + numfields.get(7).getText());
				 */

				if (isValidated() && hasValidInputs()) {

					String name = fields.get(0).getText();
					String description = fields.get(1).getText();
					double kilosPerSack = Double.parseDouble(numfields.get(0).getText());
					double quantityInSack = Double.parseDouble(numfields.get(1).getText());
					double quantityInKilo = Double.parseDouble(numfields.get(2).getText());
					// double displayInSack =
					// Double.parseDouble(numfields.get(3).getText());
					// double displayInKilo = Double.parseDouble(numfields.get(4).getText());
					double pricePerSack = Double.parseDouble(numfields.get(5).getText());
					double pricePerKilo = Double.parseDouble(numfields.get(6).getText());
//					double alertOnQuantity = Double.parseDouble(numfields.get(7).getText());

					// Product p = new Product(name, description, new Date(),
					// pricePerSack, pricePerKilo, kilosPerSack, cbox1.isSelected(),
					// quantityInSack,
					// quantityInKilo, displayInSack, displayInKilo, 0d, 0d,
					// (Category)
					// category.getSelectedItem(), true, cbox2.isSelected(),
					// alertOnQuantity);

					Product p = new Product(name, description, new Date(), pricePerSack, pricePerKilo, kilosPerSack, cbox1.isSelected(), quantityInSack,
							quantityInKilo, (Category) category.getSelectedItem(), cbox2.isSelected());

					try {
						Manager.productManager.addProduct(p);
						Values.centerPanel.changeTable(Values.PRODUCTS);
						new SuccessPopup("Add").setVisible(true);
						clearFields();

						Values.discountForm.refreshDropdown(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					error.setToolTip(msg);
			}

		});

		fwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();

				try {

					if (JOptionPane.showConfirmDialog(null, "If a new category is added, it cannot be deleted. Continue?", "Warning!",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {

						UtilityPopup uP = new UtilityPopup(b, Values.CATEGORY);
						uP.setVisible(true);

						if (!uP.getInput().equals("")) {
							// insert code here for adding category
							Manager.productManager.addCategory(new Category(uP.getInput()));
							updateCategories();

							new SuccessPopup("Add", 1).setVisible(true);
						}
						// Category c = new Category(fwd.getText());
						// category.addItem(c);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		panel.add(clear);
		panel.add(save);

		panel.add(error);

		panel.add(numField);

		// add(quantityKG);

		// add(unit);
		// add(condition);
		panel.add(fwd);
		panel.add(dLabel);
		panel.add(category);

		panel.add(cbox1);
		panel.add(cbox2);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(0, 30, 600, 380);

		add(scrollPane);
	}

	private void clearFields() {
		for (int i = 0; i < fields.size(); i++)
			fields.get(i).setText("");

		for (int i = 0; i < numfields.size(); i++)
			numfields.get(i).setText("");

		error.setToolTip("");
	}

	private boolean hasValidInputs() {

		if (Double.parseDouble(numfields.get(0).getText()) == 0d) {

			msg = "kilos per sack should not be 0";

			return false;
		}

		return true;
	}

	private boolean isValidated() {

		for (int i = 0; i < fields.size(); i++)
			if (fields.get(i).getText().equals("") && i != 1) {

				msg = "All fields EXCEPT for Description is required";
				return false;
			}

		for (int i = 0; i < numfields.size(); i++)
			if (numfields.get(i).getText().equals("")) {

				msg = "All fields EXCEPT for Description is required";
				return false;
			}

		return true;
	}

	private void updateCategories() {
		try {
			model = new DefaultComboBoxModel(Manager.productManager.getCategories().toArray());
			category.setModel(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		category.setSelectedIndex(0);
	}

}
