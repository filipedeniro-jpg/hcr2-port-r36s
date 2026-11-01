package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgd implements zzbgc {
    private zzato zzdqz;

    public zzbgd(zzato zzatoVar) {
        this.zzdqz = zzatoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbgc
    public final void zzk(Map<String, String> map) {
        this.zzdqz.zzai(Boolean.parseBoolean(map.get("content_vertical_opted_out")));
    }
}
