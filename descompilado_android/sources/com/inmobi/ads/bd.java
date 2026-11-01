package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.inmobi.commons.core.utilities.Logger;
import com.integralads.avid.library.inmobi.session.AvidManagedVideoAdSession;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.lang.ref.WeakReference;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: NativeVideoAdContainer.java */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(15)
public class bd extends ah {
    private static final String D = "bd";
    WeakReference<View> B;
    ai.a C;
    private final AdContainer.a E;

    private void a(NativeVideoView nativeVideoView) {
        int videoVolume = nativeVideoView.getVideoVolume();
        int lastVolume = nativeVideoView.getLastVolume();
        if (videoVolume == lastVolume || lastVolume <= 0) {
            return;
        }
        b(true);
        nativeVideoView.setLastVolume(videoVolume);
    }

    bd(@NonNull Context context, @NonNull AdContainer.RenderingProperties renderingProperties, @NonNull ao aoVar, @NonNull String str, @NonNull String str2, @Nullable Set<bq> set, @NonNull c cVar, long j, boolean z, String str3) {
        super(context, renderingProperties, aoVar, str, str2, set, cVar, j, z, str3);
        this.E = new AdContainer.a() { // from class: com.inmobi.ads.bd.1
            @Override // com.inmobi.ads.AdContainer.a
            public final void a() {
                String unused = bd.D;
                ah.c cVarE = bd.this.e();
                if (cVarE != null) {
                    cVarE.a();
                }
            }

            @Override // com.inmobi.ads.AdContainer.a
            public final void a(@NonNull Object obj) {
                if (bd.this.l() == null) {
                    return;
                }
                be beVar = (be) obj;
                String unused = bd.D;
                beVar.v.put("didRequestFullScreen", true);
                beVar.v.put("isFullScreen", true);
                beVar.v.put("shouldAutoPlay", true);
                if (beVar.y != null) {
                    beVar.y.v.put("didRequestFullScreen", true);
                    beVar.y.v.put("isFullScreen", true);
                    beVar.y.v.put("shouldAutoPlay", true);
                }
                if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE == bd.this.b.a) {
                    bd.this.getViewableAd().a(1);
                    beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_FULLSCREEN, bd.this.g(beVar));
                }
                ah.c cVarE = bd.this.e();
                if (cVarE != null) {
                    cVarE.b();
                }
            }

            @Override // com.inmobi.ads.AdContainer.a
            public final void b(@NonNull Object obj) {
                String unused = bd.D;
                be beVar = (be) obj;
                beVar.v.put("didRequestFullScreen", false);
                beVar.v.put("isFullScreen", false);
                if (beVar.y != null) {
                    beVar.y.v.put("didRequestFullScreen", false);
                    beVar.y.v.put("isFullScreen", false);
                    beVar.y.y = null;
                }
                beVar.y = null;
                if (bd.this.b.a == AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE) {
                    bd.this.getViewableAd().a(2);
                    if (bd.this.n != null) {
                        bd.this.n.getViewableAd().a(16);
                    }
                    beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_EXIT_FULLSCREEN, bd.this.g(beVar));
                } else {
                    bd.this.getViewableAd().a(3);
                }
                ah.c cVarE = bd.this.e();
                if (cVarE != null) {
                    cVarE.f();
                }
            }
        };
        this.C = new ai.a() { // from class: com.inmobi.ads.bd.2
            @Override // com.inmobi.ads.ai.a
            public final void a(View view, boolean z2) {
                bd.this.a(z2);
                bd.a(bd.this, view, z2);
            }
        };
        this.a = aoVar;
    }

    @Override // com.inmobi.ads.ah
    final void a(View view) {
        if (k() || this.l || !(view instanceof NativeVideoView)) {
            return;
        }
        NativeVideoView nativeVideoView = (NativeVideoView) view;
        this.k = true;
        HashMap map = new HashMap();
        map.put("type", AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN == getRenderingProperties().a ? "int" : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
        map.put("clientRequestId", this.h);
        map.put("impId", this.d);
        com.inmobi.commons.core.e.b.a();
        com.inmobi.commons.core.e.b.a("ads", "ViewableBeaconFired", map);
        f((be) nativeVideoView.getTag());
    }

    @Override // com.inmobi.ads.ah
    final boolean i() {
        return AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE == this.b.a && l() != null;
    }

    @Override // com.inmobi.ads.ah, com.inmobi.ads.AdContainer
    @SuppressLint({"SwitchIntDef"})
    public ca getViewableAd() {
        AvidManagedVideoAdSession avidManagedVideoAdSession;
        Context contextJ = j();
        if (this.j == null && contextJ != null) {
            g();
            this.j = new ab(this, new cd(this));
            if (this.i != null) {
                if (contextJ instanceof Activity) {
                    try {
                        Activity activity = (Activity) contextJ;
                        for (bq bqVar : this.i) {
                            int i = bqVar.a;
                            if (i == 1) {
                                ca caVar = this.j;
                                Map<String, Object> map = bqVar.b;
                                be beVar = (be) this.a.c(ShareConstants.VIDEO_URL).get(0);
                                StringBuilder sb = new StringBuilder();
                                for (NativeTracker nativeTracker : beVar.u) {
                                    if (NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_MOAT == nativeTracker.b) {
                                        sb.append(nativeTracker.a);
                                    }
                                }
                                if (sb.length() > 0) {
                                    map.put("zMoatVASTIDs", sb.toString());
                                }
                                this.j = new af(activity, caVar, this, map);
                            } else if (i == 3 && (avidManagedVideoAdSession = (AvidManagedVideoAdSession) bqVar.b.get("avidAdSession")) != null) {
                                this.j = new w(activity, this.j, this, avidManagedVideoAdSession);
                            }
                        }
                    } catch (Exception e) {
                        new StringBuilder("Exception occurred while creating the video viewable ad : ").append(e.getMessage());
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    }
                } else {
                    HashMap map2 = new HashMap();
                    map2.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
                    map2.put("impId", this.d);
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("ads", "TrackersForService", map2);
                }
            }
        }
        return this.j;
    }

    @Override // com.inmobi.ads.ah, com.inmobi.ads.AdContainer
    @NonNull
    public AdContainer.a getFullScreenEventsListener() {
        return this.E;
    }

    @Override // com.inmobi.ads.ah, com.inmobi.ads.AdContainer
    @Nullable
    public View getVideoContainerView() {
        WeakReference<View> weakReference = this.B;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    @Override // com.inmobi.ads.ah
    final boolean n() {
        return !this.r;
    }

    final void w() {
        this.j.a(5);
    }

    final void e(be beVar) {
        new StringBuilder("Firing Q4 beacons for completion at ").append(beVar.E);
        beVar.v.put("didQ4Fire", true);
        beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_Q4, g(beVar));
        this.j.a(12);
        HashMap map = new HashMap();
        map.put("url", beVar.b().b());
        map.put("isCached", "1");
        map.put("completeAfter", Integer.valueOf(beVar.E));
        a("VideoQ4Completed", map);
    }

    @Override // com.inmobi.ads.ah
    final void q() {
        super.q();
        NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) getVideoContainerView();
        if (nativeVideoWrapper != null) {
            NativeVideoView videoView = nativeVideoWrapper.getVideoView();
            if (this.b.a == AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE && !i() && videoView.getVideoVolume() > 0) {
                videoView.setLastVolume(-2);
                b(true);
            }
            videoView.pause();
        }
    }

    @VisibleForTesting
    private static String y() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        int iNextInt = 0;
        while (iNextInt == 0) {
            iNextInt = (secureRandom.nextInt() & Integer.MAX_VALUE) % 10;
        }
        sb.append(iNextInt);
        for (int i = 1; i < 8; i++) {
            sb.append((secureRandom.nextInt() & Integer.MAX_VALUE) % 10);
        }
        return sb.toString();
    }

    private void b(boolean z) {
        ah.c cVarE;
        if (this.b.a != AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE || i() || (cVarE = e()) == null) {
            return;
        }
        cVarE.a(z);
    }

    @Override // com.inmobi.ads.ah, com.inmobi.ads.AdContainer
    public void destroy() {
        NativeVideoWrapper nativeVideoWrapper;
        if (this.l) {
            return;
        }
        if (getVideoContainerView() != null && (nativeVideoWrapper = (NativeVideoWrapper) getVideoContainerView()) != null) {
            nativeVideoWrapper.getVideoView().c();
        }
        super.destroy();
    }

    private void f(@NonNull be beVar) {
        if (((Boolean) beVar.v.get("didImpressionFire")).booleanValue()) {
            return;
        }
        List<NativeTracker> list = beVar.u;
        Map<String, String> mapG = g(beVar);
        List arrayList = new ArrayList();
        for (NativeTracker nativeTracker : list) {
            if (NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_VIDEO_RENDER == nativeTracker.b) {
                if (nativeTracker.a.startsWith("http")) {
                    be.a(nativeTracker, mapG);
                }
                arrayList = (List) nativeTracker.d.get("referencedEvents");
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    beVar.a((NativeTracker.TrackerEventType) it.next(), mapG);
                }
            }
        }
        if (arrayList.isEmpty()) {
            beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_PLAY, mapG);
            beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_RENDER, mapG);
        }
        this.a.d.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_RENDER, g(beVar));
        beVar.v.put("didImpressionFire", true);
        this.j.a(0);
        if (this.b.a == AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE) {
            HashMap map = new HashMap();
            map.put("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
            map.put("clientRequestId", this.h);
            map.put("impId", this.d);
            a("AdRendered", map);
        }
        if (e() != null) {
            e().d();
        }
    }

    @Override // com.inmobi.ads.ah
    protected final void b(@NonNull ak akVar) {
        NativeVideoWrapper nativeVideoWrapper;
        int i = akVar.l;
        if (i != 0) {
            if (i == 1) {
                super.b(akVar);
                return;
            }
            if (i == 3) {
                try {
                    if (this.w != null) {
                        this.w.d("window.imraid.broadcastEvent('replay');");
                    }
                    if (f() != null) {
                        View viewF = f();
                        NativeTimerView nativeTimerViewB = ah.b(viewF);
                        if (nativeTimerViewB != null) {
                            nativeTimerViewB.a();
                        }
                        ViewGroup viewGroup = (ViewGroup) viewF.getParent();
                        if (viewGroup != null) {
                            viewGroup.removeView(viewF);
                        }
                    }
                    if (!ShareConstants.VIDEO_URL.equals(akVar.b)) {
                        new StringBuilder("Action 3 not valid for asset of type: ").append(akVar.b);
                        return;
                    }
                    NativeVideoWrapper nativeVideoWrapper2 = (NativeVideoWrapper) getVideoContainerView();
                    if (nativeVideoWrapper2 != null) {
                        nativeVideoWrapper2.getVideoView().e();
                        nativeVideoWrapper2.getVideoView().start();
                        return;
                    }
                    return;
                } catch (Exception e) {
                    new StringBuilder("Encountered unexpected error in handling replay action on video: ").append(e.getMessage());
                    Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in replaying video");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    return;
                }
            }
            if (i == 4) {
                try {
                    if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE != this.b.a || (nativeVideoWrapper = (NativeVideoWrapper) getVideoContainerView()) == null) {
                        return;
                    }
                    NativeVideoView videoView = nativeVideoWrapper.getVideoView();
                    be beVar = (be) videoView.getTag();
                    if (videoView.getState() != 1) {
                        try {
                            if (this.l || this.p.get() == null || ((Boolean) beVar.v.get("didRequestFullScreen")).booleanValue()) {
                                return;
                            }
                            beVar.v.put("didRequestFullScreen", true);
                            beVar.v.put("seekPosition", Integer.valueOf(videoView.getCurrentPosition()));
                            beVar.v.put("lastMediaVolume", Integer.valueOf(videoView.getVolume()));
                            if (videoView.getMediaPlayer().isPlaying()) {
                                videoView.getMediaPlayer().pause();
                            }
                            videoView.getMediaPlayer().a = 4;
                            beVar.v.put("isFullScreen", true);
                            beVar.v.put("seekPosition", Integer.valueOf(videoView.getMediaPlayer().getCurrentPosition()));
                            m();
                            return;
                        } catch (Exception e2) {
                            new StringBuilder("SDK encountered unexpected error in handling the onVideoRequestedFullScreen event; ").append(e2.getMessage());
                            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                            return;
                        }
                    }
                    return;
                } catch (Exception e3) {
                    new StringBuilder("Encountered unexpected error in handling fullscreen action on video: ").append(e3.getMessage());
                    Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in expanding video to fullscreen");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
                    return;
                }
            }
            if (i == 5) {
                try {
                    NativeVideoWrapper nativeVideoWrapper3 = (NativeVideoWrapper) getVideoContainerView();
                    if (nativeVideoWrapper3 != null) {
                        be beVar2 = (be) nativeVideoWrapper3.getVideoView().getTag();
                        beVar2.v.put("shouldAutoPlay", true);
                        if (beVar2.y != null) {
                            beVar2.y.v.put("shouldAutoPlay", true);
                        }
                        nativeVideoWrapper3.getVideoView().start();
                        return;
                    }
                    return;
                } catch (Exception e4) {
                    new StringBuilder("Encountered unexpected error in handling play action on video: ").append(e4.getMessage());
                    Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in playing video");
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e4));
                    return;
                }
            }
            try {
                if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.b.a) {
                    super.b(akVar);
                    if (!ShareConstants.VIDEO_URL.equals(akVar.b)) {
                        new StringBuilder("Action 2 not valid for asset of type: ").append(akVar.b);
                        return;
                    }
                    NativeVideoWrapper nativeVideoWrapper4 = (NativeVideoWrapper) getVideoContainerView();
                    if (nativeVideoWrapper4 != null) {
                        nativeVideoWrapper4.getVideoView().d();
                        NativeVideoView videoView2 = nativeVideoWrapper4.getVideoView();
                        if (videoView2.b() && videoView2.c.isPlaying()) {
                            videoView2.c.pause();
                            videoView2.c.seekTo(0);
                            if (videoView2.getTag() != null) {
                                be beVar3 = (be) videoView2.getTag();
                                beVar3.v.put("didPause", true);
                                beVar3.v.put("seekPosition", 0);
                                beVar3.v.put("didCompleteQ4", true);
                            }
                            videoView2.c.a = 4;
                            videoView2.getPlaybackEventListener().a(4);
                        }
                        if (videoView2.c != null) {
                            videoView2.c.b = 4;
                            return;
                        }
                        return;
                    }
                    return;
                }
                ah.c cVarE = e();
                if (cVarE != null) {
                    cVarE.i();
                }
            } catch (Exception e5) {
                new StringBuilder("Action 2 not valid for asset of type: ").append(akVar.b);
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e5));
            }
        }
    }

    final void a(be beVar, int i) {
        if (this.l) {
            return;
        }
        HashMap map = new HashMap();
        map.put("errorCode", String.valueOf(i));
        map.put(IronSourceConstants.EVENTS_ERROR_REASON, "Video Player Error");
        map.put("url", beVar.b().b());
        a("VideoError", map);
        beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_ERROR, g(beVar));
        this.j.a(17);
    }

    final void a(be beVar) {
        if (this.l) {
            return;
        }
        c(f());
        beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_PAUSE, g(beVar));
        this.j.a(7);
    }

    final void b(be beVar) {
        if (this.l) {
            return;
        }
        d(f());
        beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_RESUME, g(beVar));
        this.j.a(8);
    }

    final void c(be beVar) {
        if (this.l) {
            return;
        }
        beVar.v.put("lastMediaVolume", 0);
        beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_MUTE, g(beVar));
        this.j.a(13);
    }

    final void d(be beVar) {
        if (this.l) {
            return;
        }
        beVar.v.put("lastMediaVolume", 15);
        beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_UNMUTE, g(beVar));
        this.j.a(14);
    }

    final void b(be beVar, int i) {
        if (this.l) {
            return;
        }
        if (i == 0) {
            beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_Q1, g(beVar));
            HashMap map = new HashMap();
            map.put("url", beVar.b().b());
            map.put("isCached", "1");
            a("VideoQ1Completed", map);
            this.j.a(9);
            return;
        }
        if (i == 1) {
            beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_Q2, g(beVar));
            HashMap map2 = new HashMap();
            map2.put("url", beVar.b().b());
            map2.put("isCached", "1");
            a("VideoQ2Completed", map2);
            this.j.a(10);
            return;
        }
        if (i == 2) {
            beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_Q3, g(beVar));
            HashMap map3 = new HashMap();
            map3.put("url", beVar.b().b());
            map3.put("isCached", "1");
            a("VideoQ3Completed", map3);
            this.j.a(11);
            return;
        }
        if (i == 3 && !((Boolean) beVar.v.get("didQ4Fire")).booleanValue()) {
            e(beVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> g(@NonNull be beVar) {
        am amVar = (am) beVar.t;
        HashMap map = new HashMap(4);
        NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) this.B.get();
        if (nativeVideoWrapper != null) {
            double duration = nativeVideoWrapper.getVideoView().getDuration();
            Double.isNaN(duration);
            map.put("$MD", String.valueOf((int) Math.round((duration * 1.0d) / 1000.0d)));
        }
        map.put("[ERRORCODE]", "405");
        long jIntValue = ((Integer) beVar.v.get("seekPosition")).intValue();
        map.put("[CONTENTPLAYHEAD]", String.format(Locale.US, "%02d:%02d:%02d.%03d", Long.valueOf(TimeUnit.MILLISECONDS.toHours(jIntValue)), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(jIntValue) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(jIntValue))), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(jIntValue) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(jIntValue))), Long.valueOf(jIntValue - (TimeUnit.MILLISECONDS.toSeconds(jIntValue) * 1000))));
        map.put("[CACHEBUSTING]", y());
        map.put("[ASSETURI]", beVar.b().b());
        map.put("$TS", String.valueOf(System.currentTimeMillis()));
        map.put("$LTS", String.valueOf(this.a.d.z));
        if (amVar != null) {
            map.put("$STS", String.valueOf(amVar.z));
        }
        return map;
    }

    static /* synthetic */ void a(bd bdVar, View view, final boolean z) {
        final be beVar;
        final NativeVideoView nativeVideoView = (NativeVideoView) view.findViewById(Integer.MAX_VALUE);
        if (nativeVideoView == null || (beVar = (be) nativeVideoView.getTag()) == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.inmobi.ads.bd.3
            @Override // java.lang.Runnable
            public final void run() {
                beVar.v.put("visible", Boolean.valueOf(z));
                if (z && !bd.this.m) {
                    beVar.v.put("lastVisibleTimestamp", Long.valueOf(SystemClock.uptimeMillis()));
                    if (nativeVideoView.i && nativeVideoView.getMediaPlayer() != null) {
                        if (beVar.a()) {
                            nativeVideoView.e();
                        } else {
                            nativeVideoView.d();
                        }
                    }
                    NativeVideoView nativeVideoView2 = nativeVideoView;
                    if (nativeVideoView2.h != null) {
                        nativeVideoView2.h.removeMessages(0);
                    }
                    nativeVideoView2.i = false;
                    bd.a(bd.this, nativeVideoView);
                    bd.a(bd.this, nativeVideoView, beVar);
                    if (1 != nativeVideoView.getState()) {
                        if (2 == nativeVideoView.getState() || 4 == nativeVideoView.getState() || (5 == nativeVideoView.getState() && beVar.C)) {
                            nativeVideoView.start();
                            return;
                        }
                        return;
                    }
                    nativeVideoView.getMediaPlayer().b = 3;
                    return;
                }
                bd.b(bd.this, nativeVideoView);
                NativeVideoView nativeVideoView3 = nativeVideoView;
                int i = beVar.F;
                if (nativeVideoView3.i || 4 == nativeVideoView3.getState()) {
                    return;
                }
                if (nativeVideoView3.h == null) {
                    nativeVideoView3.h = new Handler(Looper.getMainLooper());
                }
                if (i > 0) {
                    nativeVideoView3.i = true;
                    nativeVideoView3.d();
                    nativeVideoView3.h.postDelayed(new Runnable() { // from class: com.inmobi.ads.NativeVideoView.8
                        AnonymousClass8() {
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            NativeVideoView.this.pause();
                        }
                    }, i * 1000);
                    return;
                }
                nativeVideoView3.pause();
            }
        });
    }

    static /* synthetic */ void a(bd bdVar, NativeVideoView nativeVideoView) {
        int videoVolume;
        if (bdVar.b.a != AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE || bdVar.i() || (videoVolume = nativeVideoView.getVideoVolume()) == nativeVideoView.getLastVolume() || !nativeVideoView.isPlaying()) {
            return;
        }
        bdVar.b(videoVolume <= 0);
        nativeVideoView.setLastVolume(videoVolume);
    }

    static /* synthetic */ void a(bd bdVar, NativeVideoView nativeVideoView, be beVar) {
        if (bdVar.b.a != AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE || bdVar.i() || beVar.C || nativeVideoView.isPlaying() || nativeVideoView.getState() != 5) {
            return;
        }
        bdVar.a(nativeVideoView);
    }

    static /* synthetic */ void b(bd bdVar, NativeVideoView nativeVideoView) {
        if (bdVar.b.a != AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE || bdVar.i() || bdVar.m) {
            return;
        }
        bdVar.a(nativeVideoView);
    }
}
