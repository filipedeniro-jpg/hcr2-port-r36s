package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.drive.DrivePreferencesApi;

/* JADX INFO: loaded from: classes2.dex */
final class zzce extends zzl {
    private final BaseImplementation.ResultHolder<DrivePreferencesApi.FileUploadPreferencesResult> zzdx;
    private final /* synthetic */ zzcb zzfk;

    private zzce(zzcb zzcbVar, BaseImplementation.ResultHolder<DrivePreferencesApi.FileUploadPreferencesResult> resultHolder) {
        this.zzfk = zzcbVar;
        this.zzdx = resultHolder;
    }

    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(zzfj zzfjVar) throws RemoteException {
        this.zzdx.setResult(new zzcf(this.zzfk, Status.RESULT_SUCCESS, zzfjVar.zzhw, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.drive.zzl, com.google.android.gms.internal.drive.zzeq
    public final void zza(Status status) throws RemoteException {
        this.zzdx.setResult(new zzcf(this.zzfk, status, null, 0 == true ? 1 : 0));
    }

    /* synthetic */ zzce(zzcb zzcbVar, BaseImplementation.ResultHolder resultHolder, zzcc zzccVar) {
        this(zzcbVar, resultHolder);
    }
}
