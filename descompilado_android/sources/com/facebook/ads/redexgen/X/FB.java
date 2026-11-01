package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class FB {
    public static AnonymousClass86<FB> E = new AnonymousClass87(20);
    public int B;

    @Nullable
    public EX C;

    @Nullable
    public EX D;

    private FB() {
    }

    public static void B() {
        while (E.SB() != null) {
        }
    }

    public static FB C() {
        FB record = E.SB();
        return record == null ? new FB() : record;
    }

    public static void D(FB record) {
        record.B = 0;
        record.D = null;
        record.C = null;
        E.lF(record);
    }
}
