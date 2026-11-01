package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;

/* JADX INFO: loaded from: classes2.dex */
final class zzbk extends zzav {
    private final /* synthetic */ zzbi zzev;
    private final /* synthetic */ MetadataChangeSet zzew;
    private final /* synthetic */ com.google.android.gms.drive.zzn zzex;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbk(zzbi zzbiVar, GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, com.google.android.gms.drive.zzn zznVar) {
        super(googleApiClient);
        this.zzev = zzbiVar;
        this.zzew = metadataChangeSet;
        this.zzex = zznVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        zzaw zzawVar = (zzaw) anyClient;
        this.zzew.zzq().zza(zzawVar.getContext());
        ((zzeo) zzawVar.getService()).zza(new zzm(this.zzev.zzes.getDriveId(), this.zzew.zzq(), this.zzev.zzes.getRequestId(), this.zzev.zzes.zzb(), this.zzex), new zzgy(this));
    }
}
