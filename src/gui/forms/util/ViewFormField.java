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
//		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		setHorizontalAlignment(JLabel.CENTER);
		setToolTipText(label);
	}

}
