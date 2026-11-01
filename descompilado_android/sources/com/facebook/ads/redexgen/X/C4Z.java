package com.facebook.ads.redexgen.X;

import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4Z, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class C4Z {
    private final AnonymousClass56 B;

    public C4Z(AnonymousClass56 baseBDConfig) {
        this.B = baseBDConfig;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.50 != com.facebook.botdetection.interval.buffer.CircularBuffer<com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef> */
    public final synchronized void A(EnumC02083j tier, List<C02113m> list) {
        AbstractC02043f abstractC02043fOB;
        Map<Integer, AnonymousClass50<AbstractC02043f>> mapA = C02073i.B().A();
        for (C02113m c02113m : list) {
            C4S c4sA = c02113m.A(tier);
            if (c4sA != null && (abstractC02043fOB = c4sA.oB()) != null && abstractC02043fOB.m231B() != null) {
                if (c02113m.B().contains(EnumC02053g.DYNAMIC) && mapA.containsKey(Integer.valueOf(c02113m.C()))) {
                    AnonymousClass50<AbstractC02043f> anonymousClass50 = mapA.get(Integer.valueOf(c02113m.C()));
                    AbstractC02043f abstractC02043fD = anonymousClass50 != null ? anonymousClass50.D() : null;
                    if (anonymousClass50 == null) {
                        anonymousClass50 = new AnonymousClass50<>(B(this.B, c02113m));
                    }
                    if (abstractC02043fD == null || !abstractC02043fOB.C(abstractC02043fD, c02113m.B())) {
                        anonymousClass50.A(abstractC02043fOB);
                        mapA.put(Integer.valueOf(c02113m.C()), anonymousClass50);
                        C02073i.B().B(anonymousClass50, abstractC02043fOB.m232D());
                    }
                } else {
                    AnonymousClass50<AbstractC02043f> anonymousClass51 = new AnonymousClass50<>(B(this.B, c02113m));
                    anonymousClass51.A(abstractC02043fOB);
                    mapA.put(Integer.valueOf(c02113m.C()), anonymousClass51);
                    C02073i.B().B(anonymousClass51, abstractC02043fOB.m232D());
                }
            }
        }
    }

    private int B(AnonymousClass56 baseBDConfig, C02113m signal) {
        if (baseBDConfig.F(signal.C()) != null) {
            return baseBDConfig.F(signal.C()).intValue();
        }
        if (signal.B().contains(EnumC02053g.DYNAMIC)) {
            return baseBDConfig.G();
        }
        if (signal.B().contains(EnumC02053g.BIOMETRIC)) {
            return baseBDConfig.D();
        }
        return baseBDConfig.K();
    }
}
