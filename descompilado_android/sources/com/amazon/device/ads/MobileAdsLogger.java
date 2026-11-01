package com.amazon.device.ads;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class MobileAdsLogger implements Logger {
    private static final String DEFAULT_LOGTAG_PREFIX = "AmazonMobileAds";
    private static final int DEFAULT_MAX_LENGTH = 1000;
    static final String LOGGING_ENABLED = "loggingEnabled";
    private final DebugProperties debugProperties;
    private final Logger logger;
    private int maxLength;
    private final Settings settings;

    public enum Level {
        DEBUG,
        ERROR,
        INFO,
        VERBOSE,
        WARN
    }

    public MobileAdsLogger(Logger logger) {
        this(logger, DebugProperties.getInstance(), Settings.getInstance());
    }

    MobileAdsLogger(Logger logger, DebugProperties debugProperties, Settings settings) {
        this.maxLength = 1000;
        this.logger = logger.withLogTag(DEFAULT_LOGTAG_PREFIX);
        this.debugProperties = debugProperties;
        this.settings = settings;
    }

    public MobileAdsLogger withMaxLength(int i) {
        this.maxLength = i;
        return this;
    }

    @Override // com.amazon.device.ads.Logger
    public MobileAdsLogger withLogTag(String str) {
        this.logger.withLogTag("AmazonMobileAds " + str);
        return this;
    }

    public void enableLogging(boolean z) {
        this.settings.putTransientBoolean(LOGGING_ENABLED, z);
    }

    public boolean canLog() {
        DebugProperties debugProperties;
        if (this.logger == null || (debugProperties = this.debugProperties) == null) {
            return false;
        }
        return debugProperties.getDebugPropertyAsBoolean(DebugProperties.DEBUG_LOGGING, Boolean.valueOf(this.settings.getBoolean(LOGGING_ENABLED, false))).booleanValue();
    }

    public final void enableLoggingWithSetterNotification(boolean z) {
        if (!z) {
            logSetterNotification("Debug logging", Boolean.valueOf(z));
        }
        enableLogging(z);
        if (z) {
            logSetterNotification("Debug logging", Boolean.valueOf(z));
            d("Amazon Mobile Ads API Version: %s", Version.getRawSDKVersion());
        }
    }

    public void logSetterNotification(String str, Object obj) {
        if (canLog()) {
            if (obj instanceof Boolean) {
                Object[] objArr = new Object[2];
                objArr[0] = str;
                objArr[1] = ((Boolean) obj).booleanValue() ? "enabled" : "disabled";
                d("%s has been %s.", objArr);
                return;
            }
            d("%s has been set: %s", str, String.valueOf(obj));
        }
    }

    public void i(String str, Object... objArr) {
        log(Level.INFO, str, objArr);
    }

    public void v(String str, Object... objArr) {
        log(Level.VERBOSE, str, objArr);
    }

    public void d(String str, Object... objArr) {
        log(Level.DEBUG, str, objArr);
    }

    public void w(String str, Object... objArr) {
        log(Level.WARN, str, objArr);
    }

    public void e(String str, Object... objArr) {
        log(Level.ERROR, str, objArr);
    }

    @Override // com.amazon.device.ads.Logger
    public void i(String str) {
        i(str, (Object[]) null);
    }

    @Override // com.amazon.device.ads.Logger
    public void v(String str) {
        v(str, (Object[]) null);
    }

    @Override // com.amazon.device.ads.Logger
    public void d(String str) {
        d(str, (Object[]) null);
    }

    @Override // com.amazon.device.ads.Logger
    public void w(String str) {
        w(str, (Object[]) null);
    }

    @Override // com.amazon.device.ads.Logger
    public void e(String str) {
        e(str, (Object[]) null);
    }

    public void log(Level level, String str, Object... objArr) {
        doLog(false, level, str, objArr);
    }

    public void forceLog(Level level, String str, Object... objArr) {
        doLog(true, level, str, objArr);
    }

    private void doLog(boolean z, Level level, String str, Object... objArr) {
        if (canLog() || z) {
            for (String str2 : formatAndSplit(str, objArr)) {
                int i = AnonymousClass1.$SwitchMap$com$amazon$device$ads$MobileAdsLogger$Level[level.ordinal()];
                if (i == 1) {
                    this.logger.d(str2);
                } else if (i == 2) {
                    this.logger.e(str2);
                } else if (i == 3) {
                    this.logger.i(str2);
                } else if (i == 4) {
                    this.logger.v(str2);
                } else if (i == 5) {
                    this.logger.w(str2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.device.ads.MobileAdsLogger$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$MobileAdsLogger$Level = new int[Level.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$ads$MobileAdsLogger$Level[Level.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$MobileAdsLogger$Level[Level.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$MobileAdsLogger$Level[Level.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$MobileAdsLogger$Level[Level.VERBOSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$MobileAdsLogger$Level[Level.WARN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private Iterable<String> formatAndSplit(String str, Object... objArr) {
        if (objArr != null && objArr.length > 0) {
            str = String.format(str, objArr);
        }
        return split(str, this.maxLength);
    }

    private Iterable<String> split(String str, int i) {
        ArrayList arrayList = new ArrayList();
        if (str != null && str.length() != 0) {
            int i2 = 0;
            while (i2 < str.length()) {
                int i3 = i2 + i;
                arrayList.add(str.substring(i2, Math.min(str.length(), i3)));
                i2 = i3;
            }
        }
        return arrayList;
    }
}
