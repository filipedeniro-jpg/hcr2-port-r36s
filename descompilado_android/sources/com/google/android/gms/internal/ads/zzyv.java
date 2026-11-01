package com.google.android.gms.internal.ads;

import java.util.regex.Pattern;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzyv {
    public static boolean zzck(String str) {
        return zzh((String) zzuo.zzoj().zzd(zzyt.zzcqy), str);
    }

    private static boolean zzh(String str, String str2) {
        if (str != null && str2 != null) {
            try {
                return Pattern.matches(str, str2);
            } catch (RuntimeException e) {
                com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "NonagonUtil.isPatternMatched");
            }
        }
        return false;
    }
}
