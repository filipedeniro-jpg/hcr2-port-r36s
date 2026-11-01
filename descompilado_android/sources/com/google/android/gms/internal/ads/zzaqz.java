package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzaqz implements zzawq {
    static final zzawq zzbtn = new zzaqz();

    private zzaqz() {
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    public final Object apply(Object obj) {
        IBinder iBinder = (IBinder) obj;
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdCreator");
        return iInterfaceQueryLocalInterface instanceof zzaqm ? (zzaqm) iInterfaceQueryLocalInterface : new zzaqp(iBinder);
    }
}
