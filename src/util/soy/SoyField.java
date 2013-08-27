package util.soy;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

public class SoyField extends JTextField{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1249170403454618624L;
	//private Color backgroundColor1 = new Color(245,245,220),	backgroundColor2 = new Color(220,220,220);
	private Color backgroundColor1 = new Color(245,245,220),	backgroundColor2 = new Color(255,250,250);
	
	public SoyField(int color){
		init(color);
		//setBorder(BorderFactory.createEmptyBorder());
		//setBorder(BorderFactory.createMatteBorder(1, 1,1, 1, new Color(107,142,35)));
	}

	private void init(int color){
		
		switch(color){
		case 0:
			setBackground(backgroundColor1);
			break;
		default:
			setBackground(backgroundColor2);
			break;
		}
		
		setFont(SoyFont.getFont(14));
		setPreferredSize(new Dimension(170,25));
	}
	
}
