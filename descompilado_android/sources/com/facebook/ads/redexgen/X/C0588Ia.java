package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.AnyThread;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseIntArray;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.facebook.ads.internal.dynamicloading.DynamicLoaderFactory;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.settings.MultithreadedBundleWrapper;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.facebook.share.internal.ShareConstants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Ia, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public final class C0588Ia {
    private static boolean C;
    private static final Map<String, Integer> B = new HashMap();
    private static final SparseIntArray D = new SparseIntArray();

    static {
        D.put(19, 1);
        D.put(18, 2);
        D.put(20, 4);
        D.put(36, 6);
        D.put(40, 3);
        D.put(61, 5);
    }

    private C0588Ia() {
    }

    public static synchronized Integer E(String placementId) {
        int iValueOf;
        Integer num = B.get(placementId);
        if (num != null) {
            iValueOf = Integer.valueOf(D.get(num.intValue()));
        } else {
            iValueOf = 0;
        }
        return iValueOf;
    }

    @SuppressLint({"CatchGeneralException"})
    public static synchronized void F(Context context, @Nullable MultithreadedBundleWrapper multithreadedBundleWrapper, @Nullable AudienceNetworkAds.InitListener initListener) {
        C0642Kd.B("initFromAPI", "SDK initialization started");
        if (HM.B() == null) {
            Context applicationContext = context.getApplicationContext();
            HM.C(applicationContext);
            J(applicationContext);
            LD.G.execute(new IV(applicationContext, initListener));
        } else if (initListener == null) {
            Log.w(AudienceNetworkAds.TAG, "Sdk was already initialized! Skipping.");
        } else {
            M(initListener, new IZ(true, "Sdk was already initialized! Skipping."));
        }
    }

    @AnyThread
    public static synchronized void G(Context context) {
        Context contextB = HM.B();
        if (contextB == null) {
            contextB = context.getApplicationContext();
            HM.C(contextB);
            J(contextB);
            Log.e(AudienceNetworkAds.TAG, "You don't call AudienceNetworkAds.initialize(). Some functionality may not work properly.");
            C0585Hx.F(contextB, "api", C0584Hw.P, new C0583Hv("initialize() not called."));
        }
        if (!C) {
            if (JA.r(contextB)) {
                try {
                    C0566He reportHandler = new C0566He(Thread.getDefaultUncaughtExceptionHandler(), contextB, C0597Ij.B().A(contextB));
                    Thread.setDefaultUncaughtExceptionHandler(reportHandler);
                } catch (SecurityException e) {
                    Log.e(AudienceNetworkAds.TAG, "No permissions to set the default uncaught exception handler", e);
                }
            }
            C = true;
        }
    }

    public static synchronized boolean H() {
        return C;
    }

    @AnyThread
    private static void I(Context context, JSONObject payload) throws JSONException {
        Map<String, String> environmentAdParameters = C0597Ij.B().B(context, true).CC();
        for (String key : environmentAdParameters.keySet()) {
            payload.put(key, environmentAdParameters.get(key));
        }
    }

    @AnyThread
    private static synchronized void J(Context appContext) {
        if (AdInternalSettings.isDebugBuild()) {
            C0642Kd.C();
        }
        if (C0585Hx.D == null) {
            C0585Hx.D = new IY(appContext);
        }
        MI.C(appContext);
        JI.C(appContext);
        JX.E(appContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public static void K(Context appContext) throws JSONException {
        NetworkInfo activeNetwork;
        String bidderToken = DynamicLoaderFactory.makeLoader(appContext).createBidderTokenProviderApi().getBidderToken(appContext);
        ConnectivityManager connectivityManager = (ConnectivityManager) appContext.getSystemService("connectivity");
        if (connectivityManager != null && ((activeNetwork = connectivityManager.getActiveNetworkInfo()) == null || !activeNetwork.isConnectedOrConnecting())) {
            if (BuildConfigApi.isDebug()) {
                Log.e(AudienceNetworkAds.TAG, "Skipping init due to lack of connectivity.");
                return;
            }
            return;
        }
        LI liC = IB.C(appContext);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("bidder_token", bidderToken);
        I(appContext, jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("placement_formats", "fill");
        jSONObject.put("context", jSONObject2);
        jSONObject.put(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, jSONObject3);
        LW lw = new LW();
        lw.put(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, jSONObject.toString());
        LU luD = liC.D(C0661Ky.E(), lw);
        if (luD == null || luD.D() != 200) {
            return;
        }
        String strB = luD.B();
        if (TextUtils.isEmpty(strB)) {
            return;
        }
        JSONObject request = new JSONObject(strB);
        JSONObject responseJSON = request.optJSONObject("bundles").optJSONObject("placement_formats");
        HashMap map = new HashMap();
        Iterator<String> itKeys = responseJSON.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, Integer.valueOf(Integer.parseInt(responseJSON.getString(next))));
        }
        synchronized (B) {
            B.putAll(map);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @UiThread
    public static void L(AudienceNetworkAds.InitListener initListener, AudienceNetworkAds.InitResult initResult) {
        C0642Kd.B("doNotifyInitCallback", "SDK initialization complete");
        initListener.onInitialized(initResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @AnyThread
    public static void M(AudienceNetworkAds.InitListener initializationListener, AudienceNetworkAds.InitResult initResult) {
        ExecutorC0616Jd.C.execute(new IW(initializationListener, initResult));
    }
}
