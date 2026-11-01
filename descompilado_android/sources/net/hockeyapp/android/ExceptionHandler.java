package net.hockeyapp.android;

import android.os.Process;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler defaultExceptionHandler;
    private boolean ignoreDefaultHandler;
    private CrashManagerListener listener;

    public ExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, CrashManagerListener crashManagerListener, boolean z) {
        this.ignoreDefaultHandler = false;
        this.defaultExceptionHandler = uncaughtExceptionHandler;
        this.ignoreDefaultHandler = z;
        this.listener = crashManagerListener;
    }

    public void setListener(CrashManagerListener crashManagerListener) {
        this.listener = crashManagerListener;
    }

    public static void saveException(Throwable th, CrashManagerListener crashManagerListener) {
        Date date = new Date();
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        try {
            String string = UUID.randomUUID().toString();
            String str = Constants.FILES_PATH + com.appsflyer.share.Constants.URL_PATH_DELIMITER + string + ".stacktrace";
            Log.d("HockeyApp", "Writing unhandled exception to: " + str);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
            bufferedWriter.write("Package: " + Constants.APP_PACKAGE + "\n");
            bufferedWriter.write("Version Code: " + Constants.APP_VERSION + "\n");
            bufferedWriter.write("Version Name: " + Constants.APP_VERSION_NAME + "\n");
            if (crashManagerListener == null || crashManagerListener.includeDeviceData()) {
                bufferedWriter.write("Android: " + Constants.ANDROID_VERSION + "\n");
                bufferedWriter.write("Manufacturer: " + Constants.PHONE_MANUFACTURER + "\n");
                bufferedWriter.write("Model: " + Constants.PHONE_MODEL + "\n");
            }
            if (Constants.CRASH_IDENTIFIER != null && (crashManagerListener == null || crashManagerListener.includeDeviceIdentifier())) {
                bufferedWriter.write("CrashReporter Key: " + Constants.CRASH_IDENTIFIER + "\n");
            }
            bufferedWriter.write("Date: " + date + "\n");
            bufferedWriter.write("\n");
            bufferedWriter.write(stringWriter.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
            if (crashManagerListener != null) {
                writeValueToFile(limitedString(crashManagerListener.getUserID()), string + ".user");
                writeValueToFile(limitedString(crashManagerListener.getContact()), string + ".contact");
                writeValueToFile(crashManagerListener.getDescription(), string + ".description");
            }
        } catch (Exception e) {
            Log.e("HockeyApp", "Error saving exception stacktrace!\n", e);
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (Constants.FILES_PATH == null) {
            this.defaultExceptionHandler.uncaughtException(thread, th);
            return;
        }
        saveException(th, this.listener);
        if (!this.ignoreDefaultHandler) {
            this.defaultExceptionHandler.uncaughtException(thread, th);
        } else {
            Process.killProcess(Process.myPid());
            System.exit(10);
        }
    }

    private static void writeValueToFile(String str, String str2) {
        try {
            String str3 = Constants.FILES_PATH + com.appsflyer.share.Constants.URL_PATH_DELIMITER + str2;
            if (str.trim().length() > 0) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str3));
                bufferedWriter.write(str);
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        } catch (Exception unused) {
        }
    }

    private static String limitedString(String str) {
        return (str == null || str.length() <= 255) ? str : str.substring(0, 255);
    }
}
