package com.inmobi.commons.core.d;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: compiled from: DbHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends SQLiteOpenHelper {
    public static final String a = "com.im_7.2.1.db";

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public a(Context context) {
        super(context, a, (SQLiteDatabase.CursorFactory) null, 1);
    }
}
