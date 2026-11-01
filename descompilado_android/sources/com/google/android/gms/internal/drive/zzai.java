package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;

/* JADX INFO: loaded from: classes2.dex */
final class zzai extends zzap {
    private final /* synthetic */ String zzdw;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzai(zzaf zzafVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzdw = str;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzeo) ((zzaw) anyClient).getService()).zza(new zzek(DriveId.zza(this.zzdw), false), new zzan(this));
    }
}
