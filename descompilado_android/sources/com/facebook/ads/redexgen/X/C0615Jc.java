package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.ironsource.eventsmodule.DataBaseEventsStorage;
import com.ironsource.sdk.constants.LocationConst;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Jc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0615Jc implements JT {
    private static final String D = C0615Jc.class.getSimpleName();
    private Context B;
    private HW C;

    @VisibleForTesting
    public C0615Jc(Context context, HW databaseHelper) {
        this.B = context;
        this.C = databaseHelper;
    }

    private JSONArray B(Cursor cursor) throws JSONException {
        JSONArray eventsArray = new JSONArray();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", cursor.getString(HR.E.B));
            jSONObject.put("token_id", cursor.getString(HR.J.B));
            jSONObject.put("type", cursor.getString(HR.K.B));
            jSONObject.put(LocationConst.TIME, C0622Jj.E(cursor.getDouble(HR.I.B)));
            jSONObject.put("session_time", C0622Jj.E(cursor.getDouble(HR.H.B)));
            jSONObject.put("session_id", cursor.getString(HR.G.B));
            String string = cursor.getString(HR.D.B);
            jSONObject.put("data", string != null ? new JSONObject(string) : new JSONObject());
            jSONObject.put("attempt", cursor.getString(HR.C.B));
            eventsArray.put(jSONObject);
        }
        return eventsArray;
    }

    private JSONArray C(Cursor cursor) throws JSONException {
        JSONArray eventsArray = new JSONArray();
        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", cursor.getString(2));
            jSONObject.put("token_id", cursor.getString(0));
            jSONObject.put("type", cursor.getString(4));
            jSONObject.put(LocationConst.TIME, C0622Jj.E(cursor.getDouble(5)));
            jSONObject.put("session_time", C0622Jj.E(cursor.getDouble(6)));
            jSONObject.put("session_id", cursor.getString(7));
            String string = cursor.getString(8);
            jSONObject.put("data", string != null ? new JSONObject(string) : new JSONObject());
            jSONObject.put("attempt", cursor.getString(9));
            eventsArray.put(jSONObject);
        }
        return eventsArray;
    }

    private JSONObject D() {
        JSONObject jSONObject;
        JSONArray jSONArrayH;
        Cursor cursorO = null;
        Cursor eventsCursor = null;
        try {
            cursorO = this.C.O();
            eventsCursor = this.C.N();
            JSONObject tokens = null;
            JSONArray jSONArrayI = null;
            if (cursorO.getCount() > 0 && eventsCursor.getCount() > 0) {
                tokens = F(cursorO);
                jSONArrayI = B(eventsCursor);
            }
            if (JA.r(this.B) && (jSONArrayH = C0575Hn.H(this.B)) != null && jSONArrayH.length() > 0) {
                jSONArrayI = I(jSONArrayH, jSONArrayI);
            }
            jSONObject = null;
            if (jSONArrayI != null) {
                jSONObject = new JSONObject();
                if (tokens != null) {
                    jSONObject.put("tokens", tokens);
                }
                jSONObject.put(DataBaseEventsStorage.EventEntry.TABLE_NAME, jSONArrayI);
            }
        } catch (JSONException e) {
            jSONObject = null;
        } finally {
            if (cursorO != null) {
                cursorO.close();
            }
            if (eventsCursor != null) {
                eventsCursor.close();
            }
        }
        return jSONObject;
    }

    private JSONObject E(int eventLimit) {
        JSONObject jSONObject;
        int iX;
        JSONArray jSONArrayI;
        Cursor cursorM = null;
        Cursor cursorK = null;
        try {
            cursorM = this.C.M();
            cursorK = this.C.K(eventLimit);
            JSONObject jSONObjectF = null;
            JSONArray jSONArrayJ = null;
            if (cursorK.getCount() > 0) {
                jSONObjectF = F(cursorK);
                jSONArrayJ = C(cursorK);
            }
            if (JA.r(this.B) && (jSONArrayI = C0575Hn.I(this.B, (iX = JA.X(this.B)))) != null && jSONArrayI.length() > 0) {
                int debugEventLimit = eventLimit + iX;
                jSONArrayJ = J(jSONArrayI, jSONArrayJ, debugEventLimit);
            }
            jSONObject = null;
            if (jSONArrayJ != null) {
                jSONObject = new JSONObject();
                if (jSONObjectF != null) {
                    jSONObject.put("tokens", jSONObjectF);
                }
                jSONObject.put(DataBaseEventsStorage.EventEntry.TABLE_NAME, jSONArrayJ);
            }
        } catch (JSONException e) {
            jSONObject = null;
        } finally {
            if (cursorM != null) {
                cursorM.close();
            }
            if (cursorK != null) {
                cursorK.close();
            }
        }
        return jSONObject;
    }

    private JSONObject F(Cursor cursor) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        while (cursor.moveToNext()) {
            jSONObject.put(cursor.getString(0), cursor.getString(1));
        }
        return jSONObject;
    }

    @SuppressLint({"CatchGeneralException"})
    private void G(int retryLimit) {
        try {
            int iC = this.C.C(retryLimit);
            if (iC > 0) {
                C0585Hx.D(this.B, "database", C0584Hw.v, new C0583Hv("Deleted events exceeded retry limit. Count: " + iC));
            }
        } catch (Exception e) {
            if (BuildConfigApi.isDebug()) {
                Log.e(D, "Can't delete attempts exceeded events.", e);
            }
        }
    }

    private void H(String eventId) {
        if (C0575Hn.G(eventId)) {
            C0575Hn.F(eventId);
        } else {
            this.C.I(eventId);
        }
    }

    private static JSONArray I(JSONArray debugLogEvents, JSONArray events) {
        int length = debugLogEvents != null ? 0 + debugLogEvents.length() : 0;
        if (events != null) {
            length += events.length();
        }
        return J(debugLogEvents, events, length);
    }

    private static JSONArray J(JSONArray debugLogEvents, JSONArray events, int debugIdx) {
        if (debugLogEvents == null) {
            return events;
        }
        if (events == null) {
            return debugLogEvents;
        }
        int debugLength = debugLogEvents.length();
        int length = events.length();
        JSONArray events2 = new JSONArray();
        int i = 0;
        int i2 = 0;
        JSONObject jSONObject = null;
        JSONObject jSONObject2 = null;
        double d = Double.MAX_VALUE;
        double d2 = Double.MAX_VALUE;
        while (true) {
            if ((i >= debugLength && i2 >= length) || debugIdx <= 0) {
                break;
            }
            if (i < debugLength && jSONObject == null) {
                try {
                    jSONObject = debugLogEvents.getJSONObject(i);
                    d = jSONObject.getDouble(LocationConst.TIME);
                } catch (JSONException e) {
                    if (BuildConfigApi.isDebug()) {
                        Log.e(D, "Unable to parse debugLogEvent at " + i, e);
                    }
                    jSONObject = null;
                    d = Double.MAX_VALUE;
                }
                i++;
            }
            if (i2 < length && jSONObject2 == null) {
                try {
                    jSONObject2 = events.getJSONObject(i2);
                    d2 = jSONObject2.getDouble(LocationConst.TIME);
                } catch (JSONException e2) {
                    if (BuildConfigApi.isDebug()) {
                        Log.e(D, "Unable to parse event at " + i2, e2);
                    }
                    jSONObject2 = null;
                    d2 = Double.MAX_VALUE;
                }
                i2++;
            }
            if (jSONObject != null || jSONObject2 != null) {
                if (jSONObject == null || d2 < d) {
                    events2.put(jSONObject2);
                    jSONObject2 = null;
                    d2 = Double.MAX_VALUE;
                } else {
                    events2.put(jSONObject);
                    jSONObject = null;
                    d = Double.MAX_VALUE;
                }
                debugIdx--;
            }
        }
        if (debugIdx > 0) {
            if (jSONObject != null) {
                events2.put(jSONObject);
            } else if (jSONObject2 != null) {
                events2.put(jSONObject2);
            }
        }
        return events2;
    }

    @Override // com.facebook.ads.redexgen.X.JT
    public final void UD() {
        int iO = JA.o(this.B);
        if (iO > -1) {
            G(iO);
        }
        this.C.D();
        this.C.m255B();
        C0575Hn.C(this.B);
    }

    @Override // com.facebook.ads.redexgen.X.JT
    public final boolean WC() {
        int i;
        boolean z = true;
        int iM = JA.m(this.B);
        if (iM < 1) {
            return false;
        }
        Cursor cursorM = null;
        try {
            cursorM = this.C.M();
            if (cursorM.moveToFirst()) {
                i = cursorM.getInt(0);
            } else {
                i = 0;
            }
            if (i + C0575Hn.E(this.B) <= iM) {
                z = false;
            }
            return z;
        } finally {
            if (cursorM != null) {
                cursorM.close();
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.JT
    public final JSONObject lB() {
        int iM = JA.m(this.B);
        return iM > 0 ? E(iM) : D();
    }

    @Override // com.facebook.ads.redexgen.X.JT
    public final void qD(JSONArray eventsArray) {
        int i = eventsArray.length();
        for (int i2 = 0; i2 < i; i2++) {
            try {
                H(eventsArray.getJSONObject(i2).getString("id"));
            } catch (JSONException e) {
                if (BuildConfigApi.isDebug()) {
                    Log.e(D, "Failed to parse an event in events array for dispatch failure.", e);
                }
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.JT
    @WorkerThread
    public final boolean sD(JSONArray eventsArray) {
        boolean z = true;
        boolean zR = JA.r(this.B);
        for (int i = 0; i < eventsArray.length(); i++) {
            try {
                JSONObject jSONObject = eventsArray.getJSONObject(i);
                String string = jSONObject.getString("id");
                boolean isDebugGKEnabled = "feature-config-event-magic".equals(string);
                if (isDebugGKEnabled) {
                    JA.Y(this.B).E(jSONObject.getString("feature_config"));
                } else {
                    int i2 = jSONObject.getInt("code");
                    if (i2 == 1) {
                        if (BuildConfigApi.isDebug()) {
                            String featureConfigString = "Server processed eventId " + string + " successfully.";
                            Log.v(D, featureConfigString);
                            Cursor cursorL = this.C.L(string);
                            if (cursorL.moveToNext() && cursorL.getCount() > 0) {
                                Log.v(D, "Event type processed by the server: " + cursorL.getString(cursorL.getColumnIndex(HR.K.C)));
                            }
                            if (cursorL != null) {
                                cursorL.close();
                            }
                        }
                        if (!this.C.E(string) && zR) {
                            C0575Hn.B(string);
                        }
                    } else if (i2 >= 1000 && i2 < 2000) {
                        if (BuildConfigApi.isDebug()) {
                            Log.e(D, "Server returned retryable error code " + i2 + " for eventId " + string + ".");
                        }
                        H(string);
                        z = false;
                    } else if (i2 >= 2000 && i2 < 3000) {
                        if (BuildConfigApi.isDebug()) {
                            Log.e(D, "Server returned non-retryable error code " + i2 + " for eventId " + string + ".");
                        }
                        if (!this.C.E(string) && zR) {
                            C0575Hn.B(string);
                        }
                    }
                    C0611Iy c0611IyB = C0611Iy.B(this.B);
                    if (c0611IyB != null) {
                        c0611IyB.A(jSONObject.toString());
                    }
                }
            } catch (JSONException e) {
                if (BuildConfigApi.isDebug()) {
                    Log.e(D, "Unable to parse server response at position " + i + ".", e);
                }
                z = false;
            }
        }
        return z;
    }

    @Override // com.facebook.ads.redexgen.X.JT
    public final void wE() {
        this.C.A();
        C0575Hn.D(this.B);
    }
}
