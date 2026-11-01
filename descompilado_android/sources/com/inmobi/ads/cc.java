package com.inmobi.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.rendering.RenderView;

/* JADX INFO: compiled from: ViewableNativeV2DisplayAd.java */
/* JADX INFO: loaded from: classes2.dex */
final class cc extends ca {

    @NonNull
    private final ah d;
    private boolean e;

    @Nullable
    private RenderView f;

    @Override // com.inmobi.ads.ca
    public final void a(int i) {
    }

    @Override // com.inmobi.ads.ca
    public final void a(Context context, int i) {
    }

    @Override // com.inmobi.ads.ca
    public final void a(@Nullable View... viewArr) {
    }

    @Override // com.inmobi.ads.ca
    public final void d() {
    }

    cc(@NonNull ah ahVar, @Nullable RenderView renderView) {
        super(ahVar);
        this.e = false;
        this.d = ahVar;
        this.f = renderView;
    }

    @Override // com.inmobi.ads.ca
    @NonNull
    final c c() {
        return this.d.c;
    }

    @Override // com.inmobi.ads.ca
    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        Context contextJ;
        if (this.e || (contextJ = this.d.j()) == null) {
            return null;
        }
        c cVar = this.d.c;
        ah ahVar = this.d;
        this.b = new at(contextJ, cVar, ahVar, ahVar.h());
        Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "Ad markup loaded into the container will be inflated into a View.");
        View viewA = this.b.a(view, viewGroup, z, this.f);
        a(viewA);
        this.d.t();
        return viewA;
    }

    @Override // com.inmobi.ads.ca
    public final void e() {
        if (this.e) {
            return;
        }
        this.e = true;
        if (this.b != null) {
            this.b.a();
        }
        RenderView renderView = this.f;
        if (renderView != null) {
            renderView.destroy();
            this.f = null;
        }
        super.e();
    }
}
