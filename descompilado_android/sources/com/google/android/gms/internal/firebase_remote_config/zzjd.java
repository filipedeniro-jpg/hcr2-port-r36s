package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
final class zzjd implements zzio {
    private final int flags;
    private final String info;
    private final Object[] zzvs;
    private final zziq zzvv;

    zzjd(zziq zziqVar, String str, Object[] objArr) {
        this.zzvv = zziqVar;
        this.info = str;
        this.zzvs = objArr;
        char cCharAt = str.charAt(0);
        if (cCharAt < 55296) {
            this.flags = cCharAt;
            return;
        }
        int i = cCharAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char cCharAt2 = str.charAt(i3);
            if (cCharAt2 < 55296) {
                this.flags = i | (cCharAt2 << i2);
                return;
            } else {
                i |= (cCharAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            }
        }
    }

    final String zzij() {
        return this.info;
    }

    final Object[] zzik() {
        return this.zzvs;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzio
    public final zziq zzid() {
        return this.zzvv;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzio
    public final int zzib() {
        return (this.flags & 1) == 1 ? zzhi.zze.zztu : zzhi.zze.zztv;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzio
    public final boolean zzic() {
        return (this.flags & 2) == 2;
    }
}
