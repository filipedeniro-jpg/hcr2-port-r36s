package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import androidx.annotation.Nullable;
import java.io.InputStream;
import java.util.concurrent.Future;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzrr {
    private final Object lock = new Object();

    @Nullable
    @GuardedBy("lock")
    private zzri zzbqw;

    @GuardedBy("lock")
    private boolean zzbrh;
    private final Context zzlk;

    zzrr(Context context) {
        this.zzlk = context;
    }

    final Future<InputStream> zzb(zzrl zzrlVar) {
        zzrq zzrqVar = new zzrq(this);
        zzrt zzrtVar = new zzrt(this, zzrlVar, zzrqVar);
        zzrx zzrxVar = new zzrx(this, zzrqVar);
        synchronized (this.lock) {
            this.zzbqw = new zzri(this.zzlk, com.google.android.gms.ads.internal.zzp.zzkm().zzvz(), zzrtVar, zzrxVar);
            this.zzbqw.checkAvailabilityAndConnect();
        }
        return zzrqVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void disconnect() {
        synchronized (this.lock) {
            if (this.zzbqw == null) {
                return;
            }
            this.zzbqw.disconnect();
            this.zzbqw = null;
            Binder.flushPendingCommands();
        }
    }

    static /* synthetic */ boolean zza(zzrr zzrrVar, boolean z) {
        zzrrVar.zzbrh = true;
        return true;
    }
}
