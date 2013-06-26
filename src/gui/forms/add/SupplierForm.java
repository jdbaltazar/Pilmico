package gui.forms.add;

import gui.forms.util.FormField;
import gui.popup.SuccessPopup;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import util.ErrorLabel;
import util.SimplePanel;
import util.Values;
import util.soy.SoyButton;

public class SupplierForm extends SimplePanel {

	private ArrayList<FormField> fields = new ArrayList<FormField>();
	private SoyButton clear, save;

	private final int num = Values.supplierFormLabel.length;

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

		for (int i = 0, y = 0, x1 = 15, x2 = -10; i < num; i++, y += 70) {
			fields.add(new FormField(Values.supplierFormLabel[i], 100, Color.white, Color.gray));

			if (i == 3) {
				x1 = 215;
				x2 = 295;
				y = 0;
			}

			fields.get(i).setBounds(x1, 60 + y, 170, 25);

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

			}
		});

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
