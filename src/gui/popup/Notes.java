package gui.popup;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import common.entity.note.Note;
import common.entity.profile.AccountType;
import common.manager.Manager;

import util.DateFormatter;
import util.SBButton;
import util.Utility;
import util.Values;
import util.soy.SoyPanel;

@SuppressWarnings("serial")
public class Notes extends SoyPanel {

	private String command = "";
	private int y;

	private final int ROW_WIDTH = 220, ROW_HEIGHT = 140;

	private JPanel note;
	private JScrollPane descPane;

	private JLabel date, tag;
	private JButton remove;
	private JTextArea desc;
	private BufferedImage image;

	private Note n;

	public Notes(int y, String command, Note n) {
		this.y = y;
		this.command = command;
		this.n = n;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub

		try {
			if (n.getNoteType().getName().equals("Important"))
				image = (BufferedImage) ImageIO.read(new File("images/notes_purple.png"));
			else if (n.getNoteType().getName().equals("Very Important"))
				image = (BufferedImage) ImageIO.read(new File("images/notes_pink.png"));
			else if (n.getNoteType().getName().equals("Reminder"))
				image = (BufferedImage) ImageIO.read(new File("images/notes_yellow.png"));
			else
				image = (BufferedImage) ImageIO.read(new File("images/notes_green.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setLayout(new BorderLayout());
		setOpaque(false);

		note = new JPanel();
		note.setLayout(null);
		note.setOpaque(false);

		date = new JLabel(DateFormatter.getInstance().getFormat(Utility.CompleteFormatWithoutSec).format(n.getDate()));
		date.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		tag = new JLabel(n.getTag());
		tag.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		desc = new JTextArea(n.getNoteType().toString() + ": \n" + n.getDescription());
		desc.setFont(new Font("Kristen ITC", Font.PLAIN, 11));
		desc.setEditable(false);
		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);
		desc.setToolTipText("Created by " + n.getAuthor().getDesignationPlusFirstPlusLastName());
		// desc.setBackground(Color);

		remove = new SBButton("cancel.png", "cancel.png", "Remove");
		remove.setBounds(200, 5, 16, 16);

		remove.setActionCommand(command);

		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				// TODO Auto-generated method stub

				try {

					if (Manager.isAuthorized() || n.getAuthor().getAccountType().getName().equals(AccountType.employee)) {

						Manager.getInstance().getNoteManager().deleteNote(n);
						Values.notesPopup.removeRow(Integer.parseInt(a.getActionCommand()));

						/*
						 * Account acc = Manager.getInstance() .getLoggedInAccount();
						 * Manager.logManager.addLog(new Log(Manager.logManager
						 * .getLogType(LogType.SYSTEM), acc.getUsername() +
						 * " deleted note "+ n.getId()));
						 */
						Values.footerPanel.updateNotes();
					} else {

						// null, "Cannot delete note!", "Not Authorized",
						// JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
						JOptionPane.showMessageDialog(Values.mainFrame, "Not Authorized", "Cannot delete note!", JOptionPane.WARNING_MESSAGE);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		date.setBounds(5, 5, 150, 10);
		tag.setBounds(5, 20, 100, 10);
		// description.setBounds(8, 40, 200, 100);
		desc.setBounds(8, 40, 200, 100);

		descPane = new JScrollPane(desc);
		descPane.setBounds(8, 40, 200, 90);
		descPane.setOpaque(false);
		descPane.setBorder(BorderFactory.createEmptyBorder());

		// if(Manager.loggedInAccount.getAccountType().getName().equals(AccountType.manager))
		note.add(remove);

		note.add(date);
		note.add(tag);
		note.add(descPane);

		/*
		 * rowPanel = new JPanel(); rowPanel.setLayout(new BorderLayout());
		 * rowPanel.setBackground(Color.YELLOW);
		 * rowPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		 * 
		 * // rowPanel.setBounds(0, (notesPanel.getComponentCount() * ROW_HEIGHT),
		 * // ROW_WIDTH, ROW_HEIGHT);
		 * 
		 * rowPanel.add(note);
		 */

		add(note);

		setBounds(0, y, ROW_WIDTH, ROW_HEIGHT);

	}

	public JButton getRemove() {
		return remove;
	}

	public void setRemove(JButton remove) {
		this.remove = remove;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		paintChildren(g2);

		g2.dispose();
		g.dispose();

	}
}
