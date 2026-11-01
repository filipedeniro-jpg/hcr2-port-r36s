package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbqb extends zzbos<zzpl> implements zzpl {
    private final zzcvb zzfef;

    @GuardedBy("this")
    private Map<View, zzph> zzfhm;
    private final Context zzlk;

    public zzbqb(Context context, Set<zzbqc<zzpl>> set, zzcvb zzcvbVar) {
        super(set);
        this.zzfhm = new WeakHashMap(1);
        this.zzlk = context;
        this.zzfef = zzcvbVar;
    }

    @Override // com.google.android.gms.internal.ads.zzpl
    public final synchronized void zza(final zzpi zzpiVar) {
        zza(new zzbou(zzpiVar) { // from class: com.google.android.gms.internal.ads.zzbqe
            private final zzpi zzfho;

            {
                this.zzfho = zzpiVar;
            }

            @Override // com.google.android.gms.internal.ads.zzbou
            public final void zzp(Object obj) {
                ((zzpl) obj).zza(this.zzfho);
            }
        });
    }

    public final synchronized void zzq(View view) {
        zzph zzphVar = this.zzfhm.get(view);
        if (zzphVar == null) {
            zzphVar = new zzph(this.zzlk, view);
            zzphVar.zza(this);
            this.zzfhm.put(view, zzphVar);
        }
        if (this.zzfef != null && this.zzfef.zzdlg) {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzclt)).booleanValue()) {
                zzphVar.zzeh(((Long) zzuo.zzoj().zzd(zzyt.zzcls)).longValue());
                return;
            }
        }
        zzphVar.zzlf();
    }

    public final synchronized void zzr(View view) {
        if (this.zzfhm.containsKey(view)) {
            this.zzfhm.get(view).zzb(this);
            this.zzfhm.remove(view);
        }
    }
}
