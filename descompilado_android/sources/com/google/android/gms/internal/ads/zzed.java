package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzed {
    static zzdds zzyo;

    static boolean zzb(zzdx zzdxVar) throws IllegalAccessException, GeneralSecurityException, InvocationTargetException {
        Method methodZzc;
        if (zzyo != null) {
            return true;
        }
        String str = (String) zzuo.zzoj().zzd(zzyt.zzcna);
        if (str == null || str.length() == 0) {
            str = (zzdxVar == null || (methodZzc = zzdxVar.zzc("Rx/heC2JEYsbYRXDgEN1gsty+F6bGgJ1u7nhpEb4M3zTVmNdSKl8jv7T9QBOGiBo", "VJKYVzPjjk7QpAOgA66fh/W7w026ne08y+a0kIFLwtw=")) == null) ? null : (String) methodZzc.invoke(null, new Object[0]);
            if (str == null) {
                return false;
            }
        }
        try {
            zzddw zzddwVarZzj = zzddz.zzj(zzcg.zza(str, true));
            zzddl.zza(zzdff.zzgso);
            zzyo = zzdfh.zza(zzddwVarZzj, null);
            return zzyo != null;
        } catch (IllegalArgumentException | GeneralSecurityException unused) {
        }
    }
}
