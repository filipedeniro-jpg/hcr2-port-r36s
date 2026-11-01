package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzanc extends zzfm implements zzand {
    public static zzand zzae(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseListener");
        if (iInterfaceQueryLocalInterface instanceof zzand) {
            return (zzand) iInterfaceQueryLocalInterface;
        }
        return new zzanf(iBinder);
    }
}
