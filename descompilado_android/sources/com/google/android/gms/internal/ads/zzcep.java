package com.google.android.gms.internal.ads;

import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcep {
    private final zzcen zzfvi;
    private final zzdcs zzfvj;

    public zzcep(zzcen zzcenVar, zzdcs zzdcsVar) {
        this.zzfvi = zzcenVar;
        this.zzfvj = zzdcsVar;
    }

    public final void zza(zzcwu<SQLiteDatabase, Void> zzcwuVar) {
        zzdcs zzdcsVar = this.zzfvj;
        zzcen zzcenVar = this.zzfvi;
        zzcenVar.getClass();
        zzdcf.zza(zzdcsVar.submit(zzces.zza(zzcenVar)), new zzcer(this, zzcwuVar), this.zzfvj);
    }
}
