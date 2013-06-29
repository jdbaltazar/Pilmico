package util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class DropdownLabel extends JLabel{
	
	public DropdownLabel(String label){
		super(label);
		
		setOpaque(false);
		setFont(new Font("Lucida Grande", Font.ITALIC, 10));
		setForeground(Color.LIGHT_GRAY);
//		setBackground(Color.decode("#DDEBDD"));
	}

}
