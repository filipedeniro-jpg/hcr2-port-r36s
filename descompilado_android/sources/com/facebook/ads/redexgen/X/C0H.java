package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.net.Uri;
import java.util.Map;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0H, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0H extends C0E {
    private Map<String, String> B;
    private final Uri C;

    public C0H(Context context, JV adEventManager, String clientToken, Uri mUri, Map<String, String> map) {
        super(context, adEventManager, clientToken, null, true);
        this.C = mUri;
        this.B = map;
    }

    @Override // com.facebook.ads.redexgen.X.C0E
    public final void D() {
        C(this.B, null);
    }

    public final void E(Map<String, String> map) {
        Map<String, String> mExtraData = this.B;
        mExtraData.putAll(map);
    }

    public final Uri F() {
        return Uri.parse(this.C.getQueryParameter("link"));
    }
}
