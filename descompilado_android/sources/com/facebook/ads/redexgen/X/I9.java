package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.ironsource.sdk.constants.Constants;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class I9 extends AsyncTask<String, Void, I8> {
    private Context B;
    private IA C;
    private Map<String, String> D;
    private LU E;
    private Map<String, String> F;
    private static final String H = I9.class.getSimpleName();
    private static final Set<String> G = new HashSet();

    static {
        G.add("#");
        G.add("null");
    }

    public I9(Context context) {
        this(context, null, null);
    }

    public I9(Context context, Map<String, String> map) {
        this(context, map, null);
    }

    public I9(Context context, Map<String, String> map, Map<String, String> map2) {
        this.B = context;
        Map<String, String> postData = map != null ? new HashMap<>(map) : null;
        this.D = postData;
        Map<String, String> extraData = map2 != null ? new HashMap<>(map2) : null;
        this.F = extraData;
    }

    public final void A(IA delegate) {
        this.C = delegate;
    }

    private String B(String url) {
        try {
            url = C(url, "analog", MJ.C(IK.F()));
            return url;
        } catch (Exception e) {
            return url;
        }
    }

    private String C(String url, String key, String data) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(key) || TextUtils.isEmpty(data)) {
            return url;
        }
        return url + (url.contains("?") ? Constants.RequestParameters.AMPERSAND : "?") + key + Constants.RequestParameters.EQUAL + URLEncoder.encode(data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public final I8 doInBackground(String... urls) {
        String str = urls[0];
        if (TextUtils.isEmpty(str) || G.contains(str)) {
            return null;
        }
        String strB = B(str);
        if (this.D != null && !this.D.isEmpty()) {
            for (Map.Entry<String, String> entry : this.D.entrySet()) {
                String key = entry.getKey();
                String url = entry.getValue();
                strB = C(strB, key, url);
            }
        }
        int i = 1;
        while (true) {
            int i2 = i + 1;
            if (i > 2) {
                return null;
            }
            if (E(strB)) {
                return new I8(this.E);
            }
            i = i2;
        }
    }

    private boolean E(String url) {
        LI liB = IB.B(this.B);
        try {
            if (this.F == null || this.F.size() == 0) {
                this.E = liB.B(url, null);
            } else {
                LW lw = new LW();
                lw.B((Map<? extends String, ? extends String>) this.F);
                this.E = liB.D(url, lw);
            }
            return this.E != null && this.E.D() == 200;
        } catch (Exception e) {
            Log.e(H, "Error opening url: " + url, e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public final void onPostExecute(I8 response) {
        if (this.C != null) {
            this.C.eF(response);
        }
    }

    @Override // android.os.AsyncTask
    public final void onCancelled() {
        if (this.C != null) {
            this.C.cF();
        }
    }
}
