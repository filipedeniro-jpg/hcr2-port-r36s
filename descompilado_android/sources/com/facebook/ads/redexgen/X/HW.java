package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.annotation.WorkerThread;
import com.ironsource.eventsmodule.DataBaseEventsStorage;
import com.ironsource.sdk.constants.Constants;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class HW {
    private static final String F = "SELECT tokens." + C0563Hb.D.C + ", tokens." + C0563Hb.C.C + ", " + DataBaseEventsStorage.EventEntry.TABLE_NAME + "." + HR.E.C + ", " + DataBaseEventsStorage.EventEntry.TABLE_NAME + "." + HR.F.C + ", " + DataBaseEventsStorage.EventEntry.TABLE_NAME + "." + HR.K.C + ", " + DataBaseEventsStorage.EventEntry.TABLE_NAME + "." + HR.I.C + ", " + DataBaseEventsStorage.EventEntry.TABLE_NAME + "." + HR.H.C + ", " + DataBaseEventsStorage.EventEntry.TABLE_NAME + "." + HR.G.C + ", " + DataBaseEventsStorage.EventEntry.TABLE_NAME + "." + HR.D.C + ", " + DataBaseEventsStorage.EventEntry.TABLE_NAME + "." + HR.C.C + " FROM " + DataBaseEventsStorage.EventEntry.TABLE_NAME + " JOIN tokens ON " + DataBaseEventsStorage.EventEntry.TABLE_NAME + "." + HR.J.C + " = tokens." + C0563Hb.D.C + " ORDER BY " + DataBaseEventsStorage.EventEntry.TABLE_NAME + "." + HR.I.C + " ASC";
    private static final ReentrantReadWriteLock G = new ReentrantReadWriteLock();
    private static final Lock H = G.readLock();
    private static final Lock I = G.writeLock();
    private final Context B;
    private SQLiteOpenHelper C;
    private final C0563Hb E = new C0563Hb(this);
    private final HR D = new HR(this);

    public HW(Context appContext) {
        this.B = appContext;
    }

    @WorkerThread
    public final void A() {
        I.lock();
        try {
            this.D.A();
            this.E.A();
        } finally {
            I.unlock();
        }
    }

    /* JADX INFO: renamed from: B, reason: collision with other method in class */
    public final synchronized void m255B() {
        for (HQ hq : m256F()) {
            hq.B();
        }
        if (this.C != null) {
            this.C.close();
            this.C = null;
        }
    }

    public final int C(int retryLimit) {
        I.lock();
        try {
            return H().delete(DataBaseEventsStorage.EventEntry.TABLE_NAME, HR.C.C + " > ?", new String[]{String.valueOf(retryLimit - 1)});
        } finally {
            I.unlock();
        }
    }

    @WorkerThread
    public final void D() {
        I.lock();
        try {
            this.E.H();
        } finally {
            I.unlock();
        }
    }

    @WorkerThread
    public final boolean E(String eventId) {
        I.lock();
        try {
            return this.D.H(eventId);
        } finally {
            I.unlock();
        }
    }

    private synchronized SQLiteDatabase F() {
        if (this.C == null) {
            this.C = new HX(this.B, this);
        }
        return this.C.getWritableDatabase();
    }

    /* JADX INFO: renamed from: F, reason: collision with other method in class */
    public final HQ[] m256F() {
        return new HQ[]{this.E, this.D};
    }

    @WorkerThread
    private synchronized SQLiteDatabase G() {
        SQLiteException e = null;
        for (int i = 0; i < 10; i++) {
            try {
            } catch (SQLiteException e2) {
                e = e2;
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e3) {
                }
            }
        }
        C0585Hx.D(this.B, "database", C0584Hw.q, new C0583Hv(e));
        throw e;
        return F();
    }

    public final SQLiteDatabase H() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot call getDatabase from the UI thread!");
        }
        return G();
    }

    @WorkerThread
    public final boolean I(String eventId) {
        boolean z;
        I.lock();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ").append(DataBaseEventsStorage.EventEntry.TABLE_NAME).append(" SET ").append(HR.C.C).append(Constants.RequestParameters.EQUAL).append(HR.C.C).append("+1").append(" WHERE ").append(HR.E.C).append("=?");
            H().execSQL(sb.toString(), new String[]{eventId});
            z = true;
        } catch (SQLiteException e) {
            z = false;
        }
        I.unlock();
        return z;
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.HO != com.facebook.ads.internal.database.AdDatabaseCallback<T> */
    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.HS != com.facebook.ads.internal.database.AdDatabaseQuery<T> */
    public final <T> AsyncTask J(HS<T> hs, HO<T> ho) {
        return MM.C(LD.F, new HV(this.B.getApplicationContext(), hs, ho), new Void[0]);
    }

    @WorkerThread
    public final Cursor K(int eventLimit) {
        H.lock();
        try {
            return H().rawQuery(F + " LIMIT " + String.valueOf(eventLimit), null);
        } finally {
            H.unlock();
        }
    }

    @WorkerThread
    public final Cursor L(String eventId) {
        H.lock();
        try {
            return this.D.I(eventId);
        } finally {
            H.unlock();
        }
    }

    public final Cursor M() {
        H.lock();
        try {
            return this.D.J();
        } finally {
            H.unlock();
        }
    }

    @WorkerThread
    public final Cursor N() {
        H.lock();
        try {
            return this.D.K();
        } finally {
            H.unlock();
        }
    }

    @WorkerThread
    public final Cursor O() {
        H.lock();
        try {
            return this.E.I();
        } finally {
            H.unlock();
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.HO != com.facebook.ads.internal.database.AdDatabaseCallback<java.lang.String> */
    public final AsyncTask P(String token, int priority, String type, double time, double sessionTime, String sessionId, Map<String, String> map, HO<String> ho) {
        return J(new HU(this, token, priority, type, time, sessionTime, sessionId, map), ho);
    }
}
