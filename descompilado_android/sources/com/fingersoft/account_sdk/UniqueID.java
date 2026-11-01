package com.fingersoft.account_sdk;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class UniqueID {
    private static final String FILE = "FSACCOUNTID";
    private static String sID;

    public static synchronized String id(Context context) {
        if (sID == null) {
            File file = new File(context.getFilesDir(), FILE);
            try {
                if (!file.exists()) {
                    writeInstallationFile(file);
                }
                sID = readInstallationFile(file);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sID;
    }

    public static synchronized String updateId(Context context) {
        File file = new File(context.getFilesDir(), FILE);
        try {
            writeInstallationFile(file);
            sID = readInstallationFile(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sID;
    }

    private static String readInstallationFile(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[(int) randomAccessFile.length()];
        randomAccessFile.readFully(bArr);
        randomAccessFile.close();
        return new String(bArr);
    }

    private static void writeInstallationFile(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(UUID.randomUUID().toString().getBytes());
        fileOutputStream.close();
    }
}
