package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzcya extends zzfm implements zzcyb {
    public static zzcyb zzan(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.omid.IOmid");
        if (iInterfaceQueryLocalInterface instanceof zzcyb) {
            return (zzcyb) iInterfaceQueryLocalInterface;
        }
        return new zzcyc(iBinder);
    }
}
