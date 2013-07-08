package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.FormField;
import gui.popup.SuccessPopup;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import common.entity.profile.Person;
import common.entity.supplier.Supplier;
import common.manager.Manager;

import util.DropdownLabel;
import util.ErrorLabel;
import util.SBButton;
import util.SimplePanel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class SupplierForm extends SimplePanel {

	private ArrayList<FormField> fields = new ArrayList<FormField>();
	private SoyButton clear, save;
	private JComboBox personCombo;
	private JTextField personField;
	
	private DropdownLabel contactPerson;

	private SBButton fwd;

	private final int num = Tables.supplierFormLabel.length;

	private ErrorLabel error;

	private String name, address;

	public SupplierForm() {
		super("Add Supplier");
		addComponents();
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		clear = new SoyButton("Clear");
		save = new SoyButton("Save");

		error = new ErrorLabel();
		
		fwd = new SBButton("forward.png", "forward.png", "Add new category");
		
		contactPerson = new DropdownLabel("Contact Person");
		
		personCombo = new JComboBox();

		personCombo.setSelectedIndex(-1);

		personCombo.setEditable(true);
		personField = (JTextField) personCombo.getEditor()
				.getEditorComponent();
		personField.setText("");
		personField.addKeyListener(new ComboKeyHandler(personCombo));

		for (int i = 0, y = 0, x1 = 15, x2 = -10; i < num; i++, y += 70) {
			
			fields.add(new FormField(Values.supplierFormLabel[i], 100, Color.white, Color.gray));

			if (i == 3) {
				x1 = 215;
				x2 = 295;
				y = 0;
			}

			if(i!=4)
			fields.get(i).setBounds(x1, 60 + y, 170, 25);
			
			if(i == 4){
				fwd.setBounds(x1 + 78, 49 + y, 16, 16);
				contactPerson.setBounds(x1, 60 + y - 7, 100, 11);
				personCombo.setBounds(x1, 65 + y, 170, 20);
			}

			add(fields.get(i));
		}

		clear.setBounds(220, 260, 80, 30);
		save.setBounds(100, 260, 80, 30);

		error.setBounds(160, 290, 230, 25);

		clear.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				clearFields();

			}
		});

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				System.out.println("name: " + fields.get(0).getText());
				System.out.println("address: " + fields.get(1).getText());
				System.out.println("tin: " + fields.get(2).getText());
				System.out.println("contactNo: " + fields.get(3).getText());
				System.out.println("contactPerson: " + fields.get(4).getText());
				System.out.println("remarks: " + fields.get(5).getText());

				Person person = null;
				Supplier s = new Supplier(fields.get(0).getText(), fields.get(1).getText(), fields.get(2).getText(), fields.get(3).getText(), person,
						fields.get(5).getText());

				try {
					Manager.supplierManager.addSupplier(s);
					System.out.println("supplier added!");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		add(fwd);
		add(contactPerson);
		add(personCombo);
		
		
		add(clear);
		add(save);

		add(error);

	}

	private boolean isValidated() {
		if (!name.equals("") && !address.equals(""))
			return true;

		return false;
	}

	private void clearFields() {
		for (int i = 0; i < fields.size(); i++)
			fields.get(i).setText("");

		error.setText("");
	}

}
