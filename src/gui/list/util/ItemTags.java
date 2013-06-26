package gui.list.util;

import gui.forms.util.FormTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.SBButton;
import util.Values;

public class ItemTags extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6757439983333968843L;
	private JTextField field;
	private JTextArea desc, entryDesc;
	private JScrollPane descPane;
	private JButton btn;
	private JPanel entryPanel;
	private String tag;
	private String description;
	private int type;
	private boolean viewOnly;
	private int ROW_WIDTH = 165, ROW_HEIGHT = 100;
	private Object object;

	public ItemTags(String tag, String description, int type, boolean viewOnly,
			int y, Object object) {
		this.tag = tag;
		this.description = description;
		this.type = type;
		this.viewOnly = viewOnly;
		this.object = object;
		init();
		addComponents();
		setBounds(0, y, ROW_WIDTH, ROW_HEIGHT);
	}

	public ItemTags(String tag, int type, boolean viewOnly, Object object) {
		this.tag = tag;
		this.type = type;
		this.viewOnly = viewOnly;
		this.object = object;
		init();
		addComponents();
	}

	private void init() {
		setLayout(new BorderLayout());
		setOpaque(false);
		entryPanel = new JPanel(new BorderLayout());
		entryPanel.setOpaque(false);

		// f = new FormField(tag, 100);

		field = new ListField(tag, 100, viewOnly);

		desc = new FormTextArea(description, 500, null, null);

		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);

		entryDesc = new JTextArea(description);
		entryDesc.setFont(new Font("Lucida Grande", Font.ITALIC, 11));
		entryDesc.setForeground(Color.LIGHT_GRAY);

		entryDesc.setLineWrap(true);
		entryDesc.setWrapStyleWord(true);

		if (viewOnly) {
			btn = new SBButton("edit_xs2.png", "edit_xs.png", "Edit");
			descPane = new JScrollPane(entryDesc);
		} else {
			descPane = new JScrollPane(desc);
			btn = new SBButton("add_xs2.png", "add_xs.png", "Add");
		}

		descPane.setBorder(BorderFactory.createEmptyBorder());

		btn.setPreferredSize(new Dimension(24, 24));
	}

	private void addComponents() {

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("f = " + field.getText());
				// f.setVisible(true);
				addEditEntries();
			}
		});

		entryPanel.add(field, BorderLayout.CENTER);

		entryPanel.add(btn, BorderLayout.LINE_END);
		
//		if (!Manager.loggedInAccount.getAccountType().getName()
//				.equals(AccountType.manager) && type == Values.ACCOUNT_TYPE){
			entryPanel.remove(btn);
			field.setEditable(false);
//		}

		add(entryPanel, BorderLayout.PAGE_START);
		// add(new FormDropdown(new Object[]{"sadasd","asdasdass"}),
		// BorderLayout.CENTER);

		if (type < Values.ACCOUNT_TYPE) {
			add(entryPanel, BorderLayout.PAGE_START);
			add(descPane, BorderLayout.CENTER);
		} else
			add(entryPanel, BorderLayout.CENTER);

	}

	private void addEditEntries() {

		/*if (!field.getText().equals("")) {

			switch (type) {

			case Values.UNITS:
				if (viewOnly) {
					try {

						Unit unit = (Unit) object;
						unit.setName(field.getText());
						unit.setDescription(entryDesc.getText());
						Manager.itemManager.updateUnit(unit);

						Account ac = Manager.loggedInAccount;
						Log log = new Log(ac.getAccountType() + " "
								+ ac.getFirstAndLAstName() + " updated unit "
								+ unit.getId() + " : " + unit.getName());
						Manager.logManager.addLog(log);

						new SuccessPopup("Edit").setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {

						Unit u = new Unit(field.getText(), desc.getText());
						Manager.itemManager.addUnit(u);

						Account ac = Manager.loggedInAccount;
						Log log = new Log(ac.getAccountType() + " "
								+ ac.getFirstAndLAstName() + " added unit "
								+ u.getId() + " : " + u.getName());
						Manager.logManager.addLog(log);

						new SuccessPopup("Add").setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Values.unitList.fillPanel(true);
				break;

			case Values.CONDITION:
				if (viewOnly) {
					try {

						ItemCondition itemCondition = (ItemCondition) object;
						itemCondition.setName(field.getText());
						itemCondition.setDescription(desc.getText());
						Manager.itemManager.updateItemCondition(itemCondition);

						Account ac = Manager.loggedInAccount;
						Log log = new Log(ac.getAccountType() + " "
								+ ac.getFirstAndLAstName()
								+ " updated condition " + itemCondition.getId()
								+ " : " + itemCondition.getName());
						Manager.logManager.addLog(log);

						new SuccessPopup("Edit").setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {

						ItemCondition itemCondition = new ItemCondition(
								field.getText(), desc.getText());
						Manager.itemManager.addItemCondition(itemCondition);

						Account ac = Manager.loggedInAccount;
						Log log = new Log(ac.getAccountType() + " "
								+ ac.getFirstAndLAstName()
								+ " added condition " + itemCondition.getId()
								+ " : " + itemCondition.getName());
						Manager.logManager.addLog(log);

						new SuccessPopup("Add").setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Values.conditionList.fillPanel(true);
				break;

			case Values.CATEGORY:
				if (viewOnly) {
					try {
						Category category = (Category) object;
						category.setName(field.getText());
						category.setDescription(desc.getText());
						Manager.itemManager.updateCategory(category);

						Account ac = Manager.loggedInAccount;
						Log log = new Log(ac.getAccountType() + " "
								+ ac.getFirstAndLAstName()
								+ " updated category " + category.getId()
								+ " : " + category.getName());
						Manager.logManager.addLog(log);

						new SuccessPopup("Edit").setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {

						Category cat = new Category(field.getText(),
								desc.getText());
						Manager.itemManager.addCategory(cat);

						Account ac = Manager.loggedInAccount;
						Log log = new Log(ac.getAccountType() + " "
								+ ac.getFirstAndLAstName() + " added category "
								+ cat.getId() + " : " + cat.getName());
						Manager.logManager.addLog(log);
						new SuccessPopup("Add").setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Values.categoryList.fillPanel(true);
				break;

			case Values.ACCOUNT_TYPE:
				if (viewOnly) {
					try {
						AccountType accountType = (AccountType) object;
						accountType.setName(field.getText());
						Manager.accountManager.updateAccountType(accountType);

						Account ac = Manager.loggedInAccount;
						Log log = new Log(ac.getAccountType() + " "
								+ ac.getFirstAndLAstName()
								+ " updated account type "
								+ accountType.getId() + " : "
								+ accountType.getName());
						Manager.logManager.addLog(log);

						new SuccessPopup("Edit").setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						AccountType at = new AccountType(field.getText());
						Manager.accountManager.addAccountType(at);

						Account ac = Manager.loggedInAccount;
						Log log = new Log(ac.getAccountType() + " "
								+ ac.getFirstAndLAstName()
								+ " added account type " + at.getId() + " : "
								+ at.getName());
						Manager.logManager.addLog(log);

						new SuccessPopup("Add").setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				Values.accountTypeList.fillPanel(true);

				break;

			case Values.NOTE_TYPE:
				if (viewOnly) {
					try {
						NoteType noteType = (NoteType) object;
						noteType.setName(field.getText());
						Manager.noteManager.updateNoteType(noteType);

						Account ac = Manager.loggedInAccount;
						Log log = new Log(ac.getAccountType() + " "
								+ ac.getFirstAndLAstName()
								+ " updated note type " + noteType.getId()
								+ " : " + noteType.getName());
						Manager.logManager.addLog(log);

						new SuccessPopup("Edit").setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {

					try {

						NoteType noteType = new NoteType(field.getText());
						Manager.noteManager.addNoteType(noteType);

						Account ac = Manager.loggedInAccount;
						Log log = new Log(ac.getAccountType() + " "
								+ ac.getFirstAndLAstName()
								+ " added note type " + noteType.getId()
								+ " : " + noteType.getName());
						Manager.logManager.addLog(log);

						new SuccessPopup("Add").setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Values.noteTypeList.fillPanel(true);
				break;

			default:
				System.out.println("default ItemTags.java");
				break;
			}

			Values.itemForm.updateFormDropdowns();
			Values.accountForm.refreshDropdown();
		}

		else {
			Toolkit.getDefaultToolkit().beep();

			JOptionPane.showMessageDialog(null,
					"Cannot add blank entry. Try again.", "System Error",
					JOptionPane.ERROR_MESSAGE);
		}
*/
	}
}
