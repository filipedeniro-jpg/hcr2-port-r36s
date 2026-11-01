package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4p, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02404p {
    private static final String J = C02404p.class.getSimpleName();
    private static final AtomicBoolean K = new AtomicBoolean();
    private static HandlerC02414q L;
    private final HandlerThread B = new HandlerThread("bd_bm_signal_manager_thread_handler", 0);
    private final EnumC02053g C;
    private final C02354k D;
    private final Map<Integer, C02113m> E;
    private final Context F;
    private C02314g G;
    private final List<EnumC02274c> H;
    private C02304f I;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.facebook.ads.redexgen.X.4q] */
    public C02404p(AnonymousClass56 baseBdConfig, Context context, Map<Integer, C02113m> map) {
        this.E = map;
        this.D = new C02354k(baseBdConfig, map);
        this.B.start();
        final Looper looper = this.B.getLooper();
        L = new Handler(looper) { // from class: com.facebook.ads.redexgen.X.4q
            @Override // android.os.Handler
            @SuppressLint({"CatchGeneralException", "BadMethodUse-android.util.Log.e"})
            public final void handleMessage(Message msg) {
                try {
                    switch (C02474w.B[EnumC02424r.values()[msg.what].ordinal()]) {
                        case 1:
                            MotionEvent motionEvent = (MotionEvent) msg.obj;
                            if (motionEvent != null) {
                                this.B.I.A(motionEvent);
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                } catch (Exception e) {
                    Log.e(C02404p.J, "Exception while handling signal", e);
                }
                Log.e(C02404p.J, "Exception while handling signal", e);
            }
        };
        this.F = context;
        this.H = new ArrayList();
        this.C = baseBdConfig.B();
    }

    public final void A() {
        Set<Integer> biometricSignalIds = this.E.keySet();
        Iterator<Integer> it = biometricSignalIds.iterator();
        while (it.hasNext()) {
            switch (it.next().intValue()) {
                case 10800:
                    this.I = new C02304f(this.F, this.D, this.C);
                    K.compareAndSet(false, true);
                    break;
                case 10810:
                    this.H.add(EnumC02274c.ACCELEROMETER_SENSOR);
                    break;
                case 10811:
                    this.H.add(EnumC02274c.GYROSCOPE_SENSOR);
                    break;
                case 10812:
                    this.H.add(EnumC02274c.MAGNETOMETER_SENSOR);
                    break;
                case 10813:
                    this.H.add(EnumC02274c.GEOMAGNETIC_ROTATION_SENSOR);
                    break;
                case 10814:
                    this.H.add(EnumC02274c.ORIENTATION_SENSOR);
                    break;
                case 10815:
                    this.H.add(EnumC02274c.PROXIMITY_SENSOR);
                    break;
                case 10816:
                    this.H.add(EnumC02274c.STEP_DETECTOR_SENSOR);
                    break;
                case 10817:
                    this.H.add(EnumC02274c.LIGHT_SENSOR);
                    break;
                case 10818:
                    this.H.add(EnumC02274c.BAROMETER_SENSOR);
                    break;
                case 10819:
                    this.H.add(EnumC02274c.AMBIENT_TEMPERATURE_SENSOR);
                    break;
                case 10820:
                    this.H.add(EnumC02274c.RELATIVE_HUMIDITY_SENSOR);
                    break;
            }
        }
        this.G = new C02314g(this.F, this.D, this.C);
        this.G.A(this.H);
    }

    public final void B() {
        K.compareAndSet(true, false);
        if (this.G != null) {
            this.G.B(this.H);
        }
    }

    public static void D(MotionEvent event) {
        if (!K.get()) {
            return;
        }
        Message message = new Message();
        message.what = EnumC02424r.TOUCH_EVENT.ordinal();
        message.obj = event;
        L.sendMessage(message);
    }
}
