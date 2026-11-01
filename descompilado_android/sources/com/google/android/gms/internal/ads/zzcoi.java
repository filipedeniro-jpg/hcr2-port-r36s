package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcoi implements zzcra<zzcoj> {
    private final zzdcs zzfnq;
    private final Context zzlk;

    public zzcoi(zzdcs zzdcsVar, Context context) {
        this.zzfnq = zzdcsVar;
        this.zzlk = context;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcoj> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcol
            private final zzcoi zzgej;

            {
                this.zzgej = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzgej.zzalv();
            }
        });
    }

    final /* synthetic */ zzcoj zzalv() throws Exception {
        double d;
        Intent intentRegisterReceiver = this.zzlk.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        boolean z = false;
        if (intentRegisterReceiver != null) {
            int intExtra = intentRegisterReceiver.getIntExtra("status", -1);
            double intExtra2 = intentRegisterReceiver.getIntExtra("level", -1);
            double intExtra3 = intentRegisterReceiver.getIntExtra("scale", -1);
            Double.isNaN(intExtra2);
            Double.isNaN(intExtra3);
            d = intExtra2 / intExtra3;
            if (intExtra == 2 || intExtra == 5) {
                z = true;
            }
        } else {
            d = -1.0d;
        }
        return new zzcoj(d, z);
    }
}
