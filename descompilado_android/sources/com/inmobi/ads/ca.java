package com.inmobi.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.inmobi.rendering.RenderView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: ViewableAd.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ca {

    @NonNull
    AdContainer a;

    @Nullable
    a b;

    @Nullable
    protected WeakReference<View> c;

    @Nullable
    public View a() {
        return null;
    }

    @Nullable
    public abstract View a(View view, ViewGroup viewGroup, boolean z);

    public abstract void a(int i);

    public abstract void a(Context context, int i);

    public abstract void a(@Nullable View... viewArr);

    public abstract void d();

    /* JADX INFO: compiled from: ViewableAd.java */
    static abstract class a {
        private boolean a;

        public abstract View a(View view, ViewGroup viewGroup, boolean z, RenderView renderView);

        a() {
        }

        public boolean b() {
            return this.a;
        }

        public void a() {
            if (this.a) {
                return;
            }
            this.a = true;
        }
    }

    public ca() {
    }

    public ca(@NonNull AdContainer adContainer) {
        this.a = adContainer;
    }

    @Nullable
    public a f() {
        return this.b;
    }

    protected final void a(View view) {
        this.c = new WeakReference<>(view);
    }

    @NonNull
    c c() {
        return new c();
    }

    @Nullable
    public View b() {
        WeakReference<View> weakReference = this.c;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public void e() {
        WeakReference<View> weakReference = this.c;
        if (weakReference != null) {
            weakReference.clear();
        }
    }
}
