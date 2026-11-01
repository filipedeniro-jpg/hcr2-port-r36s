package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.internal.AnalyticsEvents;
import com.inmobi.ads.listeners.NativeAdEventListener;
import com.inmobi.ads.listeners.VideoEventListener;
import com.inmobi.commons.core.utilities.Logger;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class InMobiNative {
    private static final String a = "InMobiNative";
    private static ConcurrentHashMap<aj, WeakReference<NativeAdRequestListener>> j = new ConcurrentHashMap<>(5, 0.9f, 3);
    private a b;
    private NativeAdListener c;
    private NativeAdEventListener d;

    @Nullable
    private VideoEventListener e;
    private aj f;
    private String g;
    private Map<String, String> h;
    private long i;
    private Downloader k;
    private WeakReference<View> l;
    private boolean m;
    private boolean n;
    private WeakReference<Context> o;
    private LockScreenListener p;
    private j q;
    private final i.b r;

    public interface LockScreenListener {
        void onActionRequired(InMobiNative inMobiNative);
    }

    public interface NativeAdListener {
        void onAdClicked(@NonNull InMobiNative inMobiNative);

        void onAdFullScreenDismissed(InMobiNative inMobiNative);

        void onAdFullScreenDisplayed(InMobiNative inMobiNative);

        void onAdFullScreenWillDisplay(InMobiNative inMobiNative);

        void onAdImpressed(@NonNull InMobiNative inMobiNative);

        void onAdLoadFailed(@NonNull InMobiNative inMobiNative, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(@NonNull InMobiNative inMobiNative);

        void onAdStatusChanged(@NonNull InMobiNative inMobiNative);

        void onMediaPlaybackComplete(@NonNull InMobiNative inMobiNative);

        void onUserSkippedMedia(@NonNull InMobiNative inMobiNative);

        void onUserWillLeaveApplication(InMobiNative inMobiNative);
    }

    public interface NativeAdRequestListener {
        void onAdRequestCompleted(InMobiAdRequestStatus inMobiAdRequestStatus, InMobiNative inMobiNative);
    }

    /* synthetic */ InMobiNative(Context context, bi biVar, byte b) {
        this(context, biVar);
    }

    @Deprecated
    public InMobiNative(Context context, long j2, NativeAdListener nativeAdListener) {
        this.n = true;
        this.r = new i.b() { // from class: com.inmobi.ads.InMobiNative.2
            @Override // com.inmobi.ads.i.b
            public final void a() {
                InMobiNative.this.a("AR", "");
                InMobiNative.this.b.sendEmptyMessage(1);
            }

            @Override // com.inmobi.ads.i.b
            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (AnonymousClass3.a[inMobiAdRequestStatus.getStatusCode().ordinal()]) {
                    case 1:
                        InMobiNative.this.a("ART", "NetworkNotAvailable");
                        break;
                    case 2:
                    case 3:
                        InMobiNative.this.a("ART", "LoadInProgress");
                        break;
                    case 4:
                        InMobiNative.this.a("ART", "FrequentRequests");
                        break;
                    case 5:
                        InMobiNative.this.a("ART", "MissingRequiredDependencies");
                        break;
                    case 6:
                        InMobiNative.this.a("ART", "ReloadNotPermitted");
                        break;
                    default:
                        InMobiNative.this.a("AF", "");
                        break;
                }
                if (InMobiNative.c()) {
                    return;
                }
                Message messageObtain = Message.obtain();
                messageObtain.what = 2;
                messageObtain.obj = inMobiAdRequestStatus;
                InMobiNative.this.b.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            public final void b() {
                String unused = InMobiNative.a;
            }

            @Override // com.inmobi.ads.i.b
            public final void c() {
                InMobiNative.this.b.sendEmptyMessage(3);
            }

            @Override // com.inmobi.ads.i.b
            public final void d() {
                InMobiNative.this.a("AVE", "");
                InMobiNative.this.b.sendEmptyMessage(4);
            }

            @Override // com.inmobi.ads.i.b
            public final void e() {
                InMobiNative.this.a("AVCO", "");
                InMobiNative.this.b.sendEmptyMessage(5);
            }

            @Override // com.inmobi.ads.i.b
            public final void a(@NonNull Map<Object, Object> map) {
                InMobiNative.this.a("AVCL", "");
                InMobiNative.this.b.sendEmptyMessage(7);
            }

            @Override // com.inmobi.ads.i.b
            public final void f() {
                InMobiNative.this.b.sendEmptyMessage(8);
            }

            @Override // com.inmobi.ads.i.b
            public final void g() {
                InMobiNative.this.b.sendEmptyMessage(6);
            }

            @Override // com.inmobi.ads.i.b
            public final void h() {
                InMobiNative.this.b.sendEmptyMessage(9);
            }

            @Override // com.inmobi.ads.i.b
            public final void j() {
                InMobiNative.this.b.sendEmptyMessage(11);
            }

            @Override // com.inmobi.ads.i.b
            final void a(byte[] bArr) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 12;
                messageObtain.obj = bArr;
                InMobiNative.this.b.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 13;
                messageObtain.obj = inMobiAdRequestStatus;
                InMobiNative.this.b.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void b(boolean z) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 14;
                messageObtain.obj = Boolean.valueOf(z);
                InMobiNative.this.b.sendMessage(messageObtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please initialize the SDK before creating a Native ad");
            return;
        }
        if (context == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Context is null, Native ad cannot be created.");
            return;
        }
        if (nativeAdListener == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Listener supplied is null, the Native ad cannot be created.");
            return;
        }
        this.i = j2;
        this.o = new WeakReference<>(context);
        this.c = nativeAdListener;
        this.k = new Downloader();
        this.b = new a(this);
    }

    public InMobiNative(Context context, long j2, NativeAdEventListener nativeAdEventListener) {
        this.n = true;
        this.r = new i.b() { // from class: com.inmobi.ads.InMobiNative.2
            @Override // com.inmobi.ads.i.b
            public final void a() {
                InMobiNative.this.a("AR", "");
                InMobiNative.this.b.sendEmptyMessage(1);
            }

            @Override // com.inmobi.ads.i.b
            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (AnonymousClass3.a[inMobiAdRequestStatus.getStatusCode().ordinal()]) {
                    case 1:
                        InMobiNative.this.a("ART", "NetworkNotAvailable");
                        break;
                    case 2:
                    case 3:
                        InMobiNative.this.a("ART", "LoadInProgress");
                        break;
                    case 4:
                        InMobiNative.this.a("ART", "FrequentRequests");
                        break;
                    case 5:
                        InMobiNative.this.a("ART", "MissingRequiredDependencies");
                        break;
                    case 6:
                        InMobiNative.this.a("ART", "ReloadNotPermitted");
                        break;
                    default:
                        InMobiNative.this.a("AF", "");
                        break;
                }
                if (InMobiNative.c()) {
                    return;
                }
                Message messageObtain = Message.obtain();
                messageObtain.what = 2;
                messageObtain.obj = inMobiAdRequestStatus;
                InMobiNative.this.b.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            public final void b() {
                String unused = InMobiNative.a;
            }

            @Override // com.inmobi.ads.i.b
            public final void c() {
                InMobiNative.this.b.sendEmptyMessage(3);
            }

            @Override // com.inmobi.ads.i.b
            public final void d() {
                InMobiNative.this.a("AVE", "");
                InMobiNative.this.b.sendEmptyMessage(4);
            }

            @Override // com.inmobi.ads.i.b
            public final void e() {
                InMobiNative.this.a("AVCO", "");
                InMobiNative.this.b.sendEmptyMessage(5);
            }

            @Override // com.inmobi.ads.i.b
            public final void a(@NonNull Map<Object, Object> map) {
                InMobiNative.this.a("AVCL", "");
                InMobiNative.this.b.sendEmptyMessage(7);
            }

            @Override // com.inmobi.ads.i.b
            public final void f() {
                InMobiNative.this.b.sendEmptyMessage(8);
            }

            @Override // com.inmobi.ads.i.b
            public final void g() {
                InMobiNative.this.b.sendEmptyMessage(6);
            }

            @Override // com.inmobi.ads.i.b
            public final void h() {
                InMobiNative.this.b.sendEmptyMessage(9);
            }

            @Override // com.inmobi.ads.i.b
            public final void j() {
                InMobiNative.this.b.sendEmptyMessage(11);
            }

            @Override // com.inmobi.ads.i.b
            final void a(byte[] bArr) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 12;
                messageObtain.obj = bArr;
                InMobiNative.this.b.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 13;
                messageObtain.obj = inMobiAdRequestStatus;
                InMobiNative.this.b.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void b(boolean z) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 14;
                messageObtain.obj = Boolean.valueOf(z);
                InMobiNative.this.b.sendMessage(messageObtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please initialize the SDK before creating a Native ad");
            return;
        }
        if (context == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Context is null, Native ad cannot be created.");
            return;
        }
        if (nativeAdEventListener == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Listener supplied is null, the Native ad cannot be created.");
            return;
        }
        this.i = j2;
        this.o = new WeakReference<>(context);
        this.d = nativeAdEventListener;
        this.k = new Downloader();
        this.b = new a(this);
    }

    private InMobiNative(Context context, bi biVar) {
        this.n = true;
        this.r = new i.b() { // from class: com.inmobi.ads.InMobiNative.2
            @Override // com.inmobi.ads.i.b
            public final void a() {
                InMobiNative.this.a("AR", "");
                InMobiNative.this.b.sendEmptyMessage(1);
            }

            @Override // com.inmobi.ads.i.b
            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (AnonymousClass3.a[inMobiAdRequestStatus.getStatusCode().ordinal()]) {
                    case 1:
                        InMobiNative.this.a("ART", "NetworkNotAvailable");
                        break;
                    case 2:
                    case 3:
                        InMobiNative.this.a("ART", "LoadInProgress");
                        break;
                    case 4:
                        InMobiNative.this.a("ART", "FrequentRequests");
                        break;
                    case 5:
                        InMobiNative.this.a("ART", "MissingRequiredDependencies");
                        break;
                    case 6:
                        InMobiNative.this.a("ART", "ReloadNotPermitted");
                        break;
                    default:
                        InMobiNative.this.a("AF", "");
                        break;
                }
                if (InMobiNative.c()) {
                    return;
                }
                Message messageObtain = Message.obtain();
                messageObtain.what = 2;
                messageObtain.obj = inMobiAdRequestStatus;
                InMobiNative.this.b.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            public final void b() {
                String unused = InMobiNative.a;
            }

            @Override // com.inmobi.ads.i.b
            public final void c() {
                InMobiNative.this.b.sendEmptyMessage(3);
            }

            @Override // com.inmobi.ads.i.b
            public final void d() {
                InMobiNative.this.a("AVE", "");
                InMobiNative.this.b.sendEmptyMessage(4);
            }

            @Override // com.inmobi.ads.i.b
            public final void e() {
                InMobiNative.this.a("AVCO", "");
                InMobiNative.this.b.sendEmptyMessage(5);
            }

            @Override // com.inmobi.ads.i.b
            public final void a(@NonNull Map<Object, Object> map) {
                InMobiNative.this.a("AVCL", "");
                InMobiNative.this.b.sendEmptyMessage(7);
            }

            @Override // com.inmobi.ads.i.b
            public final void f() {
                InMobiNative.this.b.sendEmptyMessage(8);
            }

            @Override // com.inmobi.ads.i.b
            public final void g() {
                InMobiNative.this.b.sendEmptyMessage(6);
            }

            @Override // com.inmobi.ads.i.b
            public final void h() {
                InMobiNative.this.b.sendEmptyMessage(9);
            }

            @Override // com.inmobi.ads.i.b
            public final void j() {
                InMobiNative.this.b.sendEmptyMessage(11);
            }

            @Override // com.inmobi.ads.i.b
            final void a(byte[] bArr) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 12;
                messageObtain.obj = bArr;
                InMobiNative.this.b.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 13;
                messageObtain.obj = inMobiAdRequestStatus;
                InMobiNative.this.b.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void b(boolean z) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 14;
                messageObtain.obj = Boolean.valueOf(z);
                InMobiNative.this.b.sendMessage(messageObtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please initialize the SDK before creating an InMobiNative ad");
            return;
        }
        if (context == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Context is null, Native ad cannot be created.");
            return;
        }
        this.f = aj.a(context, biVar, this.r, 0);
        this.i = biVar.a;
        this.o = new WeakReference<>(context);
        this.b = new a(this);
    }

    @Deprecated
    public final void setNativeAdListener(NativeAdListener nativeAdListener) {
        this.c = nativeAdListener;
    }

    public final void setListener(NativeAdEventListener nativeAdEventListener) {
        if (nativeAdEventListener == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please pass a non-null listener to the native.");
        } else {
            this.d = nativeAdEventListener;
        }
    }

    public final void setVideoEventListener(VideoEventListener videoEventListener) {
        if (videoEventListener == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please pass a non-null listener to the native.");
        } else {
            this.e = videoEventListener;
        }
    }

    private boolean a(boolean z) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized, your call is ignored.");
            return false;
        }
        if (!z ? this.d == null : !(this.r != null || this.d != null)) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Listener supplied is null, your call is ignored.");
            return false;
        }
        WeakReference<Context> weakReference = this.o;
        if (weakReference != null && weakReference.get() != null) {
            return true;
        }
        Logger.a(Logger.InternalLogLevel.ERROR, a, "Context supplied is null, your call is ignored.");
        return false;
    }

    public final void getSignals() {
        if (a(false)) {
            d();
            a("ARR", "");
            aj ajVar = this.f;
            if (ajVar != null) {
                ajVar.o();
            }
        }
    }

    public final void load(byte[] bArr) {
        if (a(false)) {
            aj ajVar = this.f;
            if (ajVar == null) {
                Logger.a(Logger.InternalLogLevel.ERROR, a, "Either getSignals() is not called or InMobiNative is not initialized, your call is ignored.");
            } else {
                ajVar.a(bArr);
            }
        }
    }

    @VisibleForTesting
    private void d() {
        WeakReference<Context> weakReference = this.o;
        Context context = weakReference == null ? null : weakReference.get();
        if (context == null) {
            return;
        }
        aj ajVar = this.f;
        if (ajVar == null) {
            bi biVarA = bi.a(this.i, this.h, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, this.g);
            biVarA.f = context instanceof Activity ? InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_OTHER;
            this.f = aj.a(context, biVarA, this.r, 0);
        } else {
            ajVar.a(context);
            this.f.a(context instanceof Activity ? InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY : InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_OTHER);
        }
        aj ajVar2 = this.f;
        ajVar2.n = false;
        ajVar2.e = this.g;
        ajVar2.f = this.h;
    }

    public final void load() {
        try {
            if (a(true)) {
                if (this.m) {
                    a("ARR", "");
                    this.r.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.REPETITIVE_LOAD));
                    Logger.a(Logger.InternalLogLevel.ERROR, a, "You can call load() on an instance of InMobiNative only once if the ad request has been successful. Ignoring InMobiNative.load()");
                    return;
                }
                d();
                if (this.f != null) {
                    a("ARR", "");
                    bi biVarA = bi.a(this.i, this.h, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, this.g);
                    biVarA.f = this.f.l();
                    this.f.n();
                    com.inmobi.ads.c.a.a(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE).a(biVarA);
                }
            }
        } catch (Exception e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Could not load ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in loading ad; ").append(e.getMessage());
        }
    }

    public final void load(Context context) {
        if (a(true)) {
            this.o = new WeakReference<>(context);
            load();
        }
    }

    public static void requestAd(Context context, InMobiAdRequest inMobiAdRequest, NativeAdRequestListener nativeAdRequestListener) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please initialize the SDK before calling requestAd. Ignoring request");
            return;
        }
        if (nativeAdRequestListener == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please supply a non null NativeAdRequestListener. Ignoring request");
            return;
        }
        if (inMobiAdRequest == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please supply a non null InMobiAdRequest. Ignoring request");
            return;
        }
        if (context == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please supply a non null Context. Ignoring request");
            return;
        }
        try {
            RecyclerView.class.getName();
            Picasso.class.getName();
            final bi biVarA = bi.a(inMobiAdRequest.a, inMobiAdRequest.f, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, inMobiAdRequest.e);
            biVarA.f = inMobiAdRequest.b;
            i.d dVar = new i.d() { // from class: com.inmobi.ads.InMobiNative.1
                @Override // com.inmobi.ads.i.d
                public final void a(@NonNull i iVar) {
                    if (iVar instanceof aj) {
                        try {
                            com.inmobi.ads.c.a.a.remove(biVarA);
                            WeakReference weakReference = (WeakReference) InMobiNative.j.get(iVar);
                            if (weakReference != null) {
                                InMobiNative.j.remove(iVar);
                                NativeAdRequestListener nativeAdRequestListener2 = (NativeAdRequestListener) weakReference.get();
                                if (nativeAdRequestListener2 != null) {
                                    bi biVarA2 = bi.a(iVar.d, iVar.f, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, iVar.e);
                                    biVarA2.f = iVar.l();
                                    InMobiNative inMobiNative = new InMobiNative(iVar.a(), biVarA2, (byte) 0);
                                    inMobiNative.setKeywords(iVar.e);
                                    inMobiNative.setExtras(iVar.f);
                                    nativeAdRequestListener2.onAdRequestCompleted(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.NO_ERROR), inMobiNative);
                                }
                            }
                        } catch (Exception e) {
                            String unused = InMobiNative.a;
                            new StringBuilder("SDK encountered unexpected error in onAdPrefetchSucceeded ").append(e.getMessage());
                        }
                    }
                }

                @Override // com.inmobi.ads.i.d
                public final void a(@NonNull i iVar, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                    if (iVar instanceof aj) {
                        try {
                            com.inmobi.ads.c.a.a.remove(biVarA);
                            WeakReference weakReference = (WeakReference) InMobiNative.j.get(iVar);
                            if (weakReference != null) {
                                InMobiNative.j.remove(iVar);
                                NativeAdRequestListener nativeAdRequestListener2 = (NativeAdRequestListener) weakReference.get();
                                if (nativeAdRequestListener2 != null) {
                                    nativeAdRequestListener2.onAdRequestCompleted(inMobiAdRequestStatus, null);
                                }
                            }
                        } catch (Exception e) {
                            String unused = InMobiNative.a;
                            new StringBuilder("SDK encountered unexpected error in onAdPrefetchFailed ").append(e.getMessage());
                        }
                    }
                }
            };
            try {
                aj ajVarA = aj.a(context.getApplicationContext(), biVarA, (i.b) null, 2);
                ajVarA.f = inMobiAdRequest.f;
                ajVarA.e = inMobiAdRequest.e;
                ajVarA.q = dVar;
                ajVarA.n = true;
                j.put(ajVarA, new WeakReference<>(nativeAdRequestListener));
                ajVarA.q();
            } catch (Exception e) {
                new StringBuilder("SDK encountered unexpected error in requestAd").append(e.getMessage());
            }
        } catch (NoClassDefFoundError unused) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Some of the dependency libraries for InMobiNative not found. Ignoring request");
            nativeAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.MISSING_REQUIRED_DEPENDENCIES), null);
        }
    }

    public final void showOnLockScreen(LockScreenListener lockScreenListener) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please initialize the SDK before calling showOnLockScreen.");
            return;
        }
        if (lockScreenListener == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please provided non null LockScreenListener. Ignoring showOnLockScreen");
            return;
        }
        WeakReference<Context> weakReference = this.o;
        if (weakReference == null || weakReference.get() == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized. Provided context is null. Ignoring showOnLockScreen");
            return;
        }
        try {
            if (this.f == null) {
                this.f = aj.a(this.o.get(), bi.a(this.i, this.h, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, this.g), this.r, 0);
            }
            this.f.A = true;
            this.p = lockScreenListener;
        } catch (Exception unused) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "SDK encountered unexpected error in showOnLockScreen");
        }
    }

    public final void takeAction() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please initialize the SDK before calling takeAction.");
            return;
        }
        try {
            if (this.f != null) {
                aj ajVar = this.f;
                if (ajVar.o != null) {
                    ajVar.o.r();
                    return;
                }
                return;
            }
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized. Ignoring takeAction");
        } catch (Exception unused) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "SDK encountered unexpected error in takeAction");
        }
    }

    public final void pause() {
        AdContainer adContainerJ;
        try {
            if (this.f != null) {
                aj ajVar = this.f;
                if (ajVar.a != 5 || (ajVar.a() instanceof Activity) || (adContainerJ = ajVar.j()) == null) {
                    return;
                }
                ((ah) adContainerJ).q();
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Could not pause ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in pausing ad; ").append(e.getMessage());
        }
    }

    public final void resume() {
        AdContainer adContainerJ;
        try {
            if (this.f != null) {
                aj ajVar = this.f;
                if (ajVar.a != 5 || (ajVar.a() instanceof Activity) || (adContainerJ = ajVar.j()) == null) {
                    return;
                }
                ((ah) adContainerJ).p();
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Could not resume ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in resuming ad; ").append(e.getMessage());
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00e1 A[Catch: Exception -> 0x010c, TryCatch #0 {Exception -> 0x010c, blocks: (B:3:0x0003, B:5:0x0009, B:8:0x0015, B:10:0x001f, B:12:0x0023, B:14:0x002d, B:16:0x004a, B:18:0x0050, B:38:0x00cd, B:40:0x00e1, B:42:0x00e9, B:44:0x00f4, B:43:0x00ef, B:46:0x0104, B:20:0x0056, B:22:0x005c, B:24:0x0061, B:26:0x006e, B:28:0x0078, B:29:0x008a, B:31:0x008e, B:33:0x00a9, B:35:0x00ad, B:36:0x00b8, B:37:0x00bf), top: B:51:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x00e9 A[Catch: Exception -> 0x010c, TryCatch #0 {Exception -> 0x010c, blocks: (B:3:0x0003, B:5:0x0009, B:8:0x0015, B:10:0x001f, B:12:0x0023, B:14:0x002d, B:16:0x004a, B:18:0x0050, B:38:0x00cd, B:40:0x00e1, B:42:0x00e9, B:44:0x00f4, B:43:0x00ef, B:46:0x0104, B:20:0x0056, B:22:0x005c, B:24:0x0061, B:26:0x006e, B:28:0x0078, B:29:0x008a, B:31:0x008e, B:33:0x00a9, B:35:0x00ad, B:36:0x00b8, B:37:0x00bf), top: B:51:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x00ef A[Catch: Exception -> 0x010c, TryCatch #0 {Exception -> 0x010c, blocks: (B:3:0x0003, B:5:0x0009, B:8:0x0015, B:10:0x001f, B:12:0x0023, B:14:0x002d, B:16:0x004a, B:18:0x0050, B:38:0x00cd, B:40:0x00e1, B:42:0x00e9, B:44:0x00f4, B:43:0x00ef, B:46:0x0104, B:20:0x0056, B:22:0x005c, B:24:0x0061, B:26:0x006e, B:28:0x0078, B:29:0x008a, B:31:0x008e, B:33:0x00a9, B:35:0x00ad, B:36:0x00b8, B:37:0x00bf), top: B:51:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:46:0x0104 A[Catch: Exception -> 0x010c, TRY_LEAVE, TryCatch #0 {Exception -> 0x010c, blocks: (B:3:0x0003, B:5:0x0009, B:8:0x0015, B:10:0x001f, B:12:0x0023, B:14:0x002d, B:16:0x004a, B:18:0x0050, B:38:0x00cd, B:40:0x00e1, B:42:0x00e9, B:44:0x00f4, B:43:0x00ef, B:46:0x0104, B:20:0x0056, B:22:0x005c, B:24:0x0061, B:26:0x006e, B:28:0x0078, B:29:0x008a, B:31:0x008e, B:33:0x00a9, B:35:0x00ad, B:36:0x00b8, B:37:0x00bf), top: B:51:0x0003 }] */
    public final View getPrimaryViewOfWidth(Context context, View view, ViewGroup viewGroup, int i) {
        View viewA;
        View view2;
        View view3;
        try {
            if (!com.inmobi.commons.a.a.a()) {
                Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiSdk is not initialized. Ignoring InMobiNative.getPrimaryView()");
                return null;
            }
            if (context == null) {
                Logger.a(Logger.InternalLogLevel.ERROR, a, "View can not be rendered using null context");
                return null;
            }
            if (this.f == null) {
                Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized. Ignoring InMobiNative.getPrimaryView()");
                return null;
            }
            this.o = new WeakReference<>(context);
            this.f.a(context);
            final aj ajVar = this.f;
            boolean z = this.n;
            if (Looper.myLooper() == Looper.getMainLooper()) {
                if (!com.inmobi.commons.core.utilities.b.e.e()) {
                    ajVar.O();
                } else {
                    if (!ajVar.P() && ajVar.a != 7) {
                        Logger.a(Logger.InternalLogLevel.ERROR, aj.y, "Ad Load is not complete. Please wait for the Ad to be in a ready state before calling getPrimaryView().");
                        if (ajVar.z != null && (view3 = ajVar.z.get()) != null) {
                            View view4 = new View(com.inmobi.commons.a.a.b());
                            view4.setLayoutParams(view3.getLayoutParams());
                            viewA = view4;
                        }
                    } else {
                        ah ahVar = ajVar.o;
                        if (ahVar != null) {
                            ahVar.u = ajVar.A;
                            ahVar.s = i;
                            ahVar.t = z;
                            final ca viewableAd = ahVar.getViewableAd();
                            viewA = viewableAd.a(view, viewGroup, true);
                            ajVar.z = new WeakReference<>(viewA);
                            if (ajVar.r == 0 && !ajVar.t) {
                                ajVar.s.post(new Runnable() { // from class: com.inmobi.ads.aj.3
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        viewableAd.a(new View[0]);
                                    }
                                });
                            } else {
                                viewableAd.a(new View[0]);
                            }
                        }
                    }
                    this.l = new WeakReference<>(viewA);
                    view2 = this.l.get();
                    a("AVR", "");
                    if (view2 == null) {
                        if (this.f.P()) {
                            a("AVFB", "");
                        } else {
                            a("AVRR", "");
                        }
                        com.inmobi.commons.core.e.b.a();
                        com.inmobi.commons.core.e.b.a("ads", "PrimaryViewInflationFailed", new HashMap());
                        return null;
                    }
                    a("AVD", "");
                    this.m = true;
                    return view2;
                }
            } else {
                Logger.a(Logger.InternalLogLevel.ERROR, InMobiNative.class.getSimpleName(), "Please ensure that you call getPrimaryView() on the UI thread");
            }
            viewA = null;
            this.l = new WeakReference<>(viewA);
            view2 = this.l.get();
            a("AVR", "");
            if (view2 == null) {
                if (this.f.P()) {
                    a("AVFB", "");
                } else {
                    a("AVRR", "");
                }
                com.inmobi.commons.core.e.b.a();
                com.inmobi.commons.core.e.b.a("ads", "PrimaryViewInflationFailed", new HashMap());
                return null;
            }
            a("AVD", "");
            this.m = true;
            return view2;
        } catch (Exception e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Could not pause ad; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in pausing ad; ").append(e.getMessage());
            return null;
        }
    }

    @Deprecated
    public final View getPrimaryViewOfWidth(View view, ViewGroup viewGroup, int i) {
        WeakReference<Context> weakReference = this.o;
        if (weakReference == null || weakReference.get() == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized or provided context is null.");
            return null;
        }
        return getPrimaryViewOfWidth(this.o.get(), view, viewGroup, i);
    }

    public final JSONObject getCustomAdContent() {
        AdContainer adContainerJ;
        ao aoVar;
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.setExtras()");
            return null;
        }
        try {
            if (this.f == null || (adContainerJ = this.f.j()) == null || (aoVar = (ao) adContainerJ.getDataModel()) == null) {
                return null;
            }
            return aoVar.i.a;
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Could not get the ad customJson ; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return null;
    }

    public final String getAdTitle() {
        AdContainer adContainerJ;
        ao aoVar;
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdTitle()");
            return null;
        }
        try {
            if (this.f == null || (adContainerJ = this.f.j()) == null || (aoVar = (ao) adContainerJ.getDataModel()) == null) {
                return null;
            }
            return aoVar.i.b.a;
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Could not get the ad title; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return null;
    }

    public final String getAdDescription() {
        AdContainer adContainerJ;
        ao aoVar;
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdDescription()");
            return null;
        }
        try {
            if (this.f == null || (adContainerJ = this.f.j()) == null || (aoVar = (ao) adContainerJ.getDataModel()) == null) {
                return null;
            }
            return aoVar.i.b.b;
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Could not get the description; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return null;
    }

    public final String getAdIconUrl() {
        AdContainer adContainerJ;
        ao aoVar;
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdIconUrl()");
            return null;
        }
        try {
            if (this.f == null || (adContainerJ = this.f.j()) == null || (aoVar = (ao) adContainerJ.getDataModel()) == null) {
                return null;
            }
            return aoVar.i.b.c;
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Could not get the iconUrl; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return null;
    }

    public final String getAdLandingPageUrl() {
        AdContainer adContainerJ;
        ao aoVar;
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdLandingPageUrl()");
            return null;
        }
        try {
            if (this.f == null || (adContainerJ = this.f.j()) == null || (aoVar = (ao) adContainerJ.getDataModel()) == null) {
                return null;
            }
            return aoVar.i.b.f;
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Could not get the adLandingPageUrl; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return null;
    }

    public final String getAdCtaText() {
        AdContainer adContainerJ;
        ao aoVar;
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdCtaText()");
            return null;
        }
        try {
            if (this.f == null || (adContainerJ = this.f.j()) == null || (aoVar = (ao) adContainerJ.getDataModel()) == null) {
                return null;
            }
            return aoVar.i.b.d;
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Could not get the ctaText; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return null;
    }

    public final float getAdRating() {
        AdContainer adContainerJ;
        ao aoVar;
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.getAdRating()");
            return 0.0f;
        }
        try {
            if (this.f == null || (adContainerJ = this.f.j()) == null || (aoVar = (ao) adContainerJ.getDataModel()) == null) {
                return 0.0f;
            }
            return aoVar.i.b.e;
        } catch (Exception e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Could not get rating; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in getAdRating(); ").append(e.getMessage());
        }
        return 0.0f;
    }

    public final boolean isAppDownload() {
        AdContainer adContainerJ;
        ao aoVar;
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.isAppDownload()");
            return false;
        }
        try {
            if (this.f == null || (adContainerJ = this.f.j()) == null || (aoVar = (ao) adContainerJ.getDataModel()) == null) {
                return false;
            }
            return aoVar.i.b.g;
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Could not get isAppDownload; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return false;
    }

    public final void reportAdClickAndOpenLandingPage() {
        AdContainer adContainerJ;
        ah ahVar;
        ao aoVarH;
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.reportAdClickAndOpenLandingPage()");
            return;
        }
        try {
            if (this.f == null || (adContainerJ = this.f.j()) == null || (aoVarH = (ahVar = (ah) adContainerJ).h()) == null) {
                return;
            }
            ahVar.a((View) null, aoVarH.i.c);
            ahVar.a(aoVarH.i.c, true);
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "reportAdClickAndOpenLandingPage failed; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    public final boolean isReady() {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.isReady()");
            return false;
        }
        aj ajVar = this.f;
        return ajVar != null && ajVar.P();
    }

    public final JSONObject getAdMetaInfo() {
        aj ajVar;
        if (!com.inmobi.commons.a.a.a() || (ajVar = this.f) == null) {
            return new JSONObject();
        }
        return ajVar.i;
    }

    public final void setExtras(Map<String, String> map) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.setExtras()");
            return;
        }
        try {
            if (this.f != null) {
                this.f.f = map;
            }
            this.h = map;
        } catch (Exception e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Could not set extras; SDK encountered an unexpected error");
            new StringBuilder("SDK encountered unexpected error in setting extras ").append(e.getMessage());
        }
    }

    public final void setKeywords(String str) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized.Ignoring InMobiNative.setKeywords()");
            return;
        }
        try {
            if (this.f != null) {
                this.f.e = str;
            }
            this.g = str;
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Could not set keywords on Native ad; SDK encountered unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            new StringBuilder("SDK encountered unexpected error in setting keywords; ").append(e.getMessage());
        }
    }

    public final void destroy() {
        try {
            if (!com.inmobi.commons.a.a.a()) {
                Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized. Ignoring InMobiNative.destroy()");
            }
            if (this.b != null) {
                this.b.removeMessages(0);
            }
            View view = this.l == null ? null : this.l.get();
            if (view != null) {
                ((ViewGroup) view).removeAllViews();
            }
            if (this.f != null) {
                this.f.O();
            }
            if (this.q != null) {
                this.q = null;
            }
            this.f = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.k = null;
            this.m = false;
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Failed to destroy ad; SDK encountered an unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    public final void setDownloaderEnabled(boolean z) {
        this.n = z;
    }

    public final Downloader getDownloader() {
        try {
            if (!com.inmobi.commons.a.a.a()) {
                Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiNative is not initialized. Ignoring InMobiNative.getDownloader()");
                return null;
            }
            return this.k;
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Failed to get Downloader; SDK encountered an unexpected error");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return null;
        }
    }

    public final String getCreativeId() {
        aj ajVar;
        return (!com.inmobi.commons.a.a.a() || (ajVar = this.f) == null) ? "" : ajVar.x;
    }

    public final class Downloader {
        public static final int STATE_DOWNLOADED = 1;
        public static final int STATE_DOWNLOADING = 0;
        public static final int STATE_ERROR = 2;
        public static final int STATE_INITIALIZING = -1;
        public static final int STATE_UNINITIALIZED = -2;

        public Downloader() {
        }

        public final int getDownloadProgress() {
            AdContainer adContainerJ;
            if (!com.inmobi.commons.a.a.a()) {
                Logger.a(Logger.InternalLogLevel.ERROR, InMobiNative.a, "InMobiNative is not initialized.Ignoring getDownloadProgress()");
                return 0;
            }
            try {
                if (InMobiNative.this.f == null || (adContainerJ = InMobiNative.this.f.j()) == null || adContainerJ.getApkDownloader() != null) {
                }
                return 0;
            } catch (Exception unused) {
                Logger.a(Logger.InternalLogLevel.ERROR, InMobiNative.a, "Encountered unexpected error in getting download progress");
            }
            return 0;
        }

        public final int getDownloadStatus() {
            AdContainer adContainerJ;
            if (!com.inmobi.commons.a.a.a()) {
                Logger.a(Logger.InternalLogLevel.ERROR, InMobiNative.a, "InMobiNative is not initialized.Ignoring getDownloadStatus()");
                return -2;
            }
            try {
                if (InMobiNative.this.f == null || (adContainerJ = InMobiNative.this.f.j()) == null || adContainerJ.getApkDownloader() != null) {
                }
                return -2;
            } catch (Exception unused) {
                Logger.a(Logger.InternalLogLevel.ERROR, InMobiNative.a, "Encountered unexpected error in getting download progress");
            }
            return -2;
        }
    }

    /* JADX INFO: renamed from: com.inmobi.ads.InMobiNative$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
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
            try {
                a[InMobiAdRequestStatus.StatusCode.MISSING_REQUIRED_DEPENDENCIES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[InMobiAdRequestStatus.StatusCode.REPETITIVE_LOAD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @VisibleForTesting
    static final class a extends Handler {
        private WeakReference<InMobiNative> a;

        a(InMobiNative inMobiNative) {
            super(Looper.getMainLooper());
            this.a = new WeakReference<>(inMobiNative);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            InMobiNative inMobiNative = this.a.get();
            if (inMobiNative == null) {
                Logger.a(Logger.InternalLogLevel.ERROR, InMobiNative.a, "Lost reference to InMobiNative! callback cannot be given");
            }
            try {
                switch (message.what) {
                    case 1:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdLoadSucceeded(inMobiNative);
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdLoadSucceeded(inMobiNative);
                        }
                        break;
                    case 2:
                        InMobiAdRequestStatus inMobiAdRequestStatus = (InMobiAdRequestStatus) message.obj;
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdLoadFailed(inMobiNative, inMobiAdRequestStatus);
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdLoadFailed(inMobiNative, inMobiAdRequestStatus);
                        }
                        break;
                    case 3:
                        if (inMobiNative.p != null) {
                            inMobiNative.p.onActionRequired(inMobiNative);
                        }
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdFullScreenWillDisplay(inMobiNative);
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdFullScreenWillDisplay(inMobiNative);
                        }
                        break;
                    case 4:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdFullScreenDisplayed(inMobiNative);
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdFullScreenDisplayed(inMobiNative);
                        }
                        break;
                    case 5:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdFullScreenDismissed(inMobiNative);
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdFullScreenDismissed(inMobiNative);
                        }
                        break;
                    case 6:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdImpressed(inMobiNative);
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdImpressed(inMobiNative);
                        }
                        break;
                    case 7:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdClicked(inMobiNative);
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdClicked(inMobiNative);
                        }
                        break;
                    case 8:
                        if (inMobiNative.p != null) {
                            inMobiNative.p.onActionRequired(inMobiNative);
                        }
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onUserWillLeaveApplication(inMobiNative);
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onUserWillLeaveApplication(inMobiNative);
                        }
                        break;
                    case 9:
                        if (inMobiNative.e != null) {
                            inMobiNative.e.onVideoCompleted(inMobiNative);
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onMediaPlaybackComplete(inMobiNative);
                        }
                        break;
                    case 10:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onAdStatusChanged(inMobiNative);
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onAdStatusChanged(inMobiNative);
                        }
                        break;
                    case 11:
                        if (inMobiNative.e != null) {
                            inMobiNative.e.onVideoSkipped(inMobiNative);
                        } else if (inMobiNative.c != null) {
                            inMobiNative.c.onUserSkippedMedia(inMobiNative);
                        }
                        break;
                    case 12:
                        byte[] bArr = (byte[]) message.obj;
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onRequestPayloadCreated(bArr);
                        }
                        break;
                    case 13:
                        if (inMobiNative.d != null) {
                            inMobiNative.d.onRequestPayloadCreationFailed((InMobiAdRequestStatus) message.obj);
                        }
                        break;
                    case 14:
                        if (inMobiNative.e != null) {
                            inMobiNative.e.onAudioStateChanged(inMobiNative, ((Boolean) message.obj).booleanValue());
                        }
                        break;
                    default:
                        String unused = InMobiNative.a;
                        break;
                }
            } catch (Exception e) {
                Logger.a(Logger.InternalLogLevel.ERROR, InMobiNative.a, "Publisher handler caused unexpected error");
                String unused2 = InMobiNative.a;
                new StringBuilder("Callback threw unexpected error: ").append(e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        if (this.q == null) {
            this.q = new k(this.f);
        }
        this.q.a(this.r, str, str2);
    }

    static /* synthetic */ boolean c() {
        return Message.obtain() == null;
    }
}
