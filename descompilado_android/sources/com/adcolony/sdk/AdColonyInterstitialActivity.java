package com.adcolony.sdk;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AdColonyInterstitialActivity extends b {
    AdColonyInterstitial n;
    private k o;

    public AdColonyInterstitialActivity() {
        this.n = !a.b() ? null : a.a().u();
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
        AdColonyInterstitial adColonyInterstitial;
        AdColonyInterstitial adColonyInterstitial2 = this.n;
        this.d = adColonyInterstitial2 == null ? 0 : adColonyInterstitial2.e();
        super.onCreate(bundle);
        if (!a.b() || (adColonyInterstitial = this.n) == null) {
            return;
        }
        if (adColonyInterstitial.g()) {
            this.n.h().a(this.n.d());
        }
        this.o = new k(new Handler(Looper.getMainLooper()), this.n);
        if (this.n.getListener() != null) {
            this.n.getListener().onOpened(this.n);
        }
    }

    @Override // com.adcolony.sdk.b
    void a(af afVar) {
        AdColonyInterstitial adColonyInterstitial;
        super.a(afVar);
        d dVarM = a.a().m();
        f fVarRemove = dVarM.g().remove(this.e);
        if (fVarRemove != null) {
            for (MediaPlayer mediaPlayer : fVarRemove.c().c().values()) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
            }
            fVarRemove.d().a().autoPause();
            fVarRemove.d().a().release();
        }
        JSONObject jSONObjectF = y.f(afVar.c(), "v4iap");
        JSONArray jSONArrayG = y.g(jSONObjectF, "product_ids");
        if (jSONObjectF != null && (adColonyInterstitial = this.n) != null && adColonyInterstitial.getListener() != null && jSONArrayG.length() > 0) {
            this.n.getListener().onIAPEvent(this.n, y.c(jSONArrayG, 0), y.c(jSONObjectF, "engagement_type"));
        }
        dVarM.a(this.c);
        if (this.n != null) {
            dVarM.c().remove(this.n.f());
            if (this.n.g()) {
                this.n.h().a();
            }
        }
        AdColonyInterstitial adColonyInterstitial2 = this.n;
        if (adColonyInterstitial2 != null && adColonyInterstitial2.getListener() != null) {
            this.n.getListener().onClosed(this.n);
            this.n.a((c) null);
            this.n.setListener(null);
            this.n = null;
        }
        k kVar = this.o;
        if (kVar != null) {
            kVar.a();
            this.o = null;
        }
        new aa.a().a("finish_ad call finished").a(aa.d);
    }

    void a(AdColonyInterstitial adColonyInterstitial) {
        this.n = adColonyInterstitial;
    }
}
