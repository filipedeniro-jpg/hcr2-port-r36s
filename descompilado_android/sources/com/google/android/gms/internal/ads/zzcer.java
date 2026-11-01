package com.google.android.gms.internal.ads;

import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcer implements zzdcg<SQLiteDatabase> {
    private final /* synthetic */ zzcwu zzfvk;

    zzcer(zzcep zzcepVar, zzcwu zzcwuVar) {
        this.zzfvk = zzcwuVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        String strValueOf = String.valueOf(th.getMessage());
        zzatm.zzes(strValueOf.length() != 0 ? "Failed to get offline signal database: ".concat(strValueOf) : new String("Failed to get offline signal database: "));
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(SQLiteDatabase sQLiteDatabase) {
        try {
            this.zzfvk.apply(sQLiteDatabase);
        } catch (Exception e) {
            String strValueOf = String.valueOf(e.getMessage());
            zzatm.zzes(strValueOf.length() != 0 ? "Error executing function on offline signal database: ".concat(strValueOf) : new String("Error executing function on offline signal database: "));
        }
    }
}
