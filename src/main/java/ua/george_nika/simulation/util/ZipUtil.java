/**
 * Work`s with zip
 */

package ua.george_nika.simulation.util;

import ua.george_nika.simulation.util.error.ZipException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    private static String LOGGER_NAME = AppLog.UTIL;
    private static String CLASS_NAME = ZipUtil.class.getSimpleName();

    public static String createZipFile(String pathName, String fileName) {
        String newFileName = fileName+".zip";
        String newFullFileName = pathName+newFileName;
        try (ZipOutputStream zOut = new ZipOutputStream(new FileOutputStream(newFullFileName))) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zOut.putNextEntry(zipEntry);
            try (FileInputStream in = new FileInputStream(pathName+fileName)) {
                byte[] bytes = new byte[1024];
                int length;
                while ((length = in.read(bytes)) >= 0) {
                    zOut.write(bytes, 0, length);
                }
            }
            zOut.closeEntry();
        } catch (IOException e) {
            AppLog.error(LOGGER_NAME, CLASS_NAME, "Error. Can not zip file - " + pathName + fileName, e);
            throw new ZipException("Error. Can not zip file - " + pathName + fileName, e);
        }
        return newFileName;
    }
}
