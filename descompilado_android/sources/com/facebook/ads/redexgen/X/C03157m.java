package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.7m, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C03157m {
    private final C1N B;
    private final JV C;
    private final C1R D;
    private final C1V E;

    @Nullable
    private InterfaceC0525Fo F;
    private final String G;
    private final Context H;
    private B0 I;
    private final C1Z J;
    private final C01471a K;
    private Executor L;
    private final C0618Jf M;
    private final C01551i N;

    @Nullable
    private C01581l O;
    private final C0621Ji P;
    private final C5I Q;
    private static final int T = (int) (4.0f * J6.B);
    private static final int R = (int) (72.0f * J6.B);
    private static final int S = (int) (8.0f * J6.B);

    public C03157m(Context context, JV adEventManager, C01481b dataBundle, C01491c adInfo, InterfaceC0525Fo adListener, C5I viewabilityChecker, C0621Ji touchDataRecorder) {
        this(context, dataBundle.C(), adEventManager, dataBundle.D(), adInfo.D(), adInfo.C(), dataBundle.I(), adInfo.A(), adInfo.B(), adListener, viewabilityChecker, touchDataRecorder);
        this.O = B() == EnumC03177o.PLAYABLE ? C01581l.B(dataBundle) : null;
    }

    public C03157m(Context context, JV adEventManager, C01601n dataBundle, InterfaceC0525Fo adListener, C5I viewabilityChecker, C0621Ji touchDataRecorder) {
        this(context, dataBundle.A(), adEventManager, dataBundle.J(), dataBundle.L(), dataBundle.K(), dataBundle.P(), dataBundle.N(), dataBundle.O(), adListener, viewabilityChecker, touchDataRecorder);
        this.O = B() == EnumC03177o.PLAYABLE ? C01581l.C(dataBundle) : null;
    }

    private C03157m(Context context, String clientToken, JV adEventManager, C1N adColorsData, C1V adMetadata, C1R adMediaData, C01551i pageDetails, C1Z ctaData, C01471a endCardsData, InterfaceC0525Fo adListener, C5I viewabilityChecker, C0621Ji touchDataRecorder) {
        this.L = LD.E;
        this.H = context;
        this.G = clientToken;
        this.C = adEventManager;
        this.M = new C0618Jf(this.G, this.C);
        this.F = adListener;
        this.B = adColorsData;
        this.E = adMetadata;
        this.D = adMediaData;
        this.N = pageDetails;
        this.J = ctaData;
        this.K = endCardsData;
        this.Q = viewabilityChecker;
        this.P = touchDataRecorder;
    }

    public final Pair<EnumC03177o, View> A() {
        View viewI;
        EnumC03177o endCardViewType = B();
        switch (endCardViewType) {
            case SCREENSHOTS:
                viewI = I();
                break;
            case PLAYABLE:
                viewI = H();
                break;
            default:
                viewI = G();
                break;
        }
        C0620Jh.C(viewI, this.M, EnumC0617Je.END_CARD_SHOWN);
        return new Pair<>(endCardViewType, viewI);
    }

    public final EnumC03177o B() {
        C01571k c01571kD = this.D.D();
        if (c01571kD != null && c01571kD.J()) {
            return EnumC03177o.PLAYABLE;
        }
        if (!this.K.B().isEmpty()) {
            return EnumC03177o.SCREENSHOTS;
        }
        return EnumC03177o.INFO;
    }

    public final void C() {
        String rewardUrl = this.K.A();
        if (!TextUtils.isEmpty(rewardUrl)) {
            I9 i9 = new I9(this.H, new HashMap());
            i9.A(new IA() { // from class: com.facebook.ads.redexgen.X.7r
                @Override // com.facebook.ads.redexgen.X.IA
                public final void cF() {
                    if (this.B.F != null) {
                        this.B.F.aB(EnumC02926p.REWARD_SERVER_FAILED.B());
                    }
                }

                @Override // com.facebook.ads.redexgen.X.IA
                public final void eF(I8 output) {
                    if (this.B.F == null) {
                        return;
                    }
                    if (output != null && output.A()) {
                        this.B.F.aB(EnumC02926p.REWARD_SERVER_SUCCESS.B());
                    } else {
                        this.B.F.aB(EnumC02926p.REWARD_SERVER_FAILED.B());
                    }
                }
            });
            i9.executeOnExecutor(this.L, rewardUrl);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E() {
        if (this.F != null) {
            this.F.aB(EnumC02926p.REWARDED_VIDEO_END_ACTIVITY.B());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public B0 F(Map<String, String> map) {
        if (this.I != null) {
            return this.I;
        }
        this.I = new B0(this.H, true, false, EnumC02926p.REWARDED_VIDEO_AD_CLICK.B(), this.B.B(), this.C, this.F, this.Q, this.P);
        this.I.setCta(this.J, this.G, map);
        return this.I;
    }

    private View G() {
        C0394An c0394An = new C0394An(this.H, this.B.B(), true, false, false);
        c0394An.A(this.E.I(), this.E.C(), null, false, true);
        c0394An.setAlignment(17);
        HashMap map = new HashMap();
        map.put("click_origin", "native_click");
        B0 b0F = F(map);
        J6.M(b0F);
        if (TextUtils.isEmpty(b0F.getText())) {
            J6.F(b0F);
        }
        CN cn = new CN(this.H);
        J6.O(cn, 0);
        cn.setRadius(50);
        new B7(cn).B().E(this.N.B());
        LinearLayout linearLayout = new LinearLayout(this.H);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.addView(cn, new LinearLayout.LayoutParams(R, R));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, S, 0, S);
        linearLayout.addView(c0394An, layoutParams);
        linearLayout.addView(b0F, layoutParams);
        return linearLayout;
    }

    private View H() {
        return new AnonymousClass81(this.H, this.O, this.C, this.F, new C03167n(this), false, false);
    }

    private View I() {
        C0503Es c0503Es = new C0503Es(this.H);
        c0503Es.setLayoutManager(new C0484Dz(this.H, 0, false));
        final List<String> listB = this.K.B();
        final int i = T;
        c0503Es.setAdapter(new C1G<C03087f>(listB, i) { // from class: com.facebook.ads.redexgen.X.7l
            private final int B;
            private final List<String> C;

            {
                this.C = listB;
                this.B = i;
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.C1G
            /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
            public final void M(C03087f holder, int position) {
                String str = this.C.get(position);
                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -1);
                marginLayoutParams.setMargins(position == 0 ? this.B * 4 : this.B, 0, position >= C() + (-1) ? this.B * 4 : this.B, 0);
                holder.h().setLayoutParams(marginLayoutParams);
                holder.h().A(str);
            }

            @Override // com.facebook.ads.redexgen.X.C1G
            public final int C() {
                return this.C.size();
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.facebook.ads.redexgen.X.C1G
            /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
            public final C03087f N(ViewGroup parent, int viewType) {
                return new C03087f(new C03097g(parent.getContext()));
            }
        });
        return c0503Es;
    }
}
