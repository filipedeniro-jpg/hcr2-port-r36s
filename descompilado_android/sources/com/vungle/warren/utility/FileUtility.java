package com.vungle.warren.utility;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.appsflyer.share.Constants;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class FileUtility {
    public static void printDirectoryTree(File file) {
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("folder is not a Directory");
        }
        StringBuilder sb = new StringBuilder();
        printDirectoryTree(file, 0, sb);
        Log.v("Vungle", sb.toString());
    }

    private static void printDirectoryTree(File file, int i, StringBuilder sb) {
        if (file == null) {
            return;
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("folder is not a Directory");
        }
        sb.append(getIndentString(i));
        sb.append("+--");
        sb.append(file.getName());
        sb.append(Constants.URL_PATH_DELIMITER);
        sb.append("\n");
        if (file.listFiles() == null) {
            return;
        }
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                printDirectoryTree(file2, i + 1, sb);
            } else {
                printFile(file2, i + 1, sb);
            }
        }
    }

    private static void printFile(File file, int i, StringBuilder sb) {
        sb.append(getIndentString(i));
        sb.append("+--");
        sb.append(file.getName());
        sb.append("\n");
    }

    private static String getIndentString(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("|  ");
        }
        return sb.toString();
    }

    public static void delete(File file) throws IOException {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                delete(file2);
            }
        }
        if (file.delete()) {
            return;
        }
        throw new FileNotFoundException("Failed to delete file: " + file);
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    @NonNull
    public static byte[] extractBytes(@Nullable File file) throws IOException {
        BufferedInputStream bufferedInputStream;
        if (file == null) {
            return new byte[0];
        }
        int length = (int) file.length();
        if (length > 0) {
            byte[] bArr = new byte[length];
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    if (bufferedInputStream.read(bArr) < length) {
                        throw new IOException("Failed to read all bytes in the file, object recreation will fail");
                    }
                    bufferedInputStream.close();
                    return bArr;
                } catch (FileNotFoundException e) {
                    e = e;
                    e.printStackTrace();
                    closeQuietly(bufferedInputStream);
                    return new byte[0];
                }
            } catch (FileNotFoundException e2) {
                e = e2;
                bufferedInputStream = null;
            }
        }
        return new byte[0];
    }
}
