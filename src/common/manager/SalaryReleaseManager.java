package common.manager;

import java.util.Date;
import java.util.List;

import common.entity.salary.Fee;
import common.entity.salary.SalaryRelease;

public interface SalaryReleaseManager {

	public void addSalaryRelease(SalaryRelease salaryRelease) throws Exception;

	public SalaryRelease getSalaryRelease(int id) throws Exception;

	public List<SalaryRelease> getAllSalaryReleases() throws Exception;

	public List<SalaryRelease> getValidSalaryReleases() throws Exception;

	public List<SalaryRelease> getInvalidSalaryReleases() throws Exception;

	public List<SalaryRelease> getPendingSalaryReleases() throws Exception;

	public List<SalaryRelease> getAllSalaryReleasesOn(Date date) throws Exception;

	public List<SalaryRelease> getValidSalaryReleasesOn(Date date) throws Exception;

	public List<SalaryRelease> getInvalidSalaryReleasesOn(Date date) throws Exception;

	public List<SalaryRelease> getPendingSalaryReleasesOn(Date date) throws Exception;

	public void updateSalaryRelease(SalaryRelease salaryRelease) throws Exception;

	public void deleteSalaryRelease(SalaryRelease salaryRelease) throws Exception;

	// fee

	public void addFees(Fee fee) throws Exception;

	public Fee getFee(int id) throws Exception;

	public Fee searchFee(String name) throws Exception;

	public List<Fee> getFees() throws Exception;

	public void updateFees(Fee fee) throws Exception;

	public void deleteFee(Fee fee) throws Exception;

	public void deleteFee(int id) throws Exception;

	public double getMostRecentAmountForFee(int feeId) throws Exception;

}
