package common.entity.inventorysheet;

import java.util.Set;

import common.entity.accountreceivable.AccountReceivable;
import common.entity.delivery.Delivery;
import common.entity.pullout.PullOut;
import common.entity.sales.Sales;

public interface InventorySheetManager {

	// beginning inventories

	public double getBeginningInventoryInSackForProduct(int productId);

	public double getBeginningInventoryInKiloForProduct(int productId);

	public double getTotalBeginningInventoryInSack();

	public double getTotalBeginningInventoryInKilo();

	// on display

	public double getOnDisplayInSackForProduct(int productId);

	public double getOnDisplayInKiloForProduct(int productId);

	public double getTotalOnDisplayInSack();

	public double getTotalOnDisplayInKilo();

	// deliveries

	public double getDeliveriesInSackForProduct(int productId);

	public double getDeliveriesInKiloForProduct(int productId);

	public double getTotalDeliveriesInSack();

	public double getTotalDeliveriesInKilo();

	public double getOverallCostOfDeliveries();

	public Set<Delivery> getDeliveries();

	// pullouts

	public double getPulloutsInSackForProduct(int productId);

	public double getPulloutsInKiloForProduct(int productId);

	public double getTotalPulloutsInSack();

	public double getTotalPulloutsInKilo();

	public double getOverallCostOfPullouts();

	public Set<PullOut> getPullOuts();

	// ending inventory

	public double getEndingInventoryInSackForProduct(int productId);

	public double getEndingInventoryInKiloForProduct(int productId);

	public double getTotalEndingInventoryInSack();

	public double getTotalEndingInventoryInKilo();

	// offtakes

	public double getOfftakesInSackForProduct(int productId);

	public double getOfftakesInKiloForProduct(int productId);

	public double getTotalOfftakesInSack();

	public double getTotalOfftakesInKilo();

	// prices

	public double getPricePerSackForProduct(int productId);

	public double getPricePerKiloForProduct(int productId);

	public double getTotalPricesInSack();

	public double getTotalPricesInKilo();

	// combined sales (cash, check & account receivables)

	public double getCombinedSalesInSackForProduct(int productId);

	public double getCombinedSalesInKiloForProduct(int productId);

	public double getCombinedSalesInSack();

	public double getCombinedSalesInKilo();

	public double getOverallCombinedSales();

	public Set<Sales> getSales();

	// cash/check sales

	public double getOverallCashAndCheckSales();

	// account receivables

	public double getOverallAccountReceivables();

	public Set<AccountReceivable> getAccountReceivables();

	// account receivables payments

	public double getOverallAccountReceivablesPayments();

	// cash advances

	public double getOverallCashAdvances();

	// cash advances payments

	public double getOverallCashAdvancesPayments();

	// personal expenses

	public double getOverallPersonalExpenses();

	// store expenses

	public double getOverallStoreExpenses();

	// personal and store

	public double getOverallPersonalAndStoreExpenses();

	// salary releases

	public double getOverallSalaryReleases();

	// overall discounts

	public double getOverallDiscounts();

	// deposits

	public double getOverallDeposits();

	// breakdown

	public Breakdown getBreakdown();

	// previous coh

	public double getPreviousCashOnHand();

	// assets

	public double getTotalAssets();

	// liabilities

	public double getTotalLiabilities();

	// acoh

	public double getActualCashOnHand();

	// acc

	public double getActualCashCount();

	// over amount

	public double getOverAmount(); 

	// short amount

	public double getShortAmount();
}
