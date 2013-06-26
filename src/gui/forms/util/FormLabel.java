package gui.forms.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class FormLabel extends JLabel{
	
	public FormLabel(String name){
		
		super(name);
		
		setFont(new Font("Harabara", Font.PLAIN, 16));
		setForeground(Color.GRAY.darker());
	}
	
	public void setSize(int size){
		setFont(new Font("Harabara", Font.PLAIN, size));
	}

}
