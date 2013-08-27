package gui.forms.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class FormDropdown extends JComboBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FormDropdown() {

		//setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		// setFont(new Font("Tahoma", Font.BOLD, 22));
		init();
	}
	
	public FormDropdown(boolean edit) {

		setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.decode("#006600")));
		setFont(new Font("Arial Narrow", Font.PLAIN, 14));
		setForeground(Color.GRAY);
		
		setRequestFocusEnabled(false);
		setUI(ColorArrowUI.createUI(this));
	}

	public FormDropdown(Object[] entries) {

		// setFont(new Font("Tahoma", Font.BOLD, 22));
		for (int i = 0; i < entries.length; i++)
			addItem(entries[i]);
		init();
	}

	private void init() {

		// setPrototypeDisplayValue("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		setRequestFocusEnabled(false);

		setUI(ColorArrowUI.createUI(this));

		/*
		 * final Color COLOR_BUTTON_BACKGROUND = Color.decode("#d3dedb");
		 * 
		 * UIManager.put("ComboBox.buttonBackground", COLOR_BUTTON_BACKGROUND);
		 * UIManager.put("ComboBox.buttonShadow", COLOR_BUTTON_BACKGROUND);
		 * UIManager.put("ComboBox.buttonDarkShadow", COLOR_BUTTON_BACKGROUND);
		 * UIManager.put("ComboBox.buttonHighlight", COLOR_BUTTON_BACKGROUND);
		 * 
		 * UIManager.put("ComboBox.background", new
		 * ColorUIResource(Color.yellow));
		 * UIManager.put("JTextField.background", new
		 * ColorUIResource(Color.yellow));
		 * UIManager.put("ComboBox.selectionBackground", new
		 * ColorUIResource(Color.magenta));
		 * UIManager.put("ComboBox.selectionForeground", new
		 * ColorUIResource(Color.blue));
		 * 
		 * 
		 * getEditor().getEditorComponent().setBackground(Color.YELLOW);
		 * ((JTextField)
		 * getEditor().getEditorComponent()).setBackground(Color.YELLOW);
		 */
	/*	addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox cb = (JComboBox) e.getSource();
				String selected = cb.getSelectedItem().toString();

				// System.out.println(selected);
			}
		});*/
	}

	public static class ColorArrowUI extends BasicComboBoxUI {

		public static ComboBoxUI createUI(JComponent c) {
			return new ColorArrowUI();
		}

		@Override
		protected JButton createArrowButton() {
			return new BasicArrowButton(BasicArrowButton.SOUTH, /* Color.gray */
					new Color(230, 230, 250), new Color(207, 207, 225)/*
																	 * Color.black
																	 */,
					Color.gray, Color.white);// new
												// Color(207,207,225)/*Color.black*/);
		}
	}
}
