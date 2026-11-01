package com.google.android.gms.internal.drive;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.CreateFileActivityOptions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes2.dex */
final class zzbg extends TaskApiCall<zzaw, IntentSender> {
    private final /* synthetic */ CreateFileActivityOptions zzer;

    zzbg(zzbb zzbbVar, CreateFileActivityOptions createFileActivityOptions) {
        this.zzer = createFileActivityOptions;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<IntentSender> taskCompletionSource) throws RemoteException {
        zzaw zzawVar = (zzaw) anyClient;
        zzeo zzeoVar = (zzeo) zzawVar.getService();
        this.zzer.zzde.zza(zzawVar.getContext());
        taskCompletionSource.setResult(zzeoVar.zza(new zzu(this.zzer.zzde, this.zzer.zzdk.intValue(), this.zzer.zzba, this.zzer.zzbd, Integer.valueOf(this.zzer.zzdl))));
    }
}
