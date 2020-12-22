package com.antecedentium.util;

import java.io.File;
import java.nio.file.Files;
import java.text.DecimalFormat;

public class FileUtil {
    public static long getSize(File file) throws Exception {
        long size;
        if (file.isDirectory()) {
            size = 0;
            for (File child : file.listFiles()) {
                size += getSize(child);
            }
        } else {
            size = file.length();
        }
        return size;
    }

    public static String getReadableSize(long size) {
        if(size <= 0) return "0";
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups))
                + " " + units[digitGroups];
    }

    public static double walk(String path) throws Exception {
        return Files.walk(new File(path).toPath())
                .map(f -> f.toFile())
                .filter(f -> f.isFile())
                .mapToLong(f -> f.length()).sum();
    }
}