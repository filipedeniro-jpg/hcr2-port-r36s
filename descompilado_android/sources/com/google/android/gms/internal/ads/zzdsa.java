package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdsa implements zzdrl {
    private final int flags;
    private final String info;
    private final Object[] zzhmn;
    private final zzdrn zzhmq;

    zzdsa(zzdrn zzdrnVar, String str, Object[] objArr) {
        this.zzhmq = zzdrnVar;
        this.info = str;
        this.zzhmn = objArr;
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

    final String zzbbb() {
        return this.info;
    }

    final Object[] zzbbc() {
        return this.zzhmn;
    }

    @Override // com.google.android.gms.internal.ads.zzdrl
    public final zzdrn zzbav() {
        return this.zzhmq;
    }

    @Override // com.google.android.gms.internal.ads.zzdrl
    public final int zzbat() {
        return (this.flags & 1) == 1 ? zzdqd.zzd.zzhkp : zzdqd.zzd.zzhkq;
    }

    @Override // com.google.android.gms.internal.ads.zzdrl
    public final boolean zzbau() {
        return (this.flags & 2) == 2;
    }
}
