package com.inmobi.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: InMobiTrackedNativeV2DisplayAd.java */
/* JADX INFO: loaded from: classes2.dex */
class aa extends bz {
    private static final String d = "aa";

    @NonNull
    private final WeakReference<Context> e;

    @NonNull
    private final ca f;

    @NonNull
    private final ai g;

    @NonNull
    private final ah h;

    aa(@NonNull Context context, @NonNull ah ahVar, @NonNull ca caVar) {
        super(ahVar);
        this.e = new WeakReference<>(context);
        this.f = caVar;
        this.h = ahVar;
        this.g = new ai(1);
    }

    @Override // com.inmobi.ads.ca
    @Nullable
    public final View b() {
        return this.f.b();
    }

    @Override // com.inmobi.ads.ca
    public final ca.a f() {
        return this.f.f();
    }

    @Override // com.inmobi.ads.ca
    @NonNull
    final c c() {
        return this.f.c();
    }

    @Override // com.inmobi.ads.ca
    public final void a(@Nullable View... viewArr) {
        try {
            try {
                Context context = this.e.get();
                View viewB = this.f.b();
                c.k kVar = this.f.c().k;
                ah ahVar = (ah) this.a;
                if (context != null && viewB != null && !ahVar.l) {
                    this.g.a(context, viewB, ahVar, kVar);
                    this.g.a(context, viewB, this.h, this.h.A, kVar);
                }
            } catch (Exception e) {
                new StringBuilder("Exception in startTrackingForImpression with message : ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        } finally {
            this.f.a(viewArr);
        }
    }

    @Override // com.inmobi.ads.ca
    public final void a(int i) {
        this.f.a(i);
    }

    @Override // com.inmobi.ads.ca
    public final void a(Context context, int i) {
        try {
            if (i == 0) {
                ai.b(context);
            } else {
                if (i != 1) {
                    if (i == 2) {
                        this.g.a(context);
                    }
                }
                ai.c(context);
            }
        } catch (Exception e) {
            new StringBuilder("Exception in onActivityStateChanged with message : ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        } finally {
            this.f.a(context, i);
        }
    }

    @Override // com.inmobi.ads.ca
    public final void e() {
        this.g.a(this.h.d(), this.f.b(), this.h);
        super.e();
        this.e.clear();
        this.f.e();
    }

    @Override // com.inmobi.ads.ca
    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        View viewB = this.f.b();
        if (viewB != null) {
            this.g.a(this.h.d(), viewB, this.h);
        }
        return this.f.a(view, viewGroup, z);
    }

    @Override // com.inmobi.ads.ca
    public final void d() {
        try {
            try {
                ah ahVar = (ah) this.a;
                if (!ahVar.l) {
                    this.g.a(this.e.get(), ahVar);
                }
            } catch (Exception e) {
                new StringBuilder("Exception in stopTrackingForImpression with message : ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        } finally {
            this.f.d();
        }
    }
}
