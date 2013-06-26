package util;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

public class SpinnerDate extends JSpinner{
	
	public SpinnerDate(String format){
		//super(new SpinnerDateModel());
		
		setModel(new SpinnerDateModel());
		
		JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(this,
				format);
		setEditor(timeEditor2);
		setValue(new Date());
		setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		setBorder(BorderFactory.createEmptyBorder());

		JComponent editor = getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			JSpinner.DefaultEditor defEditor = (JSpinner.DefaultEditor) editor;
			JFormattedTextField tf = defEditor.getTextField();
			if (tf != null) {
				tf.setForeground(new Color(25, 117, 117));
				tf.setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
	}

}
