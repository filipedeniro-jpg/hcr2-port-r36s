package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: classes2.dex */
final class zzbo extends zzam {
    private final /* synthetic */ int zzdv;
    private final /* synthetic */ DriveFile.DownloadProgressListener zzey;
    private final /* synthetic */ zzbn zzez;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbo(zzbn zzbnVar, GoogleApiClient googleApiClient, int i, DriveFile.DownloadProgressListener downloadProgressListener) {
        super(googleApiClient);
        this.zzez = zzbnVar;
        this.zzdv = i;
        this.zzey = downloadProgressListener;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        setCancelToken(ICancelToken.Stub.asInterface(((zzeo) ((zzaw) anyClient).getService()).zza(new zzgj(this.zzez.getDriveId(), this.zzdv, 0), new zzgl(this, this.zzey)).zzgs));
    }
}
