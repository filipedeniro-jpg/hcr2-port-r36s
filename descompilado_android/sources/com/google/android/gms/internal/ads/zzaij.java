package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaij implements zzaef {
    private final /* synthetic */ zzaii zzdau;
    private final zzahk zzday;
    private final zzaxf<O> zzdaz;

    public zzaij(zzaii zzaiiVar, zzahk zzahkVar, zzaxf<O> zzaxfVar) {
        this.zzdau = zzaiiVar;
        this.zzday = zzahkVar;
        this.zzdaz = zzaxfVar;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0017 A[ExcHandler: JSONException -> 0x0017] */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.google.android.gms.internal.ads.zzaef
    public final void zzc(JSONObject jSONObject) {
        try {
            this.zzdaz.set((O) this.zzdau.zzdav.zzd(jSONObject));
        } catch (JSONException e) {
            this.zzdaz.setException(e);
        } catch (IllegalStateException unused) {
        } finally {
            this.zzday.release();
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
            this.zzday.release();
        } catch (IllegalStateException unused) {
        } finally {
            this.zzday.release();
        }
    }
}
