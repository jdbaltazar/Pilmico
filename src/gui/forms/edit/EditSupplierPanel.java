package gui.forms.edit;

import gui.forms.util.EditFormField;
import gui.forms.util.FormLabel;
import gui.popup.SuccessPopup;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;

import common.entity.supplier.Supplier;

import util.EditFormPanel;
import util.ErrorLabel;
import util.Values;
import util.soy.SoyButton;

public class EditSupplierPanel extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<JTextField> fields = new ArrayList<JTextField>();
	private ArrayList<FormLabel> labels = new ArrayList<FormLabel>();
	private SoyButton edit;
	private int num = Values.supplierFormLabel.length;

	private ErrorLabel error;

	private String name, address;

	private int y1 = 70, y2 = 55, y3 = 85;

	private Supplier supplier;

	/*
	 * public EditSupplierPanel(Supplier supplier) {
	 * super("View / Edit Supplier"); this.supplier = supplier; init();
	 * addComponents(); }
	 */
	public EditSupplierPanel(Supplier supplier) {
		super("View / Edit Supplier");
		this.supplier = supplier;
		init();
		addComponents();
		fillValues();
	}

	private void init() {
		// TODO Auto-generated method stub
		setLayout(null);
	}

	private void addComponents() {
		// TODO Auto-generated method stub

		edit = new SoyButton("Edit");

		error = new ErrorLabel();

		for (int i = 0, x = 90, y = 0; i < num; i++, y += y3) {

			fields.add(new EditFormField(100));
			labels.add(new FormLabel(Values.supplierFormLabel[i]));

			if (i != 0 && (i % 3) == 0) {
				// x=0;
				// y+=70;
				y = 0;
				x += 230;
			}

			fields.get(i).setBounds(x, y1 + y, 170, 25);
			labels.get(i).setBounds(x, y2 + y, 100, 15);

			add(fields.get(i));
			add(labels.get(i));

		}

		edit.setBounds(370, 335, 80, 30);

		error.setBounds(550, 300, 230, 25);

		/*
		 * fields.get(0).setText(supplier.getName());
		 * fields.get(1).setText(supplier.getDescription());
		 * fields.get(2).setText(supplier.getAddress());
		 * fields.get(3).setText(supplier.getTin());
		 * fields.get(4).setText(supplier.getLandlineNo());
		 * fields.get(5).setText(supplier.getMobileNo());
		 * fields.get(6).setText(supplier.getEmail());
		 * fields.get(7).setText(supplier.getContactPerson());
		 * fields.get(8).setText(supplier.getNotes());
		 */

		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

		add(edit);

		add(error);
	}

	private void fillValues() {
		fields.get(0).setText(supplier.getName());
		fields.get(1).setText(supplier.getAddress());
		fields.get(2).setText(supplier.getTin());
		fields.get(3).setText(supplier.getContactNo());
//		fields.get(4).setText(supplier.getContactPerson().getFirstPlusLastName());
		fields.get(5).setText(supplier.getRemarks());
	}

	private void update() {
		Values.editPanel.startAnimation();
		// Values.stockPurchasePanel.refreshSupplier();
		new SuccessPopup("Edit").setVisible(true);
		Values.centerPanel.changeTable(Values.DELIVERY);
	}

	private boolean isValidated() {
		if (!name.equals("") && !address.equals(""))
			return true;

		return false;
	}

}
