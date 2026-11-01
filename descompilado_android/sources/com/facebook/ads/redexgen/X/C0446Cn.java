package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.NativeAd;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.google.android.gms.drive.DriveFile;
import java.util.UUID;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Cn, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0446Cn extends C0447Co {
    private static final String O = C0446Cn.class.getSimpleName();
    private JV B;
    private final C1A C;

    @Nullable
    private String D;

    @Nullable
    private G9 E;

    @Nullable
    private NativeAd F;
    private final GK G;
    private final GG H;
    private final GE I;
    private final String J;

    @Nullable
    private String K;

    @Nullable
    private String L;

    @Nullable
    private Uri M;

    @Nullable
    private C7G N;

    public C0446Cn(Context context) {
        super(context);
        this.J = UUID.randomUUID().toString();
        this.I = new C0453Cu(this);
        this.H = new C0452Ct(this);
        this.G = new C0451Cs(this);
        this.C = new C1A(this, context);
        E();
    }

    public C0446Cn(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.J = UUID.randomUUID().toString();
        this.I = new C0453Cu(this);
        this.H = new C0452Ct(this);
        this.G = new C0451Cs(this);
        this.C = new C1A(this, context);
        E();
    }

    public C0446Cn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.J = UUID.randomUUID().toString();
        this.I = new C0453Cu(this);
        this.H = new C0452Ct(this);
        this.G = new C0451Cs(this);
        this.C = new C1A(this, context);
        E();
    }

    private void C(String message) {
        C0585Hx.D(getContext(), "parsing", C0584Hw.hB, new C0583Hv(AdErrorType.PARSER_FAILURE.getDefaultErrorMessage(), "Error: " + message));
        if (AdInternalSettings.isDebugBuild()) {
            Log.w(O, message);
        }
    }

    private void D(Intent intent) {
        if (this.N == null) {
            C("Must setClientToken first");
            return;
        }
        if (this.M == null && this.L == null) {
            C("Must setVideoURI or setVideoMPD first");
            return;
        }
        intent.putExtra("useNativeCtaButton", this.K);
        intent.putExtra("viewType", L2.FULL_SCREEN_VIDEO);
        intent.putExtra("videoURL", this.M.toString());
        intent.putExtra("clientToken", this.D == null ? "" : this.D);
        intent.putExtra("videoMPD", this.L);
        intent.putExtra("predefinedOrientationKey", 13);
        intent.putExtra("videoSeekTime", getCurrentPositionInMillis());
        intent.putExtra("uniqueId", this.J);
        intent.putExtra("videoLogger", this.N.getSaveInstanceState());
        intent.putExtra("video_time_polling_interval", getVideoProgressReportIntervalMs());
        intent.addFlags(DriveFile.MODE_READ_ONLY);
    }

    private void E() {
        getEventBus().B(this.I, this.H, this.G);
    }

    public final void Q() {
        if (this.F != null) {
            this.F.onCtaBroadcast();
        }
    }

    public final void R() {
        Context context = getContext();
        Intent intent = new Intent(context, (Class<?>) MH.B());
        D(intent);
        try {
            H(false);
            setVisibility(8);
            MH.E(context, intent);
        } catch (Exception e) {
            C0585Hx.D(context, "an_activity", C0584Hw.F, new C0583Hv(e));
            Log.e(AudienceNetworkAds.TAG, "Can't start AudienceNetworkActivity. Make sure that it's in your AndroidManifest.xml file.", e);
        }
    }

    @Nullable
    public G9 getListener() {
        return this.E;
    }

    public String getUniqueId() {
        return this.J;
    }

    @Override // com.facebook.ads.redexgen.X.C0447Co, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.C.A();
    }

    @Override // com.facebook.ads.redexgen.X.C0447Co, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        this.C.B();
        super.onDetachedFromWindow();
    }

    public void setAdEventManager(JV adEventManager) {
        this.B = adEventManager;
    }

    public void setClientToken(@Nullable String clientToken) {
        C7G c7g;
        if (this.N != null) {
            this.N.L();
        }
        this.D = clientToken;
        if (clientToken != null) {
            c7g = new C7G(getContext(), this.B, this, clientToken);
        } else {
            c7g = null;
        }
        this.N = c7g;
    }

    public void setEnableBackgroundVideo(boolean enabled) {
        super.B.setBackgroundPlaybackEnabled(enabled);
    }

    public void setListener(@Nullable G9 listener) {
        this.E = listener;
    }

    public void setNativeAd(@Nullable NativeAd ad) {
        this.F = ad;
    }

    public void setVideoCTA(@Nullable String cta) {
        this.K = cta;
    }

    @Override // com.facebook.ads.redexgen.X.C0447Co
    public void setVideoMPD(@Nullable String mpd) {
        if (mpd != null && this.N == null) {
            C("Must setClientToken first");
        } else {
            this.L = mpd;
            super.setVideoMPD(mpd);
        }
    }

    @Override // com.facebook.ads.redexgen.X.C0447Co
    public void setVideoURI(@Nullable Uri uri) {
        if (uri != null && this.N == null) {
            C("Must setClientToken first");
        } else {
            this.M = uri;
            super.setVideoURI(uri);
        }
    }
}
