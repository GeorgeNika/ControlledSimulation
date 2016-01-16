package ua.george_nika.simulation.util;

import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by george on 10.12.2015.
 */
public class ZipUtil {

    public void someZip() {
        try (ZipOutputStream zOut = new ZipOutputStream(new FileOutputStream("ZipTry.zip"))) {
            ZipEntry zipEntry = new ZipEntry("george/ua/ZipTry.java");
            zOut.putNextEntry(zipEntry);
//            try (FileInputStream in = new FileInputStream("Test.txt")) {
//                byte[] bytes = new byte[1024];
//                int length;
//                while ((length = in.read(bytes)) >= 0) {
//                    zOut.write(bytes, 0, length);
//                }
//            }
            String s= "hdssssssssssssssssssssssssssssss";
            zOut.write(s.getBytes(), 0,s.length());
            zOut.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
