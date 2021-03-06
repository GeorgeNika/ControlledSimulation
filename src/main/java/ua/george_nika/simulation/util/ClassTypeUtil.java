/**
 * Returned inherited class or throw exception
 */

package ua.george_nika.simulation.util;

import ua.george_nika.simulation.service.error.WrongTypeException;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

public class ClassTypeUtil {

    private static String LOGGER_NAME = AppLog.UTIL;
    private static String CLASS_NAME = ClassTypeUtil.class.getSimpleName();

    public static <T> T getCheckedClass(Object checkedObject, Class<T> neededClass) {
        if (neededClass.isAssignableFrom(checkedObject.getClass())) {
            return neededClass.cast(checkedObject);
        } else {
            throw new WrongTypeException(LOGGER_NAME, CLASS_NAME, "Wrong type of data." +
                    " Object class - " + checkedObject.getClass().getCanonicalName() + " ; " +
                    " instead " + neededClass.getCanonicalName(), new RuntimeException());
        }
    }
}
