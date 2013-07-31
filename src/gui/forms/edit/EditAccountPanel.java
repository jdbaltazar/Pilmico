package gui.forms.edit;

import gui.forms.util.DefaultEntryLabel;
import gui.forms.util.EditFormField;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormLabel;
import gui.forms.util.IconLabel;
import gui.popup.SuccessPopup;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import common.entity.profile.Account;
import common.entity.profile.AccountType;
import common.manager.Manager;

import util.EditFormPanel;
import util.ErrorLabel;
import util.SBButton;
import util.Values;
import util.soy.SoyButton;

public class EditAccountPanel extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<EditFormField> fields = new ArrayList<EditFormField>();
	private ArrayList<FormLabel> labels = new ArrayList<FormLabel>();
	private SoyButton edit;
	private int num = Values.accountFormLabel.length;
	private FormDropdown acctType;
	private DefaultEntryLabel employee;

	private IconLabel status;
	private String statusTooltip;
	private SBButton deactivate, activate;
	private ErrorLabel error;
	private Account account;

	public EditAccountPanel(Account account) {
		super("View / Edit Account");
		this.account = account;
		init();
		addComponents();
		fillEntries();
	}

	private void fillEntries() {

		try {
			activateAccount(account.isActive());
			
			acctType.setModel(new DefaultComboBoxModel(Manager.accountManager.getAccountTypes().toArray()));
			employee.setToolTip(account.getFirstPlusLastName());
			fields.get(0).setText(account.getUsername());
			fields.get(1).setText(account.getPassword());
			
			if (!Manager.isAuthorized()) {
				labels.get(0).setVisible(false);
				acctType.setVisible(false);
				fields.get(0).setEditable(false);
				deactivate.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void init() {
		setLayout(null);
		acctType = new FormDropdown(true);
		employee = new DefaultEntryLabel("", true);

		status = new IconLabel(null, "");
		deactivate = new SBButton("deactivate.png", "deactivate2.png", "Deactivate Account");
		activate = new SBButton("activate.png", "activate2.png", "Reactivate Account");

		deactivate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				activateAccount(false);
			}
		});
		
		activate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				activateAccount(true);
			}
		});
		
		deactivate.setVisible(account.isActive());
		activate.setVisible(!account.isActive());
		/*
		 * try { List<AccountType> accountTypes =
		 * Manager.accountManager.getAccountTypes(); acctType = new
		 * FormDropdown(accountTypes.toArray()); } catch (Exception e2) {
		 * e2.printStackTrace(); }
		 */
	}

	private void addComponents() {

		edit = new SoyButton("Edit");

		error = new ErrorLabel();

		status.setBounds(200, 20, 32, 32);

		int labelsCtr = 0, fieldCtr = 0;

		for (int i = 0, x = 290, y = -5; i < num; i++, y += 73) {

			if (i != 0 && i != 1) {
				fields.add(new EditFormField(100));
				labels.add(new FormLabel(Values.accountFormLabel[i]));

				fields.get(fieldCtr).setBounds(x, 65 + y, 200, 25);
				labels.get(labelsCtr).setBounds(x, 50 + y, 100, 15);

				if (i == 2){
					deactivate.setBounds(x + 67, 46 + y, 16, 16);
					activate.setBounds(x + 67, 46 + y, 16, 16);
				}

				fieldCtr++;
				labelsCtr++;
			}

			if (i == 0) {
				labels.add(new FormLabel(Values.accountFormLabel[i]));
				labels.get(labelsCtr).setBounds(x, 50 + y, 100, 15);

				acctType.setBounds(x, 65 + y, 200, 25);

				labelsCtr++;
			}

			if (i == 1) {
				labels.add(new FormLabel(Values.accountFormLabel[i]));
				labels.get(labelsCtr).setBounds(x, 50 + y, 100, 15);

				employee.setBounds(x, 65 + y, 200, 25);

				labelsCtr++;
			}

		}

		edit.setBounds(360, 350, 80, 30);

		error.setBounds(550, 320, 230, 25);

		for (int i = 0; i < fields.size(); i++)
			add(fields.get(i));

		for (int i = 0; i < labels.size(); i++) {
			add(labels.get(i));
		}

		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				account.setAccountType((AccountType) acctType.getSelectedItem());
				account.setUsername(fields.get(0).getText());
				account.setPassword(fields.get(1).getText());
				try {
					Manager.accountManager.updateAccount(account);

					update();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		add(acctType);
		add(employee);
		add(edit);

		add(status);
		
		add(deactivate);
		add(activate);

		add(error);

	}
	
	private void activateAccount(boolean active){

		account.setActive(active);
		
		deactivate.setVisible(active);
		activate.setVisible(!active);
		
		ImageIcon icon = account.isActive() ? new ImageIcon("images/active.png") : new ImageIcon("images/inactive.png");
		statusTooltip = account.isActive() ? "Active" : "Inactive";
		
		status.setIconToolTip(icon, statusTooltip, account.isActive());
	}

	private void update() {
		Values.editPanel.startAnimation();
		new SuccessPopup("Edit").setVisible(true);
		Values.centerPanel.changeTable(Values.ACCOUNTS);
	}

	private boolean isValidated() {

		// if (!username.equals("") && !password.equals("") &&
		// !firstName.equals("") && !lastName.equals("") && !address.equals(""))
		// return true;

		return false;
	}

}
