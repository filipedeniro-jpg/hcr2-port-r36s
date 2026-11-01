package com.google.android.gms.internal.ads;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcfg {
    private zzawv zzdid;
    private zzcep zzfwc;
    private zzrz zzfwo;
    private Context zzlk;

    public zzcfg(Context context, zzawv zzawvVar, zzrz zzrzVar, zzcep zzcepVar) {
        this.zzlk = context;
        this.zzdid = zzawvVar;
        this.zzfwo = zzrzVar;
        this.zzfwc = zzcepVar;
    }

    public final void zzakn() {
        try {
            this.zzfwc.zza(new zzcwu(this) { // from class: com.google.android.gms.internal.ads.zzcff
                private final zzcfg zzfwn;

                {
                    this.zzfwn = this;
                }

                @Override // com.google.android.gms.internal.ads.zzcwu
                public final Object apply(Object obj) {
                    return this.zzfwn.zzb((SQLiteDatabase) obj);
                }
            });
        } catch (Exception e) {
            String strValueOf = String.valueOf(e.getMessage());
            zzatm.zzes(strValueOf.length() != 0 ? "Error in offline signals database startup: ".concat(strValueOf) : new String("Error in offline signals database startup: "));
        }
    }

    final /* synthetic */ Void zzb(SQLiteDatabase sQLiteDatabase) throws Exception {
        ArrayList<zzsh.zzj.zza> arrayListZza = zzcfd.zza(sQLiteDatabase);
        final zzsh.zzj zzjVar = (zzsh.zzj) ((zzdqd) zzsh.zzj.zzne().zzbv(this.zzlk.getPackageName()).zzbw(Build.MODEL).zzcc(zzcfd.zza(sQLiteDatabase, 0)).zzd(arrayListZza).zzcd(zzcfd.zza(sQLiteDatabase, 1)).zzep(com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis()).zzeq(zzcfd.zzb(sQLiteDatabase, 2)).zzazm());
        ArrayList<zzsh.zzj.zza> arrayList = arrayListZza;
        int size = arrayList.size();
        long timestamp = 0;
        int i = 0;
        while (i < size) {
            zzsh.zzj.zza zzaVar = arrayList.get(i);
            i++;
            zzsh.zzj.zza zzaVar2 = zzaVar;
            if (zzaVar2.zznb() == zzso.ENUM_TRUE && zzaVar2.getTimestamp() > timestamp) {
                timestamp = zzaVar2.getTimestamp();
            }
        }
        if (timestamp != 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("value", Long.valueOf(timestamp));
            sQLiteDatabase.update("offline_signal_statistics", contentValues, "statistic_name = 'last_successful_request_time'", null);
        }
        this.zzfwo.zza(new zzry(zzjVar) { // from class: com.google.android.gms.internal.ads.zzcfi
            private final zzsh.zzj zzfwq;

            {
                this.zzfwq = zzjVar;
            }

            @Override // com.google.android.gms.internal.ads.zzry
            public final void zza(zztd zztdVar) {
                zztdVar.zzcam = this.zzfwq;
            }
        });
        final zztg zztgVar = new zztg();
        zztgVar.zzcbg = Integer.valueOf(this.zzdid.zzdvs);
        zztgVar.zzcbh = Integer.valueOf(this.zzdid.zzdvt);
        zztgVar.zzcbi = Integer.valueOf(this.zzdid.zzdvu ? 0 : 2);
        this.zzfwo.zza(new zzry(zztgVar) { // from class: com.google.android.gms.internal.ads.zzcfh
            private final zztg zzfwp;

            {
                this.zzfwp = zztgVar;
            }

            @Override // com.google.android.gms.internal.ads.zzry
            public final void zza(zztd zztdVar) {
                zztdVar.zzcai.zzbzs = this.zzfwp;
            }
        });
        this.zzfwo.zza(zzsb.zza.zzb.OFFLINE_UPLOAD);
        sQLiteDatabase.delete("offline_signal_contents", null, null);
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("value", (Integer) 0);
        sQLiteDatabase.update("offline_signal_statistics", contentValues2, "statistic_name = ?", new String[]{"failed_requests"});
        ContentValues contentValues3 = new ContentValues();
        contentValues3.put("value", (Integer) 0);
        sQLiteDatabase.update("offline_signal_statistics", contentValues3, "statistic_name = ?", new String[]{"total_requests"});
        return null;
    }
}
