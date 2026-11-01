package com.inmobi.commons.core.e;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: CatchEvent.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends com.inmobi.commons.core.a.d {
    private static final String g = f.class.getSimpleName();

    public a(Throwable th) {
        super("crashReporting", "catchEvent");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", th.getClass().getSimpleName());
            jSONObject.put("message", th.getMessage());
            jSONObject.put("stack", Log.getStackTraceString(th));
            jSONObject.put("thread", Thread.currentThread().getName());
            this.f = jSONObject.toString();
        } catch (JSONException e) {
            new StringBuilder("JSONException: ").append(e);
        }
    }
}
