package com.ironsource.sdk;

import android.app.Activity;
import com.ironsource.sdk.agent.IronSourceAdsAdvertiserAgent;
import com.ironsource.sdk.agent.IronSourceAdsPublisherAgent;
import com.ironsource.sdk.data.SSAEnums;

/* JADX INFO: loaded from: classes2.dex */
public class SSAFactory {
    public static SSAPublisher getPublisherInstance(Activity activity) throws Exception {
        return IronSourceAdsPublisherAgent.getInstance(activity);
    }

    public static SSAPublisher getPublisherTestInstance(Activity activity) throws Exception {
        return IronSourceAdsPublisherAgent.getInstance(activity, SSAEnums.DebugMode.MODE_2.getValue());
    }

    public static SSAPublisher getPublisherTestInstance(Activity activity, int i) throws Exception {
        return IronSourceAdsPublisherAgent.getInstance(activity, i);
    }

    public static SSAAdvertiser getAdvertiserInstance() {
        return IronSourceAdsAdvertiserAgent.getInstance();
    }
}
