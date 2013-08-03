package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class TimeLabel extends JLabel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7214097620635206322L;
	private static TimeLabel ins;
	private javax.swing.Timer timer;
	public static TimeLabel timeLabel = null;
	private Date now;

	public TimeLabel() {
		setFont(new Font("Calibri", 0, 14));
		setForeground(new Color(0, 0, 77));
		setHorizontalAlignment(JLabel.RIGHT);
		timer = new javax.swing.Timer(1000, this);
		timer.setRepeats(true);
		timer.start();
	}

	/*
	 * public static TimeLabel getComponent() { if (ins == null) ins = new
	 * TimeLabel(); return ins; }
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		now = new Date();
		setText(DateFormatter.getInstance().getFormat(Utility.DMYHMSAFormat).format(now));
	}
}
