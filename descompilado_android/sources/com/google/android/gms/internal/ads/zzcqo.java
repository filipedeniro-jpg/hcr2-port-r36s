package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqo implements zzcrb<Bundle> {
    private final String zzdoy;
    private final String zzgfj;
    private final String zzgfk;
    private final String zzgfl;
    private final Long zzgfm;

    public zzcqo(String str, String str2, String str3, String str4, Long l) {
        this.zzdoy = str;
        this.zzgfj = str2;
        this.zzgfk = str3;
        this.zzgfl = str4;
        this.zzgfm = l;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        zzcvq.zza(bundle2, "gmp_app_id", this.zzdoy);
        zzcvq.zza(bundle2, "fbs_aiid", this.zzgfj);
        zzcvq.zza(bundle2, "fbs_aeid", this.zzgfk);
        zzcvq.zza(bundle2, "apm_id_origin", this.zzgfl);
        Long l = this.zzgfm;
        if (l != null) {
            bundle2.putLong("sai_timeout", l.longValue());
        }
    }
}
