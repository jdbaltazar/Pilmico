package gui.forms.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.RoundedBalloonStyle;
import net.java.balloontip.utils.ToolTipUtils;

public class RemarksLabel extends JLabel{
	
	public RemarksLabel(String label){
		super(label);
		
		setFont(new Font("Segoe Print", Font.ITALIC, 12));
		setHorizontalAlignment(JLabel.RIGHT);
		
		RoundedBalloonStyle style = new RoundedBalloonStyle(5, 5, Color.white, Color.black);
		BalloonTip balloonTip = new BalloonTip(
				this,
				new JLabel(label),
				style,
				BalloonTip.Orientation.RIGHT_ABOVE,
				BalloonTip.AttachLocation.ALIGNED,
				5, 7,
				false
			);
		
		ToolTipUtils.balloonToToolTip(balloonTip, 10, 3000);
	}

	public void setToolTip(JComponent comp, String text) {
		RoundedBalloonStyle style = new RoundedBalloonStyle(5, 5, Color.white, Color.black);
		
		final BalloonTip balloon = new BalloonTip(comp, new JLabel(text.substring(1)), style, BalloonTip.Orientation.RIGHT_ABOVE, BalloonTip.AttachLocation.ALIGNED, 5, 7, false);
		balloon.addDefaultMouseListener(false);
		
		ToolTipUtils.balloonToToolTip(balloon, 10, 3000);
		
		setText(text);
	}

}
