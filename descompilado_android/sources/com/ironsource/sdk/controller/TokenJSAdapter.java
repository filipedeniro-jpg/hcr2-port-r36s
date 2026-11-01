package com.ironsource.sdk.controller;

import com.ironsource.sdk.data.SSAObj;
import com.ironsource.sdk.service.TokenService;
import com.ironsource.sdk.utils.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class TokenJSAdapter {
    private static final String TAG = "TokenJSAdapter";
    private static final String UPDATE_TOKEN = "updateToken";
    private static final String fail = "fail";
    private static final String functionName = "functionName";
    private static final String functionParams = "functionParams";
    private static final String success = "success";
    private TokenService mTokenService;

    public TokenJSAdapter(TokenService tokenService) {
        this.mTokenService = tokenService;
    }

    private FunctionCall fetchFunctionCall(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        FunctionCall functionCall = new FunctionCall();
        functionCall.name = jSONObject.optString("functionName");
        functionCall.params = jSONObject.optJSONObject("functionParams");
        functionCall.successCallback = jSONObject.optString("success");
        functionCall.failureCallback = jSONObject.optString("fail");
        return functionCall;
    }

    void call(String str, IronSourceWebView.JSInterface.JSCallbackTask jSCallbackTask) throws Exception {
        FunctionCall functionCallFetchFunctionCall = fetchFunctionCall(str);
        if (UPDATE_TOKEN.equals(functionCallFetchFunctionCall.name)) {
            updateToken(functionCallFetchFunctionCall.params, functionCallFetchFunctionCall, jSCallbackTask);
            return;
        }
        Logger.i(TAG, "TokenJSAdapter unhandled API request " + str);
    }

    public void updateToken(JSONObject jSONObject, FunctionCall functionCall, IronSourceWebView.JSInterface.JSCallbackTask jSCallbackTask) {
        SSAObj sSAObj = new SSAObj();
        try {
            this.mTokenService.updateData(jSONObject);
            jSCallbackTask.sendMessage(true, functionCall.successCallback, sSAObj);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.i(TAG, "TokenJSAdapter UPDATE_TOKEN JSON Exception when getting parameter " + e.getMessage());
            jSCallbackTask.sendMessage(false, functionCall.failureCallback, sSAObj);
        }
    }

    private static class FunctionCall {
        String failureCallback;
        String name;
        JSONObject params;
        String successCallback;

        private FunctionCall() {
        }
    }
}
