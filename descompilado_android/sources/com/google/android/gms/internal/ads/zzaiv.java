package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaiv {
    private static zzaiv zzdbf;
    private AtomicBoolean zzdbg = new AtomicBoolean(false);

    public static zzaiv zzrj() {
        if (zzdbf == null) {
            zzdbf = new zzaiv();
        }
        return zzdbf;
    }

    @VisibleForTesting
    zzaiv() {
    }

    @Nullable
    public final Thread zzc(final Context context, final String str) {
        if (!this.zzdbg.compareAndSet(false, true)) {
            return null;
        }
        Thread thread = new Thread(new Runnable(this, context, str) { // from class: com.google.android.gms.internal.ads.zzaiy
            private final Context zzcev;
            private final zzaiv zzdbi;
            private final String zzdbj;

            {
                this.zzdbi = this;
                this.zzcev = context;
                this.zzdbj = str;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzaiv.zzd(this.zzcev, this.zzdbj);
            }
        });
        thread.start();
        return thread;
    }

    @Nullable
    public final Thread zzl(final Context context) {
        if (!this.zzdbg.compareAndSet(false, true)) {
            return null;
        }
        Thread thread = new Thread(new Runnable(this, context) { // from class: com.google.android.gms.internal.ads.zzaix
            private final Context zzcev;
            private final zzaiv zzdbi;

            {
                this.zzdbi = this;
                this.zzcev = context;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzaiv.zzn(this.zzcev);
            }
        });
        thread.start();
        return thread;
    }

    private static void zza(Context context, AppMeasurementSdk appMeasurementSdk) {
        try {
            ((zzbdn) zzawr.zza(context, "com.google.android.gms.ads.measurement.DynamiteMeasurementManager", zzaja.zzbtn)).zza(ObjectWrapper.wrap(context), new zzaiw(appMeasurementSdk));
        } catch (RemoteException | zzawt | NullPointerException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    private static boolean zzm(Context context) {
        try {
            context.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    static /* synthetic */ void zzn(Context context) {
        zzyt.initialize(context);
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjl)).booleanValue() && zzm(context)) {
            zza(context, AppMeasurementSdk.getInstance(context));
        }
    }

    /* JADX WARN: Code duplicated, block: B:8:0x002a  */
    static /* synthetic */ void zzd(Context context, String str) {
        boolean z;
        zzyt.initialize(context);
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjh)).booleanValue()) {
            z = true;
        } else {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjg)).booleanValue()) {
                z = true;
            } else {
                z = false;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("measurementEnabled", z);
        zza(context, AppMeasurementSdk.getInstance(context, "FA-Ads", "am", str, bundle));
    }
}
