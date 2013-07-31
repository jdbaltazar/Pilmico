package gui.forms.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.MinimalBalloonStyle;
import net.java.balloontip.styles.RoundedBalloonStyle;
import net.java.balloontip.utils.ToolTipUtils;

public class IconLabel extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BalloonTip balloonTip;
	private JLabel jlabel;
	MinimalBalloonStyle style2 = new MinimalBalloonStyle(Color.decode("#B2FEE1"), 7);
	MinimalBalloonStyle style3 = new MinimalBalloonStyle(Color.decode("#FFD6D6"), 7);

	public IconLabel(ImageIcon icon, String tooltip){
		super(icon);
		
		//RoundedBalloonStyle style = new RoundedBalloonStyle(5, 5, Color.white, Color.black);
		jlabel = new JLabel(tooltip);
		jlabel.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
		

		balloonTip = new BalloonTip(
				this,
				jlabel,
				style2,
				BalloonTip.Orientation.LEFT_ABOVE,
				BalloonTip.AttachLocation.NORTH,
				15, 8,
				false
			);
		
		ToolTipUtils.balloonToToolTip(balloonTip, 10, 3000);
	}
	
	public void setIconToolTip(ImageIcon icon, String text, boolean valid) {
		
		setIcon(icon);
		
		jlabel.setText(text);
		
		balloonTip.refreshLocation();
		balloonTip.setContents(jlabel);
		
		if(valid)
			balloonTip.setStyle(style2);
		else
			balloonTip.setStyle(style3);
	}

}
