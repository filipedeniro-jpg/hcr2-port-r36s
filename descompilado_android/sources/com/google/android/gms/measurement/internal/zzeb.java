package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.SystemClock;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzeb extends zzg {
    private final zzea zzjv;
    private boolean zzjw;

    zzeb(zzfj zzfjVar) {
        super(zzfjVar);
        this.zzjv = new zzea(this, getContext(), "google_app_measurement_local.db");
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    protected final boolean zzbk() {
        return false;
    }

    @WorkerThread
    public final void resetAnalyticsData() {
        zzm();
        zzo();
        try {
            int iDelete = getWritableDatabase().delete("messages", null, null) + 0;
            if (iDelete > 0) {
                zzab().zzgs().zza("Reset local analytics data. records", Integer.valueOf(iDelete));
            }
        } catch (SQLiteException e) {
            zzab().zzgk().zza("Error resetting local analytics data. error", e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:107:0x0130 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:111:0x0130 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:58:0x00e3 A[Catch: all -> 0x00ff, TryCatch #14 {all -> 0x00ff, blocks: (B:56:0x00dd, B:58:0x00e3, B:59:0x00e6), top: B:94:0x00dd }] */
    /* JADX WARN: Code duplicated, block: B:61:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:63:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:78:0x0128  */
    /* JADX WARN: Code duplicated, block: B:80:0x012d  */
    /* JADX WARN: Code duplicated, block: B:85:0x013a  */
    /* JADX WARN: Code duplicated, block: B:87:0x013f  */
    /* JADX WARN: Code duplicated, block: B:94:0x00dd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r13v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r13v9 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    @WorkerThread
    private final boolean zza(int i, byte[] bArr) throws Throwable {
        SQLiteDatabase writableDatabase;
        ?? RawQuery;
        zzm();
        zzo();
        ?? r3 = 0;
        if (this.zzjw) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put("entry", bArr);
        int i2 = 0;
        int i3 = 5;
        for (int i4 = 5; i2 < i4; i4 = 5) {
            SQLiteDatabase sQLiteDatabase = null;
             = 0;
             = 0;
             = 0;
            ?? r8 = 0;
            ?? r9 = 0;
            try {
                writableDatabase = getWritableDatabase();
                try {
                    if (writableDatabase == null) {
                        try {
                            this.zzjw = true;
                            if (writableDatabase != null) {
                                writableDatabase.close();
                            }
                            return r3;
                        } catch (SQLiteFullException e) {
                            e = e;
                            try {
                                zzab().zzgk().zza("Error writing entry to local database", e);
                                this.zzjw = true;
                                if (r9 != 0) {
                                    r9.close();
                                }
                                if (writableDatabase != null) {
                                    writableDatabase.close();
                                }
                                i2++;
                                r3 = 0;
                            } catch (Throwable th) {
                                th = th;
                                RawQuery = r9;
                                if (RawQuery != 0) {
                                    RawQuery.close();
                                }
                                if (writableDatabase != null) {
                                    writableDatabase.close();
                                }
                                throw th;
                            }
                        } catch (SQLiteException e2) {
                            e = e2;
                            RawQuery = 0;
                            sQLiteDatabase = writableDatabase;
                            RawQuery = RawQuery;
                            if (sQLiteDatabase != null) {
                                try {
                                    if (sQLiteDatabase.inTransaction()) {
                                        sQLiteDatabase.endTransaction();
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    writableDatabase = sQLiteDatabase;
                                    if (RawQuery != 0) {
                                        RawQuery.close();
                                    }
                                    if (writableDatabase != null) {
                                        writableDatabase.close();
                                    }
                                    throw th;
                                }
                            }
                            zzab().zzgk().zza("Error writing entry to local database", e);
                            this.zzjw = true;
                            if (RawQuery != 0) {
                                RawQuery.close();
                            }
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.close();
                            }
                            i2++;
                            r3 = 0;
                        }
                    } else {
                        try {
                            writableDatabase.beginTransaction();
                            long j = 0;
                            RawQuery = writableDatabase.rawQuery("select count(1) from messages", null);
                            if (RawQuery != 0) {
                                try {
                                    if (RawQuery.moveToFirst()) {
                                        j = RawQuery.getLong(r3);
                                    }
                                } catch (SQLiteDatabaseLockedException unused) {
                                    r8 = RawQuery;
                                    SystemClock.sleep(i3);
                                    i3 += 20;
                                    if (r8 != 0) {
                                        r8.close();
                                    }
                                    if (writableDatabase != null) {
                                        writableDatabase.close();
                                    }
                                    i2++;
                                    r3 = 0;
                                } catch (SQLiteFullException e3) {
                                    e = e3;
                                    r9 = RawQuery;
                                    zzab().zzgk().zza("Error writing entry to local database", e);
                                    this.zzjw = true;
                                    if (r9 != 0) {
                                        r9.close();
                                    }
                                    if (writableDatabase != null) {
                                        writableDatabase.close();
                                    }
                                    i2++;
                                    r3 = 0;
                                } catch (SQLiteException e4) {
                                    e = e4;
                                    sQLiteDatabase = writableDatabase;
                                    RawQuery = RawQuery;
                                    if (sQLiteDatabase != null) {
                                        if (sQLiteDatabase.inTransaction()) {
                                            sQLiteDatabase.endTransaction();
                                        }
                                    }
                                    zzab().zzgk().zza("Error writing entry to local database", e);
                                    this.zzjw = true;
                                    if (RawQuery != 0) {
                                        RawQuery.close();
                                    }
                                    if (sQLiteDatabase != null) {
                                        sQLiteDatabase.close();
                                    }
                                    i2++;
                                    r3 = 0;
                                } catch (Throwable th3) {
                                    th = th3;
                                    if (RawQuery != 0) {
                                        RawQuery.close();
                                    }
                                    if (writableDatabase != null) {
                                        writableDatabase.close();
                                    }
                                    throw th;
                                }
                            }
                            if (j >= 100000) {
                                zzab().zzgk().zzao("Data loss, local db full");
                                long j2 = (100000 - j) + 1;
                                String[] strArr = new String[1];
                                strArr[r3] = Long.toString(j2);
                                long jDelete = writableDatabase.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", strArr);
                                if (jDelete != j2) {
                                    zzab().zzgk().zza("Different delete count than expected in local db. expected, received, difference", Long.valueOf(j2), Long.valueOf(jDelete), Long.valueOf(j2 - jDelete));
                                }
                            }
                            writableDatabase.insertOrThrow("messages", null, contentValues);
                            writableDatabase.setTransactionSuccessful();
                            writableDatabase.endTransaction();
                            if (RawQuery != 0) {
                                RawQuery.close();
                            }
                            if (writableDatabase == null) {
                                return true;
                            }
                            writableDatabase.close();
                            return true;
                        } catch (SQLiteFullException e5) {
                            e = e5;
                        } catch (SQLiteException e6) {
                            e = e6;
                            RawQuery = 0;
                        } catch (Throwable th4) {
                            th = th4;
                            RawQuery = 0;
                        }
                    }
                } catch (SQLiteDatabaseLockedException unused2) {
                    r8 = 0;
                }
            } catch (SQLiteDatabaseLockedException unused3) {
                writableDatabase = null;
            } catch (SQLiteFullException e7) {
                e = e7;
                writableDatabase = null;
            } catch (SQLiteException e8) {
                e = e8;
                RawQuery = 0;
            } catch (Throwable th5) {
                th = th5;
                writableDatabase = null;
                RawQuery = 0;
            }
        }
        zzab().zzgn().zzao("Failed to write entry to local database");
        return false;
    }

    public final boolean zza(zzai zzaiVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzaiVar.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length > 131072) {
            zzab().zzgn().zzao("Event is too long for local database. Sending event directly to service");
            return false;
        }
        return zza(0, bArrMarshall);
    }

    public final boolean zza(zzjn zzjnVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzjnVar.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length > 131072) {
            zzab().zzgn().zzao("User property too long for local database. Sending directly to service");
            return false;
        }
        return zza(1, bArrMarshall);
    }

    public final boolean zzc(zzq zzqVar) {
        zzz();
        byte[] bArrZza = zzjs.zza(zzqVar);
        if (bArrZza.length > 131072) {
            zzab().zzgn().zzao("Conditional user property too long for local database. Sending directly to service");
            return false;
        }
        return zza(2, bArrZza);
    }

    /* JADX WARN: Code duplicated, block: B:120:0x01f5 A[Catch: all -> 0x0210, TryCatch #21 {all -> 0x0210, blocks: (B:36:0x00c1, B:38:0x00c7, B:40:0x00d5, B:42:0x00e8, B:44:0x00ed, B:50:0x0104, B:51:0x0107, B:49:0x0100, B:54:0x010c, B:56:0x011f, B:63:0x0136, B:64:0x013a, B:65:0x013d, B:61:0x0130, B:67:0x0140, B:69:0x0153, B:76:0x016a, B:77:0x016f, B:78:0x0172, B:74:0x0164, B:80:0x0175, B:81:0x0184, B:82:0x0193, B:84:0x01a9, B:85:0x01b6, B:118:0x01ef, B:120:0x01f5, B:121:0x01f8), top: B:171:0x01ef }] */
    /* JADX WARN: Code duplicated, block: B:123:0x0207  */
    /* JADX WARN: Code duplicated, block: B:125:0x020c  */
    /* JADX WARN: Code duplicated, block: B:133:0x021c  */
    /* JADX WARN: Code duplicated, block: B:135:0x0221  */
    /* JADX WARN: Code duplicated, block: B:142:0x023b  */
    /* JADX WARN: Code duplicated, block: B:144:0x0240  */
    /* JADX WARN: Code duplicated, block: B:149:0x024e  */
    /* JADX WARN: Code duplicated, block: B:151:0x0253  */
    /* JADX WARN: Code duplicated, block: B:171:0x01ef A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:183:0x0243 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:185:0x0243 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:187:0x0243 A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v11, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable>] */
    /* JADX WARN: Type inference failed for: r3v11, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v9 */
    public final List<AbstractSafeParcelable> zzc(int i) throws Throwable {
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase writableDatabase;
        ?? r3;
        SQLiteDatabase sQLiteDatabase2;
        ?? Query;
        int i2;
        zzjn zzjnVarCreateFromParcel;
        zzq zzqVarCreateFromParcel;
        String str;
        String[] strArr;
        zzo();
        zzm();
        ?? r4 = 0;
        if (this.zzjw) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!zzcg()) {
            return arrayList;
        }
        int i3 = 0;
        int i4 = 5;
        for (int i5 = 5; i3 < i5; i5 = 5) {
            try {
                writableDatabase = getWritableDatabase();
                try {
                    if (writableDatabase == null) {
                        try {
                            this.zzjw = true;
                            if (writableDatabase != null) {
                                writableDatabase.close();
                            }
                            return r4;
                        } catch (SQLiteFullException e) {
                            e = e;
                            try {
                                zzab().zzgk().zza("Error reading entries from local database", e);
                                this.zzjw = true;
                                if (r4 != 0) {
                                    r4.close();
                                }
                                if (writableDatabase != null) {
                                    writableDatabase.close();
                                }
                                i3++;
                                r4 = 0;
                            } catch (Throwable th) {
                                th = th;
                                Query = r4;
                                sQLiteDatabase = writableDatabase;
                                if (Query != 0) {
                                    Query.close();
                                }
                                if (sQLiteDatabase != null) {
                                    sQLiteDatabase.close();
                                }
                                throw th;
                            }
                        } catch (SQLiteException e2) {
                            e = e2;
                            Query = r4;
                            sQLiteDatabase = writableDatabase;
                            if (sQLiteDatabase != null) {
                                try {
                                    if (sQLiteDatabase.inTransaction()) {
                                        sQLiteDatabase.endTransaction();
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (Query != 0) {
                                        Query.close();
                                    }
                                    if (sQLiteDatabase != null) {
                                        sQLiteDatabase.close();
                                    }
                                    throw th;
                                }
                            }
                            zzab().zzgk().zza("Error reading entries from local database", e);
                            this.zzjw = true;
                            if (Query != 0) {
                                Query.close();
                            }
                            if (sQLiteDatabase != null) {
                                sQLiteDatabase.close();
                            }
                            i3++;
                            r4 = 0;
                        }
                    } else {
                        try {
                            try {
                                writableDatabase.beginTransaction();
                                long j = -1;
                                if (zzad().zza(zzak.zzjd)) {
                                    long jZza = zza(writableDatabase);
                                    if (jZza != -1) {
                                        try {
                                            str = "rowid<?";
                                            strArr = new String[]{String.valueOf(jZza)};
                                        } catch (SQLiteFullException e3) {
                                            e = e3;
                                            r4 = 0;
                                            zzab().zzgk().zza("Error reading entries from local database", e);
                                            this.zzjw = true;
                                            if (r4 != 0) {
                                                r4.close();
                                            }
                                            if (writableDatabase != null) {
                                                writableDatabase.close();
                                            }
                                            i3++;
                                            r4 = 0;
                                        }
                                    } else {
                                        str = null;
                                        strArr = null;
                                    }
                                    String str2 = str;
                                    i2 = 3;
                                    String[] strArr2 = strArr;
                                    sQLiteDatabase = writableDatabase;
                                    try {
                                        Query = writableDatabase.query("messages", new String[]{"rowid", "type", "entry"}, str2, strArr2, null, null, "rowid asc", Integer.toString(100));
                                    } catch (SQLiteDatabaseLockedException unused) {
                                        sQLiteDatabase2 = sQLiteDatabase;
                                        r3 = 0;
                                        try {
                                            SystemClock.sleep(i4);
                                            i4 += 20;
                                            if (r3 != 0) {
                                                r3.close();
                                            }
                                            if (sQLiteDatabase2 != null) {
                                                sQLiteDatabase2.close();
                                            }
                                            i3++;
                                            r4 = 0;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            Query = r3;
                                            sQLiteDatabase = sQLiteDatabase2;
                                            if (Query != 0) {
                                                Query.close();
                                            }
                                            if (sQLiteDatabase != null) {
                                                sQLiteDatabase.close();
                                            }
                                            throw th;
                                        }
                                    } catch (SQLiteFullException e4) {
                                        e = e4;
                                        writableDatabase = sQLiteDatabase;
                                        r4 = 0;
                                        zzab().zzgk().zza("Error reading entries from local database", e);
                                        this.zzjw = true;
                                        if (r4 != 0) {
                                            r4.close();
                                        }
                                        if (writableDatabase != null) {
                                            writableDatabase.close();
                                        }
                                        i3++;
                                        r4 = 0;
                                    } catch (SQLiteException e5) {
                                        e = e5;
                                        Query = 0;
                                        if (sQLiteDatabase != null) {
                                            if (sQLiteDatabase.inTransaction()) {
                                                sQLiteDatabase.endTransaction();
                                            }
                                        }
                                        zzab().zzgk().zza("Error reading entries from local database", e);
                                        this.zzjw = true;
                                        if (Query != 0) {
                                            Query.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                        i3++;
                                        r4 = 0;
                                    } catch (Throwable th4) {
                                        th = th4;
                                        Query = 0;
                                        if (Query != 0) {
                                            Query.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                        throw th;
                                    }
                                } else {
                                    sQLiteDatabase = writableDatabase;
                                    i2 = 3;
                                    Query = sQLiteDatabase.query("messages", new String[]{"rowid", "type", "entry"}, null, null, null, null, "rowid asc", Integer.toString(100));
                                }
                                while (Query.moveToNext()) {
                                    try {
                                        j = Query.getLong(0);
                                        int i6 = Query.getInt(1);
                                        byte[] blob = Query.getBlob(2);
                                        if (i6 == 0) {
                                            Parcel parcelObtain = Parcel.obtain();
                                            try {
                                                try {
                                                    parcelObtain.unmarshall(blob, 0, blob.length);
                                                    parcelObtain.setDataPosition(0);
                                                    zzai zzaiVarCreateFromParcel = zzai.CREATOR.createFromParcel(parcelObtain);
                                                    parcelObtain.recycle();
                                                    if (zzaiVarCreateFromParcel != null) {
                                                        arrayList.add(zzaiVarCreateFromParcel);
                                                    }
                                                } catch (Throwable th5) {
                                                    parcelObtain.recycle();
                                                    throw th5;
                                                }
                                            } catch (SafeParcelReader.ParseException unused2) {
                                                zzab().zzgk().zzao("Failed to load event from local database");
                                                parcelObtain.recycle();
                                            }
                                        } else if (i6 == 1) {
                                            Parcel parcelObtain2 = Parcel.obtain();
                                            try {
                                                try {
                                                    parcelObtain2.unmarshall(blob, 0, blob.length);
                                                    parcelObtain2.setDataPosition(0);
                                                    zzjnVarCreateFromParcel = zzjn.CREATOR.createFromParcel(parcelObtain2);
                                                    parcelObtain2.recycle();
                                                } catch (Throwable th6) {
                                                    parcelObtain2.recycle();
                                                    throw th6;
                                                }
                                            } catch (SafeParcelReader.ParseException unused3) {
                                                zzab().zzgk().zzao("Failed to load user property from local database");
                                                parcelObtain2.recycle();
                                                zzjnVarCreateFromParcel = null;
                                            }
                                            if (zzjnVarCreateFromParcel != null) {
                                                arrayList.add(zzjnVarCreateFromParcel);
                                            }
                                        } else if (i6 == 2) {
                                            Parcel parcelObtain3 = Parcel.obtain();
                                            try {
                                                try {
                                                    parcelObtain3.unmarshall(blob, 0, blob.length);
                                                    parcelObtain3.setDataPosition(0);
                                                    zzqVarCreateFromParcel = zzq.CREATOR.createFromParcel(parcelObtain3);
                                                    parcelObtain3.recycle();
                                                } catch (SafeParcelReader.ParseException unused4) {
                                                    zzab().zzgk().zzao("Failed to load user property from local database");
                                                    parcelObtain3.recycle();
                                                    zzqVarCreateFromParcel = null;
                                                }
                                                if (zzqVarCreateFromParcel != null) {
                                                    arrayList.add(zzqVarCreateFromParcel);
                                                }
                                            } catch (Throwable th7) {
                                                parcelObtain3.recycle();
                                                throw th7;
                                            }
                                        } else if (i6 == i2) {
                                            zzab().zzgn().zzao("Skipping app launch break");
                                        } else {
                                            zzab().zzgk().zzao("Unknown record type in local database");
                                        }
                                    } catch (SQLiteDatabaseLockedException unused5) {
                                        sQLiteDatabase2 = sQLiteDatabase;
                                        r3 = Query;
                                        SystemClock.sleep(i4);
                                        i4 += 20;
                                        if (r3 != 0) {
                                            r3.close();
                                        }
                                        if (sQLiteDatabase2 != null) {
                                            sQLiteDatabase2.close();
                                        }
                                        i3++;
                                        r4 = 0;
                                    } catch (SQLiteFullException e6) {
                                        e = e6;
                                        writableDatabase = sQLiteDatabase;
                                        r4 = Query;
                                        zzab().zzgk().zza("Error reading entries from local database", e);
                                        this.zzjw = true;
                                        if (r4 != 0) {
                                            r4.close();
                                        }
                                        if (writableDatabase != null) {
                                            writableDatabase.close();
                                        }
                                        i3++;
                                        r4 = 0;
                                    } catch (SQLiteException e7) {
                                        e = e7;
                                        if (sQLiteDatabase != null) {
                                            if (sQLiteDatabase.inTransaction()) {
                                                sQLiteDatabase.endTransaction();
                                            }
                                        }
                                        zzab().zzgk().zza("Error reading entries from local database", e);
                                        this.zzjw = true;
                                        if (Query != 0) {
                                            Query.close();
                                        }
                                        if (sQLiteDatabase != null) {
                                            sQLiteDatabase.close();
                                        }
                                        i3++;
                                        r4 = 0;
                                    }
                                }
                                if (sQLiteDatabase.delete("messages", "rowid <= ?", new String[]{Long.toString(j)}) < arrayList.size()) {
                                    zzab().zzgk().zzao("Fewer entries removed from local database than expected");
                                }
                                sQLiteDatabase.setTransactionSuccessful();
                                sQLiteDatabase.endTransaction();
                                if (Query != 0) {
                                    Query.close();
                                }
                                if (sQLiteDatabase != null) {
                                    sQLiteDatabase.close();
                                }
                                return arrayList;
                            } catch (SQLiteFullException e8) {
                                e = e8;
                            }
                        } catch (SQLiteException e9) {
                            e = e9;
                            sQLiteDatabase = writableDatabase;
                        } catch (Throwable th8) {
                            th = th8;
                            sQLiteDatabase = writableDatabase;
                        }
                    }
                } catch (SQLiteDatabaseLockedException unused6) {
                    sQLiteDatabase = writableDatabase;
                }
            } catch (SQLiteDatabaseLockedException unused7) {
                r3 = 0;
                sQLiteDatabase2 = null;
            } catch (SQLiteFullException e10) {
                e = e10;
                r4 = 0;
                writableDatabase = null;
            } catch (SQLiteException e11) {
                e = e11;
                sQLiteDatabase = null;
            } catch (Throwable th9) {
                th = th9;
                sQLiteDatabase = null;
            }
        }
        zzab().zzgn().zzao("Failed to read events from database in reasonable time");
        return null;
    }

    @WorkerThread
    public final boolean zzgh() {
        return zza(3, new byte[0]);
    }

    @WorkerThread
    public final boolean zzgi() {
        zzo();
        zzm();
        if (this.zzjw || !zzcg()) {
            return false;
        }
        int i = 5;
        for (int i2 = 0; i2 < 5; i2++) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                try {
                    SQLiteDatabase writableDatabase = getWritableDatabase();
                    if (writableDatabase == null) {
                        this.zzjw = true;
                        if (writableDatabase != null) {
                            writableDatabase.close();
                        }
                        return false;
                    }
                    writableDatabase.beginTransaction();
                    writableDatabase.delete("messages", "type == ?", new String[]{Integer.toString(3)});
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                    if (writableDatabase != null) {
                        writableDatabase.close();
                    }
                    return true;
                } catch (SQLiteException e) {
                    if (0 != 0) {
                        try {
                            if (sQLiteDatabase.inTransaction()) {
                                sQLiteDatabase.endTransaction();
                            }
                        } catch (Throwable th) {
                            if (0 != 0) {
                                sQLiteDatabase.close();
                            }
                            throw th;
                        }
                    }
                    zzab().zzgk().zza("Error deleting app launch break from local database", e);
                    this.zzjw = true;
                    if (0 != 0) {
                        sQLiteDatabase.close();
                    }
                }
            } catch (SQLiteDatabaseLockedException unused) {
                SystemClock.sleep(i);
                i += 20;
                if (0 != 0) {
                    sQLiteDatabase.close();
                }
            } catch (SQLiteFullException e2) {
                zzab().zzgk().zza("Error deleting app launch break from local database", e2);
                this.zzjw = true;
                if (0 != 0) {
                    sQLiteDatabase.close();
                }
            }
        }
        zzab().zzgn().zzao("Error deleting app launch break from local database in reasonable time");
        return false;
    }

    private static long zza(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = null;
        try {
            cursorQuery = sQLiteDatabase.query("messages", new String[]{"rowid"}, "type=?", new String[]{"3"}, null, null, "rowid desc", "1");
            if (cursorQuery.moveToFirst()) {
                return cursorQuery.getLong(0);
            }
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    @VisibleForTesting
    @WorkerThread
    private final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        if (this.zzjw) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzjv.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzjw = true;
        return null;
    }

    @VisibleForTesting
    private final boolean zzcg() {
        return getContext().getDatabasePath("google_app_measurement_local.db").exists();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzl() {
        super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzm() {
        super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzn() {
        super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzd, com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ void zzo() {
        super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zza zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzgp zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzdy zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhv zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzhq zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zzeb zzu() {
        return super.zzu();
    }

    @Override // com.google.android.gms.measurement.internal.zzd
    public final /* bridge */ /* synthetic */ zziw zzv() {
        return super.zzv();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzac zzw() {
        return super.zzw();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ Clock zzx() {
        return super.zzx();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzed zzy() {
        return super.zzy();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzjs zzz() {
        return super.zzz();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzfc zzaa() {
        return super.zzaa();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzef zzab() {
        return super.zzab();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzeo zzac() {
        return super.zzac();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf
    public final /* bridge */ /* synthetic */ zzs zzad() {
        return super.zzad();
    }

    @Override // com.google.android.gms.measurement.internal.zzgf, com.google.android.gms.measurement.internal.zzgh
    public final /* bridge */ /* synthetic */ zzr zzae() {
        return super.zzae();
    }
}
