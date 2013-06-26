package util.soy;

import java.awt.Dimension;

import javax.swing.JRadioButton;

public class SoyRadioButton extends JRadioButton{

	private String name;

	public SoyRadioButton(String name) {
		this.name = name;
		init();
	}

	private void init() {
		setText(name);
		setPreferredSize(new Dimension(120, 20));
		setFocusable(false);
		setFont(SoyFont.getFont(14));
		setOpaque(false);
	}
}
