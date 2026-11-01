package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbia extends zzbhy {
    private final View view;

    @Nullable
    private final zzbbc zzcyx;
    private final Executor zzfbm;
    private final zzcva zzfcp;
    private final zzbjr zzfdb;
    private final zzbui zzfdc;
    private final zzbqg zzfdd;
    private final zzdvc<zzclg> zzfde;
    private zztw zzfdf;
    private final Context zzlk;

    zzbia(zzbjt zzbjtVar, Context context, zzcva zzcvaVar, View view, @Nullable zzbbc zzbbcVar, zzbjr zzbjrVar, zzbui zzbuiVar, zzbqg zzbqgVar, zzdvc<zzclg> zzdvcVar, Executor executor) {
        super(zzbjtVar);
        this.zzlk = context;
        this.view = view;
        this.zzcyx = zzbbcVar;
        this.zzfcp = zzcvaVar;
        this.zzfdb = zzbjrVar;
        this.zzfdc = zzbuiVar;
        this.zzfdd = zzbqgVar;
        this.zzfde = zzdvcVar;
        this.zzfbm = executor;
    }

    @Override // com.google.android.gms.internal.ads.zzbhy
    public final View zzaer() {
        return this.view;
    }

    @Override // com.google.android.gms.internal.ads.zzbhy
    public final void zza(ViewGroup viewGroup, zztw zztwVar) {
        zzbbc zzbbcVar;
        if (viewGroup == null || (zzbbcVar = this.zzcyx) == null) {
            return;
        }
        zzbbcVar.zza(zzbct.zzb(zztwVar));
        viewGroup.setMinimumHeight(zztwVar.heightPixels);
        viewGroup.setMinimumWidth(zztwVar.widthPixels);
        this.zzfdf = zztwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbhy
    public final zzwk getVideoController() {
        try {
            return this.zzfdb.getVideoController();
        } catch (zzcvr unused) {
            return null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbhy
    public final zzcva zzaeq() {
        zztw zztwVar = this.zzfdf;
        if (zztwVar != null) {
            return zzcvo.zze(zztwVar);
        }
        return zzcvo.zza(this.zzfef.zzgiv, this.zzfcp);
    }

    @Override // com.google.android.gms.internal.ads.zzbhy
    public final int zzaev() {
        return this.zzfaq.zzgjp.zzgjm.zzgji;
    }

    @Override // com.google.android.gms.internal.ads.zzbhy
    public final void zzjn() {
        this.zzfdd.zzagm();
    }

    @Override // com.google.android.gms.internal.ads.zzbju
    public final void zzaew() {
        this.zzfbm.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzbhz
            private final zzbia zzfda;

            {
                this.zzfda = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzfda.zzaex();
            }
        });
        super.zzaew();
    }

    final /* synthetic */ void zzaex() {
        if (this.zzfdc.zzaid() != null) {
            try {
                this.zzfdc.zzaid().zza(this.zzfde.get(), ObjectWrapper.wrap(this.zzlk));
            } catch (RemoteException e) {
                zzatm.zzc("RemoteException when notifyAdLoad is called", e);
            }
        }
    }
}
