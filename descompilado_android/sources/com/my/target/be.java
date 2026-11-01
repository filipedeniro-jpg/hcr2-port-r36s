package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.my.target.common.models.ImageData;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: CommonBannerParser.java */
/* JADX INFO: loaded from: classes2.dex */
public class be {
    private static final String eN = "deeplink";

    @NonNull
    private final b adConfig;

    @Nullable
    private String ax;

    @NonNull
    private final bh eA;

    @NonNull
    private final af eO;

    @NonNull
    private final Context ez;

    /* JADX INFO: compiled from: CommonBannerParser.java */
    public interface a {
        public static final String CATEGORY = "category";
        public static final String DESCRIPTION = "description";
        public static final String DURATION = "duration";
        public static final String HEIGHT = "height";
        public static final String ID = "id";
        public static final String SOURCE = "source";
        public static final String TITLE = "title";
        public static final String TYPE = "type";
        public static final String URL = "url";
        public static final String VALUE = "value";
        public static final String WIDTH = "width";
        public static final String dc = "statistics";
        public static final String eP = "trackingLink";
        public static final String eQ = "disclaimer";
        public static final String eR = "votes";
        public static final String eS = "rating";
        public static final String eT = "domain";
        public static final String eU = "subcategory";
        public static final String eV = "urlscheme";
        public static final String eW = "ageRestrictions";
        public static final String eX = "bundle_id";
        public static final String eY = "deeplink";
        public static final String eZ = "openInBrowser";
        public static final String eq = "bannerID";
        public static final String fa = "usePlayStoreAction";
        public static final String fb = "directLink";
        public static final String fc = "navigationType";
        public static final String fd = "ctaText";
        public static final String fe = "iconLink";
        public static final String ff = "iconWidth";
        public static final String fg = "iconHeight";
        public static final String fh = "imageLink";
        public static final String fi = "imageWidth";
        public static final String fj = "imageHeight";
        public static final String fk = "pvalue";
        public static final String fl = "viewablePercent";
        public static final String fm = "ovv";
        public static final String fn = "clickArea";
        public static final String fo = "extendedClickArea";
        public static final String fp = "advertisingLabel";
        public static final String fq = "adChoices";
        public static final String fr = "clickLink";
    }

    @NonNull
    public static be b(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        return new be(afVar, bVar, context);
    }

    @Nullable
    public static String f(@Nullable String str, @Nullable String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            StringBuilder sb = new StringBuilder(str2);
            if (sb.length() > 0) {
                Matcher matcher = Pattern.compile("<script\\s+[^>]*\\bsrc\\s*=\\s*(\\\\?[\\\"\\'])mraid\\.js\\1[^>]*>\\s*<\\/script>\\n*", 2).matcher(str2);
                if (matcher.find()) {
                    int iStart = matcher.start();
                    sb.delete(iStart, matcher.end());
                    sb.insert(iStart, "<script src=\"" + str + "\"></script>");
                    return sb.toString();
                }
            }
        }
        return null;
    }

    private be(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        this.eO = afVar;
        this.adConfig = bVar;
        this.ez = context;
        this.eA = bh.d(afVar, bVar, context);
    }

    public void a(@NonNull JSONObject jSONObject, @NonNull ai aiVar) {
        this.ax = jSONObject.optString("id");
        if (TextUtils.isEmpty(this.ax)) {
            this.ax = jSONObject.optString(a.eq, aiVar.getId());
        }
        aiVar.setId(this.ax);
        String strOptString = jSONObject.optString("type");
        if (!TextUtils.isEmpty(strOptString)) {
            aiVar.setType(strOptString);
        }
        aiVar.setWidth(jSONObject.optInt("width", aiVar.getWidth()));
        aiVar.setHeight(jSONObject.optInt("height", aiVar.getHeight()));
        String strOptString2 = jSONObject.optString("ageRestrictions");
        if (!TextUtils.isEmpty(strOptString2)) {
            aiVar.setAgeRestrictions(strOptString2);
        }
        String strOptString3 = jSONObject.optString("deeplink");
        if (!TextUtils.isEmpty(strOptString3)) {
            aiVar.setDeeplink(strOptString3);
        }
        String strOptString4 = jSONObject.optString(a.eP);
        if (!TextUtils.isEmpty(strOptString4)) {
            aiVar.setTrackingLink(strOptString4);
        }
        String strOptString5 = jSONObject.optString(a.eX);
        if (!TextUtils.isEmpty(strOptString5)) {
            aiVar.setBundleId(strOptString5);
        }
        String strOptString6 = jSONObject.optString(a.eV);
        if (!TextUtils.isEmpty(strOptString6)) {
            aiVar.setUrlscheme(strOptString6);
        }
        aiVar.setOpenInBrowser(jSONObject.optBoolean(a.eZ, aiVar.isOpenInBrowser()));
        aiVar.setUsePlayStoreAction(jSONObject.optBoolean(a.fa, aiVar.isUsePlayStoreAction()));
        aiVar.setDirectLink(jSONObject.optBoolean(a.fb, aiVar.isDirectLink()));
        String strOptString7 = jSONObject.optString(a.fc);
        if (!TextUtils.isEmpty(strOptString7)) {
            if ("deeplink".equals(strOptString7)) {
                aiVar.setNavigationType("store");
            } else {
                aiVar.setNavigationType(strOptString7);
            }
        }
        String strOptString8 = jSONObject.optString("title");
        if (!TextUtils.isEmpty(strOptString8)) {
            aiVar.setTitle(strOptString8);
        }
        String strOptString9 = jSONObject.optString("description");
        if (!TextUtils.isEmpty(strOptString9)) {
            aiVar.setDescription(strOptString9);
        }
        String strOptString10 = jSONObject.optString(a.eQ);
        if (!TextUtils.isEmpty(strOptString10)) {
            aiVar.setDisclaimer(strOptString10);
        }
        aiVar.setVotes(jSONObject.optInt("votes", aiVar.getVotes()));
        String strOptString11 = jSONObject.optString("category");
        if (!TextUtils.isEmpty(strOptString11)) {
            aiVar.setCategory(strOptString11);
        }
        String strOptString12 = jSONObject.optString("subcategory");
        if (!TextUtils.isEmpty(strOptString12)) {
            aiVar.setSubCategory(strOptString12);
        }
        String strOptString13 = jSONObject.optString("domain");
        if (!TextUtils.isEmpty(strOptString13)) {
            aiVar.setDomain(strOptString13);
        }
        aiVar.setDuration((float) jSONObject.optDouble("duration", aiVar.getDuration()));
        if (jSONObject.has("rating")) {
            float fOptDouble = (float) jSONObject.optDouble("rating", -1.0d);
            double d = fOptDouble;
            if (d <= 5.0d && d >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                aiVar.setRating(fOptDouble);
            } else {
                e(az.a.ei, "unable to parse rating " + fOptDouble);
            }
        }
        aiVar.setCtaText(jSONObject.optString(a.fd, aiVar.getCtaText()));
        String strOptString14 = jSONObject.optString(a.fe);
        int iOptInt = jSONObject.optInt(a.ff);
        int iOptInt2 = jSONObject.optInt(a.fg);
        if (!TextUtils.isEmpty(strOptString14)) {
            aiVar.setIcon(ImageData.newImageData(strOptString14, iOptInt, iOptInt2));
        }
        String strOptString15 = jSONObject.optString(a.fh);
        int iOptInt3 = jSONObject.optInt(a.fi);
        int iOptInt4 = jSONObject.optInt(a.fj);
        if (!TextUtils.isEmpty(strOptString15)) {
            aiVar.setImage(ImageData.newImageData(strOptString15, iOptInt3, iOptInt4));
        }
        if (jSONObject.has(a.fn)) {
            int iOptInt5 = jSONObject.optInt(a.fn);
            if (iOptInt5 <= 0) {
                e(az.a.ei, "Bad ClickArea mask " + iOptInt5);
            } else {
                aiVar.setClickArea(ag.c(iOptInt5));
            }
        } else if (jSONObject.has(a.fo)) {
            if (jSONObject.optBoolean(a.fo, true)) {
                aiVar.setClickArea(ag.cs);
            } else {
                aiVar.setClickArea(ag.ct);
            }
        }
        aiVar.setAdvertisingLabel(jSONObject.optString("advertisingLabel", ""));
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(a.fq);
        if (jSONObjectOptJSONObject != null) {
            String strOptString16 = jSONObjectOptJSONObject.optString(a.fe);
            String strOptString17 = jSONObjectOptJSONObject.optString(a.fr);
            if (!TextUtils.isEmpty(strOptString16) && !TextUtils.isEmpty(strOptString17)) {
                aiVar.setAdChoices(ae.a(ImageData.newImageData(strOptString16), strOptString17));
            }
        }
        this.eA.a(aiVar.getStatHolder(), jSONObject, this.ax, aiVar.getDuration());
    }

    public boolean a(@NonNull String str, @NonNull JSONObject jSONObject) {
        jSONObject.remove("source");
        try {
            jSONObject.put("source", str);
            return true;
        } catch (JSONException unused) {
            e(az.a.ek, "Unable to re-encode source of html banner");
            return false;
        }
    }

    private void e(@NonNull String str, @NonNull String str2) {
        az.z(str).A(str2).g(this.adConfig.getSlotId()).C(this.ax).B(this.eO.getUrl()).e(this.ez);
    }
}
