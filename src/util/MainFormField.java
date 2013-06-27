package util;

import gui.forms.util.FieldLimit;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainFormField extends JTextField{
	
	public MainFormField(int limit){
		
		setDocument(new FieldLimit(limit));
		setOpaque(false);
		setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		setHorizontalAlignment(JLabel.CENTER);
		
	}

}
