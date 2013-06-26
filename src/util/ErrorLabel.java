package util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class ErrorLabel extends JLabel{
	
	public ErrorLabel(){
		setFont(new Font("Lucida Grande", Font.ITALIC, 11));
		setForeground(Color.red);
		setOpaque(false);
	}

}
