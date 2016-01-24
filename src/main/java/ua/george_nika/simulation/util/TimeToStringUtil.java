/**
 * Returns string representation of date
 * if date is null then will return empty string
 *
 * after lecture  JavaDoc + UnitTest = Documentation
 */
package ua.george_nika.simulation.util;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.format.DateTimeFormat;

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
