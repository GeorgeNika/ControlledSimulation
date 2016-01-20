/**
 * used for return string with date and time if exist
 * after lecture  JavaDoc + UnitTest = Documentation
 */
package ua.george_nika.simulation.util;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * Created by george on 19.01.2016.
 */
public class TimeToStringUtil {

    private TimeToStringUtil() {
    }

    public static String getSafeString(DateTime dateTime) {
        if (dateTime != null) {
            return dateTime.toString(DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT));
        } else {
            return "";
        }
    }

    public static String getSafeString(MutableDateTime dateTime) {
        if (dateTime != null) {
            return dateTime.toString(DateTimeFormat.forPattern(AppConst.DATE_TIME_FORMAT));
        } else {
            return "";
        }
    }
}
