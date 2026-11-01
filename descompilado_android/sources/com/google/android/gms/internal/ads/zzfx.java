package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzfx extends zzfm implements zzfy {
    public static zzfy zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.clearcut.IClearcut");
        if (iInterfaceQueryLocalInterface instanceof zzfy) {
            return (zzfy) iInterfaceQueryLocalInterface;
        }
        return new zzfz(iBinder);
    }
}
