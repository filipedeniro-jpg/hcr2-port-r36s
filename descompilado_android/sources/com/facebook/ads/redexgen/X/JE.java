package com.facebook.ads.redexgen.X;

import android.os.Build;
import com.facebook.ads.internal.jni.AbiArchitecture;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public enum JE {
    X86_32("x86", false, "x8&"),
    X86_64("x86_64", false, "x86_64"),
    ARM_32("armeabi-v7a", false, "arm"),
    ARM_64("arm64-v8a", false, "arm64"),
    EXT_X86_32("x86", true, "x86"),
    EXT_X86_64("x86_64", true, "x86_64"),
    EXT_ARM_32("armeabi-v7a", true, "arm"),
    EXT_ARM_64("arm64-v8a", true, "arm64");

    private final String B;
    private final boolean C;
    private final String D;

    JE(String androidArchDirName, boolean external, String shortName) {
        this.B = androidArchDirName;
        this.C = external;
        this.D = shortName;
    }

    public final String A() {
        return this.B;
    }

    /* JADX INFO: renamed from: B, reason: collision with other method in class */
    public final String m257B() {
        return this.D;
    }

    public static List<JE> B() {
        List<AbiArchitecture> abis = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 21) {
            for (String str : Build.SUPPORTED_ABIS) {
                for (JE je : values()) {
                    if (je.A().equals(str)) {
                        abis.add(je);
                        break;
                    }
                }
            }
        }
        return abis;
    }

    public final boolean C() {
        return this.C;
    }
}
