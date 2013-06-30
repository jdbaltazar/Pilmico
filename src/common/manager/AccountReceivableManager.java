package common.manager;

import java.util.List;

import common.entity.accountreceivable.ARPayment;
import common.entity.accountreceivable.AccountReceivable;

public interface AccountReceivableManager {

	// account receivables

	public void addAccountReceivable(AccountReceivable accountReceivable) throws Exception;

	public AccountReceivable getAccountReceivable(int id) throws Exception;

	public List<AccountReceivable> getAccountReceivables() throws Exception;

	public void updateAccountReceivable(AccountReceivable accountReceivable) throws Exception;

	public void deleteAccountReceivable(AccountReceivable accountReceivable) throws Exception;

	public void deleteAccountReceivable(int accountReceivableId) throws Exception;

	// account receivable payments

	public void addARPayment(ARPayment arPayment) throws Exception;

	public ARPayment getARPayment(int id) throws Exception;

	public List<ARPayment> getARPayments() throws Exception;

	public void updateARPayment(ARPayment arPayment) throws Exception;

	public void deleteARPayment(ARPayment arPayment) throws Exception;

	public void deleteARPayment(int arPaymentId) throws Exception;
}
