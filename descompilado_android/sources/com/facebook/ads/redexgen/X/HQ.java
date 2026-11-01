package com.facebook.ads.redexgen.X;

import android.database.sqlite.SQLiteDatabase;

/* JADX INFO: loaded from: assets/audience_network.dex */
public abstract class HQ {
    public final HW B;

    public abstract HP[] E();

    public abstract String G();

    public HQ(HW helper) {
        this.B = helper;
    }

    public final boolean A() {
        return F().delete(G(), null, null) > 0;
    }

    public static String B(String tableName, HP[] columns) {
        StringBuilder sb = new StringBuilder("SELECT ");
        for (int i = 0; i < columns.length - 1; i++) {
            sb.append(columns[i].C);
            sb.append(", ");
        }
        sb.append(columns[columns.length - 1].C);
        sb.append(" FROM ");
        sb.append(tableName);
        return sb.toString();
    }

    public final void B() {
    }

    public static String C(String tableName, HP[] columns, HP key) {
        return B(tableName, columns) + " WHERE " + key.C + " = ?";
    }

    public final void C(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + G() + " (" + D() + ")");
    }

    private String D() {
        HP[] hpArrE = E();
        if (hpArrE.length < 1) {
            return null;
        }
        String result = "";
        for (int i = 0; i < hpArrE.length - 1; i++) {
            result = result + hpArrE[i].A() + ", ";
        }
        return result + hpArrE[hpArrE.length - 1].A();
    }

    public final void D(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + G());
    }

    public final SQLiteDatabase F() {
        return this.B.H();
    }
}
