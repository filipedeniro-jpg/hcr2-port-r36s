package com.moat.analytics.mobile.inm;

import android.os.Build;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class l {
    private boolean a = false;
    private boolean b = false;
    private boolean c = false;
    private int d = 200;
    private int e = 10;

    l(String str) {
        a(str);
    }

    private void a(String str) {
        int i;
        if (str == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("sa");
            boolean zEquals = string.equals("c334ae83accfebb8da23104450c896463c9cfab7");
            boolean zEquals2 = string.equals("8f1d08a2d6496191a5ebae8f0590f513e2619489");
            if ((string.equals("on") || zEquals || zEquals2) && !a(jSONObject) && !b(jSONObject)) {
                this.a = true;
                this.b = zEquals;
                this.c = zEquals2;
                if (this.c) {
                    this.b = true;
                }
            }
            if (jSONObject.has("in") && (i = jSONObject.getInt("in")) >= 100 && i <= 1000) {
                this.d = i;
            }
            if (jSONObject.has("es")) {
                this.e = jSONObject.getInt("es");
            }
            if (c(jSONObject)) {
                ((k) MoatAnalytics.getInstance()).c = true;
            }
        } catch (Exception e) {
            this.a = false;
            this.b = false;
            this.d = 200;
            m.a(e);
        }
    }

    private boolean a(JSONObject jSONObject) {
        try {
            if (16 > Build.VERSION.SDK_INT) {
                return true;
            }
            if (jSONObject.has("ob")) {
                JSONArray jSONArray = jSONObject.getJSONArray("ob");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    if (jSONArray.getInt(i) == Build.VERSION.SDK_INT) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    private boolean b(JSONObject jSONObject) {
        try {
            if (jSONObject.has("ap")) {
                String strB = s.d().b();
                JSONArray jSONArray = jSONObject.getJSONArray("ap");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    if (jSONArray.getString(i).contentEquals(strB)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            m.a(e);
        }
        return false;
    }

    private boolean c(JSONObject jSONObject) {
        try {
            if (jSONObject.has("al")) {
                String strB = s.d().b();
                JSONArray jSONArray = jSONObject.getJSONArray("al");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    if (jSONArray.getString(i).contentEquals(strB)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            m.a(e);
        }
        return false;
    }

    boolean a() {
        return this.b;
    }

    boolean b() {
        return this.c;
    }

    int c() {
        return this.d;
    }

    int d() {
        return this.e;
    }

    w.d e() {
        return this.a ? w.d.ON : w.d.OFF;
    }
}
