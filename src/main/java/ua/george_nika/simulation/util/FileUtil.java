/**
 * Simple function with operation system
 * i.g. delete file
 */

package ua.george_nika.simulation.util;

import ua.george_nika.simulation.util.error.DeleteFileException;

import java.io.File;

@SuppressWarnings({"unused", "FieldCanBeLocal"})

public class FileUtil {
    private static String LOGGER_NAME = AppLog.UTIL;
    private static String CLASS_NAME = FileUtil.class.getSimpleName();

    public static void deleteFile(String pathName, String fileName) {
        try {

            File file = new File(pathName + fileName);

            if (!file.delete()) {
                throw new DeleteFileException();
            }
        } catch (RuntimeException e) {
            throw new DeleteFileException(LOGGER_NAME, CLASS_NAME, "Error. Could not delete file - "
                    + pathName + fileName, e);
        }
    }
}
