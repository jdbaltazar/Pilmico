package gui.forms.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.border.AbstractBorder;

public class ViewFormBorder extends AbstractBorder {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color bkgrnd;

	public ViewFormBorder(Color bkgrnd){
		this.bkgrnd = bkgrnd;
	}
	
	@Override
    public void paintBorder(Component comp, Graphics g, int x, int y, int w, int h) {
        Graphics2D gg = (Graphics2D) g;
        gg.setColor(Color.GRAY);
        gg.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        
        gg.drawRect(x, y, w - 1, h - 1);
        gg.setColor(bkgrnd);
        gg.fillRect(x+1, y+1, w - 2, h - 2);
    }
}
