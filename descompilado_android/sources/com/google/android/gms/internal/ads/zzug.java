package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzug extends zzul<zzve> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zztw zzccn;
    private final /* synthetic */ String zzcco;
    private final /* synthetic */ zzuc zzccq;

    zzug(zzuc zzucVar, Context context, zztw zztwVar, String str) {
        this.zzccq = zzucVar;
        this.val$context = context;
        this.zzccn = zztwVar;
        this.zzcco = str;
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzve zzoa() {
        zzuc.zza(this.val$context, "search");
        return new zzxj();
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzve zzob() throws RemoteException {
        return this.zzccq.zzccr.zza(this.val$context, this.zzccn, this.zzcco, null, 3);
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzve zza(zzvn zzvnVar) throws RemoteException {
        return zzvnVar.zza(ObjectWrapper.wrap(this.val$context), this.zzccn, this.zzcco, 15301000);
    }
}
