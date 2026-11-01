package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public interface zzajr extends IInterface {
    String getAdvertiser() throws RemoteException;

    String getBody() throws RemoteException;

    String getCallToAction() throws RemoteException;

    Bundle getExtras() throws RemoteException;

    String getHeadline() throws RemoteException;

    List getImages() throws RemoteException;

    boolean getOverrideClickHandling() throws RemoteException;

    boolean getOverrideImpressionRecording() throws RemoteException;

    zzwk getVideoController() throws RemoteException;

    void recordImpression() throws RemoteException;

    void zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException;

    zzaak zzqk() throws RemoteException;

    IObjectWrapper zzql() throws RemoteException;

    zzaas zzqm() throws RemoteException;

    IObjectWrapper zzru() throws RemoteException;

    IObjectWrapper zzrv() throws RemoteException;

    void zzx(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzy(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzz(IObjectWrapper iObjectWrapper) throws RemoteException;
}
