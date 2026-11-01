package net.nend.android.internal.utilities;

import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* JADX INFO: compiled from: NendFileUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class i {
    private static boolean c(File file) {
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    public static File a(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new File(context.getNoBackupFilesDir().getPath(), str);
        }
        return new File(context.getFilesDir().getPath(), str);
    }

    protected static void a(Context context, String str, String str2, String str3) {
        File fileA = a(context, str2);
        if (c(fileA)) {
            try {
                FileWriter fileWriter = new FileWriter(new File(fileA, str));
                fileWriter.write(str3);
                fileWriter.close();
            } catch (IOException e) {
                j.b("Failed to write file.", e);
            }
        }
    }

    @NonNull
    public static String a(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line);
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            j.b("Failed to read file.", e);
            return "";
        }
    }

    protected static void b(Context context, String str) {
        b(a(context, str));
    }

    public static void b(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    b(file2);
                }
            }
            file.delete();
        }
    }

    public static String a(String str) {
        return o.a(str).toUpperCase() + b(str);
    }

    private static String b(String str) {
        int iLastIndexOf = str.lastIndexOf(".");
        if (iLastIndexOf == -1) {
            return "";
        }
        String strSubstring = str.substring(iLastIndexOf);
        int iIndexOf = strSubstring.indexOf("?");
        return iIndexOf != -1 ? strSubstring.substring(0, iIndexOf) : strSubstring;
    }
}
