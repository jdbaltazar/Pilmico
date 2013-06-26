package core.persist;

import java.util.List;

import common.entity.pullout.PullOut;
import common.manager.PullOutManager;

public class PullOutPersistor extends Persistor implements PullOutManager {

	@Override
	public void addPullOut(PullOut pullOut) throws Exception {
		add(pullOut);
	}

	@Override
	public PullOut getPullOut(int id) throws Exception {
		return (PullOut) get(PullOut.class, id);
	}

	@Override
	public List<PullOut> getPullOuts() throws Exception {
		return getAll(PullOut.class);
	}

	@Override
	public void updatePullOut(PullOut pullOut) throws Exception {
		update(pullOut);
	}

	@Override
	public void deletePullOut(PullOut pullOut) throws Exception {
		remove(pullOut);
	}

}
