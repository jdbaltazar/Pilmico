package util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;

public class TableHeaderLabel extends JLabel{
	
	public TableHeaderLabel(String label){
		super(label);

		init();
	}
	
	public TableHeaderLabel(Icon icon){
		
		super(icon);
		
		init();
	}
	
	private void init(){
//		setBorder(BorderFactory.createRaisedBevelBorder());
		setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.white));
		setFont(new Font("Harabara", Font.PLAIN, 15));
		setHorizontalAlignment(JLabel.CENTER);
		setForeground(Color.white);
		//setBackground(new Color(119, 136, 153));
		//setBackground(Color.decode("#1E90FF"));
		setBackground(Color.decode("#6495ED"));
		setOpaque(true);
		
	}

}
