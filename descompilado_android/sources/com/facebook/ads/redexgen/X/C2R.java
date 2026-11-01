package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.protocol.AdErrorType;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.2R, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public final class C2R {
    private static final HashMap<C2Q, C2Q> G = new HashMap<>();
    private final C2U C;
    private final Context D;

    @Nullable
    private AdError F;
    public C2Q B = C2Q.CREATED;
    private final Deque<C2Q> E = new LinkedList();

    static {
        G.put(C2Q.CREATED, C2Q.LOADING);
        G.put(C2Q.LOADING, C2Q.LOADED);
        G.put(C2Q.LOADED, C2Q.SHOWING);
        G.put(C2Q.SHOWING, C2Q.SHOWN);
        G.put(C2Q.SHOWN, C2Q.LOADING);
        G.put(C2Q.DESTROYED, C2Q.LOADING);
        G.put(C2Q.ERROR, C2Q.LOADING);
    }

    public C2R(Context appContext, C2U adIpcProxy) {
        this.D = appContext;
        this.C = adIpcProxy;
        this.E.add(this.B);
    }

    public final boolean A(C2Q next, String methodName) {
        if (!next.equals(G.get(this.B))) {
            if (!JA.O(this.D)) {
                return false;
            }
            AdSettings.IntegrationErrorMode integrationErrorModeB = C2F.B(this.D);
            String strC = C(this.B);
            String lastStates = String.format(Locale.US, AdErrorType.INCORRECT_STATE_ERROR.getDefaultErrorMessage(), methodName, this.B);
            String str = lastStates + ". " + strC;
            switch (C2P.B[integrationErrorModeB.ordinal()]) {
                case 1:
                    throw new IllegalStateException(str + ". You can change Integration Error mode by setting AdSettings.setIntegrationErrorMode()");
                case 2:
                    this.C.C();
                    this.C.H(10, AdErrorType.INCORRECT_STATE_ERROR, str);
                    Log.e(AudienceNetworkAds.TAG, str);
                    C0583Hv c0583Hv = new C0583Hv(lastStates + "\n" + strC);
                    c0583Hv.D(1);
                    C0585Hx.D(this.D, "api", C0584Hw.Z, c0583Hv);
                    return true;
                default:
                    Log.e(AudienceNetworkAds.TAG, str);
                    return true;
            }
        }
        this.B = next;
        B();
        return false;
    }

    private void B() {
        this.E.addFirst(this.B);
        if (this.E.size() > 5) {
            this.E.removeLast();
        }
    }

    public final void B(AdError error) {
        this.F = error;
        m227C(C2Q.ERROR);
    }

    private String C(C2Q prevState) {
        StringBuilder sb = new StringBuilder();
        sb.append("Previous states: ");
        Iterator<C2Q> it = this.E.iterator();
        int i = 0;
        while (it.hasNext()) {
            sb.append(it.next());
            if (i != this.E.size() - 1) {
                sb.append(" << ");
            }
            i++;
        }
        if (C2Q.ERROR.equals(prevState)) {
            sb.append(". Previous error: ").append(D(this.F));
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: C, reason: collision with other method in class */
    public final void m227C(C2Q next) {
        if (!JA.O(this.D)) {
            this.B = next;
            return;
        }
        if (next.equals(C2Q.ERROR)) {
            this.B = next;
            B();
        } else if (next.equals(C2Q.DESTROYED)) {
            this.B = next;
            B();
        } else {
            if (!next.equals(G.get(this.B))) {
                C0585Hx.D(this.D, "api", C0584Hw.b, new C0583Hv("Wrong internal transition.", "From " + this.B + " to " + next + " lastStates = " + C(this.B)));
            }
            this.B = next;
            B();
        }
    }

    private static String D(@Nullable AdError error) {
        String errorMessage = error != null ? error.getErrorMessage() : "null";
        if (errorMessage.length() > 75) {
            return errorMessage.substring(0, 75) + "...";
        }
        return errorMessage;
    }
}
