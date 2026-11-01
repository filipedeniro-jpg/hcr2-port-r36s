package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.api.BuildConfigApi;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.ironsource.eventsmodule.DataBaseEventsStorage;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class JU {
    private static final String O = JU.class.getSimpleName();
    private int B;
    private long C;
    private final JT D;
    private final ConnectivityManager E;
    private final Context F;
    private final long G;
    private final LI L;
    private final long M;
    private volatile boolean N;
    private final Runnable J = new JR(this);
    private final Runnable I = new JS(this);
    private final ThreadPoolExecutor K = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    private final Handler H = new Handler(Looper.getMainLooper());

    /* JADX WARN: Failed to parse debug info
    java.lang.ArrayIndexOutOfBoundsException: Index 8 out of bounds for length 7
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.startVar(DebugInfoParser.java:203)
    	at jadx.plugins.input.dex.sections.debuginfo.DebugInfoParser.process(DebugInfoParser.java:125)
    	at jadx.plugins.input.dex.sections.DexCodeReader.getDebugInfo(DexCodeReader.java:122)
    	at jadx.core.dex.nodes.MethodNode.getDebugInfo(MethodNode.java:656)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoAttachVisitor.visit(DebugInfoAttachVisitor.java:38)
     */
    @WorkerThread
    public final void C() {
        try {
            NetworkInfo activeNetworkInfo = this.E.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                if (BuildConfigApi.isDebug()) {
                    Log.e(O, "Skipping dispatch due to lack of connectivity.");
                }
                J(this.G);
                return;
            }
            if (BuildConfigApi.isDebug()) {
                Log.d(O, "Dispatching events.");
            }
            JSONObject jSONObjectLB = this.D.lB();
            if (jSONObjectLB == null) {
                if (BuildConfigApi.isDebug()) {
                    Log.d(O, "Payload json is empty.");
                }
                H();
                return;
            }
            if (BuildConfigApi.isDebug() && jSONObjectLB.has(DataBaseEventsStorage.EventEntry.TABLE_NAME)) {
                JSONArray jSONArray = jSONObjectLB.getJSONArray(DataBaseEventsStorage.EventEntry.TABLE_NAME);
                for (int i = 0; i < jSONArray.length(); i++) {
                    Log.d(O, "Dispatching event (" + i + "): " + jSONArray.get(i));
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("attempt", String.valueOf(this.B));
            G(jSONObject);
            jSONObjectLB.put("data", jSONObject);
            LW lw = new LW();
            lw.put(MessengerShareContentUtility.ATTACHMENT_PAYLOAD, jSONObjectLB.toString());
            LU luD = this.L.D(C0661Ky.C(this.F), lw);
            String strB = luD != null ? luD.B() : null;
            if (TextUtils.isEmpty(strB)) {
                if (BuildConfigApi.isDebug()) {
                    Log.e(O, "Server response is empty.");
                }
                if (jSONObjectLB.has(DataBaseEventsStorage.EventEntry.TABLE_NAME)) {
                    this.D.qD(jSONObjectLB.getJSONArray(DataBaseEventsStorage.EventEntry.TABLE_NAME));
                }
                I();
                return;
            }
            if (luD.D() != 200) {
                if (BuildConfigApi.isDebug()) {
                    Log.e(O, "Server returned a non-successful status code of " + luD.D() + ".");
                }
                if (luD.D() == 413 && JA.TB(this.F)) {
                    this.D.wE();
                    H();
                    return;
                } else {
                    if (jSONObjectLB.has(DataBaseEventsStorage.EventEntry.TABLE_NAME)) {
                        this.D.qD(jSONObjectLB.getJSONArray(DataBaseEventsStorage.EventEntry.TABLE_NAME));
                    }
                    I();
                    return;
                }
            }
            if (!this.D.sD(new JSONArray(strB))) {
                if (BuildConfigApi.isDebug()) {
                    Log.w(O, "Server was unable to process all events, trying again.");
                }
                I();
            } else {
                if (!this.D.WC()) {
                    H();
                    return;
                }
                if (BuildConfigApi.isDebug()) {
                    Log.i(O, "Server processed partial batch, continuing to next one.");
                }
                I();
            }
        } catch (Exception e) {
            if (BuildConfigApi.isDebug()) {
                Log.e(O, "Exception while dispatching events.", e);
            }
            I();
        }
    }

    @UiThread
    public JU(Context context, JT mCallback) {
        this.D = mCallback;
        this.F = context;
        this.E = (ConnectivityManager) context.getSystemService("connectivity");
        this.L = IB.C(context);
        this.M = JA.n(context);
        this.G = JA.l(context);
    }

    public final void A() {
        if (this.N) {
            return;
        }
        this.N = true;
        this.H.removeCallbacks(this.I);
        J(this.G);
    }

    public static /* synthetic */ int B(JU x0) {
        int i = x0.B + 1;
        x0.B = i;
        return i;
    }

    public final void B() {
        this.N = true;
        this.H.removeCallbacks(this.I);
        J(this.M);
    }

    private void G(JSONObject dataJson) throws JSONException {
        Map<String, String> mapB = C0591Id.B(this.F);
        for (String str : mapB.keySet()) {
            dataJson.put(str, mapB.get(str));
        }
    }

    private void H() {
        if (BuildConfigApi.isDebug()) {
            Log.d(O, "Finishing dispatch.");
        }
        this.B = 0;
        this.C = 0L;
        if (this.K.getQueue().size() == 0) {
            this.D.UD();
        }
    }

    private void I() {
        if (this.B >= 5) {
            H();
            A();
        } else {
            if (this.B == 1) {
                this.C = 2000L;
            } else {
                this.C *= 2;
            }
            B();
        }
    }

    private void J(long delayMs) {
        this.H.postDelayed(this.I, delayMs);
    }
}
