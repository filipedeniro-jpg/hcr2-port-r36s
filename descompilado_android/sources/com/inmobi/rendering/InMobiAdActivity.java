package com.inmobi.rendering;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import com.facebook.internal.NativeProtocol;
import com.inmobi.ads.AdContainer;
import com.inmobi.ads.NativeVideoView;
import com.inmobi.ads.NativeVideoWrapper;
import com.inmobi.ads.ah;
import com.inmobi.ads.ao;
import com.inmobi.ads.bd;
import com.inmobi.ads.be;
import com.inmobi.ads.ca;
import com.inmobi.commons.core.utilities.Logger;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.constants.LocationConst;
import com.my.target.ai;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ClickableViewAccessibility"})
@TargetApi(15)
public class InMobiAdActivity extends Activity {
    private static final String g = "InMobiAdActivity";
    private static RenderView i;
    private static RenderView.a j;
    private AdContainer k;
    private RenderView l;
    private CustomView m;
    private CustomView n;
    private NativeVideoView o;
    private int p;
    private int q;
    private static SparseArray<AdContainer> h = new SparseArray<>();

    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, a> b = new HashMap();

    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, Intent> c = new HashMap();
    public static Integer d = 0;

    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, b> e = new HashMap();
    public static Integer f = 0;
    public boolean a = false;
    private boolean r = false;
    private boolean s = false;

    public interface a {
        void a();
    }

    public interface b {
        void a(int[] iArr);
    }

    static /* synthetic */ boolean c(InMobiAdActivity inMobiAdActivity) {
        inMobiAdActivity.a = true;
        return true;
    }

    public static int a(AdContainer adContainer) {
        int iHashCode = adContainer.hashCode();
        h.put(iHashCode, adContainer);
        return iHashCode;
    }

    public static void a(@NonNull Object obj) {
        h.remove(obj.hashCode());
    }

    public static void a(RenderView renderView) {
        i = renderView;
    }

    public static void a(RenderView.a aVar) {
        j = aVar;
    }

    public static int a(Intent intent, a aVar) {
        d = Integer.valueOf(d.intValue() + 1);
        b.put(d, aVar);
        c.put(d, intent);
        return d.intValue();
    }

    public static void a(String[] strArr, b bVar) {
        try {
            if (com.inmobi.commons.a.a.a()) {
                f = Integer.valueOf(f.intValue() + 1);
                e.put(f, bVar);
                int iIntValue = f.intValue();
                Intent intent = new Intent(com.inmobi.commons.a.a.b(), (Class<?>) InMobiAdActivity.class);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 104);
                intent.putExtra("id", iIntValue);
                intent.putExtra(NativeProtocol.RESULT_ARGS_PERMISSIONS, strArr);
                com.inmobi.commons.a.a.a(com.inmobi.commons.a.a.b(), intent);
            }
        } catch (Exception e2) {
            new StringBuilder("SDK encountered unexpected error while requesting permissions; ").append(e2.getMessage());
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        NativeVideoView nativeVideoView;
        super.onResume();
        if (this.a) {
            return;
        }
        int i2 = this.p;
        try {
            if (100 == i2) {
                RenderView renderView = this.l;
                if (renderView != null && renderView.getFullScreenEventsListener() != null) {
                    if (!this.r) {
                        this.r = true;
                        this.l.getFullScreenEventsListener().a(this.l);
                    }
                }
            } else if (this.q == 200 && 102 == i2) {
                AdContainer adContainer = this.k;
                if (adContainer != null && adContainer.getFullScreenEventsListener() != null) {
                    if (!this.r) {
                        this.r = true;
                        this.k.getFullScreenEventsListener().a(null);
                    }
                }
            } else if (201 == this.q) {
                if ((this.k instanceof bd) && (nativeVideoView = this.o) != null) {
                    final be beVar = (be) nativeVideoView.getTag();
                    if (beVar != null && this.s) {
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.inmobi.rendering.InMobiAdActivity.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                if (InMobiAdActivity.this.k != null) {
                                    if (InMobiAdActivity.this.k.getRenderingProperties().a == AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN && ((Boolean) beVar.v.get("didCompleteQ4")).booleanValue()) {
                                        return;
                                    }
                                    InMobiAdActivity.this.o.start();
                                }
                            }
                        }, 50L);
                    }
                    if (this.k.getFullScreenEventsListener() != null) {
                        try {
                            if (!this.r) {
                                this.r = true;
                                this.k.getFullScreenEventsListener().a(beVar);
                            }
                        } catch (Exception e2) {
                            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                        }
                    }
                } else {
                    AdContainer adContainer2 = this.k;
                    if (adContainer2 instanceof ah) {
                        try {
                            if (!this.r) {
                                this.r = true;
                                adContainer2.getFullScreenEventsListener().a(null);
                            }
                        } catch (Exception e3) {
                            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        this.s = false;
    }

    @Override // android.app.Activity
    protected void onStart() {
        AdContainer adContainer;
        super.onStart();
        if (this.a || 102 != this.p || (adContainer = this.k) == null) {
            return;
        }
        ca viewableAd = adContainer.getViewableAd();
        int i2 = this.q;
        if (200 == i2) {
            if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.k.getRenderingProperties().a) {
                try {
                    viewableAd.a(this.m, this.n);
                    return;
                } catch (Exception e2) {
                    new StringBuilder("SDK encountered unexpected error in enabling impression tracking on this ad: ").append(e2.getMessage());
                    if (this.k.getFullScreenEventsListener() != null) {
                        this.k.getFullScreenEventsListener().a();
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if (201 == i2) {
            try {
                com.inmobi.ads.c cVar = new com.inmobi.ads.c();
                com.inmobi.commons.core.configs.b.a().a(cVar, (com.inmobi.commons.core.configs.b.c) null);
                if (viewableAd.b() != null) {
                    if (this.k instanceof bd) {
                        be beVar = (be) this.o.getTag();
                        if (beVar != null) {
                            com.inmobi.ads.c.k kVar = cVar.k;
                            int iIntValue = kVar.g;
                            if (beVar.G.containsKey(LocationConst.TIME)) {
                                iIntValue = ((Integer) beVar.G.get(LocationConst.TIME)).intValue();
                            }
                            kVar.g = iIntValue;
                            viewableAd.a(new View[0]);
                            return;
                        }
                        return;
                    }
                    if (this.k instanceof ah) {
                        try {
                            viewableAd.a(new View[0]);
                        } catch (Exception e3) {
                            new StringBuilder("SDK encountered unexpected error in enabling impression tracking on this ad: ").append(e3.getMessage());
                            if (this.k.getFullScreenEventsListener() != null) {
                                this.k.getFullScreenEventsListener().a();
                            }
                        }
                    }
                }
            } catch (Exception e4) {
                new StringBuilder("SDK encountered unexpected error in enabling impression tracking on this ad: ").append(e4.getMessage());
                if (this.k.getFullScreenEventsListener() != null) {
                    this.k.getFullScreenEventsListener().a();
                }
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e4));
            }
        }
    }

    @Override // android.app.Activity
    @TargetApi(23)
    protected void onCreate(Bundle bundle) {
        int intExtra;
        String[] stringArrayExtra;
        NativeVideoWrapper nativeVideoWrapper;
        com.inmobi.ads.c cVar;
        super.onCreate(bundle);
        if (!com.inmobi.commons.a.a.a()) {
            finish();
            Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "Session not found, AdActivity will be closed");
            return;
        }
        this.r = false;
        this.p = getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
        int i2 = this.p;
        if (i2 == 100) {
            String stringExtra = getIntent().getStringExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL");
            long longExtra = getIntent().getLongExtra(Constants.PLACEMENT_ID, Long.MIN_VALUE);
            boolean booleanExtra = getIntent().getBooleanExtra("allowAutoRedirection", false);
            String stringExtra2 = getIntent().getStringExtra("impressionId");
            String stringExtra3 = getIntent().getStringExtra(Constants.RequestParameters.CREATIVE_ID);
            this.l = new RenderView(this, new AdContainer.RenderingProperties(AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN), null, stringExtra2);
            this.l.setPlacementId(longExtra);
            this.l.setCreativeId(stringExtra3);
            this.l.setAllowAutoRedirection(booleanExtra);
            RenderView.a listener = RenderView.a;
            RenderView renderView = i;
            if (renderView != null) {
                listener = renderView.getListener();
                cVar = i.getAdConfig();
            } else {
                cVar = new com.inmobi.ads.c();
                RenderView.a aVar = j;
                if (aVar != null) {
                    listener = aVar;
                }
            }
            this.l.setIsInAppBrowser(true);
            this.l.a(listener, cVar);
            RelativeLayout relativeLayout = new RelativeLayout(this);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(10);
            layoutParams.addRule(2, 65533);
            relativeLayout.setBackgroundColor(-1);
            relativeLayout.addView(this.l, layoutParams);
            float f2 = com.inmobi.commons.core.utilities.b.c.a().c;
            LinearLayout linearLayout = new LinearLayout(this);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, (int) (48.0f * f2));
            linearLayout.setOrientation(0);
            linearLayout.setId(65533);
            linearLayout.setWeightSum(100.0f);
            linearLayout.setBackgroundResource(R.drawable.bottom_bar);
            linearLayout.setBackgroundColor(-7829368);
            layoutParams2.addRule(12);
            relativeLayout.addView(linearLayout, layoutParams2);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -1);
            layoutParams3.weight = 25.0f;
            CustomView customView = new CustomView(this, f2, 2);
            customView.setOnTouchListener(new View.OnTouchListener() { // from class: com.inmobi.rendering.InMobiAdActivity.2
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1) {
                        view.setBackgroundColor(-7829368);
                        InMobiAdActivity.c(InMobiAdActivity.this);
                        InMobiAdActivity.this.finish();
                        return true;
                    }
                    if (motionEvent.getAction() == 0) {
                        view.setBackgroundColor(-16711681);
                    }
                    return true;
                }
            });
            linearLayout.addView(customView, layoutParams3);
            CustomView customView2 = new CustomView(this, f2, 3);
            customView2.setOnTouchListener(new View.OnTouchListener() { // from class: com.inmobi.rendering.InMobiAdActivity.3
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1) {
                        view.setBackgroundColor(-7829368);
                        InMobiAdActivity.this.l.reload();
                        return true;
                    }
                    if (motionEvent.getAction() == 0) {
                        view.setBackgroundColor(-16711681);
                    }
                    return true;
                }
            });
            linearLayout.addView(customView2, layoutParams3);
            CustomView customView3 = new CustomView(this, f2, 4);
            customView3.setOnTouchListener(new View.OnTouchListener() { // from class: com.inmobi.rendering.InMobiAdActivity.4
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1) {
                        view.setBackgroundColor(-7829368);
                        if (InMobiAdActivity.this.l.canGoBack()) {
                            InMobiAdActivity.this.l.goBack();
                        } else {
                            InMobiAdActivity.c(InMobiAdActivity.this);
                            InMobiAdActivity.this.finish();
                        }
                        return true;
                    }
                    if (motionEvent.getAction() == 0) {
                        view.setBackgroundColor(-16711681);
                    }
                    return true;
                }
            });
            linearLayout.addView(customView3, layoutParams3);
            CustomView customView4 = new CustomView(this, f2, 6);
            customView4.setOnTouchListener(new View.OnTouchListener() { // from class: com.inmobi.rendering.InMobiAdActivity.5
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == 1) {
                        view.setBackgroundColor(-7829368);
                        if (InMobiAdActivity.this.l.canGoForward()) {
                            InMobiAdActivity.this.l.goForward();
                        }
                        return true;
                    }
                    if (motionEvent.getAction() == 0) {
                        view.setBackgroundColor(-16711681);
                    }
                    return true;
                }
            });
            linearLayout.addView(customView4, layoutParams3);
            setContentView(relativeLayout);
            this.l.loadUrl(stringExtra);
            this.l.setFullScreenActivityContext(this);
            return;
        }
        if (i2 != 102) {
            if (i2 == 103) {
                int intExtra2 = getIntent().getIntExtra("id", -1);
                if (intExtra2 != -1) {
                    startActivityForResult(c.get(Integer.valueOf(intExtra2)), intExtra2);
                    return;
                }
                return;
            }
            if (i2 != 104 || (intExtra = getIntent().getIntExtra("id", -1)) == -1 || (stringArrayExtra = getIntent().getStringArrayExtra(NativeProtocol.RESULT_ARGS_PERMISSIONS)) == null || stringArrayExtra.length <= 0 || Build.VERSION.SDK_INT < 23) {
                return;
            }
            com.inmobi.commons.core.utilities.a.b();
            requestPermissions(stringArrayExtra, intExtra);
            return;
        }
        if (getIntent().hasExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX")) {
            this.k = h.get(getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", -1));
            if (this.k == null) {
                finish();
                return;
            }
            this.q = getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", 0);
            if (this.q == 0) {
                if (this.k.getFullScreenEventsListener() != null) {
                    this.k.getFullScreenEventsListener().a();
                }
                finish();
                return;
            }
            if (getIntent().getBooleanExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", false)) {
                requestWindowFeature(1);
                getWindow().setFlags(1024, 1024);
            }
            if ((200 == this.q && !ai.a.da.equals(this.k.getMarkupType())) || (201 == this.q && !"inmobiJson".equals(this.k.getMarkupType()))) {
                if (this.k.getFullScreenEventsListener() != null) {
                    this.k.getFullScreenEventsListener().a();
                }
                finish();
                return;
            }
            try {
                this.k.setFullScreenActivityContext(this);
                FrameLayout frameLayout = (FrameLayout) findViewById(R.id.content);
                RelativeLayout relativeLayout2 = new RelativeLayout(getApplicationContext());
                relativeLayout2.setId(65534);
                float f3 = com.inmobi.commons.core.utilities.b.c.a().c;
                if (ai.a.da.equals(this.k.getMarkupType())) {
                    relativeLayout2.setBackgroundColor(0);
                    RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
                    layoutParams4.addRule(10);
                    int i3 = (int) (50.0f * f3);
                    RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(i3, i3);
                    layoutParams5.addRule(11);
                    this.m = new CustomView(this, f3, 0);
                    this.m.setId(65532);
                    this.m.setOnClickListener(new View.OnClickListener() { // from class: com.inmobi.rendering.InMobiAdActivity.6
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            InMobiAdActivity.c(InMobiAdActivity.this);
                            try {
                                InMobiAdActivity.this.k.b();
                            } catch (Exception e2) {
                                String unused = InMobiAdActivity.g;
                                new StringBuilder("Encountered unexpected error in processing close request: ").append(e2.getMessage());
                                Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
                            }
                        }
                    });
                    this.n = new CustomView(this, f3, 1);
                    this.n.setId(65531);
                    this.n.setOnClickListener(new View.OnClickListener() { // from class: com.inmobi.rendering.InMobiAdActivity.7
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            InMobiAdActivity.c(InMobiAdActivity.this);
                            try {
                                InMobiAdActivity.this.k.b();
                            } catch (Exception e2) {
                                String unused = InMobiAdActivity.g;
                                new StringBuilder("Encountered unexpected error in processing close request: ").append(e2.getMessage());
                                Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
                            }
                        }
                    });
                    View viewA = this.k.getViewableAd().a();
                    if (viewA != null) {
                        ViewGroup viewGroup = (ViewGroup) viewA.getParent();
                        if (viewGroup != null) {
                            viewGroup.removeView(viewA);
                        }
                        relativeLayout2.addView(viewA, layoutParams4);
                        relativeLayout2.addView(this.m, layoutParams5);
                        relativeLayout2.addView(this.n, layoutParams5);
                        ((RenderView) this.k).a(((RenderView) this.k).p);
                        ((RenderView) this.k).b(((RenderView) this.k).m);
                    }
                } else {
                    if (!"inmobiJson".equals(this.k.getMarkupType())) {
                        if (this.k.getFullScreenEventsListener() != null) {
                            this.k.getFullScreenEventsListener().a();
                        }
                        finish();
                        return;
                    }
                    AdContainer.RenderingProperties.PlacementType placementType = this.k.getRenderingProperties().a;
                    relativeLayout2.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                    ao aoVar = (ao) this.k.getDataModel();
                    Point point = aoVar.d.c.a;
                    com.inmobi.commons.core.configs.b.a().a(new com.inmobi.ads.c(), (com.inmobi.commons.core.configs.b.c) null);
                    ca viewableAd = this.k.getViewableAd();
                    View viewB = aoVar.c ? viewableAd.b() : null;
                    if (viewB == null) {
                        viewB = viewableAd.a(null, relativeLayout2, false);
                    }
                    if ((this.k instanceof bd) && (nativeVideoWrapper = (NativeVideoWrapper) this.k.getVideoContainerView()) != null) {
                        this.o = nativeVideoWrapper.getVideoView();
                        this.o.requestFocus();
                        be beVar = (be) this.o.getTag();
                        if (beVar.y != null) {
                            beVar.a((be) beVar.y);
                        }
                        if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE == placementType) {
                            beVar.v.put("placementType", AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE);
                        } else {
                            beVar.v.put("placementType", AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN);
                        }
                    }
                    if (viewB != null) {
                        relativeLayout2.addView(viewB, new RelativeLayout.LayoutParams(point.x, point.y));
                    }
                    this.k.setRequestedScreenOrientation();
                }
                frameLayout.addView(relativeLayout2, new RelativeLayout.LayoutParams(-1, -1));
            } catch (Exception e2) {
                this.k.setFullScreenActivityContext(null);
                if (this.k.getFullScreenEventsListener() != null) {
                    this.k.getFullScreenEventsListener().a();
                }
                finish();
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
        }
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        if (this.a) {
            return;
        }
        this.s = true;
        NativeVideoView nativeVideoView = this.o;
        if (nativeVideoView != null) {
            nativeVideoView.pause();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        AdContainer adContainer;
        be beVar;
        AdContainer adContainer2;
        if (this.a) {
            int i2 = this.p;
            if (100 == i2) {
                RenderView renderView = this.l;
                if (renderView != null && renderView.getFullScreenEventsListener() != null) {
                    try {
                        this.l.getFullScreenEventsListener().b(this.l);
                        this.l.destroy();
                        this.l = null;
                    } catch (Exception unused) {
                    }
                }
            } else if (102 == i2 && (adContainer2 = this.k) != null && adContainer2.getFullScreenEventsListener() != null) {
                int i3 = this.q;
                if (200 == i3) {
                    try {
                        this.k.getFullScreenEventsListener().b(null);
                    } catch (Exception e2) {
                        new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e2.getMessage());
                        Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                    }
                } else if (201 == i3 && Build.VERSION.SDK_INT >= 15) {
                    AdContainer adContainer3 = this.k;
                    if (adContainer3 instanceof bd) {
                        NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) ((bd) adContainer3).getVideoContainerView();
                        if (nativeVideoWrapper != null) {
                            try {
                                this.k.getFullScreenEventsListener().b((be) nativeVideoWrapper.getVideoView().getTag());
                            } catch (Exception e3) {
                                new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e3.getMessage());
                                Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
                            }
                        }
                    } else if (adContainer3 instanceof ah) {
                        try {
                            adContainer3.getFullScreenEventsListener().b(null);
                        } catch (Exception e4) {
                            new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e4.getMessage());
                            Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e4));
                        }
                    }
                }
            }
            AdContainer adContainer4 = this.k;
            if (adContainer4 != null) {
                adContainer4.destroy();
                this.k = null;
            }
        } else {
            int i4 = this.p;
            if (100 != i4 && 102 == i4 && (adContainer = this.k) != null) {
                int i5 = this.q;
                if (200 == i5) {
                    RenderView renderView2 = (RenderView) adContainer;
                    renderView2.setFullScreenActivityContext(null);
                    try {
                        renderView2.b();
                    } catch (Exception e5) {
                        new StringBuilder("Encountered unexpected error in processing close request: ").append(e5.getMessage());
                        Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
                    }
                } else if (201 == i5 && Build.VERSION.SDK_INT >= 15) {
                    AdContainer adContainer5 = this.k;
                    if (adContainer5 instanceof bd) {
                        bd bdVar = (bd) adContainer5;
                        NativeVideoView nativeVideoView = this.o;
                        if (nativeVideoView != null && (beVar = (be) nativeVideoView.getTag()) != null) {
                            if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN == bdVar.b.a) {
                                this.o.a();
                            }
                            if (this.k.getFullScreenEventsListener() != null) {
                                try {
                                    this.k.getFullScreenEventsListener().b(beVar);
                                } catch (Exception e6) {
                                    new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e6.getMessage());
                                    Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e6));
                                }
                            }
                        }
                    } else if ((adContainer5 instanceof ah) && adContainer5.getFullScreenEventsListener() != null) {
                        try {
                            this.k.getFullScreenEventsListener().b(null);
                        } catch (Exception e7) {
                            new StringBuilder("Encountered unexpected error in onAdScreenDismissed handler: ").append(e7.getMessage());
                            Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e7));
                        }
                    }
                }
                a((Object) this.k);
                this.k.destroy();
                this.k = null;
            }
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        RenderView renderView = this.l;
        if (renderView == null || !"Resized".equals(renderView.d) || renderView.getResizeProperties() == null) {
            return;
        }
        renderView.g.a();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        a aVarRemove = b.remove(Integer.valueOf(i2));
        if (aVarRemove != null) {
            c.remove(Integer.valueOf(i2));
            aVarRemove.a();
            this.a = true;
            finish();
        }
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        if (z) {
            return;
        }
        RenderView renderView = this.l;
        if (renderView != null) {
            renderView.setOrientationProperties(renderView.getOrientationProperties());
        }
        AdContainer adContainer = this.k;
        if (adContainer != null) {
            adContainer.setRequestedScreenOrientation();
        }
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
        onMultiWindowModeChanged(z);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        com.inmobi.commons.core.utilities.a.c();
        b bVarRemove = e.remove(Integer.valueOf(i2));
        if (bVarRemove != null) {
            bVarRemove.a(iArr);
        }
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        int i2 = this.p;
        if (i2 != 102) {
            if (i2 == 100) {
                this.a = true;
                finish();
                return;
            }
            return;
        }
        AdContainer adContainer = this.k;
        if (adContainer == null || adContainer.c()) {
            return;
        }
        if (200 == this.q) {
            RenderView renderView = (RenderView) this.k;
            if (renderView != null) {
                if (renderView.r != null) {
                    renderView.a(renderView.r, "broadcastEvent('backButtonPressed')");
                }
                if (renderView.q) {
                    return;
                }
                this.a = true;
                try {
                    renderView.b();
                    return;
                } catch (Exception e2) {
                    new StringBuilder("Encountered unexpected error in processing close request: ").append(e2.getMessage());
                    Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
                    return;
                }
            }
            return;
        }
        AdContainer adContainer2 = this.k;
        if (adContainer2 instanceof bd) {
            bd bdVar = (bd) adContainer2;
            if (bdVar == null || bdVar.h().b) {
                return;
            }
            this.a = true;
            NativeVideoView nativeVideoView = this.o;
            if (nativeVideoView != null) {
                be beVar = (be) nativeVideoView.getTag();
                if (beVar != null) {
                    if (AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_FULLSCREEN == bdVar.b.a) {
                        this.o.a();
                    }
                    try {
                        if (((Boolean) beVar.v.get("isFullScreen")).booleanValue()) {
                            beVar.v.put("seekPosition", Integer.valueOf(this.o.getCurrentPosition()));
                            if (bdVar.l || !((Boolean) beVar.v.get("didRequestFullScreen")).booleanValue()) {
                                return;
                            }
                            beVar.v.put("didRequestFullScreen", false);
                            if (beVar.y != null) {
                                beVar.y.v.put("didRequestFullScreen", false);
                            }
                            bdVar.b();
                            beVar.v.put("isFullScreen", false);
                            return;
                        }
                        return;
                    } catch (Exception e3) {
                        new StringBuilder("Encountered unexpected error in onVideoClosed handler: ").append(e3.getMessage());
                        Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in closing video");
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
                        return;
                    }
                }
                return;
            }
            finish();
            return;
        }
        if (adContainer2 instanceof ah) {
            ah ahVar = (ah) adContainer2;
            if (ahVar == null) {
                finish();
            } else {
                if (ahVar.h().b) {
                    return;
                }
                ahVar.b();
            }
        }
    }
}
