package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzddx {
    private static final CopyOnWriteArrayList<zzddy> zzgsc = new CopyOnWriteArrayList<>();

    public static zzddy zzgp(String str) throws GeneralSecurityException {
        for (zzddy zzddyVar : zzgsc) {
            if (zzddyVar.zzgq(str)) {
                return zzddyVar;
            }
        }
        String strValueOf = String.valueOf(str);
        throw new GeneralSecurityException(strValueOf.length() != 0 ? "No KMS client does support: ".concat(strValueOf) : new String("No KMS client does support: "));
    }
}
