package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbwo {
    private final Executor zzfbm;
    private final zzcvk zzffn;
    private final zzbyr zzfmy;
    private final Context zzlk;

    public zzbwo(Context context, zzcvk zzcvkVar, Executor executor, zzbyr zzbyrVar) {
        this.zzlk = context;
        this.zzffn = zzcvkVar;
        this.zzfbm = executor;
        this.zzfmy = zzbyrVar;
    }

    public final zzdcp<zzbbc> zzm(final JSONObject jSONObject) {
        return zzdcf.zzb(zzdcf.zzb(zzdcf.zzah(null), new zzdbq(this) { // from class: com.google.android.gms.internal.ads.zzbwp
            private final zzbwo zzfop;

            {
                this.zzfop = this;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfop.zzq(obj);
            }
        }, this.zzfbm), new zzdbq(this, jSONObject) { // from class: com.google.android.gms.internal.ads.zzbwn
            private final JSONObject zzfbh;
            private final zzbwo zzfop;

            {
                this.zzfop = this;
                this.zzfbh = jSONObject;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfop.zza(this.zzfbh, (zzbbc) obj);
            }
        }, this.zzfbm);
    }

    public final zzdcp<zzbbc> zzp(final String str, final String str2) {
        return zzdcf.zzb(zzdcf.zzah(null), new zzdbq(this, str, str2) { // from class: com.google.android.gms.internal.ads.zzbwq
            private final String zzcyl;
            private final String zzdbj;
            private final zzbwo zzfop;

            {
                this.zzfop = this;
                this.zzcyl = str;
                this.zzdbj = str2;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfop.zza(this.zzcyl, this.zzdbj, obj);
            }
        }, this.zzfbm);
    }

    private final void zzk(zzbbc zzbbcVar) {
        zzbbcVar.zza("/video", zzadk.zzcxe);
        zzbbcVar.zza("/videoMeta", zzadk.zzcxf);
        zzbbcVar.zza("/precache", new zzbam());
        zzbbcVar.zza("/delayPageLoaded", zzadk.zzcxi);
        zzbbcVar.zza("/instrument", zzadk.zzcxg);
        zzbbcVar.zza("/log", zzadk.zzcwz);
        zzbbcVar.zza("/videoClicked", zzadk.zzcxa);
        zzbbcVar.zzyy().zzau(true);
        zzbbcVar.zza("/click", zzadk.zzcwv);
        if (this.zzffn.zzdjz != null) {
            zzbbcVar.zza("/open", new zzaeb(null, null));
        }
    }

    final /* synthetic */ zzdcp zzq(Object obj) throws Exception {
        zzbbc zzbbcVarZza = this.zzfmy.zza(zztw.zzg(this.zzlk), false);
        final zzaxc zzaxcVarZzl = zzaxc.zzl(zzbbcVarZza);
        zzk(zzbbcVarZza);
        zzbbcVarZza.zzyy().zza(new zzbco(zzaxcVarZzl) { // from class: com.google.android.gms.internal.ads.zzbws
            private final zzaxc zzeew;

            {
                this.zzeew = zzaxcVarZzl;
            }

            @Override // com.google.android.gms.internal.ads.zzbco
            public final void zzrb() {
                this.zzeew.zzwl();
            }
        });
        zzbbcVarZza.loadUrl((String) zzuo.zzoj().zzd(zzyt.zzcnv));
        return zzaxcVarZzl;
    }

    final /* synthetic */ zzdcp zza(String str, String str2, Object obj) throws Exception {
        final zzbbc zzbbcVarZza = this.zzfmy.zza(zztw.zzg(this.zzlk), false);
        final zzaxc zzaxcVarZzl = zzaxc.zzl(zzbbcVarZza);
        zzk(zzbbcVarZza);
        if (this.zzffn.zzdjz != null) {
            zzbbcVarZza.zza(zzbct.zzaap());
        } else {
            zzbbcVarZza.zza(zzbct.zzaao());
        }
        zzbbcVarZza.zzyy().zza(new zzbcp(this, zzbbcVarZza, zzaxcVarZzl) { // from class: com.google.android.gms.internal.ads.zzbwr
            private final zzbwo zzfop;
            private final zzbbc zzfoq;
            private final zzaxc zzfor;

            {
                this.zzfop = this;
                this.zzfoq = zzbbcVarZza;
                this.zzfor = zzaxcVarZzl;
            }

            @Override // com.google.android.gms.internal.ads.zzbcp
            public final void zzab(boolean z) {
                this.zzfop.zza(this.zzfoq, this.zzfor, z);
            }
        });
        zzbbcVarZza.zzb(str, str2, null);
        return zzaxcVarZzl;
    }

    final /* synthetic */ void zza(zzbbc zzbbcVar, zzaxc zzaxcVar, boolean z) {
        if (this.zzffn.zzgjr != null && zzbbcVar.zzxj() != null) {
            zzbbcVar.zzxj().zzb(this.zzffn.zzgjr);
        }
        zzaxcVar.zzwl();
    }

    final /* synthetic */ zzdcp zza(JSONObject jSONObject, final zzbbc zzbbcVar) throws Exception {
        final zzaxc zzaxcVarZzl = zzaxc.zzl(zzbbcVar);
        if (this.zzffn.zzdjz != null) {
            zzbbcVar.zza(zzbct.zzaap());
        } else {
            zzbbcVar.zza(zzbct.zzaao());
        }
        zzbbcVar.zzyy().zza(new zzbcp(this, zzbbcVar, zzaxcVarZzl) { // from class: com.google.android.gms.internal.ads.zzbwu
            private final zzbwo zzfop;
            private final zzbbc zzfoq;
            private final zzaxc zzfor;

            {
                this.zzfop = this;
                this.zzfoq = zzbbcVar;
                this.zzfor = zzaxcVarZzl;
            }

            @Override // com.google.android.gms.internal.ads.zzbcp
            public final void zzab(boolean z) {
                this.zzfop.zzb(this.zzfoq, this.zzfor, z);
            }
        });
        zzbbcVar.zzb("google.afma.nativeAds.renderVideo", jSONObject);
        return zzaxcVarZzl;
    }

    final /* synthetic */ void zzb(zzbbc zzbbcVar, zzaxc zzaxcVar, boolean z) {
        if (this.zzffn.zzgjr != null && zzbbcVar.zzxj() != null) {
            zzbbcVar.zzxj().zzb(this.zzffn.zzgjr);
        }
        zzaxcVar.zzwl();
    }
}
