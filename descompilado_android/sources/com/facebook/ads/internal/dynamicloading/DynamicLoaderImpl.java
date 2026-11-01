package com.facebook.ads.internal.dynamicloading;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.InstreamVideoAdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdScrollView;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.NativeAdsManager;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.internal.api.AdChoicesViewApi;
import com.facebook.ads.internal.api.AdOptionsViewApi;
import com.facebook.ads.internal.api.AdSettingsApi;
import com.facebook.ads.internal.api.AdSizeApi;
import com.facebook.ads.internal.api.AdViewApi;
import com.facebook.ads.internal.api.AdViewParentApi;
import com.facebook.ads.internal.api.AdsMessengerServiceApi;
import com.facebook.ads.internal.api.AudienceNetworkActivityApi;
import com.facebook.ads.internal.api.AudienceNetworkAdsApi;
import com.facebook.ads.internal.api.BidderTokenProviderApi;
import com.facebook.ads.internal.api.DefaultMediaViewVideoRendererApi;
import com.facebook.ads.internal.api.InstreamVideoAdViewApi;
import com.facebook.ads.internal.api.InterstitialAdApi;
import com.facebook.ads.internal.api.MediaViewApi;
import com.facebook.ads.internal.api.MediaViewVideoRendererApi;
import com.facebook.ads.internal.api.NativeAdBaseApi;
import com.facebook.ads.internal.api.NativeAdImageApi;
import com.facebook.ads.internal.api.NativeAdLayoutApi;
import com.facebook.ads.internal.api.NativeAdScrollViewApi;
import com.facebook.ads.internal.api.NativeAdViewApi;
import com.facebook.ads.internal.api.NativeAdViewAttributesApi;
import com.facebook.ads.internal.api.NativeAdViewTypeApi;
import com.facebook.ads.internal.api.NativeAdsManagerApi;
import com.facebook.ads.internal.api.NativeBannerAdApi;
import com.facebook.ads.internal.api.NativeBannerAdViewApi;
import com.facebook.ads.internal.api.NativeComponentTagApi;
import com.facebook.ads.internal.api.RewardedVideoAdApi;
import com.facebook.ads.internal.ipc.AdsMessengerService;
import com.facebook.ads.redexgen.X.C0513Fc;
import com.facebook.ads.redexgen.X.C0516Ff;
import com.facebook.ads.redexgen.X.C0517Fg;
import com.facebook.ads.redexgen.X.C0520Fj;
import com.facebook.ads.redexgen.X.C0523Fm;
import com.facebook.ads.redexgen.X.C0531Fu;
import com.facebook.ads.redexgen.X.C0532Fv;
import com.facebook.ads.redexgen.X.C0533Fw;
import com.facebook.ads.redexgen.X.C0536Fz;
import com.facebook.ads.redexgen.X.C0537Ga;
import com.facebook.ads.redexgen.X.C0542Gf;
import com.facebook.ads.redexgen.X.C0543Gg;
import com.facebook.ads.redexgen.X.C0544Gh;
import com.facebook.ads.redexgen.X.C0545Gi;
import com.facebook.ads.redexgen.X.C0638Jz;
import com.facebook.ads.redexgen.X.G3;
import com.facebook.ads.redexgen.X.G4;
import com.facebook.ads.redexgen.X.GB;
import com.facebook.ads.redexgen.X.GQ;
import com.facebook.ads.redexgen.X.GS;
import com.facebook.ads.redexgen.X.GV;
import com.facebook.ads.redexgen.X.GW;
import com.facebook.ads.redexgen.X.GX;
import com.facebook.ads.redexgen.X.KF;
import com.facebook.ads.redexgen.X.KG;
import com.facebook.ads.redexgen.X.KH;
import com.facebook.ads.redexgen.X.KI;
import com.facebook.ads.redexgen.X.KR;
import com.facebook.ads.redexgen.X.KV;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class DynamicLoaderImpl implements DynamicLoader {
    private static AdSettingsApi B;
    private static AudienceNetworkAdsApi C;
    private static BidderTokenProviderApi D;
    private static NativeAdViewApi E;
    private static NativeBannerAdViewApi F;

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public AdChoicesViewApi createAdChoicesViewApi(AdChoicesView adChoicesView, Context c, NativeAdBase ad) {
        return new C0513Fc(adChoicesView, c, ad);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public AdOptionsViewApi createAdOptionsView(Context context, NativeAdBase nativeAdBase, @Nullable NativeAdLayout nativeAdLayout, AdOptionsView.Orientation orientation, int iconSizeDp, AdOptionsView adOptionsView) {
        return new C0516Ff(context, nativeAdBase, nativeAdLayout, orientation, iconSizeDp, adOptionsView);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public AdOptionsViewApi createAdOptionsView(Context context, NativeAdBase nativeAdBase, @Nullable NativeAdLayout nativeAdLayout, AdOptionsView adOptionsView) {
        return new C0516Ff(context, nativeAdBase, nativeAdLayout, adOptionsView);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public AdSettingsApi createAdSettingsApi() {
        if (B == null) {
            B = new C0517Fg();
        }
        return B;
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public AdSizeApi createAdSizeApi(int sizeType) {
        return KV.C(sizeType);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public AdViewApi createAdViewApi(Context context, String placementId, AdSize adSize, AdViewParentApi adViewParentApi, AdView view) {
        return new C0520Fj(context, placementId, adSize, adViewParentApi, view);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public AdViewApi createAdViewApi(Context context, String placementId, String bidPayload, AdViewParentApi adViewParentApi, AdView view) throws Exception {
        try {
            return new C0520Fj(context, placementId, bidPayload, adViewParentApi, view);
        } catch (KR e) {
            throw new Exception(e.B());
        }
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public AdsMessengerServiceApi createAdsMessengerServiceApi(AdsMessengerService service) {
        return new C0523Fm(service);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public AudienceNetworkActivityApi createAudienceNetworkActivity(AudienceNetworkActivity activity, AudienceNetworkActivityApi activityParentApi) {
        return new C0531Fu(activity, activityParentApi);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public AudienceNetworkAdsApi createAudienceNetworkAdsApi() {
        if (C == null) {
            C = new C0532Fv();
        }
        return C;
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public BidderTokenProviderApi createBidderTokenProviderApi() {
        if (D == null) {
            D = new C0536Fz();
        }
        return D;
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public DefaultMediaViewVideoRendererApi createDefaultMediaViewVideoRendererApi() {
        return new C0638Jz();
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public InstreamVideoAdViewApi createInstreamVideoAdViewApi(InstreamVideoAdView instreamVideoAdView, Context context, Bundle previousState) {
        return new G3(instreamVideoAdView, context, previousState);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public InstreamVideoAdViewApi createInstreamVideoAdViewApi(InstreamVideoAdView instreamVideoAdView, Context context, String placementID, AdSize adSize) {
        return new G3(instreamVideoAdView, context, placementID, adSize);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public InterstitialAdApi createInterstitialAd(Context context, String placementId, InterstitialAd ad) {
        return new G4(context, placementId, ad);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public MediaViewApi createMediaViewApi() {
        return new GB();
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public MediaViewVideoRendererApi createMediaViewVideoRendererApi() {
        return new GQ();
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public GS createNativeAdApi(NativeAd nativeAd, NativeAdBaseApi nativeAdBaseApi) {
        return new GS(nativeAd, nativeAdBaseApi);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public GS createNativeAdApi(NativeAdBase other, NativeAd nativeAd, NativeAdBaseApi nativeAdBaseApi) {
        return new GS(other, nativeAd, nativeAdBaseApi);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public NativeAdBaseApi createNativeAdBaseApi(Context context, String placementId) {
        return new KF(context, placementId, KF.c());
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public NativeAdBaseApi createNativeAdBaseApi(NativeAdBaseApi other) {
        return new KF((KF) other);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public NativeAdBase createNativeAdBaseFromBidPayload(Context context, String placementId, String bidPayload) throws Exception {
        try {
            return KF.b(context, placementId, bidPayload);
        } catch (KR e) {
            throw new Exception(e.B());
        }
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    @Nullable
    public NativeAdImageApi createNativeAdImageApi(JSONObject jsonObject) {
        return KG.B(jsonObject);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public NativeAdLayoutApi createNativeAdLayoutApi() {
        return new C0533Fw();
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    @Nullable
    public KH createNativeAdRatingApi(JSONObject jsonObject) {
        return KH.B(jsonObject);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public NativeAdScrollViewApi createNativeAdScrollViewApi(NativeAdScrollView nativeAdScrollView, Context context, NativeAdsManager manager, @Nullable NativeAdScrollView.AdViewProvider viewProvider, int nativeAdViewHeightDp, @Nullable NativeAdView.Type type, NativeAdViewAttributes attributes, int maxAds) {
        return new GV(nativeAdScrollView, context, manager, viewProvider, nativeAdViewHeightDp, type, attributes, maxAds);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public NativeAdViewApi createNativeAdViewApi() {
        if (E == null) {
            E = new GW();
        }
        return E;
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public NativeAdViewAttributesApi createNativeAdViewAttributesApi() {
        return new KI();
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public NativeAdViewTypeApi createNativeAdViewTypeApi(int enumCode) {
        return new GX(enumCode);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public NativeAdsManagerApi createNativeAdsManagerApi(Context context, String placementId, int numAdsRequested) {
        return new C0537Ga(context, placementId, numAdsRequested);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public NativeBannerAdApi createNativeBannerAdApi(NativeAdBaseApi nativeAdBaseApi) {
        return new C0542Gf(nativeAdBaseApi);
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public NativeBannerAdViewApi createNativeBannerAdViewApi() {
        if (F == null) {
            F = new C0543Gg();
        }
        return F;
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public NativeComponentTagApi createNativeComponentTagApi() {
        return new C0544Gh();
    }

    @Override // com.facebook.ads.internal.dynamicloading.DynamicLoader
    public RewardedVideoAdApi createRewardedVideoAd(Context context, String placementId, RewardedVideoAd ad) {
        return new C0545Gi(context, placementId, ad);
    }
}
