package gui.forms.add;

import gui.forms.util.ComboKeyHandler;
import gui.forms.util.IconLabel;
import gui.forms.util.RowPanel;
import gui.forms.util.FormDropdown.ColorArrowUI;
import gui.popup.SuccessPopup;
import gui.popup.UtilityPopup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import common.entity.product.Product;
import common.entity.pullout.PullOut;
import common.entity.pullout.PullOutDetail;
import common.manager.Manager;

import util.ErrorLabel;
import util.MainFormField;
import util.MainFormLabel;
import util.SBButton;
import util.SimplePanel;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Values;
import util.soy.SoyButton;

public class PulloutForm extends SimplePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel productsPanel;
	private JScrollPane productsPane;
	private final int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 80, UPPER_Y = 63, ITEMS_PANE_Y = LABEL_HEIGHT + LABEL_Y;
	private Object[] array = {};
	private JScrollBar sb;

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();
	private JButton addRow;
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, priceKG, priceSACK, productLabel, deleteLabel;
	private SpinnerDate date;
	private ImageIcon icon;
	private SoyButton save;
	private JLabel issuedBy;
	private MainFormLabel issuedByLabel, dateLabel;

	private DefaultComboBoxModel model;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";
	private SBButton fwdProduct;

	public PulloutForm() {
		// TODO Auto-generated constructor stub
		super("Add Pullout Form");
		init();
		addComponents();

		Values.pulloutForm = this;
	};

	private void init() {

		fwdProduct = new SBButton("forward.png", "forward.png", "Add new product");
		addRow = new SBButton("add_row.png", "add_row.png", "Add Row");

		fwdProduct.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Values.addEntryPanel.linkPanel(Values.PRODUCTS);
			}
		});

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		icon = new ImageIcon("images/util.png");

		date = new SpinnerDate(Values.dateFormat);

		dateStatus = new IconLabel(new ImageIcon("images/valid_date.png"), "This date is valid");
		
		date.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println("Date: "+((SpinnerDateModel) date.getModel()).getDate());
				validDate = !((SpinnerDateModel) date.getModel()).getDate()
						.after(new Date());
				if (validDate)
					dateStatus.setIconToolTip(new ImageIcon(
							"images/valid_date.png"), "This date is valid", true);
				else
					dateStatus.setIconToolTip(new ImageIcon(
							"images/invalid_date2.png"),
							"Future date not allowed", false);
			}
		});


		issuedByLabel = new MainFormLabel("Issued by:");
		dateLabel = new MainFormLabel("Date:");

		issuedBy = new JLabel(Manager.loggedInAccount.getFirstPlusLastName());
		issuedBy.setOpaque(false);
		issuedBy.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		issuedBy.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
		issuedBy.setHorizontalAlignment(JLabel.CENTER);

		quantityKGLabel = new TableHeaderLabel("Qtty (kg)");
		productLabel = new TableHeaderLabel("Products");
		quantitySACKlabel = new TableHeaderLabel("Qtty (sack)");
		priceKG = new TableHeaderLabel("Price (kg)");
		priceSACK = new TableHeaderLabel("Price (sack)");
		deleteLabel = new TableHeaderLabel(icon);

		model = new DefaultComboBoxModel(array);

		productsPanel = new JPanel();
		productsPanel.setLayout(null);
		productsPanel.setOpaque(false);

		productsPane = new JScrollPane(productsPanel);

		productsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		productsPane.setOpaque(false);
		productsPane.getViewport().setOpaque(false);

		dateLabel.setBounds(40, 50, 40, 20);// 15x,12y
		date.setBounds(85, 50, 150, 20);
		dateStatus.setBounds(240, 52, 16, 16);

		issuedByLabel.setBounds(300, 50, 70, 20);
		issuedBy.setBounds(375, 50, 180, 20);

		addRow.setBounds(32, LABEL_Y + 5, 16, 16);

		quantitySACKlabel.setBounds(50, LABEL_Y, 77, LABEL_HEIGHT);
		quantityKGLabel.setBounds(127, LABEL_Y, 77, LABEL_HEIGHT);
		priceSACK.setBounds(204, LABEL_Y, 85, LABEL_HEIGHT);
		priceKG.setBounds(289, LABEL_Y, 77, LABEL_HEIGHT);
		productLabel.setBounds(366, LABEL_Y, 207, LABEL_HEIGHT);
		deleteLabel.setBounds(573, LABEL_Y, 42, LABEL_HEIGHT);
		// 136
		fwdProduct.setBounds(502, LABEL_Y + 5, 16, 16);

		productsPane.setBounds(51, ITEMS_PANE_Y, ROW_WIDTH, 140);

		addRow.addActionListener(new ActionListener() {

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
		});

		panel.add(dateLabel);
		panel.add(date);
		panel.add(dateStatus);

		panel.add(issuedByLabel);
		panel.add(issuedBy);

		panel.add(addRow);

		panel.add(fwdProduct);
		panel.add(quantitySACKlabel);
		panel.add(quantityKGLabel);
		panel.add(priceKG);
		panel.add(priceSACK);
		panel.add(productLabel);
		panel.add(deleteLabel);

		panel.add(productsPane);

		scrollPane.setViewportView(panel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setBounds(0, 5, 670, 330);

		add(scrollPane);
	}

	private void alternateRows() {

		for (int i = 0; i < rowPanel.size(); i++)
			if (i % 2 == 0)
				rowPanel.get(i).getRow().setBackground(Values.row1);
			else
				rowPanel.get(i).getRow().setBackground(Values.row2);
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

		save.setBounds(290, 270, 80, 30);

		error.setBounds(365, 260, 260, 22);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (isValidated() && !hasMultipleProduct()
						&& !hasBlankProduct() && !hasZeroQuantity()) {

					PointerInfo a = MouseInfo.getPointerInfo();
					Point b = a.getLocation();

					UtilityPopup uP = new UtilityPopup(b, Values.REMARKS);
					uP.setVisible(true);

					if (!uP.isClosed()) {
						Date d = ((SpinnerDateModel) date.getModel()).getDate();
						PullOut pullOut = new PullOut(d,
								Manager.loggedInAccount);

						for (RowPanel rp : rowPanel) {
							Product product = rp.getSelectedProduct();
							pullOut.addPullOutDetail(new PullOutDetail(pullOut,
									product, product.getCurrentPricePerKilo(),
									product.getCurrentPricePerSack(), rp
											.getQuantityInKilo(), rp
											.getQuantityInSack()));
						}
						
						pullOut.setRemarks(uP.getInput());
						
						try {
							Manager.pullOutManager.addPullOut(pullOut);
							Values.centerPanel.changeTable(Values.PULLOUT);
							new SuccessPopup("Add").setVisible(true);
							clearForm();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} else
					error.setText(msg);

			}
		});

		panel.add(save);
		add(error);

	}
	
	public void setErrorText(String msg){
		error.setText(msg);
	}
	
	public boolean hasMultipleProduct(){
		
		for (int i = 0; i < rowPanel.size(); i++) {
			for (int j = i + 1; j < rowPanel.size(); j++) {
				
				if (rowPanel.get(i).getProductCombo().getSelectedIndex() == rowPanel
						.get(j).getProductCombo().getSelectedIndex()) {
					msg = "No multiple product entry allowed ";
					
					return true;
				}
			}
		}
		
		return false;
	}

	private boolean hasBlankProduct(){
		
		for (int i = 0; i < rowPanel.size(); i++) {
			if(rowPanel.get(i).getProductCombo().getSelectedIndex() == -1){
				
				JTextField field = (JTextField) rowPanel.get(i).getProductCombo().getEditor().getEditorComponent();
				System.out.println(field.getText());
				
				if(!field.getText().equals(""))
					msg = "Unknown product found in row "+(i+1)+" ";
				else
					msg = "No product indicated in row "+(i+1)+" ";
				return true;
			}
		}
		
		return false;
	}
	
	private boolean hasZeroQuantity(){
		
		for (int i = 0; i < rowPanel.size(); i++) {
			
			if(rowPanel.get(i).getQuantityInKilo() == 0d && rowPanel.get(i).getQuantityInSack() == 0d){
				msg = "Both quantities should not be 0 in row "+(i+1)+" ";
				
				return true;
			}
		}
		
		return false;
	}

	private boolean isValidated() {
		
		if (((SpinnerDateModel) date.getModel()).getDate().after(new Date())) {

			msg = "Future date not allowed ";

			return false;
		}

		if (productsPanel.getComponentCount() == 0) {

			msg = "Put at least one product ";

			return false;
		}

		return true;

	}

	private void clearForm() {
		productsPanel.removeAll();
		rowPanel.clear();
		refreshDate();

		error.setText("");

	}

	public void refreshDate() {
		date.setValue(new Date());
	}

	public void refreshAccount() {

	}

}
