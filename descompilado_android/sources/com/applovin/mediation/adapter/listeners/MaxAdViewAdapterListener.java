package com.applovin.mediation.adapter.listeners;

import android.view.View;
import com.applovin.mediation.adapter.MaxAdapterError;

/* JADX INFO: loaded from: classes.dex */
public interface MaxAdViewAdapterListener extends MaxAdapterListener {
    void onAdViewAdClicked();

    void onAdViewAdCollapsed();

    void onAdViewAdDisplayFailed(MaxAdapterError maxAdapterError);

    void onAdViewAdDisplayed();

    void onAdViewAdExpanded();

    void onAdViewAdHidden();

    void onAdViewAdLoadFailed(MaxAdapterError maxAdapterError);

    void onAdViewAdLoaded(View view);
}
