package gui.forms.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.RoundedBalloonStyle;
import net.java.balloontip.utils.ToolTipUtils;

public class DefaultEntryLabel extends JLabel{
	
	private String label;
	private BalloonTip balloonTip;
	
	public DefaultEntryLabel(String label){
		super(label);
		
		this.label = label;
		
		init();
	}
	
	public DefaultEntryLabel(String label, boolean edit){
		super(label);
		
		this.label = label;
		
		init();
		setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.decode("#006600")));
	}
	
	private void init(){
		setOpaque(false);
		setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		setHorizontalAlignment(JLabel.CENTER);
		
		RoundedBalloonStyle style = new RoundedBalloonStyle(5, 5, Color.white, Color.black);

		balloonTip = new BalloonTip(
				this,
				new JLabel(label),
				style,
				BalloonTip.Orientation.LEFT_ABOVE,
				BalloonTip.AttachLocation.NORTH,
				5, 7,
				false
			);
		
		ToolTipUtils.balloonToToolTip(balloonTip, 10, 3000);
	}
	
	public void setToolTip(String text) {
		
		balloonTip.refreshLocation();
		balloonTip.setContents(new JLabel(text));
		
		setText(text);
	}

}
