package com.applovin.impl.sdk.ad;

import android.net.Uri;
import com.applovin.impl.sdk.utils.k;
import com.my.target.ai;
import net.nend.android.NendAdNativeMediaView;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a extends g {
    public a(JSONObject jSONObject, JSONObject jSONObject2, b bVar, com.applovin.impl.sdk.h hVar) {
        super(jSONObject, jSONObject2, bVar, hVar);
    }

    private String aA() {
        return getStringFromAdObject("stream_url", "");
    }

    private void b(String str) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put("backup_stream_url", str);
            }
        } catch (Throwable unused) {
        }
    }

    public String a() {
        String strA;
        synchronized (this.adObjectLock) {
            strA = com.applovin.impl.sdk.utils.g.a(this.adObject, ai.a.da, (String) null, this.sdk);
        }
        return strA;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.net.Uri r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.adObjectLock     // Catch: java.lang.Throwable -> L14
            monitor-enter(r0)     // Catch: java.lang.Throwable -> L14
            org.json.JSONObject r1 = r3.adObject     // Catch: java.lang.Throwable -> L11
            java.lang.String r2 = "video"
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L11
            r1.put(r2, r4)     // Catch: java.lang.Throwable -> L11
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L11
            goto L14
        L11:
            r4 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L11
            throw r4     // Catch: java.lang.Throwable -> L14
        L14:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ad.a.a(android.net.Uri):void");
    }

    public void a(String str) {
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put(ai.a.da, str);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // com.applovin.impl.sdk.ad.g
    public boolean b() {
        return this.adObject.has("stream_url");
    }

    public void c() {
        synchronized (this.adObjectLock) {
            Object objRemove = this.adObject.remove("stream_url");
            if (objRemove instanceof String) {
                b((String) objRemove);
            }
        }
    }

    public boolean d() {
        String stringFromAdObject = getStringFromAdObject("backup_stream_url", null);
        try {
            synchronized (this.adObjectLock) {
                this.adObject.put("stream_url", stringFromAdObject);
            }
        } catch (Throwable unused) {
        }
        return b();
    }

    @Override // com.applovin.impl.sdk.ad.g
    public Uri e() {
        String strAA = aA();
        if (k.b(strAA)) {
            return Uri.parse(strAA);
        }
        String strF = f();
        if (k.b(strF)) {
            return Uri.parse(strF);
        }
        return null;
    }

    public String f() {
        return getStringFromAdObject("video", "");
    }

    @Override // com.applovin.impl.sdk.ad.g
    public Uri g() {
        String stringFromAdObject = getStringFromAdObject(NendAdNativeMediaView.RESULT_CLICK_URL, "");
        if (k.b(stringFromAdObject)) {
            return Uri.parse(stringFromAdObject);
        }
        return null;
    }

    @Override // com.applovin.impl.sdk.ad.g
    public Uri h() {
        String stringFromAdObject = getStringFromAdObject("video_click_url", "");
        return k.b(stringFromAdObject) ? Uri.parse(stringFromAdObject) : g();
    }

    @Override // com.applovin.impl.sdk.AppLovinAdBase
    public boolean hasVideoUrl() {
        return e() != null;
    }

    public float i() {
        return getFloatFromAdObject("mraid_close_delay_graphic", 0.0f);
    }

    public boolean j() {
        return getBooleanFromAdObject("close_button_graphic_hidden", false);
    }

    public boolean k() {
        if (this.adObject.has("close_button_expandable_hidden")) {
            return getBooleanFromAdObject("close_button_expandable_hidden", false);
        }
        return true;
    }

    public com.applovin.impl.adview.f.a l() {
        return a(getIntFromAdObject("expandable_style", com.applovin.impl.adview.f.a.Invisible.ordinal()));
    }
}
