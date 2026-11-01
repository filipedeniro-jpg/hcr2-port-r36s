package com.facebook.ads.redexgen.X;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class IK {
    private static SensorEventListener C;
    private static volatile float[] D;
    private static SensorEventListener G;
    private static volatile float[] H;
    private static SensorManager K = null;
    private static Sensor B = null;
    private static Sensor F = null;
    private static Map<String, String> E = new ConcurrentHashMap();
    private static String[] J = {"x", "y", "z"};
    private static final AtomicReference<String> I = new AtomicReference<>();

    /* JADX WARN: Failed to parse debug info
    java.lang.ArrayIndexOutOfBoundsException: Index 6 out of bounds for length 4
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.startVar(DebugInfoParser.java:203)
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.process(DebugInfoParser.java:125)
    	at jadx.plugins.input.dex.sections.DexCodeReader.getDebugInfo(DexCodeReader.java:122)
    	at jadx.core.dex.nodes.MethodNode.getDebugInfo(MethodNode.java:656)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoAttachVisitor.visit(DebugInfoAttachVisitor.java:38)
     */
    private static void J(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        String strValueOf = String.valueOf(((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()));
        I.set(strValueOf);
        E.put("free_space", strValueOf);
    }

    private IK() {
    }

    public static Map<String, String> F() {
        Map<String, String> currentAnalogInfo = new HashMap<>();
        currentAnalogInfo.putAll(E);
        L(currentAnalogInfo);
        return currentAnalogInfo;
    }

    @Nullable
    public static String G() {
        return I.get();
    }

    /* JADX WARN: Code duplicated, block: B:12:0x002e A[Catch: all -> 0x0070, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0010, B:7:0x001d, B:9:0x0021, B:10:0x002a, B:12:0x002e, B:13:0x0037, B:15:0x003b, B:17:0x0047, B:18:0x0051, B:20:0x0055, B:22:0x0061), top: B:29:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:15:0x003b A[Catch: all -> 0x0070, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0010, B:7:0x001d, B:9:0x0021, B:10:0x002a, B:12:0x002e, B:13:0x0037, B:15:0x003b, B:17:0x0047, B:18:0x0051, B:20:0x0055, B:22:0x0061), top: B:29:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:17:0x0047 A[Catch: all -> 0x0070, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0010, B:7:0x001d, B:9:0x0021, B:10:0x002a, B:12:0x002e, B:13:0x0037, B:15:0x003b, B:17:0x0047, B:18:0x0051, B:20:0x0055, B:22:0x0061), top: B:29:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:20:0x0055 A[Catch: all -> 0x0070, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0010, B:7:0x001d, B:9:0x0021, B:10:0x002a, B:12:0x002e, B:13:0x0037, B:15:0x003b, B:17:0x0047, B:18:0x0051, B:20:0x0055, B:22:0x0061), top: B:29:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:22:0x0061 A[Catch: all -> 0x0070, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0010, B:7:0x001d, B:9:0x0021, B:10:0x002a, B:12:0x002e, B:13:0x0037, B:15:0x003b, B:17:0x0047, B:18:0x0051, B:20:0x0055, B:22:0x0061), top: B:29:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:7:0x001d A[Catch: all -> 0x0070, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0010, B:7:0x001d, B:9:0x0021, B:10:0x002a, B:12:0x002e, B:13:0x0037, B:15:0x003b, B:17:0x0047, B:18:0x0051, B:20:0x0055, B:22:0x0061), top: B:29:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:9:0x0021 A[Catch: all -> 0x0070, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0010, B:7:0x001d, B:9:0x0021, B:10:0x002a, B:12:0x002e, B:13:0x0037, B:15:0x003b, B:17:0x0047, B:18:0x0051, B:20:0x0055, B:22:0x0061), top: B:29:0x0003 }] */
    public static synchronized void H(Context context) {
        K(context);
        J(context);
        I(context);
        if (K == null) {
            K = (SensorManager) context.getSystemService("sensor");
            if (K != null) {
                if (B == null) {
                    B = K.getDefaultSensor(1);
                }
                if (F == null) {
                    F = K.getDefaultSensor(4);
                }
                if (C == null) {
                    C = new II();
                    if (B != null) {
                        K.registerListener(C, B, 3);
                    }
                }
                if (G == null) {
                    G = new IJ();
                    if (F != null) {
                        K.registerListener(G, F, 3);
                    }
                }
            }
        } else {
            if (B == null) {
                B = K.getDefaultSensor(1);
            }
            if (F == null) {
                F = K.getDefaultSensor(4);
            }
            if (C == null) {
                C = new II();
                if (B != null) {
                    K.registerListener(C, B, 3);
                }
            }
            if (G == null) {
                G = new IJ();
                if (F != null) {
                    K.registerListener(G, F, 3);
                }
            }
        }
    }

    @WorkerThread
    private static void I(Context context) {
        try {
            Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (intentRegisterReceiver != null) {
                int intExtra = intentRegisterReceiver.getIntExtra("level", -1);
                int intExtra2 = intentRegisterReceiver.getIntExtra("scale", -1);
                int level = intentRegisterReceiver.getIntExtra("status", -1);
                int scale = (level == 2 || level == 5) ? 1 : 0;
                float f = 0.0f;
                if (intExtra2 > 0) {
                    f = (intExtra / intExtra2) * 100.0f;
                }
                E.put("battery", String.valueOf(f));
                E.put("charging", scale != 0 ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
        } catch (IllegalArgumentException e) {
            C0585Hx.D(context.getApplicationContext(), MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.KB, new C0583Hv(e));
        }
    }

    private static void K(Context context) {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(mi);
        E.put("available_memory", String.valueOf(mi.availMem));
        if (Build.VERSION.SDK_INT >= 16) {
            E.put("total_memory", String.valueOf(mi.totalMem));
        }
    }

    private static void L(Map<String, String> map) {
        float[] currentGyroscopeValues = D;
        float[] fArr = H;
        if (currentGyroscopeValues != null) {
            int iMin = Math.min(J.length, currentGyroscopeValues.length);
            for (int i = 0; i < iMin; i++) {
                map.put("accelerometer_" + J[i], String.valueOf(currentGyroscopeValues[i]));
            }
        }
        if (fArr != null) {
            int iMin2 = Math.min(J.length, fArr.length);
            for (int i2 = 0; i2 < iMin2; i2++) {
                map.put("rotation_" + J[i2], String.valueOf(fArr[i2]));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void M() {
        if (K != null) {
            K.unregisterListener(C);
        }
        C = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void N() {
        if (K != null) {
            K.unregisterListener(G);
        }
        G = null;
    }
}
