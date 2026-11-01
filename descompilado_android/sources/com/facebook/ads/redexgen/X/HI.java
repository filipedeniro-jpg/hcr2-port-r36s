package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.share.internal.MessengerShareContentUtility;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class HI {
    public static final String I = HI.class.getSimpleName();
    public static int G = C0584Hw.l;
    public static int H = C0584Hw.p;
    public static int D = C0584Hw.i;
    public static int C = C0584Hw.h;
    public static int E = C0584Hw.j;
    public static int B = C0584Hw.f;
    public static int F = C0584Hw.k;

    private HI() {
    }

    public static boolean B() {
        int iU;
        Context contextB = HM.B();
        if (contextB == null || (iU = JA.U(contextB)) == 0) {
            return false;
        }
        if (iU > 0) {
            return LX.C() <= 1.0d / ((double) iU);
        }
        return true;
    }

    @SuppressLint({"CatchGeneralException"})
    public static void C(HH adCacheDebugData, boolean hit) {
        if (!B()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ad_format_type", adCacheDebugData.B);
            jSONObject.put("ad_request_id", adCacheDebugData.F);
            jSONObject.put("ad_creative_type", adCacheDebugData.D);
            jSONObject.put("cache_context", adCacheDebugData.E ? "load" : "show");
            String str = hit ? "Cache hit." : "Cache miss.";
            int i = hit ? G : H;
            Context contextB = HM.B();
            if (contextB == null) {
                return;
            }
            C0583Hv c0583Hv = new C0583Hv(str);
            c0583Hv.C(jSONObject);
            c0583Hv.D(1);
            C0585Hx.D(contextB, "cache", i, c0583Hv);
        } catch (Throwable t) {
            C0585Hx.C(t);
        }
    }

    public static void D(C0560Gy imageData, boolean duringAdLoading, int eventCode, @Nullable String failureReason, @Nullable Integer creativeSize, @Nullable Long loadTime) {
        if (!B()) {
            return;
        }
        H(imageData.B, imageData.D, MessengerShareContentUtility.MEDIA_IMAGE, duringAdLoading, eventCode, failureReason, creativeSize, loadTime, null);
    }

    public static void E(String url, boolean loading, int eventCode, @Nullable String failureReason, @Nullable Integer creativeSize, @Nullable Long loadTime) {
        if (!B()) {
            return;
        }
        HH hhC = HJ.C(url, loading);
        if (!HJ.D(hhC)) {
            return;
        }
        H(hhC.B, hhC.F, "video", loading, eventCode, failureReason, creativeSize, loadTime, hhC.C);
    }

    public static void F(String url, boolean hit, boolean loading) {
        if (!B()) {
            return;
        }
        HH videoAdCacheDebugData = HJ.C(url, loading);
        if (!HJ.D(videoAdCacheDebugData)) {
            return;
        }
        C(videoAdCacheDebugData, hit);
    }

    @SuppressLint({"CatchGeneralException"})
    public static void G(C0558Gw cacheData, boolean success, long requestTime) {
        if (!B()) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ad_format_type", cacheData.B);
            jSONObject.put("ad_request_id", cacheData.C);
            jSONObject.put("load_time", C0622Jj.F(System.currentTimeMillis() - requestTime));
            String str = success ? "Cache success." : "Cache fail.";
            int eventCode = success ? B : F;
            Context contextB = HM.B();
            if (contextB == null) {
                return;
            }
            C0583Hv c0583Hv = new C0583Hv(str);
            c0583Hv.C(jSONObject);
            c0583Hv.D(1);
            C0585Hx.D(contextB, "cache", eventCode, c0583Hv);
        } catch (Throwable t) {
            C0585Hx.C(t);
        }
    }

    @SuppressLint({"CatchGeneralException"})
    private static void H(String adFormat, String requestId, String creativeType, boolean duringAdLoading, int eventCode, @Nullable String failureReason, @Nullable Integer creativeSize, @Nullable Long loadTime, @Nullable Integer attempt) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ad_format_type", adFormat);
            jSONObject.put("ad_request_id", requestId);
            jSONObject.put("ad_creative_type", creativeType);
            if (failureReason != null) {
                jSONObject.put("failure_reason", failureReason);
            }
            if (creativeSize != null) {
                jSONObject.put("ad_creative_size_bytes", String.valueOf(creativeSize));
            }
            if (loadTime != null) {
                jSONObject.put("load_time", String.valueOf(loadTime));
            }
            if (attempt != null) {
                jSONObject.put("attempt", String.valueOf(attempt));
            }
            jSONObject.put("cache_context", duringAdLoading ? "load" : "show");
            String str = "Cache disk success.";
            if (eventCode == E) {
                str = "Cache download failure.";
            } else if (eventCode == C) {
                str = "Cache disk failure.";
            }
            Context contextB = HM.B();
            if (contextB != null) {
                C0583Hv c0583Hv = new C0583Hv(str);
                c0583Hv.C(jSONObject);
                c0583Hv.D(1);
                C0585Hx.D(contextB, "cache", eventCode, c0583Hv);
            }
        } catch (Throwable th) {
            C0585Hx.C(th);
        }
    }
}
