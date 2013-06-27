package gui.forms.add;

import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;
import gui.forms.util.NumericTextField;
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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

import util.DropdownLabel;
import util.ErrorLabel;
import util.FormCheckbox;
import util.JNumericField;
import util.SBButton;
import util.SimplePanel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class ProductForm extends SimplePanel {

	private ArrayList<FormField> fields = new ArrayList<FormField>();
	private ArrayList<JNumericField> numfields = new ArrayList<JNumericField>();
	private SoyButton clear, save;
	private FormDropdown unit, category, condition;
	private JCheckBox cbox1, cbox2;
	private DefaultComboBoxModel model;
	private JNumericField numField;
	private ErrorLabel error;
	private NumericTextField quantityKG;
	private int initY = 65;
	private String name, unitSellingPrice, unitPurchasePrice, unitsOnStock, alertOnQuantity;
	private DropdownLabel dLabel;
	private SBButton fwd;

	private final int num = Tables.productFormLabel.length;

	public ProductForm() {
		super("Add Product");
		addComponents();

		Values.productForm = this;
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		clear = new SoyButton("Clear");
		save = new SoyButton("Save");
		
		fwd = new SBButton("forward.png", "forward.png", "Add new category");
		dLabel = new DropdownLabel("Category*");

		error = new ErrorLabel();
		numField = new JNumericField("Quantity in kg");

		numField.setMaxLength(10); //Set maximum length             
		numField.setPrecision(2); //Set precision (1 in your case)              
		//numField.setAllowNegative(true); //Set false to disable negatives

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
		

		cbox1 = new FormCheckbox("Available?*");
		cbox2 = new FormCheckbox("Alert using sack?*");

		cbox1.setSelected(true);
		cbox2.setSelected(true);

		int ctr = 0, ctr2 = 0;

		for (int i = 0, y = 0, x1 = 15, x2 = -10; i < num; i++, y += 63) {
			
			if(i==4){
				x1 = 215;
				y = 0;
			}
			
			if(i==8){
				x1 = 410;
				y = 0;
			}

//			if (i<=3 && i != 2 && i != 8 && i!=9) {
			if (i<2) {
				fields.add(new FormField(Tables.productFormLabel[i], 100, Color.white, Color.GRAY));
				fields.get(ctr).setBounds(x1, initY + y, 170, 25);
				add(fields.get(ctr));

				ctr++;
			}

			if(i>=3 && i<=7 || i == 10){
				numfields.add(new JNumericField(Tables.productFormLabel[i]));
				numfields.get(ctr2).setMaxLength(10);
				numfields.get(ctr2).setBounds(x1, initY + y, 170, 25);
				add(numfields.get(ctr2));
				
				ctr2++;
			}
			
			if (i == 2)
				cbox1.setBounds(x1, initY + y, 170, 25);
			if (i == 9)
				cbox2.setBounds(x1, initY + y, 170, 25);

//			if (i == 3) 
//				unit.setBounds(x1, initY + y, 170, 25);
				
//			if(i == 7)
	//			quantityKG.setBounds(x1, initY + y, 170, 25);

/*			if (i == 8)
				condition.setBounds(x1, 60 + y, 170, 25);
*/
			if (i == 8) {
				fwd.setBounds(x1 + 55, initY + y - 11, 16, 16);
				dLabel.setBounds(x1, initY + y - 7, 100, 11);
				category.setBounds(x1, initY + y + 5, 170, 20);
				
				category.addItem("Neil");
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

		fwd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();
				
				new UtilityPopup(b, "Add new category", Values.CATEGORY).setVisible(true);
			}
		});
		
		add(clear);
		add(save);

		add(error);
		
		add(numField);
		
	//	add(quantityKG);

//		add(unit);
//		add(condition);
		add(fwd);
		add(dLabel);
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
