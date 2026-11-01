package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.ironsource.sdk.constants.Constants;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzue extends zzul<zzaqg> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ String zzcco;
    private final /* synthetic */ zzajd zzccp;
    private final /* synthetic */ zzuc zzccq;

    zzue(zzuc zzucVar, Context context, String str, zzajd zzajdVar) {
        this.zzccq = zzucVar;
        this.val$context = context;
        this.zzcco = str;
        this.zzccp = zzajdVar;
    }

    @Override // com.google.android.gms.internal.ads.zzul
    protected final /* synthetic */ zzaqg zzoa() {
        zzuc.zza(this.val$context, Constants.CONVERT_REWARDED);
        return new zzxp();
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzaqg zzob() throws RemoteException {
        return zzaqw.zzd(this.val$context, this.zzcco, this.zzccp);
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzaqg zza(zzvn zzvnVar) throws RemoteException {
        return zzvnVar.zzb(ObjectWrapper.wrap(this.val$context), this.zzcco, this.zzccp, 15301000);
    }
}
