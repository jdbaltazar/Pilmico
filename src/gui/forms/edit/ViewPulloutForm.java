package gui.forms.edit;

import gui.forms.util.EditRowPanel;
import gui.forms.util.RemarksLabel;
import gui.forms.util.RowPanel;
import gui.forms.util.ViewFormBorder;
import gui.forms.util.ViewFormField;
import gui.forms.util.ViewFormLabel;
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
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;

import common.entity.product.Product;
import common.entity.pullout.PullOut;
import common.entity.pullout.PullOutDetail;
import common.manager.Manager;

import util.DateFormatter;
import util.EditFormPanel;
import util.ErrorLabel;
import util.MainFormLabel;
import util.SBButton;
import util.SpinnerDate;
import util.TableHeaderLabel;
import util.Utility;
import util.Values;
import util.soy.SoyButton;

public class ViewPulloutForm extends EditFormPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 657028396500673907L;
	private JPanel productsPanel;
	private JScrollPane productsPane;
	private final int ROW_WIDTH = 580, ROW_HEIGHT = 35, LABEL_HEIGHT = 25, LABEL_Y = 45, UPPER_Y = 63, ITEMS_PANE_Y = LABEL_HEIGHT + LABEL_Y;
	private Object[] array = {};

	private ArrayList<EditRowPanel> rowPanel = new ArrayList<EditRowPanel>();
	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, priceKG, priceSACK, productLabel;
	private ImageIcon icon;
	private SoyButton save;

	private ViewFormField issuedBy, date;
	private ViewFormLabel issuedByLabel, dateLabel;

	private DefaultComboBoxModel model;
	private JPanel panel;
	private JScrollPane scrollPane;

	private ErrorLabel error;
	private String msg = "";

	private SBButton voidBtn;
	private JLabel status;

	private PullOut pullOut;

	public ViewPulloutForm(PullOut pullOut) {
		// TODO Auto-generated constructor stub
		super("View Pullout Form");
		this.pullOut = pullOut;
		init();
		addComponents();

		colorTable();
		fillEntries();

	};

	private void init() {

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);

		scrollPane = new JScrollPane();

		status = new JLabel("PENDING", null, JLabel.LEADING);
		status.setFont(new Font("Orator STD", Font.PLAIN, 14));
		status.setForeground(Color.orange);

		remarks = new RemarksLabel("");

		date = new ViewFormField("July 12, 2013");

		issuedByLabel = new ViewFormLabel("Issued by:");
		dateLabel = new ViewFormLabel("Date:");

		issuedBy = new ViewFormField("");

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

		dateLabel.setBounds(35, 15, 40, 20);// 15x,12y
		date.setBounds(90, 14, 180, 20);

		issuedByLabel.setBounds(300, 15, 70, 20);
		issuedBy.setBounds(375, 14, 200, 20);

		quantitySACKlabel.setBounds(20, LABEL_Y, 77, LABEL_HEIGHT);
		quantityKGLabel.setBounds(97, LABEL_Y, 77, LABEL_HEIGHT);
		priceSACK.setBounds(174, LABEL_Y, 85, LABEL_HEIGHT);
		priceKG.setBounds(259, LABEL_Y, 77, LABEL_HEIGHT);
		productLabel.setBounds(336, LABEL_Y, 249, LABEL_HEIGHT);
		// 136

		productsPane.setBounds(21, ITEMS_PANE_Y, ROW_WIDTH, 140);

		/*
		 * addRow.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) {
		 * rowPanel.add(new RowPanel(productsPanel, Values.ADD));
		 * productsPanel.add(rowPanel.get(rowPanel.size() - 1)); alternateRows();
		 * 
		 * productsPanel.setPreferredSize(new Dimension(330,
		 * productsPanel.getComponentCount() * ROW_HEIGHT));
		 * productsPanel.updateUI(); productsPanel.revalidate();
		 * 
		 * Rectangle rect = new Rectangle(0, (int)
		 * productsPanel.getPreferredSize().getHeight(), 10, 10);
		 * productsPanel.scrollRectToVisible(rect); } });
		 */

		panel.add(dateLabel);
		panel.add(date);

		panel.add(issuedByLabel);
		panel.add(issuedBy);

		panel.add(quantitySACKlabel);
		panel.add(quantityKGLabel);
		panel.add(priceKG);
		panel.add(priceSACK);
		panel.add(productLabel);

		panel.add(productsPane);

		scrollPane.setViewportView(panel);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));
		/* scrollPane.setBorder(BorderFactory.createEmptyBorder()); */

		scrollPane.setBounds(94, 88, 620, 235);

		status.setBounds(scrollPane.getX(), scrollPane.getY() - 20, 150, 20);
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

	private void addComponents() {
		// TODO Auto-generated method stub

		voidBtn = new SBButton("invalidate.png", "invalidate2.png", "Void");
		voidBtn.setBounds(Values.WIDTH - 28, 9, 16, 16);

		save = new SoyButton("Save");

		error = new ErrorLabel();

		save.setBounds(290, 270, 80, 30);

		error.setBounds(305, 340, 200, 30);

		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}
		});

		voidBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b = a.getLocation();
				UtilityPopup uP = new UtilityPopup(b, Values.INVALIDATE);
				uP.setVisible(true);

				if (!uP.getInput().equals("")) {
					pullOut.setValid(false);
					pullOut.setRemarks(uP.getInput());

					try {
						Manager.pullOutManager.updatePullOut(pullOut);

						for (PullOutDetail pod : pullOut.getPullOutDetails()) {
							Product p = pod.getProduct();
							p.incrementQuantityInSack(pod.getQuantityInSack());
							p.incrementQuantityInKilo(pod.getQuantityInKilo());
							Manager.productManager.updateProduct(p);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					Values.editPanel.startAnimation();
					new SuccessPopup("Invalidation").setVisible(true);
					Values.centerPanel.changeTable(Values.PULLOUT);
				}
			}
		});

		// panel.add(save);
		add(voidBtn);
		add(error);

	}

	private void colorTable() {

		String s = "";
		if (pullOut.getInventorySheetData() != null) {
			icon = new ImageIcon("images/accounted.png");
			s = "ACCOUNTED";
			status.setForeground(Color.GREEN.darker());
			remarks.setForeground(Color.GREEN.darker());
			scrollPane.setBorder(new ViewFormBorder(Values.ACCOUNTED_COLOR));
		} else {
			if (pullOut.isValid()) {
				icon = new ImageIcon("images/pending.png");
				s = "PENDING";
				status.setForeground(Color.orange);
				remarks.setForeground(Color.orange);
				scrollPane.setBorder(new ViewFormBorder(Values.PENDING_COLOR));
			} else {
				icon = new ImageIcon("images/invalidated.png");
				s = "INVALIDATED";
				status.setForeground(Color.RED);
				remarks.setForeground(Color.RED);
				scrollPane.setBorder(new ViewFormBorder(Values.INVALIDATED_COLOR));
			}
		}
		status.setText(s);
		status.setIcon(icon);

	}

	private void fillEntries() {

		voidBtn.setVisible(pullOut.getInventorySheetData() != null ? false : pullOut.isValid());

		date.setToolTip(date, DateFormatter.getInstance().getFormat(Utility.DMYHMAFormat).format(pullOut.getDate()));
		issuedBy.setToolTip(issuedBy, pullOut.getIssuedBy().getFirstPlusLastName());

		if (pullOut.getRemarks() != null)
			remarks.setToolTip(remarks, "-" + pullOut.getRemarks());

		Set<PullOutDetail> pullOutDetails = pullOut.getPullOutDetails();
		for (PullOutDetail sd : pullOutDetails) {
			rowPanel.add(new EditRowPanel(sd, productsPanel, Values.PULLOUT));
			productsPanel.add(rowPanel.get(rowPanel.size() - 1));
			alternateRows();

			productsPanel.setPreferredSize(new Dimension(330, productsPanel.getComponentCount() * ROW_HEIGHT));
			productsPanel.updateUI();
			productsPanel.revalidate();
		}

	}

}
