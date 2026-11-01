package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaiw extends zzbdk {
    private final AppMeasurementSdk zzdbh;

    zzaiw(AppMeasurementSdk appMeasurementSdk) {
        this.zzdbh = appMeasurementSdk;
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void performAction(Bundle bundle) throws RemoteException {
        this.zzdbh.performAction(bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final Bundle performActionWithResponse(Bundle bundle) throws RemoteException {
        return this.zzdbh.performActionWithResponse(bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void logEvent(String str, String str2, Bundle bundle) throws RemoteException {
        this.zzdbh.logEvent(str, str2, bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zza(String str, String str2, IObjectWrapper iObjectWrapper) throws RemoteException {
        this.zzdbh.setUserProperty(str, str2, iObjectWrapper != null ? ObjectWrapper.unwrap(iObjectWrapper) : null);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final Map getUserProperties(String str, String str2, boolean z) throws RemoteException {
        return this.zzdbh.getUserProperties(str, str2, z);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final int getMaxUserProperties(String str) throws RemoteException {
        return this.zzdbh.getMaxUserProperties(str);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void setConditionalUserProperty(Bundle bundle) throws RemoteException {
        this.zzdbh.setConditionalUserProperty(bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        this.zzdbh.clearConditionalUserProperty(str, str2, bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final List getConditionalUserProperties(String str, String str2) throws RemoteException {
        return this.zzdbh.getConditionalUserProperties(str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final String getAppInstanceId() throws RemoteException {
        return this.zzdbh.getAppInstanceId();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final String getGmpAppId() throws RemoteException {
        return this.zzdbh.getGmpAppId();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final long generateEventId() throws RemoteException {
        return this.zzdbh.generateEventId();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void beginAdUnitExposure(String str) throws RemoteException {
        this.zzdbh.beginAdUnitExposure(str);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void endAdUnitExposure(String str) throws RemoteException {
        this.zzdbh.endAdUnitExposure(str);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final void zzb(IObjectWrapper iObjectWrapper, String str, String str2) throws RemoteException {
        this.zzdbh.setCurrentScreen(iObjectWrapper != null ? (Activity) ObjectWrapper.unwrap(iObjectWrapper) : null, str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final String getCurrentScreenName() throws RemoteException {
        return this.zzdbh.getCurrentScreenName();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final String getCurrentScreenClass() throws RemoteException {
        return this.zzdbh.getCurrentScreenClass();
    }

    @Override // com.google.android.gms.internal.ads.zzbdl
    public final String getAppIdOrigin() throws RemoteException {
        return this.zzdbh.getAppIdOrigin();
    }
}
