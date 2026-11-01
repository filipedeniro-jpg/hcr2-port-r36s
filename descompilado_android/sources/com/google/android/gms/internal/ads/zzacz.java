package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzacz extends zzacd {
    private final OnPublisherAdViewLoadedListener zzcwj;

    public zzacz(OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener) {
        this.zzcwj = onPublisherAdViewLoadedListener;
    }

    @Override // com.google.android.gms.internal.ads.zzace
    public final void zza(zzve zzveVar, IObjectWrapper iObjectWrapper) {
        if (zzveVar == null || iObjectWrapper == null) {
            return;
        }
        PublisherAdView publisherAdView = new PublisherAdView((Context) ObjectWrapper.unwrap(iObjectWrapper));
        try {
            if (zzveVar.zzjr() instanceof zztl) {
                zztl zztlVar = (zztl) zzveVar.zzjr();
                publisherAdView.setAdListener(zztlVar != null ? zztlVar.getAdListener() : null);
            }
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
        try {
            if (zzveVar.zzjq() instanceof zzty) {
                zzty zztyVar = (zzty) zzveVar.zzjq();
                publisherAdView.setAppEventListener(zztyVar != null ? zztyVar.getAppEventListener() : null);
            }
        } catch (RemoteException e2) {
            zzawo.zzc("", e2);
        }
        zzawe.zzzb.post(new zzadc(this, publisherAdView, zzveVar));
    }
}
