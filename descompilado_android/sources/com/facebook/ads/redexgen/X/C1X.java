package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.io.Serializable;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1X, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C1X implements Serializable {
    private static final long serialVersionUID = 4559450202335985006L;
    private final String B;
    private final String C;

    private C1X() {
        this.B = "";
        this.C = "";
    }

    private C1X(String timerTextTranslation, String titleTranslation) {
        this.B = timerTextTranslation;
        this.C = titleTranslation;
    }

    public final String A(String value) {
        return TextUtils.isEmpty(value) ? this.B : this.B.replace("[fb_sec]", value);
    }

    public static C1X B(@Nullable JSONObject translationObject) {
        if (translationObject == null) {
            return new C1X();
        }
        return new C1X(translationObject.optString("timer_text"), translationObject.optString("title_text"));
    }

    public final String B() {
        return this.C;
    }
}
