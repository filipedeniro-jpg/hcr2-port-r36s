package com.my.target.core.presenters;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.my.target.aa;
import com.my.target.ab;
import com.my.target.ac;
import com.my.target.ad;
import com.my.target.bv;
import com.my.target.bx;
import com.my.target.ck;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: InterstitialMraidPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i implements aa.b, m {

    @Nullable
    private m.a G;

    @NonNull
    private final ad X;

    @NonNull
    private String ab;

    @Nullable
    private bx ad;

    @NonNull
    private final aa ae;

    @NonNull
    private final WeakReference<Activity> af;

    @Nullable
    private Integer ag;
    private boolean ah;
    private boolean ai;
    private ac aj;

    @NonNull
    private final bv ak;
    private boolean al;

    @NonNull
    private final Context context;

    @Nullable
    private com.my.target.core.models.banners.g e;

    private static boolean a(int i, int i2) {
        return (i & i2) != 0;
    }

    @NonNull
    public static i f(@NonNull Context context) {
        return new i(context);
    }

    private i(@NonNull aa aaVar, @NonNull Context context) {
        this.ai = true;
        this.aj = ac.s();
        this.ae = aaVar;
        this.context = context.getApplicationContext();
        if (context instanceof Activity) {
            this.af = new WeakReference<>((Activity) context);
        } else {
            this.af = new WeakReference<>(null);
        }
        this.ab = aa.f.bp;
        this.X = ad.d(context);
        this.ak = new bv(context);
        this.ak.setOnCloseListener(new bv.a() { // from class: com.my.target.core.presenters.i.1
            @Override // com.my.target.bv.a
            public final void onClose() {
                i.this.D();
            }
        });
        aaVar.a(this);
    }

    private i(@NonNull Context context) {
        this(aa.e(aa.e.bo), context);
    }

    @Override // com.my.target.core.presenters.m
    public final void a(@NonNull com.my.target.core.models.banners.g gVar) {
        this.e = gVar;
        String source = gVar.getSource();
        if (source != null) {
            this.ad = new bx(this.context);
            this.ae.a(this.ad);
            this.ak.addView(this.ad, new FrameLayout.LayoutParams(-1, -1));
            this.ae.f(source);
        }
    }

    @Override // com.my.target.core.presenters.m
    public final void a(@Nullable m.a aVar) {
        this.G = aVar;
    }

    @Override // com.my.target.core.presenters.j
    public final void stop() {
        this.al = true;
        bx bxVar = this.ad;
        if (bxVar != null) {
            bxVar.j(false);
        }
    }

    @Override // com.my.target.core.presenters.j
    public final void pause() {
        this.al = true;
        bx bxVar = this.ad;
        if (bxVar != null) {
            bxVar.j(false);
        }
    }

    @Override // com.my.target.core.presenters.j
    public final void resume() {
        this.al = false;
        bx bxVar = this.ad;
        if (bxVar != null) {
            bxVar.onResume();
        }
    }

    @Override // com.my.target.core.presenters.j
    public final void destroy() {
        if (!this.al) {
            this.al = true;
            bx bxVar = this.ad;
            if (bxVar != null) {
                bxVar.j(true);
            }
        }
        ViewParent parent = this.ak.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.ak);
        }
        this.ae.detach();
        bx bxVar2 = this.ad;
        if (bxVar2 != null) {
            bxVar2.destroy();
            this.ad = null;
        }
        this.ak.removeAllViews();
    }

    @Override // com.my.target.core.presenters.j
    @NonNull
    public final View v() {
        return this.ak;
    }

    @Override // com.my.target.aa.b
    public final boolean a(boolean z, ac acVar) {
        if (!a(acVar)) {
            this.ae.a(ab.bD, "Unable to force orientation to ".concat(String.valueOf(acVar)));
            return false;
        }
        this.ai = z;
        this.aj = acVar;
        if ("none".equals(this.aj.toString())) {
            if (this.ai) {
                C();
                return true;
            }
            Activity activity = this.af.get();
            if (activity == null) {
                this.ae.a(ab.bD, "Unable to set MRAID expand orientation to 'none'; expected passed in Activity Context.");
                return false;
            }
            return c(ck.a(activity));
        }
        return c(this.aj.t());
    }

    @Override // com.my.target.aa.b
    public final void c(@NonNull aa aaVar) {
        com.my.target.core.models.banners.g gVar;
        bx bxVar;
        this.ab = aa.f.bq;
        J();
        ArrayList<String> arrayList = new ArrayList<>();
        Activity activity = this.af.get();
        if ((activity == null || (bxVar = this.ad) == null) ? false : ck.a(activity, bxVar)) {
            arrayList.add("'inlineVideo'");
        }
        arrayList.add("'vpaid'");
        aaVar.a(arrayList);
        aaVar.g(aa.e.bo);
        aaVar.a(aaVar.isVisible());
        d(aa.f.bq);
        aaVar.m();
        aaVar.a(this.X);
        m.a aVar = this.G;
        if (aVar == null || (gVar = this.e) == null) {
            return;
        }
        aVar.a(gVar, this.context);
    }

    @Override // com.my.target.aa.b
    public final void onVisibilityChanged(boolean z) {
        this.ae.a(z);
    }

    @Override // com.my.target.aa.b
    public final boolean a(@NonNull String str, @NonNull JsResult jsResult) {
        com.my.target.g.a("JS Alert: ".concat(String.valueOf(str)));
        jsResult.confirm();
        return true;
    }

    @Override // com.my.target.aa.b
    public final boolean a(@NonNull ConsoleMessage consoleMessage, @NonNull aa aaVar) {
        com.my.target.g.a("Console message: " + consoleMessage.message());
        return true;
    }

    @Override // com.my.target.aa.b
    public final void onClose() {
        D();
    }

    @Override // com.my.target.aa.b
    public final void b(boolean z) {
        if (z == (!this.ak.aY())) {
            return;
        }
        this.ak.setCloseVisible(!z);
    }

    @Override // com.my.target.aa.b
    public final void b(@NonNull Uri uri) {
        m.a aVar = this.G;
        if (aVar != null) {
            aVar.a(this.e, uri.toString(), this.ak.getContext());
        }
    }

    @Override // com.my.target.aa.b
    public final void p() {
        J();
    }

    @Override // com.my.target.aa.b
    public final void q() {
        this.ah = true;
    }

    @Override // com.my.target.aa.b
    public final boolean l(@NonNull String str) {
        if (!this.ah) {
            this.ae.a(ab.bI, "Calling VPAID command before VPAID init");
            return false;
        }
        if ((this.G != null) & (this.e != null)) {
            this.G.b(this.e, str, this.context);
        }
        return true;
    }

    @Override // com.my.target.aa.b
    public final boolean a(float f, float f2) {
        m.a aVar;
        com.my.target.core.models.banners.g gVar;
        if (!this.ah) {
            this.ae.a(ab.bJ, "Calling VPAID command before VPAID init");
            return false;
        }
        if (f < 0.0f || f2 < 0.0f || (aVar = this.G) == null || (gVar = this.e) == null) {
            return true;
        }
        aVar.a(gVar, f, f2, this.context);
        return true;
    }

    @Override // com.my.target.aa.b
    public final boolean c(@Nullable Uri uri) {
        com.my.target.g.a("Expand method not used with interstitials");
        return false;
    }

    @Override // com.my.target.aa.b
    public final boolean a(int i, int i2, int i3, int i4, boolean z, int i5) {
        com.my.target.g.a("setResizeProperties method not used with interstitials");
        return false;
    }

    @Override // com.my.target.aa.b
    public final boolean r() {
        com.my.target.g.a("resize method not used with interstitials");
        return false;
    }

    @VisibleForTesting
    private boolean c(int i) {
        Activity activity = this.af.get();
        if (activity == null || !a(this.aj)) {
            this.ae.a(ab.bD, "Attempted to lock orientation to unsupported value: " + this.aj.toString());
            return false;
        }
        if (this.ag == null) {
            this.ag = Integer.valueOf(activity.getRequestedOrientation());
        }
        activity.setRequestedOrientation(i);
        return true;
    }

    @VisibleForTesting
    private void C() {
        Integer num;
        Activity activity = this.af.get();
        if (activity != null && (num = this.ag) != null) {
            activity.setRequestedOrientation(num.intValue());
        }
        this.ag = null;
    }

    @VisibleForTesting
    private boolean a(ac acVar) {
        if ("none".equals(acVar.toString())) {
            return true;
        }
        Activity activity = this.af.get();
        if (activity == null) {
            return false;
        }
        try {
            ActivityInfo activityInfo = activity.getPackageManager().getActivityInfo(new ComponentName(activity, activity.getClass()), 0);
            int i = activityInfo.screenOrientation;
            if (i != -1) {
                return i == acVar.t();
            }
            return a(activityInfo.configChanges, 128) && a(activityInfo.configChanges, 1024);
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    @VisibleForTesting
    final void D() {
        if (this.ad == null || aa.f.bp.equals(this.ab) || aa.f.br.equals(this.ab)) {
            return;
        }
        C();
        if (aa.f.bq.equals(this.ab)) {
            this.ak.setVisibility(4);
            d(aa.f.br);
        }
    }

    private void J() {
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        this.X.a(displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.X.a(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.X.b(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        this.X.c(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    private void d(@NonNull String str) {
        com.my.target.g.a("MRAID state set to ".concat(String.valueOf(str)));
        this.ab = str;
        this.ae.h(str);
        if (aa.f.br.equals(str)) {
            com.my.target.g.a("InterstitialMraidPresenter: Mraid on close");
            m.a aVar = this.G;
            if (aVar != null) {
                aVar.bf();
            }
        }
    }
}
