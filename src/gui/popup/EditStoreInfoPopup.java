package gui.popup;

import gui.forms.util.EditFormField;
import gui.forms.util.FormLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import common.entity.store.Store;
import common.manager.Manager;

import util.ErrorLabel;
import util.SBButton;
import util.Values;
import util.soy.SoyButton;
import util.soy.SoyPanel;

public class EditStoreInfoPopup extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int WIDTH = 720, HEIGHT = 340;
	private SoyPanel panel;
	private JButton close;

	private JSpinner date;

	private ArrayList<JTextField> fields = new ArrayList<JTextField>();
	private ArrayList<FormLabel> labels = new ArrayList<FormLabel>();

	private String[] storeFormLabel = { "Name*", "Description", "Address*", "TIN", "Opening Date", "Operating Hours", "Landline No.", "Mobile No.",
			"Email", "Website", "Manager", "Proprietor" };

	private SoyButton edit;

	private ErrorLabel error;

	/*
	 * public EditStoreInfoPopup(StoreInfo storeInfo) { this.storeInfo =
	 * storeInfo; init(); addComponents();
	 * 
	 * }
	 */

	public EditStoreInfoPopup() {
		init();
		addComponents();
		fillEntries();

	}

	public void fillEntries() {

		try {
			Store store = Manager.storeManager.getStore();
			fields.get(0).setText(store.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}

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

		date = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(date, "MMMM dd, yyyy");
		date.setEditor(timeEditor2);
		Date d = new Date();
		date.setValue(d != null ? d : new Date());
		date.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		date.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.GRAY.darker()));

		JComponent editor = date.getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			JSpinner.DefaultEditor defEditor = (JSpinner.DefaultEditor) editor;
			JFormattedTextField tf = defEditor.getTextField();
			if (tf != null) {
				tf.setForeground(new Color(25, 117, 117));
				tf.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}

		panel = new SoyPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				Color ppColor = new Color(0, 96, 96, 30); // r,g,b,alpha
				g2.setColor(ppColor);
				g2.fillRect(0, 0, getWidth(), getHeight());

				g2.setColor(new Color(150, 150, 150));
				g2.drawRoundRect(2, 4, getWidth() - 5, getHeight() - 8, 20, 20);

				g2.setColor(new Color(0, 0, 139));
				g2.draw3DRect(14, 320, 10, 10, true);
				g2.fill3DRect(14, 320, 10, 10, true);
				g2.setFont(new Font("TimeBurner", Font.PLAIN, 14));

				g2.drawString("View / Edit Store Info", 30, 330);

				paintChildren(g2);

				g2.dispose();
				g.dispose();
			}
		};

		panel.setLayout(null);

		edit = new SoyButton("Edit");

		for (int i = 0, x = 50, y = 0, ctr = 0; i < storeFormLabel.length; i++, y += 65) {

			if (i == 4) {
				y = 0;
				x += 230;

				labels.add(new FormLabel(storeFormLabel[i]));
				labels.get(i).setBounds(x, 35 + y, 120, 15);
				panel.add(labels.get(i));

				date.setBounds(x, 50 + y, 170, 25);

				continue;
			}

			fields.add(new EditFormField(100));
			labels.add(new FormLabel(storeFormLabel[i]));

			if (i != 0 && (i % 4) == 0) {
				// x=0;
				// y+=70;
				y = 0;
				x += 230;
			}

			fields.get(ctr).setBounds(x, 50 + y, 170, 25);
			labels.get(i).setBounds(x, 35 + y, 120, 15);

			panel.add(fields.get(ctr));
			panel.add(labels.get(i));

			ctr++;

		}

		edit.setBounds(335, 295, 80, 30);

		error.setBounds(520, 280, 200, 30);

		close = new SBButton("close.png", "close.png", "Close");
		close.setBounds(690, 10, 24, 24);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				Values.mainFrame.dimScreen(false);
			}
		});

		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

		panel.add(edit);

		panel.add(date);
		panel.add(error);

		add(close);
		add(panel);
	}

	private boolean isValidated() {

		if (!fields.get(0).getText().equals("") && !fields.get(2).getText().equals(""))
			return true;

		return false;
	}
}
