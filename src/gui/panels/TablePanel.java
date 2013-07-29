package gui.panels;

import gui.renderer.MyTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import util.SBTable;
import util.Values;
import util.soy.SoyPanel;

public class TablePanel extends SoyPanel {

	public TablePanel(Object[][] data, String[] header, List objects, String tableName) {
		this.data = data;
		this.header = header;
		init(objects, tableName);
		addComponents();
		
		Values.tablePanel = this;
	}

	private void init(List objects, String tableName) {
		setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder());
		setLayout(new BorderLayout());
		
		soyTable = new SBTable(data, header, objects, tableName);
	}

	private void addComponents() {

		pane = new JScrollPane(soyTable);
		pane.setBorder(BorderFactory.createEmptyBorder());

		add(pane, BorderLayout.CENTER);
		// add(new EditPanel(), BorderLayout.PAGE_START);
	}

	public void newFilter() {
		RowFilter<MyTableModel, Object> rf = null;
		// If current expression doesn't parse, don't update.
		try {

			rf = RowFilter.regexFilter("(?i)"
					+ TableUtilPanel.searchField.getText());
			// rf = RowFilter.regexFilter(TableUtilPanel.searchField.getText());

		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		soyTable.getSorter().setRowFilter(rf);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		gradient = new GradientPaint(0, 0, new Color(30, 30, 30), 0,
				getHeight(), new Color(5, 5, 5));
		g2.setPaint(gradient);
		g2.fill(g.getClipBounds());

		g2.setPaint(new Color(210, 210, 210));
		g2.fillRect(0, 0, g.getClipBounds().width, 1);

		paintChildren(g2);

		g2.dispose();
		g.dispose();
	}	

	public SBTable getSoyTable() {
		return soyTable;
	}

	public void setSoyTable(SBTable soyTable) {
		this.soyTable = soyTable;
	}


	private String[] header;
	private Object[][] data;
	private JScrollPane pane;

	private SBTable soyTable;

}