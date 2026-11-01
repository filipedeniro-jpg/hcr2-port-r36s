package com.inmobi.ads;

import android.util.Base64;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: BidManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r {
    private static final String d = "r";
    public final byte[] b = com.inmobi.commons.core.utilities.a.b.a();
    public byte[] a = com.inmobi.commons.core.utilities.a.b.a(16);
    public final byte[] c = com.inmobi.commons.core.utilities.a.b.a(8);

    r() {
    }

    @Nullable
    final String a(String str, String str2) {
        try {
            byte[] bArrA = com.inmobi.commons.core.utilities.a.b.a(this.b);
            return new String(Base64.encode(com.inmobi.commons.core.utilities.a.b.a(com.inmobi.commons.core.utilities.a.b.a(com.inmobi.commons.core.utilities.a.b.a(bArrA, com.inmobi.commons.core.utilities.a.b.a(this.c)), com.inmobi.commons.core.utilities.a.b.a(this.a)), str2, str), 8));
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    final String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return com.inmobi.commons.core.network.d.a(com.inmobi.commons.core.utilities.a.b.a(Base64.decode(str, 0), this.b, this.a));
        } catch (Exception unused) {
            return null;
        }
    }
}
