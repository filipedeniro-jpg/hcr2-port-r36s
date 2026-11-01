package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.ref.WeakReference;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbrc extends zzbju {
    private final WeakReference<zzbbc> zzfht;
    private final zzbps zzfhu;
    private final zzbse zzfhv;
    private final zzbko zzfhw;
    private final zzcym zzfhx;
    private boolean zzfhy;
    private final Context zzlk;

    zzbrc(zzbjt zzbjtVar, Context context, @Nullable zzbbc zzbbcVar, zzbps zzbpsVar, zzbse zzbseVar, zzbko zzbkoVar, zzcym zzcymVar) {
        super(zzbjtVar);
        this.zzfhy = false;
        this.zzlk = context;
        this.zzfht = new WeakReference<>(zzbbcVar);
        this.zzfhu = zzbpsVar;
        this.zzfhv = zzbseVar;
        this.zzfhw = zzbkoVar;
        this.zzfhx = zzcymVar;
    }

    public final void zzay(boolean z) {
        this.zzfhu.zzagj();
        this.zzfhv.zza(z, this.zzlk);
        this.zzfhy = true;
    }

    public final boolean isClosed() {
        return this.zzfhw.isClosed();
    }

    public final boolean zzagp() {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjn)).booleanValue()) {
            com.google.android.gms.ads.internal.zzp.zzjy();
            if (zzatv.zzat(this.zzlk)) {
                zzatm.zzeu("It is not recommended to show an interstitial when app is not in foreground.");
                if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjo)).booleanValue()) {
                    this.zzfhx.zzgl(this.zzfaq.zzgjp.zzgjm.zzbzx);
                }
                return false;
            }
        }
        return !this.zzfhy;
    }

    public final void finalize() throws Throwable {
        try {
            zzbbc zzbbcVar = this.zzfht.get();
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzctm)).booleanValue()) {
                if (!this.zzfhy && zzbbcVar != null) {
                    zzdcs zzdcsVar = zzawx.zzdwa;
                    zzbbcVar.getClass();
                    zzdcsVar.execute(zzbrb.zzh(zzbbcVar));
                }
            } else if (zzbbcVar != null) {
                zzbbcVar.destroy();
            }
        } finally {
            super.finalize();
        }
    }
}
