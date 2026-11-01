package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.TelephonyManager;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(26)
public class zzauj extends zzaug {
    @Override // com.google.android.gms.internal.ads.zzaub
    public final zzso zza(Context context, TelephonyManager telephonyManager) {
        com.google.android.gms.ads.internal.zzp.zzjy();
        if (zzatv.zzq(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return telephonyManager.isDataEnabled() ? zzso.ENUM_TRUE : zzso.ENUM_FALSE;
        }
        return zzso.ENUM_FALSE;
    }
}
