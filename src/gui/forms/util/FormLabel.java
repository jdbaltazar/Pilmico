package gui.forms.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class FormLabel extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5892854636340614671L;

	public FormLabel(String name){
		
		super(name);
		
		setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		setForeground(Color.decode("#006600"));
	}
	
	public void setSize(int size){
		setFont(new Font("Harabara", Font.PLAIN, size));
	}

}
