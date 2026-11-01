package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbxf extends zzabj {

    @Nullable
    private final String zzfev;
    private final zzbtx zzfjc;
    private final zzbtp zzfma;

    public zzbxf(@Nullable String str, zzbtp zzbtpVar, zzbtx zzbtxVar) {
        this.zzfev = str;
        this.zzfma = zzbtpVar;
        this.zzfjc = zzbtxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final IObjectWrapper zzqi() throws RemoteException {
        return ObjectWrapper.wrap(this.zzfma);
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final String getHeadline() throws RemoteException {
        return this.zzfjc.getHeadline();
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final List<?> getImages() throws RemoteException {
        return this.zzfjc.getImages();
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final String getBody() throws RemoteException {
        return this.zzfjc.getBody();
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final zzaas zzqm() throws RemoteException {
        return this.zzfjc.zzqm();
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final String getCallToAction() throws RemoteException {
        return this.zzfjc.getCallToAction();
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final String getAdvertiser() throws RemoteException {
        return this.zzfjc.getAdvertiser();
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final Bundle getExtras() throws RemoteException {
        return this.zzfjc.getExtras();
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final void destroy() throws RemoteException {
        this.zzfma.destroy();
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final zzwk getVideoController() throws RemoteException {
        return this.zzfjc.getVideoController();
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final void performClick(Bundle bundle) throws RemoteException {
        this.zzfma.zzf(bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final boolean recordImpression(Bundle bundle) throws RemoteException {
        return this.zzfma.zzh(bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final void reportTouchEvent(Bundle bundle) throws RemoteException {
        this.zzfma.zzg(bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final zzaak zzqk() throws RemoteException {
        return this.zzfjc.zzqk();
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final IObjectWrapper zzql() throws RemoteException {
        return this.zzfjc.zzql();
    }

    @Override // com.google.android.gms.internal.ads.zzabk
    public final String getMediationAdapterClassName() throws RemoteException {
        return this.zzfev;
    }
}
