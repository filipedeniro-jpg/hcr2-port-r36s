package com.ironsource.sdk.controller;

import android.app.Application;
import android.webkit.WebView;
import com.ironsource.sdk.analytics.moat.MOATManager;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class MOATJSAdapter {
    private static final String createAdTracker = "createAdTracker";
    private static final String fail = "fail";
    private static final String initWithOptions = "initWithOptions";
    private static final String moatFunction = "moatFunction";
    private static final String moatParams = "moatParams";
    private static final String startTracking = "startTracking";
    private static final String stopTracking = "stopTracking";
    private static final String success = "success";
    private Application mApplication;

    public MOATJSAdapter(Application application) {
        this.mApplication = application;
    }

    private static class FunctionCall {
        String failCallback;
        String name;
        JSONObject params;
        String successCallback;

        private FunctionCall() {
        }
    }

    void call(String str, IronSourceWebView.JSInterface.JSCallbackTask jSCallbackTask, WebView webView) throws Exception {
        FunctionCall functionCallFetchFunctionCall = fetchFunctionCall(str);
        if (initWithOptions.equals(functionCallFetchFunctionCall.name)) {
            MOATManager.initWithOptions(functionCallFetchFunctionCall.params, this.mApplication);
            return;
        }
        if (createAdTracker.equals(functionCallFetchFunctionCall.name) && webView != null) {
            MOATManager.createAdTracker(webView);
            return;
        }
        if (startTracking.equals(functionCallFetchFunctionCall.name)) {
            MOATManager.setEventListener(createEventListener(jSCallbackTask, functionCallFetchFunctionCall.successCallback, functionCallFetchFunctionCall.failCallback));
            MOATManager.startTracking();
        } else if (stopTracking.equals(functionCallFetchFunctionCall.name)) {
            MOATManager.setEventListener(createEventListener(jSCallbackTask, functionCallFetchFunctionCall.successCallback, functionCallFetchFunctionCall.failCallback));
            MOATManager.stopTracking();
        }
    }

    private MOATManager.EventsListener createEventListener(final IronSourceWebView.JSInterface.JSCallbackTask jSCallbackTask, final String str, final String str2) {
        return new MOATManager.EventsListener() { // from class: com.ironsource.sdk.controller.MOATJSAdapter.1
            @Override // com.moat.analytics.mobile.iro.TrackerListener
            public void onTrackingStarted(String str3) {
                IronSourceWebView.JSInterface.JSCallbackTask jSCallbackTask2 = jSCallbackTask;
                if (jSCallbackTask2 != null) {
                    jSCallbackTask2.sendMessage(true, str, str3);
                }
            }

            @Override // com.moat.analytics.mobile.iro.TrackerListener
            public void onTrackingFailedToStart(String str3) {
                IronSourceWebView.JSInterface.JSCallbackTask jSCallbackTask2 = jSCallbackTask;
                if (jSCallbackTask2 != null) {
                    jSCallbackTask2.sendMessage(false, str2, str3);
                }
            }

            @Override // com.moat.analytics.mobile.iro.TrackerListener
            public void onTrackingStopped(String str3) {
                IronSourceWebView.JSInterface.JSCallbackTask jSCallbackTask2 = jSCallbackTask;
                if (jSCallbackTask2 != null) {
                    jSCallbackTask2.sendMessage(true, str, str3);
                }
            }
        };
    }

    private FunctionCall fetchFunctionCall(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        FunctionCall functionCall = new FunctionCall();
        functionCall.name = jSONObject.optString(moatFunction);
        functionCall.params = jSONObject.optJSONObject(moatParams);
        functionCall.successCallback = jSONObject.optString("success");
        functionCall.failCallback = jSONObject.optString("fail");
        return functionCall;
    }
}
