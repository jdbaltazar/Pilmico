package gui.popup;

import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;
import gui.forms.util.FormTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import util.ErrorLabel;
import util.SBButton;
import util.SimplePanel;
import util.Values;
import util.soy.SoyButton;


public class AddNotePopup extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int WIDTH = 278, HEIGHT = 390;
	private JPanel panel;
	private JButton close;

	private FormDropdown noteType;
	private FormField tag;
	private FormTextArea description;
	private JScrollPane descPane;
	private JSpinner date;

	private SoyButton save;

	private ErrorLabel error;

	public AddNotePopup() {
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

	@SuppressWarnings("unchecked")
	private void addComponents() {

		error = new ErrorLabel();

		date = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(date,
				"MMMM dd, yyyy hh:mm:ss a");
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

		tag = new FormField("Tag", 100, Color.white, Color.gray);
		tag.setToolTipText("Tag Persons");

		description = new FormTextArea("Description", 500, Color.white,
				Color.gray);
		descPane = new JScrollPane(description);

		noteType = new FormDropdown(new String[] {});

		/*try {
			List<NoteType> noteTypes = Manager.noteManager.getNoteTypes();

			for (NoteType nt : noteTypes) {
				noteType.addItem(nt);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}*/

		close = new SBButton("close.png", "close.png", "Close");
		close.setBounds(245, 10, 24, 24);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				Values.mainFrame.dimScreen(false);
			}
		});

		save = new SoyButton("Save");

		panel = new SimplePanel("Add Note");

		date.setBounds(40, 55, 200, 25);
		noteType.setBounds(40, 100, 200, 25);
		tag.setBounds(40, 145, 200, 25);
		descPane.setBounds(40, 190, 200, 120);

		save.setBounds(105, 342, 80, 30);

		error.setBounds(120, 310, 150, 25);

		save.addMouseListener(new MouseAdapter() {
			
		});

		panel.add(date);
		panel.add(noteType);
		panel.add(tag);
		panel.add(descPane);
		panel.add(save);
		panel.add(error);

		add(close);
		add(panel);
	}

	private boolean isValidated() {

		if (!description.getText().equals("Description")
				&& !description.getText().equals("")
				&& !tag.getText().equals(""))
			return true;

		return false;
	}

}
