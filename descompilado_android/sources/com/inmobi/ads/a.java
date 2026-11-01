package com.inmobi.ads;

import android.content.ContentValues;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.ads.mediation.facebook.FacebookMediationAdapter;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.sdk.constants.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Ad.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static final String l = "a";
    final int a;
    final String b;
    final String c;
    final long d;
    long e;
    long f;
    public final String g;
    String h;
    String i;
    boolean j;
    public final float k;
    private final String m;
    private String n;
    private InMobiAdRequest.MonetizationContext o;

    @Nullable
    private final String p;

    /* synthetic */ a(JSONObject jSONObject, long j, String str, String str2, String str3, String str4, InMobiAdRequest.MonetizationContext monetizationContext, long j2, float f, String str5, byte b) {
        this(jSONObject, j, str, str2, str3, str4, monetizationContext, j2, f, str5);
    }

    /* JADX INFO: renamed from: com.inmobi.ads.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Ad.java */
    static final class C0070a {
        @Nullable
        static a a(JSONObject jSONObject, long j, String str, String str2, String str3, InMobiAdRequest.MonetizationContext monetizationContext, r rVar) {
            a aVar;
            try {
                String string = jSONObject.getString("markupType");
                long jOptLong = jSONObject.optLong("expiry", -1L);
                long millis = (-1 == jOptLong || jOptLong <= 0) ? -1L : TimeUnit.SECONDS.toMillis(jOptLong);
                String string2 = jSONObject.getString("impressionId");
                float f = Float.parseFloat(com.inmobi.commons.core.network.d.a(com.inmobi.commons.core.utilities.a.b.a(Base64.decode(jSONObject.getString("bid"), 0), rVar.b, rVar.a)));
                String strA = rVar.a(jSONObject.optString("bidInfoEncrypted", null));
                byte b = -1;
                int iHashCode = string.hashCode();
                if (iHashCode != -1084172778) {
                    if (iHashCode == 3213227 && string.equals(com.my.target.ai.a.da)) {
                        b = 1;
                    }
                } else if (string.equals("inmobiJson")) {
                    b = 2;
                }
                try {
                    if (b == 1) {
                        return new a(jSONObject, j, str, str2, string2, str3, monetizationContext, millis, f, strA, (byte) 0);
                    }
                    if (b != 2) {
                        return null;
                    }
                    JSONObject jSONObject2 = new JSONObject(jSONObject.getString("pubContent"));
                    String unused = a.l;
                    jSONObject2.toString();
                    if (jSONObject2.isNull("rootContainer")) {
                        String unused2 = a.l;
                        try {
                            HashMap map = new HashMap();
                            map.put("errorCode", "MISSING rootContainer");
                            map.put(IronSourceConstants.EVENTS_ERROR_REASON, "Missing rootContainer ad markup");
                            com.inmobi.commons.core.e.b.a();
                            com.inmobi.commons.core.e.b.a("ads", "ServerError", map);
                        } catch (Exception e) {
                            String unused3 = a.l;
                            StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                            sb.append(e.getMessage());
                            sb.append(")");
                        }
                        return null;
                    }
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("rootContainer");
                    JSONArray jSONArray = new JSONArray();
                    Iterator<String> it = c(jSONObject3).iterator();
                    while (it.hasNext()) {
                        a.a(jSONArray, it.next(), 2);
                    }
                    Iterator<String> it2 = d(jSONObject3).iterator();
                    while (it2.hasNext()) {
                        a.a(jSONArray, it2.next(), 1);
                    }
                    String strA2 = a(jSONObject3);
                    boolean zB = b(jSONObject3);
                    try {
                        if (strA2.trim().length() == 0) {
                            String unused4 = a.l;
                            return new a(jSONObject, jSONArray.toString(), j, str, str2, string2, str3, monetizationContext, zB, millis, f, strA);
                        }
                        c cVar = new c();
                        com.inmobi.commons.core.configs.b.a().a(cVar, (com.inmobi.commons.core.configs.b.c) null);
                        bx bxVarA = new bu(cVar.m).a(strA2);
                        if (bxVarA.f != 0) {
                            String unused5 = a.l;
                            HashMap map2 = new HashMap();
                            map2.put("errorCode", String.valueOf(bxVarA.f));
                            map2.put(IronSourceConstants.EVENTS_ERROR_REASON, "Processing VAST XML to build a video descriptor failed");
                            map2.put("latency", AppEventsConstants.EVENT_PARAM_VALUE_NO);
                            com.inmobi.commons.core.e.b.a();
                            com.inmobi.commons.core.e.b.a("ads", "VastProcessingError", map2);
                            return null;
                        }
                        HashMap map3 = new HashMap();
                        map3.put("message", "VAST PROCESSING SUCCESS");
                        com.inmobi.commons.core.e.b.a();
                        com.inmobi.commons.core.e.b.a("ads", "VastProcessingSuccess", map3);
                        List<NativeTracker> list = bxVarA.d;
                        JSONArray jSONArray2 = new JSONArray();
                        Iterator<NativeTracker> it3 = list.iterator();
                        while (it3.hasNext()) {
                            jSONArray2.put(it3.next().toString());
                        }
                        List<bt> list2 = bxVarA.e;
                        JSONArray jSONArray3 = new JSONArray();
                        Iterator<bt> it4 = list2.iterator();
                        while (it4.hasNext()) {
                            jSONArray3.put(it4.next().toString());
                        }
                        String strB = bxVarA.b();
                        if (strB != null && !strB.isEmpty()) {
                            a.a(jSONArray, strB, 0);
                            List<String> listA = a(jSONObject2, cVar.m);
                            String unused6 = a.l;
                            new StringBuilder("Media size for pages").append(listA.size());
                            Iterator<String> it5 = listA.iterator();
                            while (it5.hasNext()) {
                                a.a(jSONArray, it5.next(), 0);
                            }
                            Iterator<String> it6 = a(jSONObject2, "pages").iterator();
                            while (it6.hasNext()) {
                                a.a(jSONArray, it6.next(), 2);
                            }
                            Iterator<String> it7 = b(jSONObject2, "pages").iterator();
                            while (it7.hasNext()) {
                                a.a(jSONArray, it7.next(), 1);
                            }
                            return new bc(jSONObject, jSONArray.toString(), j, str, str2, string2, str3, bxVarA.b(), bxVarA.b, bxVarA.c, jSONArray2.toString(), jSONArray3.toString(), monetizationContext, zB, millis, f, strA);
                        }
                        HashMap map4 = new HashMap();
                        map4.put("errorCode", "ZERO LENGTH ASSET");
                        map4.put(IronSourceConstants.EVENTS_ERROR_REASON, "Asset length is 0");
                        com.inmobi.commons.core.e.b.a();
                        com.inmobi.commons.core.e.b.a("ads", "ServerError", map4);
                        String unused7 = a.l;
                        return null;
                    } catch (Exception e2) {
                        e = e2;
                        aVar = null;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Exception e4) {
                e = e4;
                aVar = null;
            }
            String unused8 = a.l;
            new StringBuilder("Error parsing ad markup; ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return aVar;
        }

        private static List<String> a(JSONObject jSONObject, c.j jVar) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("pages");
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (!jSONArray.getJSONObject(i).isNull("rootContainer")) {
                        String strA = a(jSONArray.getJSONObject(i).getJSONObject("rootContainer"));
                        if (strA.trim().length() == 0) {
                            String unused = a.l;
                        } else {
                            bx bxVarA = new bu(jVar).a(strA);
                            if (bxVarA == null || bxVarA.f != 0) {
                                String unused2 = a.l;
                            } else {
                                String strB = bxVarA.b();
                                if (strB != null && !strB.isEmpty()) {
                                    arrayList.add(strB);
                                }
                            }
                        }
                    }
                }
            } catch (JSONException unused3) {
                String unused4 = a.l;
            }
            return arrayList;
        }

        private static List<String> a(JSONObject jSONObject, String str) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (!jSONArray.getJSONObject(i).isNull("rootContainer")) {
                        arrayList.addAll(c(jSONArray.getJSONObject(i).getJSONObject("rootContainer")));
                    }
                }
            } catch (JSONException unused) {
                String unused2 = a.l;
            }
            return arrayList;
        }

        private static List<String> b(JSONObject jSONObject, String str) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray(str);
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (!jSONArray.getJSONObject(i).isNull("rootContainer")) {
                        arrayList.addAll(d(jSONArray.getJSONObject(i).getJSONObject("rootContainer")));
                    }
                }
            } catch (JSONException unused) {
                String unused2 = a.l;
            }
            return arrayList;
        }

        static a a(ContentValues contentValues) {
            if (contentValues.containsKey("video_url") && contentValues.getAsString("video_url") != null && !contentValues.getAsString("video_url").isEmpty()) {
                return new bc(contentValues);
            }
            return new a(contentValues);
        }

        @NonNull
        private static String a(@NonNull JSONObject jSONObject) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray.length() == 0) {
                    return "";
                }
                String string = jSONObject.getString("assetType");
                if (string.equalsIgnoreCase("video")) {
                    return jSONArray.getString(0);
                }
                if (!string.equalsIgnoreCase("container")) {
                    return "";
                }
                String strA = "";
                for (int i = 0; i < jSONArray.length(); i++) {
                    strA = a(jSONArray.getJSONObject(i));
                    if (strA.trim().length() != 0) {
                        break;
                    }
                }
                return strA;
            } catch (JSONException e) {
                String unused = a.l;
                StringBuilder sb = new StringBuilder("Error getting VAST video XML (");
                sb.append(e.getMessage());
                sb.append(")");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return "";
            }
        }

        private static boolean b(@NonNull JSONObject jSONObject) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray.length() == 0) {
                    return false;
                }
                String string = jSONObject.getString("assetType");
                if (string.equalsIgnoreCase(Constants.ParametersKeys.WEB_VIEW)) {
                    return !jSONObject.isNull("preload") && jSONObject.getBoolean("preload");
                }
                if (!string.equalsIgnoreCase("container")) {
                    return false;
                }
                boolean zB = false;
                for (int i = 0; i < jSONArray.length() && !(zB = b(jSONArray.getJSONObject(i))); i++) {
                }
                return zB;
            } catch (JSONException e) {
                String unused = a.l;
                StringBuilder sb = new StringBuilder("Error getting preload webview flag (");
                sb.append(e.getMessage());
                sb.append(")");
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return false;
            }
        }

        @NonNull
        private static List<String> c(@NonNull JSONObject jSONObject) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray.length() == 0) {
                    return arrayList;
                }
                String string = jSONObject.getString("assetType");
                if (string.equalsIgnoreCase(MessengerShareContentUtility.MEDIA_IMAGE)) {
                    if (!jSONObject.isNull("preload") && jSONObject.getBoolean("preload")) {
                        arrayList.add(jSONArray.getString(0));
                    }
                    return arrayList;
                }
                if (string.equalsIgnoreCase("container")) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.addAll(c(jSONArray.getJSONObject(i)));
                    }
                }
                return arrayList;
            } catch (JSONException e) {
                String unused = a.l;
                StringBuilder sb = new StringBuilder("Error getting getImageAssetUrls (");
                sb.append(e.getMessage());
                sb.append(")");
                return arrayList;
            }
        }

        @NonNull
        private static List<String> d(@NonNull JSONObject jSONObject) {
            ArrayList arrayList = new ArrayList();
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray.length() == 0) {
                    return arrayList;
                }
                String string = jSONObject.getString("assetType");
                if (string.equalsIgnoreCase("gif")) {
                    arrayList.add(jSONArray.getString(0));
                    return arrayList;
                }
                if (string.equalsIgnoreCase("container")) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.addAll(d(jSONArray.getJSONObject(i)));
                    }
                }
                return arrayList;
            } catch (JSONException e) {
                String unused = a.l;
                StringBuilder sb = new StringBuilder("Error getting getGifAssetUrls (");
                sb.append(e.getMessage());
                sb.append(")");
                return arrayList;
            }
        }
    }

    private a(JSONObject jSONObject, long j, String str, String str2, String str3, String str4, InMobiAdRequest.MonetizationContext monetizationContext, long j2, float f, @Nullable String str5) {
        this(jSONObject, null, j, str, str2, str3, str4, monetizationContext, false, j2, f, str5);
    }

    a(JSONObject jSONObject, String str, long j, String str2, String str3, String str4, String str5, InMobiAdRequest.MonetizationContext monetizationContext, boolean z, long j2, float f, @Nullable String str6) {
        this.a = -1;
        this.c = jSONObject.toString();
        this.n = str;
        this.d = j;
        this.b = str2;
        this.m = str3;
        this.e = System.currentTimeMillis();
        this.g = str4;
        this.h = str5;
        this.o = monetizationContext;
        this.i = "";
        this.j = z;
        this.f = j2;
        this.k = f;
        this.p = str6;
    }

    a(ContentValues contentValues) {
        this.a = contentValues.getAsInteger("id").intValue();
        this.b = contentValues.getAsString(AppEventsConstants.EVENT_PARAM_AD_TYPE);
        this.m = contentValues.getAsString("ad_size");
        this.n = contentValues.getAsString("asset_urls");
        this.c = contentValues.getAsString("ad_content");
        this.d = contentValues.getAsLong(FacebookMediationAdapter.RTB_PLACEMENT_PARAMETER).longValue();
        this.e = contentValues.getAsLong("insertion_ts").longValue();
        this.f = contentValues.getAsLong("expiry_duration").longValue();
        this.g = contentValues.getAsString("imp_id");
        this.h = contentValues.getAsString("client_request_id");
        this.o = InMobiAdRequest.MonetizationContext.a(contentValues.getAsString("m10_context"));
        if (this.o == null) {
            this.o = InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;
        }
        this.i = contentValues.getAsString("web_vast");
        this.j = contentValues.getAsInteger("preload_webView").intValue() != 0;
        this.k = contentValues.getAsFloat("bid").floatValue();
        this.p = contentValues.getAsString("bidInfo");
    }

    public ContentValues a() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppEventsConstants.EVENT_PARAM_AD_TYPE, this.b);
        contentValues.put("ad_size", this.m);
        contentValues.put("asset_urls", this.n);
        contentValues.put("ad_content", this.c);
        contentValues.put(FacebookMediationAdapter.RTB_PLACEMENT_PARAMETER, Long.valueOf(this.d));
        contentValues.put("insertion_ts", Long.valueOf(this.e));
        contentValues.put("expiry_duration", Long.valueOf(this.f));
        contentValues.put("imp_id", this.g);
        contentValues.put("client_request_id", this.h);
        contentValues.put("m10_context", this.o.a);
        String str = this.i;
        if (str != null) {
            contentValues.put("web_vast", str);
        }
        contentValues.put("preload_webView", Integer.valueOf(this.j ? 1 : 0));
        contentValues.put("bid", Float.valueOf(this.k));
        contentValues.put("bidInfo", this.p);
        return contentValues;
    }

    @NonNull
    public final JSONObject b() {
        try {
            return this.p == null ? new JSONObject() : new JSONObject(this.p);
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }

    final long c() {
        long j = this.f;
        if (j == -1) {
            return -1L;
        }
        return this.e + j;
    }

    @NonNull
    public final Set<bm> d() {
        HashSet hashSet = new HashSet();
        String str = this.n;
        if (str != null && str.length() != 0) {
            try {
                JSONArray jSONArray = new JSONArray(this.n);
                if (jSONArray.length() == 0) {
                    return hashSet;
                }
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = new JSONObject(jSONArray.getString(i));
                    int i2 = jSONObject.getInt("type");
                    String string = jSONObject.getString("url");
                    if (string != null) {
                        hashSet.add(new bm(i2, string));
                    }
                }
                return hashSet;
            } catch (JSONException e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
        return hashSet;
    }

    @NonNull
    final String e() {
        try {
            JSONObject jSONObject = new JSONObject(this.c);
            return jSONObject.isNull("markupType") ? "" : jSONObject.getString("markupType");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }

    final boolean a(@NonNull a aVar) {
        return d().equals(aVar.d());
    }

    @Nullable
    public final JSONObject f() {
        try {
            JSONObject jSONObject = new JSONObject(this.c);
            if (jSONObject.has("cachedAdData")) {
                return jSONObject.getJSONObject("cachedAdData");
            }
            return null;
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return null;
        }
    }

    static /* synthetic */ void a(JSONArray jSONArray, String str, int i) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", i);
        jSONObject.put("url", str);
        jSONArray.put(jSONObject);
    }
}
