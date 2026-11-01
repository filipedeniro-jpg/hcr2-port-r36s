package com.inmobi.commons.core.d;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: DbStore.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    private static final String a = "b";
    private static volatile b b;
    private static final Object c = new Object();
    private static final Object d = new Object();
    private static int e = 0;
    private SQLiteDatabase f;

    private b() {
        try {
            this.f = new a(com.inmobi.commons.a.a.b()).getWritableDatabase();
            b = this;
        } catch (Exception unused) {
        }
    }

    public static synchronized b a() {
        b bVar;
        b bVar2;
        synchronized (d) {
            e++;
        }
        bVar = b;
        if (bVar == null) {
            synchronized (c) {
                bVar2 = b;
                if (bVar2 == null) {
                    bVar2 = new b();
                    b = bVar2;
                }
            }
            bVar = bVar2;
        }
        return bVar;
    }

    public final synchronized void a(String str, ContentValues contentValues, String str2, String[] strArr) {
        try {
            if (!a(str, contentValues)) {
                b(str, contentValues, str2, strArr);
            }
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in DbStore.insertOrUpdate() method; ").append(e2.getMessage());
        }
    }

    public final synchronized boolean a(String str, ContentValues contentValues) {
        try {
            return this.f.insertWithOnConflict(str, null, contentValues, 4) != -1;
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in DbStore.insert() method; ").append(e2.getMessage());
            return false;
        }
    }

    public final synchronized int a(String str, String str2, String[] strArr) {
        try {
        } catch (Exception e2) {
            new StringBuilder("SDK encountered an unexpected error in DbStore.delete() method; ").append(e2.getMessage());
            return -1;
        }
        return this.f.delete(str, str2, strArr);
    }

    public final synchronized int b(String str, ContentValues contentValues, String str2, String[] strArr) {
        try {
        } catch (Exception e2) {
            new StringBuilder("SDK encountered an unexpected error in DbStore.delete() method; ").append(e2.getMessage());
            return -1;
        }
        return this.f.updateWithOnConflict(str, contentValues, str2, strArr, 4);
    }

    public final synchronized List<ContentValues> a(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.f.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
                if (cursorQuery.moveToFirst()) {
                    do {
                        ContentValues contentValues = new ContentValues();
                        DatabaseUtils.cursorRowToContentValues(cursorQuery, contentValues);
                        arrayList.add(contentValues);
                    } while (cursorQuery.moveToNext());
                }
                cursorQuery.close();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Exception e2) {
                new StringBuilder("SDK encountered unexpected error in DbStore.getRows() method; ").append(e2.getMessage());
                if (cursorQuery != null) {
                }
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
        return arrayList;
    }

    public final synchronized int a(String str) {
        int i;
        try {
            Cursor cursorRawQuery = this.f.rawQuery("SELECT COUNT(*) FROM " + str + " ; ", null);
            cursorRawQuery.moveToFirst();
            i = cursorRawQuery.getInt(0);
            cursorRawQuery.close();
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in DbStore.getCount() method; ").append(e2.getMessage());
            return -1;
        }
        return i;
    }

    public final synchronized int b(String str, String str2, String[] strArr) {
        int i;
        try {
            Cursor cursorRawQuery = this.f.rawQuery("SELECT COUNT(*) FROM " + str + " WHERE " + str2 + " ; ", strArr);
            cursorRawQuery.moveToFirst();
            i = cursorRawQuery.getInt(0);
            cursorRawQuery.close();
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in DbStore.getCount() method; ").append(e2.getMessage());
            return -1;
        }
        return i;
    }

    public final synchronized void a(String str, String str2) {
        try {
            this.f.execSQL("CREATE TABLE IF NOT EXISTS " + str + str2 + ";");
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in DbStore.createTableIfNotExists() method; ").append(e2.getMessage());
        }
    }

    public final synchronized void b() {
        try {
            synchronized (d) {
                int i = e - 1;
                e = i;
                if (i == 0) {
                    this.f.close();
                    b = null;
                }
            }
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error in DbStore.close() method; ").append(e2.getMessage());
        }
    }
}
