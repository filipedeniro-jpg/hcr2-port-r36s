package com.google.android.gms.internal.ads;

import java.security.SecureRandom;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdnq {
    private static final ThreadLocal<SecureRandom> zzhei = new zzdnt();

    /* JADX INFO: Access modifiers changed from: private */
    public static SecureRandom zzaww() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextLong();
        return secureRandom;
    }

    public static byte[] zzfg(int i) {
        byte[] bArr = new byte[i];
        zzhei.get().nextBytes(bArr);
        return bArr;
    }
}
