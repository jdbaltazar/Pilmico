package gui.forms.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class FieldLimit extends PlainDocument {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2038972305417308000L;
	private int limit;

	public FieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	@Override
	public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, attr);
		}
	}
}
