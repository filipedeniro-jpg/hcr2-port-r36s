package com.facebook.ads.redexgen.X;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import com.ironsource.eventsmodule.DataBaseEventsStorage;
import com.ironsource.sdk.constants.LocationConst;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class HR extends HQ {
    public static final HP E = new HP(0, "event_id", "TEXT PRIMARY KEY");
    public static final HP J = new HP(1, "token_id", "TEXT REFERENCES tokens ON UPDATE CASCADE ON DELETE RESTRICT");
    public static final HP F = new HP(2, "priority", "INTEGER");
    public static final HP K = new HP(3, "type", "TEXT");
    public static final HP I = new HP(4, LocationConst.TIME, "REAL");
    public static final HP H = new HP(5, "session_time", "REAL");
    public static final HP G = new HP(6, "session_id", "TEXT");
    public static final HP D = new HP(7, "data", "TEXT");
    public static final HP C = new HP(8, "attempt", "INTEGER");
    public static final HP[] B = {E, J, F, K, I, H, G, D, C};
    private static final String L = HQ.B(DataBaseEventsStorage.EventEntry.TABLE_NAME, B);

    public HR(HW helper) {
        super(helper);
    }

    @Override // com.facebook.ads.redexgen.X.HQ
    public final HP[] E() {
        return B;
    }

    @Override // com.facebook.ads.redexgen.X.HQ
    public final String G() {
        return DataBaseEventsStorage.EventEntry.TABLE_NAME;
    }

    public final boolean H(String eventId) {
        return F().delete(DataBaseEventsStorage.EventEntry.TABLE_NAME, new StringBuilder().append(E.C).append(" = ?").toString(), new String[]{eventId}) > 0;
    }

    @WorkerThread
    public final Cursor I(String eventId) {
        return F().rawQuery("SELECT * FROM events WHERE " + E.C + " = ?", new String[]{eventId});
    }

    @WorkerThread
    public final Cursor J() {
        return F().rawQuery("SELECT count(*) FROM events", null);
    }

    @WorkerThread
    public final Cursor K() {
        return F().rawQuery(L, null);
    }

    @WorkerThread
    public final String L(String tokenId, int priority, String type, double time, double sessionTime, String sessionId, Map<String, String> map) throws SQLiteException {
        String string = UUID.randomUUID().toString();
        ContentValues contentValues = new ContentValues(9);
        contentValues.put(E.C, string);
        contentValues.put(J.C, tokenId);
        contentValues.put(F.C, Integer.valueOf(priority));
        contentValues.put(K.C, type);
        contentValues.put(I.C, Double.valueOf(time));
        contentValues.put(H.C, Double.valueOf(sessionTime));
        contentValues.put(G.C, sessionId);
        contentValues.put(D.C, map != null ? new JSONObject(map).toString() : null);
        contentValues.put(C.C, (Integer) 0);
        F().insertOrThrow(DataBaseEventsStorage.EventEntry.TABLE_NAME, null, contentValues);
        return string;
    }
}
