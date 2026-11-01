package com.facebook.ads.redexgen.X;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import android.view.WindowManager;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.sdk.constants.Constants;
import java.util.EnumSet;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0n, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01340n implements C0O, InterfaceC01330m {
    private boolean B;
    private AnonymousClass13 C;
    private Context D;
    private C01671u E;
    private AnonymousClass12 F;

    @Nullable
    private String G;
    private String H;
    private long I;
    private final String J = UUID.randomUUID().toString();

    public final void A(Context context, AnonymousClass12 adapterListener, Map<String, Object> map, EnumSet<CacheFlag> enumSet, @Nullable String extraHints, @Nullable String mediationData) {
        this.D = context;
        this.F = adapterListener;
        this.H = (String) map.get(Constants.PLACEMENT_ID);
        this.I = ((Long) map.get("requestTime")).longValue();
        this.G = mediationData;
        D(context, adapterListener, map, enumSet, extraHints);
    }

    @Override // com.facebook.ads.redexgen.X.C0O
    @Nullable
    public final String AC() {
        return this.E.C();
    }

    private void B(Intent intent) {
        this.E.A(intent);
    }

    @WorkerThread
    public final boolean B() {
        boolean z = false;
        if (!this.B) {
            if (this.F == null) {
                return false;
            }
            this.F.TE(this, AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            return false;
        }
        Intent intent = new Intent(this.D, (Class<?>) MH.B());
        intent.putExtra("predefinedOrientationKey", C());
        intent.putExtra("uniqueId", this.J);
        intent.putExtra(Constants.PLACEMENT_ID, this.H);
        intent.putExtra("requestTime", this.I);
        intent.putExtra("viewType", F());
        intent.putExtra("mediationData", this.G);
        B(intent);
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        intent.putExtra("clientToken", this.E.C());
        try {
            MH.E(this.D, intent);
            z = true;
            return true;
        } catch (ActivityNotFoundException e) {
            C0585Hx.D(this.D, "an_activity", C0584Hw.F, new C0583Hv(e));
            Log.e(AudienceNetworkAds.TAG, "Can't start AudienceNetworkActivity. Make sure that it's in your AndroidManifest.xml file.", e);
            return z;
        }
    }

    private int C() {
        WindowManager windowManager = (WindowManager) this.D.getSystemService("window");
        int rotation = windowManager.getDefaultDisplay().getRotation();
        B6 b6E = E();
        if (b6E == B6.UNSPECIFIED) {
            return -1;
        }
        if (b6E == B6.LANDSCAPE) {
            switch (rotation) {
                case 2:
                case 3:
                    return 8;
                default:
                    return 0;
            }
        }
        switch (rotation) {
            case 2:
                return 9;
            default:
                return 1;
        }
    }

    private final void D(Context context, AnonymousClass12 listener, Map<String, Object> map, EnumSet<CacheFlag> enumSet, @Nullable String extraHints) {
        this.E = new C01671u(context, map, this, extraHints);
        this.E.E(context, enumSet);
    }

    private B6 E() {
        return this.E.B();
    }

    private final L2 F() {
        return this.E.D();
    }

    private void G() {
        this.B = true;
    }

    @Override // com.facebook.ads.redexgen.X.C0O
    public final AdPlacementType HC() {
        return AdPlacementType.INTERSTITIAL;
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01330m
    public final void ND(AdError adError) {
        if (this.F != null) {
            this.F.TE(this, adError);
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01330m
    public final void OD() {
        G();
        this.F.SE(this);
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01330m
    public final void kF() {
        this.C = new AnonymousClass13(this.D, this.J, this, this.F);
        this.C.A();
    }

    @Override // com.facebook.ads.redexgen.X.C0O
    public final void onDestroy() {
        if (this.E != null) {
            this.E.F();
        }
    }

    @Override // com.facebook.ads.redexgen.X.InterfaceC01330m
    public final void xF() {
        if (this.C != null) {
            this.C.B();
        }
    }
}
