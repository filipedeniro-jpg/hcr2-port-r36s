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
import com.my.target.ads.InterstitialAd;
import com.my.target.br;
import com.my.target.common.MyTargetActivity;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: InterstitialAdEngine.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class c implements br.a, MyTargetActivity.ActivityEngine {

    @NonNull
    final InterstitialAd a;

    @Nullable
    private WeakReference<MyTargetActivity> b;

    @Nullable
    private WeakReference<br> c;
    private boolean d;

    @Override // com.my.target.br.a
    public void i(boolean z) {
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public boolean onActivityBackPressed() {
        return true;
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public boolean onActivityOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public void onActivityPause() {
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public void onActivityResume() {
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public void onActivityStart() {
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public void onActivityStop() {
    }

    @Nullable
    public static c a(@NonNull InterstitialAd interstitialAd, @NonNull com.my.target.core.models.banners.e eVar, @NonNull com.my.target.core.models.sections.c cVar) {
        if (eVar instanceof com.my.target.core.models.banners.i) {
            return f.a(interstitialAd, (com.my.target.core.models.banners.i) eVar, cVar);
        }
        if (eVar instanceof com.my.target.core.models.banners.g) {
            return d.a(interstitialAd, (com.my.target.core.models.banners.g) eVar, cVar);
        }
        if (eVar instanceof com.my.target.core.models.banners.h) {
            return e.a(interstitialAd, (com.my.target.core.models.banners.h) eVar);
        }
        return null;
    }

    c(@NonNull InterstitialAd interstitialAd) {
        this.a = interstitialAd;
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
            com.my.target.g.c("InterstitialAdEngine.showDialog: dialog already showing");
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
    public void onActivityCreate(@NonNull MyTargetActivity myTargetActivity, @NonNull Intent intent, @NonNull FrameLayout frameLayout) {
        this.b = new WeakReference<>(myTargetActivity);
        myTargetActivity.setTheme(R.style.Theme.NoTitleBar);
        myTargetActivity.getWindow().setFlags(1024, 1024);
        InterstitialAd.InterstitialAdListener listener = this.a.getListener();
        if (listener != null) {
            listener.onDisplay(this.a);
        }
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public void onActivityDestroy() {
        this.d = false;
        this.b = null;
        InterstitialAd.InterstitialAdListener listener = this.a.getListener();
        if (listener != null) {
            listener.onDismiss(this.a);
        }
    }

    @Override // com.my.target.br.a
    public void a(@NonNull br brVar, @NonNull FrameLayout frameLayout) {
        this.c = new WeakReference<>(brVar);
        if (this.a.isHideStatusBarInDialog()) {
            brVar.aU();
        }
        InterstitialAd.InterstitialAdListener listener = this.a.getListener();
        if (listener != null) {
            listener.onDisplay(this.a);
        }
    }

    @Override // com.my.target.br.a
    public void aV() {
        this.d = false;
        this.c = null;
        InterstitialAd.InterstitialAdListener listener = this.a.getListener();
        if (listener != null) {
            listener.onDismiss(this.a);
        }
    }
}
