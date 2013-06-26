package gui.popup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import util.SBButton;
import util.SimplePanel;
import util.TableHeaderLabel;
import util.Values;
import util.soy.SoyButton;

public class ProductOnDisplayPopup extends JDialog{
	
	private int WIDTH = 440, HEIGHT = 260;
	private JPanel panel;
	private JPanel onDisplayPanel;
	private JScrollPane productsPane;
	private SoyButton update;
	
	private SBButton close;
	private final int ROW_WIDTH = 392, ROW_HEIGHT = 140, LABEL_HEIGHT = 25, LABEL_Y = 50, PRODUCTS_PANE_Y = 74;

	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, productLabel, deleteLabel;

	private ArrayList<Notes> notes = new ArrayList<Notes>();

	private ImageIcon icon;

	public ProductOnDisplayPopup() {
		Values.mainFrame.dimScreen(true);
		init();
		addComponents();
	}

	private void init() {

		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(JDialog.ModalityType.APPLICATION_MODAL);

	}

	private void addComponents() {
		
		icon = new ImageIcon("images/util.png");
		
		close = new SBButton("close.png", "close.png", "Close");
		close.setBounds(408, 10, 24, 24);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				Values.mainFrame.dimScreen(false);
			}
		});
		
		update = new SoyButton("Update");
		update.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent m){
				dispose();
				Values.mainFrame.dimScreen(false);
			}
		});
		update.setBounds(185, 212, 80, 30);
		
		quantityKGLabel = new TableHeaderLabel("Qtty (kg)");
		productLabel = new TableHeaderLabel("Products");
		quantitySACKlabel = new TableHeaderLabel("Qtty (sack)");
		deleteLabel = new TableHeaderLabel(icon);
		
		quantitySACKlabel.setBounds(23, LABEL_Y, 77, LABEL_HEIGHT);
		quantityKGLabel.setBounds(100, LABEL_Y, 77, LABEL_HEIGHT);
		productLabel.setBounds(177, LABEL_Y, 197, LABEL_HEIGHT);

		deleteLabel.setBounds(374, LABEL_Y, 42, LABEL_HEIGHT);

		onDisplayPanel = new JPanel();
		onDisplayPanel.setLayout(null);
		onDisplayPanel.setOpaque(false);
		
			panel = new SimplePanel("Update Products On Display");

			productsPane = new JScrollPane(onDisplayPanel);
			productsPane.setOpaque(false);
			productsPane.getViewport().setOpaque(false);
//			productsPane.setBorder(BorderFactory.createEmptyBorder());

			productsPane.setBounds(24, PRODUCTS_PANE_Y, ROW_WIDTH, 120);

		panel.add(productsPane);
		panel.add(quantityKGLabel);
		panel.add(quantitySACKlabel);
		panel.add(productLabel);
		panel.add(deleteLabel);
		panel.add(update);

		add(close);
		add(panel);
	}

	public void removeRow(int rowNum) {
		System.out.println("pressed row button: " + rowNum);

		onDisplayPanel.remove(rowNum);
		onDisplayPanel.updateUI();
		onDisplayPanel.revalidate();

		onDisplayPanel.setPreferredSize(new Dimension(ROW_WIDTH, onDisplayPanel
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

		System.out.println("rowpanel2 size: " + notes.size());

	}

}
