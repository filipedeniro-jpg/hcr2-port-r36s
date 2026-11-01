package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* JADX INFO: Add missing generic type declarations: [ResultT] */
/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzat<ResultT> implements Continuation<ResultT, Task<ResultT>> {
    private final /* synthetic */ zzap zza;
    private final /* synthetic */ zzau zzb;

    zzat(zzau zzauVar, zzap zzapVar) {
        this.zzb = zzauVar;
        this.zza = zzapVar;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Object then(Task task) throws Exception {
        return task.getException() instanceof UnsupportedApiCallException ? this.zzb.zza(this.zza.zzc()) : task;
    }
}
