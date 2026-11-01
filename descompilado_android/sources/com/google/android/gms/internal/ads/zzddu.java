package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public interface zzddu<P> {
    String getKeyType();

    int getVersion();

    P zza(zzdrn zzdrnVar) throws GeneralSecurityException;

    Class<P> zzapj();

    zzdrn zzb(zzdrn zzdrnVar) throws GeneralSecurityException;

    P zzp(zzdot zzdotVar) throws GeneralSecurityException;

    zzdrn zzq(zzdot zzdotVar) throws GeneralSecurityException;

    zzdiu zzr(zzdot zzdotVar) throws GeneralSecurityException;
}
