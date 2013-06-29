package util;

import java.awt.Font;

import javax.swing.JLabel;

public class MainFormLabel extends JLabel{
	
	public MainFormLabel(String label){
		super(label);
		setFont(new Font("FangSong", Font.PLAIN, 14));
		setOpaque(false);
	}

}
