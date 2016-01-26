/**
 * Facade for log framework
 * <p>
 * now in application use log4j2
 */

package ua.george_nika.simulation.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.joda.time.DateTime;
import ua.george_nika.simulation.util.error.LoggerPoolWrongName;
import ua.george_nika.simulation.util.error.NoEmptyLoggerInPool;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")

public class AppLog {

    private static String CLASS_NAME = AppLog.class.getSimpleName();

    public static final String CONTROLLER = "Controller";
    public static final String MODEL = "Model";
    public static final String DAO = "Dao";
    public static final String SERVICE = "Service";
    public static final String UTIL = "Util";

    private static Map<String, Boolean> loggerPool = new HashMap<>();
    private static Map<String, String> loggerFileName = new HashMap<>();
    private static Map<String, String> currentLoggerFileName = new HashMap<>();

    private AppLog() {
    }

    public static void initApplicationLog() {
        // todo in future. It should convert log to db

        loggerFileName.put("LoggerPool1", "loggerPool1FileName");
        loggerFileName.put("LoggerPool2", "loggerPool2FileName");
        loggerFileName.put("LoggerPool3", "loggerPool3FileName");
        loggerFileName.put("LoggerPool4", "loggerPool4FileName");
        loggerFileName.put("LoggerPool5", "loggerPool5FileName");
        loggerFileName.put("LoggerPool6", "loggerPool6FileName");
        loggerFileName.put("LoggerPool7", "loggerPool7FileName");
        loggerFileName.put("LoggerPool8", "loggerPool8FileName");
        loggerFileName.put("LoggerPool9", "loggerPool9FileName");

        for (String loopLogger : loggerFileName.keySet()) {
            loggerPool.put(loopLogger, false);
            System.setProperty(loggerFileName.get(loopLogger),
                    AppConst.getPathExperimentLog() + AppConst.EXPERIMENT_EMPTY_LOG);
        }

        System.setProperty(AppConst.APPLICATION_LOG_PATH, AppConst.getPathApplicationLog());
        System.setProperty(AppConst.APPLICATION_ARCHIVE_LOG_PATH, AppConst.getPathArchiveApplicationLog());
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        ctx.reconfigure();

    }

    public static void userInfo(String logger, HttpSession session, String message) {
        StringBuilder userIdString = new StringBuilder();
        userIdString.append("id - ").append(session.getId());

        Object login = session.getAttribute(AppConst.LOGIN_IN_SESSION);
        if ((login != null) && ((boolean) login)) {
            userIdString.append(" login - ").append(session.getAttribute(AppConst.USER_IN_SESSION));
        }
        userIdString.append(" * ");

        Logger log = LogManager.getLogger(logger);
        log.info(userIdString.toString() + message);
    }

    public static void info(String logger, String message) {
        Logger log = LogManager.getLogger(logger);
        log.info(message+"\n");
    }

    public static void info(String logger, String className, String message) {
        Logger log = LogManager.getLogger(logger);
        log.info("[class-" + className + "] * " + message);
    }

    public static void error(String logger, String className, String message) {
        Logger log = LogManager.getLogger(logger);
        log.error("[class-" + className + "] * " + message);
    }

    public static void error(String logger, String className, String message, Throwable ex) {
        Logger log = LogManager.getLogger(logger);
        log.error("[class-" + className + "] * " + message, ex);
    }

    public static void warn(String logger, String className, String message) {
        Logger log = LogManager.getLogger(logger);
        log.warn("[class-" + className + "] * " + message);
    }

    public static synchronized String getEmptyLoggerName(String addInfo) {

        String loggerName = "";
        for (String loopLogger : loggerPool.keySet()) {
            if (!loggerPool.get(loopLogger)) {
                loggerName = loopLogger;
                loggerPool.put(loggerName, true);
                break;
            }
        }
        if (loggerName.isEmpty()) {
            throw new NoEmptyLoggerInPool(UTIL, CLASS_NAME, "No empty Logger in Pool", new RuntimeException());
        }
        currentLoggerFileName.put(loggerName, getFileName(addInfo));
        System.setProperty(loggerFileName.get(loggerName),
                AppConst.getPathExperimentLog() + currentLoggerFileName.get(loggerName));
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        ctx.reconfigure();
        return loggerName;
    }

    public static synchronized String getLoggerFileName(String loggerName) {
        if (loggerPool.containsKey(loggerName)) {
            return currentLoggerFileName.get(loggerName);
        } else {
            throw new LoggerPoolWrongName(UTIL, CLASS_NAME, "Wrong logger pool name "
                    + loggerName, new RuntimeException());
        }
    }

    private static String getFileName(String addInfo) {
        final String DATE_TIME_FORMAT = "yyyy_MM_dd__HH_mm_ss";
        String dateTimeInfo = new DateTime().toString(DATE_TIME_FORMAT);
        return "Log " + addInfo + " " + dateTimeInfo + ".txt";
    }

    public static synchronized void freeLoggerName(String loggerName) {
        if (loggerPool.containsKey(loggerName)) {
            loggerPool.put(loggerName, false);
        } else {
            throw new LoggerPoolWrongName(UTIL, CLASS_NAME, "Wrong logger pool name "
                    + loggerName, new RuntimeException());
        }
        currentLoggerFileName.put(loggerName, AppConst.EXPERIMENT_EMPTY_LOG);
        System.setProperty(loggerFileName.get(loggerName), currentLoggerFileName.get(loggerName));
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        ctx.reconfigure();
    }
}
