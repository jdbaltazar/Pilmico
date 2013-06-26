package util.soy;

import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class SoyPanel extends JPanel{
	protected GradientPaint gradient;
	protected GradientPaint gradient1;

	public SoyPanel(){
		setOpaque(false);
	}

	@Override
	public abstract void paintComponent(Graphics g);

	public void setMaxSize(int width, int height){
		setMaximumSize(new Dimension(width,height));
	}

	public void setMinSize(int width, int height){
		setMinimumSize(new Dimension(width,height));
	}

	public void setPrefSize(int width, int height){
		setPreferredSize(new Dimension(width,height));
	}

}