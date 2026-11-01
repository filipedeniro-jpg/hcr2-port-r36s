package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.appevents.UserDataStore;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.ironsource.sdk.constants.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0p, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C01360p implements C0C, C0O {
    private static final String k = C01360p.class.getSimpleName();
    private KG B;
    private String C;
    private Uri D;

    @Nullable
    private JV E;
    private int F;
    private List<KF> G;
    private int H;
    private String J;
    private Context K;
    private boolean L;
    private boolean M;
    private Collection<String> N;
    private KG O;
    private KG P;
    private boolean Q;
    private C0B R;
    private boolean S;
    private AnonymousClass01 T;
    private String U;
    private int V;
    private int W;
    private KH X;
    private boolean Z;
    private String a;
    private KL b;
    private String c;
    private String e;
    private boolean f;
    private boolean g;
    private K9 h;
    private int i;
    private int j;
    private HashMap<String, String> Y = new HashMap<>();
    private int d = 200;
    private int I = -1;

    public final boolean A() {
        return true;
    }

    @Override // com.facebook.ads.redexgen.X.C0C, com.facebook.ads.redexgen.X.C0O
    public final String AC() {
        return this.J;
    }

    public final String B() {
        if (!W()) {
            return null;
        }
        I();
        return C0662Kz.B(this.Y.get(TtmlNode.TAG_BODY));
    }

    @Override // com.facebook.ads.redexgen.X.C0C
    public final Collection<String> BC() {
        return this.N;
    }

    public final KG C() {
        if (W()) {
            return this.B;
        }
        return null;
    }

    public final String D() {
        if (W()) {
            return this.C;
        }
        return null;
    }

    private Map<String, String> D(Map<String, String> map) {
        HashMap map2 = new HashMap();
        if (map.containsKey(Constants.ParametersKeys.VIEW)) {
            map2.put(Constants.ParametersKeys.VIEW, map.get(Constants.ParametersKeys.VIEW));
        }
        if (map.containsKey("snapshot")) {
            map2.put("snapshot", map.get("snapshot"));
        }
        return map2;
    }

    /* JADX INFO: renamed from: E, reason: collision with other method in class */
    public final String m223E() {
        if (W()) {
            return "AdChoices";
        }
        return null;
    }

    private boolean E() {
        return (!(this.S || TextUtils.isEmpty(this.Y.get("advertiser_name"))) || (!TextUtils.isEmpty(this.Y.get("title")) && this.S)) && (this.O != null || this.S) && (this.P != null || HC() == AdPlacementType.NATIVE_BANNER);
    }

    @Override // com.facebook.ads.redexgen.X.C0C
    public final C0B EC() {
        return this.R;
    }

    public final KG F() {
        if (W()) {
            return this.P;
        }
        return null;
    }

    private void F(JSONObject jsonObject, String clientToken) {
        if (this.L) {
            throw new IllegalStateException("Adapter already loaded data");
        }
        if (jsonObject == null) {
            return;
        }
        MM.B(this.K, "Audience Network Loaded");
        this.J = clientToken;
        this.U = jsonObject.optString("request_id");
        this.F = jsonObject.optInt("an_logo_type", 0);
        String fbadCommand = MJ.D(jsonObject, "fbad_command");
        this.D = TextUtils.isEmpty(fbadCommand) ? null : Uri.parse(fbadCommand);
        for (String str : new String[]{"advertiser_name", "title", MessengerShareContentUtility.SUBTITLE, "headline", TtmlNode.TAG_BODY, FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET, "link_description", "sponsored_translation", "ad_translation", "promoted_translation"}) {
            this.Y.put(str, MJ.D(jsonObject, str));
        }
        String strD = MJ.D(jsonObject, "call_to_action");
        if (!TextUtils.isEmpty(strD)) {
            this.Y.put("call_to_action", strD);
        }
        this.O = KG.B(jsonObject.optJSONObject("icon"));
        this.P = KG.B(jsonObject.optJSONObject(MessengerShareContentUtility.MEDIA_IMAGE));
        this.X = KH.B(jsonObject.optJSONObject("star_rating"));
        this.a = MJ.D(jsonObject, "used_report_url");
        this.f = jsonObject.optBoolean("enable_view_log");
        this.g = jsonObject.optBoolean("enable_snapshot_log");
        this.W = jsonObject.optInt("snapshot_log_delay_second", 4);
        this.V = jsonObject.optInt("snapshot_compress_quality", 0);
        this.i = jsonObject.optInt("viewability_check_initial_delay", 0);
        this.j = jsonObject.optInt("viewability_check_interval", 1000);
        JSONObject adChoicesIconObject = jsonObject.optJSONObject("ad_choices_icon");
        if (adChoicesIconObject != null) {
            this.B = KG.B(adChoicesIconObject);
        }
        this.C = MJ.D(jsonObject, "ad_choices_link_url");
        this.R = C0B.B(jsonObject.optString("invalidation_behavior"));
        JSONArray jSONArray = null;
        try {
            JSONArray detectionStringsArray = new JSONArray(jsonObject.optString("detection_strings"));
            jSONArray = detectionStringsArray;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.N = C0D.C(jSONArray);
        this.e = MJ.D(jsonObject, "video_url");
        this.c = MJ.D(jsonObject, "video_mpd");
        if (!jsonObject.has("video_autoplay_enabled")) {
            this.b = KL.DEFAULT;
        } else {
            this.b = jsonObject.optBoolean("video_autoplay_enabled") ? KL.ON : KL.OFF;
        }
        try {
            JSONArray jSONArrayOptJSONArray = jsonObject.optJSONArray("carousel");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                int length = jSONArrayOptJSONArray.length();
                ArrayList arrayList = new ArrayList(length);
                for (int i = 0; i < length; i++) {
                    C01360p c01360p = new C01360p();
                    c01360p.G(this.K, jSONArrayOptJSONArray.getJSONObject(i), this.E, this.J, i, length);
                    arrayList.add(new KF(this.K, c01360p, null, this.h));
                }
                this.G = arrayList;
            }
        } catch (JSONException e2) {
            Log.e(k, "Unable to parse carousel data.", e2);
        }
        this.L = true;
        this.M = E();
    }

    public final KG G() {
        if (W()) {
            return this.O;
        }
        return null;
    }

    private void G(Context mContext, JSONObject json, JV mAdEventManager, String mClientToken, int cardIndex, int cardCount) {
        this.S = true;
        this.K = mContext;
        this.E = mAdEventManager;
        this.I = cardIndex;
        this.H = cardCount;
        F(json, mClientToken);
    }

    public final KH H() {
        if (!W()) {
            return null;
        }
        I();
        return this.X;
    }

    private void H(Map<String, String> map, final Map<String, String> map2) {
        try {
            final Map<String, String> urlParams = D(map);
            new Handler().postDelayed(new Runnable() { // from class: com.facebook.ads.redexgen.X.0o
                @Override // java.lang.Runnable
                public final void run() {
                    if (!TextUtils.isEmpty(this.B.J)) {
                        HashMap map3 = new HashMap();
                        map3.putAll(map2);
                        map3.putAll(urlParams);
                        if (this.B.E == null) {
                            return;
                        }
                        this.B.E.xC(this.B.J, map3);
                    }
                }
            }, this.W * 1000);
        } catch (Exception e) {
        }
    }

    @Override // com.facebook.ads.redexgen.X.C0O
    public AdPlacementType HC() {
        return AdPlacementType.NATIVE;
    }

    /* JADX INFO: renamed from: I, reason: collision with other method in class */
    public final int m224I() {
        return this.F;
    }

    private void I() {
        if (!this.Z) {
            if (this.E != null) {
                this.E.YF(this.a);
            }
            this.Z = true;
        }
    }

    public final List<KF> J() {
        if (W()) {
            return this.G;
        }
        return null;
    }

    public final int K() {
        return this.H;
    }

    public final int L() {
        return this.I;
    }

    public final String M() {
        return this.U;
    }

    public final int N() {
        if (this.V < 0 || this.V > 100) {
            return 0;
        }
        return this.V;
    }

    @Nullable
    public final String O(String name) {
        if (!W()) {
            return null;
        }
        I();
        return this.Y.get(name);
    }

    public final KL P() {
        return !W() ? KL.DEFAULT : this.b;
    }

    public final String Q() {
        if (W()) {
            return this.c;
        }
        return null;
    }

    public final int R() {
        return this.d;
    }

    public final String S() {
        if (W()) {
            return this.e;
        }
        return null;
    }

    public final int T() {
        return this.i;
    }

    public final int U() {
        return this.j;
    }

    public final boolean V() {
        return W() && this.D != null;
    }

    public final boolean W() {
        return this.L && this.M;
    }

    public final boolean X() {
        return this.S;
    }

    public final boolean Y() {
        return JA.w(this.K) && W() && this.f;
    }

    public final boolean Z() {
        return JA.w(this.K) && W() && this.g;
    }

    public final void a(Context mContext, AnonymousClass01 mListener, JV mAdEventManager, Map<String, Object> map, K9 viewTraversalPredicate) {
        int I;
        this.K = mContext;
        this.T = mListener;
        this.E = mAdEventManager;
        this.h = viewTraversalPredicate;
        JSONObject jSONObject = (JSONObject) map.get("data");
        IQ iq = (IQ) map.get("definition");
        if (iq != null) {
            I = iq.I();
        } else {
            I = 200;
        }
        this.d = I;
        F(jSONObject, MJ.D(jSONObject, UserDataStore.CITY));
        if (C0D.D(mContext, this, mAdEventManager)) {
            mListener.hE(this, new KQ(AdErrorType.NO_FILL, "No Fill"));
        } else {
            if (mListener == null) {
                return;
            }
            mListener.eE(this);
        }
    }

    public final void b(Map<String, String> map) {
        if (!W()) {
            return;
        }
        boolean shouldBlockLockscreenClicks = JA.HB(this.K);
        if (shouldBlockLockscreenClicks) {
            boolean shouldBlockLockscreenClicks2 = J4.C(map);
            if (shouldBlockLockscreenClicks2) {
                Log.e(k, "Click happened on lockscreen ad");
                return;
            }
        }
        HashMap map2 = new HashMap();
        if (map != null) {
            map2.putAll(map);
        }
        MM.B(this.K, "Click logged");
        if (this.T != null) {
            this.T.dE(this);
        }
        if (this.S) {
            map2.put("cardind", String.valueOf(this.I));
            map2.put("cardcnt", String.valueOf(this.H));
        }
        AnonymousClass09 anonymousClass09B = C0A.B(this.K, this.E, this.J, this.D, map2);
        if (anonymousClass09B == null) {
            return;
        }
        try {
            anonymousClass09B.A();
        } catch (Exception e) {
            Log.e(k, "Error executing action", e);
        }
    }

    public final void c(Map<String, String> map) {
        if (this.E != null) {
            this.E.hC(this.J, map);
        }
    }

    public final void d(Map<String, String> map) {
        if (!W() || this.Q) {
            return;
        }
        if (this.T != null) {
            this.T.fE(this);
        }
        HashMap map2 = new HashMap();
        if (map != null) {
            map2.putAll(map);
        }
        if (this.S) {
            map2.put("cardind", String.valueOf(this.I));
            map2.put("cardcnt", String.valueOf(this.H));
        }
        if (!TextUtils.isEmpty(AC()) && this.E != null) {
            this.E.lC(AC(), map2);
        }
        if (Y() || Z()) {
            H(map, map2);
        }
        this.Q = true;
    }

    public final void e(Map<String, String> map) {
        if (this.E != null) {
            this.E.AD(this.J, map);
        }
    }

    public final void f(Map<String, String> map) {
        if (this.E != null) {
            this.E.BD(this.J, map);
        }
    }

    public final void g(int visibility) {
    }

    public final void h(View view, List<View> clickableViews) {
    }

    public final void i(AnonymousClass01 mListener) {
        this.T = mListener;
    }

    public final void j() {
        if (this.G != null && !this.G.isEmpty()) {
            Iterator<KF> it = this.G.iterator();
            while (it.hasNext()) {
                it.next().unregisterView();
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.C0O
    public final void onDestroy() {
    }
}
