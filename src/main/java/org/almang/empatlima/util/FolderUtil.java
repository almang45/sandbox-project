package org.almang.empatlima.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Collection;

import org.almang.empatlima.model.Constant;
import org.apache.commons.io.FileUtils;

/**
 * Created by Eleanor on 11/18/2015.
 */
public class FolderUtil {

    public static String calculateAverageSizePerSeason(File source, int totalSeason) {
        long size = FileUtils.sizeOfDirectory(source) / totalSeason;
        int unitIndex = (int) (Math.log10(size) / 3);
        double unitValue = 1 << (unitIndex * 10);
        return new DecimalFormat("#,##0.#").format(size / unitValue) + " " +
                Constant.units[unitIndex];
    }

    public static String calculateFolderSizeString(File source) {
        long size = FileUtils.sizeOfDirectory(source);
        int unitIndex = (int) (Math.log10(size) / 3);
        double unitValue = 1 << (unitIndex * 10);
        return new DecimalFormat("#,##0.#").format(size / unitValue) + " " +
                Constant.units[unitIndex];
    }

    public static boolean checkSubtitlesFiles(String pathName) {
        GenericExtensionFilter filter = new GenericExtensionFilter(Constant.SRT_EXT);

        File dir = new File(pathName);
        if (!dir.isDirectory()) {
            System.out.println("Directory does not exists : " + pathName);
            return false;
        }

        String[] list = dir.list(filter);
        if (list.length == 0) {
            System.out.println("no files end with : " + Constant.SRT_EXT);
            return false;
        } else {
            return true;
        }
    }

    public static int countSeriesEpisodeFiles(String pathName) {
        File dir = new File(pathName);
        if (!dir.isDirectory()) {
            System.out.println("Directory does not exists : " + pathName);
            return 0;
        }

        Collection<File> files = FileUtils.listFiles(dir, Constant.FILM_EXTS, true);
        return files.size();
    }

    public static int countSubFolder(File source) {
        File[] files = source.listFiles();
        int count = 0;
        for (File file : files) {
            if (file.isDirectory()) {
                count++;
            }
        }
        return count;
    }

    public static String getTitle(String source) {
        if (source.contains("(")) {
            return source.substring(0, source.indexOf("("));
        } else {
            return source;
        }
    }

    public static String getYear(String source) {
        if (source.contains("(") && source.contains(")")) {
            return source.substring(source.indexOf("(") + 1, source.indexOf(")"));
        } else {
            return Constant.EMPTY_STRING;
        }
    }
}
