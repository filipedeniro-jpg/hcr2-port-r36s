package com.google.android.gms.internal.plus;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes2.dex */
final class zzl extends zzp {
    private final /* synthetic */ String zzak;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzl(zzj zzjVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient, null);
        this.zzak = str;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        setCancelToken(((com.google.android.gms.plus.internal.zzh) anyClient).zza(this, 0, this.zzak));
    }
}
