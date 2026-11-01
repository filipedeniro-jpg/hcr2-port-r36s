package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: classes2.dex */
final class zzah extends zzam {
    private final /* synthetic */ int zzdv = DriveFile.MODE_WRITE_ONLY;

    zzah(zzaf zzafVar, GoogleApiClient googleApiClient, int i) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzr(this.zzdv), new zzak(this));
    }
}
