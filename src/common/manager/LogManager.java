package common.manager;

import java.util.List;

import common.entity.log.Log;
import common.entity.log.LogType;

public interface LogManager {

	void addLog(Log log) throws Exception;

	void updateLog(Log log) throws Exception;

	Log getLog(int logId) throws Exception;

	void deleteLog(Log log) throws Exception;

	void deleteLog(int logId) throws Exception;

	List<Log> getAllLogs() throws Exception;

	List<Log> getLogsRecentFirst() throws Exception;

	List<Log> getLogsWithinThisWeekRecentFirst() throws Exception;

	List<Log> getLogsWithinThisMonthRecentFirst() throws Exception;

	void addLogType(String name) throws Exception;

	void updateLogType(LogType logType) throws Exception;

	List<LogType> getLogTypes() throws Exception;

	LogType getLogType(String name) throws Exception;

}
