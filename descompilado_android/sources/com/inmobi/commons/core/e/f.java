package com.inmobi.commons.core.e;

import android.content.ContentValues;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.UUID;

/* JADX INFO: compiled from: TelemetryEvent.java */
/* JADX INFO: loaded from: classes2.dex */
public class f {
    private static final String g = "f";
    int a;
    String b;
    String c;
    String d;
    long e;
    String f;

    public f(String str, String str2) {
        this.b = UUID.randomUUID().toString();
        this.d = str;
        this.c = str2;
        this.f = null;
        this.e = System.currentTimeMillis();
    }

    private f(String str, String str2, String str3, String str4) {
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

    public static f a(ContentValues contentValues) {
        String asString = contentValues.getAsString("eventId");
        String asString2 = contentValues.getAsString("eventType");
        String asString3 = contentValues.getAsString("componentType");
        String asString4 = contentValues.getAsString(MessengerShareContentUtility.ATTACHMENT_PAYLOAD);
        long jLongValue = Long.valueOf(contentValues.getAsString("ts")).longValue();
        f fVar = new f(asString, asString3, asString2, asString4);
        fVar.e = jLongValue;
        fVar.a = contentValues.getAsInteger("id").intValue();
        return fVar;
    }
}
