package com.facebook.ads.redexgen.X;

import android.os.Build;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.ironsource.sdk.constants.Constants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class IQ {
    public int B;
    public int C;
    private boolean D;
    private List<IO> E;
    private int F;
    private int G;
    private AdPlacementType H;
    private int I;
    private int J;
    private final long K = System.currentTimeMillis();
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private static final String R = IQ.class.getSimpleName();
    private static final AdPlacementType Q = AdPlacementType.UNKNOWN;

    private IQ(Map<String, String> map) {
        this.C = -1;
        this.B = -1;
        this.H = Q;
        this.G = 1;
        this.P = 0;
        this.I = 0;
        this.J = 20;
        this.N = 0;
        this.O = 1000;
        this.L = 10000;
        this.M = 200;
        this.F = 3600;
        this.D = false;
        this.E = null;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            switch (entry.getKey()) {
                case "type":
                    this.H = AdPlacementType.fromString(entry.getValue());
                    break;
                case "min_viewability_percentage":
                    this.G = Integer.parseInt(entry.getValue());
                    break;
                case "viewability_check_ticker":
                    this.P = Integer.parseInt(entry.getValue());
                    break;
                case "refresh":
                    this.I = Integer.parseInt(entry.getValue());
                    break;
                case "refresh_threshold":
                    this.J = Integer.parseInt(entry.getValue());
                    break;
                case "invalidation_duration_in_seconds":
                    this.F = Integer.parseInt(entry.getValue());
                    break;
                case "cacheable":
                    this.D = Boolean.valueOf(entry.getValue()).booleanValue();
                    break;
                case "placement_width":
                    this.C = Integer.parseInt(entry.getValue());
                    break;
                case "placement_height":
                    this.B = Integer.parseInt(entry.getValue());
                    break;
                case "viewability_check_initial_delay":
                    this.N = Integer.parseInt(entry.getValue());
                    break;
                case "viewability_check_interval":
                    this.O = Integer.parseInt(entry.getValue());
                    break;
                case "request_timeout":
                    this.L = Integer.parseInt(entry.getValue());
                    break;
                case "conv_tracking_data":
                    this.E = IO.B(entry.getValue());
                    try {
                        CookieManager cookieManager = CookieManager.getInstance();
                        boolean zAcceptCookie = cookieManager.acceptCookie();
                        cookieManager.setAcceptCookie(true);
                        for (IO io2 : this.E) {
                            if (io2.B()) {
                                cookieManager.setCookie(io2.E, io2.D + Constants.RequestParameters.EQUAL + io2.F + ";Domain=" + io2.E + ";Expires=" + io2.A() + ";path=/");
                            }
                        }
                        if (Build.VERSION.SDK_INT < 21) {
                            CookieSyncManager.getInstance().startSync();
                        }
                        cookieManager.setAcceptCookie(zAcceptCookie);
                        break;
                    } catch (Exception e) {
                        Log.w(R, "Failed to set cookie.", e);
                        break;
                    }
                    break;
                case "video_time_polling_interval":
                    try {
                        this.M = Integer.parseInt(entry.getValue());
                        break;
                    } catch (NumberFormatException e2) {
                        this.M = 200;
                        break;
                    }
                    break;
            }
        }
    }

    public final boolean A() {
        return this.D;
    }

    public final int B() {
        return this.F * 1000;
    }

    public static IQ B(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        Iterator<String> itKeys = jsonObject.keys();
        HashMap map = new HashMap();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            map.put(next, String.valueOf(jsonObject.opt(next)));
        }
        return new IQ(map);
    }

    public final int C() {
        return this.G;
    }

    public final AdPlacementType D() {
        return this.H;
    }

    public final long E() {
        return this.I * 1000;
    }

    public final long F() {
        return this.J * 1000;
    }

    public final long G() {
        return this.K;
    }

    public final int H() {
        return this.L;
    }

    public final int I() {
        return this.M;
    }

    public final int J() {
        return this.N;
    }

    public final int K() {
        return this.O;
    }

    public final int L() {
        return this.P;
    }
}
