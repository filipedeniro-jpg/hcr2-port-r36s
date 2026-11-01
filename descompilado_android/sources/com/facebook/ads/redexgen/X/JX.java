package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.util.Log;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public final class JX implements JV {
    private static final String G = JX.class.getSimpleName();
    private static volatile boolean H = false;

    @SuppressLint({"StaticFieldLeak"})
    @Nullable
    private static JV I;
    private static String J;
    private static double K;
    private final Context B;
    private final HW C;
    private final JU D;

    @Nullable
    private JY E;
    private final C5D F;

    /* JADX WARN: Failed to parse debug info
    java.lang.ArrayIndexOutOfBoundsException: Index 6 out of bounds for length 6
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.startVar(DebugInfoParser.java:203)
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.process(DebugInfoParser.java:135)
    	at jadx.plugins.input.dex.sections.DexCodeReader.getDebugInfo(DexCodeReader.java:122)
    	at jadx.core.dex.nodes.MethodNode.getDebugInfo(MethodNode.java:656)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoAttachVisitor.visit(DebugInfoAttachVisitor.java:38)
     */
    @Override // com.facebook.ads.redexgen.X.JV
    public final void CD(String str, Map<String, String> map) {
        G(new JP().H(str).E(K).D(J).B(map).C(JZ.IMMEDIATE).I(EnumC0613Ja.USER_RETURN).F(true).A());
    }

    /* JADX WARN: Failed to parse debug info
    java.lang.ArrayIndexOutOfBoundsException: Index 6 out of bounds for length 6
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.startVar(DebugInfoParser.java:203)
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.process(DebugInfoParser.java:135)
    	at jadx.plugins.input.dex.sections.DexCodeReader.getDebugInfo(DexCodeReader.java:122)
    	at jadx.core.dex.nodes.MethodNode.getDebugInfo(MethodNode.java:656)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoAttachVisitor.visit(DebugInfoAttachVisitor.java:38)
     */
    @Override // com.facebook.ads.redexgen.X.JV
    public final void eC(String str, Map<String, String> map) {
        G(new JP().H(str).E(K).D(J).B(map).C(JZ.DEFERRED).I(EnumC0613Ja.AD_REPORTING).F(false).A());
    }

    /* JADX WARN: Failed to parse debug info
    java.lang.ArrayIndexOutOfBoundsException: Index 6 out of bounds for length 6
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.startVar(DebugInfoParser.java:203)
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.process(DebugInfoParser.java:135)
    	at jadx.plugins.input.dex.sections.DexCodeReader.getDebugInfo(DexCodeReader.java:122)
    	at jadx.core.dex.nodes.MethodNode.getDebugInfo(MethodNode.java:656)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoAttachVisitor.visit(DebugInfoAttachVisitor.java:38)
     */
    @Override // com.facebook.ads.redexgen.X.JV
    public final void gC(String str, Map<String, String> map) {
        G(new JP().H(str).E(K).D(J).B(map).C(JZ.DEFERRED).I(EnumC0613Ja.BROWSER_SESSION).F(false).A());
    }

    private JX(Context context) {
        this.B = context.getApplicationContext();
        this.C = new HW(context);
        this.D = new JU(context, new C0615Jc(context, this.C));
        this.D.A();
        this.F = new C5D(context);
        F(context);
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void AD(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.DEFERRED).I(EnumC0613Ja.TWO_STEP_CANCEL).F(true).G(C0620Jh.B(token, EnumC0617Je.TWO_STEP_CANCEL_CLICK)).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void BD(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.DEFERRED).I(EnumC0613Ja.TWO_STEP).F(true).G(C0620Jh.B(token, EnumC0617Je.TWO_STEP_CLICK)).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void DD(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.IMMEDIATE).I(EnumC0613Ja.VIDEO).F(true).A());
    }

    public static synchronized JV E(Context appContext) {
        if (I == null) {
            I = new JX(appContext.getApplicationContext());
        }
        return I;
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void ED(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.IMMEDIATE).I(EnumC0613Ja.WATCH_AND_X_MINIMIZED).F(true).A());
    }

    private static synchronized void F(Context context) {
        if (!H) {
            C0588Ia.G(context);
            LX.E();
            K = LX.D();
            J = LX.B();
            H = true;
        }
    }

    private void G(JQ event) {
        if (!event.H()) {
            Log.e(G, "Attempting to log an invalid " + event.G() + " event.");
            return;
        }
        if (this.E != null) {
            this.E.preprocess(event);
        }
        this.C.P(event.F(), event.B().B, event.G().toString(), event.E(), event.D(), event.C(), event.A(), new JW(this, event));
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void YF(String baseURL) {
        new I9(this.B).execute(baseURL);
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void fC(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.DEFERRED).I(EnumC0613Ja.AD_SELECTION).F(true).G(C0620Jh.B(token, EnumC0617Je.AD_NUMBER_SELECTED)).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void hC(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.DEFERRED).I(EnumC0613Ja.CLICK_GUARD).F(true).G(C0620Jh.B(token, EnumC0617Je.CLICKGUARD_CLICK)).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void iC(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.DEFERRED).I(EnumC0613Ja.CLOSE).F(true).G(C0620Jh.B(token, EnumC0617Je.AD_CLOSED)).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void jC(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.DEFERRED).I(EnumC0613Ja.DYNAMIC_SDK_LAYER_EVENT).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void kC(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.DEFERRED).I(EnumC0613Ja.FUNNEL_LOGGING).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void lC(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.IMMEDIATE).I(EnumC0613Ja.IMPRESSION).F(true).G(C0620Jh.B(token, EnumC0617Je.IMPRESSION)).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void mC(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.IMMEDIATE).I(EnumC0613Ja.INVALIDATION).F(false).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void nC(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.IMMEDIATE).I(EnumC0613Ja.OPEN_LINK).F(true).G(C0620Jh.B(token, EnumC0617Je.LINK_CLICK)).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void oC(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.DEFERRED).I(EnumC0613Ja.OFF_TARGET_CLICK).F(true).G(C0620Jh.B(token, EnumC0617Je.OFF_TARGET_CLICK)).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void qC(String token, Map<String, String> map, String type, JZ priority) {
        G(new JP().H(token).E(K).D(J).B(map).C(priority).I(EnumC0613Ja.B(type)).F(true).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void rC(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.DEFERRED).I(EnumC0613Ja.PREVIEW_IMPRESSION).F(true).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void uC(String token) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).C(JZ.DEFERRED).I(EnumC0613Ja.SHOW_AD_CALLED).F(true).G(C0620Jh.B(token, EnumC0617Je.SHOW_AD_CALLED)).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void xC(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.DEFERRED).I(EnumC0613Ja.NATIVE_VIEW).F(false).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void yC(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.IMMEDIATE).I(EnumC0613Ja.STORE).F(true).G(C0620Jh.B(token, EnumC0617Je.STORE_CLICK)).A());
    }

    @Override // com.facebook.ads.redexgen.X.JV
    public final void zC(String token, Map<String, String> map) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        G(new JP().H(token).E(K).D(J).B(map).C(JZ.IMMEDIATE).I(EnumC0613Ja.SWIPE_TO_CLICK).F(true).G(C0620Jh.B(token, EnumC0617Je.SWIPE_TO_CLICK)).A());
    }
}
