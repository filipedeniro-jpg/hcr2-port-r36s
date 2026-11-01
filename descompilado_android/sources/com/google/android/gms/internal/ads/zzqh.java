package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public abstract class zzqh {

    @Nullable
    private static MessageDigest zzbqg;
    protected Object mLock = new Object();

    abstract byte[] zzbr(String str);

    @Nullable
    protected final MessageDigest zzmb() {
        synchronized (this.mLock) {
            if (zzbqg != null) {
                return zzbqg;
            }
            for (int i = 0; i < 2; i++) {
                try {
                    zzbqg = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return zzbqg;
        }
    }
}
