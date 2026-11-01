package com.unity3d.services.banners;

import android.os.ConditionVariable;
import com.ironsource.sdk.constants.Constants;
import com.unity3d.services.ads.properties.AdsProperties;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.bridge.CallbackStatus;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class BannerHide {
    private static ConditionVariable _waitHideStatus;

    public static synchronized boolean hide() throws NoSuchMethodException {
        boolean zBlock;
        Method method = BannerHide.class.getMethod("showCallback", CallbackStatus.class);
        _waitHideStatus = new ConditionVariable();
        WebViewApp.getCurrentApp().invokeMethod(Constants.ParametersKeys.WEB_VIEW, "hideBanner", method, new Object[0]);
        zBlock = _waitHideStatus.block(AdsProperties.getShowTimeout());
        _waitHideStatus = null;
        return zBlock;
    }

    public static void showCallback(CallbackStatus callbackStatus) {
        if (_waitHideStatus == null || !callbackStatus.equals(CallbackStatus.OK)) {
            return;
        }
        _waitHideStatus.open();
    }
}
