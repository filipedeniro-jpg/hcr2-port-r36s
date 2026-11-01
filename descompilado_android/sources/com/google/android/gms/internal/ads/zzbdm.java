package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzbdm extends zzfm implements zzbdn {
    public static zzbdn zzam(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.measurement.IMeasurementManager");
        if (iInterfaceQueryLocalInterface instanceof zzbdn) {
            return (zzbdn) iInterfaceQueryLocalInterface;
        }
        return new zzbdo(iBinder);
    }
}
