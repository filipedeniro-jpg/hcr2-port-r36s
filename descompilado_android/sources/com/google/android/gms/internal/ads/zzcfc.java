package com.google.android.gms.internal.ads;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcfc implements zzdcg<Bundle> {
    private final /* synthetic */ boolean zzfwk;
    final /* synthetic */ zzcez zzfwl;

    zzcfc(zzcez zzcezVar, boolean z) {
        this.zzfwl = zzcezVar;
        this.zzfwk = z;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        zzatm.zzes("Failed to get signals bundle");
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(Bundle bundle) {
        Bundle bundle2 = bundle;
        zzcez zzcezVar = this.zzfwl;
        final ArrayList arrayListZzl = zzcez.zzl(bundle2);
        zzcez zzcezVar2 = this.zzfwl;
        final zzsh.zzj.zzb zzbVarZzk = zzcez.zzk(bundle2);
        final zzsh.zzh zzhVarZzj = this.zzfwl.zzj(bundle2);
        zzcep zzcepVar = this.zzfwl.zzfwc;
        final boolean z = this.zzfwk;
        zzcepVar.zza(new zzcwu(this, z, arrayListZzl, zzhVarZzj, zzbVarZzk) { // from class: com.google.android.gms.internal.ads.zzcfb
            private final boolean zzdye;
            private final zzcfc zzfwg;
            private final ArrayList zzfwh;
            private final zzsh.zzh zzfwi;
            private final zzsh.zzj.zzb zzfwj;

            {
                this.zzfwg = this;
                this.zzdye = z;
                this.zzfwh = arrayListZzl;
                this.zzfwi = zzhVarZzj;
                this.zzfwj = zzbVarZzk;
            }

            @Override // com.google.android.gms.internal.ads.zzcwu
            public final Object apply(Object obj) {
                zzcfc zzcfcVar = this.zzfwg;
                boolean z2 = this.zzdye;
                SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) obj;
                byte[] bArrZza = zzcfcVar.zzfwl.zza(z2, this.zzfwh, this.zzfwi, this.zzfwj);
                ContentValues contentValues = new ContentValues();
                contentValues.put("timestamp", Long.valueOf(com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis()));
                contentValues.put("serialized_proto_data", bArrZza);
                sQLiteDatabase.insert("offline_signal_contents", null, contentValues);
                sQLiteDatabase.execSQL(String.format("UPDATE offline_signal_statistics SET value = value+1 WHERE statistic_name = '%s'", "total_requests"));
                if (!z2) {
                    sQLiteDatabase.execSQL(String.format("UPDATE offline_signal_statistics SET value = value+1 WHERE statistic_name = '%s'", "failed_requests"));
                }
                return null;
            }
        });
    }
}
