package util;

import gui.renderer.MyTableModel;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

public class ColumnSizer {
	
	public static void resizeColumns(JTable table, int form){
		
//		System.out.println("form: "+form);

		switch(form){
		
		case Values.PRODUCTS:
			table.getColumnModel().getColumn(0).setMinWidth(70);
			table.getColumnModel().getColumn(0).setMaxWidth(70);
			
			table.getColumnModel().getColumn(1).setMinWidth(160);
			table.getColumnModel().getColumn(1).setMaxWidth(160);
			break;
			
		case Values.SALES:
			
			table.getColumnModel().getColumn(0).setMinWidth(90);
			table.getColumnModel().getColumn(0).setMaxWidth(90);
			
			table.getColumnModel().getColumn(1).setMinWidth(90);
			table.getColumnModel().getColumn(1).setMaxWidth(90);
			
			table.getColumnModel().getColumn(3).setMinWidth(150);
			table.getColumnModel().getColumn(3).setMaxWidth(150);
			
			table.getColumnModel().getColumn(5).setMinWidth(90);
			table.getColumnModel().getColumn(5).setMaxWidth(90);
			
			break;
			
		case Values.PULLOUT:
			
			break;

		case Values.ACCOUNT_RECEIVABLES:
			
			break;

		case Values.AR_PAYMENTS:
			break;

		case Values.DELIVERY:
			break;

		case Values.SUPPLIERS:
			break;

		case Values.CASH_ADVANCE:
			break;

		case Values.SALARY:
			break;

		case Values.CUSTOMERS:
			break;

		case Values.EMPLOYEES:
			break;

		case Values.ACCOUNTS:
			break;

		case Values.EXPENSES:
			break;

		case Values.DISCOUNTS:
			break;

		case Values.DEPOSITS:
			break;

		case Values.BANK:
			break;

		case Values.LOGS:
			
			table.getColumnModel().getColumn(0).setMinWidth(200);
			table.getColumnModel().getColumn(0).setMaxWidth(200);
			
			break;

		case Values.CA_PAYMENTS:
			break;

		case Values.INVENTORY_SHEET:
			break;
			
		default:
			break;
		
		}
	}

}
