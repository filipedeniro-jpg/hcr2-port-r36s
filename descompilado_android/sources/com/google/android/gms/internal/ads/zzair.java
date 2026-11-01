package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzair implements zzaef {
    private final zzaxf<O> zzdaz;
    private final /* synthetic */ zzaip zzdbc;

    public zzair(zzaip zzaipVar, zzaxf<O> zzaxfVar) {
        this.zzdbc = zzaipVar;
        this.zzdaz = zzaxfVar;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.gms.internal.ads.zzaef
    public final void zzc(JSONObject jSONObject) {
        try {
            this.zzdaz.set((O) this.zzdbc.zzdav.zzd(jSONObject));
        } catch (IllegalStateException unused) {
        } catch (JSONException e) {
            this.zzdaz.setException(e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaef
    public final void onFailure(@Nullable String str) {
        try {
            if (str == null) {
                this.zzdaz.setException(new zzahw());
            } else {
                this.zzdaz.setException(new zzahw(str));
            }
        } catch (IllegalStateException unused) {
        }
    }
}
