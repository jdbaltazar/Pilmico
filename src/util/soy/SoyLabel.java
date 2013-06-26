package util.soy;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class SoyLabel extends JLabel {
	String name;

	public SoyLabel(String name) {
		this.name = name;

		init(15);
	}

	public SoyLabel(String name, int size) {
		this.name = name;

		init(size);
	}

	private void init(int size) {

		setFont(SoyFont.getFont(size));
		setOpaque(false);
		setText(name);
	}

}
