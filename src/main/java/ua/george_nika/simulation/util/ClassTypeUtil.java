package ua.george_nika.simulation.util;

import ua.george_nika.simulation.service.error.WrongTypeException;

/**
 * Created by george on 14.01.2016.
 */
public class ClassTypeUtil {

    private static String LOGGER_NAME = AppLog.UTIL;
    private static String CLASS_NAME = ClassTypeUtil.class.getSimpleName();

    public static <T> T getCheckedClass(Object checkedObject, Class<T> neededClass) {
        if (neededClass.isAssignableFrom(checkedObject.getClass())) {
            return neededClass.cast(checkedObject);
        } else {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Wrong type of data." +
                    " Object class - " + checkedObject.getClass().getCanonicalName() + " ; " +
                    " instead " + neededClass.getCanonicalName());
            throw new WrongTypeException("Wrong type of data." +
                    " Object class - " + checkedObject.getClass().getCanonicalName() + " ; " +
                    " instead " + neededClass.getCanonicalName());
        }
    }
}
