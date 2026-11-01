package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.AdMetadataListener;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcto extends AdMetadataListener implements zzbmg, zzbml, zzbmp, zzbnm {
    private final AtomicReference<AdMetadataListener> zzghh = new AtomicReference<>();
    private final AtomicReference<zzaqo> zzghi = new AtomicReference<>();
    private final AtomicReference<zzaql> zzghj = new AtomicReference<>();
    private final AtomicReference<zzapo> zzghk = new AtomicReference<>();
    private final AtomicReference<zzaqt> zzghl = new AtomicReference<>();
    private final AtomicReference<zzapj> zzghm = new AtomicReference<>();

    public final void zza(zzaqo zzaqoVar) {
        this.zzghi.set(zzaqoVar);
    }

    public final void zzb(zzaql zzaqlVar) {
        this.zzghj.set(zzaqlVar);
    }

    public final void zzb(zzaqt zzaqtVar) {
        this.zzghl.set(zzaqtVar);
    }

    public final void zza(AdMetadataListener adMetadataListener) {
        this.zzghh.set(adMetadataListener);
    }

    @Deprecated
    public final void zzb(zzapo zzapoVar) {
        this.zzghk.set(zzapoVar);
    }

    @Deprecated
    public final void zzb(zzapj zzapjVar) {
        this.zzghm.set(zzapjVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbnm
    public final void onAdLoaded() {
        zza(this.zzghi, zzctr.zzghn);
        zza(this.zzghk, zzctq.zzghn);
    }

    @Override // com.google.android.gms.internal.ads.zzbml
    public final void onAdFailedToLoad(final int i) {
        zza(this.zzghi, new zzcug(i) { // from class: com.google.android.gms.internal.ads.zzcty
            private final int zzdvo;

            {
                this.zzdvo = i;
            }

            @Override // com.google.android.gms.internal.ads.zzcug
            public final void zzt(Object obj) throws RemoteException {
                ((zzaqo) obj).onRewardedAdFailedToLoad(this.zzdvo);
            }
        });
        zza(this.zzghk, new zzcug(i) { // from class: com.google.android.gms.internal.ads.zzcub
            private final int zzdvo;

            {
                this.zzdvo = i;
            }

            @Override // com.google.android.gms.internal.ads.zzcug
            public final void zzt(Object obj) throws RemoteException {
                ((zzapo) obj).onRewardedVideoAdFailedToLoad(this.zzdvo);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdOpened() {
        zza(this.zzghj, zzcua.zzghn);
        zza(this.zzghk, zzcud.zzghn);
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdClosed() {
        zza(this.zzghj, zzcuc.zzghn);
        zza(this.zzghk, zzcuf.zzghn);
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdLeftApplication() {
        zza(this.zzghk, zzcue.zzghn);
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onRewardedVideoStarted() {
        zza(this.zzghk, zzcuh.zzghn);
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void zzb(final zzape zzapeVar, final String str, final String str2) {
        zza(this.zzghj, new zzcug(zzapeVar) { // from class: com.google.android.gms.internal.ads.zzctt
            private final zzape zzfgp;

            {
                this.zzfgp = zzapeVar;
            }

            @Override // com.google.android.gms.internal.ads.zzcug
            public final void zzt(Object obj) throws RemoteException {
                zzape zzapeVar2 = this.zzfgp;
                ((zzaql) obj).zza(new zzarc(zzapeVar2.getType(), zzapeVar2.getAmount()));
            }
        });
        zza(this.zzghl, new zzcug(zzapeVar, str, str2) { // from class: com.google.android.gms.internal.ads.zzcts
            private final String zzcyl;
            private final String zzdbj;
            private final zzape zzfgp;

            {
                this.zzfgp = zzapeVar;
                this.zzcyl = str;
                this.zzdbj = str2;
            }

            @Override // com.google.android.gms.internal.ads.zzcug
            public final void zzt(Object obj) throws RemoteException {
                zzape zzapeVar2 = this.zzfgp;
                ((zzaqt) obj).zza(new zzarc(zzapeVar2.getType(), zzapeVar2.getAmount()), this.zzcyl, this.zzdbj);
            }
        });
        zza(this.zzghk, new zzcug(zzapeVar) { // from class: com.google.android.gms.internal.ads.zzctv
            private final zzape zzfgp;

            {
                this.zzfgp = zzapeVar;
            }

            @Override // com.google.android.gms.internal.ads.zzcug
            public final void zzt(Object obj) throws RemoteException {
                ((zzapo) obj).zza(this.zzfgp);
            }
        });
        zza(this.zzghm, new zzcug(zzapeVar, str, str2) { // from class: com.google.android.gms.internal.ads.zzctu
            private final String zzcyl;
            private final String zzdbj;
            private final zzape zzfgp;

            {
                this.zzfgp = zzapeVar;
                this.zzcyl = str;
                this.zzdbj = str2;
            }

            @Override // com.google.android.gms.internal.ads.zzcug
            public final void zzt(Object obj) throws RemoteException {
                ((zzapj) obj).zza(this.zzfgp, this.zzcyl, this.zzdbj);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onRewardedVideoCompleted() {
        zza(this.zzghk, zzctx.zzghn);
    }

    @Override // com.google.android.gms.internal.ads.zzbmp
    public final void zzcm(final int i) {
        zza(this.zzghj, new zzcug(i) { // from class: com.google.android.gms.internal.ads.zzctw
            private final int zzdvo;

            {
                this.zzdvo = i;
            }

            @Override // com.google.android.gms.internal.ads.zzcug
            public final void zzt(Object obj) throws RemoteException {
                ((zzaql) obj).onRewardedAdFailedToShow(this.zzdvo);
            }
        });
    }

    @Override // com.google.android.gms.ads.reward.AdMetadataListener
    public final void onAdMetadataChanged() {
        zza(this.zzghh, zzctz.zzghn);
    }

    private static <T> void zza(AtomicReference<T> atomicReference, zzcug<T> zzcugVar) {
        T t = atomicReference.get();
        if (t == null) {
            return;
        }
        try {
            zzcugVar.zzt(t);
        } catch (RemoteException e) {
            zzatm.zze("#007 Could not call remote method.", e);
        }
    }
}
