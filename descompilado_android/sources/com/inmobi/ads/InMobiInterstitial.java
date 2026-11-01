package com.inmobi.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import com.inmobi.ads.ac;
import com.inmobi.ads.listeners.InterstitialAdEventListener;
import com.inmobi.commons.core.utilities.Logger;
import com.ironsource.sdk.constants.Constants;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class InMobiInterstitial {
    private static final String a = "InMobiInterstitial";
    private static ConcurrentHashMap<ac, ArrayList<WeakReference<InterstitialAdRequestListener>>> m = new ConcurrentHashMap<>(2, 0.9f, 3);

    @Nullable
    private ac b;
    private a c;
    private InterstitialAdListener2 d;
    private InterstitialAdEventListener e;
    private Context f;
    private long g;
    private boolean h;
    private String i;
    private Map<String, String> j;
    private boolean k;
    private boolean l;
    private String n;
    private j o;
    private JSONObject p;

    @NonNull
    private final i.b q;

    public interface InterstitialAdListener2 {
        void onAdDismissed(InMobiInterstitial inMobiInterstitial);

        void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial);

        void onAdDisplayed(InMobiInterstitial inMobiInterstitial);

        void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map);

        void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus);

        void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial);

        void onAdReceived(InMobiInterstitial inMobiInterstitial);

        void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map);

        void onAdWillDisplay(InMobiInterstitial inMobiInterstitial);

        void onUserLeftApplication(InMobiInterstitial inMobiInterstitial);
    }

    public interface InterstitialAdRequestListener {
        void onAdRequestCompleted(InMobiAdRequestStatus inMobiAdRequestStatus, InMobiInterstitial inMobiInterstitial);
    }

    /* synthetic */ InMobiInterstitial(Context context, long j, byte b) {
        this(context, j);
    }

    @Deprecated
    public InMobiInterstitial(Context context, long j, InterstitialAdListener2 interstitialAdListener2) {
        this.h = false;
        this.l = false;
        this.n = "";
        this.q = new i.b() { // from class: com.inmobi.ads.InMobiInterstitial.2
            @Override // com.inmobi.ads.i.b
            public final void a(boolean z) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 2;
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constants.ParametersKeys.AVAILABLE, z);
                messageObtain.setData(bundle);
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            public final void a(i iVar) {
                InMobiInterstitial.this.a("AR", "");
                InMobiInterstitial.this.n = iVar.x;
                InMobiInterstitial.this.p = iVar.i;
                InMobiInterstitial.this.c.sendEmptyMessage(3);
            }

            @Override // com.inmobi.ads.i.b
            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                int i = AnonymousClass3.a[inMobiAdRequestStatus.getStatusCode().ordinal()];
                if (i == 1) {
                    InMobiInterstitial.this.a("ART", "NetworkNotAvailable");
                } else if (i == 2) {
                    InMobiInterstitial.this.a("ART", "LoadInProgress");
                } else if (i == 3) {
                    InMobiInterstitial.this.a("ART", "ReloadNotPermitted");
                } else if (i == 4) {
                    InMobiInterstitial.this.a("ART", "FrequentRequests");
                } else if (i != 5) {
                    InMobiInterstitial.this.a("AF", "");
                } else {
                    InMobiInterstitial.this.a("ART", "MissingRequiredDependencies");
                }
                if (InMobiInterstitial.c()) {
                    return;
                }
                Message messageObtain = Message.obtain();
                messageObtain.what = 1;
                messageObtain.obj = inMobiAdRequestStatus;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            public final void b() {
                InMobiInterstitial.this.c.sendEmptyMessage(5);
            }

            @Override // com.inmobi.ads.i.b
            public final void c() {
                InMobiInterstitial.this.c.sendEmptyMessage(6);
            }

            @Override // com.inmobi.ads.i.b
            public final void d() {
                InMobiInterstitial.this.a("AVD", "");
                InMobiInterstitial.this.c.sendEmptyMessage(7);
            }

            @Override // com.inmobi.ads.i.b
            public final void e() {
                InMobiInterstitial.this.a("AVCD", "");
                InMobiInterstitial.this.c.sendEmptyMessage(10);
                final com.inmobi.ads.c.b bVarD = com.inmobi.ads.c.b.d();
                final bi biVarA = bi.a(InMobiInterstitial.this.g, InMobiInterstitial.this.j, "int", InMobiInterstitial.this.i);
                if (com.inmobi.ads.c.b.b.c(bVarD.c).a) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.inmobi.ads.c.b.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                b.a(b.this);
                                if (com.inmobi.ads.c.a.a.containsKey(biVarA)) {
                                    return;
                                }
                                String unused = b.d;
                                StringBuilder sb = new StringBuilder("preLoadAdUnit. pid:");
                                sb.append(biVarA.a);
                                sb.append(" tp:");
                                sb.append(biVarA.b);
                                if (biVarA.c == null && biVarA.b != null) {
                                    HashMap map = new HashMap();
                                    map.put("tp", biVarA.b);
                                    biVarA.c = map;
                                }
                                a aVar = new a(biVarA);
                                b.g.add(aVar);
                                ac acVarA = ac.a.a(com.inmobi.commons.a.a.b(), biVarA, aVar);
                                acVarA.e = biVarA.d;
                                acVarA.f = biVarA.c;
                                acVarA.n = true;
                                com.inmobi.ads.c.a.a.put(biVarA, acVarA);
                                acVarA.e(aVar);
                            } catch (Exception e) {
                                String unused2 = b.d;
                                new StringBuilder("SDK encountered an unexpected error preloading ad units; ").append(e.getMessage());
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                            }
                        }
                    });
                }
            }

            @Override // com.inmobi.ads.i.b
            public final void a(@NonNull Map<Object, Object> map) {
                InMobiInterstitial.this.a("AVCL", "");
                Message messageObtain = Message.obtain();
                messageObtain.what = 9;
                messageObtain.obj = map;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            public final void f() {
                InMobiInterstitial.this.c.sendEmptyMessage(11);
            }

            @Override // com.inmobi.ads.i.b
            public final void b(@NonNull Map<Object, Object> map) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 4;
                messageObtain.obj = map;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void a(byte[] bArr) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 12;
                messageObtain.obj = bArr;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 13;
                messageObtain.obj = inMobiAdRequestStatus;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please initialize the SDK before creating an Interstitial ad");
            return;
        }
        if (interstitialAdListener2 == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "The Interstitial ad cannot be created as no event listener was supplied. Please attach a listener to proceed");
            return;
        }
        if (context == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Unable to create Interstitial ad with null context object.");
            return;
        }
        this.h = true;
        this.f = context.getApplicationContext();
        this.g = j;
        this.d = interstitialAdListener2;
        this.c = new a(this);
    }

    public InMobiInterstitial(Context context, long j, InterstitialAdEventListener interstitialAdEventListener) {
        this.h = false;
        this.l = false;
        this.n = "";
        this.q = new i.b() { // from class: com.inmobi.ads.InMobiInterstitial.2
            @Override // com.inmobi.ads.i.b
            public final void a(boolean z) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 2;
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constants.ParametersKeys.AVAILABLE, z);
                messageObtain.setData(bundle);
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            public final void a(i iVar) {
                InMobiInterstitial.this.a("AR", "");
                InMobiInterstitial.this.n = iVar.x;
                InMobiInterstitial.this.p = iVar.i;
                InMobiInterstitial.this.c.sendEmptyMessage(3);
            }

            @Override // com.inmobi.ads.i.b
            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                int i = AnonymousClass3.a[inMobiAdRequestStatus.getStatusCode().ordinal()];
                if (i == 1) {
                    InMobiInterstitial.this.a("ART", "NetworkNotAvailable");
                } else if (i == 2) {
                    InMobiInterstitial.this.a("ART", "LoadInProgress");
                } else if (i == 3) {
                    InMobiInterstitial.this.a("ART", "ReloadNotPermitted");
                } else if (i == 4) {
                    InMobiInterstitial.this.a("ART", "FrequentRequests");
                } else if (i != 5) {
                    InMobiInterstitial.this.a("AF", "");
                } else {
                    InMobiInterstitial.this.a("ART", "MissingRequiredDependencies");
                }
                if (InMobiInterstitial.c()) {
                    return;
                }
                Message messageObtain = Message.obtain();
                messageObtain.what = 1;
                messageObtain.obj = inMobiAdRequestStatus;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            public final void b() {
                InMobiInterstitial.this.c.sendEmptyMessage(5);
            }

            @Override // com.inmobi.ads.i.b
            public final void c() {
                InMobiInterstitial.this.c.sendEmptyMessage(6);
            }

            @Override // com.inmobi.ads.i.b
            public final void d() {
                InMobiInterstitial.this.a("AVD", "");
                InMobiInterstitial.this.c.sendEmptyMessage(7);
            }

            @Override // com.inmobi.ads.i.b
            public final void e() {
                InMobiInterstitial.this.a("AVCD", "");
                InMobiInterstitial.this.c.sendEmptyMessage(10);
                final com.inmobi.ads.c.b bVarD = com.inmobi.ads.c.b.d();
                final bi biVarA = bi.a(InMobiInterstitial.this.g, InMobiInterstitial.this.j, "int", InMobiInterstitial.this.i);
                if (com.inmobi.ads.c.b.b.c(bVarD.c).a) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.inmobi.ads.c.b.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                b.a(b.this);
                                if (com.inmobi.ads.c.a.a.containsKey(biVarA)) {
                                    return;
                                }
                                String unused = b.d;
                                StringBuilder sb = new StringBuilder("preLoadAdUnit. pid:");
                                sb.append(biVarA.a);
                                sb.append(" tp:");
                                sb.append(biVarA.b);
                                if (biVarA.c == null && biVarA.b != null) {
                                    HashMap map = new HashMap();
                                    map.put("tp", biVarA.b);
                                    biVarA.c = map;
                                }
                                a aVar = new a(biVarA);
                                b.g.add(aVar);
                                ac acVarA = ac.a.a(com.inmobi.commons.a.a.b(), biVarA, aVar);
                                acVarA.e = biVarA.d;
                                acVarA.f = biVarA.c;
                                acVarA.n = true;
                                com.inmobi.ads.c.a.a.put(biVarA, acVarA);
                                acVarA.e(aVar);
                            } catch (Exception e) {
                                String unused2 = b.d;
                                new StringBuilder("SDK encountered an unexpected error preloading ad units; ").append(e.getMessage());
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                            }
                        }
                    });
                }
            }

            @Override // com.inmobi.ads.i.b
            public final void a(@NonNull Map<Object, Object> map) {
                InMobiInterstitial.this.a("AVCL", "");
                Message messageObtain = Message.obtain();
                messageObtain.what = 9;
                messageObtain.obj = map;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            public final void f() {
                InMobiInterstitial.this.c.sendEmptyMessage(11);
            }

            @Override // com.inmobi.ads.i.b
            public final void b(@NonNull Map<Object, Object> map) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 4;
                messageObtain.obj = map;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void a(byte[] bArr) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 12;
                messageObtain.obj = bArr;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 13;
                messageObtain.obj = inMobiAdRequestStatus;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }
        };
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please initialize the SDK before creating an Interstitial ad");
            return;
        }
        if (interstitialAdEventListener == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "The Interstitial ad cannot be created as no event listener was supplied. Please attach a listener to proceed");
            return;
        }
        if (context == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Unable to create Interstitial ad with null context object.");
            return;
        }
        this.h = true;
        this.f = context.getApplicationContext();
        this.g = j;
        this.e = interstitialAdEventListener;
        this.c = new a(this);
    }

    private InMobiInterstitial(Context context, long j) {
        this.h = false;
        this.l = false;
        this.n = "";
        this.q = new i.b() { // from class: com.inmobi.ads.InMobiInterstitial.2
            @Override // com.inmobi.ads.i.b
            public final void a(boolean z) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 2;
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constants.ParametersKeys.AVAILABLE, z);
                messageObtain.setData(bundle);
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            public final void a(i iVar) {
                InMobiInterstitial.this.a("AR", "");
                InMobiInterstitial.this.n = iVar.x;
                InMobiInterstitial.this.p = iVar.i;
                InMobiInterstitial.this.c.sendEmptyMessage(3);
            }

            @Override // com.inmobi.ads.i.b
            public final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
                int i = AnonymousClass3.a[inMobiAdRequestStatus.getStatusCode().ordinal()];
                if (i == 1) {
                    InMobiInterstitial.this.a("ART", "NetworkNotAvailable");
                } else if (i == 2) {
                    InMobiInterstitial.this.a("ART", "LoadInProgress");
                } else if (i == 3) {
                    InMobiInterstitial.this.a("ART", "ReloadNotPermitted");
                } else if (i == 4) {
                    InMobiInterstitial.this.a("ART", "FrequentRequests");
                } else if (i != 5) {
                    InMobiInterstitial.this.a("AF", "");
                } else {
                    InMobiInterstitial.this.a("ART", "MissingRequiredDependencies");
                }
                if (InMobiInterstitial.c()) {
                    return;
                }
                Message messageObtain = Message.obtain();
                messageObtain.what = 1;
                messageObtain.obj = inMobiAdRequestStatus;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            public final void b() {
                InMobiInterstitial.this.c.sendEmptyMessage(5);
            }

            @Override // com.inmobi.ads.i.b
            public final void c() {
                InMobiInterstitial.this.c.sendEmptyMessage(6);
            }

            @Override // com.inmobi.ads.i.b
            public final void d() {
                InMobiInterstitial.this.a("AVD", "");
                InMobiInterstitial.this.c.sendEmptyMessage(7);
            }

            @Override // com.inmobi.ads.i.b
            public final void e() {
                InMobiInterstitial.this.a("AVCD", "");
                InMobiInterstitial.this.c.sendEmptyMessage(10);
                final com.inmobi.ads.c.b bVarD = com.inmobi.ads.c.b.d();
                final bi biVarA = bi.a(InMobiInterstitial.this.g, InMobiInterstitial.this.j, "int", InMobiInterstitial.this.i);
                if (com.inmobi.ads.c.b.b.c(bVarD.c).a) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.inmobi.ads.c.b.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                b.a(b.this);
                                if (com.inmobi.ads.c.a.a.containsKey(biVarA)) {
                                    return;
                                }
                                String unused = b.d;
                                StringBuilder sb = new StringBuilder("preLoadAdUnit. pid:");
                                sb.append(biVarA.a);
                                sb.append(" tp:");
                                sb.append(biVarA.b);
                                if (biVarA.c == null && biVarA.b != null) {
                                    HashMap map = new HashMap();
                                    map.put("tp", biVarA.b);
                                    biVarA.c = map;
                                }
                                a aVar = new a(biVarA);
                                b.g.add(aVar);
                                ac acVarA = ac.a.a(com.inmobi.commons.a.a.b(), biVarA, aVar);
                                acVarA.e = biVarA.d;
                                acVarA.f = biVarA.c;
                                acVarA.n = true;
                                com.inmobi.ads.c.a.a.put(biVarA, acVarA);
                                acVarA.e(aVar);
                            } catch (Exception e) {
                                String unused2 = b.d;
                                new StringBuilder("SDK encountered an unexpected error preloading ad units; ").append(e.getMessage());
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                            }
                        }
                    });
                }
            }

            @Override // com.inmobi.ads.i.b
            public final void a(@NonNull Map<Object, Object> map) {
                InMobiInterstitial.this.a("AVCL", "");
                Message messageObtain = Message.obtain();
                messageObtain.what = 9;
                messageObtain.obj = map;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            public final void f() {
                InMobiInterstitial.this.c.sendEmptyMessage(11);
            }

            @Override // com.inmobi.ads.i.b
            public final void b(@NonNull Map<Object, Object> map) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 4;
                messageObtain.obj = map;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void a(byte[] bArr) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 12;
                messageObtain.obj = bArr;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }

            @Override // com.inmobi.ads.i.b
            final void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 13;
                messageObtain.obj = inMobiAdRequestStatus;
                InMobiInterstitial.this.c.sendMessage(messageObtain);
            }
        };
        this.h = true;
        this.f = context;
        this.g = j;
        this.c = new a(this);
    }

    @Deprecated
    public final void setInterstitialAdListener(InterstitialAdListener2 interstitialAdListener2) {
        this.d = interstitialAdListener2;
    }

    public final void setListener(InterstitialAdEventListener interstitialAdEventListener) {
        if (interstitialAdEventListener == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please pass a non-null listener to the interstitial.");
        } else {
            this.e = interstitialAdEventListener;
        }
    }

    public final void setKeywords(String str) {
        if (this.h) {
            this.i = str;
        }
    }

    private boolean a(boolean z) {
        if (!this.h) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "InMobiInterstitial is not initialized, your call is ignored.");
            return false;
        }
        if (!z ? this.e == null : !(this.d != null || this.e != null)) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Listener supplied is null, your call is ignored.");
            return false;
        }
        if (this.f != null) {
            return true;
        }
        Logger.a(Logger.InternalLogLevel.ERROR, a, "Context supplied is null, your call is ignored.");
        return false;
    }

    public final void getSignals() {
        if (a(false)) {
            if (this.b == null) {
                this.b = ac.a.b(this.f, bi.a(this.g, this.j, "int", this.i), this.q);
            }
            a("ARR", "");
            a(this.b);
            this.b.o();
        }
    }

    public final void load(byte[] bArr) {
        if (a(false)) {
            ac acVar = this.b;
            if (acVar == null) {
                Logger.a(Logger.InternalLogLevel.ERROR, a, "Either getSignals() is not called or InMobiInterstitial is not initialized, your call is ignored.");
                return;
            }
            this.l = true;
            if (acVar.d(this.q)) {
                acVar.a(bArr);
            }
        }
    }

    public final void load() {
        try {
            if (a(true)) {
                bi biVarA = bi.a(this.g, this.j, "int", this.i);
                com.inmobi.ads.c.b bVarD = com.inmobi.ads.c.b.d();
                i iVar = null;
                if (!com.inmobi.ads.c.b.b.c(bVarD.c).a) {
                    StringBuilder sb = new StringBuilder("No cached ad unit found as config is disabled. pid:");
                    sb.append(biVarA.a);
                    sb.append(" tp:");
                    sb.append(biVarA.b);
                } else {
                    bVarD.a(biVarA);
                    i iVar2 = com.inmobi.ads.c.b.a.get(biVarA);
                    if (iVar2 == null) {
                        StringBuilder sb2 = new StringBuilder("No cached ad unit found for pid:");
                        sb2.append(biVarA.a);
                        sb2.append(" tp:");
                        sb2.append(biVarA.b);
                    } else if (iVar2.h()) {
                        StringBuilder sb3 = new StringBuilder("Expired cached ad unit found for pid:");
                        sb3.append(biVarA.a);
                        sb3.append(" tp:");
                        sb3.append(biVarA.b);
                        iVar2.v();
                        com.inmobi.ads.c.b.a.remove(biVarA);
                        com.inmobi.ads.c.b.a("AdUnitExpired", iVar2);
                    } else {
                        StringBuilder sb4 = new StringBuilder("Cached ad unit found for pid:");
                        sb4.append(biVarA.a);
                        sb4.append(" tp:");
                        sb4.append(biVarA.b);
                        com.inmobi.ads.c.b.a(com.inmobi.ads.c.b.a.remove(biVarA));
                        iVar = iVar2;
                    }
                }
                this.l = true;
                if (iVar != null) {
                    this.b = (ac) iVar;
                } else {
                    this.b = ac.a.a(this.f, biVarA, this.q);
                }
                a("ARR", "");
                a(this.b);
                ac acVar = this.b;
                Logger.a(Logger.InternalLogLevel.DEBUG, a, "Fetching an Interstitial ad for placement id: " + acVar.d);
                this.n = "";
                this.p = this.b.b;
                acVar.a(this.q);
                acVar.e(this.q);
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Unable to load ad; SDK encountered an unexpected error");
            new StringBuilder("Load failed with unexpected error: ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    public static void requestAd(Context context, InMobiAdRequest inMobiAdRequest, InterstitialAdRequestListener interstitialAdRequestListener) {
        if (!com.inmobi.commons.a.a.a()) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please initialize the SDK before calling requestAd. Ignoring request");
            return;
        }
        if (interstitialAdRequestListener == null) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Please supply a non null InterstitialAdRequestListener. Ignoring request");
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
        ac acVar = null;
        try {
            RecyclerView.class.getName();
            Picasso.class.getName();
            i.d dVar = new i.d() { // from class: com.inmobi.ads.InMobiInterstitial.1
                @Override // com.inmobi.ads.i.d
                public final void a(@NonNull i iVar) {
                    ArrayList<WeakReference> arrayList;
                    final InterstitialAdRequestListener interstitialAdRequestListener2;
                    try {
                        if (!(iVar instanceof ac) || (arrayList = (ArrayList) InMobiInterstitial.m.get(iVar)) == null) {
                            return;
                        }
                        InMobiInterstitial.m.remove(iVar);
                        Handler handler = new Handler(Looper.getMainLooper());
                        for (WeakReference weakReference : arrayList) {
                            if (weakReference != null && (interstitialAdRequestListener2 = (InterstitialAdRequestListener) weakReference.get()) != null) {
                                final InMobiInterstitial inMobiInterstitial = new InMobiInterstitial(iVar.a(), iVar.d, (byte) 0);
                                inMobiInterstitial.setKeywords(iVar.e);
                                inMobiInterstitial.setExtras(iVar.f);
                                handler.post(new Runnable() { // from class: com.inmobi.ads.InMobiInterstitial.1.1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        try {
                                            interstitialAdRequestListener2.onAdRequestCompleted(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.NO_ERROR), inMobiInterstitial);
                                        } catch (Exception unused) {
                                            Logger.a(Logger.InternalLogLevel.ERROR, InMobiInterstitial.a, "Publisher handler caused unexpected error");
                                        }
                                    }
                                });
                            }
                        }
                    } catch (Exception e) {
                        String unused = InMobiInterstitial.a;
                        new StringBuilder("SDK encountered unexpected error in onAdPrefetchSucceeded ").append(e.getMessage());
                    }
                }

                @Override // com.inmobi.ads.i.d
                public final void a(@NonNull i iVar, @NonNull final InMobiAdRequestStatus inMobiAdRequestStatus) {
                    ArrayList arrayList;
                    WeakReference weakReference;
                    try {
                        if (!(iVar instanceof ac) || (arrayList = (ArrayList) InMobiInterstitial.m.get(iVar)) == null || arrayList.size() <= 0 || (weakReference = (WeakReference) arrayList.get(arrayList.size() - 1)) == null) {
                            return;
                        }
                        arrayList.remove(weakReference);
                        if (arrayList.size() == 0) {
                            InMobiInterstitial.m.remove(iVar);
                        }
                        final InterstitialAdRequestListener interstitialAdRequestListener2 = (InterstitialAdRequestListener) weakReference.get();
                        if (interstitialAdRequestListener2 != null) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.inmobi.ads.InMobiInterstitial.1.2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    try {
                                        interstitialAdRequestListener2.onAdRequestCompleted(inMobiAdRequestStatus, null);
                                    } catch (Exception unused) {
                                        Logger.a(Logger.InternalLogLevel.ERROR, InMobiInterstitial.a, "Publisher handler caused unexpected error");
                                    }
                                }
                            });
                        }
                    } catch (Exception e) {
                        String unused = InMobiInterstitial.a;
                        new StringBuilder("SDK encountered unexpected error in onAdPrefetchFailed ").append(e.getMessage());
                    }
                }
            };
            try {
                Iterator<Map.Entry<ac, ArrayList<WeakReference<InterstitialAdRequestListener>>>> it = m.entrySet().iterator();
                while (it.hasNext()) {
                    ac key = it.next().getKey();
                    if (key != null && key.d == inMobiAdRequest.a) {
                        acVar = key;
                        break;
                    }
                }
                if (acVar != null) {
                    ArrayList<WeakReference<InterstitialAdRequestListener>> arrayList = m.get(acVar);
                    arrayList.add(new WeakReference<>(interstitialAdRequestListener));
                    ac acVarA = a(context, inMobiAdRequest, dVar);
                    m.put(acVarA, arrayList);
                    acVarA.q();
                    return;
                }
                ac acVarA2 = a(context, inMobiAdRequest, dVar);
                acVarA2.q = dVar;
                ArrayList<WeakReference<InterstitialAdRequestListener>> arrayList2 = new ArrayList<>();
                arrayList2.add(new WeakReference<>(interstitialAdRequestListener));
                m.put(acVarA2, arrayList2);
                acVarA2.q();
            } catch (Exception e) {
                new StringBuilder("SDK encountered unexpected error in requestAd").append(e.getMessage());
            }
        } catch (NoClassDefFoundError unused) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Some of the dependency libraries for Interstitial not found. Ignoring request");
            interstitialAdRequestListener.onAdRequestCompleted(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.MISSING_REQUIRED_DEPENDENCIES), null);
        }
    }

    private static ac a(Context context, InMobiAdRequest inMobiAdRequest, i.d dVar) {
        ac acVarA = ac.a.a(context.getApplicationContext(), bi.a(inMobiAdRequest.a, inMobiAdRequest.f, "int", inMobiAdRequest.e), null);
        acVarA.f = inMobiAdRequest.f;
        acVarA.e = inMobiAdRequest.e;
        acVarA.a(InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
        acVarA.n = true;
        acVarA.q = dVar;
        return acVarA;
    }

    @VisibleForTesting
    private void a(@NonNull ac acVar) {
        AdContainer adContainerJ;
        acVar.a(this.f);
        acVar.f = this.j;
        acVar.e = this.i;
        acVar.a(InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY);
        if (this.k && (adContainerJ = acVar.j()) != null) {
            acVar.y = true;
            adContainerJ.a();
        }
        acVar.n = false;
    }

    public final void show() {
        try {
            if (!this.l) {
                Logger.a(Logger.InternalLogLevel.ERROR, a, "load() must be called before trying to show the ad");
            } else {
                if (!this.h || this.b == null) {
                    return;
                }
                a("AVR", "");
                this.b.f(this.q);
            }
        } catch (Exception e) {
            Logger.a(Logger.InternalLogLevel.ERROR, a, "Unable to show ad; SDK encountered an unexpected error");
            new StringBuilder("Show failed with unexpected error: ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
    }

    @Deprecated
    public final void show(int i, int i2) {
        show();
    }

    public final boolean isReady() {
        ac acVar;
        return this.h && (acVar = this.b) != null && acVar.O();
    }

    public final JSONObject getAdMetaInfo() {
        JSONObject jSONObject = this.p;
        return jSONObject == null ? new JSONObject() : jSONObject;
    }

    public final String getCreativeId() {
        return this.n;
    }

    public final void setExtras(Map<String, String> map) {
        if (this.h) {
            this.j = map;
        }
    }

    public final void disableHardwareAcceleration() {
        if (this.h) {
            this.k = true;
        }
    }

    @VisibleForTesting
    static final class a extends Handler {
        private WeakReference<InMobiInterstitial> a;

        a(InMobiInterstitial inMobiInterstitial) {
            super(Looper.getMainLooper());
            this.a = new WeakReference<>(inMobiInterstitial);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Map<Object, Object> map;
            InMobiInterstitial inMobiInterstitial = this.a.get();
            if (inMobiInterstitial != null) {
                try {
                    switch (message.what) {
                        case 1:
                            InMobiAdRequestStatus inMobiAdRequestStatus = (InMobiAdRequestStatus) message.obj;
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                            }
                            break;
                        case 2:
                            if (message.getData().getBoolean(Constants.ParametersKeys.AVAILABLE)) {
                                if (inMobiInterstitial.e != null) {
                                    inMobiInterstitial.e.onAdReceived(inMobiInterstitial);
                                }
                                if (inMobiInterstitial.d != null) {
                                    inMobiInterstitial.d.onAdReceived(inMobiInterstitial);
                                }
                            }
                            break;
                        case 3:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdLoadSucceeded(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdLoadSucceeded(inMobiInterstitial);
                            }
                            break;
                        case 4:
                            map = message.obj != null ? (Map) message.obj : null;
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onRewardsUnlocked(inMobiInterstitial, map);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdRewardActionCompleted(inMobiInterstitial, map);
                            }
                            break;
                        case 5:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdDisplayFailed(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdDisplayFailed(inMobiInterstitial);
                            }
                            break;
                        case 6:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdWillDisplay(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdWillDisplay(inMobiInterstitial);
                            }
                            break;
                        case 7:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdDisplayed(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdDisplayed(inMobiInterstitial);
                            }
                            break;
                        case 8:
                        default:
                            String unused = InMobiInterstitial.a;
                            break;
                        case 9:
                            map = message.obj != null ? (Map) message.obj : null;
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdClicked(inMobiInterstitial, map);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdInteraction(inMobiInterstitial, map);
                            }
                            break;
                        case 10:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onAdDismissed(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onAdDismissed(inMobiInterstitial);
                            }
                            break;
                        case 11:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onUserLeftApplication(inMobiInterstitial);
                            }
                            if (inMobiInterstitial.d != null) {
                                inMobiInterstitial.d.onUserLeftApplication(inMobiInterstitial);
                            }
                            break;
                        case 12:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onRequestPayloadCreated((byte[]) message.obj);
                            }
                            break;
                        case 13:
                            if (inMobiInterstitial.e != null) {
                                inMobiInterstitial.e.onRequestPayloadCreationFailed((InMobiAdRequestStatus) message.obj);
                            }
                            break;
                    }
                } catch (Exception e) {
                    Logger.a(Logger.InternalLogLevel.ERROR, InMobiInterstitial.a, "Publisher handler caused unexpected error");
                    String unused2 = InMobiInterstitial.a;
                    new StringBuilder("Callback threw unexpected error: ").append(e.getMessage());
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.inmobi.ads.InMobiInterstitial$3, reason: invalid class name */
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
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        if (this.o == null) {
            this.o = new k(this.b);
        }
        this.o.a(this.q, str, str2);
    }

    static /* synthetic */ boolean c() {
        return Message.obtain() == null;
    }
}
