package util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JCheckBox;

public class FormCheckbox extends JCheckBox{
	
	public FormCheckbox(String label){
		
		super(label);
		
		setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		setFocusPainted(false);
		setBackground(Color.decode("#F0F8FF"));
		
	}

}
