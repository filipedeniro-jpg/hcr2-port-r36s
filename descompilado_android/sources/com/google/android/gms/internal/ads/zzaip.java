package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaip<I, O> implements zzdbq<I, O> {
    private final zzahx<O> zzdav;
    private final zzaia<I> zzdaw;
    private final String zzdax;
    private final zzdcp<zzahr> zzdbb;

    zzaip(zzdcp<zzahr> zzdcpVar, String str, zzaia<I> zzaiaVar, zzahx<O> zzahxVar) {
        this.zzdbb = zzdcpVar;
        this.zzdax = str;
        this.zzdaw = zzaiaVar;
        this.zzdav = zzahxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdbq
    public final zzdcp<O> zzf(final I i) throws Exception {
        return zzdcf.zzb(this.zzdbb, new zzdbq(this, i) { // from class: com.google.android.gms.internal.ads.zzais
            private final zzaip zzdbd;
            private final Object zzdbe;

            {
                this.zzdbd = this;
                this.zzdbe = i;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzdbd.zza(this.zzdbe, (zzahr) obj);
            }
        }, zzawx.zzdwb);
    }

    final /* synthetic */ zzdcp zza(Object obj, zzahr zzahrVar) throws Exception {
        zzaxf zzaxfVar = new zzaxf();
        com.google.android.gms.ads.internal.zzp.zzjy();
        String strZzvi = zzatv.zzvi();
        zzadk.zzcxh.zza(strZzvi, new zzair(this, zzaxfVar));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", strZzvi);
        jSONObject.put("args", this.zzdaw.zzj(obj));
        zzahrVar.zzb(this.zzdax, jSONObject);
        return zzaxfVar;
    }
}
