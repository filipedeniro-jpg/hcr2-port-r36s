package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzcbn implements AppEventListener, zzbmg, zzbml, zzbms, zzbmt, zzbnm, zzboi, zzcxt, zzth {
    private long startTime;
    private final List<Object> zzdvn;
    private final zzcbb zzfsd;

    public zzcbn(zzcbb zzcbbVar, zzbds zzbdsVar) {
        this.zzfsd = zzcbbVar;
        this.zzdvn = Collections.singletonList(zzbdsVar);
    }

    @Override // com.google.android.gms.internal.ads.zzboi
    public final void zza(zzcvj zzcvjVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzbms
    public final void zzbt(Context context) {
        zza(zzbms.class, "onPause", context);
    }

    @Override // com.google.android.gms.internal.ads.zzbms
    public final void zzbu(Context context) {
        zza(zzbms.class, "onResume", context);
    }

    @Override // com.google.android.gms.internal.ads.zzbms
    public final void zzbv(Context context) {
        zza(zzbms.class, "onDestroy", context);
    }

    @Override // com.google.android.gms.ads.doubleclick.AppEventListener
    public final void onAppEvent(String str, String str2) {
        zza(AppEventListener.class, "onAppEvent", str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzbnm
    public final void onAdLoaded() {
        long jElapsedRealtime = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - this.startTime;
        StringBuilder sb = new StringBuilder(41);
        sb.append("Ad Request Latency : ");
        sb.append(jElapsedRealtime);
        zzatm.zzdy(sb.toString());
        zza(zzbnm.class, "onAdLoaded", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzbml
    public final void onAdFailedToLoad(int i) {
        zza(zzbml.class, "onAdFailedToLoad", Integer.valueOf(i));
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdOpened() {
        zza(zzbmg.class, "onAdOpened", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdClosed() {
        zza(zzbmg.class, "onAdClosed", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onAdLeftApplication() {
        zza(zzbmg.class, "onAdLeftApplication", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzth
    public final void onAdClicked() {
        zza(zzth.class, "onAdClicked", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzbmt
    public final void onAdImpression() {
        zza(zzbmt.class, "onAdImpression", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onRewardedVideoStarted() {
        zza(zzbmg.class, "onRewardedVideoStarted", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    @ParametersAreNonnullByDefault
    public final void zzb(zzape zzapeVar, String str, String str2) {
        zza(zzbmg.class, "onRewarded", zzapeVar, str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzbmg
    public final void onRewardedVideoCompleted() {
        zza(zzbmg.class, "onRewardedVideoCompleted", new Object[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zza(zzcxk zzcxkVar, String str) {
        zza(zzcxl.class, "onTaskCreated", str);
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zzb(zzcxk zzcxkVar, String str) {
        zza(zzcxl.class, "onTaskStarted", str);
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zza(zzcxk zzcxkVar, String str, Throwable th) {
        zza(zzcxl.class, "onTaskFailed", str, th.getClass().getSimpleName());
    }

    @Override // com.google.android.gms.internal.ads.zzcxt
    public final void zzc(zzcxk zzcxkVar, String str) {
        zza(zzcxl.class, "onTaskSucceeded", str);
    }

    private final void zza(Class<?> cls, String str, Object... objArr) {
        zzcbb zzcbbVar = this.zzfsd;
        List<Object> list = this.zzdvn;
        String strValueOf = String.valueOf(cls.getSimpleName());
        zzcbbVar.zza(list, strValueOf.length() != 0 ? "Event-".concat(strValueOf) : new String("Event-"), str, objArr);
    }

    @Override // com.google.android.gms.internal.ads.zzboi
    public final void zzb(zzaok zzaokVar) {
        this.startTime = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime();
        zza(zzboi.class, "onAdRequest", new Object[0]);
    }
}
