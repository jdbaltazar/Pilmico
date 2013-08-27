package util;

import java.awt.Font;

import javax.swing.JLabel;

public class MainFormLabel extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3252025401744874975L;

	public MainFormLabel(String label){
		super(label);
//		setFont(new Font("FangSong", Font.PLAIN, 14));
		setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		setOpaque(false);
	}

}
