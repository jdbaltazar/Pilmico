package gui.forms.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import gui.forms.util.FieldLimit;

public class FormField extends JTextField {

	private int limit = 0;
	private String label = "";
	private Color c = null, c2 = null;

	public FormField(String label, int limit) {
		this.label = label;
		this.limit = limit;
		init();
	}
	
	public FormField(String label, int limit, Color c, Color c2) {
		this.label = label;
		this.limit = limit;
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
		
		setDocument(new FieldLimit(limit));
		setFont(new Font("Lucida Grande", Font.PLAIN, 12));

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
