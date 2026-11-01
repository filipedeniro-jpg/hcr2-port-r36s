package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgb implements zzbgc {
    private zzato zzdqz;

    public zzbgb(zzato zzatoVar) {
        this.zzdqz = zzatoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbgc
    public final void zzk(Map<String, String> map) {
        this.zzdqz.zzah(Boolean.parseBoolean(map.get("content_url_opted_out")));
    }
}
