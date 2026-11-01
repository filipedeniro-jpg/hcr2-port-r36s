package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzarg implements Runnable {
    private final /* synthetic */ Bitmap val$bitmap;
    private final /* synthetic */ zzarf zzdoh;

    zzarg(zzarf zzarfVar, Bitmap bitmap) {
        this.zzdoh = zzarfVar;
        this.val$bitmap = bitmap;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.val$bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        synchronized (this.zzdoh.lock) {
            this.zzdoh.zzdnw.zzhuy = new zzdup();
            this.zzdoh.zzdnw.zzhuy.zzhvx = byteArrayOutputStream.toByteArray();
            this.zzdoh.zzdnw.zzhuy.mimeType = "image/png";
            this.zzdoh.zzdnw.zzhuy.zzhvw = zzdua.zzb.zzf.EnumC0051zzb.TYPE_CREATIVE;
        }
    }
}
