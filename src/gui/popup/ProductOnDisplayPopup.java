package gui.popup;

import gui.forms.util.RowPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import common.entity.product.Product;
import common.manager.Manager;

import util.ErrorLabel;
import util.SBButton;
import util.SimplePanel;
import util.TableHeaderLabel;
import util.Tables;
import util.Values;
import util.soy.SoyButton;

public class ProductOnDisplayPopup extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6042444877917291223L;
	private int WIDTH = 440, HEIGHT = 260;
	private JPanel panel;
	private JPanel onDisplayPanel;
	private JScrollPane productsPane;
	private SoyButton update;

	private SBButton close;
	private final int ROW_WIDTH = 392, ROW_HEIGHT = 30, LABEL_HEIGHT = 25, LABEL_Y = 47, PRODUCTS_PANE_Y = LABEL_Y + LABEL_HEIGHT;

	private TableHeaderLabel quantityKGLabel, quantitySACKlabel, productLabel, deleteLabel;

	private ArrayList<RowPanel> rowPanel = new ArrayList<RowPanel>();

	private ImageIcon icon;

	private List<Product> products;

	private ErrorLabel error;
	private String msg;

	private boolean done = false;

	public ProductOnDisplayPopup() {
		init();
		addComponents();
	}

	private void init() {
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		setBackground(new Color(0, 0, 0, 0));
	}

	private void addComponents() {

		error = new ErrorLabel();

		icon = new ImageIcon("images/util.png");
		close = new SBButton("close.png", "close.png", "Close", true);
		close.setBounds(408, 10, 24, 24);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// rowPanel.clear();
				// onDisplayPanel.removeAll();
				// onDisplayPanel.updateUI();
				// onDisplayPanel.revalidate();
				Values.mainFrame.dimScreen(false);
				Values.tableUtilPanel.nullifyThread();
//				setVisible(false);
				dispose();
			}
		});

		update = new SoyButton("Update");
		update.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent m) {

				if (isValidated()) {
					for (RowPanel rp : rowPanel) {
						try {
							Product p = rp.getOnDisplayProduct();
							p.setQuantity(Double.parseDouble(rp.getOnDisplayInSack()), Double.parseDouble(rp.getOnDisplayInKilo()));
							Manager.getInstance().getProductManager().updateProduct(p);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					Values.tableUtilPanel.nullifyThread();
					dispose();
					new SuccessPopup("Update").setVisible(true);
					Values.mainFrame.dimScreen(false);
					Values.centerPanel.changeTable(Values.PRODUCTS);
				} else
					error.setToolTip(msg);
			}
		});
		update.setBounds(185, 212, 80, 30);

		quantityKGLabel = new TableHeaderLabel("Qtty (kg)");
		productLabel = new TableHeaderLabel("Products");
		quantitySACKlabel = new TableHeaderLabel("Qtty (sack)");
		deleteLabel = new TableHeaderLabel(icon);

		// quantitySACKlabel.setBounds(23, LABEL_Y, 77, LABEL_HEIGHT);
		// quantityKGLabel.setBounds(100, LABEL_Y, 77, LABEL_HEIGHT);
		// productLabel.setBounds(177, LABEL_Y, 197, LABEL_HEIGHT);

		productLabel.setBounds(23, LABEL_Y, 197, LABEL_HEIGHT);
		quantitySACKlabel.setBounds(220, LABEL_Y, 90, LABEL_HEIGHT);
		quantityKGLabel.setBounds(310, LABEL_Y, 90, LABEL_HEIGHT);

		deleteLabel.setBounds(374, LABEL_Y, 42, LABEL_HEIGHT);

		onDisplayPanel = new JPanel();
		onDisplayPanel.setLayout(null);
		onDisplayPanel.setOpaque(false);

		panel = new SimplePanel("Update Products On Display");
		panel.setOpaque(false);

		productsPane = new JScrollPane(onDisplayPanel);
		productsPane.setOpaque(false);
		productsPane.getViewport().setOpaque(false);
		productsPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		productsPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		productsPane.getVerticalScrollBar().setUnitIncrement(10);
		// productsPane.setBorder(BorderFactory.createEmptyBorder());

		productsPane.setBounds(24, PRODUCTS_PANE_Y, ROW_WIDTH - 1, 120);

		error.setBounds(productsPane.getX(), productsPane.getY() + productsPane.getHeight(), productsPane.getWidth(), 22);

		panel.add(error);
		panel.add(quantityKGLabel);
		panel.add(quantitySACKlabel);
		panel.add(productLabel);
		panel.add(productsPane);
		// panel.add(deleteLabel);
		panel.add(update);

		add(close);
		add(panel);
	}

	private boolean isValidated() {

		for (int i = 0; i < rowPanel.size(); i++) {

			/*if (Product.totalKilos(rowPanel.get(i).getOnDisplayInSack(), rowPanel.get(i).getOnDisplayInKilo(), rowPanel.get(i).getOnDisplayProduct()
					.getKilosPerSack()) > rowPanel.get(i).getOnDisplayProduct().getTotalQuantityInKilo()) {
				msg = "quantity on display cannot be greater than total quantity kilos in row " + (i + 1);
				return false;
			}*/
			
			if(rowPanel.get(i).getOnDisplayInSack().equals("") || rowPanel.get(i).getOnDisplayInKilo().equals("")){
				msg = "Blank field/s found in row " + (i + 1);
				
				return false;
			}

		}

		return true;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void fillTable() {

		rowPanel.clear();
		onDisplayPanel.removeAll();

		for (Product p : products) {
			rowPanel.add(new RowPanel(p, onDisplayPanel, Tables.PRODUCTS));
			onDisplayPanel.add(rowPanel.get(rowPanel.size() - 1));
			onDisplayPanel.setPreferredSize(new Dimension(330, onDisplayPanel.getComponentCount() * ROW_HEIGHT));
		}

		/*
		 * for (int i = 0; i < 28; i ++) { rowPanel.add(new RowPanel(null,
		 * onDisplayPanel, Tables.PRODUCTS));
		 * onDisplayPanel.add(rowPanel.get(rowPanel.size() - 1));
		 * onDisplayPanel.setPreferredSize(new Dimension(330,
		 * onDisplayPanel.getComponentCount() * ROW_HEIGHT)); }
		 */

		done = true;

		onDisplayPanel.updateUI();
		onDisplayPanel.revalidate();
	}

	public boolean isDone() {
		return done;
	}

}
