package gui.forms.edit;

import gui.forms.util.EditFormField;
import gui.forms.util.FormField;
import gui.forms.util.FormLabel;
import gui.forms.util.RowPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import common.entity.deposit.Bank;
import common.entity.deposit.BankAccount;
import common.manager.Manager;

import util.EditFormPanel;
import util.ErrorLabel;
import util.SBButton;
import util.TableHeaderLabel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class EditBankForm extends EditFormPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel bankAccountPanel;
	private JScrollPane bankAccountPane;
	private final int ROW_WIDTH = 305, ROW_HEIGHT = 35, LABEL_HEIGHT = 20, LABEL_Y = 0, UPPER_Y = 63, ITEMS_PANE_Y = 25;
	private Object[] array = {};
	private JScrollBar sb;

	private ArrayList<RowPanel> accountRowPanel = new ArrayList<RowPanel>();
	private ArrayList<EditFormField> fields = new ArrayList<EditFormField>();
	private ArrayList<FormLabel> labels = new ArrayList<FormLabel>();
	private JButton addRow;
	private TableHeaderLabel deleteLabel, bankAccountLabel;
	private ImageIcon icon;
	private SoyButton edit;

	private DefaultComboBoxModel model;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";

	public EditBankForm() {
		super("View / Edit Bank");
		init();
		addComponents();
	}

	private void init() {

		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		icon = new ImageIcon("images/util.png");

		bankAccountLabel = new TableHeaderLabel("Bank Accounts");
		deleteLabel = new TableHeaderLabel(icon);

		model = new DefaultComboBoxModel(array);

		bankAccountPanel = new JPanel();
		bankAccountPanel.setLayout(null);
		bankAccountPanel.setOpaque(false);

		bankAccountPane = new JScrollPane(bankAccountPanel);
		bankAccountPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		bankAccountPane.setOpaque(false);
		bankAccountPane.getViewport().setOpaque(false);

		for (int i = 0, y = 15; i < Tables.bankFormLabel.length; i++, y += 55) {
			fields.add(new EditFormField(100));
			labels.add(new FormLabel(Tables.bankFormLabel[i]));
			
			fields.get(i).setBounds(24, y, 200, 25);// 115
			labels.get(i).setBounds(24, y - 15, 200, 15);
			
			panel.add(fields.get(i));
			panel.add(labels.get(i));
		}

		addRow.setBounds(245, 10, 16, 16);

		bankAccountLabel.setBounds(265, 5, 200, 25);
		deleteLabel.setBounds(465, 5, 46, 25);
		bankAccountPane.setBounds(266, 30, 262, 140);//+25

		addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				accountRowPanel.add(new RowPanel(bankAccountPanel, Tables.BANK));
				bankAccountPanel.add(accountRowPanel.get(accountRowPanel.size() - 1));
				alternateRows();

				bankAccountPanel.setPreferredSize(new Dimension(237, bankAccountPanel.getComponentCount() * ROW_HEIGHT));
				bankAccountPanel.updateUI();
				bankAccountPanel.revalidate();

				Rectangle rect = new Rectangle(0, (int) bankAccountPanel.getPreferredSize().getHeight(), 10, 10);
				bankAccountPanel.scrollRectToVisible(rect);
			}
		});

		panel.add(addRow);

		panel.add(bankAccountLabel);
		panel.add(deleteLabel);
		panel.add(bankAccountPane);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(130, 100, 550, 190);

		add(scrollPane);
	}

	private void alternateRows() {

		for (int i = 0; i < accountRowPanel.size(); i++)
			if (i % 2 == 0)
				accountRowPanel.get(i).getRow().setBackground(Values.row1);
			else
				accountRowPanel.get(i).getRow().setBackground(Values.row2);
	}

	public void removeRow(int rowNum) {
		bankAccountPanel.remove(rowNum);
		bankAccountPanel.updateUI();
		bankAccountPanel.revalidate();

		bankAccountPanel.setPreferredSize(new Dimension(237, bankAccountPanel.getComponentCount() * ROW_HEIGHT));

		updateList(rowNum);

		alternateRows();
	}

	private void updateList(int removedRow) {

		for (int i = removedRow + 1; i < accountRowPanel.size(); i++) {
			accountRowPanel.get(i).setBounds(0, accountRowPanel.get(i).getY() - ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT);
			accountRowPanel.get(i).setY(accountRowPanel.get(i).getY() - ROW_HEIGHT);
			// System.out.println("command: "+rowPanel2.get(i).getCommand());
			accountRowPanel.get(i).getDeleteRow().setActionCommand((i - 1) + "");
			accountRowPanel.get(i).updateUI();
			accountRowPanel.get(i).revalidate();
		}

		accountRowPanel.remove(removedRow);
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		edit = new SoyButton("Edit");

		error = new ErrorLabel();

		edit.setBounds(360, 310, 80, 30);

		error.setBounds(305, 350, 200, 30);

		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
			}
		});

		add(edit);
		add(error);

	}

	private boolean isValidated() {

		if (bankAccountPanel.getComponentCount() == 0) {

			msg = "Put at least one item";

			return false;
		}

		return true;

	}

	private void clearForm() {
		bankAccountPanel.removeAll();
		accountRowPanel.clear();

		error.setText("");

	}

	public void refreshAccount() {

	}
}
