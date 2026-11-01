package com.inmobi.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.inmobi.rendering.RenderView;

/* JADX INFO: compiled from: ViewableHtmlAd.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cb extends ca {

    @NonNull
    private final RenderView d;

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

    public cb(@NonNull RenderView renderView) {
        super(renderView);
        this.d = renderView;
    }

    @Override // com.inmobi.ads.ca
    public final View a() {
        a(this.d);
        return this.d;
    }

    @Override // com.inmobi.ads.ca
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        return a();
    }

    @Override // com.inmobi.ads.ca
    @NonNull
    final c c() {
        return this.d.getAdConfig();
    }
}
