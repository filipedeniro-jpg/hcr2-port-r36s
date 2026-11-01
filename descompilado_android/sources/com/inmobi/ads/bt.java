package com.inmobi.ads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: VastCompanionAd.java */
/* JADX INFO: loaded from: classes2.dex */
public class bt {
    static final ArrayList<String> f = new ArrayList<>(Arrays.asList("image/jpeg", "image/png"));
    private static final String h = "bt";
    int a;
    int b;
    List<a> c = new ArrayList();
    List<NativeTracker> d = new ArrayList();
    String e;
    boolean g;

    @Nullable
    private String i;

    /* JADX INFO: compiled from: VastCompanionAd.java */
    static final class a {
        int a;
        String b;

        a(int i, String str) {
            this.a = i;
            this.b = str;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        static a a(JSONObject jSONObject) {
            try {
                String string = jSONObject.getString("type");
                int i = 0;
                if (string != null && string.trim().length() != 0) {
                    String lowerCase = string.toLowerCase(Locale.US);
                    byte b = -1;
                    switch (lowerCase.hashCode()) {
                        case -1191214428:
                            if (lowerCase.equals("iframe")) {
                                b = 4;
                            }
                            break;
                        case -892481938:
                            if (lowerCase.equals("static")) {
                                b = 2;
                            }
                            break;
                        case -284840886:
                            if (lowerCase.equals("unknown")) {
                                b = 1;
                            }
                            break;
                        case 3213227:
                            if (lowerCase.equals(com.my.target.ai.a.da)) {
                                b = 3;
                            }
                            break;
                    }
                    if (b == 2) {
                        i = 1;
                    } else if (b == 3) {
                        i = 2;
                    } else if (b == 4) {
                        i = 3;
                    }
                }
                return new a(i, jSONObject.getString("content"));
            } catch (JSONException e) {
                String unused = bt.h;
                new StringBuilder("Error building resource from JSONObject; ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return null;
            }
        }

        public final String toString() {
            String str;
            JSONObject jSONObject = new JSONObject();
            try {
                int i = this.a;
                if (i == 1) {
                    str = "static";
                } else if (i != 2) {
                    str = i != 3 ? "unknown" : "iframe";
                } else {
                    str = com.my.target.ai.a.da;
                }
                jSONObject.put("type", str);
                jSONObject.put("content", this.b);
                return jSONObject.toString();
            } catch (JSONException e) {
                String unused = bt.h;
                new StringBuilder("Error serializing resource: ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return "";
            }
        }
    }

    public bt(int i, int i2, String str, @Nullable String str2) {
        this.i = str2;
        this.a = i;
        this.b = i2;
        this.e = str;
    }

    @NonNull
    final List<a> a(int i) {
        ArrayList arrayList = new ArrayList();
        for (a aVar : this.c) {
            if (aVar.a == i) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    final void a(@NonNull a aVar) {
        this.c.add(aVar);
    }

    @NonNull
    final List<NativeTracker> a(NativeTracker.TrackerEventType trackerEventType) {
        ArrayList arrayList = new ArrayList();
        for (NativeTracker nativeTracker : this.d) {
            if (nativeTracker.b.equals(trackerEventType)) {
                arrayList.add(nativeTracker);
            }
        }
        return arrayList;
    }

    public final void a(@NonNull NativeTracker nativeTracker) {
        this.d.add(nativeTracker);
    }

    @Nullable
    static bt a(JSONObject jSONObject) {
        try {
            bt btVar = new bt(jSONObject.getInt("width"), jSONObject.getInt("height"), jSONObject.has("clickThroughUrl") ? jSONObject.getString("clickThroughUrl") : null, jSONObject.optString("id", null));
            try {
                JSONArray jSONArray = new JSONArray(jSONObject.getString("trackers"));
                for (int i = 0; i < jSONArray.length(); i++) {
                    NativeTracker nativeTrackerA = NativeTracker.a(new JSONObject(jSONArray.getString(i)));
                    if (nativeTrackerA != null) {
                        btVar.a(nativeTrackerA);
                    }
                }
            } catch (JSONException e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            try {
                JSONArray jSONArray2 = new JSONArray(jSONObject.getString("resources"));
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    a aVarA = a.a(new JSONObject(jSONArray2.getString(i2)));
                    if (aVarA != null) {
                        btVar.a(aVarA);
                    }
                }
            } catch (JSONException e2) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
            return btVar;
        } catch (JSONException e3) {
            new StringBuilder("Error building companion from JSON: ").append(e3.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
            return null;
        }
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.i != null) {
                jSONObject.put("id", this.i);
            }
            jSONObject.put("width", this.a);
            jSONObject.put("height", this.b);
            jSONObject.put("clickThroughUrl", this.e);
            JSONArray jSONArray = new JSONArray();
            Iterator<a> it = this.c.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toString());
            }
            jSONObject.put("resources", jSONArray);
            JSONArray jSONArray2 = new JSONArray();
            Iterator<NativeTracker> it2 = this.d.iterator();
            while (it2.hasNext()) {
                jSONArray2.put(it2.next().toString());
            }
            jSONObject.put("trackers", jSONArray2);
            return jSONObject.toString();
        } catch (JSONException e) {
            StringBuilder sb = new StringBuilder("Error serializing an ");
            sb.append(h);
            sb.append(" instance: ");
            sb.append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }
}
