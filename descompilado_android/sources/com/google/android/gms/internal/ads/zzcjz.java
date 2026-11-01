package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzcjz extends zzajm {
    private final zzbnl zzfeu;
    private final zzbnb zzffi;
    private final zzbnu zzfib;
    private final zzbmo zzfjd;
    private final zzbmf zzfje;
    private final zzbop zzfpl;
    private final zzbqr zzfzx;

    public zzcjz(zzbmf zzbmfVar, zzbmo zzbmoVar, zzbnb zzbnbVar, zzbnl zzbnlVar, zzbop zzbopVar, zzbnu zzbnuVar, zzbqr zzbqrVar) {
        this.zzfje = zzbmfVar;
        this.zzfjd = zzbmoVar;
        this.zzffi = zzbnbVar;
        this.zzfeu = zzbnlVar;
        this.zzfpl = zzbopVar;
        this.zzfib = zzbnuVar;
        this.zzfzx = zzbqrVar;
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAdFailedToLoad(int i) {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void zza(zzabo zzaboVar, String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void zza(zzajo zzajoVar) {
    }

    public void zza(zzaqf zzaqfVar) throws RemoteException {
    }

    public void zzb(Bundle bundle) throws RemoteException {
    }

    public void zzb(zzaqd zzaqdVar) {
    }

    public void zzcm(int i) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void zzde(String str) {
    }

    public void zzrt() throws RemoteException {
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAdClicked() {
        this.zzfje.onAdClicked();
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAdClosed() {
        this.zzfib.zzse();
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAdLeftApplication() {
        this.zzffi.onAdLeftApplication();
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAdOpened() {
        this.zzfib.zzsf();
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAppEvent(String str, String str2) {
        this.zzfpl.onAppEvent(str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAdLoaded() {
        this.zzfeu.onAdLoaded();
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onAdImpression() {
        this.zzfjd.onAdImpression();
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onVideoPause() {
        this.zzfzx.onVideoPause();
    }

    public void zzrs() {
        this.zzfzx.onVideoStart();
    }

    public void onVideoEnd() {
        this.zzfzx.onVideoEnd();
    }

    @Override // com.google.android.gms.internal.ads.zzajj
    public final void onVideoPlay() throws RemoteException {
        this.zzfzx.onVideoPlay();
    }
}
