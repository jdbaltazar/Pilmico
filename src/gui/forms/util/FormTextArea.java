package gui.forms.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;

import gui.forms.util.FieldLimit;

public class FormTextArea extends JTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1725291372602876413L;
	private int limit = 0;
	private String label = "";
	
	public FormTextArea(String label, int limit, Color c, Color c2) {
		this.label = label;
		this.limit = limit;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub

		//setBorder(new RoundedCornerBorder(c, c2));
		
		setDocument(new FieldLimit(limit));
		setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		setForeground(Color.LIGHT_GRAY);
		setText(label);

		addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if (getText().equals("")) {
					setFont(new Font("Lucida Grande", Font.ITALIC, 12));
					setForeground(Color.LIGHT_GRAY);
					setText(label);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if (getText().equals(label)) {
					setFont(new Font("Lucida Grande", Font.PLAIN, 12));
					setText("");
					setForeground(Color.BLACK);
				}
			}
		});
	}

}
