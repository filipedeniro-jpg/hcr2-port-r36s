package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzui extends zzul<zzux> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ String zzcco;
    private final /* synthetic */ zzajd zzccp;
    private final /* synthetic */ zzuc zzccq;

    zzui(zzuc zzucVar, Context context, String str, zzajd zzajdVar) {
        this.zzccq = zzucVar;
        this.val$context = context;
        this.zzcco = str;
        this.zzccp = zzajdVar;
    }

    @Override // com.google.android.gms.internal.ads.zzul
    protected final /* synthetic */ zzux zzoa() {
        zzuc.zza(this.val$context, "native_ad");
        return new zzxf();
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzux zzob() throws RemoteException {
        return this.zzccq.zzccs.zza(this.val$context, this.zzcco, this.zzccp);
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzux zza(zzvn zzvnVar) throws RemoteException {
        return zzvnVar.zza(ObjectWrapper.wrap(this.val$context), this.zzcco, this.zzccp, 15301000);
    }
}
