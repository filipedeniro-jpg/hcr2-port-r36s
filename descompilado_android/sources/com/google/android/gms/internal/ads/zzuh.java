package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzuh extends zzul<zzvu> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzuc zzccq;

    zzuh(zzuc zzucVar, Context context) {
        this.zzccq = zzucVar;
        this.val$context = context;
    }

    @Override // com.google.android.gms.internal.ads.zzul
    protected final /* synthetic */ zzvu zzoa() {
        zzuc.zza(this.val$context, "mobile_ads_settings");
        return new zzxl();
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzvu zzob() throws RemoteException {
        return this.zzccq.zzcct.zzi(this.val$context);
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzvu zza(zzvn zzvnVar) throws RemoteException {
        return zzvnVar.zza(ObjectWrapper.wrap(this.val$context), 15301000);
    }
}
