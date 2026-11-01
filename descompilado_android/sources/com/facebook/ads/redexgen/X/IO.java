package com.facebook.ads.redexgen.X;

import com.facebook.ads.internal.dto.AdCookieData;
import com.ironsource.sdk.constants.Constants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class IO {
    public Date B;
    public boolean C;
    public String D;
    public String E;
    public String F;

    private IO(String url, String key, String value, Date expirationDate) {
        this.E = url;
        this.D = key;
        this.F = value;
        this.B = expirationDate;
        this.C = (key == null || value == null) ? false : true;
    }

    private IO(JSONObject jsonObject) {
        this(jsonObject.optString("url"), jsonObject.optString(Constants.ParametersKeys.KEY), jsonObject.optString("value"), new Date(jsonObject.optLong("expiration") * 1000));
    }

    public final String A() {
        Date expirationDate = this.B;
        if (expirationDate == null) {
            expirationDate = new Date();
            expirationDate.setTime(expirationDate.getTime() + 3600000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(expirationDate);
    }

    public static List<IO> B(String metadata) {
        IO io2;
        List<AdCookieData> cookies = null;
        if (metadata != null) {
            JSONArray jSONArray = null;
            try {
                JSONArray jsonArray = new JSONArray(metadata);
                jSONArray = jsonArray;
            } catch (JSONException e) {
            }
            if (jSONArray != null) {
                cookies = new ArrayList<>();
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = null;
                    try {
                        jSONObject = jSONArray.getJSONObject(i);
                    } catch (JSONException e2) {
                    }
                    if (jSONObject != null && (io2 = new IO(jSONObject)) != null) {
                        cookies.add(io2);
                    }
                }
            }
        }
        return cookies;
    }

    public final boolean B() {
        return (this.D == null || this.F == null || this.E == null) ? false : true;
    }
}
