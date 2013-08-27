package gui.forms.edit;

import gui.forms.util.EditFormField;
import gui.forms.util.FormLabel;
import gui.forms.util.RowPanel;
import gui.popup.SuccessPopup;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import util.EditFormPanel;
import util.ErrorLabel;
import util.SBButton;
import util.TableHeaderLabel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

import common.entity.deposit.Bank;
import common.entity.deposit.BankAccount;
import common.manager.Manager;

public class EditBankForm extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel bankAccountPanel;
	private JScrollPane bankAccountPane;
	private final int ROW_WIDTH = 305, ROW_HEIGHT = 35;

	private ArrayList<RowPanel> accountRowPanel = new ArrayList<RowPanel>();
	private ArrayList<EditFormField> fields = new ArrayList<EditFormField>();
	private ArrayList<FormLabel> labels = new ArrayList<FormLabel>();
	private JButton addRow;
	private TableHeaderLabel deleteLabel, bankAccountLabel;
	private ImageIcon icon;
	private SoyButton edit;

	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";

	private Bank bank;

	public EditBankForm(Bank bank) {
		super("View / Edit Bank");
		this.bank = bank;
		init();
		addComponents();
		fillEntries();

		Values.editBankForm = this;
	}

	public void fillEntries() {
		fields.get(0).setText(bank.getName());
		fields.get(1).setText(bank.getAddress());
		fields.get(2).setText(bank.getContactNo());

		Set<BankAccount> bAccts = bank.getBankAccounts();
		for (BankAccount ba : bAccts) {
			accountRowPanel.add(new RowPanel(ba, bankAccountPanel, Tables.BANK_EDIT));
			bankAccountPanel.add(accountRowPanel.get(accountRowPanel.size() - 1));
			alternateRows();

			bankAccountPanel.setPreferredSize(new Dimension(237, bankAccountPanel.getComponentCount() * ROW_HEIGHT));
			bankAccountPanel.updateUI();
			bankAccountPanel.revalidate();

			Rectangle rect = new Rectangle(0, (int) bankAccountPanel.getPreferredSize().getHeight(), 10, 10);
			bankAccountPanel.scrollRectToVisible(rect);
		}
		// populate accounte portion here
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
		bankAccountPane.setBounds(266, 30, 262, 140);// +25

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

		// panel.add(addRow);

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
				
				if(isValidated()){
				bank.setName(fields.get(0).getText());
				bank.setAddress(fields.get(1).getText());
				bank.setContactNo(fields.get(2).getText());

				try {
					Manager.depositManager.updateBank(bank);
					Values.editPanel.startAnimation();
					new SuccessPopup("Edit").setVisible(true);
					Values.centerPanel.changeTable(Values.BANK);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				}else
					error.setToolTip(msg);

			}
		});

		add(edit);
		add(error);

	}

	private boolean isValidated() {

		if(fields.get(0).getText().equals("")){
			
			msg = "Name is required ";
			
			return false;
		}

		return true;

	}

}
