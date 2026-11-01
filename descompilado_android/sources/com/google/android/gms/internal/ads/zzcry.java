package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import java.util.concurrent.Callable;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcry implements zzcra<zzcrz> {
    private final String packageName;
    private final zzdcs zzfnq;
    private final zzaor zzggl;
    private final Context zzlk;

    public zzcry(@Nullable zzaor zzaorVar, Context context, String str, zzdcs zzdcsVar) {
        this.zzggl = zzaorVar;
        this.zzlk = context;
        this.packageName = str;
        this.zzfnq = zzdcsVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcrz> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcsb
            private final zzcry zzggn;

            {
                this.zzggn = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzggn.zzamg();
            }
        });
    }

    final /* synthetic */ zzcrz zzamg() throws Exception {
        JSONObject jSONObject = new JSONObject();
        zzaor zzaorVar = this.zzggl;
        if (zzaorVar != null) {
            zzaorVar.zza(this.zzlk, this.packageName, jSONObject);
        }
        return new zzcrz(jSONObject);
    }
}
