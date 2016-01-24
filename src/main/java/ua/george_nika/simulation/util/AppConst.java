/**
 * Here placed all global application constants
 */
package ua.george_nika.simulation.util;

import org.springframework.stereotype.Component;

@SuppressWarnings("unused")

@Component
public class AppConst {

    public static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm";

    public static final String USER_IN_SESSION = "user";
    public static final String LOGIN_IN_SESSION = "isUserLogin";

    private static String pathLog;
    private static String pathXml;


    public static String getPathLog() {
        return pathLog;
    }

    public static String getPathXml() {
        return pathXml;
    }


    private static void setPathLog(String path) {
        // run from dispatcher-servlet.xml
        AppConst.pathLog = path.replace(" ", "").replace("\n", "");
    }

    private static void setPathXml(String path) {
        // run from dispatcher-servlet.xml
        AppConst.pathXml = path.replace(" ", "").replace("\n", "");
    }

}
