package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.integralads.avid.library.inmobi.session.AvidAdSessionManager;
import com.integralads.avid.library.inmobi.session.AvidManagedVideoAdSession;
import com.integralads.avid.library.inmobi.session.ExternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.video.AvidVideoPlaybackListener;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: IasTrackedNativeV2VideoAd.java */
/* JADX INFO: loaded from: classes2.dex */
class w extends bz {
    private static final String d = "w";

    @NonNull
    private final WeakReference<Activity> e;

    @NonNull
    private final ca f;

    @NonNull
    private final AvidManagedVideoAdSession g;

    @Nullable
    private a h;

    @Nullable
    private WeakReference<View> i;

    @NonNull
    static AvidManagedVideoAdSession a(@NonNull Context context, @NonNull Set<String> set) {
        AvidManagedVideoAdSession avidManagedVideoAdSessionStartAvidManagedVideoAdSession = AvidAdSessionManager.startAvidManagedVideoAdSession(context, new ExternalAvidAdSessionContext("7.2.1", true));
        if (context instanceof Activity) {
            avidManagedVideoAdSessionStartAvidManagedVideoAdSession.registerAdView(null, (Activity) context);
        } else {
            avidManagedVideoAdSessionStartAvidManagedVideoAdSession.registerAdView(null, null);
        }
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            avidManagedVideoAdSessionStartAvidManagedVideoAdSession.injectJavaScriptResource(it.next());
        }
        return avidManagedVideoAdSessionStartAvidManagedVideoAdSession;
    }

    w(@NonNull Activity activity, @NonNull ca caVar, @NonNull bd bdVar, @NonNull AvidManagedVideoAdSession avidManagedVideoAdSession) {
        super(bdVar);
        this.e = new WeakReference<>(activity);
        this.f = caVar;
        this.g = avidManagedVideoAdSession;
    }

    @Override // com.inmobi.ads.ca
    @Nullable
    public final View a() {
        return this.f.a();
    }

    @Override // com.inmobi.ads.ca
    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        return this.f.a(view, viewGroup, z);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ViewGroup viewGroup, NativeVideoWrapper nativeVideoWrapper) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (!childAt.equals(nativeVideoWrapper)) {
                this.g.registerFriendlyObstruction(childAt);
                if (childAt instanceof ViewGroup) {
                    ViewGroup viewGroup2 = (ViewGroup) childAt;
                    if (viewGroup2.getChildCount() > 0) {
                        a(viewGroup2, nativeVideoWrapper);
                    }
                }
            }
        }
    }

    private void g() {
        final NativeVideoWrapper nativeVideoWrapper;
        Activity activity = this.e.get();
        if (activity == null || !(this.a instanceof bd) || (nativeVideoWrapper = (NativeVideoWrapper) this.a.getVideoContainerView()) == null) {
            return;
        }
        this.i = new WeakReference<>(nativeVideoWrapper);
        final View viewB = this.f.b();
        if (nativeVideoWrapper != null && viewB != null && (viewB instanceof ViewGroup)) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.inmobi.ads.w.1
                @Override // java.lang.Runnable
                public final void run() {
                    w.this.a((ViewGroup) viewB, nativeVideoWrapper);
                }
            });
        }
        this.g.registerAdView(this.i.get(), activity);
        if (this.h == null) {
            this.h = new a(activity.getApplicationContext(), this);
            activity.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.h);
        }
        new StringBuilder("Registered ad view with AVID Video AdSession ").append(this.g.hashCode());
    }

    @Override // com.inmobi.ads.ca
    @SuppressLint({"SwitchIntDef"})
    public final void a(int i) {
        int iA;
        try {
            try {
                if (this.g.getAvidVideoPlaybackListener() != null) {
                    StringBuilder sb = new StringBuilder("Sending event (");
                    sb.append(i);
                    sb.append(") to IAS AdSession : ");
                    sb.append(this.g.hashCode());
                    switch (i) {
                        case 0:
                            this.g.getAvidVideoPlaybackListener().recordAdImpressionEvent();
                            break;
                        case 1:
                            this.g.getAvidVideoPlaybackListener().recordAdExpandedChangeEvent();
                            this.g.getAvidVideoPlaybackListener().recordAdEnteredFullscreenEvent();
                            break;
                        case 2:
                            this.g.getAvidVideoPlaybackListener().recordAdExitedFullscreenEvent();
                            this.g.getAvidVideoPlaybackListener().recordAdUserMinimizeEvent();
                            break;
                        case 3:
                            this.g.getAvidVideoPlaybackListener().recordAdUserCloseEvent();
                            break;
                        case 4:
                            this.g.getAvidVideoPlaybackListener().recordAdClickThruEvent();
                            break;
                        case 6:
                            this.g.getAvidVideoPlaybackListener().recordAdStartedEvent();
                            this.g.getAvidVideoPlaybackListener().recordAdVideoStartEvent();
                            break;
                        case 7:
                            this.g.getAvidVideoPlaybackListener().recordAdPausedEvent();
                            this.g.unregisterAdView(this.i == null ? null : this.i.get());
                            break;
                        case 8:
                            this.g.getAvidVideoPlaybackListener().recordAdPlayingEvent();
                            break;
                        case 9:
                            this.g.getAvidVideoPlaybackListener().recordAdVideoFirstQuartileEvent();
                            break;
                        case 10:
                            this.g.getAvidVideoPlaybackListener().recordAdVideoMidpointEvent();
                            break;
                        case 11:
                            this.g.getAvidVideoPlaybackListener().recordAdVideoThirdQuartileEvent();
                            break;
                        case 12:
                            this.g.getAvidVideoPlaybackListener().recordAdCompleteEvent();
                            this.g.getAvidVideoPlaybackListener().recordAdStoppedEvent();
                            break;
                        case 13:
                        case 14:
                            AvidVideoPlaybackListener avidVideoPlaybackListener = this.g.getAvidVideoPlaybackListener();
                            boolean z = true;
                            if (13 == i) {
                                iA = 0;
                            } else {
                                iA = this.h != null ? com.inmobi.commons.core.utilities.b.b.a(this.e.get()) : 1;
                            }
                            avidVideoPlaybackListener.recordAdVolumeChangeEvent(Integer.valueOf(iA));
                            if (this.h != null) {
                                a aVar = this.h;
                                if (13 != i) {
                                    z = false;
                                }
                                aVar.d = z;
                            }
                            break;
                        case 15:
                            this.g.getAvidVideoPlaybackListener().recordAdSkippedEvent();
                            break;
                        case 16:
                            g();
                            break;
                        case 17:
                            this.g.getAvidVideoPlaybackListener().recordAdError("Unknown Player error");
                            break;
                    }
                }
            } catch (Exception e) {
                new StringBuilder("Exception in onAdEvent with message : ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        } finally {
            this.f.a(i);
        }
    }

    @Override // com.inmobi.ads.ca
    public final void a(Context context, int i) {
        this.f.a(context, i);
    }

    @Override // com.inmobi.ads.ca
    public final void e() {
        super.e();
        try {
            try {
                this.e.clear();
                if (this.i != null) {
                    this.i.clear();
                }
                this.h = null;
            } catch (Exception e) {
                new StringBuilder("Exception in destroy with message : ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        } finally {
            this.f.e();
        }
    }

    /* JADX INFO: compiled from: IasTrackedNativeV2VideoAd.java */
    private static final class a extends ContentObserver {
        private Context a;
        private int b;
        private WeakReference<w> c;
        private boolean d;

        a(Context context, w wVar) {
            super(new Handler());
            this.a = context;
            this.b = -1;
            this.d = false;
            this.c = new WeakReference<>(wVar);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            int iA;
            super.onChange(z);
            Context context = this.a;
            if (context == null || (iA = com.inmobi.commons.core.utilities.b.b.a(context)) == this.b) {
                return;
            }
            this.b = iA;
            w wVar = this.c.get();
            if (this.d || wVar == null) {
                return;
            }
            w.a(wVar, iA);
        }
    }

    @Override // com.inmobi.ads.ca
    public final void a(@Nullable View... viewArr) {
        try {
            try {
                if (this.f.c().k.j) {
                    g();
                    try {
                        if (this.g.getAvidDeferredAdSessionListener() != null) {
                            this.g.getAvidDeferredAdSessionListener().recordReadyEvent();
                        }
                    } catch (Exception unused) {
                    }
                    if (this.g.getAvidVideoPlaybackListener() != null) {
                        this.g.getAvidVideoPlaybackListener().recordAdLoadedEvent();
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

    @Override // com.inmobi.ads.ca
    public final void d() {
        try {
            try {
                if (!((bd) this.a).i()) {
                    this.g.unregisterAdView(this.i == null ? null : this.i.get());
                    this.g.endSession();
                    new StringBuilder("Unregistered VideoView to IAS AdSession : ").append(this.g.hashCode());
                }
                Activity activity = this.e.get();
                if (activity != null && this.h != null) {
                    activity.getContentResolver().unregisterContentObserver(this.h);
                }
            } catch (Exception e) {
                new StringBuilder("Exception in stopTrackingForImpression with message : ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        } finally {
            this.f.d();
        }
    }

    static /* synthetic */ void a(w wVar, int i) {
        try {
            if (wVar.g.getAvidVideoPlaybackListener() != null) {
                StringBuilder sb = new StringBuilder("Sending volumeChange to IAS AdSession(");
                sb.append(wVar.g.hashCode());
                sb.append(") with volume - ");
                sb.append(i);
                wVar.g.getAvidVideoPlaybackListener().recordAdVolumeChangeEvent(Integer.valueOf(i));
            }
        } catch (Exception e) {
            new StringBuilder("Exception in onVolumeChange with message : ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }
}
