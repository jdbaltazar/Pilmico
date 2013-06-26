package gui.popup;

import gui.forms.util.FormField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import util.ErrorLabel;
import util.SBButton;
import util.SimplePanel;
import util.soy.SoyButton;

public class AddStoreInfoPopup extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int WIDTH = 280, HEIGHT = 570;
	private JPanel panel;
	private JButton close;
	private ArrayList<FormField> fields = new ArrayList<FormField>();
	private JLabel label;
	private JSpinner date;

	private String[] storeFormLabel = { "Name*", "Description", "Address*",
			"TIN", "Opening Date", "Operating Hours", "Landline No.",
			"Mobile No.", "Email", "Website", "Manager", "Proprietor" };

	private SoyButton save;

	private ErrorLabel error;

	public AddStoreInfoPopup() {

		init();
		addComponents();

	}

	private void init() {

		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(JDialog.ModalityType.APPLICATION_MODAL);

	}

	private void addComponents() {

		error = new ErrorLabel();

		label = new JLabel("Opening Date");
		label.setFont(new Font("Lucida Grande", Font.ITALIC, 10));
		label.setForeground(Color.LIGHT_GRAY);

		date = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(date,
				"MMMM dd, yyyy");
		date.setEditor(timeEditor2);
		date.setValue(new Date());
		date.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		date.setBorder(BorderFactory.createEmptyBorder());

		JComponent editor = date.getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			JSpinner.DefaultEditor defEditor = (JSpinner.DefaultEditor) editor;
			JFormattedTextField tf = defEditor.getTextField();
			if (tf != null) {
				tf.setForeground(new Color(25, 117, 117));
				tf.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}

		panel = new SimplePanel("Store Information");

		for (int i = 0, y = 50, ctr = 0; i < storeFormLabel.length; i++, y += 37) {

			if (i == 4) {
				label.setBounds(40, y, 100, 15);
				date.setBounds(40, y + 15, 200, 20);

				y += 10;
				continue;
			}

			fields.add(new FormField(storeFormLabel[i], 100, Color.white,
					Color.gray));

			fields.get(ctr).setBounds(40, y, 200, 25);

			panel.add(fields.get(ctr));

			ctr++;
		}

		close = new SBButton("close.png", "close.png", "Close");
		close.setBounds(247, 10, 24, 24);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				// Values.mainFrame.dimScreen(false);
			}
		});

		save = new SoyButton("Save");

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if(isValidated()){
					
				}else
					error.setText("Please fill up fields marked with *");
				
			}
		});

		save.setBounds(103, 517, 80, 30);

		error.setBounds(53, 493, 200, 25);

		panel.add(save);
		panel.add(label);
		panel.add(date);
		panel.add(error);

		add(close);
		add(panel);

	}

	private boolean isValidated() {

		if (!fields.get(0).getText().equals("")
				&& !fields.get(2).getText().equals(""))
			return true;

		return false;
	}
}
