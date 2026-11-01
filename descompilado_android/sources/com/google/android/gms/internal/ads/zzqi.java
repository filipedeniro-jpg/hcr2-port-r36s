package com.google.android.gms.internal.ads;

import android.util.Base64OutputStream;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
final class zzqi {

    @VisibleForTesting
    private ByteArrayOutputStream zzbqh = new ByteArrayOutputStream(4096);

    @VisibleForTesting
    private Base64OutputStream zzbqi = new Base64OutputStream(this.zzbqh, 10);

    public final void write(byte[] bArr) throws IOException {
        this.zzbqi.write(bArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        try {
            this.zzbqi.close();
        } catch (IOException e) {
            zzatm.zzc("HashManager: Unable to convert to Base64.", e);
        }
        try {
            this.zzbqh.close();
            return this.zzbqh.toString();
        } catch (IOException e2) {
            zzatm.zzc("HashManager: Unable to convert to Base64.", e2);
            return "";
        } finally {
            this.zzbqh = null;
            this.zzbqi = null;
        }
    }
}
