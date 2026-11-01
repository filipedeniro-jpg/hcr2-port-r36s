package com.crashlytics.android.core;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
final class NativeFileUtils {
    private NativeFileUtils() {
    }

    static byte[] readFile(File file) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bytes = readBytes(fileInputStream);
                CommonUtils.closeQuietly(fileInputStream);
                return bytes;
            } catch (FileNotFoundException unused) {
                CommonUtils.closeQuietly(fileInputStream);
                return null;
            } catch (IOException unused2) {
                CommonUtils.closeQuietly(fileInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                fileInputStream2 = fileInputStream;
                CommonUtils.closeQuietly(fileInputStream2);
                throw th;
            }
        } catch (FileNotFoundException unused3) {
            fileInputStream = null;
        } catch (IOException unused4) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static byte[] readBytes(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    private static File filter(File file, String str) {
        for (File file2 : file.listFiles()) {
            if (file2.getName().endsWith(str)) {
                return file2;
            }
        }
        return null;
    }

    static byte[] minidumpFromDirectory(File file) {
        File fileFilter = filter(file, ".dmp");
        return fileFilter == null ? new byte[0] : minidumpFromFile(fileFilter);
    }

    private static byte[] minidumpFromFile(File file) {
        return readFile(file);
    }

    static byte[] binaryImagesJsonFromDirectory(File file, Context context) throws IOException {
        File fileFilter = filter(file, ".maps");
        if (fileFilter != null) {
            return binaryImagesJsonFromMapsFile(fileFilter, context);
        }
        File fileFilter2 = filter(file, ".binary_libs");
        if (fileFilter2 != null) {
            return binaryImagesJsonFromBinaryLibsFile(fileFilter2, context);
        }
        return null;
    }

    private static byte[] binaryImagesJsonFromBinaryLibsFile(File file, Context context) throws Throwable {
        byte[] file2 = readFile(file);
        if (file2 == null || file2.length == 0) {
            return null;
        }
        return processBinaryImages(context, new String(file2));
    }

    private static byte[] binaryImagesJsonFromMapsFile(File file, Context context) throws Throwable {
        BufferedReader bufferedReader;
        if (!file.exists()) {
            return null;
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            try {
                byte[] bArrConvert = new BinaryImagesConverter(context, new Sha1FileIdStrategy()).convert(bufferedReader);
                CommonUtils.closeQuietly(bufferedReader);
                return bArrConvert;
            } catch (Throwable th) {
                th = th;
                CommonUtils.closeQuietly(bufferedReader);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
    }

    static byte[] metadataJsonFromDirectory(File file) {
        File fileFilter = filter(file, ".device_info");
        if (fileFilter == null) {
            return null;
        }
        return readFile(fileFilter);
    }

    private static byte[] processBinaryImages(Context context, String str) throws IOException {
        return new BinaryImagesConverter(context, new Sha1FileIdStrategy()).convert(str);
    }
}
