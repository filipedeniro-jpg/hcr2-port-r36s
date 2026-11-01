package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0583Hv extends Exception {

    @Nullable
    private JSONObject B;
    private int C;

    public C0583Hv(String message) {
        super(message);
        this.C = 0;
    }

    public C0583Hv(String constantPart, String info) {
        super(constantPart + '\n' + info);
        this.C = 0;
    }

    public C0583Hv(Throwable cause) {
        super(cause);
        this.C = 0;
    }

    @Nullable
    public final JSONObject A() {
        return this.B;
    }

    public final int B() {
        return this.C;
    }

    public final void C(@Nullable JSONObject additionalInfo) {
        this.B = additionalInfo;
    }

    public final void D(int level) {
        this.C = level;
    }
}
