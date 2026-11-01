package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.ads.zzatm;
import com.google.android.gms.internal.ads.zzawv;
import com.google.android.gms.internal.ads.zzawx;
import com.google.android.gms.internal.ads.zzdc;
import com.google.android.gms.internal.ads.zzdg;
import com.google.android.gms.internal.ads.zzuo;
import com.google.android.gms.internal.ads.zzyt;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzf implements zzdc, Runnable {
    private zzawv zzbkw;
    private Context zzlk;
    private final List<Object[]> zzbku = new Vector();
    private final AtomicReference<zzdc> zzbkv = new AtomicReference<>();
    private CountDownLatch zzbkx = new CountDownLatch(1);

    public zzf(Context context, zzawv zzawvVar) {
        this.zzlk = context;
        this.zzbkw = zzawvVar;
        zzawx.zzdvx.execute(this);
    }

    private final boolean zzji() {
        try {
            this.zzbkx.await();
            return true;
        } catch (InterruptedException e) {
            zzatm.zzd("Interrupted during GADSignals creation.", e);
            return false;
        }
    }

    private final void zzjj() {
        if (this.zzbku.isEmpty()) {
            return;
        }
        for (Object[] objArr : this.zzbku) {
            if (objArr.length == 1) {
                this.zzbkv.get().zzb((MotionEvent) objArr[0]);
            } else if (objArr.length == 3) {
                this.zzbkv.get().zza(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
            }
        }
        this.zzbku.clear();
    }

    private static Context zze(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }

    @Override // com.google.android.gms.internal.ads.zzdc
    public final String zza(Context context) {
        zzdc zzdcVar;
        if (!zzji() || (zzdcVar = this.zzbkv.get()) == null) {
            return "";
        }
        zzjj();
        return zzdcVar.zza(zze(context));
    }

    @Override // com.google.android.gms.internal.ads.zzdc
    public final String zza(Context context, String str, View view) {
        return zza(context, str, view, null);
    }

    @Override // com.google.android.gms.internal.ads.zzdc
    public final String zza(Context context, String str, View view, Activity activity) {
        zzdc zzdcVar;
        if (!zzji() || (zzdcVar = this.zzbkv.get()) == null) {
            return "";
        }
        zzjj();
        return zzdcVar.zza(zze(context), str, view, activity);
    }

    @Override // com.google.android.gms.internal.ads.zzdc
    public final void zzb(View view) {
        zzdc zzdcVar = this.zzbkv.get();
        if (zzdcVar != null) {
            zzdcVar.zzb(view);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdc
    public final void zzb(MotionEvent motionEvent) {
        zzdc zzdcVar = this.zzbkv.get();
        if (zzdcVar != null) {
            zzjj();
            zzdcVar.zzb(motionEvent);
        } else {
            this.zzbku.add(new Object[]{motionEvent});
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdc
    public final void zza(int i, int i2, int i3) {
        zzdc zzdcVar = this.zzbkv.get();
        if (zzdcVar != null) {
            zzjj();
            zzdcVar.zza(i, i2, i3);
        } else {
            this.zzbku.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        boolean z = false;
        try {
            boolean z2 = this.zzbkw.zzdvu;
            if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzckl)).booleanValue() && z2) {
                z = true;
            }
            this.zzbkv.set(zzdg.zza(this.zzbkw.zzbnh, zze(this.zzlk), z));
        } finally {
            this.zzbkx.countDown();
            this.zzlk = null;
            this.zzbkw = null;
        }
    }
}
