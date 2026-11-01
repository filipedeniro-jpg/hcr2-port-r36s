package com.my.target.core.engines;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.drive.DriveFile;
import com.my.target.ads.InterstitialSliderAd;
import com.my.target.aq;
import com.my.target.br;
import com.my.target.cc;
import com.my.target.cj;
import com.my.target.common.MyTargetActivity;
import com.my.target.common.models.ImageData;
import com.my.target.core.presenters.l;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: InterstitialSliderAdEngine.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g implements br.a, MyTargetActivity.ActivityEngine {

    @Nullable
    private WeakReference<MyTargetActivity> b;

    @Nullable
    private WeakReference<br> c;
    private boolean d;

    @NonNull
    private final InterstitialSliderAd m;

    @NonNull
    private final com.my.target.core.models.sections.d n;

    @NonNull
    private final ArrayList<Object> o = new ArrayList<>();

    @Nullable
    private WeakReference<l> p;

    @Override // com.my.target.br.a
    public final void i(boolean z) {
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public final boolean onActivityBackPressed() {
        return true;
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public final boolean onActivityOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityPause() {
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityResume() {
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityStart() {
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityStop() {
    }

    @NonNull
    public static g a(@NonNull InterstitialSliderAd interstitialSliderAd, @NonNull com.my.target.core.models.sections.d dVar) {
        return new g(interstitialSliderAd, dVar);
    }

    private g(@NonNull InterstitialSliderAd interstitialSliderAd, @NonNull com.my.target.core.models.sections.d dVar) {
        this.m = interstitialSliderAd;
        this.n = dVar;
    }

    public final void s(@NonNull Context context) {
        if (this.d) {
            com.my.target.g.a("Unable to open Interstitial Ad twice, please dismiss currently showing ad first");
            return;
        }
        this.d = true;
        MyTargetActivity.activityEngine = this;
        Intent intent = new Intent(context, (Class<?>) MyTargetActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(DriveFile.MODE_READ_ONLY);
        }
        context.startActivity(intent);
    }

    public final void showDialog(@NonNull Context context) {
        if (this.d) {
            com.my.target.g.a("Unable to open Interstitial Ad twice, please dismiss currently showing ad first");
            return;
        }
        this.d = true;
        WeakReference<br> weakReference = this.c;
        br brVar = weakReference == null ? null : weakReference.get();
        if (brVar != null && brVar.isShowing()) {
            com.my.target.g.c("InterstitialSliderAdEngine.showDialog: dialog already showing");
        } else {
            br.a(this, context).show();
        }
    }

    public final void dismiss() {
        this.d = false;
        WeakReference<MyTargetActivity> weakReference = this.b;
        MyTargetActivity myTargetActivity = weakReference == null ? null : weakReference.get();
        if (myTargetActivity != null) {
            myTargetActivity.finish();
            return;
        }
        WeakReference<br> weakReference2 = this.c;
        br brVar = weakReference2 != null ? weakReference2.get() : null;
        if (brVar == null || !brVar.isShowing()) {
            return;
        }
        brVar.dismiss();
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityCreate(@NonNull MyTargetActivity myTargetActivity, @NonNull Intent intent, @NonNull FrameLayout frameLayout) {
        this.b = new WeakReference<>(myTargetActivity);
        myTargetActivity.setTheme(R.style.Theme.NoTitleBar);
        myTargetActivity.getWindow().setFlags(1024, 1024);
        a(frameLayout);
        InterstitialSliderAd.InterstitialSliderAdListener listener = this.m.getListener();
        if (listener != null) {
            listener.onDisplay(this.m);
        }
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityDestroy() {
        this.d = false;
        k();
        this.p = null;
        this.b = null;
        InterstitialSliderAd.InterstitialSliderAdListener listener = this.m.getListener();
        if (listener != null) {
            listener.onDismiss(this.m);
        }
    }

    @Override // com.my.target.br.a
    public final void a(@NonNull br brVar, @NonNull FrameLayout frameLayout) {
        this.c = new WeakReference<>(brVar);
        if (this.m.isHideStatusBarInDialog()) {
            brVar.aU();
        }
        a(frameLayout);
        InterstitialSliderAd.InterstitialSliderAdListener listener = this.m.getListener();
        if (listener != null) {
            listener.onDisplay(this.m);
        }
    }

    @Override // com.my.target.br.a
    public final void aV() {
        this.d = false;
        k();
        this.p = null;
        this.c = null;
        InterstitialSliderAd.InterstitialSliderAdListener listener = this.m.getListener();
        if (listener != null) {
            listener.onDismiss(this.m);
        }
    }

    private void k() {
        for (com.my.target.core.models.banners.h hVar : this.n.R()) {
            Iterator<ImageData> it = hVar.getLandscapeImages().iterator();
            while (it.hasNext()) {
                it.next().setBitmap(null);
            }
            Iterator<ImageData> it2 = hVar.getPortraitImages().iterator();
            while (it2.hasNext()) {
                it2.next().setBitmap(null);
            }
        }
    }

    private void a(@NonNull FrameLayout frameLayout) {
        l lVarG = l.g(frameLayout.getContext());
        this.p = new WeakReference<>(lVarG);
        lVarG.a(new a(this, (byte) 0));
        lVarG.a(this.n);
        frameLayout.addView(lVarG.getView(), new FrameLayout.LayoutParams(-1, -1));
    }

    /* JADX INFO: compiled from: InterstitialSliderAdEngine.java */
    private class a implements l.a {
        private a() {
        }

        /* synthetic */ a(g gVar, byte b) {
            this();
        }

        @Override // com.my.target.core.presenters.l.a
        public final void a(@NonNull com.my.target.core.models.banners.h hVar) {
            l lVar = g.this.p != null ? (l) g.this.p.get() : null;
            if (lVar == null) {
                return;
            }
            cc.bg().a(hVar, lVar.getView().getContext());
            InterstitialSliderAd.InterstitialSliderAdListener listener = g.this.m.getListener();
            if (listener != null) {
                listener.onClick(g.this.m);
            }
        }

        @Override // com.my.target.core.presenters.l.a
        public final void bf() {
            g.this.dismiss();
        }

        @Override // com.my.target.core.presenters.l.a
        public final void b(@NonNull com.my.target.core.models.banners.h hVar) {
            l lVar = g.this.p != null ? (l) g.this.p.get() : null;
            if (lVar == null) {
                return;
            }
            Context context = lVar.getView().getContext();
            if (g.this.o.contains(hVar)) {
                return;
            }
            g.this.o.add(hVar);
            cj.a(hVar.getStatHolder().x(aq.a.du), context);
        }
    }
}
