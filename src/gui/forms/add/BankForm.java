package gui.forms.add;

import gui.forms.util.FormDropdown;
import gui.forms.util.FormField;
import gui.forms.util.RowPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import util.ErrorLabel;
import util.JNumericField;
import util.MainFormField;
import util.MainFormLabel;
import util.SBButton;
import util.SimplePanel;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

import common.entity.profile.Employee;
import common.manager.Manager;

public class BankForm extends SimplePanel {

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
	private ArrayList<FormField> fields = new ArrayList<FormField>();
	private JButton addRow;
	private TableHeaderLabel deleteLabel, bankAccountLabel;
	private ImageIcon icon;
	private SoyButton save;

	private DefaultComboBoxModel model;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";

	public BankForm() {
		super("Add Bank Account");
		init();
		addComponents();

		Values.bankForm = this;
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

		for(int i = 0, y = 80 ; i < Tables.bankFormLabel.length; i++, y+=55){
			fields.add(new FormField(Tables.bankFormLabel[i], 100, Color.white, Color.GRAY));
			fields.get(i).setBounds(24, y, 200, 25);//115
			panel.add(fields.get(i));
		}

		addRow.setBounds(245, 65, 16, 16);

		bankAccountLabel.setBounds(265, 60, 200, 25);
		deleteLabel.setBounds(465, 60, 46, 25);
		bankAccountPane.setBounds(266, 85, 262, 140);

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

		scrollPane.setBounds(0, 0, 550, 310);

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
		save = new SoyButton("Save");

		error = new ErrorLabel();

		save.setBounds(235, LABEL_Y + 255, 80, 30);

		error.setBounds(305, 340, 200, 30);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

		panel.add(save);
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
