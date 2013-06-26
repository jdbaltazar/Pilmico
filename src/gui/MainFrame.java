package gui;

import gui.list.util.ItemTags;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.DarkBackground;
import util.Values;

public class MainFrame extends JFrame {
	private Point point = new Point();
	private DarkBackground dbkg;
	private JPanel frame;
	private static final Color DarkColor = new Color(0, 0, 0, 60);

	public MainFrame() {
		super(":: Pilmico Inventory System ::");
		init();
		addPanels();
		
		Values.mainFrame = this;
	}

	private void init() {

		// setLayout(null);

		dbkg = new DarkBackground();
		
		
		setMinimumSize(new Dimension(800, 600));
		setMaximumSize(new Dimension(800, 600));

		setResizable(false);
		setUndecorated(true);
		
		setIconImage(new ImageIcon("images/frameicon.png").getImage());

		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (!e.isMetaDown()) {
					point.x = e.getX();
					point.y = e.getY();
				}
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (!e.isMetaDown()) {
					Point p = getLocation();
					setLocation(p.x + e.getX() - point.x, p.y + e.getY()
							- point.y);
				}
			}
		});

	}

	private void addPanels() {
		getContentPane().add(dbkg, BorderLayout.LINE_START);
		dbkg.setGlassPane(new MyGlassPane(DarkColor));
		
		frame = new JPanel();
		frame.setBorder(BorderFactory.createMatteBorder(1, 1,1, 1, Color.LIGHT_GRAY));
		frame.setLayout(new BorderLayout());
	
		TopPanel top = new TopPanel();
		top.showMenuButtons(false);

		CenterPanel center = new CenterPanel();
		FooterPanel footer = new FooterPanel();
		footer.showMenuButtons(false);

		frame.add(top, BorderLayout.PAGE_START);
		frame.add(center, BorderLayout.CENTER);
		frame.add(footer, BorderLayout.PAGE_END);
		// add(new TestEdit(), BorderLayout.LINE_START);
		// add(new TestEdit(), BorderLayout.PAGE_END);
		
		add(frame, BorderLayout.CENTER);
	}
	
	public void dimScreen(boolean toggle){
		dbkg.setGlassPaneVisible(toggle);
	}

	public void showFrame() {
		setVisible(true);
	}
	
	public void closeFrame(){
		System.exit(1);
	}
	
	public void minimizeFrame(){
		setState(Frame.ICONIFIED);
	}

}

class MyGlassPane extends JComponent {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private Color backgroundColor;

	public MyGlassPane(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void itemStateChanged(ItemEvent e) {
		setVisible(e.getStateChange() == ItemEvent.SELECTED);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

}
