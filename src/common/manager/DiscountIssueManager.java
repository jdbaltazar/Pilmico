package common.manager;

import java.util.Date;
import java.util.List;

import common.entity.discountissue.DiscountIssue;

public interface DiscountIssueManager {

	public void addDiscountIssue(DiscountIssue discountIssue) throws Exception;

	public DiscountIssue getDiscountIssue(int id) throws Exception;

	public List<DiscountIssue> getAllDiscountIssues() throws Exception;

	public List<DiscountIssue> getValidDiscountIssues() throws Exception;

	public List<DiscountIssue> getInvalidDiscountIssues() throws Exception;

	public List<DiscountIssue> getPendingDiscountIssues() throws Exception;

	public List<DiscountIssue> getAllDiscountIssuesOn(Date date) throws Exception;

	public List<DiscountIssue> getValidDiscountIssuesOn(Date date) throws Exception;

	public List<DiscountIssue> getInvalidDiscountIssuesOn(Date date) throws Exception;

	public List<DiscountIssue> getPendingDiscountIssuesOn(Date date) throws Exception;

	// excludes the start and end dates
	public List<DiscountIssue> getPendingDiscountIssuesBetween(Date startDate, Date endDate) throws Exception;

	public void updateDiscountIssue(DiscountIssue discountIssue) throws Exception;

	public void deleteDiscountIssue(DiscountIssue discountIssue) throws Exception;

	public List<Date> getDatesOfPendingDiscountIssues() throws Exception;

}