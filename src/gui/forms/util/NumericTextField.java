package gui.forms.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;


@SuppressWarnings("serial")
public class NumericTextField extends JTextField {

	private int limit = 0;
	private String label;
	private Color c, c2;
	
	public NumericTextField(String label, int limit, Color c, Color c2) {
		super();
		
		this.limit = limit;
		this.label = label;
		this.c = c;
		this.c2 = c2;
		
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		
		if(c!=null)
			setBorder(new RoundedCornerBorder(c, c2));
		else
			setBorder(new RoundedCornerBorder());
		
		setFont(new Font("Lucida Grande", Font.PLAIN, 12));
	}


	@Override
	protected Document createDefaultModel() {
		return new NumericDocument();
	}

	private class NumericDocument extends PlainDocument {
		// The regular expression to match input against (zero or more digits)
		private Pattern DIGITS = Pattern.compile("\\d*");
		
		@Override
		public void insertString(int offs, String str, AttributeSet a)
				throws BadLocationException {
			
			// Only insert the text if it matches the regular expression
			if (str != null && DIGITS.matcher(str).matches() && (getLength() + str.length()) <= limit)
				super.insertString(offs, str, a);
			
		}
	}
	
	@Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (label.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
        g.drawString(label, getInsets().left, pG.getFontMetrics()
            .getMaxAscent() + getInsets().top);
    }
}