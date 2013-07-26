package gui.forms.add;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import util.SimplePanel;
import util.Values;

public class SalesGraphForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel bankAccountPanel;
	private JScrollPane bankAccountPane;
	private JPanel panel;

	public SalesGraphForm() {
		super("Sales Graph");
		init();
		Values.salesGraphForm = this;
	}

	private void init() {

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		bankAccountPanel = new JPanel();
		bankAccountPanel.setLayout(null);
		bankAccountPanel.setOpaque(false);

		bankAccountPane = new JScrollPane(bankAccountPanel);
		bankAccountPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		bankAccountPane.setOpaque(false);
		bankAccountPane.getViewport().setOpaque(false);

	}

	public void refreshAccount() {

	}

}
