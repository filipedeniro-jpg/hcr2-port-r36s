package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzanh extends zzfm implements zzane {
    public static zzane zzaf(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
        if (iInterfaceQueryLocalInterface instanceof zzane) {
            return (zzane) iInterfaceQueryLocalInterface;
        }
        return new zzang(iBinder);
    }
}
