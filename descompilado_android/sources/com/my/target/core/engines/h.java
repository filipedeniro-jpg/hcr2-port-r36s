package com.my.target.core.engines;

import android.R;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.drive.DriveFile;
import com.my.target.br;
import com.my.target.ck;
import com.my.target.common.MyTargetActivity;
import com.my.target.fc;
import com.my.target.nativeads.NativeAppwallAd;
import com.my.target.nativeads.factories.NativeAppwallViewsFactory;
import com.my.target.nativeads.views.AppwallAdView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: NativeAppwallAdEngine.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h implements br.a, MyTargetActivity.ActivityEngine {

    @NonNull
    private final NativeAppwallAd a;

    @Nullable
    private WeakReference<MyTargetActivity> b;

    @Nullable
    private WeakReference<br> c;
    private boolean d;

    @Override // com.my.target.br.a
    public final void i(boolean z) {
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public final boolean onActivityBackPressed() {
        return true;
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
    public static h a(@NonNull NativeAppwallAd nativeAppwallAd) {
        return new h(nativeAppwallAd);
    }

    private h(@NonNull NativeAppwallAd nativeAppwallAd) {
        this.a = nativeAppwallAd;
    }

    public final void s(Context context) {
        if (this.d) {
            com.my.target.g.a("Unable to open Appwall Ad twice, please dismiss currently showing ad first");
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

    public final void showDialog(Context context) {
        if (this.d) {
            com.my.target.g.a("Unable to open Appwall Ad twice, please dismiss currently showing ad first");
            return;
        }
        this.d = true;
        WeakReference<br> weakReference = this.c;
        br brVar = weakReference == null ? null : weakReference.get();
        if (brVar != null && brVar.isShowing()) {
            com.my.target.g.c("NativeAppwallAdEngine.showDialog: dialog already showing");
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

    public final void destroy() {
        dismiss();
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityCreate(@NonNull MyTargetActivity myTargetActivity, @NonNull Intent intent, @NonNull FrameLayout frameLayout) {
        this.b = new WeakReference<>(myTargetActivity);
        Window window = myTargetActivity.getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.addFlags(Integer.MIN_VALUE);
            myTargetActivity.setTheme(R.style.Theme.Material.Light.DarkActionBar);
            ActionBar actionBar = myTargetActivity.getActionBar();
            if (actionBar != null) {
                actionBar.setTitle(this.a.getTitle());
                actionBar.setIcon(R.color.transparent);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setBackgroundDrawable(new ColorDrawable(this.a.getTitleBackgroundColor()));
                a(actionBar, this.a.getTitleTextColor());
                actionBar.setElevation(ck.x(myTargetActivity).l(4));
            }
            window.setStatusBarColor(this.a.getTitleSupplementaryColor());
        } else if (Build.VERSION.SDK_INT >= 14) {
            myTargetActivity.setTheme(R.style.Theme.Holo.Light.DarkActionBar);
            ActionBar actionBar2 = myTargetActivity.getActionBar();
            if (actionBar2 != null) {
                actionBar2.setTitle(this.a.getTitle());
                actionBar2.setBackgroundDrawable(new ColorDrawable(this.a.getTitleBackgroundColor()));
                a(actionBar2, this.a.getTitleTextColor());
                actionBar2.setIcon(R.color.transparent);
                actionBar2.setDisplayShowTitleEnabled(true);
                actionBar2.setDisplayHomeAsUpEnabled(true);
            }
        }
        if (myTargetActivity.getActionBar() == null) {
            LinearLayout linearLayout = new LinearLayout(myTargetActivity);
            linearLayout.setOrientation(1);
            linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            frameLayout.addView(linearLayout);
            a(linearLayout);
            b(linearLayout);
        } else {
            b(frameLayout);
        }
        NativeAppwallAd.AppwallAdListener listener = this.a.getListener();
        if (listener != null) {
            listener.onDisplay(this.a);
        }
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityDestroy() {
        this.d = false;
        this.b = null;
        NativeAppwallAd.AppwallAdListener listener = this.a.getListener();
        if (listener != null) {
            listener.onDismiss(this.a);
        }
    }

    @Override // com.my.target.common.MyTargetActivity.ActivityEngine
    public final boolean onActivityOptionsItemSelected(MenuItem menuItem) {
        WeakReference<MyTargetActivity> weakReference;
        MyTargetActivity myTargetActivity;
        if (menuItem.getItemId() != 16908332 || (weakReference = this.b) == null || (myTargetActivity = weakReference.get()) == null) {
            return false;
        }
        myTargetActivity.finish();
        return true;
    }

    @Override // com.my.target.br.a
    public final void a(@NonNull br brVar, @NonNull FrameLayout frameLayout) {
        this.c = new WeakReference<>(brVar);
        if (this.a.isHideStatusBarInDialog()) {
            brVar.aU();
        }
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(linearLayout);
        a(linearLayout);
        b(linearLayout);
        NativeAppwallAd.AppwallAdListener listener = this.a.getListener();
        if (listener != null) {
            listener.onDisplay(this.a);
        }
    }

    @Override // com.my.target.br.a
    public final void aV() {
        this.d = false;
        this.c = null;
        NativeAppwallAd.AppwallAdListener listener = this.a.getListener();
        if (listener != null) {
            listener.onDismiss(this.a);
        }
    }

    private static void a(@NonNull ActionBar actionBar, int i) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(actionBar.getTitle());
        spannableStringBuilder.setSpan(new ForegroundColorSpan(i), 0, actionBar.getTitle().length(), 18);
        actionBar.setTitle(spannableStringBuilder);
    }

    private void a(@NonNull ViewGroup viewGroup) {
        fc fcVar = new fc(viewGroup.getContext());
        fcVar.setTitle(this.a.getTitle());
        fcVar.setStripeColor(this.a.getTitleSupplementaryColor());
        fcVar.setMainColor(this.a.getTitleBackgroundColor());
        fcVar.setTitleColor(this.a.getTitleTextColor());
        fcVar.setLayoutParams(new ViewGroup.LayoutParams(-1, ck.x(viewGroup.getContext()).l(52)));
        viewGroup.addView(fcVar);
        fcVar.setOnCloseClickListener(new fc.a() { // from class: com.my.target.core.engines.h.1
            @Override // com.my.target.fc.a
            public final void bf() {
                h.this.dismiss();
            }
        });
    }

    private void b(@NonNull ViewGroup viewGroup) {
        AppwallAdView appwallView = NativeAppwallViewsFactory.getAppwallView(this.a, viewGroup.getContext());
        this.a.registerAppwallAdView(appwallView);
        appwallView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        viewGroup.addView(appwallView);
    }
}
