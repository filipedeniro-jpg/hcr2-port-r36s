package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public interface zzgv extends zzgg {
    void disable();

    int getState();

    int getTrackType();

    boolean isReady();

    void setIndex(int i);

    void start() throws zzgb;

    void stop() throws zzgb;

    void zza(zzgx zzgxVar, zzgq[] zzgqVarArr, zzmf zzmfVar, long j, boolean z, long j2) throws zzgb;

    void zza(zzgq[] zzgqVarArr, zzmf zzmfVar, long j) throws zzgb;

    void zzb(long j, long j2) throws zzgb;

    zzgy zzdg();

    void zzdg(long j) throws zzgb;

    zznx zzdh();

    zzmf zzdi();

    boolean zzdj();

    void zzdk();

    boolean zzdl();

    void zzdm() throws IOException;

    boolean zzel();
}
