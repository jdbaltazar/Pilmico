package util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.MinimalBalloonStyle;
import net.java.balloontip.styles.RoundedBalloonStyle;
import net.java.balloontip.utils.ToolTipUtils;

public class ErrorLabel extends JLabel{
	
	private BalloonTip balloonTip;
	private JLabel jlabel;

	RoundedBalloonStyle style = new RoundedBalloonStyle(5, 5, Color.decode("#FFD6D6"), Color.red);
	RoundedBalloonStyle wStyle = new RoundedBalloonStyle(5, 5, Color.decode("#FFDB99"), Color.ORANGE);
	
	public ErrorLabel(){
		
		setFont(new Font("Lucida Sans", Font.ITALIC, 11));
		setHorizontalAlignment(JLabel.RIGHT);
//		setBorder(BorderFactory.createEtchedBorder());
		setForeground(Color.red);
		setOpaque(false);
		RoundedBalloonStyle style = new RoundedBalloonStyle(5, 5, Color.decode("#FFD6D6"), Color.red);
		jlabel = new JLabel();
		jlabel.setFont(new Font("Arial Narrow", Font.PLAIN, 14));


		balloonTip = new BalloonTip(
				this,
				jlabel,
				style,
				BalloonTip.Orientation.LEFT_ABOVE,
				BalloonTip.AttachLocation.NORTH,
				5, 7,
				false
			);
		
		ToolTipUtils.balloonToToolTip(balloonTip, 10, 10000);
	}

	public void setToolTip(String text) {
		
		jlabel.setText(text.trim());

		balloonTip.refreshLocation();
		balloonTip.setContents(jlabel);
		
		setText(text);
	}
	
	public void setWarningToolTip(String text) {
		
		jlabel.setText(text.trim());
		
		balloonTip.refreshLocation();
		balloonTip.setContents(jlabel);
		
		balloonTip.setStyle(wStyle);
		
		setText(text);
	}

}
