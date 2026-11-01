package com.inmobi.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: InMobiTrackedNativeV2VideoAd.java */
/* JADX INFO: loaded from: classes2.dex */
class ab extends bz {
    private static final String d = "ab";

    @NonNull
    private final WeakReference<Context> e;

    @NonNull
    private final ca f;

    @NonNull
    private final ai g;

    @NonNull
    private final ah h;

    ab(@NonNull bd bdVar, @NonNull ca caVar) {
        super(bdVar);
        this.e = new WeakReference<>(bdVar.j());
        this.f = caVar;
        this.h = bdVar;
        this.g = new ai(0);
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
    public final void d() {
        try {
            try {
                Context context = this.e.get();
                bd bdVar = (bd) this.a;
                if (!bdVar.l && context != null) {
                    this.g.a(context, bdVar);
                }
            } catch (Exception e) {
                new StringBuilder("Exception in stopTrackingForImpression with message : ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        } finally {
            this.f.d();
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
        this.g.a(this.e.get(), this.f.b(), this.h);
        super.e();
        this.e.clear();
        this.f.e();
    }

    @Override // com.inmobi.ads.ca
    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        View viewB = this.f.b();
        if (viewB != null) {
            this.g.a(this.e.get(), viewB, this.h);
        }
        return this.f.a(view, viewGroup, z);
    }

    @Override // com.inmobi.ads.ca
    public final void a(@Nullable View... viewArr) {
        try {
            try {
                bd bdVar = (bd) this.a;
                NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) bdVar.getVideoContainerView();
                Context context = this.e.get();
                c.k kVar = this.f.c().k;
                if (context != null && nativeVideoWrapper != null && !bdVar.l) {
                    NativeVideoView videoView = nativeVideoWrapper.getVideoView();
                    this.g.a(context, videoView, bdVar, kVar);
                    View viewB = this.f.b();
                    if (videoView.getTag() != null && viewB != null) {
                        be beVar = (be) videoView.getTag();
                        if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE == bdVar.b.a && !((Boolean) beVar.v.get("isFullScreen")).booleanValue()) {
                            this.g.a(context, viewB, this.h, ((bd) this.h).C, kVar);
                        }
                    }
                }
            } catch (Exception e) {
                new StringBuilder("Exception in startTrackingForImpression with message : ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        } finally {
            this.f.a(viewArr);
        }
    }
}
