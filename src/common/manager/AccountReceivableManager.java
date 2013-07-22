package common.manager;

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

	public void updateAccountReceivable(AccountReceivable accountReceivable) throws Exception;

	public void deleteAccountReceivable(AccountReceivable accountReceivable) throws Exception;

	public void deleteAccountReceivable(int accountReceivableId) throws Exception;

	// account receivable payments

	public void addARPayment(ARPayment arPayment) throws Exception;

	public ARPayment getARPayment(int id) throws Exception;

	public List<ARPayment> getAllARPayments() throws Exception;

	public List<ARPayment> getValidARPayments() throws Exception;

	public List<ARPayment> getInvalidARPayments() throws Exception;

	public List<ARPayment> getPendingARPayments() throws Exception;

	public void updateARPayment(ARPayment arPayment) throws Exception;

	public void deleteARPayment(ARPayment arPayment) throws Exception;

	public void deleteARPayment(int arPaymentId) throws Exception;
}
