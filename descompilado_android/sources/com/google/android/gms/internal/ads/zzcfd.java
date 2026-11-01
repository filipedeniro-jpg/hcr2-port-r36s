package com.google.android.gms.internal.ads;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcfd {
    public static ArrayList<zzsh.zzj.zza> zza(SQLiteDatabase sQLiteDatabase) {
        ArrayList<zzsh.zzj.zza> arrayList = new ArrayList<>();
        Cursor cursorQuery = sQLiteDatabase.query("offline_signal_contents", new String[]{"serialized_proto_data"}, null, null, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                arrayList.add(zzsh.zzj.zza.zzg(cursorQuery.getBlob(cursorQuery.getColumnIndexOrThrow("serialized_proto_data"))));
            } catch (zzdqn e) {
                zzatm.zzes("Unable to deserialize proto from offline signals database:");
                zzatm.zzes(e.getMessage());
            }
        }
        cursorQuery.close();
        return arrayList;
    }

    public static int zza(SQLiteDatabase sQLiteDatabase, int i) {
        int i2 = 0;
        if (i == 2) {
            return 0;
        }
        Cursor cursorZzc = zzc(sQLiteDatabase, i);
        if (cursorZzc.getCount() > 0) {
            cursorZzc.moveToNext();
            i2 = 0 + cursorZzc.getInt(cursorZzc.getColumnIndexOrThrow("value"));
        }
        cursorZzc.close();
        return i2;
    }

    public static long zzb(SQLiteDatabase sQLiteDatabase, int i) {
        Cursor cursorZzc = zzc(sQLiteDatabase, 2);
        long j = 0;
        if (cursorZzc.getCount() > 0) {
            cursorZzc.moveToNext();
            j = 0 + cursorZzc.getLong(cursorZzc.getColumnIndexOrThrow("value"));
        }
        cursorZzc.close();
        return j;
    }

    private static Cursor zzc(SQLiteDatabase sQLiteDatabase, int i) {
        String[] strArr = {"value"};
        String[] strArr2 = new String[1];
        if (i == 0) {
            strArr2[0] = "failed_requests";
        } else if (i == 1) {
            strArr2[0] = "total_requests";
        } else if (i == 2) {
            strArr2[0] = "last_successful_request_time";
        }
        return sQLiteDatabase.query("offline_signal_statistics", strArr, "statistic_name = ?", strArr2, null, null, null);
    }
}
