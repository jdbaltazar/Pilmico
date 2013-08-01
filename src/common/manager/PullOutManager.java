package common.manager;

import java.util.Date;
import java.util.List;

import common.entity.pullout.PullOut;

public interface PullOutManager {

	public void addPullOut(PullOut pullOut) throws Exception;

	public PullOut getPullOut(int id) throws Exception;

	public List<PullOut> getAllPullOuts() throws Exception;

	public List<PullOut> getValidPullOuts() throws Exception;

	public List<PullOut> getInvalidPullOuts() throws Exception;

	public List<PullOut> getPendingPullOuts() throws Exception;

	public List<PullOut> getAllPullOutsOn(Date date) throws Exception;

	public List<PullOut> getValidPullOutsOn(Date date) throws Exception;

	public List<PullOut> getInvalidPullOutsOn(Date date) throws Exception;

	public List<PullOut> getPendingPullOutsOn(Date date) throws Exception;

	public void updatePullOut(PullOut pullOut) throws Exception;

	public void deletePullOut(PullOut pullOut) throws Exception;

}
