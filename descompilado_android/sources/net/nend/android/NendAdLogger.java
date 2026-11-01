package net.nend.android;

import android.util.Log;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdLogger {
    private static NendAdLogger a;
    private static LogLevel b = LogLevel.OFF;
    public NendAdLogging logger = new a();

    private NendAdLogger() {
    }

    public static synchronized NendAdLogger sharedInstance() {
        if (a == null) {
            a = new NendAdLogger();
        }
        return a;
    }

    public static LogLevel getLogLevel() {
        return b;
    }

    public static void setLogLevel(LogLevel logLevel) {
        b = logLevel;
    }

    public enum LogLevel {
        DEBUG(3),
        INFO(4),
        WARN(5),
        ERROR(6),
        OFF(Integer.MAX_VALUE);

        private final int a;

        LogLevel(int i) {
            this.a = i;
        }

        public int getInt() {
            return this.a;
        }
    }

    private final class a implements NendAdLogging {
        private a() {
        }

        @Override // net.nend.android.NendAdLogging
        public void logMessage(@NonNull String str, @NonNull LogLevel logLevel) {
            if (Log.isLoggable("nend_SDK", logLevel.getInt())) {
                Log.println(logLevel.getInt(), "nend_SDK", str);
            }
        }
    }
}
