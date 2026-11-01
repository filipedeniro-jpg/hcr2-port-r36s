package com.google.android.gms.internal.plus;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes2.dex */
final class zzo extends zzp {
    private final /* synthetic */ String[] zzam;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzo(zzj zzjVar, GoogleApiClient googleApiClient, String[] strArr) {
        super(googleApiClient, null);
        this.zzam = strArr;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((com.google.android.gms.plus.internal.zzh) anyClient).zza(this, this.zzam);
    }
}
