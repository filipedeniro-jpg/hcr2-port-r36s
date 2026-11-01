package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.ExtraHints;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.internal.api.AdNativeComponentView;
import com.facebook.ads.internal.api.NativeAdBaseApi;
import com.facebook.ads.internal.api.NativeAdImageApi;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.internal.AnalyticsEvents;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class KF implements NativeAdBaseApi {
    private static H2 o;

    @Nullable
    public C01360p B;
    private final H2 C;

    @Nullable
    private K4 D;
    private WeakReference<C0516Ff> E;
    private KW F;

    @Nullable
    private View G;
    private final List<View> H;
    private final Context I;
    private C2L J;

    @Nullable
    private B8 K;
    private View L;
    private KD M;
    private String N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private boolean R;
    private volatile boolean S;

    @Nullable
    private Drawable T;
    private K0 U;
    private AnonymousClass14 V;

    @Nullable
    private String W;

    @Nullable
    private AnonymousClass16 X;

    @Nullable
    private NativeAdLayout Y;
    private KI Z;
    private KJ a;

    @Nullable
    private AnonymousClass07 b;

    @Nullable
    private View c;

    @Nullable
    private IQ d;
    private final String e;
    private final C0621Ji f;
    private View.OnTouchListener g;
    private final String h;
    private final K9 i;
    private WeakReference<C1B> j;
    private C5I k;
    private C1B l;
    private C6 m;
    private static final String n = KF.class.getSimpleName();
    private static final WeakHashMap<View, WeakReference<KF>> p = new WeakHashMap<>();

    public KF(Context context, C01360p adapter, @Nullable IQ placementDefinition, K9 viewTraversalPredicate) {
        this(context, null, viewTraversalPredicate);
        this.B = adapter;
        this.d = placementDefinition;
        this.S = true;
        this.L = new View(context);
    }

    public KF(Context context, C01360p adapter, @Nullable IQ placementDefinition, K9 viewTraversalPredicate, @Nullable AnonymousClass07 nativeAdsManagerController) {
        this(context, adapter, placementDefinition, viewTraversalPredicate);
        this.b = nativeAdsManagerController;
    }

    public KF(Context context, String placementId, K9 viewTraversalPredicate) {
        this.h = UUID.randomUUID().toString();
        this.F = KW.NATIVE_UNKNOWN;
        this.E = new WeakReference<>(null);
        this.H = new ArrayList();
        this.f = new C0621Ji();
        this.R = false;
        this.Q = false;
        this.U = K0.ALL;
        this.V = AnonymousClass14.ALL;
        this.I = context;
        this.e = placementId;
        this.i = viewTraversalPredicate;
        if (o != null) {
            this.C = o;
        } else {
            this.C = new H2(context);
        }
        this.L = new View(context);
    }

    public KF(KF other) {
        this(other.I, null, other.i);
        this.d = other.d;
        this.B = other.B;
        this.b = other.b;
        this.S = true;
        this.L = new View(this.I);
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public final KG getAdChoicesIcon() {
        if (isAdLoaded()) {
            return this.B.C();
        }
        return null;
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final KG getAdCoverImage() {
        if (isAdLoaded()) {
            return this.B.F();
        }
        return null;
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public final KG getAdIcon() {
        if (isAdLoaded()) {
            return this.B.G();
        }
        return null;
    }

    @Nullable
    public final K4 D() {
        return this.D;
    }

    @Nullable
    public final C01360p E() {
        return this.B;
    }

    @Nullable
    public final List<KF> F() {
        if (isAdLoaded()) {
            return this.B.J();
        }
        return null;
    }

    @Nullable
    public final String G() {
        if (isAdLoaded()) {
            return this.B.AC();
        }
        return null;
    }

    public final Context H() {
        return this.I;
    }

    @Nullable
    public final String I() {
        return this.W;
    }

    public final KI J() {
        return this.Z;
    }

    public final KJ K() {
        return this.a;
    }

    @Nullable
    public final String L(String name) {
        if (isAdLoaded()) {
            return this.B.O(name);
        }
        return null;
    }

    public final KL M() {
        return !isAdLoaded() ? KL.DEFAULT : this.B.P();
    }

    @Nullable
    public final String N() {
        if (isAdLoaded()) {
            return this.B.Q();
        }
        return null;
    }

    @Nullable
    public final String O() {
        if (!isAdLoaded() || TextUtils.isEmpty(this.B.S())) {
            return null;
        }
        return this.C.K(this.B.S());
    }

    public final void P() {
        if (!C01972y.Q(this.I, false)) {
            q();
            return;
        }
        AbstractC0432Bz abstractC0432BzC = C0431By.C(this.I, JX.E(this.I), G(), this.Y);
        if (abstractC0432BzC == null) {
            q();
        } else {
            ((C0533Fw) this.Y.getNativeAdLayoutApi()).C(abstractC0432BzC);
            abstractC0432BzC.F();
        }
    }

    public final void Q(boolean wereSuccessfullyLoaded, boolean reportErrorIfNeeded) {
        if (wereSuccessfullyLoaded) {
            if (this.U.equals(K0.NONE) && !p() && this.D != null) {
                this.D.aE();
            }
            if (this.k != null) {
                this.k.F();
                return;
            }
            return;
        }
        if (this.k != null) {
            this.k.G();
        }
        if (this.D == null || !reportErrorIfNeeded) {
            return;
        }
        this.D.yD(KQ.C(AdErrorType.BROKEN_MEDIA_ERROR, "Failed to load Media."));
    }

    public final void R(@Nullable Drawable successfullyLoadedDrawable) {
        this.T = successfullyLoadedDrawable;
        Q(successfullyLoadedDrawable != null, true);
    }

    public final void S(View view, ImageView imageView) {
        ArrayList arrayList = new ArrayList();
        e(arrayList, view);
        s(view, imageView, arrayList, true);
    }

    public final void T(View view, ImageView imageView, List<View> list) {
        s(view, imageView, list, true);
    }

    public final void U(View view, AdNativeComponentView adNativeComponentView) {
        ArrayList arrayList = new ArrayList();
        e(arrayList, view);
        s(view, adNativeComponentView, arrayList, false);
    }

    public final void V(View view, AdNativeComponentView adNativeComponentView, List<View> list) {
        s(view, adNativeComponentView, list, false);
    }

    public final void W(@Nullable C0516Ff adOptionsViewApi) {
        this.E = new WeakReference<>(adOptionsViewApi);
    }

    public final void X(KW adTemplate) {
        this.F = adTemplate;
    }

    public final void Y(boolean isHScroll) {
        this.O = isHScroll;
    }

    public final void Z(@Nullable NativeAdLayout nativeAdLayout) {
        this.Y = nativeAdLayout;
    }

    public static void a(NativeAdImageApi image, ImageView imageView) {
        if (image != null && imageView != null) {
            new B7(imageView).D(image.getHeight(), image.getWidth()).E(image.getUrl());
        }
    }

    public final void a(KI attributes) {
        this.Z = attributes;
    }

    public static NativeAdBase b(Context context, String placementId, String bidPayload) throws KR {
        KW kwB = C0640Kb.B(bidPayload);
        if (kwB == null) {
            throw new KR(AdErrorType.BID_PAYLOAD_ERROR, String.format(Locale.US, "Failed to obtain template ID from bid payload '%s'", bidPayload));
        }
        if (kwB == KW.NATIVE_BANNER) {
            return new NativeBannerAd(context, placementId);
        }
        if (kwB == KW.NATIVE_UNKNOWN) {
            return new NativeAd(context, placementId);
        }
        throw new KR(AdErrorType.BID_PAYLOAD_ERROR, String.format(Locale.US, "AdTemplate '%s' is not a native ad", kwB));
    }

    public final void b(KJ nativeViewType) {
        this.a = nativeViewType;
    }

    public static K9 c() {
        return new KA();
    }

    public final void c(boolean usedByCustomVideoRendrer) {
        this.P = usedByCustomVideoRendrer;
    }

    private void d(View v) {
        this.H.add(v);
        v.setOnClickListener(this.M);
        v.setOnTouchListener(this.M);
        if (Build.VERSION.SDK_INT >= 18 && JA.s(v.getContext())) {
            v.setOnLongClickListener(this.M);
        }
    }

    public final void d(ImageView adIconView) {
        if (adIconView != null) {
            this.Q = true;
        }
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final void destroy() {
        C0642Kd.B("destroy", "Native ad destroyed");
        if (this.J != null) {
            this.J.P(true);
            this.J = null;
        }
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final void downloadMedia() {
        if (this.U.equals(K0.NONE)) {
            this.V = AnonymousClass14.MANUAL;
        }
        this.U = K0.ALL;
        r(this.B, false);
    }

    public final void e(MediaView adIconView) {
        if (adIconView != null) {
            this.Q = true;
        }
    }

    private void e(List<View> list, View v) {
        if (this.i != null && this.i.rF(v)) {
            return;
        }
        if (v instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) v;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                e(list, viewGroup.getChildAt(i));
            }
            return;
        }
        list.add(v);
    }

    private void f() {
        for (View view : this.H) {
            view.setOnClickListener(null);
            view.setOnTouchListener(null);
            view.setOnLongClickListener(null);
        }
        this.H.clear();
    }

    public final void f(MediaView mediaView) {
        if (mediaView != null) {
            this.R = true;
        }
    }

    public final void g(C1B viewabilityListener) {
        this.j = new WeakReference<>(viewabilityListener);
    }

    private final void g(K0 mediaCacheFlag, @Nullable String bidPayload) {
        if (this.S) {
            throw new IllegalStateException("loadAd cannot be called more than once");
        }
        this.S = true;
        this.U = mediaCacheFlag;
        if (mediaCacheFlag.equals(K0.NONE)) {
            this.V = AnonymousClass14.NONE;
        }
        AnonymousClass25 anonymousClass25 = new AnonymousClass25(this.e, this.F, h(), null, 1);
        anonymousClass25.F(mediaCacheFlag);
        anonymousClass25.D(this.N);
        anonymousClass25.G(this.W);
        this.J = new C2L(this.I, anonymousClass25);
        this.J.L(new K2(this));
        this.J.E(bidPayload);
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    public final String getAdBodyText() {
        if (isAdLoaded()) {
            return this.B.B();
        }
        return null;
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    public final String getAdCallToAction() {
        return L("call_to_action");
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    public final String getAdChoicesImageUrl() {
        if (getAdChoicesIcon() == null) {
            return null;
        }
        return getAdChoicesIcon().getUrl();
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    public final String getAdChoicesLinkUrl() {
        if (isAdLoaded()) {
            return this.B.D();
        }
        return null;
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    public final String getAdChoicesText() {
        if (isAdLoaded()) {
            return this.B.m223E();
        }
        return null;
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    public final String getAdHeadline() {
        return L("headline");
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    public final String getAdLinkDescription() {
        return L("link_description");
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    public final String getAdSocialContext() {
        return L(FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET);
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    public final String getAdTranslation() {
        return L("ad_translation");
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    public final String getAdUntrimmedBodyText() {
        return L(TtmlNode.TAG_BODY);
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    public final String getAdvertiserName() {
        return L("advertiser_name");
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    public final String getId() {
        if (isAdLoaded()) {
            return this.h;
        }
        return null;
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final String getPlacementId() {
        return this.e;
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    public final String getPromotedTranslation() {
        return L("promoted_translation");
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    public final String getSponsoredTranslation() {
        return L("sponsored_translation");
    }

    private AdPlacementType h() {
        return this.F == KW.NATIVE_UNKNOWN ? AdPlacementType.NATIVE : AdPlacementType.NATIVE_BANNER;
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final boolean hasCallToAction() {
        return this.B != null && this.B.V();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    @Nullable
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public final KH getAdStarRating() {
        if (isAdLoaded()) {
            return this.B.H();
        }
        return null;
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final boolean isAdInvalidated() {
        if (this.J != null) {
            return this.J.F();
        }
        if (this.b != null) {
            return this.b.D();
        }
        return true;
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final boolean isAdLoaded() {
        return this.B != null && this.B.W();
    }

    @Nullable
    private final C01360p j() {
        C01360p c01360p = this.B;
        if (c01360p == null || !c01360p.W()) {
            return null;
        }
        return c01360p;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k() {
        return M() == KL.ON;
    }

    private int l() {
        if (this.d != null) {
            return this.d.C();
        }
        if (this.J == null || this.J.D() == null) {
            return 1;
        }
        return this.J.D().C();
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final void loadAd() {
        C0642Kd.B("loadAd", "Native ad load requested");
        loadAd(NativeAdBase.MediaCacheFlag.ALL);
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final void loadAd(NativeAdBase.MediaCacheFlag mediaCacheFlag) {
        C0642Kd.B("loadAd", "Native ad load requested");
        g(K0.B(mediaCacheFlag), null);
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final void loadAdFromBid(String str) {
        C0642Kd.B("loadAdFromBid", "Native ad load requested");
        loadAdFromBid(str, NativeAdBase.MediaCacheFlag.ALL);
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final void loadAdFromBid(String str, NativeAdBase.MediaCacheFlag mediaCacheFlag) {
        C0642Kd.B("loadAdFromBid", "Native ad load requested");
        g(K0.B(mediaCacheFlag), str);
    }

    private int m() {
        if (this.d != null) {
            return this.d.J();
        }
        if (this.B != null) {
            return this.B.T();
        }
        if (this.J != null && this.J.D() != null) {
            return this.J.D().J();
        }
        return 0;
    }

    private int n() {
        if (this.d != null) {
            return this.d.K();
        }
        if (this.B != null) {
            return this.B.U();
        }
        if (this.J != null && this.J.D() != null) {
            return this.J.D().K();
        }
        return 1000;
    }

    private int o() {
        if (this.d != null) {
            return this.d.L();
        }
        if (this.J == null || this.J.D() == null) {
            return 0;
        }
        return this.J.D().L();
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final void onCtaBroadcast() {
        this.L.performClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean p() {
        return this.B != null && this.B.X();
    }

    private void q() {
        if (!TextUtils.isEmpty(getAdChoicesLinkUrl())) {
            I7.B(new I7(), this.I, Uri.parse(getAdChoicesLinkUrl()), G());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r(@Nullable C01360p nativeAdapter, boolean notifyAdLoaded) {
        if (nativeAdapter == null) {
            return;
        }
        if (this.U.equals(K0.ALL)) {
            String strAC = nativeAdapter.AC();
            if (!TextUtils.isEmpty(strAC)) {
                C0618Jf funnelLoggingHandler = new C0618Jf(strAC, JX.E(this.I));
                this.C.L(funnelLoggingHandler);
            }
            if (nativeAdapter.G() != null) {
                this.C.B(new C0560Gy(nativeAdapter.G().getUrl(), nativeAdapter.G().getHeight(), nativeAdapter.G().getWidth(), nativeAdapter.M(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE));
            }
            if (!this.F.equals(KW.NATIVE_BANNER)) {
                if (nativeAdapter.F() != null) {
                    this.C.B(new C0560Gy(nativeAdapter.F().getUrl(), nativeAdapter.F().getHeight(), nativeAdapter.F().getWidth(), nativeAdapter.M(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE));
                }
                if (nativeAdapter.J() != null) {
                    for (KF carouselAd : nativeAdapter.J()) {
                        if (carouselAd.getAdCoverImage() != null) {
                            this.C.B(new C0560Gy(carouselAd.getAdCoverImage().getUrl(), carouselAd.getAdCoverImage().getHeight(), carouselAd.getAdCoverImage().getWidth(), nativeAdapter.M(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE));
                        }
                    }
                }
                String strS = nativeAdapter.S();
                if (!TextUtils.isEmpty(strS)) {
                    this.C.E(new H0(strS, nativeAdapter.M(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE));
                }
            }
        }
        this.C.H(new K3(this, nativeAdapter, notifyAdLoaded), new C0558Gw(nativeAdapter.M(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE));
    }

    /* JADX WARN: Code duplicated, block: B:52:0x00c3  */
    private void s(View view, View nativeAdView, List<View> list, boolean isNativeBanner) {
        boolean z;
        View adContentsView = nativeAdView;
        if (view == null) {
            throw new IllegalArgumentException("Must provide a View");
        }
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("Invalid set of clickable views");
        }
        C01360p c01360pJ = j();
        if (c01360pJ == null) {
            Log.e(n, "Ad not loaded");
            return;
        }
        if (this.Y != null) {
            ((C0533Fw) this.Y.getNativeAdLayoutApi()).B();
        }
        C0516Ff c0516Ff = this.E.get();
        if (c0516Ff != null && c01360pJ.m224I() == 1) {
            c0516Ff.m251B(EnumC0606Is.AN_INFO_ICON);
        }
        if (adContentsView == null) {
            if (this.F == KW.NATIVE_UNKNOWN) {
                if (this.D != null) {
                    this.D.yD(new KQ(AdErrorType.NO_MEDIAVIEW_IN_NATIVEAD, "MediaView is missing."));
                }
                if (!AdInternalSettings.isDebugBuild()) {
                    return;
                }
                Log.e(n, "MediaView is missing.");
                return;
            }
            if (this.D != null) {
                this.D.yD(new KQ(AdErrorType.NO_ICONVIEW_IN_NATIVEBANNERAD, "AdIconView is missing."));
            }
            if (!AdInternalSettings.isDebugBuild()) {
                return;
            }
            Log.e(n, "AdIconView is missing.");
            return;
        }
        boolean nativeAdBannerViewIsImageView = (adContentsView instanceof AdNativeComponentView) && ((AdNativeComponentView) adContentsView).getAdContentsView() != null;
        if (isNativeBanner) {
            boolean nativeAdViewIsValidAdNativeComponentView = adContentsView instanceof ImageView;
            if (nativeAdViewIsValidAdNativeComponentView) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        if (!nativeAdBannerViewIsImageView && !z) {
            if (this.D == null) {
                return;
            }
            this.D.yD(new KQ(AdErrorType.UNSUPPORTED_AD_ASSET_NATIVEAD, "ad media type is not supported."));
            return;
        }
        if (this.G != null) {
            Log.w(n, "Native Ad was already registered with a View. Auto unregistering and proceeding.");
            unregisterView();
        }
        if (p.containsKey(view) && p.get(view).get() != null) {
            Log.w(n, "View already registered with a NativeAd. Auto unregistering and proceeding.");
            p.get(view).get().unregisterView();
        }
        this.M = new KD(this, null);
        this.G = view;
        this.c = adContentsView;
        if (view instanceof ViewGroup) {
            this.m = new C6(view.getContext(), new K7(this));
            ((ViewGroup) view).addView(this.m);
        }
        ArrayList<View> arrayList = new ArrayList(list);
        if (this.L != null) {
            arrayList.add(this.L);
        }
        for (View v : arrayList) {
            d(v);
        }
        this.B.h(view, arrayList);
        int iL = l();
        this.l = new K8(this, adContentsView, z);
        if (adContentsView instanceof AdNativeComponentView) {
            adContentsView = ((AdNativeComponentView) adContentsView).getAdContentsView();
        }
        this.k = new C5I(adContentsView, iL, o(), true, this.l);
        this.k.D(m());
        this.k.E(n());
        this.X = new AnonymousClass16(this.I, new KE(this, null), this.k, this.B);
        this.X.C(arrayList);
        p.put(view, new WeakReference<>(this));
        if (Build.VERSION.SDK_INT < 18 || !JA.s(this.I)) {
            return;
        }
        this.K = new B8();
        this.K.F(this.e);
        this.K.D(this.I.getPackageName());
        this.K.H(this.k);
        if (this.B.K() > 0) {
            this.K.E(this.B.K(), this.B.L());
        }
        if (this.d != null) {
            this.K.G(this.d.G());
        } else if (this.J != null && this.J.D() != null) {
            this.K.G(this.J.D().G());
        }
        this.G.getOverlay().add(this.K);
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final void setAdListener(NativeAdListener adListener, NativeAdBase ad) {
        if (adListener == null) {
            return;
        }
        t(new K5(this, adListener, ad));
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final void setExtraHints(ExtraHints extraHints) {
        if (extraHints == null) {
            return;
        }
        v(extraHints.getHints());
        this.W = extraHints.getMediationData();
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final void setOnTouchListener(View.OnTouchListener touchListener) {
        this.g = touchListener;
    }

    private final void t(K4 adListener) {
        this.D = adListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(AnonymousClass01 nativeAdapterListener) {
        if (this.B == null) {
            return;
        }
        this.B.i(nativeAdapterListener);
    }

    @Override // com.facebook.ads.internal.api.NativeAdBaseApi
    public final void unregisterView() {
        if (this.G == null || this.c == null) {
            return;
        }
        if (!p.containsKey(this.G) || p.get(this.G).get() != this) {
            throw new IllegalStateException("View not registered with this NativeAd");
        }
        if ((this.G instanceof ViewGroup) && this.m != null) {
            ((ViewGroup) this.G).removeView(this.m);
            this.m = null;
        }
        if (this.B != null) {
            this.B.j();
        }
        if (Build.VERSION.SDK_INT >= 18 && this.K != null && JA.s(this.I)) {
            this.K.B();
            this.G.getOverlay().remove(this.K);
        }
        p.remove(this.G);
        f();
        this.G = null;
        this.c = null;
        if (this.k != null) {
            this.k.G();
            this.k = null;
        }
        this.X = null;
    }

    private final void v(String extraHints) {
        this.N = extraHints;
    }
}
