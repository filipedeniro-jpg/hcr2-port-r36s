package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdeh implements zzdef.zza {
    private final /* synthetic */ zzddu zzgsn;

    zzdeh(zzddu zzdduVar) {
        this.zzgsn = zzdduVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdef.zza
    public final <Q> zzddu<Q> zzb(Class<Q> cls) throws GeneralSecurityException {
        if (this.zzgsn.zzapj().equals(cls)) {
            return this.zzgsn;
        }
        throw new InternalError("This should never be called, as we always first check supportedPrimitives.");
    }

    @Override // com.google.android.gms.internal.ads.zzdef.zza
    public final zzddu<?> zzapr() {
        return this.zzgsn;
    }

    @Override // com.google.android.gms.internal.ads.zzdef.zza
    public final Class<?> zzaps() {
        return this.zzgsn.getClass();
    }

    @Override // com.google.android.gms.internal.ads.zzdef.zza
    public final Set<Class<?>> zzapt() {
        return Collections.singleton(this.zzgsn.zzapj());
    }
}
