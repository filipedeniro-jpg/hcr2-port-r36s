package com.google.firebase.auth.internal;

import android.os.Handler;
import android.os.HandlerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.FirebaseApp;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaa {
    private static Logger zzc = new Logger("TokenRefresher", "FirebaseAuth:");

    @VisibleForTesting
    volatile long zza;

    @VisibleForTesting
    volatile long zzb;
    private final FirebaseApp zzd;

    @VisibleForTesting
    private long zze;

    @VisibleForTesting
    private HandlerThread zzf;

    @VisibleForTesting
    private Handler zzg;

    @VisibleForTesting
    private Runnable zzh;

    public zzaa(FirebaseApp firebaseApp) {
        zzc.v("Initializing TokenRefresher", new Object[0]);
        this.zzd = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        this.zzf = new HandlerThread("TokenRefresher", 10);
        this.zzf.start();
        this.zzg = new com.google.android.gms.internal.firebase_auth.zzj(this.zzf.getLooper());
        this.zzh = new zzz(this, this.zzd.getName());
        this.zze = 300000L;
    }

    public final void zza() {
        Logger logger = zzc;
        long j = this.zza - this.zze;
        StringBuilder sb = new StringBuilder(43);
        sb.append("Scheduling refresh for ");
        sb.append(j);
        logger.v(sb.toString(), new Object[0]);
        zzc();
        this.zzb = Math.max((this.zza - DefaultClock.getInstance().currentTimeMillis()) - this.zze, 0L) / 1000;
        this.zzg.postDelayed(this.zzh, this.zzb * 1000);
    }

    final void zzb() {
        long j;
        int i = (int) this.zzb;
        if (i == 30 || i == 60 || i == 120 || i == 240 || i == 480) {
            j = 2 * this.zzb;
        } else {
            j = i != 960 ? 30L : 960L;
        }
        this.zzb = j;
        this.zza = DefaultClock.getInstance().currentTimeMillis() + (this.zzb * 1000);
        Logger logger = zzc;
        long j2 = this.zza;
        StringBuilder sb = new StringBuilder(43);
        sb.append("Scheduling refresh for ");
        sb.append(j2);
        logger.v(sb.toString(), new Object[0]);
        this.zzg.postDelayed(this.zzh, this.zzb * 1000);
    }

    public final void zzc() {
        this.zzg.removeCallbacks(this.zzh);
    }
}
