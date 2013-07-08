package util;

import gui.forms.util.FieldLimit;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainFormField extends JTextField{
	private int limit;
	
	public MainFormField(int limit){
		this.limit = limit;
		
		init();
	}
	
	public MainFormField(int limit, boolean isNotEditable){
		this.limit = limit;
		setEditable(false);
		
		init();
	}
	
	private void init(){
		setDocument(new FieldLimit(limit));
		setOpaque(false);
		setFont(new Font("Arial Narrow", Font.PLAIN, 14));
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		setHorizontalAlignment(JLabel.CENTER);
		
	}

}
