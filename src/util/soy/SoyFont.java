package util.soy;

import java.awt.Font;

public class SoyFont {
	private static String fontStyle = "Dialog";
	private static Font defFont = new Font(fontStyle, 1, 14);

	public static Font getFont(int style, int size) {
		Font font = new Font(fontStyle, style, size);

		return font;
	}

	public static Font getFont(int size) {
		Font font = new Font(fontStyle, 0, size);

		return font;
	}

	public static Font getFont() {

		return defFont;
	}

	public static Font getBoldFont(int size) {
		Font font = new Font(fontStyle, 1, size);

		return font;
	}

}
