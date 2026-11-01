package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcrk implements zzcra<zzcrl> {
    private final zzdcs zzfnq;
    private final Context zzlk;

    public zzcrk(zzdcs zzdcsVar, Context context) {
        this.zzfnq = zzdcsVar;
        this.zzlk = context;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcrl> zzalr() {
        return this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcrn
            private final zzcrk zzggd;

            {
                this.zzggd = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzggd.zzamf();
            }
        });
    }

    final /* synthetic */ zzcrl zzamf() throws Exception {
        int i;
        boolean zIsActiveNetworkMetered;
        int i2;
        int i3;
        TelephonyManager telephonyManager = (TelephonyManager) this.zzlk.getSystemService("phone");
        String networkOperator = telephonyManager.getNetworkOperator();
        int networkType = telephonyManager.getNetworkType();
        int phoneType = telephonyManager.getPhoneType();
        com.google.android.gms.ads.internal.zzp.zzjy();
        int i4 = -1;
        if (zzatv.zzq(this.zzlk, "android.permission.ACCESS_NETWORK_STATE")) {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.zzlk.getSystemService("connectivity");
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                int type = activeNetworkInfo.getType();
                int iOrdinal = activeNetworkInfo.getDetailedState().ordinal();
                i3 = type;
                i4 = iOrdinal;
            } else {
                i3 = -1;
            }
            if (Build.VERSION.SDK_INT >= 16) {
                i2 = i4;
                i = i3;
                zIsActiveNetworkMetered = connectivityManager.isActiveNetworkMetered();
            } else {
                i2 = i4;
                i = i3;
                zIsActiveNetworkMetered = false;
            }
        } else {
            i = -2;
            zIsActiveNetworkMetered = false;
            i2 = -1;
        }
        return new zzcrl(networkOperator, i, networkType, phoneType, zIsActiveNetworkMetered, i2);
    }
}
