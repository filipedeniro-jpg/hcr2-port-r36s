package com.inmobi.sdk;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.SystemClock;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.a.o;
import com.inmobi.ads.cache.AssetStore;
import com.inmobi.ads.cache.d;
import com.inmobi.commons.core.d.c;
import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.b.e;
import com.inmobi.commons.core.utilities.b.g;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class InMobiSdk {
    private static final ExecutorService COMPONENT_SERVICE = Executors.newSingleThreadExecutor();
    public static final String IM_GDPR_CONSENT_AVAILABLE = "gdpr_consent_available";
    private static final String TAG = "InMobiSdk";

    public enum LogLevel {
        NONE,
        ERROR,
        DEBUG
    }

    public static String getVersion() {
        return "7.2.1";
    }

    public enum Education {
        HIGH_SCHOOL_OR_LESS("highschoolorless"),
        COLLEGE_OR_GRADUATE("collegeorgraduate"),
        POST_GRADUATE_OR_ABOVE("postgraduateorabove");

        private String a;

        Education(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return this.a;
        }
    }

    public enum Gender {
        FEMALE("f"),
        MALE("m");

        private String a;

        Gender(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return this.a;
        }
    }

    public enum AgeGroup {
        BELOW_18("below18"),
        BETWEEN_18_AND_24("between18and24"),
        BETWEEN_25_AND_29("between25and29"),
        BETWEEN_30_AND_34("between30and34"),
        BETWEEN_35_AND_44("between35and44"),
        BETWEEN_45_AND_54("between45and54"),
        BETWEEN_55_AND_65("between55and65"),
        ABOVE_65("above65");

        private String a;

        AgeGroup(String str) {
            this.a = str;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return this.a;
        }
    }

    public static void init(Context context, String str) {
        init(context, str, null);
    }

    public static void init(Context context, String str, JSONObject jSONObject) {
        com.inmobi.commons.core.utilities.a aVarA;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        try {
            e.a(jSONObject);
            if (Build.VERSION.SDK_INT < 14) {
                Logger.a(Logger.InternalLogLevel.ERROR, TAG, "The minimum supported Android API level is 14, SDK could not be initialized.");
                return;
            }
            if (context == null) {
                Logger.a(Logger.InternalLogLevel.ERROR, TAG, "Context supplied as null, SDK could not be initialized.");
                return;
            }
            if (str != null && str.trim().length() != 0) {
                Intent intent = new Intent();
                intent.setClassName(context.getPackageName(), "com.inmobi.rendering.InMobiAdActivity");
                if (context.getPackageManager().resolveActivity(intent, 65536) == null) {
                    Logger.a(Logger.InternalLogLevel.ERROR, TAG, "The activity com.inmobi.rendering.InMobiAdActivity not present in AndroidManifest. SDK could not be initialized.");
                    return;
                }
                if (com.inmobi.commons.core.utilities.e.a(context, "ads", "android.permission.INTERNET") && com.inmobi.commons.core.utilities.e.a(context, "ads", "android.permission.ACCESS_NETWORK_STATE")) {
                    if (!com.inmobi.commons.core.utilities.e.a(context, "ads", "android.permission.ACCESS_COARSE_LOCATION") && !com.inmobi.commons.core.utilities.e.a(context, "ads", "android.permission.ACCESS_FINE_LOCATION")) {
                        Logger.a(Logger.InternalLogLevel.ERROR, TAG, "Please grant the location permissions (ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION, or both) for better ad targeting.");
                    }
                    str = str.trim();
                    if (str.length() != 32 && str.length() != 36) {
                        Logger.a(Logger.InternalLogLevel.DEBUG, TAG, "Invalid account id passed to init. Please provide a valid account id.");
                    }
                    if (com.inmobi.commons.a.a.a()) {
                        try {
                            b.a();
                            b.a("root", "InitRequested", null);
                            return;
                        } catch (Exception e) {
                            StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                            sb.append(e.getMessage());
                            sb.append(")");
                            return;
                        }
                    }
                    if (hasSdkVersionChanged(context)) {
                        com.inmobi.commons.a.b.a(context, a.a(context));
                        c.a(context, "sdk_version_store").a("sdk_version", "7.2.1");
                        resetMediaCache(context.getApplicationContext());
                    }
                    com.inmobi.commons.a.a.a(context, str);
                    com.inmobi.commons.core.configs.b.a().b();
                    b.a().b();
                    if (c.a(context, "sdk_version_store").b("db_deletion_failed", false)) {
                        List<String> listB = a.b(context);
                        Iterator<String> it = listB.iterator();
                        while (it.hasNext()) {
                            sendDbDeletionTelemetryEvent(it.next());
                        }
                        if (listB.isEmpty()) {
                            com.inmobi.commons.a.b.a(context, false);
                        }
                    }
                    g.b();
                    initComponents();
                    com.inmobi.commons.core.configs.b.a();
                    com.inmobi.commons.core.configs.b.d();
                    COMPONENT_SERVICE.execute(new Runnable() { // from class: com.inmobi.sdk.InMobiSdk.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            File[] fileArrListFiles;
                            boolean z;
                            try {
                                com.inmobi.ads.b.c();
                                AssetStore.a().b();
                                AssetStore assetStoreA = AssetStore.a();
                                synchronized (AssetStore.e) {
                                    d dVar = assetStoreA.a;
                                    List<com.inmobi.ads.cache.a> listB2 = d.b();
                                    if (listB2.isEmpty()) {
                                        return;
                                    }
                                    Iterator<com.inmobi.ads.cache.a> it2 = listB2.iterator();
                                    while (true) {
                                        boolean z2 = true;
                                        if (!it2.hasNext()) {
                                            break;
                                        }
                                        com.inmobi.ads.cache.a next = it2.next();
                                        if (System.currentTimeMillis() <= next.h) {
                                            z2 = false;
                                        }
                                        if (z2) {
                                            AssetStore.a(next);
                                        }
                                    }
                                    while (true) {
                                        long length = 0;
                                        Iterator<com.inmobi.ads.cache.a> it3 = d.b().iterator();
                                        while (it3.hasNext()) {
                                            length += new File(it3.next().e).length();
                                        }
                                        new StringBuilder("MAX CACHESIZE ").append(assetStoreA.b.d);
                                        if (length <= assetStoreA.b.d) {
                                            break;
                                        }
                                        List<ContentValues> listA = com.inmobi.commons.core.d.b.a().a("asset", d.a, null, null, null, null, "ts ASC ", null);
                                        com.inmobi.ads.cache.a aVarA2 = listA.size() == 0 ? null : d.a(listA.get(0));
                                        if (aVarA2 == null) {
                                            break;
                                        } else {
                                            AssetStore.a(aVarA2);
                                        }
                                    }
                                    File fileA = com.inmobi.commons.a.a.a(com.inmobi.commons.a.a.b());
                                    if (fileA.exists() && (fileArrListFiles = fileA.listFiles()) != null) {
                                        for (File file : fileArrListFiles) {
                                            Iterator<com.inmobi.ads.cache.a> it4 = listB2.iterator();
                                            while (true) {
                                                if (it4.hasNext()) {
                                                    if (file.getAbsolutePath().equals(it4.next().e)) {
                                                        z = true;
                                                        break;
                                                    }
                                                } else {
                                                    z = false;
                                                    break;
                                                }
                                            }
                                            if (!z) {
                                                new StringBuilder("found Orphan file ").append(file.getAbsolutePath());
                                                file.delete();
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e2) {
                                String unused = InMobiSdk.TAG;
                                StringBuilder sb2 = new StringBuilder("Error in starting Asset Cache : (");
                                sb2.append(e2.getMessage());
                                sb2.append(")");
                            }
                        }
                    });
                    if ((context instanceof Activity) && (aVarA = com.inmobi.commons.core.utilities.a.a()) != null) {
                        aVarA.a(new com.inmobi.commons.core.utilities.a.b() { // from class: com.inmobi.sdk.InMobiSdk.2
                            @Override // com.inmobi.commons.core.utilities.a.b
                            public final void a(boolean z) {
                                com.inmobi.commons.a.a.b(z);
                                try {
                                    if (z) {
                                        InMobiSdk.initComponents();
                                    } else {
                                        InMobiSdk.deInitComponents();
                                    }
                                } catch (Exception e2) {
                                    String unused = InMobiSdk.TAG;
                                    new StringBuilder("Encountered unexpected error in the onFocusChanged handler: ").append(e2.getMessage());
                                    Logger.a(Logger.InternalLogLevel.DEBUG, InMobiSdk.TAG, "SDK encountered an unexpected error; some components may not work as advertised");
                                }
                            }
                        });
                    }
                    try {
                        b.a();
                        b.a("root", "InitRequested", null);
                    } catch (Exception e2) {
                        StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                        sb2.append(e2.getMessage());
                        sb2.append(")");
                    }
                    com.inmobi.commons.a.a.c();
                    Logger.a(Logger.InternalLogLevel.ERROR, TAG, "SDK could not be initialized; an unexpected error was encountered");
                    new StringBuilder("Encountered unexpected error while initializing the SDK: ").append(e.getMessage());
                }
                Logger.a(Logger.InternalLogLevel.ERROR, TAG, "Please grant the mandatory permissions : INTERNET and ACCESS_NETWORK_STATE, SDK could not be initialized.");
                return;
            }
            Logger.a(Logger.InternalLogLevel.ERROR, TAG, "Account id cannot be null or empty. Please provide a valid account id.");
            return;
        } catch (Exception e3) {
            com.inmobi.commons.a.a.c();
            Logger.a(Logger.InternalLogLevel.ERROR, TAG, "SDK could not be initialized; an unexpected error was encountered");
            new StringBuilder("Encountered unexpected error while initializing the SDK: ").append(e3.getMessage());
        }
        Logger.a(Logger.InternalLogLevel.DEBUG, TAG, "InMobi SDK initialized with account id: " + str);
        try {
            HashMap map = new HashMap();
            map.put("initTime", Long.valueOf(SystemClock.elapsedRealtime() - jElapsedRealtime));
            b.a();
            b.a("root", "SdkInitialized", map);
        } catch (Exception e4) {
            StringBuilder sb3 = new StringBuilder("Error in submitting telemetry event : (");
            sb3.append(e4.getMessage());
            sb3.append(")");
        }
        printGrantedPermissions();
    }

    public static void updateGDPRConsent(JSONObject jSONObject) {
        e.a(jSONObject);
    }

    public static void setApplicationMuted(boolean z) {
        com.inmobi.commons.a.a.a(z);
    }

    private static void sendDbDeletionTelemetryEvent(String str) {
        if (com.inmobi.commons.a.a.a()) {
            try {
                HashMap map = new HashMap();
                map.put("filename", str);
                map.put("description", "DB Deleted : " + str);
                b.a();
                b.a("ads", "PersistentDataCleanFail", map);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                sb.append(e.getMessage());
                sb.append(")");
            }
        }
    }

    private static boolean hasSdkVersionChanged(Context context) {
        return com.inmobi.commons.a.b.a(context) == null || !com.inmobi.commons.a.b.a(context).equals("7.2.1");
    }

    private static void printGrantedPermissions() {
        COMPONENT_SERVICE.execute(new Runnable() { // from class: com.inmobi.sdk.InMobiSdk.3
            @Override // java.lang.Runnable
            public final void run() {
                String[] strArr = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.VIBRATE", "android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR", "com.google.android.gms.permission.ACTIVITY_RECOGNITION"};
                StringBuilder sb = new StringBuilder("Permissions granted to SDK are :\nandroid.permission.INTERNET\nandroid.permission.ACCESS_NETWORK_STATE");
                for (int i = 0; i < 8; i++) {
                    String str = strArr[i];
                    if (com.inmobi.commons.core.utilities.e.a(com.inmobi.commons.a.a.b(), "ads", str)) {
                        sb.append("\n");
                        sb.append(str);
                    }
                }
                Logger.a(Logger.InternalLogLevel.DEBUG, InMobiSdk.TAG, sb.toString());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initComponents() {
        try {
            COMPONENT_SERVICE.execute(new Runnable() { // from class: com.inmobi.sdk.InMobiSdk.4
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        com.inmobi.commons.core.utilities.uid.c cVarA = com.inmobi.commons.core.utilities.uid.c.a();
                        try {
                            com.inmobi.commons.core.utilities.uid.c.c();
                            cVarA.b();
                        } catch (Exception e) {
                            new StringBuilder("SDK encountered an unexpected error while initializing the UID helper component; ").append(e.getMessage());
                        }
                        com.inmobi.commons.core.utilities.uid.c.a().b();
                        com.inmobi.commons.core.configs.b.a().b();
                        com.inmobi.rendering.a.c.a().b();
                        com.inmobi.commons.core.a.a aVarA = com.inmobi.commons.core.a.a.a();
                        com.inmobi.commons.core.a.a.b.set(false);
                        com.inmobi.commons.core.configs.b.a().a(aVarA.c, aVarA);
                        aVarA.d = aVarA.c.a;
                        aVarA.a.execute(new Runnable() { // from class: com.inmobi.commons.core.a.a.2
                            public AnonymousClass2() {
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                a.b(a.this);
                            }
                        });
                        b.a().b();
                        com.inmobi.b.a aVarA2 = com.inmobi.b.a.a();
                        com.inmobi.b.a.b.set(false);
                        e.c();
                        com.inmobi.commons.core.configs.b.a().a(aVarA2.c, aVarA2);
                        aVarA2.d = aVarA2.c.b;
                        aVarA2.a.execute(new Runnable() { // from class: com.inmobi.b.a.2
                            public AnonymousClass2() {
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                a.a(a.this);
                            }
                        });
                        com.inmobi.ads.b.a();
                        o.a().b();
                        com.inmobi.ads.c.b.d().a();
                        com.inmobi.ads.c.a.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE).a();
                        AssetStore.a().b();
                    } catch (Exception e2) {
                        String unused = InMobiSdk.TAG;
                        new StringBuilder("Encountered unexpected error in starting SDK components: ").append(e2.getMessage());
                        Logger.a(Logger.InternalLogLevel.DEBUG, InMobiSdk.TAG, "SDK encountered unexpected error while starting internal components");
                    }
                }
            });
        } catch (Exception e) {
            new StringBuilder("Encountered unexpected error in starting SDK components: ").append(e.getMessage());
            Logger.a(Logger.InternalLogLevel.DEBUG, TAG, "SDK encountered unexpected error while starting internal components");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void deInitComponents() {
        try {
            COMPONENT_SERVICE.execute(new Runnable() { // from class: com.inmobi.sdk.InMobiSdk.5
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        com.inmobi.commons.core.configs.b.a().c();
                        b bVarA = b.a();
                        b.b.set(true);
                        bVarA.a.execute(new Runnable() { // from class: com.inmobi.commons.core.e.b.2
                            public AnonymousClass2() {
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                if (b.this.j != null) {
                                    b.this.j.a();
                                    b.c(b.this);
                                }
                            }
                        });
                        com.inmobi.b.a aVarA = com.inmobi.b.a.a();
                        com.inmobi.b.a.b.set(true);
                        aVarA.a.execute(new Runnable() { // from class: com.inmobi.b.a.3
                            public AnonymousClass3() {
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                if (a.this.i != null) {
                                    a.this.i.a();
                                    a.c(a.this);
                                }
                            }
                        });
                        o.a().c();
                        com.inmobi.ads.c.b.d().b();
                        com.inmobi.ads.c.a.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE).b();
                        AssetStore assetStoreA = AssetStore.a();
                        assetStoreA.d.set(true);
                        assetStoreA.c();
                    } catch (Exception e) {
                        String unused = InMobiSdk.TAG;
                        new StringBuilder("Encountered unexpected error in stopping SDK components; ").append(e.getMessage());
                        Logger.a(Logger.InternalLogLevel.ERROR, InMobiSdk.TAG, "SDK encountered unexpected error while stopping internal components");
                    }
                }
            });
        } catch (Exception e) {
            new StringBuilder("Encountered unexpected error in stopping SDK components; ").append(e.getMessage());
            Logger.a(Logger.InternalLogLevel.ERROR, TAG, "SDK encountered unexpected error while stopping internal components");
        }
    }

    private static void resetMediaCache(final Context context) {
        final File fileA = com.inmobi.commons.a.a.a(context);
        COMPONENT_SERVICE.execute(new Runnable() { // from class: com.inmobi.sdk.InMobiSdk.6
            @Override // java.lang.Runnable
            public final void run() {
                com.inmobi.commons.a.a.a(fileA);
                com.inmobi.commons.a.a.b(context);
            }
        });
        if (fileA.mkdir()) {
            return;
        }
        fileA.isDirectory();
    }

    /* JADX INFO: renamed from: com.inmobi.sdk.InMobiSdk$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] a = new int[LogLevel.values().length];

        static {
            try {
                a[LogLevel.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[LogLevel.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[LogLevel.DEBUG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static void setLogLevel(LogLevel logLevel) {
        int i = AnonymousClass7.a[logLevel.ordinal()];
        if (i == 1) {
            Logger.a(Logger.InternalLogLevel.NONE);
        } else if (i == 2) {
            Logger.a(Logger.InternalLogLevel.ERROR);
        } else {
            if (i != 3) {
                return;
            }
            Logger.a(Logger.InternalLogLevel.DEBUG);
        }
    }

    public static void setAge(int i) {
        g.a(i);
    }

    public static void setAgeGroup(AgeGroup ageGroup) {
        g.a(ageGroup.toString().toLowerCase(Locale.ENGLISH));
    }

    public static void setAreaCode(String str) {
        g.b(str);
    }

    public static void setPostalCode(String str) {
        g.c(str);
    }

    public static void setLocationWithCityStateCountry(String str, String str2, String str3) {
        g.d(str);
        g.e(str2);
        g.f(str3);
    }

    public static void setYearOfBirth(int i) {
        g.b(i);
    }

    public static void setGender(Gender gender) {
        g.g(gender.toString().toLowerCase(Locale.ENGLISH));
    }

    public static void setEducation(Education education) {
        g.h(education.toString().toLowerCase(Locale.ENGLISH));
    }

    public static void setLanguage(String str) {
        g.i(str);
    }

    public static void setInterests(String str) {
        g.j(str);
    }

    public static void setLocation(Location location) {
        g.a(location);
    }
}
