package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzxc extends zzafd {
    private final OnInitializationCompleteListener zzcew;
    private final /* synthetic */ zzwv zzcex;

    private zzxc(zzwv zzwvVar, OnInitializationCompleteListener onInitializationCompleteListener) {
        this.zzcex = zzwvVar;
        this.zzcew = onInitializationCompleteListener;
    }

    @Override // com.google.android.gms.internal.ads.zzafe
    public final void zzc(List<zzaex> list) throws RemoteException {
        OnInitializationCompleteListener onInitializationCompleteListener = this.zzcew;
        zzwv zzwvVar = this.zzcex;
        onInitializationCompleteListener.onInitializationComplete(zzwv.zzb(list));
    }

    /* synthetic */ zzxc(zzwv zzwvVar, OnInitializationCompleteListener onInitializationCompleteListener, zzwz zzwzVar) {
        this(zzwvVar, onInitializationCompleteListener);
    }
}
