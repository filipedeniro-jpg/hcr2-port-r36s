package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import com.facebook.appevents.UserDataStore;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class IP {

    @Nullable
    private String C;
    private IQ D;

    @Nullable
    private String E;
    private int F = 0;
    private List<IN> B = new ArrayList();

    public IP(IQ definition, @Nullable String featureConfig, @Nullable String adReportingConfig) {
        this.D = definition;
        this.E = featureConfig;
        this.C = adReportingConfig;
    }

    public final void A(IN adCandidate) {
        this.B.add(adCandidate);
    }

    @Nullable
    public final String B() {
        return this.C;
    }

    public final long C() {
        if (this.D != null) {
            return this.D.G() + ((long) this.D.B());
        }
        return -1L;
    }

    @Nullable
    public final String D() {
        if (this.F <= 0 || this.F > this.B.size()) {
            return null;
        }
        return this.B.get(this.F - 1).B().optString(UserDataStore.CITY);
    }

    public final IQ E() {
        return this.D;
    }

    @Nullable
    public final String F() {
        return this.E;
    }

    public final IN G() {
        if (this.F >= this.B.size()) {
            return null;
        }
        this.F++;
        return this.B.get(this.F - 1);
    }

    public final int H() {
        return this.B.size();
    }

    public final boolean I() {
        return this.D == null || C0622Jj.B() > this.D.G() + ((long) this.D.B());
    }
}
