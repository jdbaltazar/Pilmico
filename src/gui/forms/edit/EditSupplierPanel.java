package gui.forms.edit;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.EditFormField;
import gui.forms.util.FormLabel;
import gui.forms.util.FormDropdown.ColorArrowUI;
import gui.popup.SuccessPopup;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import common.entity.supplier.Supplier;
import common.manager.Manager;

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

	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;

	private String name, address;

	private int y1 = 30, y2 = 15, y3 = 85;

	private Supplier supplier;
	private JComboBox contactPersonCombo;
	private JTextField contactPersonComboField;

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

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane(panel);

		edit = new SoyButton("Edit");

		error = new ErrorLabel();

		contactPersonCombo = new JComboBox();

		contactPersonCombo.setUI(ColorArrowUI.createUI(this));
		contactPersonCombo.setEditable(true);
		contactPersonComboField = (JTextField) contactPersonCombo.getEditor().getEditorComponent();
		contactPersonComboField.setText("");
		contactPersonComboField.setOpaque(false);
		contactPersonComboField.setBorder(BorderFactory.createEmptyBorder());
		contactPersonComboField.addKeyListener(new ComboKeyHandler(contactPersonCombo));

		contactPersonCombo.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
		contactPersonCombo.setForeground(Color.GRAY);
		contactPersonCombo.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.decode("#006600")));
		// contactPersonCombo.setOpaque(false);

		int labelsCtr = 0, fieldCtr = 0;
		for (int i = 0, x = 10, y = 0; i < num; i++, y += y3) {

			if (i != 0 && (i % 3) == 0) {
				// x=0;
				// y+=70;
				y = 0;
				x += 230;
			}

			if (i != 5) {
				fields.add(new EditFormField(100));
				labels.add(new FormLabel(Values.supplierFormLabel[i]));

				fields.get(fieldCtr).setBounds(x, y1 + y, 170, 25);
				labels.get(labelsCtr).setBounds(x, y2 + y, 100, 15);

				panel.add(fields.get(fieldCtr));
				panel.add(labels.get(labelsCtr));

				labelsCtr++;
				fieldCtr++;
			}

			// if (i == 4) {
			// labels.add(new FormLabel(Values.supplierFormLabel[i]));
			// labels.get(labelsCtr).setBounds(x, y2 + y, 100, 15);
			//
			// contactPersonCombo.setBounds(x, y1 + y, 170, 25);
			//
			// labels.get(labelsCtr).setVisible(false);
			// contactPersonCombo.setVisible(false);
			//
			// labelsCtr++;
			// }

		}

		edit.setBounds(370, 335, 80, 30);

		error.setBounds(550, 300, 230, 25);

		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				supplier.setName(fields.get(0).getText());
				supplier.setAddress(fields.get(1).getText());
				supplier.setTin(fields.get(2).getText());
				supplier.setContactNo(fields.get(3).getText());
				supplier.setRemarks(fields.get(4).getText());

				try {
					Manager.supplierManager.updateSupplier(supplier);
					Values.editPanel.startAnimation();
					new SuccessPopup("Edit").setVisible(true);
					Values.centerPanel.changeTable(Values.SUPPLIERS);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		panel.add(contactPersonCombo);

		scrollPane.setBounds(197, 55, 425, 245);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		add(scrollPane);
		add(edit);

		add(error);
	}

	private void fillValues() {
		fields.get(0).setText(supplier.getName());
		fields.get(1).setText(supplier.getAddress());
		fields.get(2).setText(supplier.getTin());
		fields.get(3).setText(supplier.getContactNo());
		// fields.get(4).setText(supplier.getContactPerson().getFirstPlusLastName());
		fields.get(4).setText(supplier.getRemarks());
	}

	private void update() {
		Values.editPanel.startAnimation();
		// Values.stockPurchasePanel.refreshSupplier();
		new SuccessPopup("Edit").setVisible(true);
		Values.centerPanel.changeTable(Values.SUPPLIERS);
	}

	private boolean isValidated() {
		if (!name.equals("") && !address.equals(""))
			return true;

		return false;
	}

}
