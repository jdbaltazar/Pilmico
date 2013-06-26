package gui.list.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gui.forms.util.FieldLimit;

public class ListField extends JTextField {

	private int limit = 0;
	private String label = "";
	private Color c = null, c2 = null;
	private boolean forView;

	public ListField(String label, int limit, boolean forAdd) {
		this.label = label;
		this.limit = limit;
		this.forView = forAdd;
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		setHorizontalAlignment(SwingConstants.CENTER);
		setForeground(Color.BLACK);
		
		if(forView){
			setFont(new Font("Lucida Grande", Font.PLAIN, 11));
			setBorder(BorderFactory.createEmptyBorder());
		}
		else{
			setFont(new Font("Lucida Grande", Font.PLAIN, 11));			
		}
		
		addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
	//			if (getText().equals("") && !forView) {
	//				setFont(new Font("Lucida Grande", Font.ITALIC, 10));
	//				setForeground(Color.LIGHT_GRAY);
	//				setText(label);
	//			}
		//		else
				if(!forView)
					setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
				else
					setBorder(BorderFactory.createEmptyBorder());
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
			//	if (getText().equals(label)) {
					
//					if(!forView){
//						setForeground(Color.BLACK);
//					}
//					else
						setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.CYAN));
					
			//		setFont(new Font("Lucida Grande", Font.PLAIN, 11));
			//	}
			}
		});
		
		setDocument(new FieldLimit(limit));
		
		if(forView)
			setText(label);
	}
	
	@Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if(forView)
        	return;
        
        if (label.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Lucida Grande", Font.ITALIC, 11));
        g.drawString(label, getInsets().left, pG.getFontMetrics()
            .getMaxAscent() + getInsets().top);
    }

}
