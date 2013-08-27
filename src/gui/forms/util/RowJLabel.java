package gui.forms.util;

import java.awt.Font;

import javax.swing.JLabel;

public class RowJLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8296007163904194059L;

	public RowJLabel(String label){
		super(label);
		
		setOpaque(false);
		setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		setHorizontalAlignment(JLabel.CENTER);
	}
}
