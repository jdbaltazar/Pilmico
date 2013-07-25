package common.manager;

import java.util.List;

import common.entity.salary.SalaryRelease;

public interface SalaryReleaseManager {

	public void addSalaryRelease(SalaryRelease salaryRelease) throws Exception;

	public SalaryRelease getSalaryRelease(int id) throws Exception;

	public List<SalaryRelease> getAllSalaryReleases() throws Exception;

	public List<SalaryRelease> getValidSalaryReleases() throws Exception;

	public List<SalaryRelease> getInvalidSalaryReleases() throws Exception;

	public List<SalaryRelease> getPendingSalaryReleases() throws Exception;

	public void updateSalaryRelease(SalaryRelease salaryRelease) throws Exception;

	public void deleteSalaryRelease(SalaryRelease salaryRelease) throws Exception;


	
	// add portion for fees. include search
}
