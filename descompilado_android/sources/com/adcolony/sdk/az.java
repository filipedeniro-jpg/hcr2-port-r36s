package com.adcolony.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class az extends FrameLayout {
    public static final int ADCOLONY_IAP_ENGAGEMENT_END_CARD = 0;
    public static final int ADCOLONY_IAP_ENGAGEMENT_OVERLAY = 1;
    float a;
    int b;
    private c c;
    private c d;
    private e e;
    private String f;
    private String g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private ImageView l;
    private String m;
    private String n;
    private String o;
    private String p;
    private ExecutorService q;

    az(Context context, af afVar, e eVar) {
        super(context);
        this.m = "";
        this.n = "";
        this.o = "";
        this.p = "";
        this.a = 1.0f;
        this.b = 2;
        this.q = Executors.newSingleThreadExecutor();
        this.e = eVar;
        this.p = eVar.a;
        this.f = y.b(afVar.c(), "id");
        new aa.a().a("Retrieving container tied to ad session id: ").a(this.f).a(aa.b);
        this.c = a.a().m().b().get(this.f);
        setLayoutParams(new FrameLayout.LayoutParams(this.c.q(), this.c.p()));
        addView(this.c);
        d();
    }

    public boolean destroy() {
        af afVar;
        if (this.i) {
            new aa.a().a("Ignoring subsequent call to destroy()").a(aa.e);
            return false;
        }
        if (!a.b()) {
            return false;
        }
        l lVarA = a.a();
        this.i = true;
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "id", this.f);
        if (this.j) {
            afVar = new af("AdSession.on_native_ad_view_destroyed", this.c.c(), jSONObjectA);
        } else {
            afVar = new af("AdSession.on_ad_view_destroyed", this.c.c(), jSONObjectA);
        }
        if (lVarA.y()) {
            lVarA.c(afVar);
        } else {
            afVar.b();
        }
        return true;
    }

    boolean a() {
        d dVarM = a.a().m();
        dVarM.a(this.c);
        c cVar = this.d;
        if (cVar != null) {
            dVarM.a(cVar);
        }
        f fVarRemove = dVarM.g().remove(this.f);
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
        dVarM.f().remove(this.f);
        this.c = null;
        this.e = null;
        removeAllViews();
        this.q.shutdown();
        return true;
    }

    public boolean setVolume(float f) {
        double d = f;
        if (d < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || d > 1.0d) {
            return false;
        }
        if (this.i) {
            new aa.a().a("Ignoring call to setVolume as view has been destroyed.").a(aa.e);
            return false;
        }
        if (d > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.a = f;
        }
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "id", this.f);
        y.a(jSONObjectA, "volume", d);
        new af(this.j ? "AdSession.on_native_ad_view_set_volume" : "AdSession.on_ad_view_destroyed", this.c.c(), jSONObjectA).b();
        return true;
    }

    public boolean setMuted(boolean z) {
        if (this.i) {
            new aa.a().a("Ignoring call to setMuted() as view has been destroyed").a(aa.e);
            return false;
        }
        if (z) {
            return setVolume(0.0f);
        }
        return setVolume(this.a);
    }

    public String getZoneID() {
        if (this.i) {
            new aa.a().a("Ignoring call to getZoneID() as view has been destroyed").a(aa.e);
            return "";
        }
        return this.p;
    }

    public boolean pause() {
        if (this.i) {
            new aa.a().a("Ignoring call to pause() as view has been destroyed").a(aa.e);
            return false;
        }
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "id", this.f);
        new af("AdSession.on_manual_pause", this.c.c(), jSONObjectA).b();
        return true;
    }

    public boolean resume() {
        if (this.i) {
            new aa.a().a("Ignoring call to resume() as view has been destroyed").a(aa.e);
            return false;
        }
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "id", this.f);
        new af("AdSession.on_manual_resume", this.c.c(), jSONObjectA).b();
        return true;
    }

    boolean b() {
        Activity activityC;
        if (this.g.equals("") || (activityC = a.c()) == null) {
            return false;
        }
        this.l = new ImageView(activityC);
        this.l.setImageBitmap(BitmapFactory.decodeFile(this.g));
        return true;
    }

    private void d() {
        try {
            this.q.submit(new Runnable() { // from class: com.adcolony.sdk.az.1
                @Override // java.lang.Runnable
                public void run() {
                    JSONObject jSONObjectA = y.a();
                    y.a(jSONObjectA, "id", az.this.f);
                    while (!az.this.i) {
                        Rect rect = new Rect();
                        Rect rect2 = new Rect();
                        az.this.getLocalVisibleRect(rect);
                        az.this.getGlobalVisibleRect(rect2);
                        ViewParent parent = az.this.getParent();
                        if (parent != null) {
                            parent.getChildVisibleRect(az.this, rect2, null);
                        }
                        boolean z = rect.bottom - rect.top > az.this.c.p() / 2;
                        boolean z2 = (rect2.bottom - rect2.top < az.this.c.p() / 2 || rect2.bottom - rect2.top >= az.this.c.p()) && az.this.k;
                        boolean z3 = rect.bottom > az.this.c.p() || rect.bottom < 0 || rect.top < 0 || rect2.top <= 0;
                        if (z3 || z2 || !z || az.this.h) {
                            if ((!z || (z && z3)) && az.this.h) {
                                az.this.h = false;
                                new af(az.this.j ? "AdSession.on_native_ad_view_hidden" : "AdSession.on_ad_view_hidden", az.this.c.c(), jSONObjectA).b();
                                new aa.a().a("AdColonyAdView has been hidden.").a(aa.d);
                            }
                        } else {
                            az.this.k = true;
                            az.this.h = true;
                            new af(az.this.j ? "AdSession.on_native_ad_view_visible" : "AdSession.on_ad_view_visible", az.this.c.c(), jSONObjectA).b();
                        }
                        try {
                            Thread.sleep(50L);
                        } catch (InterruptedException unused) {
                        }
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
            JSONObject jSONObjectA = y.a();
            y.a(jSONObjectA, "id", this.f);
            new af("AdSession.on_error", this.c.c(), jSONObjectA).b();
        }
    }

    void setNative(boolean z) {
        this.j = z;
    }

    void setAdvertiserName(String str) {
        this.m = str;
    }

    void setTitle(String str) {
        this.n = str;
    }

    void setDescription(String str) {
        this.o = str;
    }

    void setImageFilepath(String str) {
        this.g = str;
    }

    void setExpandedContainer(c cVar) {
        this.d = cVar;
    }

    boolean c() {
        return this.i;
    }

    String getAdSessionId() {
        return this.f;
    }

    c getContainer() {
        return this.c;
    }

    c getExpandedContainer() {
        return this.d;
    }

    String getAdvertiserName() {
        return this.m;
    }

    ImageView getIcon() {
        return this.l;
    }

    String getTitle() {
        return this.n;
    }

    String getDescription() {
        return this.o;
    }

    e getListener() {
        return this.e;
    }
}
