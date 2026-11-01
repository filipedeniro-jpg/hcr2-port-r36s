package com.facebook.ads.redexgen.X;

import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AU {
    private AU() {
    }

    /* JADX WARN: Code duplicated, block: B:16:0x009f  */
    /* JADX WARN: Code duplicated, block: B:18:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:20:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:21:0x00bd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:22:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:24:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:26:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:27:0x00cf  */
    public static AbstractC0390Aj B(final AF params, @Nullable Bundle savedInstanceState, boolean shouldLoadData) {
        final C1Y c1yA;
        boolean isWatchAndBrowse;
        AbstractC0390Aj a2;
        boolean isWatchAndBrowse2;
        boolean isWatchAndBrowse3;
        if (params.H() == 1) {
            c1yA = params.C().D().B();
        } else {
            c1yA = params.C().D().A();
        }
        C01491c c01491c = params.C().A().get(0);
        double dB = C0391Ak.B(c01491c);
        boolean zF = params.C().A().get(0).F();
        boolean zD = C0391Ak.D(params.H(), params.K(), dB);
        AnonymousClass09 anonymousClass09B = C0A.B(params.B(), params.A(), "", Uri.parse(params.C().A().get(0).A().C()), new HashMap());
        if (zF) {
            boolean isWatchAndBrowse4 = anonymousClass09B instanceof C0I;
            if (isWatchAndBrowse4) {
                if (params.H() == 1) {
                    a2 = new AbstractC0390Aj(params, c1yA) { // from class: com.facebook.ads.redexgen.X.9c
                        private static final int D = Resources.getSystem().getDisplayMetrics().widthPixels;
                        private int B;
                        private final C03569b C;

                        {
                            super(params, c1yA, true);
                            this.C = new C03569b(params.B(), params.E());
                            this.C.A(params.I(), params.F());
                            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
                            layoutParams.gravity = 17;
                            addView(this.C, layoutParams);
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
                        public final boolean B() {
                            return true;
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
                        public final void E(C01491c adInfo, String clientToken, double aspectRatio, @Nullable Bundle savedInstanceState2, @Nullable D4 onAdShownListener) {
                            super.E(adInfo, clientToken, aspectRatio, savedInstanceState2, onAdShownListener);
                            if (aspectRatio > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                                int i = (int) (((double) (D - (AbstractC0390Aj.E * 2))) / aspectRatio);
                                if (J6.C.heightPixels - i < C9M.F) {
                                    i = J6.C.heightPixels - C9M.F;
                                }
                                this.C.C(i);
                                this.B = i;
                            }
                            if (onAdShownListener != null) {
                                onAdShownListener.TD();
                            }
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
                        public final int getExactMediaHeightIfAvailable() {
                            return this.B;
                        }
                    };
                } else {
                    a2 = new AbstractC0390Aj(params, c1yA) { // from class: com.facebook.ads.redexgen.X.9d
                        private static final int B = Resources.getSystem().getDisplayMetrics().widthPixels;

                        {
                            super(params, c1yA, true);
                            RelativeLayout relativeLayout = new RelativeLayout(params.B());
                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(B / 2, -2);
                            layoutParams.addRule(12);
                            GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{-1778384896, 0});
                            gradientDrawable.setCornerRadius(0.0f);
                            gradientDrawable.setGradientType(0);
                            J6.P(relativeLayout, gradientDrawable);
                            new LinearLayout.LayoutParams(-2, -2).setMargins(AbstractC0390Aj.E, 0, 0, 0);
                            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2);
                            layoutParams2.setMargins(0, 0, 0, 0);
                            layoutParams2.weight = 1.0f;
                            if (params.I() != null) {
                                RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
                                layoutParams3.setMargins(0, 0, 0, 0);
                                relativeLayout.addView(params.I(), layoutParams3);
                            }
                            View viewE = params.E();
                            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(B / 2, -1);
                            layoutParams4.addRule(13);
                            layoutParams4.addRule(9);
                            addView(viewE, layoutParams4);
                            addView(relativeLayout, layoutParams);
                            if (params.F() != null) {
                                RelativeLayout.LayoutParams muteParams = new RelativeLayout.LayoutParams(AbstractC0390Aj.F, AbstractC0390Aj.F);
                                muteParams.addRule(12);
                                muteParams.setMargins(AbstractC0390Aj.E, AbstractC0390Aj.E + params.K(), AbstractC0390Aj.E, AbstractC0390Aj.E);
                                addView(params.F(), muteParams);
                            }
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
                        public final boolean B() {
                            return true;
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
                        public final void E(C01491c adInfo, String clientToken, double aspectRatio, @Nullable Bundle savedInstanceState2, @Nullable D4 onAdShownListener) {
                            super.E(adInfo, clientToken, aspectRatio, savedInstanceState2, onAdShownListener);
                            if (onAdShownListener != null) {
                                onAdShownListener.TD();
                            }
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC0390Aj
                        public int getExactMediaWidthIfAvailable() {
                            return B / 2;
                        }
                    };
                }
            } else {
                isWatchAndBrowse = JA.PB(params.B());
                if (isWatchAndBrowse) {
                    isWatchAndBrowse3 = TextUtils.isEmpty(c01491c.C().H());
                    if (!isWatchAndBrowse3) {
                        a2 = new A3(params, c1yA);
                    } else if (zD) {
                        if (params.H() == 2) {
                            isWatchAndBrowse2 = true;
                        } else {
                            isWatchAndBrowse2 = false;
                        }
                        a2 = new AV(params, c1yA, isWatchAndBrowse2);
                    } else {
                        boolean isWatchAndBrowse5 = C0391Ak.C(dB);
                        a2 = new A2(params, isWatchAndBrowse5, c1yA);
                    }
                } else if (zD) {
                    if (params.H() == 2) {
                        isWatchAndBrowse2 = true;
                    } else {
                        isWatchAndBrowse2 = false;
                    }
                    a2 = new AV(params, c1yA, isWatchAndBrowse2);
                } else {
                    boolean isWatchAndBrowse6 = C0391Ak.C(dB);
                    a2 = new A2(params, isWatchAndBrowse6, c1yA);
                }
            }
        } else {
            isWatchAndBrowse = JA.PB(params.B());
            if (isWatchAndBrowse) {
                isWatchAndBrowse3 = TextUtils.isEmpty(c01491c.C().H());
                if (!isWatchAndBrowse3) {
                    a2 = new A3(params, c1yA);
                } else if (zD) {
                    if (params.H() == 2) {
                        isWatchAndBrowse2 = true;
                    } else {
                        isWatchAndBrowse2 = false;
                    }
                    a2 = new AV(params, c1yA, isWatchAndBrowse2);
                } else {
                    boolean isWatchAndBrowse7 = C0391Ak.C(dB);
                    a2 = new A2(params, isWatchAndBrowse7, c1yA);
                }
            } else if (zD) {
                if (params.H() == 2) {
                    isWatchAndBrowse2 = true;
                } else {
                    isWatchAndBrowse2 = false;
                }
                a2 = new AV(params, c1yA, isWatchAndBrowse2);
            } else {
                boolean isWatchAndBrowse8 = C0391Ak.C(dB);
                a2 = new A2(params, isWatchAndBrowse8, c1yA);
            }
        }
        if (shouldLoadData) {
            a2.E(c01491c, params.C().C(), dB, savedInstanceState, params.G());
        }
        return a2;
    }
}
