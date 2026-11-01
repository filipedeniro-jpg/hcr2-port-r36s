package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.31, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass31 implements Serializable {
    private static final long serialVersionUID = -3209129042070173126L;
    private final int B;
    private AnonymousClass31 C;
    private final List<AnonymousClass31> D;
    private final String E;

    @Nullable
    private final String F;

    public AnonymousClass31(int number, @Nullable String title, String subtitle) {
        this.D = new ArrayList();
        this.B = number;
        this.F = title;
        this.E = subtitle;
    }

    public AnonymousClass31(String subtitle) {
        this(0, null, subtitle);
    }

    public final void A(AnonymousClass31 subReason) {
        subReason.B(this);
        this.D.add(subReason);
    }

    public final List<AnonymousClass31> B() {
        return this.D;
    }

    private void B(AnonymousClass31 parentReason) {
        this.C = parentReason;
    }

    public final int C() {
        return this.B;
    }

    public final AnonymousClass31 D() {
        return this.C;
    }

    @Nullable
    public final String E() {
        return this.E;
    }

    public final String F() {
        return this.F;
    }
}
