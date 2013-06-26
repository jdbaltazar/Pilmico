package gui.list;

import gui.list.util.ItemTags;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import util.Values;
import util.soy.RoundedPanel;

public class NoteTypeList extends RoundedPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3768755243786736446L;
	private JPanel noteTypePanel, p1, p2, p3;
	private JLabel label;
	private JScrollPane pane;
	private Icon icon;

	public NoteTypeList() {
		init();
		addComponents();

		Values.noteTypeList = this;
	}

	private void init() {
		// TODO Auto-generated method stub
		setLayout(new BorderLayout());
		setBackground(Color.RED);

		pane = new JScrollPane();

		icon = new ImageIcon("images/notes_icon.png");

		p1 = new JPanel();

		p2 = new JPanel();

		p3 = new JPanel();

		label = new JLabel("Note Type", icon, SwingConstants.LEFT);

	}

	private void addComponents() {
		// TODO Auto-generated method stub
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Harabara", Font.PLAIN, 16));

		pane.setOpaque(false);
		pane.setBorder(BorderFactory.createEmptyBorder());

		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);

		fillPanel(false);

		add(label, BorderLayout.PAGE_START);
		add(p1, BorderLayout.LINE_START);
		add(p2, BorderLayout.LINE_END);
		add(p3, BorderLayout.PAGE_END);
		add(pane, BorderLayout.CENTER);
	}

	public void fillPanel(boolean refresh) {
		if (refresh)
			remove(getComponent(getComponentCount() - 1));

		noteTypePanel = new JPanel(new GridLayout(0, 1, 0, 0));
		noteTypePanel.setOpaque(false);

		noteTypePanel.add(new ItemTags("Add new type of note",
				Values.NOTE_TYPE, false, null));

	/*	List<NoteType> noteTypes = new ArrayList<NoteType>();
		try {
			noteTypes = Manager.noteManager.getNoteTypes();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (NoteType noteType : noteTypes) {
			noteTypePanel.add(new ItemTags(noteType.getName(),
					Values.NOTE_TYPE, true, noteType));
		}

		try {
			noteTypePanel.setPreferredSize(new Dimension(150,
					(Manager.noteManager.getNoteTypes().size() * 25) + 50));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		pane.setViewportView(noteTypePanel);

		if (refresh) {
			add(pane, BorderLayout.CENTER);
			updateUI();
			revalidate();
		}

	}

}
