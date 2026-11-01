package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5D, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@Singleton
public final class C5D {
    private static int I = 0;
    private final C3L B;
    private final C3K C;
    private final int D;
    private final Context E;
    private final boolean F;
    private final ThreadFactoryC0664Lb G = new ThreadFactoryC0664Lb();
    private final ThreadPoolExecutor H = (ThreadPoolExecutor) Executors.newCachedThreadPool(this.G);

    /* JADX WARN: Type inference failed for: r1v0, types: [com.facebook.ads.redexgen.X.3M] */
    public C5D(final Context context) {
        this.E = context;
        this.B = new Object() { // from class: com.facebook.ads.redexgen.X.3M
            private boolean B;
            private boolean C;
            private boolean D;
            private boolean E;
            private boolean F;
            private boolean G;
            private boolean H;
            private boolean I;
            private boolean J;
            private boolean K;
            private boolean L;
            private boolean M;
            private boolean N;
            private boolean O;
            private boolean P;
            private boolean Q;
            private boolean R;
            private boolean S;
            private boolean T;
            private boolean U;
            private boolean V;
            private String W;
            private int X;
            private int Y;
            private C5C Z;

            public final C3L A() {
                C3L c3l = new C3L();
                c3l.P = this.P;
                c3l.W = this.W;
                c3l.O = this.O;
                c3l.Y = this.Y;
                c3l.X = this.X;
                c3l.Z = this.Z;
                c3l.M = this.M;
                c3l.G = this.G;
                c3l.B = this.B;
                c3l.R = this.R;
                c3l.U = this.U;
                c3l.Q = this.Q;
                c3l.E = this.E;
                c3l.L = this.L;
                c3l.V = this.V;
                c3l.J = this.J;
                c3l.F = this.F;
                c3l.N = this.N;
                c3l.S = this.S;
                c3l.I = this.I;
                c3l.C = this.C;
                c3l.H = this.H;
                c3l.K = this.K;
                c3l.T = this.T;
                c3l.D = this.D;
                return c3l;
            }

            public final C3M B(boolean isActivityManagerSignalEnabled) {
                this.B = isActivityManagerSignalEnabled;
                return this;
            }

            public final C3M C(boolean isApplicationInfoSignalEnabled) {
                this.E = isApplicationInfoSignalEnabled;
                return this;
            }

            public final C3M D(boolean isAudioManagerSignalEnabled) {
                this.F = isAudioManagerSignalEnabled;
                return this;
            }

            public final C3M E(boolean isBatterySignalEnabled) {
                this.G = isBatterySignalEnabled;
                return this;
            }

            public final C3M F(boolean isConnectivityManagerSignalEnabled) {
                this.I = isConnectivityManagerSignalEnabled;
                return this;
            }

            public final C3M G(boolean isDeviceSettingsSignalEnabled) {
                this.J = isDeviceSettingsSignalEnabled;
                return this;
            }

            public final C3M H(boolean isKeyguardManagerSignalEnabled) {
                this.L = isKeyguardManagerSignalEnabled;
                return this;
            }

            public final C3M I(boolean isNetworkNativeSignalEnabled) {
                this.N = isNetworkNativeSignalEnabled;
                return this;
            }

            public final C3M J(boolean isPackageManagerSignalEnabled) {
                this.Q = isPackageManagerSignalEnabled;
                return this;
            }

            public final C3M K(boolean isPowerManagerSignalEnabled) {
                this.R = isPowerManagerSignalEnabled;
                return this;
            }

            public final C3M L(boolean isRootInfoNativeSignalEnabled) {
                this.S = isRootInfoNativeSignalEnabled;
                return this;
            }

            public final C3M M(boolean isSystemSettingsSignalEnabled) {
                this.U = isSystemSettingsSignalEnabled;
                return this;
            }

            public final C3M N(boolean isTelephonyManagerSignalEnabled) {
                this.V = isTelephonyManagerSignalEnabled;
                return this;
            }

            public final C3M O(boolean isNativeSignalCollectionEnabled) {
                this.M = isNativeSignalCollectionEnabled;
                return this;
            }

            public final C3M P(String networkRequestUrl) {
                this.W = networkRequestUrl;
                return this;
            }

            public final C3M Q(boolean isNetworkSignalCollectionEnabled) {
                this.O = isNetworkSignalCollectionEnabled;
                return this;
            }

            public final C3M R(boolean isNetworkSignalPaddingEnabled) {
                this.P = isNetworkSignalPaddingEnabled;
                return this;
            }

            public final C3M S(int networkSignalPaddingSamplingRate) {
                this.X = networkSignalPaddingSamplingRate;
                return this;
            }

            public final C3M T(int networkSignalPaddingSize) {
                this.Y = networkSignalPaddingSize;
                return this;
            }

            public final C3M U(C5C signalDataLoggingDelegate) {
                this.Z = signalDataLoggingDelegate;
                return this;
            }
        }.P("https://s.update.fbsbx.com/843748/gs?dt=8437481537452023477000").Q(AnonymousClass59.G(context)).R(AnonymousClass59.C(context)).T(AnonymousClass59.E(context)).S(AnonymousClass59.D(context)).U(new C5C(context) { // from class: com.facebook.ads.redexgen.X.5B
            private static final String C = C5B.class.getSimpleName();
            private final Context B;

            {
                this.B = context;
            }

            @Override // com.facebook.ads.redexgen.X.C5C
            public final void wC(String eventId, String productType, String eventType, String appSessionId, String signalType, String jsonData, @Nullable String exception) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("wo_bd_event_id", eventId);
                    jSONObject.put("wo_bd_product_type", productType);
                    jSONObject.put("wo_bd_event_type", eventType);
                    jSONObject.put("wo_bd_app_session_id", appSessionId);
                    jSONObject.put("wo_bd_signal_type", signalType);
                    jSONObject.put("wo_bd_json_data", jsonData);
                    jSONObject.put("wo_bd_exception", exception);
                    C0583Hv c0583Hv = new C0583Hv(jSONObject.toString());
                    c0583Hv.D(1);
                    C0585Hx.D(this.B, "bot_detection_wo_signal_data_logging", C0584Hw.e, c0583Hv);
                } catch (JSONException e) {
                    Log.e(C, "Exception building FAN logging JSON", e);
                }
            }
        }).O(AnonymousClass59.F(context)).E(C5A.BATTERY.A(context)).B(C5A.ACTIVITY_MANAGER.A(context)).K(C5A.POWER_MANAGER.A(context)).M(C5A.SYSTEM_SETTINGS.A(context)).J(C5A.PACKAGE_MANAGER.A(context)).C(C5A.APPLICATION_INFO.A(context)).H(C5A.KEYGUARD_MANAGER.A(context)).N(C5A.TELEPHONY_MANAGER.A(context)).G(C5A.DEVICE_SETTINGS.A(context)).D(C5A.AUDIO_MANAGER.A(context)).I(C5A.NETWORK_NATIVE.A(context)).L(C5A.ROOT_INFO.A(context)).F(C5A.CONNECTIVITY_MANAGER.A(context)).A();
        this.F = AnonymousClass59.F(context);
        this.D = AnonymousClass59.B(context);
        this.C = new C3K(context, this.B);
    }

    public final void A(final String eventType, final String eventId) {
        if (this.C == null || !C() || I > JA.B(this.E)) {
            return;
        }
        this.H.execute(new Runnable() { // from class: com.facebook.ads.redexgen.X.5E
            @Override // java.lang.Runnable
            public final void run() {
                this.B.C.A("AN_ANDROID", eventType, eventId);
            }
        });
        I++;
    }

    private boolean C() {
        return this.F && ((double) this.D) > Math.random() * 100.0d;
    }
}
