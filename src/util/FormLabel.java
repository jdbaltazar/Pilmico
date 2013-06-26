package util;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import util.soy.SoyFont;

public class FormLabel extends JLabel{

	String name;

	public FormLabel(String name) {
		this.name = name;

		init(15);
	}

	public FormLabel(String name, int size) {
		this.name = name;

		init(size);
	}

	private void init(int size) {

		setFont(new Font("Calibri", Font.BOLD, size));
		setHorizontalAlignment(SwingConstants.RIGHT);
		setOpaque(false);
		setText(name);
	}
}
