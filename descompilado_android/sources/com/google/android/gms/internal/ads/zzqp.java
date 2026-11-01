package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzqp {
    final long value;
    final int zzbqc;
    final String zzbqm;

    zzqp(long j, String str, int i) {
        this.value = j;
        this.zzbqm = str;
        this.zzbqc = i;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj != null && (obj instanceof zzqp)) {
            zzqp zzqpVar = (zzqp) obj;
            if (zzqpVar.value == this.value && zzqpVar.zzbqc == this.zzbqc) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (int) this.value;
    }
}
