package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.30, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass30 {

    @Nullable
    private C0618Jf B;
    private final List<String> D = new ArrayList();
    private final List<String> C = new ArrayList();

    private AnonymousClass30() {
    }

    public AnonymousClass30(C0618Jf funnelLoggingHandler) {
        this.B = funnelLoggingHandler;
    }

    public final void A() {
        this.D.clear();
        this.C.clear();
    }

    public final Map<String, String> B() {
        HashMap map = new HashMap();
        map.put("user_journey", new JSONArray((Collection) this.D).toString());
        map.put("options_selected", new JSONArray((Collection) this.C).toString());
        return map;
    }

    public final boolean C() {
        return (this.D.isEmpty() && this.C.isEmpty()) ? false : true;
    }

    public final void D(EnumC01982z type) {
        this.D.add(type.A() + "_end");
        if (this.B != null) {
            this.B.A(EnumC0617Je.AD_REPORTED, null);
        }
    }

    public final void E() {
        this.D.add("manage_ad_preferences");
        if (this.B != null) {
            this.B.A(EnumC0617Je.AD_REPORT_MANAGE_PREFS_CLICKED, null);
        }
    }

    public final void F(EnumC01982z type, int depth) {
        this.D.add(type.A() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + depth);
    }

    public final void G(int option) {
        this.C.add(String.valueOf(option));
    }

    public final void H() {
        this.D.add("start");
    }

    public final void I() {
        this.D.add("why_am_i_seeing_this");
        if (this.B != null) {
            this.B.A(EnumC0617Je.AD_REPORT_WHY_SEEING_THIS_CLICKED, null);
        }
    }
}
