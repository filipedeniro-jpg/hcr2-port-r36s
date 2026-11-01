package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcow implements zzcra<zzcox> {
    private final zzdcs zzfnq;
    private final Context zzlk;

    public zzcow(Context context, zzdcs zzdcsVar) {
        this.zzlk = context;
        this.zzfnq = zzdcsVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcox> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcoz
            private final zzcow zzget;

            {
                this.zzget = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                String strZzut;
                String strZzuv;
                String strZzlj;
                zzcow zzcowVar = this.zzget;
                com.google.android.gms.ads.internal.zzp.zzjy();
                zzqb zzqbVarZzur = com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzur();
                Bundle bundle = null;
                if (zzqbVarZzur != null && zzqbVarZzur != null && (!com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzus() || !com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzuu())) {
                    if (zzqbVarZzur.zzlv()) {
                        zzqbVarZzur.wakeup();
                    }
                    zzpv zzpvVarZzlt = zzqbVarZzur.zzlt();
                    if (zzpvVarZzlt != null) {
                        strZzut = zzpvVarZzlt.zzli();
                        strZzlj = zzpvVarZzlt.zzlj();
                        strZzuv = zzpvVarZzlt.zzlk();
                        if (strZzut != null) {
                            com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzdz(strZzut);
                        }
                        if (strZzuv != null) {
                            com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzea(strZzuv);
                        }
                    } else {
                        strZzut = com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzut();
                        strZzuv = com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzuv();
                        strZzlj = null;
                    }
                    Bundle bundle2 = new Bundle(1);
                    if (!com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzuu()) {
                        if (strZzuv == null || TextUtils.isEmpty(strZzuv)) {
                            bundle2.putString("v_fp_vertical", "no_hash");
                        } else {
                            bundle2.putString("v_fp_vertical", strZzuv);
                        }
                    }
                    if (strZzut != null && !com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzus()) {
                        bundle2.putString("fingerprint", strZzut);
                        if (!strZzut.equals(strZzlj)) {
                            bundle2.putString("v_fp", strZzlj);
                        }
                    }
                    if (!bundle2.isEmpty()) {
                        bundle = bundle2;
                    }
                }
                return new zzcox(bundle);
            }
        });
    }
}
