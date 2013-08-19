package gui.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import common.entity.note.Note;
import common.manager.Manager;

import util.SBButton;
import util.SimplePanel;
import util.Values;

public class NotesPopup extends JDialog {

	private int WIDTH = 280, HEIGHT = 400;
	private JPanel panel;
	private JPanel note, rowPanel, notesPanel;
	private JScrollPane notesPane, descPane;

	private JLabel date, tag, description;
	private JButton remove, close;
	private JTextArea desc;

	private ArrayList<Notes> notes = new ArrayList<Notes>();

	private final int ROW_HEIGHT = 140, ROW_WIDTH = 220;

	public NotesPopup() {
		init();
		addComponents();

		Values.notesPopup = this;
	}

	private void init() {

		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		setBackground(new Color(0, 0, 0, 0));

	}

	private void addComponents() {

		close = new SBButton("close.png", "close.png", "Close", true);
		close.setBounds(245, 10, 24, 24);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				Values.mainFrame.dimScreen(false);
			}
		});

		notesPanel = new JPanel();
		notesPanel.setLayout(null);
		notesPanel.setOpaque(false);

		panel = new SimplePanel("View / Remove Notes");
		panel.setOpaque(false);
		/*if (Manager.loggedInAccount.getAccountType().getName()
				.equals(AccountType.manager))
		else
			panel = new SimplePanel("View Notes");*/

		List<Note> ns;
		try {
			ns = Manager.noteManager.getNotes();
			int i = 0;
			for (Note n : ns) {
				notes.add(new Notes(
						notesPanel.getComponentCount() * ROW_HEIGHT, notesPanel
								.getComponentCount() + "", n));
				notesPanel.add(notes.get(i));
				notesPanel.setPreferredSize(new Dimension(ROW_WIDTH, notesPanel
						.getComponentCount() * ROW_HEIGHT));
				i++;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		notesPane = new JScrollPane(notesPanel);
		notesPane.setOpaque(false);
		notesPane.getViewport().setOpaque(false);
		notesPane.setBorder(BorderFactory.createEmptyBorder());

		notesPane.setBounds(23, 55, ROW_WIDTH + 17, 320);

		panel.add(notesPane);

		add(close);
		add(panel);
	}

	public void removeRow(int rowNum) {
		System.out.println("pressed row button: " + rowNum);

		notesPanel.remove(rowNum);
		notesPanel.updateUI();
		notesPanel.revalidate();

		notesPanel.setPreferredSize(new Dimension(ROW_WIDTH, notesPanel
				.getComponentCount() * ROW_HEIGHT));

		updateList(rowNum);
	}

	private void updateList(int removedRow) {

		for (int i = removedRow + 1; i < notes.size(); i++) {
			notes.get(i).setBounds(0, notes.get(i).getY() - ROW_HEIGHT,
					ROW_WIDTH, ROW_HEIGHT);
			notes.get(i).setY(notes.get(i).getY() - ROW_HEIGHT);
			notes.get(i).getRemove().setActionCommand((i - 1) + "");
			notes.get(i).updateUI();
			notes.get(i).revalidate();
		}

		notes.remove(removedRow);

	}
}