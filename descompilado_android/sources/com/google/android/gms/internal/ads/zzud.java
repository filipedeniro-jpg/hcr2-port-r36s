package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzud extends zzul<zzamu> {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zzuc zzccq;

    zzud(zzuc zzucVar, Activity activity) {
        this.zzccq = zzucVar;
        this.val$activity = activity;
    }

    @Override // com.google.android.gms.internal.ads.zzul
    protected final /* synthetic */ zzamu zzoa() {
        zzuc.zza(this.val$activity, "ad_overlay");
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzamu zzob() throws RemoteException {
        return this.zzccq.zzccx.zzc(this.val$activity);
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzamu zza(zzvn zzvnVar) throws RemoteException {
        return zzvnVar.zzf(ObjectWrapper.wrap(this.val$activity));
    }
}
