package gui.forms.util;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import util.SBTable;
import util.Tables;
import util.Values;

public class HistoryTable extends JScrollPane {

	private SBTable sbTable;
	private String[] header;
	private Object[][] entries;

	public HistoryTable(String[] header, Object[][] entries) {
		this.header = header;
		this.entries = entries;
		
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
		if(Values.tableUtilPanel.getLabel().equals(Tables.PRODUCTS))
			setPreferredSize(new Dimension(390, 150));
		else
			setPreferredSize(new Dimension(340, 150));
		
		setBorder(BorderFactory.createEmptyBorder());
		setOpaque(false);

		sbTable = new SBTable(entries, header);

		sbTable.getColumnModel().getColumn(0).setMinWidth(170);
		sbTable.getColumnModel().getColumn(0).setMaxWidth(170);
		
		setViewportView(sbTable);

		getViewport().setOpaque(false);
	}

}
