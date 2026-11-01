package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbxn {
    private final zzbbl zzblw;
    private final zzawv zzdid;
    private final zzdf zzefm;
    private final Executor zzfbm;
    private final com.google.android.gms.ads.internal.zzb zzfoe;
    private final zzbxw zzfpa = new zzbxw(null);
    private final zzaed zzfpb = new zzaed();
    private zzdcp<zzbbc> zzfpc;
    private final Context zzlk;

    zzbxn(zzbxx zzbxxVar) {
        this.zzlk = zzbxxVar.zzlk;
        this.zzfbm = zzbxxVar.zzfbm;
        this.zzefm = zzbxxVar.zzefm;
        this.zzdid = zzbxxVar.zzdid;
        this.zzfoe = zzbxxVar.zzfoe;
        this.zzblw = zzbxxVar.zzblw;
    }

    public final synchronized void zzajb() {
        this.zzfpc = zzdcf.zzb(zzbbl.zza(this.zzlk, this.zzdid, (String) zzuo.zzoj().zzd(zzyt.zzcns), this.zzefm, this.zzfoe), new zzczs(this) { // from class: com.google.android.gms.internal.ads.zzbxq
            private final zzbxn zzfpe;

            {
                this.zzfpe = this;
            }

            @Override // com.google.android.gms.internal.ads.zzczs
            public final Object apply(Object obj) {
                return this.zzfpe.zzm((zzbbc) obj);
            }
        }, this.zzfbm);
        zzaxb.zza(this.zzfpc, "NativeJavascriptExecutor.initializeEngine");
    }

    public final synchronized void destroy() {
        if (this.zzfpc == null) {
            return;
        }
        zzdcf.zza(this.zzfpc, new zzbxs(this), this.zzfbm);
        this.zzfpc = null;
    }

    public final synchronized zzdcp<JSONObject> zzc(final String str, final JSONObject jSONObject) {
        if (this.zzfpc == null) {
            return zzdcf.zzah(null);
        }
        return zzdcf.zzb(this.zzfpc, new zzdbq(this, str, jSONObject) { // from class: com.google.android.gms.internal.ads.zzbxp
            private final String zzcyl;
            private final zzbxn zzfpe;
            private final JSONObject zzfpf;

            {
                this.zzfpe = this;
                this.zzcyl = str;
                this.zzfpf = jSONObject;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfpe.zza(this.zzcyl, this.zzfpf, (zzbbc) obj);
            }
        }, this.zzfbm);
    }

    public final synchronized void zza(String str, zzadx<Object> zzadxVar) {
        if (this.zzfpc == null) {
            return;
        }
        zzdcf.zza(this.zzfpc, new zzbxr(this, str, zzadxVar), this.zzfbm);
    }

    public final synchronized void zzb(String str, zzadx<Object> zzadxVar) {
        if (this.zzfpc == null) {
            return;
        }
        zzdcf.zza(this.zzfpc, new zzbxu(this, str, zzadxVar), this.zzfbm);
    }

    public final synchronized void zza(String str, Map<String, ?> map) {
        if (this.zzfpc == null) {
            return;
        }
        zzdcf.zza(this.zzfpc, new zzbxt(this, str, map), this.zzfbm);
    }

    public final <T> void zza(WeakReference<T> weakReference, String str, zzadx<T> zzadxVar) {
        zza(str, new zzbya(this, weakReference, str, zzadxVar, null));
    }

    final /* synthetic */ zzdcp zza(String str, JSONObject jSONObject, zzbbc zzbbcVar) throws Exception {
        return this.zzfpb.zza(zzbbcVar, str, jSONObject);
    }

    final /* synthetic */ zzbbc zzm(zzbbc zzbbcVar) {
        zzbbcVar.zza("/result", this.zzfpb);
        zzbcm zzbcmVarZzyy = zzbbcVar.zzyy();
        zzbxw zzbxwVar = this.zzfpa;
        zzbcmVarZzyy.zza(null, zzbxwVar, zzbxwVar, zzbxwVar, zzbxwVar, false, null, new com.google.android.gms.ads.internal.zza(this.zzlk, null, null), null, null);
        return zzbbcVar;
    }
}
