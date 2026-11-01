package com.google.android.gms.internal.firebase_remote_config;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzeu implements SuccessContinuation {
    static final SuccessContinuation zzjk = new zzeu();

    private zzeu() {
    }

    @Override // com.google.android.gms.tasks.SuccessContinuation
    public final Task then(Object obj) {
        return Tasks.forResult(zzet.zzg((zzen) obj));
    }
}
