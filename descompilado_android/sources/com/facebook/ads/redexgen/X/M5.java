package com.facebook.ads.redexgen.X;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class M5 {
    private M5() {
    }

    public static List<File> B(File directory) {
        List<File> result = new LinkedList<>();
        File[] fileArrListFiles = directory.listFiles();
        if (fileArrListFiles != null) {
            List<File> result2 = Arrays.asList(fileArrListFiles);
            Collections.sort(result2, new M4());
            return result2;
        }
        return result;
    }

    public static void C(File directory) throws IOException {
        if (directory.exists()) {
            if (!directory.isDirectory()) {
                throw new IOException("File " + directory + " is not directory!");
            }
        } else if (!directory.mkdirs()) {
            throw new IOException(String.format(Locale.US, "Directory %s can't be created", directory.getAbsolutePath()));
        }
    }

    public static void D(File file) throws IOException {
        if (file.exists()) {
            long now = System.currentTimeMillis();
            if (!file.setLastModified(now)) {
                E(file);
                if (file.lastModified() < now) {
                    throw new IOException("Error set last modified date to " + file);
                }
            }
        }
    }

    private static void E(File file) throws IOException {
        long size = file.length();
        if (size == 0) {
            F(file);
            return;
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
        randomAccessFile.seek(size - 1);
        byte b = randomAccessFile.readByte();
        randomAccessFile.seek(size - 1);
        randomAccessFile.write(b);
        randomAccessFile.close();
    }

    private static void F(File file) throws IOException {
        if (!file.delete() || !file.createNewFile()) {
            throw new IOException("Error recreate zero-size file " + file);
        }
    }
}
