package com.adcolony.sdk;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AdColonyAdViewActivity extends b {
    az n;
    boolean o;

    public AdColonyAdViewActivity() {
        this.n = !a.b() ? null : a.a().t();
        this.o = this.n instanceof AdColonyNativeAdView;
    }

    @Override // com.adcolony.sdk.b, android.app.Activity
    public /* bridge */ /* synthetic */ void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.adcolony.sdk.b, android.app.Activity, android.content.ComponentCallbacks
    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // com.adcolony.sdk.b, android.app.Activity
    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    @Override // com.adcolony.sdk.b, android.app.Activity
    public /* bridge */ /* synthetic */ void onPause() {
        super.onPause();
    }

    @Override // com.adcolony.sdk.b, android.app.Activity
    public /* bridge */ /* synthetic */ void onResume() {
        super.onResume();
    }

    @Override // com.adcolony.sdk.b, android.app.Activity, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    @Override // com.adcolony.sdk.b, android.app.Activity
    public void onCreate(Bundle bundle) {
        az azVar = this.n;
        this.d = azVar == null ? 0 : azVar.b;
        super.onCreate(bundle);
        if (!a.b() || this.n == null) {
            return;
        }
        a.a().d(true);
        e listener = this.n.getListener();
        if (listener == null || !(listener instanceof AdColonyNativeAdViewListener)) {
            return;
        }
        ((AdColonyNativeAdViewListener) listener).onOpened((AdColonyNativeAdView) this.n);
    }

    @Override // com.adcolony.sdk.b
    void a(af afVar) {
        super.a(afVar);
        if (this.n.getExpandedContainer() == null) {
            return;
        }
        JSONObject jSONObjectF = y.f(afVar.c(), "v4iap");
        JSONArray jSONArrayG = y.g(jSONObjectF, "product_ids");
        e listener = this.n.getListener();
        if (listener != null) {
            if (this.o) {
                AdColonyNativeAdViewListener adColonyNativeAdViewListener = (AdColonyNativeAdViewListener) listener;
                adColonyNativeAdViewListener.onClosed((AdColonyNativeAdView) this.n);
                if (jSONObjectF != null && jSONArrayG.length() > 0) {
                    adColonyNativeAdViewListener.onIAPEvent((AdColonyNativeAdView) this.n, y.c(jSONArrayG, 0), y.c(jSONObjectF, "engagement_type"));
                }
            } else {
                ba baVar = (ba) listener;
                baVar.c(this.n);
                if (jSONObjectF != null && jSONArrayG.length() > 0) {
                    baVar.a(this.n, y.c(jSONArrayG, 0), y.c(jSONObjectF, "engagement_type"));
                }
            }
        }
        ((ViewGroup) this.n.getExpandedContainer().getParent()).removeView(this.n.getExpandedContainer());
        a.a().m().a(this.n.getExpandedContainer());
        this.n.setExpandedContainer(null);
        System.gc();
    }
}
