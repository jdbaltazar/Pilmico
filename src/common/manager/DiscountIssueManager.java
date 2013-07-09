package common.manager;

import java.util.List;

import common.entity.discountissue.DiscountIssue;

public interface DiscountIssueManager {

	public void addDiscountIssue(DiscountIssue discountIssue) throws Exception;

	public DiscountIssue getDiscountIssue(int id) throws Exception;

	public List<DiscountIssue> getAllDiscountIssues() throws Exception;

	public List<DiscountIssue> getValidDiscountIssues() throws Exception;

	public List<DiscountIssue> getInvalidDiscountIssues() throws Exception;

	public void updateDiscountIssue(DiscountIssue discountIssue) throws Exception;

	public void deleteDiscountIssue(DiscountIssue discountIssue) throws Exception;

}