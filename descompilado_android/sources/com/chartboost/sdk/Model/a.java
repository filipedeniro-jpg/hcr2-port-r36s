package com.chartboost.sdk.Model;

import com.applovin.mediation.AppLovinNativeAdapter;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.ironsource.eventsmodule.DataBaseEventsStorage;
import com.ironsource.sdk.constants.Constants;
import com.my.target.ai;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public final JSONObject a;
    public final int b;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final int k;
    public final String l;
    public final String m;
    public final int o;
    public final String p;
    public final String q;
    public final b r;
    public final Map<String, b> c = new HashMap();
    public final Map<String, String> d = new HashMap();
    public final Map<String, List<String>> n = new HashMap();
    public final HashSet<String> s = new HashSet<>();

    public a(int i, JSONObject jSONObject, boolean z) throws JSONException {
        this.b = i;
        this.a = jSONObject;
        this.f = jSONObject.getString(AppLovinNativeAdapter.KEY_EXTRA_AD_ID);
        this.g = jSONObject.getString("cgn");
        this.h = jSONObject.getString("creative");
        this.i = jSONObject.optString("deep-link");
        this.j = jSONObject.getString("link");
        this.m = jSONObject.getString("to");
        this.o = jSONObject.optInt("animation");
        this.p = jSONObject.optString("media-type");
        this.q = jSONObject.optString("name");
        if (i == 1) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Constants.ParametersKeys.WEB_VIEW);
            JSONArray jSONArray = jSONObject2.getJSONArray(MessengerShareContentUtility.ELEMENTS);
            String str = "";
            int i2 = 0;
            int iIntValue = 0;
            while (true) {
                int length = jSONArray.length();
                String str2 = TtmlNode.TAG_BODY;
                if (i2 >= length) {
                    break;
                }
                JSONObject jSONObject3 = jSONArray.getJSONObject(i2);
                String string = jSONObject3.getString("name");
                String strOptString = jSONObject3.optString("param");
                String string2 = jSONObject3.getString("type");
                String string3 = jSONObject3.getString("value");
                if (string2.equals("param")) {
                    this.d.put(strOptString, string3);
                    if (string.equals("reward_amount")) {
                        iIntValue = Integer.valueOf(string3).intValue();
                    } else if (string.equals("reward_currency")) {
                        str = string3;
                    }
                } else {
                    this.c.put((string2.equals(ai.a.da) && strOptString.isEmpty()) ? str2 : strOptString.isEmpty() ? string : strOptString, new b(string2, string, string3));
                }
                i2++;
            }
            this.k = iIntValue;
            this.l = str;
            this.r = this.c.get(TtmlNode.TAG_BODY);
            if (this.r == null) {
                throw new RuntimeException("WebView AdUnit does not have a template html body asset");
            }
            this.e = jSONObject2.getString("template");
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(DataBaseEventsStorage.EventEntry.TABLE_NAME);
            if (jSONObjectOptJSONObject != null) {
                Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    JSONArray jSONArray2 = jSONObjectOptJSONObject.getJSONArray(next);
                    ArrayList arrayList = new ArrayList();
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        arrayList.add(jSONArray2.getString(i3));
                    }
                    this.n.put(next, arrayList);
                }
            }
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("certification_providers");
            if (jSONArrayOptJSONArray != null) {
                for (int i4 = 0; i4 < jSONArrayOptJSONArray.length(); i4++) {
                    this.s.add(jSONArrayOptJSONArray.getString(i4));
                }
                return;
            }
            return;
        }
        if (z) {
            String string4 = jSONObject.getJSONObject("icons").getString("lg");
            this.c.put("lg", new b("inPlayIcons", string4.substring(string4.lastIndexOf(com.appsflyer.share.Constants.URL_PATH_DELIMITER) + 1), string4));
            this.k = 0;
            this.l = "";
        } else {
            JSONObject jSONObject4 = jSONObject.getJSONObject("assets");
            Iterator<String> itKeys2 = jSONObject4.keys();
            while (itKeys2.hasNext()) {
                String next2 = itKeys2.next();
                JSONObject jSONObject5 = jSONObject4.getJSONObject(next2);
                String str3 = (next2.equals("video-portrait") || next2.equals("video-landscape")) ? "videos" : "images";
                String strOptString2 = jSONObject5.optString("id", null);
                if (strOptString2 == null) {
                    strOptString2 = jSONObject5.getString("checksum") + ".png";
                }
                this.c.put(next2, new b(str3, strOptString2, jSONObject5.getString("url")));
            }
            this.k = jSONObject.optInt("reward");
            this.l = jSONObject.optString("currency-name");
        }
        this.r = null;
        this.e = "";
    }
}
