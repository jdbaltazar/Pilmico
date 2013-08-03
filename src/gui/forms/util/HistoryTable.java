package gui.forms.util;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
		
		
		setBorder(BorderFactory.createEmptyBorder());
		setOpaque(false);

		sbTable = new SBTable(entries, header);

		if(Values.tableUtilPanel.getLabel().equals(Tables.PRODUCTS)){
			sbTable.getColumnModel().getColumn(0).setMinWidth(170);
			sbTable.getColumnModel().getColumn(0).setMaxWidth(170);

			setPreferredSize(new Dimension(390, 150));
		}
		else if(Values.tableUtilPanel.getLabel().equals(Tables.EMPLOYEES)){
			sbTable.getColumnModel().getColumn(0).setMinWidth(150);
			sbTable.getColumnModel().getColumn(0).setMaxWidth(150);
			
			sbTable.getColumnModel().getColumn(1).setMaxWidth(160);
			sbTable.getColumnModel().getColumn(1).setMinWidth(160);

			setPreferredSize(new Dimension(430, 150));
		}
		else{
			sbTable.getColumnModel().getColumn(1).setMinWidth(160);
			sbTable.getColumnModel().getColumn(1).setMaxWidth(160);
			
			sbTable.getColumnModel().getColumn(0).setMaxWidth(60);
			sbTable.getColumnModel().getColumn(0).setMinWidth(60);

			setPreferredSize(new Dimension(340, 150));
		}
		setViewportView(sbTable);

		getViewport().setOpaque(false);
	}

}
