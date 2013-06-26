package core.persist;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import common.entity.log.Log;
import common.entity.log.LogType;
import common.manager.LogManager;

public class LogPersistor extends Persistor implements LogManager {

	@Override
	public void addLog(Log log) throws Exception {
		add(log);
	}

	@Override
	public void updateLog(Log log) throws Exception {
		update(log);
	}

	@Override
	public Log getLog(int logId) throws Exception {
		return (Log) get(Log.class, logId);
	}

	@Override
	public void deleteLog(Log log) throws Exception {
		remove(log);
	}

	@Override
	public void deleteLog(int logId) throws Exception {
		remove(getLog(logId));
	}

	@Override
	public List<Log> getAllLogs() throws Exception {
		return getAll(Log.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getLogsRecentFirst() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Log.class);
		List<Log> logs = new ArrayList<Log>();
		try {
			logs = criteria.addOrder(Order.desc("id")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return logs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getLogsWithinThisWeekRecentFirst() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Log.class);
		List<Log> logs = new ArrayList<Log>();
		// DateFormat sdf = new SimpleDateFormat("d MMM yyyy hh:mm:ss a");
		// System.out.println("1st day of week: " +
		// sdf.format(getFirstDayOfCurrentWeek()));
		try {
			logs = criteria.add(Restrictions.ge("date", getFirstDayOfCurrentWeek())).addOrder(Order.desc("id")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return logs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Log> getLogsWithinThisMonthRecentFirst() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Log.class);
		List<Log> logs = new ArrayList<Log>();
		// DateFormat sdf = new SimpleDateFormat("d MMM yyyy hh:mm:ss a");
		// System.out.println("1st day of month: " +
		// sdf.format(getFirstDayOfCurrentMonth()));
		try {
			logs = criteria.add(Restrictions.ge("date", getFirstDayOfCurrentMonth())).addOrder(Order.desc("id")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return logs;
	}

	private Date getFirstDayOfCurrentMonth() {
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	private Date getFirstDayOfCurrentWeek() {
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	@Override
	public void addLogType(String name) throws Exception {
		add(new LogType(name));
	}

	@Override
	public void updateLogType(LogType logType) throws Exception {
		update(logType);
	}

	@Override
	public List<LogType> getLogTypes() throws Exception {
		return getAll(LogType.class);
	}

	@Override
	public LogType getLogType(String name) throws Exception {
		return (LogType) get(LogType.class, name);
	}

}
