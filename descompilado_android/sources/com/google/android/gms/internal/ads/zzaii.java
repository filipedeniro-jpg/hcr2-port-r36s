package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzaii<I, O> implements zzahv<I, O> {
    private final zzagt zzdar;
    private final zzahx<O> zzdav;
    private final zzaia<I> zzdaw;
    private final String zzdax;

    zzaii(zzagt zzagtVar, String str, zzaia<I> zzaiaVar, zzahx<O> zzahxVar) {
        this.zzdar = zzagtVar;
        this.zzdax = str;
        this.zzdaw = zzaiaVar;
        this.zzdav = zzahxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzahv
    public final zzdcp<O> zzi(I i) {
        zzaxf zzaxfVar = new zzaxf();
        zzahk zzahkVarZzb = this.zzdar.zzb((zzdf) null);
        zzahkVarZzb.zza(new zzaih(this, zzahkVarZzb, i, zzaxfVar), new zzaik(this, zzaxfVar, zzahkVarZzb));
        return zzaxfVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzahk zzahkVar, zzahr zzahrVar, I i, zzaxf<O> zzaxfVar) {
        try {
            com.google.android.gms.ads.internal.zzp.zzjy();
            String strZzvi = zzatv.zzvi();
            zzadk.zzcxh.zza(strZzvi, new zzaij(this, zzahkVar, zzaxfVar));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", strZzvi);
            jSONObject.put("args", this.zzdaw.zzj(i));
            zzahrVar.zzb(this.zzdax, jSONObject);
        } catch (Exception e) {
            try {
                zzaxfVar.setException(e);
                zzatm.zzc("Unable to invokeJavascript", e);
            } finally {
                zzahkVar.release();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdbq
    public final zzdcp<O> zzf(@Nullable I i) throws Exception {
        return zzi(i);
    }
}
