package gui.forms.add;

import gui.forms.util.IconLabel;
import gui.forms.util.RowPanel;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import common.entity.product.Product;
import common.entity.product.exception.NegativeValueException;
import common.entity.product.exception.NotEnoughQuantityException;
import common.entity.product.exception.ZeroKilosPerSackException;
import common.entity.pullout.PullOut;
import common.entity.pullout.PullOutDetail;
import common.manager.Manager;

import util.ErrorLabel;
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
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, kgpersack, priceKG, priceSACK, productLabel, deleteLabel;
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
		super("Add Pullout Form");
		init();
		addComponents();

		Values.pulloutForm = this;
	};

	@SuppressWarnings({ "unchecked", "rawtypes" })
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

		error = new ErrorLabel();
		dateStatus = new IconLabel(new ImageIcon("images/valid_date.png"), Values.VALID_DATE);
		date.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				// System.out.println("Date: "+((SpinnerDateModel)
				// date.getModel()).getDate());
				validDate = !((SpinnerDateModel) date.getModel()).getDate().after(new Date());
				// if (validDate)
				dateStatus.setIconToolTip(new ImageIcon("images/valid_date.png"), Values.VALID_DATE, true);
				// else
				// dateStatus.setIconToolTip(new
				// ImageIcon("images/invalid_date2.png"), "Future date not allowed",
				// false);
				determineDateStatus();
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
		kgpersack = new TableHeaderLabel("kg / sk");
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
		kgpersack.setBounds(204, LABEL_Y, 50, LABEL_HEIGHT);
		priceSACK.setBounds(254, LABEL_Y, 85, LABEL_HEIGHT);
		priceKG.setBounds(339, LABEL_Y, 77, LABEL_HEIGHT);
		productLabel.setBounds(416, LABEL_Y, 167, LABEL_HEIGHT);
		deleteLabel.setBounds(583, LABEL_Y, 32, LABEL_HEIGHT);
	
		// 136
		fwdProduct.setBounds(532, LABEL_Y + 5, 16, 16);

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
		panel.add(kgpersack);
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

		add(error);
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

		save.setBounds(290, 270, 80, 30);

		error.setBounds(375, 256, 250, 22);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (isValidated() && !hasMultipleProduct() && !hasBlankProduct() && !hasInvalidQuantity() && !hasZeroQuantity()) {

					boolean valid = true;
					for (RowPanel rp : rowPanel) {
						Product p = rp.getSelectedProduct();
						try {
							if (!Product.validDecrement(p.getSacks(), p.getKilosOnDisplay(), p.getKilosPerSack(), rp.getQuantityInSack(),
									rp.getQuantityInKilo())) {
								valid = false;
							}
						} catch (NegativeValueException e1) {
							e1.printStackTrace();
						} catch (NotEnoughQuantityException e1) {
							e1.printStackTrace();
						} catch (ZeroKilosPerSackException e2) {
							e2.printStackTrace();
						}
					}

						PointerInfo a = MouseInfo.getPointerInfo();
						Point b = a.getLocation();

						UtilityPopup uP = new UtilityPopup(b, Values.REMARKS);
						uP.setVisible(true);

						if (!uP.isClosed()) {
							Date d = ((SpinnerDateModel) date.getModel()).getDate();
							PullOut pullOut = new PullOut(d, Manager.loggedInAccount);

							for (RowPanel rp : rowPanel) {
								Product product = rp.getSelectedProduct();
								pullOut.addPullOutDetail(new PullOutDetail(pullOut, product, product.getCurrentPricePerKilo(), product
										.getCurrentPricePerSack(), rp.getQuantityInKilo(), rp.getQuantityInSack()));
							}

							pullOut.setRemarks(uP.getInput());

							try {
								Manager.pullOutManager.addPullOut(pullOut);

								for (RowPanel rp : rowPanel) {
									Product product = rp.getSelectedProduct();
									product.decrementQuantity(rp.getQuantityInSack(), rp.getQuantityInKilo());
									Manager.productManager.updateProduct(product);
								}

								Values.centerPanel.changeTable(Values.PULLOUT);
								new SuccessPopup("Add").setVisible(true);
								clearForm();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
				}
				else
					error.setToolTip(msg);
				/* else {

					JOptionPane.showMessageDialog(Values.mainFrame, "This action will result to NEGATIVE QUANTITY to product/s in this form: "
							+ "\n In order to proceed: " + "\n (1) Update the quantity of the affected product/s; or"
							+ "\n (2) Add a delivery for affected product/s; or" + "\n (3) Invalidate a Sales and/or Account Receivables", "Not Allowed!",
							JOptionPane.WARNING_MESSAGE);
				}*/
			}
		});

		panel.add(save);
	}

	public void setErrorText(String msg) {
		error.setToolTip(msg);
	}

	public boolean hasMultipleProduct() {

		for (int i = 0; i < rowPanel.size(); i++) {
			for (int j = i + 1; j < rowPanel.size(); j++) {

				if (rowPanel.get(i).getProductCombo().getSelectedIndex() == rowPanel.get(j).getProductCombo().getSelectedIndex()) {
					msg = "No multiple product entry allowed ";

					return true;
				}
			}
		}

		return false;
	}

	private boolean hasBlankProduct() {

		for (int i = 0; i < rowPanel.size(); i++) {
			if (rowPanel.get(i).getProductCombo().getSelectedIndex() == -1) {

				JTextField field = (JTextField) rowPanel.get(i).getProductCombo().getEditor().getEditorComponent();
				System.out.println(field.getText());

				if (!field.getText().equals(""))
					msg = "Unknown product found in row " + (i + 1) + " ";
				else
					msg = "No product indicated in row " + (i + 1) + " ";
				return true;
			}
		}

		return false;
	}

	private boolean hasZeroQuantity() {

		for (int i = 0; i < rowPanel.size(); i++) {

			if (rowPanel.get(i).getQuantityInKilo() == 0d && rowPanel.get(i).getQuantityInSack() == 0d) {
				msg = "Both quantities should not be 0 in row " + (i + 1) + " ";

				return true;
			}
		}

		return false;
	}

	private void determineDateStatus() {

		formDate = ((SpinnerDateModel) date.getModel()).getDate();

		try {
			if (!Manager.inventorySheetDataManager.isValidFor(formDate)) {

				String str = Manager.inventorySheetDataManager.getValidityRemarksFor(formDate);
				dateStatus.setIconToolTip(new ImageIcon("images/invalid_date2.png"), str, false);
				error.setToolTip(str);
			}

			else {
				dateStatus.setIconToolTip(new ImageIcon("images/valid_date.png"), "Valid date", true);
				error.setToolTip("");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean hasInvalidQuantity() {

		for (int i = 0; i < rowPanel.size(); i++) {

			if (rowPanel.get(i).getQuantityInSack() > rowPanel.get(i).getSelectedProduct().getTotalQuantityInSack()) {

				msg = "Invalid sack qty. Only " + rowPanel.get(i).getSelectedProduct().getTotalQuantityInSack() + " left for product in row " + (i + 1) + ". ";

				return true;
			}

			if (rowPanel.get(i).getQuantityInKilo() > rowPanel.get(i).getSelectedProduct().getTotalQuantityInKilo()) {
				msg = "Invalid sack kg. Only " + rowPanel.get(i).getSelectedProduct().getTotalQuantityInKilo() + " left for product in row " + (i + 1) + ". ";

				return true;
			}
			
			if (rowPanel.get(i).getQuantityInKilo() + (rowPanel.get(i).getQuantityInSack() * rowPanel.get(i).getSelectedProduct().getKilosPerSack()) > rowPanel.get(i).getSelectedProduct().getTotalQuantityInKilo()) {
				msg = "Invalid qty inputs in row " + (i + 1)+". Total qty would exceed the total qty in kilos ("+rowPanel.get(i).getSelectedProduct().getTotalQuantityInKilo()+") of the product. ";

				return true;
			}
		}

		return false;
	}

	private boolean isValidated() {

		if (((SpinnerDateModel) date.getModel()).getDate().after(new Date())) {
			formDate = ((SpinnerDateModel) date.getModel()).getDate();

			try {
				if (!Manager.inventorySheetDataManager.isValidFor(formDate)) {
					msg = Manager.inventorySheetDataManager.getValidityRemarksFor(formDate);
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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

		error.setToolTip("");

	}

	public void refreshDate() {
		date.setValue(new Date());
	}

	public void refreshAccount() {

	}

	public void setProductQuantities(double qtySack, double qtyKG) {
		this.qtySack = qtySack;
		this.qtyKG = qtyKG;
	}
}
