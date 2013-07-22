package gui.forms.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class DefaultEntryLabel extends JLabel{
	
	public DefaultEntryLabel(String label){
		super(label);
		
		setOpaque(false);
		setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		setHorizontalAlignment(JLabel.CENTER);
	}

}
