package com.ironsource.mediationsdk.logger;

/* JADX INFO: loaded from: classes2.dex */
public abstract class IronSourceLogger {
    int mDebugLevel;
    private String mLoggerName;

    public enum IronSourceTag {
        API,
        ADAPTER_API,
        CALLBACK,
        ADAPTER_CALLBACK,
        NETWORK,
        INTERNAL,
        NATIVE,
        EVENT
    }

    public abstract void log(IronSourceTag ironSourceTag, String str, int i);

    public abstract void logException(IronSourceTag ironSourceTag, String str, Throwable th);

    IronSourceLogger(String str) {
        this.mLoggerName = str;
        this.mDebugLevel = 0;
    }

    IronSourceLogger(String str, int i) {
        this.mLoggerName = str;
        this.mDebugLevel = i;
    }

    String getLoggerName() {
        return this.mLoggerName;
    }

    int getDebugLevel() {
        return this.mDebugLevel;
    }

    public void setDebugLevel(int i) {
        this.mDebugLevel = i;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof IronSourceLogger)) {
            return false;
        }
        IronSourceLogger ironSourceLogger = (IronSourceLogger) obj;
        String str = this.mLoggerName;
        return str != null && str.equals(ironSourceLogger.mLoggerName);
    }

    public class IronSourceLogLevel {
        public static final int ERROR = 3;
        public static final int INFO = 1;
        public static final int VERBOSE = 0;
        public static final int WARNING = 2;

        public IronSourceLogLevel() {
        }
    }
}
