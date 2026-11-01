package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzuk extends zzul<zzaaw> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzuc zzccq;
    private final /* synthetic */ FrameLayout zzcdc;
    private final /* synthetic */ FrameLayout zzcdd;

    zzuk(zzuc zzucVar, FrameLayout frameLayout, FrameLayout frameLayout2, Context context) {
        this.zzccq = zzucVar;
        this.zzcdc = frameLayout;
        this.zzcdd = frameLayout2;
        this.val$context = context;
    }

    @Override // com.google.android.gms.internal.ads.zzul
    protected final /* synthetic */ zzaaw zzoa() {
        zzuc.zza(this.val$context, "native_ad_view_delegate");
        return new zzxn();
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzaaw zzob() throws RemoteException {
        return this.zzccq.zzccu.zzb(this.val$context, this.zzcdc, this.zzcdd);
    }

    @Override // com.google.android.gms.internal.ads.zzul
    public final /* synthetic */ zzaaw zza(zzvn zzvnVar) throws RemoteException {
        return zzvnVar.zzc(ObjectWrapper.wrap(this.zzcdc), ObjectWrapper.wrap(this.zzcdd));
    }
}
