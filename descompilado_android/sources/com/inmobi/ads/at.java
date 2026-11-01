package com.inmobi.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.inmobi.rendering.RenderView;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* JADX INFO: compiled from: NativeInflater.java */
/* JADX INFO: loaded from: classes2.dex */
public class at extends ca.a implements au.b {
    private static final String b = "at";

    @NonNull
    final au a;

    @NonNull
    private final ah c;
    private final au.c d = new au.c() { // from class: com.inmobi.ads.at.1
        @Override // com.inmobi.ads.au.c
        public final void a(int i, ak akVar) {
            if (at.this.b()) {
                return;
            }
            at.this.c.a(i, akVar);
        }
    };
    private final au.a e = new au.a() { // from class: com.inmobi.ads.at.2
        @Override // com.inmobi.ads.au.a
        public final void a(View view, ak akVar) {
            if (at.this.b()) {
                return;
            }
            at.this.c.a(view, akVar);
            at.this.c.a(akVar, false);
        }
    };
    private final bg f = new bg() { // from class: com.inmobi.ads.at.3
        @Override // com.inmobi.ads.bg
        public final void a(NativeVideoView nativeVideoView) {
            if (at.this.b() || !(at.this.c instanceof bd)) {
                return;
            }
            bd bdVar = (bd) at.this.c;
            nativeVideoView.setIsLockScreen(bdVar.u);
            NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) nativeVideoView.getParent();
            bdVar.B = new WeakReference<>(nativeVideoWrapper);
            NativeVideoController mediaController = nativeVideoWrapper.getVideoView().getMediaController();
            if (mediaController != null) {
                mediaController.setVideoAd(bdVar);
            }
        }

        @Override // com.inmobi.ads.bg
        public final void a(be beVar, int i) {
            if (at.this.b() || !(at.this.c instanceof bd)) {
                return;
            }
            ((bd) at.this.c).a(beVar, i);
        }

        @Override // com.inmobi.ads.bg
        public final void a() {
            if (at.this.b() || !(at.this.c instanceof bd)) {
                return;
            }
            ((bd) at.this.c).w();
        }

        @Override // com.inmobi.ads.bg
        public final void a(be beVar) {
            if (at.this.b() || !(at.this.c instanceof bd)) {
                return;
            }
            bd bdVar = (bd) at.this.c;
            if (bdVar.l) {
                return;
            }
            if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE == bdVar.b.a) {
                if (((Integer) beVar.v.get("currentMediaVolume")).intValue() > 0 && ((Integer) beVar.v.get("lastMediaVolume")).intValue() == 0) {
                    bdVar.d(beVar);
                }
                if (((Integer) beVar.v.get("currentMediaVolume")).intValue() == 0 && ((Integer) beVar.v.get("lastMediaVolume")).intValue() > 0) {
                    bdVar.c(beVar);
                }
            }
            if (((Boolean) beVar.v.get("didStartPlaying")).booleanValue()) {
                return;
            }
            beVar.v.put("didStartPlaying", true);
            bdVar.getViewableAd().a(6);
            try {
                HashMap map = new HashMap();
                map.put("isCached", "1");
                bdVar.a("VideoPlayed", map);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                sb.append(e.getMessage());
                sb.append(")");
            }
        }

        @Override // com.inmobi.ads.bg
        public final void b(be beVar) {
            if (at.this.b() || !(at.this.c instanceof bd)) {
                return;
            }
            ((bd) at.this.c).a(beVar);
        }

        @Override // com.inmobi.ads.bg
        public final void c(be beVar) {
            if (at.this.b() || !(at.this.c instanceof bd)) {
                return;
            }
            ((bd) at.this.c).b(beVar);
        }

        @Override // com.inmobi.ads.bg
        public final void d(be beVar) {
            if (at.this.b() || !(at.this.c instanceof bd)) {
                return;
            }
            bd bdVar = (bd) at.this.c;
            if (!((Boolean) beVar.v.get("didSignalVideoCompleted")).booleanValue()) {
                bdVar.o();
                ah.c cVarE = bdVar.e();
                if (cVarE != null) {
                    cVarE.h();
                }
            }
            if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN == bdVar.b.a) {
                bdVar.c((ak) beVar);
            }
        }

        @Override // com.inmobi.ads.bg
        public final void b(be beVar, int i) {
            if (at.this.b() || !(at.this.c instanceof bd)) {
                return;
            }
            ((bd) at.this.c).b(beVar, i);
        }

        @Override // com.inmobi.ads.bg
        public final void e(be beVar) {
            if (at.this.b() || !(at.this.c instanceof bd)) {
                return;
            }
            ((bd) at.this.c).e(beVar);
        }
    };

    @Override // com.inmobi.ads.ca.a
    public final /* bridge */ /* synthetic */ boolean b() {
        return super.b();
    }

    public at(@NonNull Context context, @NonNull c cVar, @NonNull ah ahVar, @NonNull ao aoVar) {
        this.c = ahVar;
        this.a = new au(context, cVar, this.c, aoVar, this.d, this.e, this);
        NativeViewFactory nativeViewFactory = this.a.d;
        NativeViewFactory.a(ahVar.s);
        this.a.a = this.f;
    }

    @Override // com.inmobi.ads.ca.a
    public final View a(View view, ViewGroup viewGroup, boolean z, RenderView renderView) {
        aw awVarA;
        if (view != null) {
            View viewFindViewWithTag = view.findViewWithTag("InMobiAdView");
            if (viewFindViewWithTag != null) {
                aw awVar = (aw) viewFindViewWithTag;
                if (z) {
                    awVarA = this.a.b(awVar, viewGroup, renderView);
                } else {
                    awVarA = this.a.a(awVar, viewGroup, renderView);
                }
            } else if (z) {
                awVarA = this.a.b(null, viewGroup, renderView);
            } else {
                awVarA = this.a.a(null, viewGroup, renderView);
            }
        } else if (z) {
            awVarA = this.a.b(null, viewGroup, renderView);
        } else {
            awVarA = this.a.a(null, viewGroup, renderView);
        }
        awVarA.a = new WeakReference<>(this.c);
        awVarA.setTag("InMobiAdView");
        return awVarA;
    }

    @Override // com.inmobi.ads.ca.a
    public final void a() {
        this.a.b();
        super.a();
    }

    @Override // com.inmobi.ads.au.b
    public final void a(bb bbVar) {
        if (bbVar.k == 1) {
            this.c.b();
        }
    }
}
