package gui.forms.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class EditFormField extends JTextField{
	
	private int limit = 0;
	private String label = "";
	private Color c = null, c2 = null;

	public EditFormField(int limit) {
		this.limit = limit;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub

		setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.decode("#006600")));
		
		setDocument(new FieldLimit(limit));
		setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		setForeground(Color.GRAY);
		setText(label);

		/*addFocusListener(new FocusListener() {

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
		});*/
	}


}
