package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4k, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02354k {
    private static final String G = C02354k.class.getSimpleName();
    private final AnonymousClass56 C;
    private final Map<Integer, C02113m> D;
    private HandlerC02364l E;
    private final AtomicBoolean F = new AtomicBoolean();
    private final HandlerThread B = new HandlerThread("bd_bm_value_thread_handler", 10);

    /* JADX WARN: Type inference failed for: r1v1, types: [com.facebook.ads.redexgen.X.4l] */
    public C02354k(AnonymousClass56 baseBdConfig, Map<Integer, C02113m> map) {
        this.C = baseBdConfig;
        this.D = map;
        this.B.start();
        final Looper looper = this.B.getLooper();
        this.E = new Handler(looper) { // from class: com.facebook.ads.redexgen.X.4l
            @Override // android.os.Handler
            @SuppressLint({"CatchGeneralException", "BadMethodUse-android.util.Log.e"})
            public final void handleMessage(Message msg) {
                try {
                    EnumC02374m enumC02374m = EnumC02374m.values()[msg.what];
                    AbstractC02043f abstractC02043f = (AbstractC02043f) msg.obj;
                    switch (C02384n.B[enumC02374m.ordinal()]) {
                        case 1:
                            C02113m bdStepDetectorSensorSignal = (C02113m) this.B.D.get(10800);
                            if (abstractC02043f != null && bdStepDetectorSensorSignal != null) {
                                this.B.E(abstractC02043f, bdStepDetectorSensorSignal);
                            }
                            break;
                        case 2:
                            C02113m c02113m = (C02113m) this.B.D.get(10810);
                            if (abstractC02043f != null && c02113m != null) {
                                this.B.E(abstractC02043f, c02113m);
                            }
                            break;
                        case 3:
                            C02113m bdStepDetectorSensorSignal2 = (C02113m) this.B.D.get(10812);
                            if (abstractC02043f != null && bdStepDetectorSensorSignal2 != null) {
                                this.B.E(abstractC02043f, bdStepDetectorSensorSignal2);
                            }
                            break;
                        case 4:
                            C02113m bdStepDetectorSensorSignal3 = (C02113m) this.B.D.get(10813);
                            if (abstractC02043f != null && bdStepDetectorSensorSignal3 != null) {
                                this.B.E(abstractC02043f, bdStepDetectorSensorSignal3);
                            }
                            break;
                        case 5:
                            C02113m bdStepDetectorSensorSignal4 = (C02113m) this.B.D.get(10814);
                            if (abstractC02043f != null && bdStepDetectorSensorSignal4 != null) {
                                this.B.E(abstractC02043f, bdStepDetectorSensorSignal4);
                            }
                            break;
                        case 6:
                            C02113m bdStepDetectorSensorSignal5 = (C02113m) this.B.D.get(10815);
                            if (abstractC02043f != null && bdStepDetectorSensorSignal5 != null) {
                                this.B.E(abstractC02043f, bdStepDetectorSensorSignal5);
                            }
                            break;
                        case 7:
                            C02113m bdStepDetectorSensorSignal6 = (C02113m) this.B.D.get(10816);
                            if (abstractC02043f != null && bdStepDetectorSensorSignal6 != null) {
                                this.B.E(abstractC02043f, bdStepDetectorSensorSignal6);
                            }
                            break;
                        case 8:
                            C02113m c02113m2 = (C02113m) this.B.D.get(10817);
                            if (abstractC02043f != null && c02113m2 != null) {
                                this.B.E(abstractC02043f, c02113m2);
                            }
                            break;
                        case 9:
                            C02113m bdAmbientTemperatureSensorSignal = (C02113m) this.B.D.get(10818);
                            if (abstractC02043f != null && bdAmbientTemperatureSensorSignal != null) {
                                this.B.E(abstractC02043f, bdAmbientTemperatureSensorSignal);
                            }
                            break;
                        case 10:
                            C02113m bdAmbientTemperatureSensorSignal2 = (C02113m) this.B.D.get(10819);
                            if (abstractC02043f != null && bdAmbientTemperatureSensorSignal2 != null) {
                                this.B.E(abstractC02043f, bdAmbientTemperatureSensorSignal2);
                            }
                            break;
                        case 11:
                            C02113m c02113m3 = (C02113m) this.B.D.get(10820);
                            if (abstractC02043f != null && c02113m3 != null) {
                                this.B.E(abstractC02043f, c02113m3);
                            }
                            break;
                    }
                } catch (Exception e) {
                    Log.e(C02354k.G, "Exception while handling BD Signal", e);
                }
            }
        };
        this.F.compareAndSet(false, true);
    }

    public final void A(AbstractC02043f signalValueTypeDef, EnumC02374m event) {
        if (!this.F.get()) {
            return;
        }
        Message message = new Message();
        message.what = event.ordinal();
        message.obj = signalValueTypeDef;
        sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.50 != com.facebook.botdetection.interval.buffer.CircularBuffer<com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef> */
    public synchronized void E(AbstractC02043f newSignalValueTypeDef, C02113m bdSignal) {
        int signalId = bdSignal.C();
        Map<Integer, AnonymousClass50<AbstractC02043f>> mapA = C02073i.B().A();
        if (mapA.containsKey(Integer.valueOf(signalId))) {
            AnonymousClass50<AbstractC02043f> anonymousClass50 = mapA.get(Integer.valueOf(signalId));
            AbstractC02043f abstractC02043fD = anonymousClass50 != null ? anonymousClass50.D() : null;
            if (anonymousClass50 == null) {
                anonymousClass50 = new AnonymousClass50<>(F(signalId));
            }
            if (!newSignalValueTypeDef.C(abstractC02043fD, bdSignal.B())) {
                anonymousClass50.A(newSignalValueTypeDef);
                mapA.put(Integer.valueOf(signalId), anonymousClass50);
                C02073i.B().B(anonymousClass50, newSignalValueTypeDef.m232D());
            }
        } else {
            AnonymousClass50<AbstractC02043f> anonymousClass51 = new AnonymousClass50<>(F(signalId));
            anonymousClass51.A(newSignalValueTypeDef);
            mapA.put(Integer.valueOf(signalId), anonymousClass51);
            C02073i.B().B(anonymousClass51, newSignalValueTypeDef.m232D());
        }
    }

    private int F(int touchSignalId) {
        return this.C.F(touchSignalId) != null ? this.C.F(touchSignalId).intValue() : this.C.D();
    }
}
