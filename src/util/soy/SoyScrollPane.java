package util.soy;

import java.awt.Graphics;

import javax.swing.JScrollPane;

public class SoyScrollPane extends JScrollPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = -233545010447466398L;

	public SoyScrollPane(){
		getViewport().setOpaque(false);
		setOpaque(false);
		setBorder(null);
	}

	@Override
	public void paintComponent(Graphics g){
		
		paintChildren(g);
		g.dispose();
	}
}
