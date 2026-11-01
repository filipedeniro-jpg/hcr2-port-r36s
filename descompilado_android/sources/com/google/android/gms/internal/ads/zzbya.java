package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbya<T> implements zzadx<Object> {
    private final WeakReference<T> zzfpn;
    private final String zzfpo;
    private final zzadx<T> zzfpp;
    private final /* synthetic */ zzbxn zzfpq;

    private zzbya(zzbxn zzbxnVar, WeakReference<T> weakReference, String str, zzadx<T> zzadxVar) {
        this.zzfpq = zzbxnVar;
        this.zzfpn = weakReference;
        this.zzfpo = str;
        this.zzfpp = zzadxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final void zza(Object obj, Map<String, String> map) {
        T t = this.zzfpn.get();
        if (t == null) {
            this.zzfpq.zzb(this.zzfpo, this);
        } else {
            this.zzfpp.zza(t, map);
        }
    }

    /* synthetic */ zzbya(zzbxn zzbxnVar, WeakReference weakReference, String str, zzadx zzadxVar, zzbxs zzbxsVar) {
        this(zzbxnVar, weakReference, str, zzadxVar);
    }
}
