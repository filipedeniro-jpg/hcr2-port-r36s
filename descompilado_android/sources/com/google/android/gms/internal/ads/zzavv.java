package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzavv extends zzati {
    private final String url;
    private final zzaws zzduo;

    public zzavv(Context context, String str, String str2) {
        this(str2, com.google.android.gms.ads.internal.zzp.zzjy().zzr(context, str));
    }

    private zzavv(String str, String str2) {
        this.zzduo = new zzaws(str2);
        this.url = str;
    }

    @Override // com.google.android.gms.internal.ads.zzati
    public final void zzst() {
        this.zzduo.zzei(this.url);
    }
}
