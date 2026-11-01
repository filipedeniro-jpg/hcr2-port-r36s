package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzavt {
    private Map<Integer, Bitmap> zzduk = new ConcurrentHashMap();
    private AtomicInteger zzdul = new AtomicInteger(0);

    public final Bitmap zza(Integer num) {
        return this.zzduk.get(num);
    }
}
