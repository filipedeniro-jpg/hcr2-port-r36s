package com.google.android.gms.internal.drive;

import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: loaded from: classes2.dex */
public final class zzip extends zzb implements zzio {
    public static zzio zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IRealtimeService");
        if (iInterfaceQueryLocalInterface instanceof zzio) {
            return (zzio) iInterfaceQueryLocalInterface;
        }
        return new zziq(iBinder);
    }
}
