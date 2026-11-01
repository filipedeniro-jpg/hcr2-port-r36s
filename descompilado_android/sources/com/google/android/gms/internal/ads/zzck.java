package com.google.android.gms.internal.ads;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzck implements Runnable {
    private zzck() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            MessageDigest unused = zzci.zznu = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException unused2) {
        } finally {
            zzci.zznx.countDown();
        }
    }
}
