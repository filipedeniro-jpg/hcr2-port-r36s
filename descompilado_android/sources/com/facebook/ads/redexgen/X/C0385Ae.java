package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ae, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C0385Ae {
    private static final AW C;
    private final Object B;

    static {
        if (Build.VERSION.SDK_INT >= 19) {
            C = new C0384Ad();
        } else if (Build.VERSION.SDK_INT >= 16) {
            C = new C0381Aa();
        } else {
            C = new AZ();
        }
    }

    public C0385Ae() {
        this.B = C.GD(this);
    }

    public C0385Ae(Object provider) {
        this.B = provider;
    }

    @Nullable
    public AT A(int virtualViewId) {
        return null;
    }

    @Nullable
    public final List<AT> B(String text, int virtualViewId) {
        return null;
    }

    @Nullable
    public AT C(int focus) {
        return null;
    }

    public final Object D() {
        return this.B;
    }

    public boolean E(int virtualViewId, int action, Bundle arguments) {
        return false;
    }
}
