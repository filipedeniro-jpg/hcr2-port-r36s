package com.google.android.gms.internal.ads;

import android.content.Context;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcvt {
    public static void zzc(Throwable th, String str) {
        int iZzd = zzcce.zzd(th);
        StringBuilder sb = new StringBuilder(31);
        sb.append("Ad failed to load : ");
        sb.append(iZzd);
        zzatm.zzet(sb.toString());
        zzatm.zza(str, th);
        if (zzcce.zzd(th) == 3) {
            return;
        }
        com.google.android.gms.ads.internal.zzp.zzkc().zzb(th, str);
    }

    public static void zze(Context context, boolean z) {
        if (z) {
            zzatm.zzet("This request is sent from a test device.");
            return;
        }
        zzuo.zzof();
        String strZzbh = zzawe.zzbh(context);
        StringBuilder sb = new StringBuilder(String.valueOf(strZzbh).length() + 71);
        sb.append("Use AdRequest.Builder.addTestDevice(\"");
        sb.append(strZzbh);
        sb.append("\") to get test ads on this device.");
        zzatm.zzet(sb.toString());
    }
}
