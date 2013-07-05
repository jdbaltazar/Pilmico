package gui.forms.util;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

import javax.swing.JComponent;
import javax.swing.JViewport;

public class ViewportDragScrollListener extends MouseAdapter implements
		HierarchyListener {
	private static final int SPEED = 4;
	private static final int DELAY = 10;
	private final Cursor dc;
	private final Cursor hc = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
	private final Timer scroller;
	private final JComponent label;
	private Point startPt = new Point();
	private Point move = new Point();

	public ViewportDragScrollListener(JComponent comp) {
		this.label = comp;
		this.dc = comp.getCursor();

		this.scroller = new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JViewport vport = (JViewport) label.getParent();
				Point vp = vport.getViewPosition(); // =
													// SwingUtilities.convertPoint(vport,0,0,label);
				vp.translate(move.x, move.y);
				label.scrollRectToVisible(new Rectangle(vp, vport.getSize())); // vport.setViewPosition(vp);
			}
		});
	}

	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		JComponent c = (JComponent) e.getSource();
		if ((e.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0
				&& !c.isDisplayable()) {
			scroller.stop();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		JViewport vport = (JViewport) e.getSource();
		Point pt = e.getPoint();
		int dx = startPt.x - pt.x;
		int dy = startPt.y - pt.y;
		Point vp = vport.getViewPosition();
		vp.translate(dx, dy);
		label.scrollRectToVisible(new Rectangle(vp, vport.getSize()));
		move.setLocation(SPEED * dx, SPEED * dy);
		startPt.setLocation(pt);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		((JComponent) e.getSource()).setCursor(hc); // label.setCursor(hc);
		startPt.setLocation(e.getPoint());
		move.setLocation(0, 0);
		scroller.stop();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		((JComponent) e.getSource()).setCursor(dc); // label.setCursor(dc);
		scroller.start();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		((JComponent) e.getSource()).setCursor(dc); // label.setCursor(dc);
		move.setLocation(0, 0);
		scroller.stop();
	}
}
