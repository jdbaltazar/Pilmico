package gui.forms.util;

import java.awt.Color;
import java.awt.Font;

import javax.accessibility.AccessibleContext;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.EdgedBalloonStyle;
import net.java.balloontip.styles.MinimalBalloonStyle;
import net.java.balloontip.styles.RoundedBalloonStyle;
import net.java.balloontip.styles.TexturedBalloonStyle;
import net.java.balloontip.utils.ToolTipUtils;

public class ViewFormField extends JLabel{
	
	private String label;
	
	public ViewFormField(String label){
		super(label);
		
		this.label = label;
		
		setOpaque(false);
		setFont(new Font("Arial Narrow", Font.PLAIN, 14));
//		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		setHorizontalAlignment(JLabel.CENTER);
	//	setToolTipText(label);
//		EdgedBalloonStyle style = new EdgedBalloonStyle(Color.WHITE, Color.BLUE);
//		BalloonTip balloonTip = new BalloonTip(this, label);
		RoundedBalloonStyle style = new RoundedBalloonStyle(5, 5, Color.white, Color.black);
//		MinimalBalloonStyle style  = new MinimalBalloonStyle(Color.blue, 5);
	//	BalloonTip = new Ball
		
//		BalloonTip sdas = new Ball
		BalloonTip balloonTip = new BalloonTip(
				this,
				new JLabel(this.label),
				style,
				BalloonTip.Orientation.LEFT_ABOVE,
				BalloonTip.AttachLocation.NORTH,
				5, 7,
				false
			);
		
		ToolTipUtils.balloonToToolTip(balloonTip, 10, 3000);
	}

	/**
	 * Set a tooltip
	 * @param comp		sets a tooltip for this component
	 * @param text		the contents of the tooltip (you may use html)
	 */
	public void setToolTip(JComponent comp, String text) {
		RoundedBalloonStyle style = new RoundedBalloonStyle(5, 5, Color.white, Color.black);
		
		final BalloonTip balloon = new BalloonTip(comp, new JLabel(text), style, BalloonTip.Orientation.LEFT_ABOVE, BalloonTip.AttachLocation.NORTH, 5, 7, false);
		balloon.addDefaultMouseListener(false);
		
		ToolTipUtils.balloonToToolTip(balloon, 10, 3000);
		
		setText(text);
	}
}
