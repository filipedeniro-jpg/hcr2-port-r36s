package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3i, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02073i {

    @InterfaceC01641r
    private static C02073i C = null;
    private static int D = 0;
    private final Map<Integer, AnonymousClass50<AbstractC02043f>> B;

    private C02073i(Map<Integer, AnonymousClass50<AbstractC02043f>> map) {
        this.B = map;
    }

    public final Map<Integer, AnonymousClass50<AbstractC02043f>> A() {
        return this.B;
    }

    @SuppressLint({"UseSparseArrays"})
    public static C02073i B() {
        if (C == null) {
            C = new C02073i(Collections.synchronizedMap(new HashMap()));
        }
        return C;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.50 != com.facebook.botdetection.interval.buffer.CircularBuffer<com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef> */
    public final synchronized void B(AnonymousClass50<AbstractC02043f> anonymousClass50, int newSignalValueTypeSize) {
        int iM232D = 0;
        boolean z = false;
        if (anonymousClass50.E() == 0) {
            z = true;
            iM232D = anonymousClass50.C().m232D();
        }
        int updatedSize = newSignalValueTypeSize - iM232D;
        D = (z ? 0 : 4) + updatedSize + D;
    }

    public static synchronized float C() {
        return D / 1024;
    }
}
