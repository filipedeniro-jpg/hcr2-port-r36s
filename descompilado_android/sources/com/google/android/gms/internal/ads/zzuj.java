package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzuj extends zzul<zzaaz> {
    private final /* synthetic */ zzuc zzccq;
    private final /* synthetic */ View zzccz;
    private final /* synthetic */ HashMap zzcda;
    private final /* synthetic */ HashMap zzcdb;

    zzuj(zzuc zzucVar, View view, HashMap map, HashMap map2) {
        this.zzccq = zzucVar;
        this.zzccz = view;
        this.zzcda = map;
        this.zzcdb = map2;
    }

    @Override // com.google.android.gms.internal.ads.zzul
    protected final /* synthetic */ zzaaz zzoa() {
        zzuc.zza(this.zzccz.getContext(), "native_ad_view_holder_delegate");
        return new zzxq();
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzaaz zzob() throws RemoteException {
        return this.zzccq.zzccy.zzb(this.zzccz, this.zzcda, this.zzcdb);
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzaaz zza(zzvn zzvnVar) throws RemoteException {
        return zzvnVar.zza(ObjectWrapper.wrap(this.zzccz), ObjectWrapper.wrap(this.zzcda), ObjectWrapper.wrap(this.zzcdb));
    }
}
