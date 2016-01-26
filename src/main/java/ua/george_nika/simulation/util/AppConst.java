/**
 * Here placed all global application constants
 */
package ua.george_nika.simulation.util;

import org.springframework.stereotype.Component;

@SuppressWarnings("unused")

@Component
public class AppConst {

    public static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm";
    public static final String APPLICATION_LOG_PATH = "SimulationLogPath";
    public static final String APPLICATION_ARCHIVE_LOG_PATH = "SimulationArchiveLogPath";
    public static final String EXPERIMENT_EMPTY_LOG = "empty.log";

    public static final String USER_IN_SESSION = "user";
    public static final String LOGIN_IN_SESSION = "isUserLogin";

    private static String pathApplicationLog;
    private static String pathArchiveApplicationLog;
    private static String pathExperimentLog;
    private static String pathExperimentXml;

    public static String getPathApplicationLog() {
        return pathApplicationLog;
    }

    public static String getPathArchiveApplicationLog() {
        return pathArchiveApplicationLog;
    }

    public static String getPathExperimentLog() {
        return pathExperimentLog;
    }

    public static String getPathExperimentXml() {
        return pathExperimentXml;
    }

    private static void setPathApplicationLog(String path) {
        // run from dispatcher-servlet.xml
        AppConst.pathApplicationLog = path.replace(" ", "").replace("\n", "");
    }

    private static void setPathExperimentLog(String path) {
        // run from dispatcher-servlet.xml
        AppConst.pathExperimentLog = path.replace(" ", "").replace("\n", "");
    }

    private static void setPathExperimentXml(String path) {
        // run from dispatcher-servlet.xml
        AppConst.pathExperimentXml = path.replace(" ", "").replace("\n", "");
    }

    public static void setPathArchiveApplicationLog(String path) {
        // run from dispatcher-servlet.xml
        AppConst.pathArchiveApplicationLog = path.replace(" ", "").replace("\n", "");
    }
}

