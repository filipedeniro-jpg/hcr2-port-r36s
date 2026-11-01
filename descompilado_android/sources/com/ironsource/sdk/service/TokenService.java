package com.ironsource.sdk.service;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.utils.SDKUtils;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class TokenService {
    private JSONObject tokeData = new JSONObject();

    synchronized void put(String str, Object obj) {
        try {
            this.tokeData.put(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void collectApplicationUserId(String str) {
        if (str != null) {
            put(Constants.RequestParameters.APPLICATION_USER_ID, SDKUtils.encodeString(str));
        }
    }

    public void collectApplicationKey(String str) {
        if (str != null) {
            put(Constants.RequestParameters.APPLICATION_KEY, SDKUtils.encodeString(str));
        }
    }

    public void collectDataFromActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            put(SDKUtils.encodeString(Constants.RequestParameters.IMMERSIVE), Boolean.valueOf(DeviceStatus.isImmersiveSupported(activity)));
        }
        put(Constants.RequestParameters.APP_ORIENTATION, SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(activity)));
    }

    public void collectAdvertisingID(final Context context) {
        try {
            new Thread(new Runnable() { // from class: com.ironsource.sdk.service.TokenService.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        TokenService.this.updateData(DeviceData.fetchAdvertiserIdData(context));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collectDataFromDevice(Context context) {
        updateData(DeviceData.fetchPermanentData(context));
        updateData(DeviceData.fetchMutableData(context));
    }

    public void collectDataFromExternalParams(Map<String, String> map) {
        if (map == null) {
            Log.d("TokenService", "collectDataFromExternalParams params=null");
            return;
        }
        for (String str : map.keySet()) {
            put(str, SDKUtils.encodeString(map.get(str)));
        }
    }

    public void collectDataFromControllerConfig(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            put("chinaCDN", new JSONObject(str).opt("chinaCDN"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateData(JSONObject jSONObject) {
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            put(next, jSONObject.opt(next));
        }
    }

    public String getToken() {
        return Gibberish.encode(this.tokeData.toString());
    }
}
