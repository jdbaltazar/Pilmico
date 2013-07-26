package gui.forms.edit;

import gui.forms.util.DefaultEntryLabel;
import gui.forms.util.EditFormField;
import gui.forms.util.FormDropdown;
import gui.forms.util.FormLabel;
import gui.popup.SuccessPopup;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import util.EditFormPanel;
import util.ErrorLabel;
import util.SBButton;
import util.Values;
import util.soy.SoyButton;
import util.soy.SoyPanel;

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
	
	private JLabel status;
	private SBButton deactivate;

	private ErrorLabel error;

	/*public EditAccountPanel(Account account) {
		this.account = account;
		init();

		addComponents();
	}*/
	
	public EditAccountPanel() {
		super("View / Edit Account");
		init();
		addComponents();
		fillEntries();
	}

	private void fillEntries() {
		
	}

	private void init() {
		setLayout(null);
		acctType = new FormDropdown(true);
		employee = new DefaultEntryLabel("", true);

		status = new JLabel(new ImageIcon("images/active.png"));
		deactivate = new SBButton("deactivate.png", "deactivate2.png", "Deactivate Account");
		
	/*	try {
			List<AccountType> accountTypes = Manager.accountManager.getAccountTypes();
			acctType = new FormDropdown(accountTypes.toArray());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
*/
	}

	private void addComponents() {

		edit = new SoyButton("Edit");

		error = new ErrorLabel();
		
		status.setBounds(200, 20, 32, 32);
		status.setToolTipText("Active");

		
		int labelsCtr = 0, fieldCtr = 0;

		for (int i = 0, x = 290, y = -5; i < num; i++, y += 73) {

			if (i != 0 && i != 1) {
				fields.add(new EditFormField(100));
				labels.add(new FormLabel(Values.accountFormLabel[i]));

				fields.get(fieldCtr).setBounds(x, 65 + y, 200, 25);
				labels.get(labelsCtr).setBounds(x, 50 + y, 100, 15);
				
				if(i == 2)
					deactivate.setBounds(x + 67, 46 + y, 16, 16);

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
		
		/*try {

			fields.get(0).setText(account.getUsername());
			fields.get(1).setText(account.getPassword());
			fields.get(2).setText(account.getFirstName());
			fields.get(3).setText(account.getMiddleName());
			fields.get(4).setText(account.getLastName());
			fields.get(5).setText(account.getAddress());
			fields.get(6).setText(account.getContactNo());

			int total = acctType.getItemCount();
			while (total > 0) {
				total--;
				AccountType at = (AccountType) acctType.getItemAt(total);
				if (at.getId() == account.getAccountType().getId()) {
					acctType.setSelectedIndex(total);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}*/

		edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			
			}
		});

		add(acctType);
		add(employee);
		add(edit);
		
		add(status);
		add(deactivate);

		add(error);

	}

	private void update() {
		Values.editPanel.startAnimation();
		//Values.salesOrderForm.refreshAccount();
		new SuccessPopup("Edit").setVisible(true);
		Values.centerPanel.changeTable(Values.ACCOUNTS);
	}

	private boolean isValidated() {

//		if (!username.equals("") && !password.equals("") && !firstName.equals("") && !lastName.equals("") && !address.equals(""))
//			return true;

		return false;
	}

}
