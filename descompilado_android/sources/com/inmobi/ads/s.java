package com.inmobi.ads;

import android.app.Activity;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: DecorViewVisibilityTracker.java */
/* JADX INFO: loaded from: classes2.dex */
final class s extends ce {
    private static final String d = "s";

    @NonNull
    private ViewTreeObserver.OnPreDrawListener e;

    @NonNull
    private final WeakReference<View> f;

    @Override // com.inmobi.ads.ce
    protected final int a() {
        return 100;
    }

    @Override // com.inmobi.ads.ce
    protected final void b() {
    }

    s(@NonNull ce.a aVar, @NonNull Activity activity) {
        super(aVar);
        View decorView = activity.getWindow().getDecorView();
        this.f = new WeakReference<>(decorView);
        ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.e = new ViewTreeObserver.OnPreDrawListener() { // from class: com.inmobi.ads.s.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public final boolean onPreDraw() {
                    s.this.g();
                    return true;
                }
            };
            viewTreeObserver.addOnPreDrawListener(this.e);
        }
    }

    private void h() {
        View view = this.f.get();
        if (view != null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.e);
            }
        }
    }

    @Override // com.inmobi.ads.ce
    protected final void e() {
        h();
        super.e();
    }

    @Override // com.inmobi.ads.ce
    public final void c() {
        if (this.a) {
            return;
        }
        h();
        super.c();
    }

    @Override // com.inmobi.ads.ce
    public final void d() {
        if (this.a) {
            View view = this.f.get();
            if (view != null) {
                ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.addOnPreDrawListener(this.e);
                }
            }
            super.d();
        }
    }
}
