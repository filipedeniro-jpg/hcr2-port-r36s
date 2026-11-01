package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcju implements zzcfo<zzalj, zzcgu> {
    private final zzckv zzfzs;

    public zzcju(zzckv zzckvVar) {
        this.zzfzs = zzckvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfo
    public final zzcfl<zzalj, zzcgu> zzd(String str, JSONObject jSONObject) throws zzcvr {
        zzalj zzaljVarZzgc = this.zzfzs.zzgc(str);
        if (zzaljVarZzgc == null) {
            return null;
        }
        return new zzcfl<>(zzaljVarZzgc, new zzcgu(), str);
    }
}
