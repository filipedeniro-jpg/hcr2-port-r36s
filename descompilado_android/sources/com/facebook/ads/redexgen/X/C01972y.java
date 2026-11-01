package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2y, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01972y {
    private static final String[] C = {"hide_ad", "hide_ad_description", "hide_ad_follow_up_heading", "hide_ad_options", "report_ad", "report_ad_description", "report_ad_follow_up_heading", "report_ad_options", "manage_ad_preferences", "finished_hide_ad", "finished_report_ad", "finished_description", "why_am_i_seeing_this", "ad_choices_uri", "manage_ad_preferences_uri"};
    private static C01972y D;
    private final SharedPreferences B;

    private C01972y(Context context) {
        this.B = context.getApplicationContext().getSharedPreferences(I6.C("com.facebook.ads.AD_REPORTING_CONFIG", context), 0);
    }

    public static String B(Context context) {
        return S(context).W("ad_choices_uri", "");
    }

    public static String C(Context context) {
        return S(context).W("finished_description", "Your submission is now being reviewed.");
    }

    public static String D(Context context) {
        return S(context).W("finished_hide_ad", "Ad hidden.");
    }

    public static String E(Context context) {
        return S(context).W("finished_report_ad", "Ad reported.");
    }

    public static String F(Context context) {
        return S(context).W("hide_ad_description", "See fewer ads like this");
    }

    public static AnonymousClass31 G(Context context) {
        AnonymousClass31 anonymousClass31 = new AnonymousClass31(R(context));
        try {
            Iterator<AnonymousClass31> it = U(S(context).W("hide_ad_options", "")).iterator();
            while (it.hasNext()) {
                anonymousClass31.A(it.next());
            }
        } catch (JSONException e) {
            S(context).Y();
            C0585Hx.D(context, "reporting", C0584Hw.fB, new C0583Hv(e));
        }
        return anonymousClass31;
    }

    public static String H(Context context) {
        return S(context).W("hide_ad", "Hide Ad");
    }

    public static long I(Context context) {
        return S(context).T("last_updated_timestamp", 0L);
    }

    public static String J(Context context) {
        return S(context).W("manage_ad_preferences_uri", "");
    }

    public static String K(Context context) {
        return S(context).W("manage_ad_preferences", "Manage ad preferences");
    }

    public static String L(Context context) {
        return S(context).W("report_ad_description", " Mark ad as offensive or inappropriate");
    }

    public static AnonymousClass31 M(Context context) {
        AnonymousClass31 anonymousClass31 = new AnonymousClass31(V(context));
        try {
            Iterator<AnonymousClass31> it = U(S(context).W("report_ad_options", "")).iterator();
            while (it.hasNext()) {
                anonymousClass31.A(it.next());
            }
        } catch (JSONException e) {
            S(context).Y();
            C0585Hx.D(context, "reporting", C0584Hw.iB, new C0583Hv(e));
        }
        return anonymousClass31;
    }

    public static String N(Context context) {
        return S(context).W("report_ad", "Report Ad");
    }

    public static String O(Context context) {
        return S(context).W("why_am_i_seeing_this", "Why am I seeing this?");
    }

    public static void P(Context context, @Nullable String jsonString) {
        Map<String, String> jsonValues = X(context, jsonString);
        if (jsonValues == null || jsonValues.size() != C.length) {
            return;
        }
        SharedPreferences.Editor editor = S(context).B.edit();
        for (String str : C) {
            editor.putString(str, jsonValues.get(str));
        }
        editor.putLong("last_updated_timestamp", System.currentTimeMillis());
        editor.apply();
    }

    public static boolean Q(Context context, boolean fullscreen) {
        boolean zYB;
        if (fullscreen) {
            zYB = JA.XB(context);
        } else {
            zYB = JA.YB(context);
        }
        return zYB && I(context) > 0;
    }

    private static String R(Context context) {
        return S(context).W("hide_ad_follow_up_heading", "Help us understand what is happening. Why don't you want to see this?");
    }

    private static C01972y S(Context context) {
        if (D == null) {
            synchronized (C01972y.class) {
                if (D == null) {
                    D = new C01972y(context);
                }
            }
        }
        return D;
    }

    private long T(String key, long defaultValue) {
        return this.B.getLong(key, defaultValue);
    }

    private static List<AnonymousClass31> U(String value) throws JSONException {
        if (TextUtils.isEmpty(value) || value.equalsIgnoreCase("null")) {
            return new ArrayList();
        }
        JSONArray jSONArray = new JSONArray(value);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < i; i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.get(i);
            AnonymousClass31 anonymousClass31 = new AnonymousClass31(jSONObject.getInt("option_value"), jSONObject.getString("option_text"), jSONObject.optString("children_heading"));
            Iterator<AnonymousClass31> it = U(jSONObject.optString("children_options")).iterator();
            while (it.hasNext()) {
                anonymousClass31.A(it.next());
            }
            arrayList.add(anonymousClass31);
        }
        return arrayList;
    }

    private static String V(Context context) {
        return S(context).W("report_ad_follow_up_heading", "Help us understand what is happening. Why is this inappropriate?");
    }

    private String W(String key, String defaultValue) {
        String string = this.B.getString(key, defaultValue);
        return (string == null || string.equals("null")) ? defaultValue : string;
    }

    @Nullable
    private static Map<String, String> X(Context context, @Nullable String jsonString) {
        if (jsonString == null || jsonString.isEmpty() || jsonString.equals("[]")) {
            return null;
        }
        HashMap map = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(jsonString);
            for (String str : C) {
                if (!jSONObject.has(str)) {
                    return null;
                }
                map.put(str, jSONObject.getString(str));
            }
            if (U(jSONObject.getString("report_ad_options")).size() == 0) {
                C0585Hx.D(context, "reporting", C0584Hw.hB, new C0583Hv("No report ad options"));
                return null;
            }
            if (U(jSONObject.getString("hide_ad_options")).size() != 0) {
                return map;
            }
            C0585Hx.D(context, "reporting", C0584Hw.eB, new C0583Hv("No hide ad options"));
            return null;
        } catch (JSONException e) {
            C0585Hx.D(context, "reporting", C0584Hw.gB, new C0583Hv(e));
            return null;
        }
    }

    private void Y() {
        SharedPreferences.Editor editorEdit = this.B.edit();
        editorEdit.putLong("last_updated_timestamp", 0L);
        editorEdit.apply();
    }
}
