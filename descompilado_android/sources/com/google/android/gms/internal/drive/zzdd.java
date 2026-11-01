package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes2.dex */
final class zzdd extends TaskApiCall<zzaw, Metadata> {
    private final /* synthetic */ MetadataChangeSet zzfd;
    private final /* synthetic */ DriveResource zzfq;

    zzdd(zzch zzchVar, MetadataChangeSet metadataChangeSet, DriveResource driveResource) {
        this.zzfd = metadataChangeSet;
        this.zzfq = driveResource;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<Metadata> taskCompletionSource) throws RemoteException {
        zzaw zzawVar = (zzaw) anyClient;
        this.zzfd.zzq().zza(zzawVar.getContext());
        ((zzeo) zzawVar.getService()).zza(new zzhf(this.zzfq.getDriveId(), this.zzfd.zzq()), new zzhp(taskCompletionSource));
    }
}
