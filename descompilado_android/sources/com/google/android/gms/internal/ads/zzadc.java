package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzadc implements Runnable {
    private final /* synthetic */ PublisherAdView zzcwm;
    private final /* synthetic */ zzve zzcwn;
    private final /* synthetic */ zzacz zzcwo;

    zzadc(zzacz zzaczVar, PublisherAdView publisherAdView, zzve zzveVar) {
        this.zzcwo = zzaczVar;
        this.zzcwm = publisherAdView;
        this.zzcwn = zzveVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzcwm.zza(this.zzcwn)) {
            this.zzcwo.zzcwj.onPublisherAdViewLoaded(this.zzcwm);
        } else {
            zzawo.zzeu("Could not bind.");
        }
    }
}
