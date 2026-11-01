package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.AnyThread;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.view.MotionEvent;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Iy, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@SuppressLint({"BadMethodUse-java.util.concurrent.Executors.newCachedThreadPool", "StaticFieldLeak", "POSSIBLE_VARIABLE_NAME_TYPO"})
public final class C0611Iy {
    private static final AtomicReference<AnonymousClass55> E = new AtomicReference<>();
    private static final AtomicBoolean F = new AtomicBoolean();

    @Nullable
    private static C0611Iy G;
    private final J0 B;
    private final J2 C;
    private final Context D;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.facebook.ads.redexgen.X.57] */
    @WorkerThread
    private C0611Iy(Context context) {
        this.B = new J0(context);
        this.C = new J2(context);
        this.D = context;
        F.set(J5.K(this.D));
        if (F.get()) {
            AnonymousClass55 anonymousClass55B = AnonymousClass55.B(this.D, new Object() { // from class: com.facebook.ads.redexgen.X.57
                private EnumC02053g B;
                private J1 C;
                private InterfaceC0612Iz D;
                private int E;
                private Map<Integer, String> F;
                private HashMap<Integer, Integer> G;
                private int H;
                private double I;
                private int J;
                private boolean K;
                private String L;
                private int M;

                public final AnonymousClass56 A() {
                    AnonymousClass56 anonymousClass56 = new AnonymousClass56();
                    anonymousClass56.K = this.K;
                    anonymousClass56.J = this.J;
                    anonymousClass56.G = this.G;
                    anonymousClass56.H = this.H;
                    anonymousClass56.E = this.E;
                    anonymousClass56.M = this.M;
                    anonymousClass56.L = this.L;
                    anonymousClass56.D = this.D;
                    anonymousClass56.C = this.C;
                    anonymousClass56.I = this.I;
                    anonymousClass56.B = this.B;
                    anonymousClass56.F = this.F;
                    return anonymousClass56;
                }

                public final AnonymousClass57 B(J1 bdOperationalLoggingDelegate) {
                    this.C = bdOperationalLoggingDelegate;
                    return this;
                }

                public final AnonymousClass57 C(EnumC02053g bdIntegrationSite) {
                    this.B = bdIntegrationSite;
                    return this;
                }

                public final AnonymousClass57 D(InterfaceC0612Iz bdSignalDataLoggingDelegate) {
                    this.D = bdSignalDataLoggingDelegate;
                    return this;
                }

                public final AnonymousClass57 E(int biometricSignalCircularBufferLength) {
                    this.E = biometricSignalCircularBufferLength;
                    return this;
                }

                public final AnonymousClass57 F(Map bundledSignalsConfig) {
                    this.F = bundledSignalsConfig;
                    return this;
                }

                public final AnonymousClass57 G(HashMap<Integer, Integer> circularBufferLengthMap) {
                    this.G = circularBufferLengthMap;
                    return this;
                }

                public final AnonymousClass57 H(int dynamicSignalCircularBufferLength) {
                    this.H = dynamicSignalCircularBufferLength;
                    return this;
                }

                public final AnonymousClass57 I(double intervalExecutionOperationalLoggingSamplingRate) {
                    this.I = intervalExecutionOperationalLoggingSamplingRate;
                    return this;
                }

                public final AnonymousClass57 J(int intervalModeDurationSec) {
                    this.J = intervalModeDurationSec;
                    return this;
                }

                public final AnonymousClass57 K(boolean isIntervalModeEnabled) {
                    this.K = isIntervalModeEnabled;
                    return this;
                }

                public final AnonymousClass57 L(String signalConfig) {
                    this.L = signalConfig;
                    return this;
                }

                public final AnonymousClass57 M(int staticSignalCircularBufferLength) {
                    this.M = staticSignalCircularBufferLength;
                    return this;
                }
            }.K(true).J(J5.F(this.D)).E(J5.B(this.D)).H(J5.E(this.D)).M(J5.J(this.D)).G(J5.D()).D(this.B).B(this.C).I(J5.G(this.D)).C(EnumC02053g.OFFSITE).L(J5.I(this.D)).F(J5.C(this.D)).A());
            E.compareAndSet(null, anonymousClass55B);
            anonymousClass55B.B(J5.H(this.D));
        }
    }

    @SuppressLint({"CatchGeneralException"})
    @AnyThread
    public final void A(String response) {
        AnonymousClass55 anonymousClass55 = E.get();
        if (anonymousClass55 == null || !J5.K(this.D)) {
            return;
        }
        LD.G.execute(new RunnableC0610Ix(this, anonymousClass55, response));
    }

    @WorkerThread
    @SuppressLint({"CatchGeneralException"})
    @Nullable
    public static synchronized C0611Iy B(Context context) {
        C0611Iy c0611Iy;
        try {
            if (G == null) {
                G = new C0611Iy(context);
            }
            c0611Iy = G;
        } catch (Throwable th) {
            C0585Hx.D(context, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.BB, new C0583Hv(th));
            c0611Iy = null;
        }
        return c0611Iy;
    }

    @AnyThread
    public static void C(Context context) {
        LD.G.execute(new RunnableC0609Iw(context));
    }

    @SuppressLint({"CatchGeneralException", "BadMethodUse-android.util.Log.e"})
    public static void D(@Nullable Activity activity, MotionEvent ev) {
        try {
            if (F.get() && E.get() != null) {
                AnonymousClass55.C(ev);
            }
        } catch (Throwable th) {
            C0585Hx.E(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.BB, new C0583Hv(th));
        }
    }
}
