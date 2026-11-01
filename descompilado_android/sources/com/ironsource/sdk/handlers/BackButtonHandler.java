package com.ironsource.sdk.handlers;

import android.app.Activity;
import com.ironsource.sdk.agent.IronSourceAdsPublisherAgent;
import com.ironsource.sdk.controller.IronSourceWebView;
import com.ironsource.sdk.data.SSAEnums;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;

/* JADX INFO: loaded from: classes2.dex */
public class BackButtonHandler {
    public static BackButtonHandler mInstance;

    public static BackButtonHandler getInstance() {
        BackButtonHandler backButtonHandler = mInstance;
        return backButtonHandler == null ? new BackButtonHandler() : backButtonHandler;
    }

    public boolean handleBackButton(Activity activity) {
        int i = AnonymousClass1.$SwitchMap$com$ironsource$sdk$data$SSAEnums$BackButtonState[IronSourceSharedPrefHelper.getSupersonicPrefHelper().getBackButtonState().ordinal()];
        if (i == 1 || i == 2 || i != 3) {
            return false;
        }
        try {
            IronSourceWebView webViewController = IronSourceAdsPublisherAgent.getInstance(activity).getWebViewController();
            if (webViewController != null) {
                webViewController.nativeNavigationPressed("back");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: renamed from: com.ironsource.sdk.handlers.BackButtonHandler$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ironsource$sdk$data$SSAEnums$BackButtonState = new int[SSAEnums.BackButtonState.values().length];

        static {
            try {
                $SwitchMap$com$ironsource$sdk$data$SSAEnums$BackButtonState[SSAEnums.BackButtonState.None.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$ironsource$sdk$data$SSAEnums$BackButtonState[SSAEnums.BackButtonState.Device.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$ironsource$sdk$data$SSAEnums$BackButtonState[SSAEnums.BackButtonState.Controller.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
