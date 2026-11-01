package com.facebook.ads.redexgen.X;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.view.MotionEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4f, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C02304f {
    private final EnumC02053g B;
    private final C02354k C;
    private final Context D;

    public C02304f(Context context, C02354k biometricSignalValueHandler, EnumC02053g bdIntegrationSite) {
        this.C = biometricSignalValueHandler;
        this.D = context;
        this.B = bdIntegrationSite;
    }

    @TargetApi(9)
    public final void A(MotionEvent event) {
        int toolType;
        int toolType2;
        final long jElapsedRealtime = SystemClock.elapsedRealtime();
        final C3T c3t = this.B == EnumC02053g.OFFSITE ? null : new C3T(this.D == null ? "" : this.D.getPackageName());
        switch (event.getActionMasked()) {
            case 0:
            case 1:
            case 3:
            case 5:
            case 6:
                int i = event.getActionIndex();
                int pointerId = event.getPointerId(i);
                if (Build.VERSION.SDK_INT >= 14) {
                    toolType = event.getToolType(i);
                } else {
                    toolType = -1;
                }
                final C02264b c02264b = new C02264b(event.getActionMasked(), pointerId, toolType, new float[]{event.getX(i), event.getY(i)}, event.getSize(i), Math.min(event.getPressure(i), 1.0f), event.getOrientation(i));
                this.C.A(new AbstractC02043f<C02264b>(jElapsedRealtime, c3t, c02264b) { // from class: com.facebook.ads.redexgen.X.3Q
                    {
                        C3S c3s = C3S.TOUCH;
                    }

                    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<com.facebook.botdetection.signals.biometric.model.TouchSignalRawValue> */
                    @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                    public final boolean A(AbstractC02043f<C02264b> abstractC02043f) {
                        return ((C02264b) B()).A(abstractC02043f.m231B());
                    }

                    @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                    public final int E() {
                        return ((C02264b) B()).m235B();
                    }

                    @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                    public final JSONObject F(JSONObject inputObject) throws JSONException {
                        return ((C02264b) B()).C(inputObject);
                    }
                }, EnumC02374m.ADD_TOUCH_SIGNAL);
                break;
            case 2:
            case 4:
                for (int i2 = 0; i2 < event.getPointerCount(); i2++) {
                    int pointerId2 = event.getPointerId(i2);
                    if (Build.VERSION.SDK_INT >= 14) {
                        toolType2 = event.getToolType(i2);
                    } else {
                        toolType2 = -1;
                    }
                    final C02264b c02264b2 = new C02264b(event.getActionMasked(), pointerId2, toolType2, new float[]{event.getX(i2), event.getY(i2)}, event.getSize(i2), Math.min(event.getPressure(i2), 1.0f), event.getOrientation(i2));
                    this.C.A(new AbstractC02043f<C02264b>(jElapsedRealtime, c3t, c02264b2) { // from class: com.facebook.ads.redexgen.X.3Q
                        {
                            C3S c3s = C3S.TOUCH;
                        }

                        /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.3f != com.facebook.botdetection.signals.model.signal_value.SignalValueTypeDef<com.facebook.botdetection.signals.biometric.model.TouchSignalRawValue> */
                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final boolean A(AbstractC02043f<C02264b> abstractC02043f) {
                            return ((C02264b) B()).A(abstractC02043f.m231B());
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final int E() {
                            return ((C02264b) B()).m235B();
                        }

                        @Override // com.facebook.ads.redexgen.X.AbstractC02043f
                        public final JSONObject F(JSONObject inputObject) throws JSONException {
                            return ((C02264b) B()).C(inputObject);
                        }
                    }, EnumC02374m.ADD_TOUCH_SIGNAL);
                }
                break;
        }
    }
}
