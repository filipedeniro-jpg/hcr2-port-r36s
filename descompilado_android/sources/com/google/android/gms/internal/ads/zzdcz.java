package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzdcz<V> extends zzdcc<V> {

    @NullableDecl
    private zzdcp<V> zzgro;

    @NullableDecl
    private ScheduledFuture<?> zzgrp;

    static <V> zzdcp<V> zzb(zzdcp<V> zzdcpVar, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        zzdcz zzdczVar = new zzdcz(zzdcpVar);
        zzddb zzddbVar = new zzddb(zzdczVar);
        zzdczVar.zzgrp = scheduledExecutorService.schedule(zzddbVar, j, timeUnit);
        zzdcpVar.addListener(zzddbVar, zzdbx.INSTANCE);
        return zzdczVar;
    }

    private zzdcz(zzdcp<V> zzdcpVar) {
        this.zzgro = (zzdcp) zzczx.checkNotNull(zzdcpVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdbf
    protected final String pendingToString() {
        zzdcp<V> zzdcpVar = this.zzgro;
        ScheduledFuture<?> scheduledFuture = this.zzgrp;
        if (zzdcpVar == null) {
            return null;
        }
        String strValueOf = String.valueOf(zzdcpVar);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 14);
        sb.append("inputFuture=[");
        sb.append(strValueOf);
        sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
        String string = sb.toString();
        if (scheduledFuture == null) {
            return string;
        }
        long delay = scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
        if (delay <= 0) {
            return string;
        }
        String strValueOf2 = String.valueOf(string);
        StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 43);
        sb2.append(strValueOf2);
        sb2.append(", remaining delay=[");
        sb2.append(delay);
        sb2.append(" ms]");
        return sb2.toString();
    }

    @Override // com.google.android.gms.internal.ads.zzdbf
    protected final void afterDone() {
        maybePropagateCancellationTo(this.zzgro);
        ScheduledFuture<?> scheduledFuture = this.zzgrp;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.zzgro = null;
        this.zzgrp = null;
    }

    static /* synthetic */ ScheduledFuture zza(zzdcz zzdczVar, ScheduledFuture scheduledFuture) {
        zzdczVar.zzgrp = null;
        return null;
    }
}
