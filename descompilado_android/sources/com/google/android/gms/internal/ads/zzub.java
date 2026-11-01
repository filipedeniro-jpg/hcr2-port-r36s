package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.my.target.ai;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzub extends zzul<zzve> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zztw zzccn;
    private final /* synthetic */ String zzcco;
    private final /* synthetic */ zzajd zzccp;
    private final /* synthetic */ zzuc zzccq;

    zzub(zzuc zzucVar, Context context, zztw zztwVar, String str, zzajd zzajdVar) {
        this.zzccq = zzucVar;
        this.val$context = context;
        this.zzccn = zztwVar;
        this.zzcco = str;
        this.zzccp = zzajdVar;
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzve zzoa() {
        zzuc.zza(this.val$context, ai.a.cW);
        return new zzxj();
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzve zzob() throws RemoteException {
        return this.zzccq.zzccr.zza(this.val$context, this.zzccn, this.zzcco, this.zzccp, 1);
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzve zza(zzvn zzvnVar) throws RemoteException {
        return zzvnVar.zza(ObjectWrapper.wrap(this.val$context), this.zzccn, this.zzcco, this.zzccp, 15301000);
    }
}
