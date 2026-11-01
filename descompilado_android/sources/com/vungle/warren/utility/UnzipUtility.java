package com.vungle.warren.utility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* JADX INFO: loaded from: classes2.dex */
public class UnzipUtility {
    private static final int BUFFER_SIZE = 4096;

    public static void unzip(String str, String str2) throws Throwable {
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdir();
        }
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
        for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
            String str3 = str2 + File.separator + nextEntry.getName();
            if (nextEntry.isDirectory()) {
                File file2 = new File(str3);
                if (!file2.exists()) {
                    file2.mkdir();
                }
            } else {
                extractFile(zipInputStream, str3);
            }
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
    }

    private static void extractFile(ZipInputStream zipInputStream, String str) throws Throwable {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(str));
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int i = zipInputStream.read(bArr);
                        if (i != -1) {
                            bufferedOutputStream2.write(bArr, 0, i);
                        } else {
                            FileUtility.closeQuietly(bufferedOutputStream2);
                            return;
                        }
                    }
                } catch (FileNotFoundException unused) {
                    bufferedOutputStream = bufferedOutputStream2;
                    File file = new File(str);
                    if (!file.getParentFile().exists()) {
                        for (File parentFile = file.getParentFile(); !parentFile.mkdir(); parentFile = parentFile.getParentFile()) {
                        }
                        extractFile(zipInputStream, str);
                    }
                    FileUtility.closeQuietly(bufferedOutputStream);
                } catch (Throwable th) {
                    th = th;
                    bufferedOutputStream = bufferedOutputStream2;
                    FileUtility.closeQuietly(bufferedOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (FileNotFoundException unused2) {
        }
    }
}
