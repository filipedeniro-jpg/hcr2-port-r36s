package com.ironsource.sdk.controller;

import android.text.TextUtils;
import com.ironsource.sdk.ISNAdView.ISNAdView;
import com.ironsource.sdk.ISNAdView.ISNAdViewDelegate;
import com.ironsource.sdk.utils.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class BannerJSAdapter implements ISNAdViewDelegate {
    public static final String ERR_MSG = "errMsg";
    public static final String FAIL = "fail";
    public static final String FUNCTION_NAME = "functionName";
    public static final String FUNCTION_PARAMS = "functionParams";
    public static final String SUCCESS = "success";
    private static final String TAG = "BannerJSAdapter";
    private WebViewMessagingMediator mControllerMessageMediator;
    private ISNAdView mIsnAdView;

    public void setCommunicationWithController(WebViewMessagingMediator webViewMessagingMediator) {
        this.mControllerMessageMediator = webViewMessagingMediator;
    }

    public void setCommunicationWithAdView(ISNAdView iSNAdView) {
        this.mIsnAdView = iSNAdView;
        this.mIsnAdView.setControllerDelegate(this);
    }

    @Override // com.ironsource.sdk.ISNAdView.ISNAdViewDelegate
    public void sendMessageToController(String str, JSONObject jSONObject) {
        if (this.mControllerMessageMediator == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.mControllerMessageMediator.sendMessageToController(str, jSONObject);
    }

    @Override // com.ironsource.sdk.ISNAdView.ISNAdViewDelegate
    public void sendErrorMessageToController(String str, final String str2) {
        sendMessageToController(str, new JSONObject() { // from class: com.ironsource.sdk.controller.BannerJSAdapter.1
            {
                try {
                    put("errMsg", str2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void sendMessageToISNAdView(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString(FUNCTION_NAME);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(FUNCTION_PARAMS);
            String strOptString2 = jSONObject.optString("success");
            String strOptString3 = jSONObject.optString(FAIL);
            if (TextUtils.isEmpty(strOptString)) {
                Logger.i(TAG, "BannerJSAdapter | sendMessageToISNAdView | Invalid message format: " + str);
                return;
            }
            if (this.mIsnAdView == null) {
                sendErrorMessageToController(strOptString3, "Send message to ISNAdView failed");
            } else {
                this.mIsnAdView.receiveMessageFromController(strOptString, jSONObjectOptJSONObject, strOptString2, strOptString3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
