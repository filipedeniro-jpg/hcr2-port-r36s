package com.adcolony.sdk;

import android.app.Activity;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class k extends ContentObserver {
    private AudioManager a;
    private AdColonyInterstitial b;

    @Override // android.database.ContentObserver
    public boolean deliverSelfNotifications() {
        return false;
    }

    public k(Handler handler, AdColonyInterstitial adColonyInterstitial) {
        super(handler);
        Activity activityC = a.c();
        if (activityC != null) {
            this.a = (AudioManager) activityC.getSystemService("audio");
            this.b = adColonyInterstitial;
            activityC.getApplicationContext().getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this);
        }
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        AdColonyInterstitial adColonyInterstitial;
        if (this.a == null || (adColonyInterstitial = this.b) == null || adColonyInterstitial.d() == null) {
            return;
        }
        double streamVolume = (this.a.getStreamVolume(3) / 15.0f) * 100.0f;
        int i = (int) streamVolume;
        if (this.b.g() && this.b.h().e() != null && !this.b.i()) {
            this.b.h().e().getAvidVideoPlaybackListener().recordAdVolumeChangeEvent(Integer.valueOf(i));
            this.b.h().a("volume_change");
        }
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "audio_percentage", streamVolume);
        y.a(jSONObjectA, "ad_session_id", this.b.d().b());
        y.b(jSONObjectA, "id", this.b.d().d());
        new af("AdContainer.on_audio_change", this.b.d().c(), jSONObjectA).b();
        new aa.a().a("Volume changed to ").a(streamVolume).a(aa.d);
    }

    void a() {
        Activity activityC = a.c();
        if (activityC != null) {
            activityC.getApplicationContext().getContentResolver().unregisterContentObserver(this);
        }
        this.b = null;
        this.a = null;
    }
}
