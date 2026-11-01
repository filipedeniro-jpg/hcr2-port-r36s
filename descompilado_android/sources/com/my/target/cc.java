package com.my.target;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.drive.DriveFile;
import com.my.target.common.MyTargetActivity;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ClickHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class cc {

    @NonNull
    private static final WeakHashMap<ai, Boolean> jy = new WeakHashMap<>();

    @NonNull
    public static cc bg() {
        return new cc();
    }

    private cc() {
    }

    public void a(@NonNull ai aiVar, @NonNull Context context) {
        a(aiVar, aiVar.getTrackingLink(), context);
    }

    public void a(@NonNull ai aiVar, @Nullable String str, @NonNull Context context) {
        if (jy.containsKey(aiVar) || a.a(aiVar).r(context)) {
            return;
        }
        if (str != null) {
            a(str, aiVar, context);
        }
        cj.a(aiVar.getStatHolder().x(aq.a.dF), context);
    }

    private void a(@NonNull String str, @NonNull final ai aiVar, @NonNull final Context context) {
        if (aiVar.isDirectLink() || cl.S(str)) {
            b(str, aiVar, context);
        } else {
            jy.put(aiVar, true);
            cl.V(str).a(new cl.a() { // from class: com.my.target.cc.1
                @Override // com.my.target.cl.a
                public void L(@Nullable String str2) {
                    if (!TextUtils.isEmpty(str2)) {
                        cc.this.b(str2, aiVar, context);
                    }
                    cc.jy.remove(aiVar);
                }
            }).y(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(@NonNull String str, @NonNull ai aiVar, @NonNull Context context) {
        a.a(str, aiVar).r(context);
    }

    /* JADX INFO: compiled from: ClickHandler.java */
    private static abstract class a {

        @NonNull
        protected final ai jB;

        protected abstract boolean r(@NonNull Context context);

        @NonNull
        static a a(@NonNull ai aiVar) {
            return new b(aiVar);
        }

        @NonNull
        static a a(@NonNull String str, @NonNull ai aiVar) {
            if (cl.S(str)) {
                return new c(str, aiVar);
            }
            return new d(str, aiVar);
        }

        protected a(@NonNull ai aiVar) {
            this.jB = aiVar;
        }
    }

    /* JADX INFO: compiled from: ClickHandler.java */
    private static class b extends a {
        private b(@NonNull ai aiVar) {
            super(aiVar);
        }

        @Override // com.my.target.cc.a
        protected boolean r(@NonNull Context context) {
            String bundleId;
            Intent launchIntentForPackage;
            if (!"store".equals(this.jB.getNavigationType()) || (bundleId = this.jB.getBundleId()) == null || (launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(bundleId)) == null) {
                return false;
            }
            if (a(bundleId, this.jB.getDeeplink(), context)) {
                cj.a(this.jB.getStatHolder().x(aq.a.dG), context);
                return true;
            }
            if (!b(bundleId, this.jB.getUrlscheme(), context) && !a(launchIntentForPackage, context)) {
                return false;
            }
            cj.a(this.jB.getStatHolder().x(aq.a.dF), context);
            String trackingLink = this.jB.getTrackingLink();
            if (trackingLink != null && !cl.S(trackingLink)) {
                cl.V(trackingLink).y(context);
            }
            return true;
        }

        private boolean a(@NonNull String str, @Nullable String str2, @NonNull Context context) {
            if (str2 == null) {
                return false;
            }
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
                if (!(context instanceof Activity)) {
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                }
                intent.setPackage(str);
                context.startActivity(intent);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        private boolean b(@NonNull String str, @Nullable String str2, @NonNull Context context) {
            if (str2 == null) {
                return false;
            }
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
                if (!(context instanceof Activity)) {
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                }
                intent.setPackage(str);
                context.startActivity(intent);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        private boolean a(@NonNull Intent intent, @NonNull Context context) {
            try {
                if (!(context instanceof Activity)) {
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                }
                context.startActivity(intent);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: ClickHandler.java */
    private static class c extends d {
        private c(@NonNull String str, @NonNull ai aiVar) {
            super(str, aiVar);
        }

        @Override // com.my.target.cc.d, com.my.target.cc.a
        protected boolean r(@NonNull Context context) {
            if (cl.T(this.url)) {
                if (j(this.url, context)) {
                    return true;
                }
            } else if (k(this.url, context)) {
                return true;
            }
            return super.r(context);
        }

        private boolean j(@NonNull String str, @NonNull Context context) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                if (!(context instanceof Activity)) {
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                }
                context.startActivity(intent);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        private boolean k(@NonNull String str, @NonNull Context context) {
            try {
                if (this.jB.isUsePlayStoreAction()) {
                    Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.android.vending");
                    if (launchIntentForPackage == null) {
                        return true;
                    }
                    launchIntentForPackage.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.activities.LaunchUrlHandlerActivity"));
                    if (!(context instanceof Activity)) {
                        launchIntentForPackage.addFlags(DriveFile.MODE_READ_ONLY);
                    }
                    launchIntentForPackage.setData(Uri.parse(str));
                    context.startActivity(launchIntentForPackage);
                    return true;
                }
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                if (!(context instanceof Activity)) {
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                }
                context.startActivity(intent);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: ClickHandler.java */
    private static class d extends a {
        private static final String jC = "android.support.customtabs.extra.SESSION";

        @NonNull
        protected final String url;

        private d(@NonNull String str, @NonNull ai aiVar) {
            super(aiVar);
            this.url = str;
        }

        @Override // com.my.target.cc.a
        protected boolean r(@NonNull Context context) {
            if (this.jB.isOpenInBrowser()) {
                return n(this.url, context);
            }
            if (Build.VERSION.SDK_INT >= 18 && m(this.url, context)) {
                return true;
            }
            if ("store".equals(this.jB.getNavigationType()) || (Build.VERSION.SDK_INT >= 28 && !cl.U(this.url))) {
                return n(this.url, context);
            }
            return l(this.url, context);
        }

        private boolean l(@NonNull String str, @NonNull Context context) {
            e.M(str).s(context);
            return true;
        }

        @TargetApi(18)
        private boolean m(@NonNull String str, @NonNull Context context) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                Bundle bundle = new Bundle();
                bundle.putBinder("android.support.customtabs.extra.SESSION", null);
                if (!(context instanceof Activity)) {
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                }
                intent.setPackage("com.android.chrome");
                intent.putExtras(bundle);
                context.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException unused) {
                return false;
            }
        }

        private boolean n(@NonNull String str, @NonNull Context context) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                if (!(context instanceof Activity)) {
                    intent.addFlags(DriveFile.MODE_READ_ONLY);
                }
                context.startActivity(intent);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
    }

    /* JADX INFO: compiled from: ClickHandler.java */
    private static class e implements MyTargetActivity.ActivityEngine {

        @NonNull
        private final String jD;

        @Nullable
        private ca jE;

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

        @NonNull
        public static e M(@NonNull String str) {
            return new e(str);
        }

        private e(@NonNull String str) {
            this.jD = str;
        }

        public void s(@NonNull Context context) {
            MyTargetActivity.activityEngine = this;
            Intent intent = new Intent(context, (Class<?>) MyTargetActivity.class);
            if (!(context instanceof Activity)) {
                intent.addFlags(DriveFile.MODE_READ_ONLY);
            }
            context.startActivity(intent);
        }

        @Override // com.my.target.common.MyTargetActivity.ActivityEngine
        public void onActivityCreate(@NonNull final MyTargetActivity myTargetActivity, @NonNull Intent intent, @NonNull FrameLayout frameLayout) {
            myTargetActivity.setTheme(android.R.style.Theme.Light.NoTitleBar);
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = myTargetActivity.getWindow();
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(-12232092);
            }
            this.jE = new ca(myTargetActivity);
            frameLayout.addView(this.jE);
            this.jE.bb();
            this.jE.setUrl(this.jD);
            this.jE.setListener(new ca.b() { // from class: com.my.target.cc.e.1
                @Override // com.my.target.ca.b
                public void bf() {
                    myTargetActivity.finish();
                }
            });
        }

        @Override // com.my.target.common.MyTargetActivity.ActivityEngine
        public void onActivityDestroy() {
            ca caVar = this.jE;
            if (caVar != null) {
                caVar.destroy();
                this.jE = null;
            }
        }

        @Override // com.my.target.common.MyTargetActivity.ActivityEngine
        public boolean onActivityBackPressed() {
            ca caVar = this.jE;
            if (caVar == null || !caVar.canGoBack()) {
                return true;
            }
            this.jE.goBack();
            return false;
        }
    }
}
