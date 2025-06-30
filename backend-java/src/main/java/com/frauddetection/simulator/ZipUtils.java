package com.frauddetection.simulator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {
    /**
     * Extracts a zip file to the specified destination folder.
     */
    public static void unzip(String zipFilePath, String destDir) throws IOException {
        File dir = new File(destDir);
        if (!dir.exists()) dir.mkdirs();

        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                String filePath = destDir + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    // Extract file
                    extractFile(zipIn, filePath);
                } else {
                    // Make directories
                    new File(filePath).mkdirs();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytesIn = new byte[4096];
            int read;
            while ((read = zipIn.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
    }
}
