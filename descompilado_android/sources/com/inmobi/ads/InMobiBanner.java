package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.inmobi.ads.listeners.BannerAdEventListener;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.rendering.RenderView;
import com.ironsource.sdk.constants.Constants;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class InMobiBanner extends RelativeLayout {
    private static final String a = "InMobiBanner";
    private static ConcurrentHashMap<p, WeakReference<BannerAdRequestListener>> q = new ConcurrentHashMap<>(5, 0.9f, 3);

    @Nullable
    private BannerAdListener b;

    @Nullable
    private BannerAdEventListener c;
    private b d;

    @Nullable
    private p e;

    @Nullable
    private p f;

    @Nullable
    private p g;

    @Nullable
    private p h;
    private boolean i;
    private int j;
    private boolean k;

    @Nullable
    private q l;
    private int m;
    private int n;
    private AnimationType o;
    private long p;
    private j r;

    @Nullable
    private WeakReference<Activity> s;

    @Nullable
    private bi t;
    private boolean u;
    private boolean v;
    private final i.b w;

    public enum AnimationType {
        ANIMATION_OFF,
        ROTATE_HORIZONTAL_AXIS,
        ANIMATION_ALPHA,
        ROTATE_VERTICAL_AXIS
    }

    public interface BannerAdListener {
        void onAdDismissed(InMobiBanner inMobiBanner);

        void onAdDisplayed(InMobiBanner inMobiBanner);

        void onAdInteraction(InMobiBanner inMobiBanner, Map<Object, Object> map);

        void onAdLoadFailed(InMobiBanner inMobiBanner, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiBanner inMobiBanner);

        void onAdRewardActionCompleted(InMobiBanner inMobiBanner, Map<Object, Object> map);

        void onUserLeftApplication(InMobiBanner inMobiBanner);
    }

    public interface BannerAdRequestListener {
        void onAdRequestCompleted(InMobiAdRequestStatus inMobiAdRequestStatus, InMobiBanner inMobiBanner);
    }

    private interface a {
        void a();
    }

    public InMobiBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = false;
        this.k = true;
        this.m = 0;
        this.n = 0;
        this.o = AnimationType.ROTATE_HORIZONTAL_AXIS;
        this.p = 0L;
        this.v = true;
        this.w = new i.b() { // from class: com.inmobi.ads.InMobiBanner.4
            @Override // com.inmobi.ads.i.b
            public final void a() {
                try {
                    if (InMobiBanner.this.g == null || !InMobiBanner.this.g.P()) {
                        InMobiBanner.a(InMobiBanner.this, new a() { // from class: com.inmobi.ads.InMobiBanner.4.1
                            @Override // com.inmobi.ads.InMobiBanner.a
                            public final void a() {
                                try {
                                    InMobiBanner.this.a("AR", "");
                                    InMobiBanner.this.d.sendEmptyMessage(1);
                                    InMobiBanner.this.b();
                                } catch (Exception e) {
                                    Logger.a(Logger.InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in scheduling refresh for banner ad");
                                    String unused = InMobiBanner.a;
                                    new StringBuilder("InMobiBanner$5.onSuccess() handler threw unexpected error: ").append(e.getMessage());
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    Logger.a(Logger.InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in loading banner ad");
                    String unused = InMobiBanner.a;
                    new StringBuilder("InMobiBanner$2.onAdLoadSucceeded() handler threw unexpected error: ").append(e.getMessage());
                }
            }

            @Override // com.inmobi.ads.i.b
            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                try {
                    int i = AnonymousClass5.a[inMobiAdRequestStatus.getStatusCode().ordinal()];
                    if (i == 1) {
                        InMobiBanner.this.a("ART", "NetworkNotAvailable");
                    } else if (i == 2 || i == 3) {
                        InMobiBanner.this.a("ART", "LoadInProgress");
                    } else if (i == 4) {
                        InMobiBanner.this.a("ART", "FrequentRequests");
                    } else {
                        InMobiBanner.this.a("AF", "");
                    }
                    if (!InMobiBanner.c()) {
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 2;
                        messageObtain.obj = inMobiAdRequestStatus;
                        InMobiBanner.this.d.sendMessage(messageObtain);
                    }
                    InMobiBanner.this.b();
                } catch (Exception e) {
                    Logger.a(Logger.InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in loading banner ad");
                    String unused = InMobiBanner.a;
                    new StringBuilder("InMobiBanner$2.onAdLoadFailed() handler threw unexpected error: ").append(e.getMessage());
                }
            }

            @Override // com.inmobi.ads.i.b
            public final void d() {
                InMobiBanner.this.d.sendEmptyMessage(3);
            }

            @Override // com.inmobi.ads.i.b
            public final void e() {
                try {
                    InMobiBanner.this.b();
                    InMobiBanner.this.d.sendEmptyMessage(4);
                } catch (Exception e) {
                    Logger.a(Logger.InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in closing banner ad");
                    String unused = InMobiBanner.a;
                    new StringBuilder("InMobiBanner$2.onAdDismissed() handler threw unexpected error: ").append(e.getMessage());
                }
            }

            @Override // com.inmobi.ads.i.b
            public final void a(@NonNull Map<Object, Object> map) {
                InMobiBanner.this.a("AVCL", "");
                Message messageObtain = Message.obtain();
                messageObtain.what = 5;
                messageObtain.obj = map;
                InMobiBanner.this.d.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            public final void f() {
                InMobiBanner.this.d.sendEmptyMessage(6);
            }

            @Override // com.inmobi.ads.i.b
            public final void b(@NonNull Map<Object, Object> map) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 7;
                messageObtain.obj = map;
                InMobiBanner.this.d.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void a(byte[] bArr) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 8;
                messageObtain.obj = bArr;
                InMobiBanner.this.d.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 9;
                messageObtain.obj = inMobiAdRequestStatus;
                InMobiBanner.this.d.sendMessage(messageObtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please initialize the SDK before creating a Banner ad");
            return;
        }
        boolean z = context instanceof Activity;
        if (z) {
            this.s = new WeakReference<>((Activity) context);
        }
        this.d = new b(this);
        String attributeValue = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.inmobi.ads", Constants.PLACEMENT_ID);
        String attributeValue2 = attributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.inmobi.ads", "refreshInterval");
        if (attributeValue != null) {
            long jA = a(attributeValue);
            if (jA != Long.MIN_VALUE) {
                this.t = bi.a(jA, null, com.my.target.ai.a.cW, null);
                this.t.f = z ? InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_OTHER;
                a(context, this.t);
                this.i = true;
            }
        } else {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Placement id value is not supplied in XML layout. Banner creation failed.");
        }
        if (attributeValue2 != null) {
            try {
                setRefreshInterval(Integer.parseInt(attributeValue2.trim()));
            } catch (NumberFormatException unused) {
                Logger.a(Logger.InternalLogLevel.ERROR, a, "Refresh interval value supplied in XML layout is not valid. Falling back to default value.");
            }
        }
    }

    private static long a(@NonNull String str) {
        try {
            StringBuilder sb = new StringBuilder(str.trim());
            if ("plid-".equalsIgnoreCase(sb.substring(0, 5))) {
                return Long.parseLong(sb.substring(5, sb.length()).trim());
            }
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Invalid Placement id: " + str + " Placement id value supplied in XML layout is not valid. Please make sure placement id is in plid-0123456789 format.");
            return Long.MIN_VALUE;
        } catch (NumberFormatException unused) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Invalid Placement id: " + str + " Placement id value supplied in XML layout is not valid. Banner creation failed.");
        } catch (StringIndexOutOfBoundsException unused2) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Invalid Placement id: " + str + " Placement id value supplied in XML layout is not valid. Please make sure placement id is in plid-0123456789 format.");
        }
    }

    public InMobiBanner(Context context, long j) {
        super(context);
        this.i = false;
        this.k = true;
        this.m = 0;
        this.n = 0;
        this.o = AnimationType.ROTATE_HORIZONTAL_AXIS;
        this.p = 0L;
        this.v = true;
        this.w = new i.b() { // from class: com.inmobi.ads.InMobiBanner.4
            @Override // com.inmobi.ads.i.b
            public final void a() {
                try {
                    if (InMobiBanner.this.g == null || !InMobiBanner.this.g.P()) {
                        InMobiBanner.a(InMobiBanner.this, new a() { // from class: com.inmobi.ads.InMobiBanner.4.1
                            @Override // com.inmobi.ads.InMobiBanner.a
                            public final void a() {
                                try {
                                    InMobiBanner.this.a("AR", "");
                                    InMobiBanner.this.d.sendEmptyMessage(1);
                                    InMobiBanner.this.b();
                                } catch (Exception e) {
                                    Logger.a(Logger.InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in scheduling refresh for banner ad");
                                    String unused = InMobiBanner.a;
                                    new StringBuilder("InMobiBanner$5.onSuccess() handler threw unexpected error: ").append(e.getMessage());
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    Logger.a(Logger.InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in loading banner ad");
                    String unused = InMobiBanner.a;
                    new StringBuilder("InMobiBanner$2.onAdLoadSucceeded() handler threw unexpected error: ").append(e.getMessage());
                }
            }

            @Override // com.inmobi.ads.i.b
            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                try {
                    int i = AnonymousClass5.a[inMobiAdRequestStatus.getStatusCode().ordinal()];
                    if (i == 1) {
                        InMobiBanner.this.a("ART", "NetworkNotAvailable");
                    } else if (i == 2 || i == 3) {
                        InMobiBanner.this.a("ART", "LoadInProgress");
                    } else if (i == 4) {
                        InMobiBanner.this.a("ART", "FrequentRequests");
                    } else {
                        InMobiBanner.this.a("AF", "");
                    }
                    if (!InMobiBanner.c()) {
                        Message messageObtain = Message.obtain();
                        messageObtain.what = 2;
                        messageObtain.obj = inMobiAdRequestStatus;
                        InMobiBanner.this.d.sendMessage(messageObtain);
                    }
                    InMobiBanner.this.b();
                } catch (Exception e) {
                    Logger.a(Logger.InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in loading banner ad");
                    String unused = InMobiBanner.a;
                    new StringBuilder("InMobiBanner$2.onAdLoadFailed() handler threw unexpected error: ").append(e.getMessage());
                }
            }

            @Override // com.inmobi.ads.i.b
            public final void d() {
                InMobiBanner.this.d.sendEmptyMessage(3);
            }

            @Override // com.inmobi.ads.i.b
            public final void e() {
                try {
                    InMobiBanner.this.b();
                    InMobiBanner.this.d.sendEmptyMessage(4);
                } catch (Exception e) {
                    Logger.a(Logger.InternalLogLevel.ERROR, InMobiBanner.a, "Encountered unexpected error in closing banner ad");
                    String unused = InMobiBanner.a;
                    new StringBuilder("InMobiBanner$2.onAdDismissed() handler threw unexpected error: ").append(e.getMessage());
                }
            }

            @Override // com.inmobi.ads.i.b
            public final void a(@NonNull Map<Object, Object> map) {
                InMobiBanner.this.a("AVCL", "");
                Message messageObtain = Message.obtain();
                messageObtain.what = 5;
                messageObtain.obj = map;
                InMobiBanner.this.d.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            public final void f() {
                InMobiBanner.this.d.sendEmptyMessage(6);
            }

            @Override // com.inmobi.ads.i.b
            public final void b(@NonNull Map<Object, Object> map) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 7;
                messageObtain.obj = map;
                InMobiBanner.this.d.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void a(byte[] bArr) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 8;
                messageObtain.obj = bArr;
                InMobiBanner.this.d.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 9;
                messageObtain.obj = inMobiAdRequestStatus;
                InMobiBanner.this.d.sendMessage(messageObtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please initialize the SDK before creating a Banner ad");
            return;
        }
        if (context == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Unable to create InMobiBanner ad with null context object.");
            return;
        }
        boolean z = context instanceof Activity;
        if (z) {
            this.s = new WeakReference<>((Activity) context);
        }
        this.d = new b(this);
        this.t = bi.a(j, null, com.my.target.ai.a.cW, null);
        this.t.f = z ? InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_OTHER;
        a(context, this.t);
        this.i = true;
    }

    private boolean b(boolean z) {
        if (!this.i) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiBanner is not initialized. Ignoring your call");
            return false;
        }
        if (!z || this.c != null) {
            return true;
        }
        Logger.a(Logger.InternalLogLevel.ERROR, a, "Listener supplied is null, Ignoring your call.");
        return false;
    }

    public final void getSignals() {
        if (b(true)) {
            setEnableAutoRefresh(false);
            a("ARR", "");
            p pVar = this.h;
            if (pVar != null) {
                pVar.A = getFrameSizeString();
                p pVar2 = this.h;
                pVar2.y = false;
                pVar2.o();
            }
        }
    }

    public final void load(byte[] bArr) {
        boolean z = !b(false);
        p pVar = this.h;
        if (pVar == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please make sure getSignals is called before calling Load");
        } else {
            if (z) {
                return;
            }
            pVar.w = false;
            pVar.a(bArr);
        }
    }

    public final void load() {
        if (b(false)) {
            a(false);
        }
    }

    final void a(final boolean z) {
        try {
            if (!com.inmobi.commons.a.a.a()) {
                Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiBanner is not initialized. Ignoring InMobiBanner.load()");
                return;
            }
            if (this.i) {
                a("ARR", "");
                if (this.g != null && this.g.P()) {
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 2;
                    messageObtain.obj = new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.AD_ACTIVE);
                    a("ART", "LoadInProgress");
                    this.d.sendMessage(messageObtain);
                    this.g.b("AdActive");
                    Logger.a(Logger.InternalLogLevel.ERROR, a, "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad.");
                    return;
                }
                if (!a()) {
                    if (getLayoutParams() == null) {
                        Logger.a(Logger.InternalLogLevel.ERROR, a, "The layout params of the banner must be set before calling load or call setBannerSize(int widthInDp, int heightInDp) before load");
                        this.w.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.REQUEST_INVALID));
                        return;
                    }
                    if (getLayoutParams().width != -2 && getLayoutParams().height != -2) {
                        g();
                    }
                    Logger.a(Logger.InternalLogLevel.ERROR, a, "The height or width of a Banner ad can't be WRAP_CONTENT or call setBannerSize(int widthInDp, int heightInDp) before load");
                    this.w.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.REQUEST_INVALID));
                    return;
                }
                if (!a()) {
                    new Handler().postDelayed(new Runnable() { // from class: com.inmobi.ads.InMobiBanner.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                if (InMobiBanner.this.a()) {
                                    InMobiBanner.this.h();
                                    if (!InMobiBanner.this.f() || InMobiBanner.this.h == null) {
                                        return;
                                    }
                                    InMobiBanner.this.h.A = InMobiBanner.this.getFrameSizeString();
                                    InMobiBanner.this.h.b(z);
                                    return;
                                }
                                Logger.a(Logger.InternalLogLevel.ERROR, InMobiBanner.a, "The height or width of the banner can not be determined");
                                i.b bVar = InMobiBanner.this.w;
                                p unused = InMobiBanner.this.h;
                                bVar.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR));
                            } catch (Exception e) {
                                Logger.a(Logger.InternalLogLevel.ERROR, InMobiBanner.a, "SDK encountered unexpected error while loading an ad");
                                String unused2 = InMobiBanner.a;
                                new StringBuilder("InMobiBanner$4.run() threw unexpected error: ").append(e.getMessage());
                            }
                        }
                    }, 200L);
                    return;
                }
                h();
                if (!f() || this.h == null) {
                    return;
                }
                this.h.A = getFrameSizeString();
                this.h.b(z);
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Unable to load ad; SDK encountered an unexpected error");
            new StringBuilder("Load failed with unexpected error: ").append(e.getMessage());
        }
    }

    public final void load(Context context) {
        if (b(false)) {
            boolean z = context instanceof Activity;
            if (z) {
                this.s = new WeakReference<>((Activity) context);
            } else {
                this.s = null;
            }
            bi biVar = this.t;
            if (biVar != null) {
                biVar.f = z ? InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_OTHER;
                a(context, this.t);
            }
            a(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMonetizationContext(InMobiAdRequest.MonetizationContext monetizationContext) {
        p pVar = this.e;
        if (pVar == null || this.f == null) {
            return;
        }
        pVar.a(monetizationContext);
        this.f.a(monetizationContext);
    }

    public static void requestAd(Context context, InMobiAdRequest inMobiAdRequest, BannerAdRequestListener bannerAdRequestListener) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please initialize the SDK before calling requestAd. Ignoring request");
            return;
        }
        if (context == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please supply a non null Context. Aborting request");
            return;
        }
        if (inMobiAdRequest == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please supply a non  null InMobiAdRequest. Ignoring request");
            return;
        }
        if (bannerAdRequestListener == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please supply a non null BannerAdRequestListener. Ignoring request");
            return;
        }
        if (inMobiAdRequest.c <= 0 && inMobiAdRequest.d <= 0) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please provide positive width and height for banner. Ignoring request");
            return;
        }
        i.d dVar = new i.d() { // from class: com.inmobi.ads.InMobiBanner.2
            @Override // com.inmobi.ads.i.d
            public final void a(@NonNull i iVar) {
                if (iVar instanceof p) {
                    try {
                        WeakReference weakReference = (WeakReference) InMobiBanner.q.get(iVar);
                        if (weakReference != null) {
                            InMobiBanner.q.remove(iVar);
                            BannerAdRequestListener bannerAdRequestListener2 = (BannerAdRequestListener) weakReference.get();
                            if (bannerAdRequestListener2 != null) {
                                InMobiBanner inMobiBanner = new InMobiBanner(iVar.a(), iVar.d);
                                inMobiBanner.setExtras(iVar.f);
                                inMobiBanner.setKeywords(iVar.e);
                                inMobiBanner.setMonetizationContext(iVar.l());
                                bannerAdRequestListener2.onAdRequestCompleted(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.NO_ERROR), inMobiBanner);
                            }
                        }
                    } catch (Exception e) {
                        String unused = InMobiBanner.a;
                        new StringBuilder("SDK encountered unexpected error in onAdPrefetchSucceeded ").append(e.getMessage());
                    }
                }
            }

            @Override // com.inmobi.ads.i.d
            public final void a(@NonNull i iVar, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                WeakReference weakReference;
                try {
                    if (!(iVar instanceof p) || (weakReference = (WeakReference) InMobiBanner.q.get(iVar)) == null) {
                        return;
                    }
                    InMobiBanner.q.remove(iVar);
                    BannerAdRequestListener bannerAdRequestListener2 = (BannerAdRequestListener) weakReference.get();
                    if (bannerAdRequestListener2 != null) {
                        bannerAdRequestListener2.onAdRequestCompleted(inMobiAdRequestStatus, null);
                    }
                } catch (Exception e) {
                    String unused = InMobiBanner.a;
                    new StringBuilder("SDK encountered unexpected error in onAdPrefetchFailed ").append(e.getMessage());
                }
            }
        };
        try {
            bi biVarA = bi.a(inMobiAdRequest.a, inMobiAdRequest.f, com.my.target.ai.a.cW, inMobiAdRequest.e);
            biVarA.f = inMobiAdRequest.b;
            p pVarA = p.a(context.getApplicationContext(), biVarA, (i.b) null, 2);
            pVarA.f = inMobiAdRequest.f;
            pVarA.a(inMobiAdRequest.b);
            pVarA.e = inMobiAdRequest.e;
            pVarA.A = inMobiAdRequest.c + "x" + inMobiAdRequest.d;
            pVarA.q = dVar;
            pVarA.n = true;
            q.put(pVarA, new WeakReference<>(bannerAdRequestListener));
            pVarA.q();
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in requestAd").append(e.getMessage());
        }
    }

    @VisibleForTesting
    final void setTrcCollector(j jVar) {
        this.r = jVar;
    }

    @NonNull
    private j getAdUnitTRCCollector() {
        if (this.r == null) {
            this.r = new k(this.h);
        }
        return this.r;
    }

    @VisibleForTesting
    final void a(String str, String str2) {
        getAdUnitTRCCollector().a(this.w, str, str2);
    }

    public final JSONObject getAdMetaInfo() {
        p pVar;
        if (!this.i || (pVar = this.g) == null) {
            return new JSONObject();
        }
        return pVar.i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean f() {
        p pVar = this.h;
        if (pVar == null) {
            return false;
        }
        if (this.p != 0) {
            int i = pVar.g.c;
            if (SystemClock.elapsedRealtime() - this.p < i * 1000) {
                this.h.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.EARLY_REFRESH_REQUEST).setCustomMessage("Ad cannot be refreshed before " + i + " seconds"), false);
                Logger.a(Logger.InternalLogLevel.ERROR, a, "Ad cannot be refreshed before " + i + " seconds (Placement Id = " + this.h.d + ")");
                return false;
            }
        }
        this.p = SystemClock.elapsedRealtime();
        return true;
    }

    public final void setExtras(Map<String, String> map) {
        if (!this.i || this.t == null) {
            return;
        }
        this.e.f = map;
        this.f.f = map;
    }

    public final void setKeywords(String str) {
        if (!this.i || this.t == null) {
            return;
        }
        this.e.e = str;
        this.f.e = str;
    }

    @Deprecated
    public final void setListener(BannerAdListener bannerAdListener) {
        if (bannerAdListener == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please pass a non-null listener to the banner.");
        } else {
            this.b = bannerAdListener;
        }
    }

    public final void setListener(BannerAdEventListener bannerAdEventListener) {
        if (bannerAdEventListener == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please pass a non-null listener to the banner.");
        } else {
            this.c = bannerAdEventListener;
        }
    }

    public final void setEnableAutoRefresh(boolean z) {
        try {
            if (!this.i || this.k == z) {
                return;
            }
            this.k = z;
            if (this.k) {
                b();
            } else {
                h();
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Unable to setup auto-refresh on the ad; SDK encountered an unexpected error");
            new StringBuilder("Setting up auto-refresh failed with unexpected error: ").append(e.getMessage());
        }
    }

    public final void setRefreshInterval(int i) {
        try {
            if (!this.i || this.h == null) {
                return;
            }
            if (i < this.h.g.c) {
                i = this.h.g.c;
            }
            this.j = i;
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Unable to set refresh interval for the ad; SDK encountered an unexpected error");
            new StringBuilder("Setting refresh interval failed with unexpected error: ").append(e.getMessage());
        }
    }

    public final void setAnimationType(AnimationType animationType) {
        if (this.i) {
            this.o = animationType;
        }
    }

    public final void disableHardwareAcceleration() {
        this.u = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        try {
            super.onAttachedToWindow();
            if (this.i) {
                g();
                if (!a()) {
                    getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.inmobi.ads.InMobiBanner.3
                        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                        public final void onGlobalLayout() {
                            try {
                                InMobiBanner.this.m = com.inmobi.commons.core.utilities.b.c.b(InMobiBanner.this.getMeasuredWidth());
                                InMobiBanner.this.n = com.inmobi.commons.core.utilities.b.c.b(InMobiBanner.this.getMeasuredHeight());
                                if (InMobiBanner.this.a()) {
                                    if (Build.VERSION.SDK_INT >= 16) {
                                        InMobiBanner.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                    } else {
                                        InMobiBanner.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                                    }
                                }
                            } catch (Exception e) {
                                Logger.a(Logger.InternalLogLevel.ERROR, InMobiBanner.a, "InMobiBanner$1.onGlobalLayout() handler threw unexpected error");
                                String unused = InMobiBanner.a;
                                new StringBuilder("InMobiBanner$1.onGlobalLayout() handler threw unexpected error: ").append(e.getMessage());
                            }
                        }
                    });
                }
                b();
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiBanner#onAttachedToWindow() handler threw unexpected error");
            new StringBuilder("InMobiBanner#onAttachedToWindow() handler threw unexpected error: ").append(e.getMessage());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
            if (this.i) {
                h();
            }
            if (this.e != null) {
                this.e.S();
            }
            if (this.f != null) {
                this.f.S();
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiBanner.onDetachedFromWindow() handler threw unexpected error");
            new StringBuilder("InMobiBanner.onDetachedFromWindow() handler threw unexpected error: ").append(e.getMessage());
        }
    }

    private void g() {
        if (getLayoutParams() != null) {
            this.m = com.inmobi.commons.core.utilities.b.c.b(getLayoutParams().width);
            this.n = com.inmobi.commons.core.utilities.b.c.b(getLayoutParams().height);
        }
    }

    public final void setBannerSize(int i, int i2) {
        if (this.i) {
            this.m = i;
            this.n = i2;
        }
    }

    final boolean a() {
        return this.m > 0 && this.n > 0;
    }

    final String getFrameSizeString() {
        return this.m + "x" + this.n;
    }

    @Override // android.view.View
    protected final void onVisibilityChanged(@NonNull View view, int i) {
        try {
            super.onVisibilityChanged(view, i);
            if (this.i) {
                if (i == 0) {
                    b();
                } else {
                    h();
                }
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiBanner$1.onVisibilityChanged() handler threw unexpected error");
            new StringBuilder("InMobiBanner$1.onVisibilityChanged() handler threw unexpected error: ").append(e.getMessage());
        }
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
            if (this.i) {
                if (z) {
                    b();
                } else {
                    h();
                }
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiBanner$1.onWindowFocusChanged() handler threw unexpected error");
            new StringBuilder("InMobiBanner$1.onWindowFocusChanged() handler threw unexpected error: ").append(e.getMessage());
        }
    }

    private void a(Context context, @NonNull bi biVar) {
        p pVar = this.e;
        if (pVar == null || this.f == null) {
            this.e = p.a(context, biVar, this.w, 0);
            this.f = p.a(context, biVar, this.w, 0);
            this.h = this.e;
            this.j = this.h.g.d;
        } else {
            pVar.a(context);
            this.f.a(context);
            boolean z = context instanceof Activity;
            this.e.a(z ? InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_OTHER);
            this.f.a(z ? InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_OTHER);
        }
        this.l = new q(this);
        p pVar2 = this.e;
        pVar2.n = false;
        this.f.n = false;
        if (this.u) {
            pVar2.O();
            this.f.O();
        }
    }

    @VisibleForTesting
    final void b() {
        q qVar;
        if (isShown() && hasWindowFocus() && this.h != null) {
            q qVar2 = this.l;
            if (qVar2 != null) {
                qVar2.removeMessages(1);
            }
            if (this.h.a == 1 || this.h.a == 2) {
                return;
            }
            p pVar = this.g;
            if ((pVar == null || pVar.a != 8) && this.k && (qVar = this.l) != null) {
                qVar.sendEmptyMessageDelayed(1, this.j * 1000);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        q qVar = this.l;
        if (qVar != null) {
            qVar.removeMessages(1);
        }
    }

    @VisibleForTesting
    final void setAnimateAndDisplayAd(boolean z) {
        this.v = z;
    }

    public final void resume() {
        try {
            if (this.g == null || this.s != null) {
                return;
            }
            this.g.R();
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Could not resume ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in resuming ad; ").append(e.getMessage());
        }
    }

    public final void pause() {
        try {
            if (this.g == null || this.s != null) {
                return;
            }
            this.g.Q();
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Could not pause ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in pausing ad; ").append(e.getMessage());
        }
    }

    public final String getCreativeId() {
        p pVar;
        return (!this.i || (pVar = this.g) == null) ? "" : pVar.x;
    }

    @VisibleForTesting
    static final class b extends Handler {
        private WeakReference<InMobiBanner> a;

        b(InMobiBanner inMobiBanner) {
            super(Looper.getMainLooper());
            this.a = new WeakReference<>(inMobiBanner);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Map<Object, Object> map;
            InMobiBanner inMobiBanner = this.a.get();
            if (inMobiBanner != null) {
                try {
                    switch (message.what) {
                        case 1:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdLoadSucceeded(inMobiBanner);
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdLoadSucceeded(inMobiBanner);
                            }
                            break;
                        case 2:
                            InMobiAdRequestStatus inMobiAdRequestStatus = (InMobiAdRequestStatus) message.obj;
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdLoadFailed(inMobiBanner, inMobiAdRequestStatus);
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdLoadFailed(inMobiBanner, inMobiAdRequestStatus);
                            }
                            break;
                        case 3:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdDisplayed(inMobiBanner);
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdDisplayed(inMobiBanner);
                            }
                            break;
                        case 4:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdDismissed(inMobiBanner);
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdDismissed(inMobiBanner);
                            }
                            break;
                        case 5:
                            map = message.obj != null ? (Map) message.obj : null;
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onAdClicked(inMobiBanner, map);
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdInteraction(inMobiBanner, map);
                            }
                            break;
                        case 6:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onUserLeftApplication(inMobiBanner);
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onUserLeftApplication(inMobiBanner);
                            }
                            break;
                        case 7:
                            map = message.obj != null ? (Map) message.obj : null;
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onRewardsUnlocked(inMobiBanner, map);
                            } else if (inMobiBanner.b != null) {
                                inMobiBanner.b.onAdRewardActionCompleted(inMobiBanner, map);
                            }
                            break;
                        case 8:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onRequestPayloadCreated((byte[]) message.obj);
                            }
                            break;
                        case 9:
                            if (inMobiBanner.c != null) {
                                inMobiBanner.c.onRequestPayloadCreationFailed((InMobiAdRequestStatus) message.obj);
                            }
                            break;
                        default:
                            String unused = InMobiBanner.a;
                            break;
                    }
                } catch (Exception e) {
                    Logger.a(Logger.InternalLogLevel.ERROR, InMobiBanner.a, "Publisher handler caused unexpected error");
                    String unused2 = InMobiBanner.a;
                    new StringBuilder("Callback threw unexpected error: ").append(e.getMessage());
                }
            }
        }
    }

    @VisibleForTesting
    static boolean c() {
        return Message.obtain() == null;
    }

    /* JADX INFO: renamed from: com.inmobi.ads.InMobiBanner$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a = new int[InMobiAdRequestStatus.StatusCode.values().length];

        static {
            try {
                a[InMobiAdRequestStatus.StatusCode.NETWORK_UNREACHABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[InMobiAdRequestStatus.StatusCode.REQUEST_PENDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[InMobiAdRequestStatus.StatusCode.AD_ACTIVE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[InMobiAdRequestStatus.StatusCode.EARLY_REFRESH_REQUEST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @VisibleForTesting
    final void setClientCallbackHandler(b bVar) {
        this.d = bVar;
    }

    static /* synthetic */ void a(InMobiBanner inMobiBanner, a aVar) {
        RenderView renderView;
        p pVar = inMobiBanner.g;
        if (pVar == null) {
            inMobiBanner.g = inMobiBanner.e;
            inMobiBanner.h = inMobiBanner.f;
        } else if (pVar.equals(inMobiBanner.e)) {
            inMobiBanner.g = inMobiBanner.f;
            inMobiBanner.h = inMobiBanner.e;
        } else if (inMobiBanner.g.equals(inMobiBanner.f)) {
            inMobiBanner.g = inMobiBanner.e;
            inMobiBanner.h = inMobiBanner.f;
        }
        try {
            if (inMobiBanner.v) {
                AnimationType animationType = inMobiBanner.o;
                float width = inMobiBanner.getWidth();
                float height = inMobiBanner.getHeight();
                Animation animation = null;
                if (animationType == AnimationType.ANIMATION_ALPHA) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.5f);
                    alphaAnimation.setDuration(1000L);
                    alphaAnimation.setFillAfter(false);
                    alphaAnimation.setInterpolator(new DecelerateInterpolator());
                    animation = alphaAnimation;
                } else if (animationType == AnimationType.ROTATE_HORIZONTAL_AXIS) {
                    m.a aVar2 = new m.a(width / 2.0f, height / 2.0f);
                    aVar2.setDuration(500L);
                    aVar2.setFillAfter(false);
                    aVar2.setInterpolator(new AccelerateInterpolator());
                    animation = aVar2;
                } else if (animationType == AnimationType.ROTATE_VERTICAL_AXIS) {
                    m.b bVar = new m.b(width / 2.0f, height / 2.0f);
                    bVar.setDuration(500L);
                    bVar.setFillAfter(false);
                    bVar.setInterpolator(new AccelerateInterpolator());
                    animation = bVar;
                }
                if (inMobiBanner.getContext() != null && inMobiBanner.g != null && (renderView = (RenderView) inMobiBanner.g.j()) != null) {
                    ca viewableAd = renderView.getViewableAd();
                    if (inMobiBanner.g.z) {
                        renderView.a();
                    }
                    ViewGroup viewGroup = (ViewGroup) renderView.getParent();
                    ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                    View viewA = viewableAd.a();
                    viewableAd.a(new View[0]);
                    if (inMobiBanner.h != null) {
                        inMobiBanner.h.Q();
                    }
                    if (viewGroup == null) {
                        inMobiBanner.addView(viewA, layoutParams);
                    } else {
                        viewGroup.removeAllViews();
                        viewGroup.addView(viewA, layoutParams);
                    }
                    inMobiBanner.h.v();
                }
                if (animation != null) {
                    inMobiBanner.startAnimation(animation);
                }
            }
            aVar.a();
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Unexpected error while displaying Banner Ad.");
            new StringBuilder("Unexpected error while displaying Banner Ad : ").append(e.getMessage());
        }
    }
}
