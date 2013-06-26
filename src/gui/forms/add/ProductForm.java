package gui.forms.add;

import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;
import gui.forms.util.NumericTextField;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

import util.ErrorLabel;
import util.SimplePanel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class ProductForm extends SimplePanel {

	private ArrayList<FormField> fields = new ArrayList<FormField>();
	private SoyButton clear, save;
	private FormDropdown unit, category, condition;
	private JCheckBox cbox1, cbox2;
	private DefaultComboBoxModel model;
	private ErrorLabel error;
	private NumericTextField quantityKG;
	private int initY = 65;
	private String name, unitSellingPrice, unitPurchasePrice, unitsOnStock, alertOnQuantity;
	

	private final int num = Tables.productFormLabel.length;

	public ProductForm() {
		super("Add Product");
		addComponents();

		Values.itemForm = this;
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		error = new ErrorLabel();

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
		
		quantityKG = new NumericTextField("Quantity*", 12, Color.white, Color.GRAY);
		

		cbox1 = new JCheckBox("Available?*");
		cbox2 = new JCheckBox("Allow Alert?*");

		cbox1.setSelected(true);
		cbox2.setSelected(true);

		int ctr = 0;

		for (int i = 0, y = 0, x1 = 15, x2 = -10; i < num; i++, y += 63) {
			
			if(i==3){
				x1 = 215;
				y = 0;
			}
			
			if(i==7){
				x1 = 410;
				y = 0;
			}

			if (i != 2 && i != 8 && i != 7) {
				fields.add(new FormField(Tables.productFormLabel[i], 100, Color.white, Color.GRAY));
				fields.get(ctr).setBounds(x1, initY + y, 170, 25);
				add(fields.get(ctr));

				ctr++;
			}

			if (i == 2)
				cbox1.setBounds(x1, initY + y, 170, 25);
			if (i == 8)
				cbox2.setBounds(x1, initY + y, 170, 25);

//			if (i == 3) 
//				unit.setBounds(x1, initY + y, 170, 25);
				
//			if(i == 7)
	//			quantityKG.setBounds(x1, initY + y, 170, 25);

/*			if (i == 8)
				condition.setBounds(x1, 60 + y, 170, 25);
*/
			if (i == 7) {
				
				category.setBounds(x1, initY + y, 170, 25);
			}
		}

		clear.setBounds(330, 330, 80, 30);
		save.setBounds(215, 330, 80, 30);

		error.setBounds(340, 300, 230, 25);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clearFields();

			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {


			}
			
		});

		add(clear);
		add(save);

		add(error);
		
	//	add(quantityKG);

//		add(unit);
//		add(condition);
		add(category);

		add(cbox1);
		add(cbox2);
	}

	private void clearFields() {
		for (int i = 0; i < fields.size(); i++)
			fields.get(i).setText("");
		
		fields.get(fields.size() - 1).setText("0");
		quantityKG.setText("");

		error.setText("");
	}

	private boolean isValidated() {
		if (!name.equals("") && !unitSellingPrice.equals("") && !unitPurchasePrice.equals("") && !unitsOnStock.equals("")
				&& !alertOnQuantity.equals(""))
			return true;

		return false;
	}

	public void updateFormDropdowns() {
		/*try {
			model = new DefaultComboBoxModel(Manager.itemManager.getUnits().toArray());
			unit.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		try {
			List<Category> categories = Manager.itemManager.getCategories();
			model = new DefaultComboBoxModel(categories.toArray());
			category.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		try {
			List<ItemCondition> conditions = Manager.itemManager.getConditions();
			model = new DefaultComboBoxModel(conditions.toArray());
			condition.setModel(model);
		} catch (Exception e2) {
			e2.printStackTrace();
		}*/
	}

}
