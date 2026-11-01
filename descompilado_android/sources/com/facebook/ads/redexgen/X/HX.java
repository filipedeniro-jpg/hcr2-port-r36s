package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import java.util.Locale;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class HX extends SQLiteOpenHelper {
    public static final String C = HX.class.getSimpleName();
    private final HW B;

    public HX(Context context, HW helper) {
        super(context, B(context), (SQLiteDatabase.CursorFactory) null, 4);
        if (helper == null) {
            throw new IllegalArgumentException("AdDatabaseHelper can not be null");
        }
        this.B = helper;
    }

    private static String B(Context context) {
        String str = String.format(Locale.US, "ads%s.db", "");
        if (!JA.a(context)) {
            return str;
        }
        String packageName = context.getPackageName();
        String strB = I6.B(context);
        return (packageName.equals(strB) || TextUtils.isEmpty(strB)) ? str : String.format(Locale.US, "ads%s.db", '_' + strB);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase db) {
        for (HQ hq : this.B.m256F()) {
            hq.C(db);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (HQ hq : this.B.m256F()) {
            hq.D(db);
            hq.C(db);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 2 && newVersion >= 3) {
            db.execSQL("DROP TABLE IF EXISTS crashes");
        }
        if (oldVersion <= 3 && newVersion >= 4) {
            HP hp = HR.C;
            db.execSQL("ALTER TABLE events ADD COLUMN " + hp.C + " " + hp.D + " DEFAULT 0");
        }
    }
}
