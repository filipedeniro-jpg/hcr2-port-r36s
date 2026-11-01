package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcho implements zzcfo<zzcvs, zzcgu> {
    private final zzcgw zzfsj;

    public zzcho(zzcgw zzcgwVar) {
        this.zzfsj = zzcgwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfo
    public final zzcfl<zzcvs, zzcgu> zzd(String str, JSONObject jSONObject) throws zzcvr {
        zzcvs zzcvsVarZze = this.zzfsj.zze(str, jSONObject);
        if (zzcvsVarZze == null) {
            return null;
        }
        return new zzcfl<>(zzcvsVarZze, new zzcgu(), str);
    }
}
