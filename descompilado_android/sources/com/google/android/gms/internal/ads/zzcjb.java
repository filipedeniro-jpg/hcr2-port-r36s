package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcjb implements zzcfo<zzcvs, zzcgt> {
    private final zzcgw zzfsj;

    @GuardedBy("this")
    private final Map<String, zzcfl<zzcvs, zzcgt>> zzfzc = new HashMap();

    public zzcjb(zzcgw zzcgwVar) {
        this.zzfsj = zzcgwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfo
    public final zzcfl<zzcvs, zzcgt> zzd(String str, JSONObject jSONObject) throws zzcvr {
        synchronized (this) {
            zzcfl<zzcvs, zzcgt> zzcflVar = this.zzfzc.get(str);
            if (zzcflVar == null) {
                zzcvs zzcvsVarZze = this.zzfsj.zze(str, jSONObject);
                if (zzcvsVarZze == null) {
                    return null;
                }
                zzcflVar = new zzcfl<>(zzcvsVarZze, new zzcgt(), str);
                this.zzfzc.put(str, zzcflVar);
            }
            return zzcflVar;
        }
    }
}
