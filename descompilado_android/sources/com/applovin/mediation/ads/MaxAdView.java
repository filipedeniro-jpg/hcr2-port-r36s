package com.applovin.mediation.ads;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.mediation.ads.MaxAdViewImpl;
import com.applovin.impl.sdk.utils.n;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.sdk.AppLovinSdk;

/* JADX INFO: loaded from: classes.dex */
public class MaxAdView extends RelativeLayout {
    private MaxAdViewImpl a;

    public MaxAdView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaxAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        String attributeValue = attributeSet.getAttributeValue(AppLovinAdView.NAMESPACE, "adUnitId");
        if (attributeValue == null) {
            throw new IllegalArgumentException("No ad unit ID specified");
        }
        if (TextUtils.isEmpty(attributeValue)) {
            throw new IllegalArgumentException("Empty ad unit ID specified");
        }
        if (!(context instanceof Activity)) {
            throw new IllegalArgumentException("Max ad view context is not an activity");
        }
        Activity activity = (Activity) context;
        a(attributeValue, AppLovinSdk.getInstance(activity), activity);
    }

    public MaxAdView(String str, Activity activity) {
        this(str, AppLovinSdk.getInstance(activity), activity);
    }

    public MaxAdView(String str, AppLovinSdk appLovinSdk, Activity activity) {
        super(activity);
        a(str, appLovinSdk, activity);
    }

    private void a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int iApplyDimension = (int) TypedValue.applyDimension(1, 50.0f, displayMetrics);
        TextView textView = new TextView(context);
        textView.setBackgroundColor(Color.rgb(220, 220, 220));
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        textView.setText("Max Ad");
        textView.setGravity(17);
        addView(textView, i, iApplyDimension);
    }

    private void a(String str, AppLovinSdk appLovinSdk, Activity activity) {
        if (isInEditMode()) {
            a(activity);
        } else {
            this.a = new MaxAdViewImpl(str, this, n.a(appLovinSdk), activity);
        }
    }

    public void destroy() {
        this.a.destroy();
    }

    public String getPlacement() {
        return this.a.getPlacement();
    }

    public void loadAd() {
        this.a.loadAd();
    }

    public void setExtraParameter(String str, String str2) {
        this.a.setExtraParameter(str, str2);
    }

    public void setListener(MaxAdViewAdListener maxAdViewAdListener) {
        this.a.setListener(maxAdViewAdListener);
    }

    public void setPlacement(String str) {
        this.a.setPlacement(str);
    }

    public void startAutoRefresh() {
        this.a.startAutoRefresh();
    }

    public void stopAutoRefresh() {
        this.a.stopAutoRefresh();
    }

    @Override // android.view.View
    public String toString() {
        MaxAdViewImpl maxAdViewImpl = this.a;
        return maxAdViewImpl != null ? maxAdViewImpl.toString() : "MaxAdView";
    }
}
