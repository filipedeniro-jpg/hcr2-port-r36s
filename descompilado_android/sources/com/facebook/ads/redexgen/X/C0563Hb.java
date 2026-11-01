package com.facebook.ads.redexgen.X;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.ironsource.eventsmodule.DataBaseEventsStorage;
import java.util.UUID;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Hb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0563Hb extends HQ {
    public static final HP D = new HP(0, "token_id", "TEXT PRIMARY KEY");
    public static final HP C = new HP(1, "token", "TEXT");
    public static final HP[] B = {D, C};
    private static final String H = C0563Hb.class.getSimpleName();
    private static final String F = HQ.B("tokens", B);
    private static final String G = HQ.C("tokens", B, C);
    private static final String E = "DELETE FROM tokens WHERE NOT EXISTS (SELECT 1 FROM events WHERE tokens." + D.C + " = " + DataBaseEventsStorage.EventEntry.TABLE_NAME + "." + HR.J.C + ")";

    public C0563Hb(HW helper) {
        super(helper);
    }

    @Override // com.facebook.ads.redexgen.X.HQ
    public final HP[] E() {
        return B;
    }

    @Override // com.facebook.ads.redexgen.X.HQ
    public final String G() {
        return "tokens";
    }

    @WorkerThread
    public final void H() {
        try {
            F().execSQL(E);
        } catch (SQLException e) {
            if (BuildConfigApi.isDebug()) {
                Log.e(H, "Exception when trying to delete childless tokens.", e);
            }
        }
    }

    @WorkerThread
    public final Cursor I() {
        return F().rawQuery(F, null);
    }

    @WorkerThread
    public final String J(String token) throws SQLiteException, IllegalArgumentException {
        if (TextUtils.isEmpty(token)) {
            throw new IllegalArgumentException("Invalid token.");
        }
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = F().rawQuery(G, new String[]{token});
            String existingTokenId = cursorRawQuery.moveToNext() ? cursorRawQuery.getString(D.B) : null;
            if (TextUtils.isEmpty(existingTokenId)) {
                existingTokenId = UUID.randomUUID().toString();
                ContentValues contentValues = new ContentValues(2);
                String newTokenId = D.C;
                contentValues.put(newTokenId, existingTokenId);
                String newTokenId2 = C.C;
                contentValues.put(newTokenId2, token);
                F().insertOrThrow("tokens", null, contentValues);
            }
            return existingTokenId;
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
    }
}
