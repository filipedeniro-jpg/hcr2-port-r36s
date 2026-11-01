package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzddb<V> implements Runnable {

    @NullableDecl
    private zzdcz<V> zzgrq;

    zzddb(zzdcz<V> zzdczVar) {
        this.zzgrq = zzdczVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzdcp<? extends V> zzdcpVar;
        zzdcz<V> zzdczVar = this.zzgrq;
        if (zzdczVar == null || (zzdcpVar = ((zzdcz) zzdczVar).zzgro) == null) {
            return;
        }
        this.zzgrq = null;
        if (zzdcpVar.isDone()) {
            zzdczVar.setFuture(zzdcpVar);
            return;
        }
        try {
            ScheduledFuture scheduledFuture = ((zzdcz) zzdczVar).zzgrp;
            zzdcz.zza((zzdcz) zzdczVar, (ScheduledFuture) null);
            String string = "Timed out";
            if (scheduledFuture != null) {
                try {
                    long jAbs = Math.abs(scheduledFuture.getDelay(TimeUnit.MILLISECONDS));
                    if (jAbs > 10) {
                        StringBuilder sb = new StringBuilder("Timed out".length() + 66);
                        sb.append("Timed out");
                        sb.append(" (timeout delayed by ");
                        sb.append(jAbs);
                        sb.append(" ms after scheduled time)");
                        string = sb.toString();
                    }
                } catch (Throwable th) {
                    zzdczVar.setException(new zzdde(string));
                    throw th;
                }
            }
            String strValueOf = String.valueOf(string);
            String strValueOf2 = String.valueOf(zzdcpVar);
            StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf).length() + 2 + String.valueOf(strValueOf2).length());
            sb2.append(strValueOf);
            sb2.append(": ");
            sb2.append(strValueOf2);
            zzdczVar.setException(new zzdde(sb2.toString()));
            zzdcpVar.cancel(true);
        } catch (Throwable th2) {
            zzdcpVar.cancel(true);
            throw th2;
        }
    }
}
