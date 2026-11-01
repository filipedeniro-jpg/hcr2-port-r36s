package com.facebook.ads.redexgen.X;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.appsflyer.AppsFlyerProperties;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.sdk.constants.Constants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0x, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01440x extends AbstractC01430w {
    private C1M B;
    private String D;
    private AnonymousClass19 E;
    private Context F;
    private C0618Jf G;
    private AnonymousClass18 H;

    @Nullable
    private String I;
    private String J;
    private long K;
    private L2 M;
    private final String L = UUID.randomUUID().toString();
    private final AtomicBoolean C = new AtomicBoolean();

    @Override // com.facebook.ads.redexgen.X.AbstractC01430w
    public final int A() {
        if (this.B == null) {
            return -1;
        }
        return this.B.D();
    }

    @Override // com.facebook.ads.redexgen.X.C0O
    public final String AC() {
        return this.B.A();
    }

    @Override // com.facebook.ads.redexgen.X.AbstractC01430w
    public final boolean B() {
        if (!this.C.get()) {
            return false;
        }
        String strF = F();
        this.B.H(strF);
        Intent intent = new Intent(this.F, (Class<?>) MH.B());
        intent.putExtra("viewType", this.M);
        intent.putExtra("rewardedVideoAdDataBundle", this.B);
        String rewardUrl = this.L;
        intent.putExtra("uniqueId", rewardUrl);
        intent.putExtra("rewardServerURL", strF);
        String rewardUrl2 = this.J;
        intent.putExtra(Constants.PLACEMENT_ID, rewardUrl2);
        intent.putExtra("requestTime", this.K);
        String rewardUrl3 = this.I;
        intent.putExtra("mediationData", rewardUrl3);
        D(intent);
        if (!(this.F instanceof Activity)) {
            intent.setFlags(intent.getFlags() | DriveFile.MODE_READ_ONLY);
        }
        String rewardUrl4 = this.B.A();
        intent.putExtra("clientToken", rewardUrl4);
        MH.E(this.F, intent);
        return true;
    }

    private void D(Intent intent) {
        if (super.B != -1 && Settings.System.getInt(this.F.getContentResolver(), "accelerometer_rotation", 0) != 1) {
            intent.putExtra("predefinedOrientationKey", super.B);
        } else {
            if (JA.E(this.F)) {
                return;
            }
            intent.putExtra("predefinedOrientationKey", 6);
        }
    }

    public final void E(Context context, AnonymousClass18 adapterListener, Map<String, Object> map, boolean failOnCacheFailure, @Nullable String extraHints, @Nullable String mediationData) {
        this.C.set(false);
        this.F = context;
        this.H = adapterListener;
        this.J = (String) map.get(Constants.PLACEMENT_ID);
        this.K = ((Long) map.get("requestTime")).longValue();
        this.I = mediationData;
        this.D = this.J != null ? this.J.split(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR)[0] : "";
        this.B = C1M.B((JSONObject) map.get("data"));
        this.B.G(extraHints);
        this.B.I(((IQ) map.get("definition")).I());
        this.G = new C0618Jf(this.B.A(), JX.E(context));
        switch (this.B.B()) {
            case 0:
                this.M = L2.REWARDED_VIDEO;
                break;
            case 1:
                this.M = L2.REWARDED_PLAYABLE;
                break;
            case 2:
                this.M = L2.REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD;
                break;
        }
        this.E = new AnonymousClass19(this.L, this, adapterListener);
        J();
        E(failOnCacheFailure);
    }

    private void E(boolean failOnCacheFailure) {
        if (this.M == L2.REWARDED_VIDEO) {
            I(failOnCacheFailure);
        } else if (this.M == L2.REWARDED_PLAYABLE) {
            H(failOnCacheFailure);
        } else {
            G(failOnCacheFailure);
        }
    }

    private String F() {
        if (super.C != null) {
            String urlPrefix = AdSettings.getUrlPrefix();
            String urlPrefix2 = (urlPrefix == null || urlPrefix.isEmpty()) ? "https://www.facebook.com/audience_network/server_side_reward" : String.format(Locale.US, "https://www.%s.facebook.com/audience_network/server_side_reward", urlPrefix);
            Uri uri = Uri.parse(urlPrefix2);
            Uri.Builder builder = new Uri.Builder();
            builder.scheme(uri.getScheme());
            builder.authority(uri.getAuthority());
            builder.path(uri.getPath());
            builder.query(uri.getQuery());
            builder.fragment(uri.getFragment());
            builder.appendQueryParameter("puid", super.C.getUserID());
            builder.appendQueryParameter("pc", super.C.getCurrency());
            builder.appendQueryParameter("ptid", this.L);
            builder.appendQueryParameter(AppsFlyerProperties.APP_ID, this.D);
            Uri serverSideURL = builder.build();
            return serverSideURL.toString();
        }
        return null;
    }

    private void G(final boolean failOnCacheFailure) {
        H2 h2 = new H2(this.F);
        h2.L(this.G);
        String strQ = "unknown";
        for (C01601n c01601n : ((C1W) this.B).K()) {
            if ("unknown".equals(strQ)) {
                strQ = c01601n.Q();
            }
            AnonymousClass23.B(h2, c01601n);
        }
        h2.H(new AbstractC01380r(failOnCacheFailure) { // from class: com.facebook.ads.redexgen.X.0t
            @Override // com.facebook.ads.redexgen.X.AbstractC01380r
            public final void A(boolean cacheSuccess) {
                this.B.C.set(true);
                this.B.H.CF(this);
            }

            @Override // com.facebook.ads.redexgen.X.AbstractC01380r
            public final void B() {
                this.B.H.FF(this, AdError.CACHE_ERROR);
            }
        }, new C0558Gw(strQ, "rewarded_video"));
    }

    private void H(boolean failOnCacheFailure) {
        AnonymousClass22.B(this.F, C01581l.C((C01601n) this.B), failOnCacheFailure, new InterfaceC01410u() { // from class: com.facebook.ads.redexgen.X.0v
            @Override // com.facebook.ads.redexgen.X.InterfaceC01410u
            public final void rE(AdError adError) {
                this.B.H.FF(this, adError);
            }

            @Override // com.facebook.ads.redexgen.X.InterfaceC01410u
            public final void sE() {
                this.B.C.set(true);
                this.B.H.CF(this);
            }
        });
    }

    private void I(final boolean failOnCacheFailure) {
        H2 cacheManager = new H2(this.F);
        cacheManager.L(this.G);
        C01601n c01601n = (C01601n) this.B;
        if (TextUtils.isEmpty(c01601n.K().H())) {
            this.H.FF(this, AdError.INTERNAL_ERROR);
        } else {
            AnonymousClass23.B(cacheManager, c01601n);
            cacheManager.H(new AbstractC01380r(failOnCacheFailure) { // from class: com.facebook.ads.redexgen.X.0s
                @Override // com.facebook.ads.redexgen.X.AbstractC01380r
                public final void A(boolean cacheSuccess) {
                    this.B.C.set(true);
                    this.B.H.CF(this);
                }

                @Override // com.facebook.ads.redexgen.X.AbstractC01380r
                public final void B() {
                    this.B.H.FF(this, AdError.CACHE_ERROR);
                }
            }, new C0558Gw(c01601n.Q(), "rewarded_video"));
        }
    }

    private void J() {
        C02464v.C(this.F).A(this.E, this.E.A());
    }

    private void K() {
        if (this.E != null) {
            try {
                C02464v.C(this.F).C(this.E);
            } catch (Exception e) {
            }
        }
    }

    @Override // com.facebook.ads.redexgen.X.C0O
    public final void onDestroy() {
        K();
    }
}
