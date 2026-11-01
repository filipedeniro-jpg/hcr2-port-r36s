package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaeg implements zzaef {
    private final /* synthetic */ zzaxf zzcxs;

    zzaeg(zzaed zzaedVar, zzaxf zzaxfVar) {
        this.zzcxs = zzaxfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaef
    public final void zzc(JSONObject jSONObject) {
        this.zzcxs.set(jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzaef
    public final void onFailure(@Nullable String str) {
        this.zzcxs.setException(new zzahw(str));
    }
}
