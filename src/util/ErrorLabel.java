package util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class ErrorLabel extends JLabel{
	
	public ErrorLabel(){
		setFont(new Font("Lucida Sans", Font.ITALIC, 11));
		setHorizontalAlignment(JLabel.RIGHT);
//		setBorder(BorderFactory.createEtchedBorder());
		setForeground(Color.red);
		setOpaque(false);
	}

}
