package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import com.amazon.device.ads.WebRequest;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.protocol.AdPlacementType;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0Z, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0Z implements C0O {
    private static final String J = C0Z.class.getSimpleName();

    @Nullable
    private JV B;
    private AnonymousClass10 C;

    @Nullable
    private BB D;

    @Nullable
    private C0V E;
    private String F;
    private Context G;
    private Map<String, Object> H;
    private C0U I;

    public final void A(Context context, JV adEventManager, KV adSize, C0U adapterListener, Map<String, Object> map) {
        this.G = context;
        this.B = adEventManager;
        this.I = adapterListener;
        this.H = map;
        G((IQ) this.H.get("definition"));
    }

    @Override // com.facebook.ads.redexgen.X.C0O
    public final String AC() {
        return this.F;
    }

    private void G(IQ placementDefinition) {
        JSONObject dataObject = (JSONObject) this.H.get("data");
        final C01450y c01450yB = C01450y.B(dataObject);
        this.F = c01450yB.AC();
        if (C0D.D(this.G, c01450yB, this.B)) {
            this.I.aD(this, AdError.internalError(2006));
            return;
        }
        this.E = new C0W() { // from class: com.facebook.ads.redexgen.X.0X
            @Override // com.facebook.ads.redexgen.X.C0W, com.facebook.ads.redexgen.X.C0V
            public final void HE() {
                if (this.B.C == null) {
                    return;
                }
                this.B.C.A();
            }

            @Override // com.facebook.ads.redexgen.X.C0W, com.facebook.ads.redexgen.X.C0V
            public final void hD(String url, Map<String, String> map) {
                Uri uri = Uri.parse(url);
                if ("fbad".equals(uri.getScheme()) && C0A.D(uri.getAuthority()) && this.B.I != null) {
                    this.B.I.YD(this.B);
                }
                AnonymousClass09 anonymousClass09B = C0A.B(this.B.G, this.B.B, c01450yB.AC(), uri, map);
                if (anonymousClass09B != null) {
                    try {
                        anonymousClass09B.A();
                    } catch (Exception e) {
                        Log.e(C0Z.J, "Error executing action", e);
                    }
                }
            }

            @Override // com.facebook.ads.redexgen.X.C0W, com.facebook.ads.redexgen.X.C0V
            public final void lE() {
                this.B.C.m225C();
            }
        };
        this.D = new BB(this.G, new WeakReference(this.E), placementDefinition.C());
        this.D.D(placementDefinition.J(), placementDefinition.K());
        this.C = new AnonymousClass10(this.G, this.B, this.D, this.D.getViewabilityChecker(), new C0R() { // from class: com.facebook.ads.redexgen.X.0Y
            @Override // com.facebook.ads.redexgen.X.C0R
            public final void B() {
                if (this.B.I == null) {
                    return;
                }
                this.B.I.bD(this.B);
            }
        });
        this.C.D(c01450yB);
        this.D.loadDataWithBaseURL(ID.B(), c01450yB.B(), WebRequest.CONTENT_TYPE_HTML, "utf-8", null);
        if (this.I == null) {
            return;
        }
        this.I.ZD(this, this.D);
    }

    @Override // com.facebook.ads.redexgen.X.C0O
    public final AdPlacementType HC() {
        return AdPlacementType.BANNER;
    }

    @Override // com.facebook.ads.redexgen.X.C0O
    public final void onDestroy() {
        if (this.D != null) {
            this.D.destroy();
            this.D = null;
            this.E = null;
        }
    }
}
