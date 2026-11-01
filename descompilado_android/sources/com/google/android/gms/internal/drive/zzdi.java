package com.google.android.gms.internal.drive;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.OnChangeListener;

/* JADX INFO: loaded from: classes2.dex */
final class zzdi {
    private OnChangeListener zzgg;
    private zzee zzgh;
    private DriveId zzk;

    zzdi(zzch zzchVar, OnChangeListener onChangeListener, DriveId driveId) {
        Preconditions.checkState(com.google.android.gms.drive.events.zzj.zza(1, driveId));
        this.zzgg = onChangeListener;
        this.zzk = driveId;
        Looper looper = zzchVar.getLooper();
        Context applicationContext = zzchVar.getApplicationContext();
        onChangeListener.getClass();
        this.zzgh = new zzee(looper, applicationContext, 1, zzdj.zza(onChangeListener));
        this.zzgh.zzf(1);
    }
}
