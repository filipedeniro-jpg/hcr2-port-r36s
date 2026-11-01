package com.facebook.ads.redexgen.X;

import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.view.accessibility.AccessibilityNodeInfo;
import com.ironsource.sdk.constants.Constants;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AT {
    public static final AH D;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int B = -1;
    private final AccessibilityNodeInfo C;

    static {
        if (Build.VERSION.SDK_INT >= 24) {
            D = new AP();
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            D = new AO();
            return;
        }
        if (Build.VERSION.SDK_INT >= 22) {
            D = new AN();
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            D = new AM();
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            D = new AL();
            return;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            D = new AK();
            return;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            D = new AJ();
        } else if (Build.VERSION.SDK_INT >= 16) {
            D = new AI();
        } else {
            D = new AH();
        }
    }

    private AT(AccessibilityNodeInfo info) {
        this.C = info;
    }

    public final void A(int action) {
        this.C.addAction(action);
    }

    public final AccessibilityNodeInfo B() {
        return this.C;
    }

    public static AT B(@NonNull AccessibilityNodeInfo info) {
        return new AT(info);
    }

    /* JADX INFO: renamed from: C, reason: collision with other method in class */
    public final int m239C() {
        return this.C.getActions();
    }

    private final boolean C() {
        return this.C.isCheckable();
    }

    private final String D() {
        return D.E(this.C);
    }

    public final void D(Rect outBounds) {
        this.C.getBoundsInParent(outBounds);
    }

    private static String E(int action) {
        switch (action) {
            case 1:
                return "ACTION_FOCUS";
            case 2:
                return "ACTION_CLEAR_FOCUS";
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            default:
                return "ACTION_UNKNOWN";
        }
    }

    public final void E(Rect outBounds) {
        this.C.getBoundsInScreen(outBounds);
    }

    public final void F(boolean scrollable) {
        this.C.setScrollable(scrollable);
    }

    public final CharSequence G() {
        return this.C.getClassName();
    }

    public final CharSequence H() {
        return this.C.getContentDescription();
    }

    public final void I(Object collectionItemInfo) {
        D.A(this.C, ((AR) collectionItemInfo).B);
    }

    public final CharSequence J() {
        return this.C.getPackageName();
    }

    public final CharSequence K() {
        return this.C.getText();
    }

    public final void L(Object collectionInfo) {
        D.B(this.C, ((AQ) collectionInfo).B);
    }

    public final boolean M() {
        return this.C.isChecked();
    }

    public final boolean N() {
        return this.C.isClickable();
    }

    public final boolean O() {
        return this.C.isEnabled();
    }

    public final boolean P() {
        return this.C.isFocusable();
    }

    public final boolean Q() {
        return this.C.isFocused();
    }

    public final boolean R() {
        return this.C.isLongClickable();
    }

    public final boolean S() {
        return this.C.isPassword();
    }

    public final boolean T() {
        return this.C.isScrollable();
    }

    public final boolean U() {
        return this.C.isSelected();
    }

    public final void V(CharSequence className) {
        this.C.setClassName(className);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            AT at = (AT) obj;
            if (this.C == null) {
                return at.C == null;
            }
            return this.C.equals(at.C);
        }
        return false;
    }

    public final int hashCode() {
        if (this.C == null) {
            return 0;
        }
        return this.C.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        D(rect);
        sb.append("; boundsInParent: " + rect);
        E(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ").append(J());
        sb.append("; className: ").append(G());
        sb.append("; text: ").append(K());
        sb.append("; contentDescription: ").append(H());
        sb.append("; viewId: ").append(D());
        sb.append("; checkable: ").append(C());
        sb.append("; checked: ").append(M());
        sb.append("; focusable: ").append(P());
        sb.append("; focused: ").append(Q());
        sb.append("; selected: ").append(U());
        sb.append("; clickable: ").append(N());
        sb.append("; longClickable: ").append(R());
        sb.append("; enabled: ").append(O());
        sb.append("; password: ").append(S());
        sb.append("; scrollable: " + T());
        sb.append("; [");
        int iM239C = m239C();
        while (iM239C != 0) {
            int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(iM239C);
            iM239C &= iNumberOfTrailingZeros ^ (-1);
            sb.append(E(iNumberOfTrailingZeros));
            if (iM239C != 0) {
                sb.append(", ");
            }
        }
        sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
        return sb.toString();
    }
}
