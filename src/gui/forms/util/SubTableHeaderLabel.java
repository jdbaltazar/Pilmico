package gui.forms.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class SubTableHeaderLabel extends JLabel{
	
	private Color color = new Color(119,136,153); 
	
	public SubTableHeaderLabel(String label){
		super(label);
		
		init();
	}
	
	public SubTableHeaderLabel(String label, int transactionTable){
		super(label);
		
		color = Color.decode("#483D8B");
		
		init();
	}
	
	public SubTableHeaderLabel(String label, Color color){
		super(label);
		
		this.color = color;
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		setOpaque(true);
		setFont(new Font("Tahoma", Font.BOLD, 10));
		setForeground(Color.white);
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.white));
		setBackground(color);
		setHorizontalAlignment(JLabel.CENTER);
	}

}
