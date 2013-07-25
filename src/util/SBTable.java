package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import gui.popup.NotesPopup;
import gui.renderer.MyCellRenderer;
import gui.renderer.MyTableModel;
import gui.renderer.RowHighlightRenderer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class SBTable extends JTable implements MouseListener {

	final RowHighlightRenderer renderer = new RowHighlightRenderer();
	GradientTableHeader gradientTableHeader = new GradientTableHeader();
	private TableCellListener tcl;
	private TableRowSorter<MyTableModel> sorter;
	private MyTableModel model;
	private List objects;
	private Object[][] data;
	private String[] header;

	public SBTable(Object[][] data, String[] header, List objects) {
		// TODO Auto-generated constructor stub

		this.data = data;
		this.header = header;
		this.objects = objects;
		// gradientTableHeader.setColumnModel(this.getColumnModel());
		// setTableHeader(gradientTableHeader);

		init();
		addMouseListener(this);
	}
	
	public SBTable(Object[][] data, String[] header) {
		// TODO Auto-generated constructor stub

		this.data = data;
		this.header = header;

		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		getTableHeader().setDefaultRenderer(new MyCellRenderer());

		model = new MyTableModel(data, header);

		setModel(model);

		getTableHeader().setReorderingAllowed(false);
		setFocusable(false);
		setShowGrid(false);
		setOpaque(false);
		setFont(new Font("Lucida Sans", Font.PLAIN, 12));
		// removeColumn(getColumnModel().getColumn(getColumnCount()-1));

		sorter = new TableRowSorter<MyTableModel>(model);
		setRowSorter(sorter);

		setDefaultRenderer(Object.class, renderer);
	}

	public List getObjects() {
		return objects;
	}

	public TableRowSorter<MyTableModel> getSorter() {
		return sorter;
	}

	class GradientTableHeader extends JTableHeader {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getClickCount() == 2 && !Values.tableUtilPanel.getLabel().equals(Tables.LOGS)) {

			/*
			 * if (!(Manager.loggedInAccount != null &&
			 * !Manager.loggedInAccount.getAccountType().getName()
			 * .equals(AccountType.manager) &&
			 * Values.tableUtilPanel.getLabel().equals("ACCOUNTS"))){
			 */
			JTable target = (JTable) arg0.getSource();
			int row = target.getSelectedRow();
			row = target.convertRowIndexToModel(row);

			// do some action

			// System.out.println(getValueAt(row, column));
			// new EditItemPopup().setVisible(true);
			Values.editPanel.setHide(false);
			Values.editPanel.startAnimation();
			// Values.editPanel.showComponent(objects.get(row));
			Values.editPanel.showComponent(objects.get(row));
			// Values.editPanel.showComponent(null);
			// }
		}

		if (arg0.getClickCount() == 1 && (Values.tableUtilPanel.getLabel().equals(Tables.LOGS))) {
			JTable target = (JTable) arg0.getSource();
			int row = target.getSelectedRow();
			int column = target.getSelectedColumn();

			// setToolTipText(getValueAt(convertRowIndexToModel(row),
			// column).toString());

			JOptionPane.showMessageDialog(null, getValueAt(convertRowIndexToModel(row), 1).toString());

		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
