package com.google.android.gms.drive.events;

import android.os.Looper;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
final class zzh extends Thread {
    private final /* synthetic */ CountDownLatch zzcn;
    private final /* synthetic */ DriveEventService zzco;

    zzh(DriveEventService driveEventService, CountDownLatch countDownLatch) {
        this.zzco = driveEventService;
        this.zzcn = countDownLatch;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            Looper.prepare();
            this.zzco.zzck = new DriveEventService.zza(this.zzco, null);
            this.zzco.zzcl = false;
            this.zzcn.countDown();
            Looper.loop();
        } finally {
            if (this.zzco.zzcj != null) {
                this.zzco.zzcj.countDown();
            }
        }
    }
}
