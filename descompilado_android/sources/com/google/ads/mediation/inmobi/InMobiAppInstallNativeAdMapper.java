package com.google.ads.mediation.inmobi;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.inmobi.ads.InMobiNative;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class InMobiAppInstallNativeAdMapper extends NativeAppInstallAdMapper {
    private String[] mImpressionTrackers;
    private final InMobiAdapter mInMobiAdapter;
    private final InMobiNative mInMobiNative;
    private final Boolean mIsOnlyURL;
    private final HashMap<String, String> mLandingUrlMap = new HashMap<>();
    private final MediationNativeListener mMediationNativeListener;

    @Override // com.google.android.gms.ads.mediation.NativeAdMapper
    public void recordImpression() {
    }

    @Override // com.google.android.gms.ads.mediation.NativeAdMapper
    public void trackView(View view) {
    }

    InMobiAppInstallNativeAdMapper(InMobiAdapter inMobiAdapter, InMobiNative inMobiNative, Boolean bool, MediationNativeListener mediationNativeListener) {
        this.mInMobiAdapter = inMobiAdapter;
        this.mInMobiNative = inMobiNative;
        this.mIsOnlyURL = bool;
        this.mMediationNativeListener = mediationNativeListener;
    }

    void mapAppInstallAd(Context context) {
        try {
            if (this.mInMobiNative.getCustomAdContent() != null) {
                JSONObject customAdContent = this.mInMobiNative.getCustomAdContent();
                setHeadline((String) InMobiAdapterUtils.mandatoryChecking(this.mInMobiNative.getAdTitle(), "title"));
                setBody((String) InMobiAdapterUtils.mandatoryChecking(this.mInMobiNative.getAdDescription(), "description"));
                setCallToAction((String) InMobiAdapterUtils.mandatoryChecking(this.mInMobiNative.getAdCtaText(), InMobiNetworkValues.CTA));
                String str = (String) InMobiAdapterUtils.mandatoryChecking(this.mInMobiNative.getAdLandingPageUrl(), InMobiNetworkValues.LANDING_URL);
                Bundle bundle = new Bundle();
                bundle.putString(InMobiNetworkValues.LANDING_URL, str);
                setExtras(bundle);
                this.mLandingUrlMap.put(InMobiNetworkValues.LANDING_URL, str);
                HashMap map = new HashMap();
                URL url = new URL(this.mInMobiNative.getAdIconUrl());
                final Uri uri = Uri.parse(url.toURI().toString());
                final Double dValueOf = Double.valueOf(1.0d);
                if (!this.mIsOnlyURL.booleanValue()) {
                    map.put("icon_key", url);
                } else {
                    setIcon(new InMobiNativeMappedImage(null, uri, dValueOf.doubleValue()));
                    List<NativeAd.Image> arrayList = new ArrayList<>();
                    arrayList.add(new InMobiNativeMappedImage(new ColorDrawable(0), null, 1.0d));
                    setImages(arrayList);
                }
                try {
                    if (customAdContent.has("rating")) {
                        setStarRating(Double.parseDouble(customAdContent.getString("rating")));
                    }
                    if (customAdContent.has(InMobiNetworkValues.PACKAGE_NAME)) {
                        setStore("Google Play");
                    } else {
                        setStore("Others");
                    }
                    if (customAdContent.has("price")) {
                        setPrice(customAdContent.getString("price"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final RelativeLayout relativeLayout = new RelativeLayout(context);
                relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.google.ads.mediation.inmobi.InMobiAppInstallNativeAdMapper.1
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public void onGlobalLayout() {
                        InMobiNative inMobiNative = InMobiAppInstallNativeAdMapper.this.mInMobiNative;
                        RelativeLayout relativeLayout2 = relativeLayout;
                        relativeLayout.addView(inMobiNative.getPrimaryViewOfWidth(null, relativeLayout2, relativeLayout2.getWidth()));
                        if (Build.VERSION.SDK_INT >= 16) {
                            relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        } else {
                            relativeLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }
                    }
                });
                setMediaView(relativeLayout);
                setHasVideoContent(true);
                setOverrideClickHandling(false);
                if (!this.mIsOnlyURL.booleanValue()) {
                    new ImageDownloaderAsyncTask(new ImageDownloaderAsyncTask.DrawableDownloadListener() { // from class: com.google.ads.mediation.inmobi.InMobiAppInstallNativeAdMapper.2
                        @Override // com.google.ads.mediation.inmobi.ImageDownloaderAsyncTask.DrawableDownloadListener
                        public void onDownloadSuccess(HashMap<String, Drawable> map2) {
                            Drawable drawable = map2.get("icon_key");
                            InMobiAppInstallNativeAdMapper.this.setIcon(new InMobiNativeMappedImage(drawable, uri, dValueOf.doubleValue()));
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(new InMobiNativeMappedImage(new ColorDrawable(0), null, 1.0d));
                            InMobiAppInstallNativeAdMapper.this.setImages(arrayList2);
                            if (drawable != null) {
                                InMobiAppInstallNativeAdMapper.this.mMediationNativeListener.onAdLoaded(InMobiAppInstallNativeAdMapper.this.mInMobiAdapter, InMobiAppInstallNativeAdMapper.this);
                            } else {
                                InMobiAppInstallNativeAdMapper.this.mMediationNativeListener.onAdFailedToLoad(InMobiAppInstallNativeAdMapper.this.mInMobiAdapter, 2);
                            }
                        }

                        @Override // com.google.ads.mediation.inmobi.ImageDownloaderAsyncTask.DrawableDownloadListener
                        public void onDownloadFailure() {
                            InMobiAppInstallNativeAdMapper.this.mMediationNativeListener.onAdFailedToLoad(InMobiAppInstallNativeAdMapper.this.mInMobiAdapter, 3);
                        }
                    }).execute(map);
                    return;
                } else {
                    this.mMediationNativeListener.onAdLoaded(this.mInMobiAdapter, this);
                    return;
                }
            }
            this.mMediationNativeListener.onAdFailedToLoad(this.mInMobiAdapter, 3);
        } catch (MandatoryParamException | MalformedURLException | URISyntaxException e2) {
            e2.printStackTrace();
            this.mMediationNativeListener.onAdFailedToLoad(this.mInMobiAdapter, 3);
        }
    }

    @Override // com.google.android.gms.ads.mediation.NativeAdMapper
    public void handleClick(View view) {
        this.mInMobiNative.reportAdClickAndOpenLandingPage();
    }

    @Override // com.google.android.gms.ads.mediation.NativeAdMapper
    public void untrackView(View view) {
        this.mInMobiNative.destroy();
    }
}
