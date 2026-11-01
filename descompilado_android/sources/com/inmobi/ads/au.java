package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import com.facebook.share.internal.ShareConstants;
import com.inmobi.rendering.RenderView;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: NativeLayoutInflater.java */
/* JADX INFO: loaded from: classes2.dex */
final class au implements NativeScrollableContainer.a {
    private static final String e = "au";
    private static Handler n = new Handler(Looper.getMainLooper());

    @Nullable
    bg a;
    NativeViewFactory d;

    @NonNull
    private final WeakReference<Context> f;

    @NonNull
    private final ao g;

    @NonNull
    private final ah h;

    @NonNull
    private final com.inmobi.ads.c i;

    @NonNull
    private c j;

    @NonNull
    private a k;

    @Nullable
    private b l;
    private ax m;
    private RenderView p;
    int b = 0;
    private boolean o = false;
    final n c = new n();

    /* JADX INFO: compiled from: NativeLayoutInflater.java */
    interface a {
        void a(View view, ak akVar);
    }

    /* JADX INFO: compiled from: NativeLayoutInflater.java */
    interface b {
        void a(bb bbVar);
    }

    /* JADX INFO: compiled from: NativeLayoutInflater.java */
    interface c {
        void a(int i, ak akVar);
    }

    au(@NonNull Context context, @NonNull com.inmobi.ads.c cVar, @NonNull ah ahVar, @NonNull ao aoVar, @NonNull c cVar2, @NonNull a aVar, @NonNull b bVar) {
        this.f = new WeakReference<>(context);
        this.h = ahVar;
        this.g = aoVar;
        this.j = cVar2;
        this.k = aVar;
        this.l = bVar;
        this.i = cVar;
        this.d = NativeViewFactory.a(context);
    }

    public final Context a() {
        return this.f.get();
    }

    public final aw a(@Nullable aw awVar, @NonNull ViewGroup viewGroup, RenderView renderView) {
        this.p = renderView;
        aw awVarA = a(awVar, viewGroup);
        if (!this.o) {
            b(awVarA, this.g.d);
        }
        return awVarA;
    }

    final aw b(@Nullable aw awVar, @NonNull final ViewGroup viewGroup, RenderView renderView) {
        this.p = renderView;
        final aw awVarA = a(awVar, viewGroup);
        n.post(new Runnable() { // from class: com.inmobi.ads.au.1
            @Override // java.lang.Runnable
            public final void run() {
                if (au.this.o) {
                    return;
                }
                au auVar = au.this;
                auVar.b(awVarA, auVar.g.d);
            }
        });
        return awVarA;
    }

    private aw a(@Nullable aw awVar, @NonNull ViewGroup viewGroup) {
        aw awVar2 = awVar == null ? (aw) this.d.a(a(), this.g.d, this.i) : awVar;
        if (awVar2 != null && awVar != null) {
            ViewParent parent = awVar2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(awVar2);
            }
            NativeViewFactory nativeViewFactory = this.d;
            for (int childCount = awVar2.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = awVar2.getChildAt(childCount);
                awVar2.removeViewAt(childCount);
                nativeViewFactory.a(childAt);
            }
            NativeViewFactory.a(awVar2, this.g.d.c);
        }
        NativeViewFactory.b(this.g.d.c.a.x);
        awVar2.setLayoutParams(NativeViewFactory.a(this.g.d, viewGroup));
        return awVar2;
    }

    public final ViewGroup a(@NonNull ViewGroup viewGroup, @NonNull am amVar) {
        ViewGroup viewGroup2 = (ViewGroup) this.d.a(a(), amVar, this.i);
        if (viewGroup2 != null) {
            viewGroup2.setLayoutParams(NativeViewFactory.a(amVar, viewGroup));
        }
        return viewGroup2;
    }

    @Override // com.inmobi.ads.NativeScrollableContainer.a
    public final int a(int i) {
        this.b = i;
        this.j.a(i, this.g.a(i));
        return d();
    }

    private void a(View view, final ak akVar) {
        boolean z;
        final List<n.a> listA = this.c.a(view, akVar);
        if (listA == null) {
            NativeTracker.TrackerEventType trackerEventType = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW;
            Iterator<NativeTracker> it = akVar.u.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (trackerEventType == it.next().b) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                return;
            }
        }
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.inmobi.ads.au.3
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view2) {
                au.this.c.a(listA);
                ah unused = au.this.h;
                ak akVarA = ah.a(au.this.h.h(), akVar);
                ak akVar2 = akVar;
                NativeTracker.TrackerEventType trackerEventType2 = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW;
                ah ahVar = au.this.h;
                if (akVarA == null) {
                    akVarA = akVar;
                }
                akVar2.a(trackerEventType2, ahVar.a(akVarA));
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view2) {
                view2.removeOnAttachStateChangeListener(this);
                n nVar = au.this.c;
                List list = listA;
                if (list == null) {
                    return;
                }
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    ((n.a) it2.next()).a.cancel();
                }
                nVar.a.removeAll(list);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:112:0x0240 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:116:0x01f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:125:0x00df A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:141:0x000d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:35:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:39:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:40:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:42:0x0101  */
    /* JADX WARN: Code duplicated, block: B:49:0x0137  */
    /* JADX WARN: Code duplicated, block: B:55:0x014d  */
    /* JADX WARN: Code duplicated, block: B:58:0x0179  */
    /* JADX WARN: Code duplicated, block: B:59:0x017f  */
    /* JADX WARN: Code duplicated, block: B:62:0x0189  */
    /* JADX WARN: Code duplicated, block: B:63:0x0191  */
    /* JADX WARN: Code duplicated, block: B:69:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:78:0x0211  */
    /* JADX WARN: Code duplicated, block: B:88:0x0298 A[Catch: Exception -> 0x02db, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x02db, blocks: (B:85:0x0240, B:88:0x0298, B:90:0x02ad, B:93:0x02d3, B:91:0x02b5), top: B:112:0x0240 }] */
    /* JADX WARN: Code duplicated, block: B:91:0x02b5 A[Catch: Exception -> 0x02db, TRY_LEAVE, TryCatch #0 {Exception -> 0x02db, blocks: (B:85:0x0240, B:88:0x0298, B:90:0x02ad, B:93:0x02d3, B:91:0x02b5), top: B:112:0x0240 }] */
    final ViewGroup b(@NonNull ViewGroup viewGroup, am amVar) {
        View view;
        final WeakReference weakReference;
        NativeVideoWrapper nativeVideoWrapper;
        be beVar;
        RelativeLayout.LayoutParams layoutParams;
        Point point;
        double d;
        double d2;
        double d3;
        double dC;
        double d4;
        final be beVar2;
        NativeVideoView videoView;
        am amVar2;
        long jCurrentTimeMillis;
        av avVarA;
        bg bgVar;
        a(amVar, viewGroup);
        for (ak akVar : amVar) {
            if ("CONTAINER" == akVar.b) {
                if (akVar.d.equalsIgnoreCase("card_scrollable")) {
                    NativeScrollableContainer nativeScrollableContainer = (NativeScrollableContainer) this.d.a(a(), akVar, this.i);
                    if (nativeScrollableContainer != null) {
                        this.m = ay.a(nativeScrollableContainer.getType(), this.g, this);
                        ax axVar = this.m;
                        if (axVar != null) {
                            nativeScrollableContainer.a((am) akVar, axVar, this.b, d(), this);
                            nativeScrollableContainer.setLayoutParams(NativeViewFactory.a(akVar, viewGroup));
                            a(nativeScrollableContainer, akVar);
                            viewGroup.addView(nativeScrollableContainer);
                        }
                    }
                } else {
                    ViewGroup viewGroup2 = (ViewGroup) this.d.a(a(), akVar, this.i);
                    if (viewGroup2 != null) {
                        ViewGroup viewGroupB = b(viewGroup2, (am) akVar);
                        viewGroupB.setLayoutParams(NativeViewFactory.a(akVar, viewGroup));
                        a(viewGroupB, akVar);
                        viewGroup.addView(viewGroupB);
                    }
                }
            } else {
                View viewA = null;
                if ("WEBVIEW".equals(akVar.b)) {
                    bf bfVar = (bf) akVar;
                    if (bfVar.A && (view = this.p) != null) {
                        if (view.getParent() != null) {
                            ((ViewGroup) view.getParent()).removeView(view);
                        }
                        this.p = null;
                        viewA = view;
                    } else if ("UNKNOWN".equals(bfVar.z)) {
                    }
                    if (viewA == null) {
                        viewA = this.d.a(a(), akVar, this.i);
                    }
                    if (viewA != null) {
                        weakReference = new WeakReference(viewA);
                        if (akVar.o != -1) {
                            viewA.setVisibility(4);
                            n.postDelayed(new Runnable() { // from class: com.inmobi.ads.au.4
                                @Override // java.lang.Runnable
                                public final void run() {
                                    View view2 = (View) weakReference.get();
                                    if (view2 != null) {
                                        view2.setVisibility(0);
                                    }
                                }
                            }, akVar.o * 1000);
                        } else if (akVar.p != -1) {
                            n.postDelayed(new Runnable() { // from class: com.inmobi.ads.au.5
                                @Override // java.lang.Runnable
                                public final void run() {
                                    View view2 = (View) weakReference.get();
                                    if (view2 != null) {
                                        view2.setVisibility(4);
                                    }
                                }
                            }, akVar.p * 1000);
                        }
                        viewA.setLayoutParams(NativeViewFactory.a(akVar, viewGroup));
                        a(viewA, akVar);
                        viewGroup.addView(viewA);
                        if (Build.VERSION.SDK_INT >= 15 && ShareConstants.VIDEO_URL == akVar.b) {
                            beVar2 = (be) akVar;
                            videoView = ((NativeVideoWrapper) viewA).getVideoView();
                            if (Build.VERSION.SDK_INT >= 15) {
                                amVar2 = (am) beVar2.t;
                                jCurrentTimeMillis = System.currentTimeMillis();
                                if (amVar2 != null && 0 != amVar2.z) {
                                    jCurrentTimeMillis = amVar2.z;
                                }
                                if (amVar2 != null) {
                                    amVar2.z = jCurrentTimeMillis;
                                }
                                videoView.setClickable(false);
                                videoView.setId(Integer.MAX_VALUE);
                                videoView.e = 0;
                                videoView.f = 0;
                                videoView.a = Uri.parse(((by) beVar2.e).b());
                                if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN == ((AdContainer.RenderingProperties.PlacementType) beVar2.v.get("placementType"))) {
                                    avVarA = new av();
                                } else {
                                    avVarA = av.a();
                                }
                                videoView.c = avVarA;
                                if (videoView.d != 0) {
                                    videoView.c.setAudioSessionId(videoView.d);
                                } else {
                                    videoView.d = videoView.c.getAudioSessionId();
                                }
                                try {
                                    videoView.c.setDataSource(videoView.getContext().getApplicationContext(), videoView.a, videoView.b);
                                    videoView.setTag(beVar2);
                                    videoView.g = new NativeVideoView.d(videoView);
                                    videoView.setSurfaceTextureListener(videoView.l);
                                    videoView.setFocusable(true);
                                    videoView.setFocusableInTouchMode(true);
                                    videoView.requestFocus();
                                } catch (IOException unused) {
                                    videoView.c.a = -1;
                                    videoView.c.b = -1;
                                }
                                if (beVar2.y != null) {
                                    beVar2.a((be) beVar2.y);
                                }
                                videoView.setQuartileCompletedListener(new NativeVideoView.c() { // from class: com.inmobi.ads.au.7
                                    @Override // com.inmobi.ads.NativeVideoView.c
                                    public final void a(int i) {
                                        if (au.this.a != null) {
                                            au.this.a.b(beVar2, i);
                                            if (3 == i) {
                                                try {
                                                    au.this.a.d(beVar2);
                                                } catch (Exception e2) {
                                                    String unused2 = au.e;
                                                    new StringBuilder("SDK encountered unexpected error in handling the onVideoCompleted event; ").append(e2.getMessage());
                                                }
                                            }
                                        }
                                    }
                                });
                                videoView.setPlaybackEventListener(new NativeVideoView.b() { // from class: com.inmobi.ads.au.8
                                    @Override // com.inmobi.ads.NativeVideoView.b
                                    @SuppressLint({"SwitchIntDef"})
                                    public final void a(int i) {
                                        if (au.this.a != null) {
                                            if (i == 0) {
                                                try {
                                                    au.this.a.a();
                                                    return;
                                                } catch (Exception e2) {
                                                    String unused2 = au.e;
                                                    new StringBuilder("SDK encountered unexpected error in handling onVideoPrepared event; ").append(e2.getMessage());
                                                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                                                    return;
                                                }
                                            }
                                            if (i == 1) {
                                                try {
                                                    au.this.a.a(beVar2);
                                                    return;
                                                } catch (Exception e3) {
                                                    String unused3 = au.e;
                                                    new StringBuilder("SDK encountered unexpected error in handling onVideoPlayed event; ").append(e3.getMessage());
                                                    return;
                                                }
                                            }
                                            if (i == 2) {
                                                try {
                                                    au.this.a.b(beVar2);
                                                    return;
                                                } catch (Exception e4) {
                                                    String unused4 = au.e;
                                                    new StringBuilder("SDK encountered unexpected error in handling onVideoPaused event; ").append(e4.getMessage());
                                                    return;
                                                }
                                            }
                                            if (i == 3) {
                                                try {
                                                    au.this.a.c(beVar2);
                                                    return;
                                                } catch (Exception e5) {
                                                    String unused5 = au.e;
                                                    new StringBuilder("SDK encountered unexpected error in handling onVideoResumed event; ").append(e5.getMessage());
                                                    return;
                                                }
                                            }
                                            if (i != 5) {
                                                return;
                                            }
                                            try {
                                                au.this.a.e(beVar2);
                                            } catch (Exception e6) {
                                                String unused6 = au.e;
                                                new StringBuilder("SDK encountered unexpected error in handling fireVideoQ4Beacons event; ").append(e6.getMessage());
                                            }
                                        }
                                    }
                                });
                                videoView.setMediaErrorListener(new NativeVideoView.a() { // from class: com.inmobi.ads.au.9
                                    @Override // com.inmobi.ads.NativeVideoView.a
                                    public final void a(int i) {
                                        if (au.this.a != null) {
                                            try {
                                                au.this.a.a(beVar2, i);
                                            } catch (Exception e2) {
                                                String unused2 = au.e;
                                                new StringBuilder("SDK encountered unexpected error in handling the onVideoError event; ").append(e2.getMessage());
                                            }
                                        }
                                    }
                                });
                                bgVar = this.a;
                                if (bgVar != null) {
                                    try {
                                        bgVar.a(videoView);
                                    } catch (Exception e2) {
                                        new StringBuilder("SDK encountered unexpected error in handling the onVideoViewCreated event; ").append(e2.getMessage());
                                    }
                                }
                            }
                        }
                        a(akVar, viewA);
                        if ("TIMER" == akVar.b) {
                            viewA.setTag("timerView");
                            final bb bbVar = (bb) akVar;
                            ((NativeTimerView) viewA).setTimerEventsListener(new NativeTimerView.b() { // from class: com.inmobi.ads.au.2
                                @Override // com.inmobi.ads.NativeTimerView.b
                                public final void a() {
                                    if (au.this.l != null) {
                                        au.this.l.a(bbVar);
                                    }
                                }
                            });
                        }
                        if (Build.VERSION.SDK_INT >= 15 && ShareConstants.VIDEO_URL == akVar.b) {
                            nativeVideoWrapper = (NativeVideoWrapper) viewA;
                            nativeVideoWrapper.setVideoEventListener(this.a);
                            beVar = (be) nativeVideoWrapper.a.getTag();
                            if (beVar != null) {
                                try {
                                    String strB = beVar.b().b();
                                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                                    mediaMetadataRetriever.setDataSource(strB);
                                    int iIntValue = Integer.valueOf(mediaMetadataRetriever.extractMetadata(18)).intValue();
                                    int iIntValue2 = Integer.valueOf(mediaMetadataRetriever.extractMetadata(19)).intValue();
                                    mediaMetadataRetriever.release();
                                    point = beVar.c.a;
                                    double dC2 = NativeViewFactory.c(point.x);
                                    double dC3 = NativeViewFactory.c(point.y);
                                    Double.isNaN(dC2);
                                    Double.isNaN(dC3);
                                    d = dC2 / dC3;
                                    d2 = iIntValue;
                                    d3 = iIntValue2;
                                    Double.isNaN(d2);
                                    Double.isNaN(d3);
                                    if (d > d2 / d3) {
                                        double dC4 = NativeViewFactory.c(point.y);
                                        Double.isNaN(dC4);
                                        Double.isNaN(d3);
                                        Double.isNaN(d2);
                                        d4 = d2 * ((dC4 * 1.0d) / d3);
                                        dC = NativeViewFactory.c(point.y);
                                    } else {
                                        double dC5 = NativeViewFactory.c(point.x);
                                        double dC6 = NativeViewFactory.c(point.x);
                                        Double.isNaN(dC6);
                                        Double.isNaN(d2);
                                        Double.isNaN(d3);
                                        dC = ((dC6 * 1.0d) / d2) * d3;
                                        d4 = dC5;
                                    }
                                    layoutParams = new RelativeLayout.LayoutParams((int) d4, (int) dC);
                                } catch (Exception e3) {
                                    RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
                                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
                                    layoutParams = layoutParams2;
                                }
                                layoutParams.addRule(13);
                                nativeVideoWrapper.a.setLayoutParams(layoutParams);
                            }
                        }
                        if ("WEBVIEW" != akVar.b && (viewA instanceof RenderView)) {
                            RenderView renderView = (RenderView) viewA;
                            bf bfVar2 = (bf) akVar;
                            renderView.setScrollable(bfVar2.B);
                            renderView.setReferenceContainer(this.h.n);
                            renderView.setRenderViewEventListener(this.h.u());
                            renderView.setPlacementId(this.h.e);
                            renderView.setAllowAutoRedirection(this.h.g);
                            renderView.setCreativeId(this.h.f);
                            renderView.setImpressionId(this.h.d);
                            if (!bfVar2.A) {
                                ah ahVar = this.h;
                                if (ahVar.y == 0 && ahVar.x == null && ahVar.w == null) {
                                    ahVar.x = renderView;
                                }
                            }
                        }
                    }
                } else if (!ShareConstants.IMAGE_URL.equals(akVar.b) || akVar.e != null) {
                    if (viewA == null) {
                        viewA = this.d.a(a(), akVar, this.i);
                    }
                    if (viewA != null) {
                        weakReference = new WeakReference(viewA);
                        if (akVar.o != -1) {
                            viewA.setVisibility(4);
                            n.postDelayed(new Runnable() { // from class: com.inmobi.ads.au.4
                                @Override // java.lang.Runnable
                                public final void run() {
                                    View view2 = (View) weakReference.get();
                                    if (view2 != null) {
                                        view2.setVisibility(0);
                                    }
                                }
                            }, akVar.o * 1000);
                        } else if (akVar.p != -1) {
                            n.postDelayed(new Runnable() { // from class: com.inmobi.ads.au.5
                                @Override // java.lang.Runnable
                                public final void run() {
                                    View view2 = (View) weakReference.get();
                                    if (view2 != null) {
                                        view2.setVisibility(4);
                                    }
                                }
                            }, akVar.p * 1000);
                        }
                        viewA.setLayoutParams(NativeViewFactory.a(akVar, viewGroup));
                        a(viewA, akVar);
                        viewGroup.addView(viewA);
                        if (Build.VERSION.SDK_INT >= 15) {
                            beVar2 = (be) akVar;
                            videoView = ((NativeVideoWrapper) viewA).getVideoView();
                            if (Build.VERSION.SDK_INT >= 15) {
                                amVar2 = (am) beVar2.t;
                                jCurrentTimeMillis = System.currentTimeMillis();
                                if (amVar2 != null) {
                                    jCurrentTimeMillis = amVar2.z;
                                }
                                if (amVar2 != null) {
                                    amVar2.z = jCurrentTimeMillis;
                                }
                                videoView.setClickable(false);
                                videoView.setId(Integer.MAX_VALUE);
                                videoView.e = 0;
                                videoView.f = 0;
                                videoView.a = Uri.parse(((by) beVar2.e).b());
                                if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN == ((AdContainer.RenderingProperties.PlacementType) beVar2.v.get("placementType"))) {
                                    avVarA = new av();
                                } else {
                                    avVarA = av.a();
                                }
                                videoView.c = avVarA;
                                if (videoView.d != 0) {
                                    videoView.c.setAudioSessionId(videoView.d);
                                } else {
                                    videoView.d = videoView.c.getAudioSessionId();
                                }
                                videoView.c.setDataSource(videoView.getContext().getApplicationContext(), videoView.a, videoView.b);
                                videoView.setTag(beVar2);
                                videoView.g = new NativeVideoView.d(videoView);
                                videoView.setSurfaceTextureListener(videoView.l);
                                videoView.setFocusable(true);
                                videoView.setFocusableInTouchMode(true);
                                videoView.requestFocus();
                                if (beVar2.y != null) {
                                    beVar2.a((be) beVar2.y);
                                }
                                videoView.setQuartileCompletedListener(new NativeVideoView.c() { // from class: com.inmobi.ads.au.7
                                    @Override // com.inmobi.ads.NativeVideoView.c
                                    public final void a(int i) {
                                        if (au.this.a != null) {
                                            au.this.a.b(beVar2, i);
                                            if (3 == i) {
                                                try {
                                                    au.this.a.d(beVar2);
                                                } catch (Exception e4) {
                                                    String unused2 = au.e;
                                                    new StringBuilder("SDK encountered unexpected error in handling the onVideoCompleted event; ").append(e4.getMessage());
                                                }
                                            }
                                        }
                                    }
                                });
                                videoView.setPlaybackEventListener(new NativeVideoView.b() { // from class: com.inmobi.ads.au.8
                                    @Override // com.inmobi.ads.NativeVideoView.b
                                    @SuppressLint({"SwitchIntDef"})
                                    public final void a(int i) {
                                        if (au.this.a != null) {
                                            if (i == 0) {
                                                try {
                                                    au.this.a.a();
                                                    return;
                                                } catch (Exception e4) {
                                                    String unused2 = au.e;
                                                    new StringBuilder("SDK encountered unexpected error in handling onVideoPrepared event; ").append(e4.getMessage());
                                                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e4));
                                                    return;
                                                }
                                            }
                                            if (i == 1) {
                                                try {
                                                    au.this.a.a(beVar2);
                                                    return;
                                                } catch (Exception e5) {
                                                    String unused3 = au.e;
                                                    new StringBuilder("SDK encountered unexpected error in handling onVideoPlayed event; ").append(e5.getMessage());
                                                    return;
                                                }
                                            }
                                            if (i == 2) {
                                                try {
                                                    au.this.a.b(beVar2);
                                                    return;
                                                } catch (Exception e6) {
                                                    String unused4 = au.e;
                                                    new StringBuilder("SDK encountered unexpected error in handling onVideoPaused event; ").append(e6.getMessage());
                                                    return;
                                                }
                                            }
                                            if (i == 3) {
                                                try {
                                                    au.this.a.c(beVar2);
                                                    return;
                                                } catch (Exception e7) {
                                                    String unused5 = au.e;
                                                    new StringBuilder("SDK encountered unexpected error in handling onVideoResumed event; ").append(e7.getMessage());
                                                    return;
                                                }
                                            }
                                            if (i != 5) {
                                                return;
                                            }
                                            try {
                                                au.this.a.e(beVar2);
                                            } catch (Exception e8) {
                                                String unused6 = au.e;
                                                new StringBuilder("SDK encountered unexpected error in handling fireVideoQ4Beacons event; ").append(e8.getMessage());
                                            }
                                        }
                                    }
                                });
                                videoView.setMediaErrorListener(new NativeVideoView.a() { // from class: com.inmobi.ads.au.9
                                    @Override // com.inmobi.ads.NativeVideoView.a
                                    public final void a(int i) {
                                        if (au.this.a != null) {
                                            try {
                                                au.this.a.a(beVar2, i);
                                            } catch (Exception e4) {
                                                String unused2 = au.e;
                                                new StringBuilder("SDK encountered unexpected error in handling the onVideoError event; ").append(e4.getMessage());
                                            }
                                        }
                                    }
                                });
                                bgVar = this.a;
                                if (bgVar != null) {
                                    bgVar.a(videoView);
                                }
                            }
                        }
                        a(akVar, viewA);
                        if ("TIMER" == akVar.b) {
                            viewA.setTag("timerView");
                            final bb bbVar2 = (bb) akVar;
                            ((NativeTimerView) viewA).setTimerEventsListener(new NativeTimerView.b() { // from class: com.inmobi.ads.au.2
                                @Override // com.inmobi.ads.NativeTimerView.b
                                public final void a() {
                                    if (au.this.l != null) {
                                        au.this.l.a(bbVar2);
                                    }
                                }
                            });
                        }
                        if (Build.VERSION.SDK_INT >= 15) {
                            nativeVideoWrapper = (NativeVideoWrapper) viewA;
                            nativeVideoWrapper.setVideoEventListener(this.a);
                            beVar = (be) nativeVideoWrapper.a.getTag();
                            if (beVar != null) {
                                String strB2 = beVar.b().b();
                                MediaMetadataRetriever mediaMetadataRetriever2 = new MediaMetadataRetriever();
                                mediaMetadataRetriever2.setDataSource(strB2);
                                int iIntValue3 = Integer.valueOf(mediaMetadataRetriever2.extractMetadata(18)).intValue();
                                int iIntValue4 = Integer.valueOf(mediaMetadataRetriever2.extractMetadata(19)).intValue();
                                mediaMetadataRetriever2.release();
                                point = beVar.c.a;
                                double dC7 = NativeViewFactory.c(point.x);
                                double dC8 = NativeViewFactory.c(point.y);
                                Double.isNaN(dC7);
                                Double.isNaN(dC8);
                                d = dC7 / dC8;
                                d2 = iIntValue3;
                                d3 = iIntValue4;
                                Double.isNaN(d2);
                                Double.isNaN(d3);
                                if (d > d2 / d3) {
                                    double dC9 = NativeViewFactory.c(point.y);
                                    Double.isNaN(dC9);
                                    Double.isNaN(d3);
                                    Double.isNaN(d2);
                                    d4 = d2 * ((dC9 * 1.0d) / d3);
                                    dC = NativeViewFactory.c(point.y);
                                } else {
                                    double dC10 = NativeViewFactory.c(point.x);
                                    double dC11 = NativeViewFactory.c(point.x);
                                    Double.isNaN(dC11);
                                    Double.isNaN(d2);
                                    Double.isNaN(d3);
                                    dC = ((dC11 * 1.0d) / d2) * d3;
                                    d4 = dC10;
                                }
                                layoutParams = new RelativeLayout.LayoutParams((int) d4, (int) dC);
                                layoutParams.addRule(13);
                                nativeVideoWrapper.a.setLayoutParams(layoutParams);
                            }
                        }
                        if ("WEBVIEW" != akVar.b) {
                        }
                    }
                }
            }
        }
        return viewGroup;
    }

    private int d() {
        if (this.b == 0) {
            return GravityCompat.START;
        }
        if (this.g.b() - 1 == this.b) {
            return GravityCompat.END;
        }
        return 1;
    }

    final void b() {
        this.o = true;
        this.f.clear();
        ax axVar = this.m;
        if (axVar != null) {
            axVar.destroy();
        }
    }

    private void a(final ak akVar, View view) {
        if (akVar.h) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.inmobi.ads.au.6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    au.this.k.a(view2, akVar);
                }
            });
        }
    }
}
