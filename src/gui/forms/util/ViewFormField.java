package gui.forms.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class ViewFormField extends JLabel{
	
	public ViewFormField(String label){
		super(label);
		
		setOpaque(false);
		setFont(new Font("Arial Narrow", Font.PLAIN, 14));
//		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		setHorizontalAlignment(JLabel.CENTER);
		setToolTipText(label);
	}

}
