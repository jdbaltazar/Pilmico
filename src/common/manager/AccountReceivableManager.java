package common.manager;

import java.util.Date;
import java.util.List;

import common.entity.accountreceivable.ARPayment;
import common.entity.accountreceivable.AccountReceivable;

public interface AccountReceivableManager {

	// account receivables

	public void addAccountReceivable(AccountReceivable accountReceivable) throws Exception;

	public AccountReceivable getAccountReceivable(int id) throws Exception;

	public List<AccountReceivable> getAllAccountReceivables() throws Exception;

	public List<AccountReceivable> getValidAccountReceivables() throws Exception;

	public List<AccountReceivable> getInvalidAccountReceivables() throws Exception;

	public List<AccountReceivable> getPendingAccountReceivables() throws Exception;

	public List<AccountReceivable> getAllAccountReceivablesOn(Date date) throws Exception;

	public List<AccountReceivable> getValidAccountReceivablesOn(Date date) throws Exception;

	public List<AccountReceivable> getInvalidAccountReceivablesOn(Date date) throws Exception;

	public List<AccountReceivable> getPendingAccountReceivablesOn(Date date) throws Exception;

	// excludes the start and end dates
	public List<AccountReceivable> getPendingAccountReceivablesBetween(Date startDate, Date endDate) throws Exception;
	
	public List<AccountReceivable> getPendingAccountReceivablesBefore(Date date) throws Exception;

	public void updateAccountReceivable(AccountReceivable accountReceivable) throws Exception;

	public void deleteAccountReceivable(AccountReceivable accountReceivable) throws Exception;

	public void deleteAccountReceivable(int accountReceivableId) throws Exception;

	public List<Date> getDatesOfPendingAccountReceivables() throws Exception;

	// account receivable payments

	public void addARPayment(ARPayment arPayment) throws Exception;

	public ARPayment getARPayment(int id) throws Exception;

	public List<ARPayment> getAllARPayments() throws Exception;

	public List<ARPayment> getValidARPayments() throws Exception;

	public List<ARPayment> getInvalidARPayments() throws Exception;

	public List<ARPayment> getPendingARPayments() throws Exception;

	public List<ARPayment> getAllARPaymentsOn(Date date) throws Exception;

	public List<ARPayment> getValidARPaymentsOn(Date date) throws Exception;

	public List<ARPayment> getInvalidARPaymentsOn(Date date) throws Exception;

	public List<ARPayment> getPendingARPaymentsOn(Date date) throws Exception;

	// excludes the start and end dates
	public List<ARPayment> getPendingARPaymentsBetween(Date startDate, Date endDate) throws Exception;

	public List<ARPayment> getPendingARPaymentsBefore(Date date) throws Exception;

	public void updateARPayment(ARPayment arPayment) throws Exception;

	public void deleteARPayment(ARPayment arPayment) throws Exception;

	public void deleteARPayment(int arPaymentId) throws Exception;

	public List<Date> getDatesOfPendingARPayments() throws Exception;
}
