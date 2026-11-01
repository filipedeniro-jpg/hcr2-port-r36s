package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzum extends zzul<zzapl> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzajd zzccp;
    private final /* synthetic */ zzuc zzccq;

    zzum(zzuc zzucVar, Context context, zzajd zzajdVar) {
        this.zzccq = zzucVar;
        this.val$context = context;
        this.zzccp = zzajdVar;
    }

    @Override // com.google.android.gms.internal.ads.zzul
    protected final /* synthetic */ zzapl zzoa() {
        zzuc.zza(this.val$context, "rewarded_video");
        return new zzxr();
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzapl zzob() throws RemoteException {
        return this.zzccq.zzccv.zza(this.val$context, this.zzccp);
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzapl zza(zzvn zzvnVar) throws RemoteException {
        return zzvnVar.zza(ObjectWrapper.wrap(this.val$context), this.zzccp, 15301000);
    }
}
