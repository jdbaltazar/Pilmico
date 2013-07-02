package gui.forms.util;

import java.awt.Font;

import javax.swing.JLabel;

public class RowJLabel extends JLabel{

	public RowJLabel(String label){
		super(label);
		
		setOpaque(false);
		setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		setHorizontalAlignment(JLabel.CENTER);
	}
}
