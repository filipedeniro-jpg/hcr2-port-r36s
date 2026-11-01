package com.appsflyer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import java.security.SecureRandom;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
final class t {

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static String f256;

    t() {
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    public static synchronized String m211(WeakReference<Context> weakReference) {
        String string;
        if (weakReference.get() == null) {
            return f256;
        }
        if (f256 == null) {
            String string2 = null;
            if (weakReference.get() != null) {
                string2 = weakReference.get().getSharedPreferences("appsflyer-data", 0).getString("AF_INSTALLATION", null);
            }
            if (string2 != null) {
                f256 = string2;
            } else {
                try {
                    File file = new File(weakReference.get().getFilesDir(), "AF_INSTALLATION");
                    if (file.exists()) {
                        f256 = m210(file);
                        file.delete();
                    } else {
                        if (Build.VERSION.SDK_INT >= 9) {
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            StringBuilder sb = new StringBuilder();
                            sb.append(jCurrentTimeMillis);
                            sb.append("-");
                            sb.append(Math.abs(new SecureRandom().nextLong()));
                            string = sb.toString();
                        } else {
                            string = UUID.randomUUID().toString();
                        }
                        f256 = string;
                    }
                    String str = f256;
                    SharedPreferences.Editor editorEdit = weakReference.get().getSharedPreferences("appsflyer-data", 0).edit();
                    editorEdit.putString("AF_INSTALLATION", str);
                    if (Build.VERSION.SDK_INT >= 9) {
                        editorEdit.apply();
                    } else {
                        editorEdit.commit();
                    }
                } catch (Exception e) {
                    AFLogger.afErrorLog("Error getting AF unique ID", e);
                }
            }
            if (f256 != null) {
                AppsFlyerProperties.getInstance().set("uid", f256);
            }
        }
        return f256;
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static String m210(File file) throws Throwable {
        byte[] bArr;
        RandomAccessFile randomAccessFile = null;
        byte[] bArr2 = null;
        randomAccessFile = null;
        try {
            try {
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
                try {
                    bArr2 = new byte[(int) randomAccessFile2.length()];
                    randomAccessFile2.readFully(bArr2);
                    randomAccessFile2.close();
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e) {
                        AFLogger.afErrorLog("Exception while trying to close the InstallationFile", e);
                    }
                } catch (IOException e2) {
                    e = e2;
                    bArr = bArr2;
                    randomAccessFile = randomAccessFile2;
                    AFLogger.afErrorLog("Exception while reading InstallationFile: ", e);
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e3) {
                            AFLogger.afErrorLog("Exception while trying to close the InstallationFile", e3);
                        }
                    }
                    bArr2 = bArr;
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFile2;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e4) {
                            AFLogger.afErrorLog("Exception while trying to close the InstallationFile", e4);
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
                bArr = null;
            }
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            return new String(bArr2);
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
