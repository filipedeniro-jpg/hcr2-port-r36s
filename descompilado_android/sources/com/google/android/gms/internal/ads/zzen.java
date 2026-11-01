package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzen extends zzfl {
    private static zzfk<String> zzzw = new zzfk<>();
    private final Context zzzu;

    public zzen(zzdx zzdxVar, String str, String str2, zzbo.zza.zzb zzbVar, int i, int i2, Context context) {
        super(zzdxVar, str, str2, zzbVar, i, 29);
        this.zzzu = context;
    }

    @Override // com.google.android.gms.internal.ads.zzfl
    protected final void zzct() throws IllegalAccessException, InvocationTargetException {
        this.zzaaa.zzac("E");
        AtomicReference<String> atomicReferenceZzau = zzzw.zzau(this.zzzu.getPackageName());
        if (atomicReferenceZzau.get() == null) {
            synchronized (atomicReferenceZzau) {
                if (atomicReferenceZzau.get() == null) {
                    atomicReferenceZzau.set((String) this.zzaal.invoke(null, this.zzzu));
                }
            }
        }
        String str = atomicReferenceZzau.get();
        synchronized (this.zzaaa) {
            this.zzaaa.zzac(zzcg.zza(str.getBytes(), true));
        }
    }
}
