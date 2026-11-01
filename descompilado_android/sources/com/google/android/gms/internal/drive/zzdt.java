package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;

/* JADX INFO: loaded from: classes2.dex */
final class zzdt extends zzea {
    private final /* synthetic */ MetadataChangeSet zzfd;
    private final /* synthetic */ zzdp zzgq;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdt(zzdp zzdpVar, GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet) {
        super(zzdpVar, googleApiClient, null);
        this.zzgq = zzdpVar;
        this.zzfd = metadataChangeSet;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        zzaw zzawVar = (zzaw) anyClient;
        this.zzfd.zzq().zza(zzawVar.getContext());
        ((zzeo) zzawVar.getService()).zza(new zzhf(this.zzgq.zzk, this.zzfd.zzq()), new zzdy(this));
    }
}
