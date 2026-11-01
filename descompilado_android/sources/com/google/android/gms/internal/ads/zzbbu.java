package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.util.CollectionUtils;
import com.my.target.bj;
import com.my.target.m;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbbu extends zzwj {

    @GuardedBy("lock")
    private boolean zzabq;

    @GuardedBy("lock")
    private boolean zzabr;

    @GuardedBy("lock")
    private int zzacw;

    @GuardedBy("lock")
    private zzwl zzddd;
    private final zzayt zzdxm;
    private final boolean zzegx;
    private final boolean zzegy;

    @GuardedBy("lock")
    private boolean zzegz;

    @GuardedBy("lock")
    private float zzehb;

    @GuardedBy("lock")
    private float zzehc;

    @GuardedBy("lock")
    private float zzehd;
    private final Object lock = new Object();

    @GuardedBy("lock")
    private boolean zzeha = true;

    public zzbbu(zzayt zzaytVar, float f, boolean z, boolean z2) {
        this.zzdxm = zzaytVar;
        this.zzehb = f;
        this.zzegx = z;
        this.zzegy = z2;
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final void play() {
        zzf("play", null);
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final void pause() {
        zzf("pause", null);
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final void stop() {
        zzf(m.at, null);
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final void mute(boolean z) {
        zzf(z ? bj.gv : bj.gw, null);
    }

    public final void zzb(zzyc zzycVar) {
        boolean z = zzycVar.zzabp;
        boolean z2 = zzycVar.zzabq;
        boolean z3 = zzycVar.zzabr;
        synchronized (this.lock) {
            this.zzabq = z2;
            this.zzabr = z3;
        }
        zzf("initialState", CollectionUtils.mapOf("muteStart", z ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO, "customControlsRequested", z2 ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO, "clickToExpandRequested", z3 ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO));
    }

    private final void zzf(String str, @Nullable Map<String, String> map) {
        final HashMap map2 = map == null ? new HashMap() : new HashMap(map);
        map2.put("action", str);
        zzawx.zzdwa.execute(new Runnable(this, map2) { // from class: com.google.android.gms.internal.ads.zzbbx
            private final Map zzdvp;
            private final zzbbu zzehe;

            {
                this.zzehe = this;
                this.zzdvp = map2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzehe.zzj(this.zzdvp);
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final boolean isMuted() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzeha;
        }
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final int getPlaybackState() {
        int i;
        synchronized (this.lock) {
            i = this.zzacw;
        }
        return i;
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final float getAspectRatio() {
        float f;
        synchronized (this.lock) {
            f = this.zzehd;
        }
        return f;
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final float zzot() {
        float f;
        synchronized (this.lock) {
            f = this.zzehb;
        }
        return f;
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final float zzou() {
        float f;
        synchronized (this.lock) {
            f = this.zzehc;
        }
        return f;
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final void zza(zzwl zzwlVar) {
        synchronized (this.lock) {
            this.zzddd = zzwlVar;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final zzwl zzov() throws RemoteException {
        zzwl zzwlVar;
        synchronized (this.lock) {
            zzwlVar = this.zzddd;
        }
        return zzwlVar;
    }

    @Override // com.google.android.gms.internal.ads.zzwk
    public final boolean isCustomControlsEnabled() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzegx && this.zzabq;
        }
        return z;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0015  */
    @Override // com.google.android.gms.internal.ads.zzwk
    public final boolean isClickToExpandEnabled() {
        boolean z;
        boolean zIsCustomControlsEnabled = isCustomControlsEnabled();
        synchronized (this.lock) {
            if (zIsCustomControlsEnabled) {
                z = false;
            } else {
                try {
                    if (this.zzabr && this.zzegy) {
                        z = true;
                    } else {
                        z = false;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return z;
    }

    public final void zze(float f) {
        synchronized (this.lock) {
            this.zzehc = f;
        }
    }

    public final void zzaal() {
        boolean z;
        int i;
        synchronized (this.lock) {
            z = this.zzeha;
            i = this.zzacw;
            this.zzacw = 3;
        }
        zza(i, 3, z, z);
    }

    public final void zza(float f, float f2, int i, boolean z, float f3) {
        boolean z2;
        int i2;
        synchronized (this.lock) {
            this.zzehb = f2;
            this.zzehc = f;
            z2 = this.zzeha;
            this.zzeha = z;
            i2 = this.zzacw;
            this.zzacw = i;
            float f4 = this.zzehd;
            this.zzehd = f3;
            if (Math.abs(this.zzehd - f4) > 1.0E-4f) {
                this.zzdxm.getView().invalidate();
            }
        }
        zza(i2, i, z2, z);
    }

    private final void zza(final int i, final int i2, final boolean z, final boolean z2) {
        zzawx.zzdwa.execute(new Runnable(this, i, i2, z, z2) { // from class: com.google.android.gms.internal.ads.zzbbw
            private final int zzdsy;
            private final int zzdsz;
            private final boolean zzeey;
            private final boolean zzeez;
            private final zzbbu zzehe;

            {
                this.zzehe = this;
                this.zzdsy = i;
                this.zzdsz = i2;
                this.zzeey = z;
                this.zzeez = z2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzehe.zzb(this.zzdsy, this.zzdsz, this.zzeey, this.zzeez);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:50:0x005e A[Catch: RemoteException -> 0x0044, all -> 0x007f, TryCatch #0 {RemoteException -> 0x0044, blocks: (B:36:0x003a, B:38:0x003e, B:42:0x0048, B:44:0x004c, B:46:0x0053, B:48:0x0057, B:50:0x005e, B:52:0x0062, B:53:0x0067, B:55:0x006e, B:57:0x0072), top: B:65:0x003a, outer: #1 }] */
    /* JADX WARN: Code duplicated, block: B:52:0x0062 A[Catch: RemoteException -> 0x0044, all -> 0x007f, TryCatch #0 {RemoteException -> 0x0044, blocks: (B:36:0x003a, B:38:0x003e, B:42:0x0048, B:44:0x004c, B:46:0x0053, B:48:0x0057, B:50:0x005e, B:52:0x0062, B:53:0x0067, B:55:0x006e, B:57:0x0072), top: B:65:0x003a, outer: #1 }] */
    final /* synthetic */ void zzb(int i, int i2, boolean z, boolean z2) {
        synchronized (this.lock) {
            boolean z3 = i != i2;
            boolean z4 = !this.zzegz && i2 == 1;
            boolean z5 = z3 && i2 == 1;
            boolean z6 = z3 && i2 == 2;
            boolean z7 = z3 && i2 == 3;
            boolean z8 = z != z2;
            this.zzegz = this.zzegz || z4;
            if (z4) {
                try {
                    if (this.zzddd != null) {
                        this.zzddd.onVideoStart();
                    }
                    if (z5 && this.zzddd != null) {
                        this.zzddd.onVideoPlay();
                    }
                    if (z6 && this.zzddd != null) {
                        this.zzddd.onVideoPause();
                    }
                    if (z7) {
                        if (this.zzddd != null) {
                            this.zzddd.onVideoEnd();
                        }
                        this.zzdxm.zzxs();
                    }
                    if (z8 && this.zzddd != null) {
                        this.zzddd.onVideoMute(z2);
                    }
                } catch (RemoteException e) {
                    zzawo.zze("#007 Could not call remote method.", e);
                }
            } else {
                if (z5) {
                    this.zzddd.onVideoPlay();
                }
                if (z6) {
                    this.zzddd.onVideoPause();
                }
                if (z7) {
                    if (this.zzddd != null) {
                        this.zzddd.onVideoEnd();
                    }
                    this.zzdxm.zzxs();
                }
                if (z8) {
                    this.zzddd.onVideoMute(z2);
                }
            }
            throw th;
        }
    }

    final /* synthetic */ void zzj(Map map) {
        this.zzdxm.zza("pubVideoCmd", (Map<String, ?>) map);
    }
}
