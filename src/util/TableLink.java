package util;

import gui.popup.NotesPopup;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class TableLink extends JLabel implements MouseListener{
	
	Font original;
	String label;
	int index;
	
	public TableLink(String label, int index){
		this.label = label;
		this.index = index;
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
//		setBorder(BorderFactory.createLineBorder(Color.black));
		setText(label);
		setFont(new Font("Forte", Font.PLAIN, 18));
//		setForeground(Color.blue.darker());
		setForeground(Color.LIGHT_GRAY);
		
		addMouseListener(this);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void mouseEntered(MouseEvent e) {
		original = e.getComponent().getFont();
		Map attributes = original.getAttributes();
		attributes.put(TextAttribute.UNDERLINE,
				TextAttribute.UNDERLINE_ON);
		e.getComponent().setFont(original.deriveFont(attributes));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		e.getComponent().setFont(original);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Values.centerPanel.changeTable(index);
	}

	@Override
	public void mousePressed(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent m) {
		// TODO Auto-generated method stub
		
	}

}
