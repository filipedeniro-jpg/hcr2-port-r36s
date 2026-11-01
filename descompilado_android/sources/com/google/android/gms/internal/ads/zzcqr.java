package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqr implements zzcra<zzcqo> {
    private final zzarv zzbmt;
    private final zzdcs zzfnq;
    private final Context zzlk;

    public zzcqr(zzarv zzarvVar, zzdcs zzdcsVar, Context context) {
        this.zzbmt = zzarvVar;
        this.zzfnq = zzdcsVar;
        this.zzlk = context;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcqo> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcqq
            private final zzcqr zzgfn;

            {
                this.zzgfn = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzgfn.zzamc();
            }
        });
    }

    final /* synthetic */ zzcqo zzamc() throws Exception {
        Long l;
        if (!this.zzbmt.zzaa(this.zzlk)) {
            return new zzcqo(null, null, null, null, null);
        }
        String strZzad = this.zzbmt.zzad(this.zzlk);
        String str = strZzad == null ? "" : strZzad;
        String strZzae = this.zzbmt.zzae(this.zzlk);
        String str2 = strZzae == null ? "" : strZzae;
        String strZzaf = this.zzbmt.zzaf(this.zzlk);
        String str3 = strZzaf == null ? "" : strZzaf;
        String strZzag = this.zzbmt.zzag(this.zzlk);
        String str4 = strZzag == null ? "" : strZzag;
        if ("TIME_OUT".equals(str2)) {
            l = (Long) zzuo.zzoj().zzd(zzyt.zzcje);
        } else {
            l = null;
        }
        return new zzcqo(str, str2, str3, str4, l);
    }
}
