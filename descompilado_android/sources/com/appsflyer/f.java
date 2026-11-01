package com.appsflyer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class f {

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private static volatile f f124;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    boolean f129;

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    private boolean f130;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final Handler f131;

    /* JADX INFO: renamed from: ͺ, reason: contains not printable characters */
    private final SensorManager f133;

    /* JADX INFO: renamed from: ॱॱ, reason: contains not printable characters */
    private static final BitSet f126 = new BitSet(6);

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private static final Handler f125 = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final Object f132 = new Object();

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private final Map<i, i> f127 = new HashMap(f126.size());

    /* JADX INFO: renamed from: ॱˊ, reason: contains not printable characters */
    private final Map<i, Map<String, Object>> f135 = new HashMap(f126.size());

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final Runnable f134 = new AnonymousClass1();

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final Runnable f128 = new AnonymousClass4();

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    final Runnable f136 = new Runnable() { // from class: com.appsflyer.f.5
        @Override // java.lang.Runnable
        public final void run() {
            synchronized (f.this.f132) {
                if (f.this.f129) {
                    f.this.f131.removeCallbacks(f.this.f128);
                    f.this.f131.removeCallbacks(f.this.f134);
                    f.this.m123();
                    f.this.f129 = false;
                }
            }
        }
    };

    static {
        f126.set(1);
        f126.set(2);
        f126.set(4);
    }

    /* JADX INFO: renamed from: com.appsflyer.f$1, reason: invalid class name */
    final class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (f.this.f132) {
                f.this.m123();
                f.this.f131.postDelayed(f.this.f128, 1800000L);
            }
        }

        AnonymousClass1() {
        }

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        static boolean m125(Context context, String str) {
            int iCheckSelfPermission = ContextCompat.checkSelfPermission(context, str);
            StringBuilder sb = new StringBuilder("is Permission Available: ");
            sb.append(str);
            sb.append("; res: ");
            sb.append(iCheckSelfPermission);
            AFLogger.afRDLog(sb.toString());
            return iCheckSelfPermission == 0;
        }
    }

    /* JADX INFO: renamed from: com.appsflyer.f$4, reason: invalid class name */
    final class AnonymousClass4 implements Runnable {

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        private static String f138;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        private static String f139;

        AnonymousClass4() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (f.this.f132) {
                f.this.m124();
                f.this.f131.postDelayed(f.this.f134, 500L);
                f.this.f129 = true;
            }
        }

        AnonymousClass4() {
        }

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        static void m126(String str) {
            f138 = str;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                if (i == 0 || i == str.length() - 1) {
                    sb.append(str.charAt(i));
                } else {
                    sb.append("*");
                }
            }
            f139 = sb.toString();
        }

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        static void m127(String str) {
            if (f138 == null) {
                m126(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY));
            }
            String str2 = f138;
            if (str2 == null || !str.contains(str2)) {
                return;
            }
            AFLogger.afInfoLog(str.replace(f138, f139));
        }
    }

    private f(@NonNull SensorManager sensorManager, Handler handler) {
        this.f133 = sensorManager;
        this.f131 = handler;
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    static f m120(Context context) {
        return m121((SensorManager) context.getApplicationContext().getSystemService("sensor"), f125);
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static f m121(SensorManager sensorManager, Handler handler) {
        if (f124 == null) {
            synchronized (f.class) {
                if (f124 == null) {
                    f124 = new f(sensorManager, handler);
                }
            }
        }
        return f124;
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m124() {
        try {
            for (Sensor sensor : this.f133.getSensorList(-1)) {
                int type = sensor.getType();
                if (type >= 0 && f126.get(type)) {
                    i iVarM141 = i.m141(sensor);
                    if (!this.f127.containsKey(iVarM141)) {
                        this.f127.put(iVarM141, iVarM141);
                    }
                    this.f133.registerListener(this.f127.get(iVarM141), sensor, 0);
                }
            }
        } catch (Throwable unused) {
        }
        this.f130 = true;
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    final void m123() {
        try {
            if (!this.f127.isEmpty()) {
                for (i iVar : this.f127.values()) {
                    this.f133.unregisterListener(iVar);
                    iVar.m147(this.f135);
                }
            }
        } catch (Throwable unused) {
        }
        this.f130 = false;
    }

    @NonNull
    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    final List<Map<String, Object>> m122() {
        synchronized (this.f132) {
            if (!this.f127.isEmpty() && this.f130) {
                Iterator<i> it = this.f127.values().iterator();
                while (it.hasNext()) {
                    it.next().m146(this.f135);
                }
            }
            if (this.f135.isEmpty()) {
                return Collections.emptyList();
            }
            return new ArrayList(this.f135.values());
        }
    }
}
