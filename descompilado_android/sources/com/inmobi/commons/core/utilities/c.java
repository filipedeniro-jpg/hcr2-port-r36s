package com.inmobi.commons.core.utilities;

import android.net.Uri;
import java.io.File;

/* JADX INFO: compiled from: FileUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    private static final String a = "c";

    public static void a(File file) {
        try {
            if (file.exists()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        if (file2.isDirectory()) {
                            a(file2);
                        } else {
                            file2.delete();
                        }
                    }
                }
                file.delete();
            }
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in deleting directory; ").append(e.getMessage());
        }
    }

    public static long a(String str) {
        try {
            File file = new File(Uri.parse(str).getPath());
            if (file.exists()) {
                return file.length();
            }
            return 0L;
        } catch (Exception unused) {
            return 0L;
        }
    }
}
