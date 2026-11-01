package com.inmobi.commons.core.a;

import android.content.ContentValues;
import android.util.Log;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: CrashEvent.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {
    private static final String g = "d";
    int a;
    String b;
    String c;
    String d;
    long e;
    public String f;

    public d(Thread thread, Throwable th) {
        this("crashReporting", "CrashEvent");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", th.getClass().getSimpleName());
            jSONObject.put("message", th.getMessage());
            jSONObject.put("stack", Log.getStackTraceString(th));
            jSONObject.put("thread", thread.getName());
            this.f = jSONObject.toString();
        } catch (JSONException e) {
            new StringBuilder("JSONException: ").append(e);
        }
    }

    public d(String str, String str2) {
        this.b = UUID.randomUUID().toString();
        this.d = str;
        this.c = str2;
        this.f = null;
        this.e = System.currentTimeMillis();
    }

    private d(String str, String str2, String str3, String str4) {
        this.b = str;
        this.d = str2;
        this.c = str3;
        this.f = str4;
        this.e = System.currentTimeMillis();
    }

    public final String a() {
        String str = this.f;
        return str == null ? "" : str;
    }

    public String toString() {
        return this.c + "@" + this.d + " ";
    }

    public static d a(ContentValues contentValues) {
        String asString = contentValues.getAsString("eventId");
        String asString2 = contentValues.getAsString("eventType");
        String asString3 = contentValues.getAsString("componentType");
        String asString4 = contentValues.getAsString(MessengerShareContentUtility.ATTACHMENT_PAYLOAD);
        long jLongValue = Long.valueOf(contentValues.getAsString("ts")).longValue();
        d dVar = new d(asString, asString3, asString2, asString4);
        dVar.e = jLongValue;
        dVar.a = contentValues.getAsInteger("id").intValue();
        return dVar;
    }
}
