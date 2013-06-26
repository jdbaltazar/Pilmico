package util.soy;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class SoyButton extends SoyPanel {

	private String name;
	private SoyLabel title;
	private boolean isHovered;
	private boolean alternate;

	public SoyButton(String name) {
		super();

		this.name = name;
		init();
		addListener();
	}

	public SoyButton(String name, boolean bool) {
		super();

		alternate = true;
		this.name = name;
		init();
		addListener();
	}

	private void init() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

		title = new SoyLabel(name);
		title.setFont(SoyFont.getFont(16));
		title.setForeground(Color.WHITE);
		add(title);
	}

	private void addListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("SOYBUTTON");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				isHovered = true;
				updateUI();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				isHovered = false;
				updateUI();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		if (!alternate) {
			title.setForeground(Color.WHITE);
			if (!isHovered) {
				gradient = new GradientPaint(0, 0, new Color(0,0,128), 0,
						getHeight(), new Color(0, 0, 123));
				g2.setPaint(gradient);
			} else {
				gradient1 = new GradientPaint(0, 0, new Color(30,30,158), 0,
						getHeight(), new Color(25, 25, 148));
				g2.setPaint(gradient1);
			}
		}
		else{
			title.setForeground(Color.BLACK);
			if (!isHovered) {
				gradient = new GradientPaint(0, 0, new Color(220, 220, 220), 0,
						getHeight(), new Color(245, 245, 245));
				g2.setPaint(gradient);
			} else {
				gradient1 = new GradientPaint(0, 0, new Color(245, 245, 245), 0,
						getHeight(), new Color(220, 220, 220));
				g2.setPaint(gradient1);
			}
		}

		g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

		paintChildren(g2);

		g2.dispose();
		g.dispose();

	}
}
