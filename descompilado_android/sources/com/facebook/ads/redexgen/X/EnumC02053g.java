package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3g, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public enum EnumC02053g {
    ACTIVE(1),
    DYNAMIC(2),
    BIOMETRIC(4),
    DEPRECATED(8),
    WEB(16),
    IOS_NATIVE(32),
    ANDROID_NATIVE(64),
    EQUAL_BY_VALUE(128),
    EQUAL_BY_CONTEXT(256),
    EQUAL_BY_TIMESTAMP(512),
    SUSPICIOUS_TIER(1024),
    PARANOID_TIER(2048),
    BENIGN_COLLECT_TIER(4096),
    BUNDLE(8192),
    ONSITE(16384),
    OFFSITE(32768);

    private int B;
    private static final String S = EnumC02053g.class.getSimpleName();
    private static final Map<Integer, EnumC02053g> U = new HashMap();

    static {
        for (EnumC02053g enumC02053g : values()) {
            U.put(Integer.valueOf(enumC02053g.B), enumC02053g);
        }
    }

    EnumC02053g(int value) {
        this.B = value;
    }

    @SuppressLint({"BadMethodUse-android.util.Log.e"})
    public static EnumSet<EnumC02053g> B(int signalFlagsEncoded, Class<EnumC02053g> cls) {
        EnumSet<EnumC02053g> enumSetNoneOf = EnumSet.noneOf(cls);
        try {
            EnumC02053g[] enumC02053gArr = (EnumC02053g[]) cls.getMethod("values", new Class[0]).invoke(null, new Object[0]);
            while (signalFlagsEncoded != 0) {
                int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(signalFlagsEncoded);
                signalFlagsEncoded ^= Integer.lowestOneBit(signalFlagsEncoded);
                enumSetNoneOf.add(enumC02053gArr[iNumberOfTrailingZeros]);
            }
        } catch (IllegalAccessException e) {
            Log.e(S, "IllegalAccessException while parsing flags", e);
        } catch (NoSuchMethodException e2) {
            Log.e(S, "NoSuchMethodException while parsing flags", e2);
        } catch (InvocationTargetException e3) {
            Log.e(S, "InvocationTargetException while parsing flags", e3);
        }
        return enumSetNoneOf;
    }
}
