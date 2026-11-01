package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.55, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@SuppressLint({"StaticFieldLeak"})
public final class AnonymousClass55 {

    @InterfaceC01641r
    private static AnonymousClass55 H = null;
    private final AnonymousClass56 B;
    private final Context C;
    private EnumC02083j D;
    private AnonymousClass51 E;
    private AnonymousClass53 F;
    private C02254a G;

    private AnonymousClass55(Context context, AnonymousClass56 baseBdConfig) {
        this.C = context;
        this.B = baseBdConfig;
    }

    public final boolean A(String jsonResponse) {
        String string;
        boolean bdResponseHandled = false;
        if (jsonResponse == null || jsonResponse.isEmpty()) {
            return false;
        }
        try {
            JSONObject rootNode = new JSONObject(jsonResponse).getJSONObject("bd");
            if (rootNode == null) {
                return false;
            }
            bdResponseHandled = rootNode.has("rt") ? false | E(EnumC02083j.B(rootNode.getInt("rt"))) : false;
            JSONArray jSONArray = rootNode.getJSONArray("ch");
            for (int i = 0; i < i; i++) {
                JSONObject rootNode2 = jSONArray.getJSONObject(i);
                if (rootNode2.has("id") && (string = rootNode2.getString("id")) != null && !string.isEmpty()) {
                    bdResponseHandled |= D(string);
                }
            }
        } catch (JSONException e) {
        }
        return bdResponseHandled;
    }

    public static AnonymousClass55 B(Context context, AnonymousClass56 baseBdConfig) {
        if (H == null) {
            H = new AnonymousClass55(context, baseBdConfig);
        }
        return H;
    }

    public final void B(EnumC02083j reputationTier) {
        this.G = new C02254a(this.C, this.B);
        this.E = new AnonymousClass51(this.B, this.G);
        this.F = new AnonymousClass53();
        this.F.A(this.E);
        if (this.G.C() && C3O.E(reputationTier)) {
            this.E.A(reputationTier);
        }
        this.D = reputationTier;
        this.F.B(reputationTier);
    }

    public static void C(MotionEvent me) {
        C02404p.D(me);
    }

    private boolean D(String bdSessionId) {
        if (!C3O.E(this.D)) {
            return false;
        }
        C3O.C(this.B, EnumC02504z.INFO, EnumC02494y.EVENT_OCCURRED.A(), bdSessionId, null, null);
        if (this.G != null) {
            this.G.B(EnumC02063h.EVENT, this.D, bdSessionId);
        }
        return true;
    }

    private boolean E(EnumC02083j reputationTier) {
        if (reputationTier.equals(this.D)) {
            return false;
        }
        this.D = reputationTier;
        if (this.F != null) {
            this.F.B(reputationTier);
        }
        return true;
    }
}
