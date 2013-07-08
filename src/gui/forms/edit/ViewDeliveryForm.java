package gui.forms.edit;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.RowPanel;
import gui.forms.util.FormDropdown.ColorArrowUI;
import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;

import common.entity.delivery.Delivery;
import common.entity.delivery.DeliveryDetail;
import common.entity.product.Product;
import common.entity.store.Store;
import common.entity.supplier.Supplier;
import common.manager.Manager;

import util.EditFormPanel;
import util.ErrorLabel;
import util.FormCheckbox;
import util.MainFormField;
import util.MainFormLabel;
import util.SBButton;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Values;
import util.soy.SoyButton;

public class ViewDeliveryForm extends EditFormPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel productsPanel, row, p;
	private JScrollPane productsPane;
	private int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y, ITEMS_PANE_Y = 116; // 125
	private Object[] array = {};

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, priceKG, priceSACK, productLabel;
	private ImageIcon icon;
	private SoyButton save;
	private ViewFormField terms, po_no, delivery_no, date, receivedBy, supplier;
	private ViewFormLabel termsLabel, ponumLabel, deliveryNumLabel, dateLabel, receivedByLabel, supplierLabel, remarks;

	private DefaultComboBoxModel model;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";
	
	private JLabel status;

	public ViewDeliveryForm() {
		// TODO Auto-generated constructor stub
		super("View Delivery Form");
		init();
		addComponents();

	};

	private void init() {

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		icon = new ImageIcon("images/invalidated.png");
		
		status = new JLabel("VOID", icon, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));
		status.setForeground(Color.RED);
		
		remarks = new ViewFormLabel("-wrong date and wrong number of items", true);
		

		date = new ViewFormField("");
		supplier = new ViewFormField("");

		termsLabel = new ViewFormLabel("Terms:");
		ponumLabel = new ViewFormLabel("PO_No:");
		deliveryNumLabel = new ViewFormLabel("Delivery No.:");
		dateLabel = new ViewFormLabel("Date:");

		receivedByLabel = new ViewFormLabel("Received by:");
		supplierLabel = new ViewFormLabel("Supplier:");

		receivedBy = new ViewFormField(Manager.loggedInAccount.getEmployee().getFirstPlusLastName());

		terms = new ViewFormField("");
		po_no = new ViewFormField("");
		delivery_no = new ViewFormField("");

		quantityKGLabel = new TableHeaderLabel("Qtty (kg)");
		productLabel = new TableHeaderLabel("Products");
		quantitySACKlabel = new TableHeaderLabel("Qtty (sack)");
		priceKG = new TableHeaderLabel("Price (kg)");
		priceSACK = new TableHeaderLabel("Price (sack)");

		model = new DefaultComboBoxModel(array);

		productsPanel = new JPanel();
		productsPanel.setLayout(null);
		productsPanel.setOpaque(false);

		productsPane = new JScrollPane(productsPanel);

		productsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		productsPane.setOpaque(false);
		productsPane.getViewport().setOpaque(false);

		dateLabel.setBounds(27, 12, 70, 20); // 15
		date.setBounds(102, 10, 170, 20);

		receivedByLabel.setBounds(305, 12, 80, 20);// 260
		receivedBy.setBounds(390, 10, 200, 20);

		ponumLabel.setBounds(27, 35, 70, 20);
		po_no.setBounds(102, 32, 70, 20);

		deliveryNumLabel.setBounds(305, 35, 80, 20); // 260
		delivery_no.setBounds(390, 32, 140, 20);

		supplierLabel.setBounds(27, 58, 70, 20);
		supplier.setBounds(102, 56, 200, 20);

		termsLabel.setBounds(305, 58, 80, 20);
		terms.setBounds(390, 56, 200, 20);

		setupTable(91, true);

		/*addRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				rowPanel.add(new RowPanel(productsPanel, Values.ADD));
				productsPanel.add(rowPanel.get(rowPanel.size() - 1));
				alternateRows();

				productsPanel.setPreferredSize(new Dimension(330, productsPanel.getComponentCount() * ROW_HEIGHT));
				productsPanel.updateUI();
				productsPanel.revalidate();

				Rectangle rect = new Rectangle(0, (int) productsPanel.getPreferredSize().getHeight(), 10, 10);
				productsPanel.scrollRectToVisible(rect);
			}
		});*/

		showUnrequired(true);

		panel.add(dateLabel);
		panel.add(date);

		panel.add(receivedByLabel);
		panel.add(receivedBy);

		panel.add(ponumLabel);
		panel.add(po_no);

		panel.add(deliveryNumLabel);
		panel.add(delivery_no);

		// panel.add(issuedOnLabel);
		// panel.add(issueDate);

		panel.add(supplierLabel);
		panel.add(supplier);

		panel.add(termsLabel);
		panel.add(terms);

		panel.add(quantityKGLabel);
		panel.add(quantitySACKlabel);
		panel.add(priceKG);
		panel.add(priceSACK);
		panel.add(productLabel);

		panel.add(productsPane);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.INVALIDATED_COLOR));

		scrollPane.setBounds(85, 62, 630, 285);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 100, 20);
		remarks.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 2, scrollPane.getWidth(), 20);
		
		add(scrollPane);
		add(status);
		add(remarks);
	}

	private void alternateRows() {

		for (int i = 0; i < rowPanel.size(); i++)
			if (i % 2 == 0)
				rowPanel.get(i).getRow().setBackground(Values.row1);
			else
				rowPanel.get(i).getRow().setBackground(Values.row2);
	}

	private void setupTable(int y, boolean shownFields) {
		LABEL_Y = y;

		quantitySACKlabel.setBounds(25, LABEL_Y, 77, LABEL_HEIGHT);
		quantityKGLabel.setBounds(102, LABEL_Y, 77, LABEL_HEIGHT);
		priceSACK.setBounds(179, LABEL_Y, 85, LABEL_HEIGHT);
		priceKG.setBounds(264, LABEL_Y, 77, LABEL_HEIGHT);
		productLabel.setBounds(341, LABEL_Y, 249, LABEL_HEIGHT);

		if (shownFields) {
			productsPane.setBounds(26, ITEMS_PANE_Y, ROW_WIDTH, 140);
		} else {
			productsPane.setBounds(26, 75, ROW_WIDTH, 175);
		}

	}

	private void showUnrequired(boolean show) {
		supplierLabel.setVisible(show);
		supplier.setVisible(show);

		ponumLabel.setVisible(show);
		po_no.setVisible(show);

		termsLabel.setVisible(show);
		terms.setVisible(show);

		deliveryNumLabel.setVisible(show);
		delivery_no.setVisible(show);

	}

	public void removeRow(int rowNum) {
		productsPanel.remove(rowNum);
		productsPanel.updateUI();
		productsPanel.revalidate();

		productsPanel.setPreferredSize(new Dimension(330, productsPanel.getComponentCount() * ROW_HEIGHT));
		updateList(rowNum);

		alternateRows();
	}

	private void updateList(int removedRow) {

		for (int i = removedRow + 1; i < rowPanel.size(); i++) {
			rowPanel.get(i).setBounds(0, rowPanel.get(i).getY() - ROW_HEIGHT, ROW_WIDTH, ROW_HEIGHT);
			rowPanel.get(i).setY(rowPanel.get(i).getY() - ROW_HEIGHT);
			// System.out.println("command: "+rowPanel2.get(i).getCommand());
			rowPanel.get(i).getDeleteRow().setActionCommand((i - 1) + "");
			rowPanel.get(i).updateUI();
			rowPanel.get(i).revalidate();
		}

		rowPanel.remove(removedRow);
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		save = new SoyButton("Save");

		error = new ErrorLabel();

		save.setBounds(275, 275, 80, 30);

		error.setBounds(305, 340, 200, 30);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

//		panel.add(save);
		add(error);

	}

}