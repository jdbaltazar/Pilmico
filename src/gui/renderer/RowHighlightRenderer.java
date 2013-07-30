package gui.renderer;

import java.awt.Color;
import java.awt.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import common.entity.accountreceivable.ARPayment;
import common.entity.accountreceivable.AccountReceivable;
import common.entity.cashadvance.CAPayment;
import common.entity.cashadvance.CashAdvance;
import common.entity.dailyexpenses.DailyExpenses;
import common.entity.dailyexpenses.Expense;
import common.entity.delivery.Delivery;
import common.entity.deposit.Deposit;
import common.entity.discountissue.DiscountIssue;
import common.entity.pullout.PullOut;
import common.entity.salary.SalaryRelease;
import common.entity.sales.Sales;

import util.Tables;
import util.Values;

public class RowHighlightRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Set<Integer> highlightedRows = new HashSet<Integer>();
	Color selectedColor = new Color(0, 255, 255), backgroundColor1 = new Color(245, 245, 220), backgroundColor2 = new Color(220, 220, 220),
			invalidColor = Color.decode("#F9CCCC");//= new Color(255, 140, 140);

	private boolean valid = true;
//	private int alertOnQuantity, quantity, itemId;

	@SuppressWarnings("unchecked")
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		setHorizontalAlignment(SwingConstants.CENTER);

		if (row % 2 == 0)
			setBackground(backgroundColor1);
		else
			setBackground(backgroundColor2);

		if (isSelected) {
			setBackground(selectedColor);
			setForeground(Color.BLACK);
		}

		if(table.getName().equals(Tables.SALES)){
			List<Sales> sales = (List<Sales>) Values.tablePanel.getSoyTable().getObjects();
			Sales object = sales.get(table.convertRowIndexToModel(row));
			
			valid = object.isValid();
			
		}
		else if(table.getName().equals(Tables.EXPENSES)){
			List<DailyExpenses> expense = (List<DailyExpenses>) Values.tablePanel.getSoyTable().getObjects();
			DailyExpenses object = expense.get(table.convertRowIndexToModel(row));
			
			valid = object.isValid();
		}
		
		else if(table.getName().equals(Tables.SALARY)){
			List<SalaryRelease> sr = (List<SalaryRelease>) Values.tablePanel.getSoyTable().getObjects();
			SalaryRelease object = sr.get(table.convertRowIndexToModel(row));
			
			valid = object.isValid();
		}
		
		else if(table.getName().equals(Tables.DELIVERIES)){
			List<Delivery> delivery = (List<Delivery>) Values.tablePanel.getSoyTable().getObjects();
			Delivery object = delivery.get(table.convertRowIndexToModel(row));
			
			valid = object.isValid();
		}
		
		else if(table.getName().equals(Tables.PULLOUTS)){
			List<PullOut> po = (List<PullOut>) Values.tablePanel.getSoyTable().getObjects();
			PullOut object = po.get(table.convertRowIndexToModel(row));
			
			valid = object.isValid();
		}
		
		else if(table.getName().equals(Tables.ACCOUNT_RECEIVABLES)){
			List<AccountReceivable> ar = (List<AccountReceivable>) Values.tablePanel.getSoyTable().getObjects();
			AccountReceivable object = ar.get(table.convertRowIndexToModel(row));
			
			valid = object.isValid();
		}
		
		else if(table.getName().equals(Tables.AR_PAYMENTS)){
			List<ARPayment> arp = (List<ARPayment>) Values.tablePanel.getSoyTable().getObjects();
			ARPayment object = arp.get(table.convertRowIndexToModel(row));
			
			valid = object.isValid();
		}
		
		else if(table.getName().equals(Tables.CASH_ADVANCE)){
			List<CashAdvance> ca = (List<CashAdvance>) Values.tablePanel.getSoyTable().getObjects();
			CashAdvance object = ca.get(table.convertRowIndexToModel(row));
			
			valid = object.isValid();
		}
		
		else if(table.getName().equals(Tables.CA_PAYMENTS)){
			List<CAPayment> cap = (List<CAPayment>) Values.tablePanel.getSoyTable().getObjects();
			CAPayment object = cap.get(table.convertRowIndexToModel(row));
			
			valid = object.isValid();
		}
		
		else if(table.getName().equals(Tables.DISCOUNTS)){
			List<DiscountIssue> di = (List<DiscountIssue>) Values.tablePanel.getSoyTable().getObjects();
			DiscountIssue object = di.get(table.convertRowIndexToModel(row));
			
			valid = object.isValid();
		}
		
		else if(table.getName().equals(Tables.DEPOSITS)){
			List<Deposit> d = (List<Deposit>) Values.tablePanel.getSoyTable().getObjects();
			Deposit object = d.get(table.convertRowIndexToModel(row));
			
			valid = object.isValid();
		}
		
		if (!valid) {
			setBackground(invalidColor);
		}

		return this;
	}
}
