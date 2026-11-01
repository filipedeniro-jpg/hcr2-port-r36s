package com.facebook.ads.redexgen.X;

import android.annotation.TargetApi;
import android.content.Context;
import android.provider.Settings;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3r, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C02163r implements C4M<String> {
    private final String B;
    private final String C;

    public C02163r(Context context, String systemSettingName, EnumC02173s settingType) {
        this.B = systemSettingName;
        this.C = E(context, systemSettingName, settingType);
    }

    public final int A() {
        if (this.B == null || this.C == null) {
            return 0;
        }
        return this.B.getBytes().length + this.C.getBytes().length;
    }

    @TargetApi(17)
    private static String B(Context context, String systemPropertyName) {
        return Settings.Global.getString(context.getContentResolver(), systemPropertyName);
    }

    @TargetApi(17)
    private static String C(Context context, String systemPropertyName) {
        return Settings.Secure.getString(context.getContentResolver(), systemPropertyName);
    }

    @TargetApi(17)
    private static String D(Context context, String systemPropertyName) {
        return Settings.System.getString(context.getContentResolver(), systemPropertyName);
    }

    private static String E(Context context, String systemPropertyName, EnumC02173s settingType) {
        switch (settingType) {
            case GLOBAL:
                return B(context, systemPropertyName);
            case SECURE:
                return C(context, systemPropertyName);
            case SYSTEM:
                return D(context, systemPropertyName);
            default:
                return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.C4M
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public final JSONObject uF(String key, JSONObject inputObject) throws JSONException {
        inputObject.put(this.B, this.C);
        return inputObject;
    }

    @Override // com.facebook.ads.redexgen.X.C4M
    public final boolean SC(Object newMapSignalValueDef) {
        C02163r c02163r = (C02163r) newMapSignalValueDef;
        boolean zEquals = false;
        if (this.B != null && c02163r.B != null) {
            zEquals = this.B.equals(c02163r.B);
        }
        if (!zEquals || this.C == null || c02163r.C == null) {
            return false;
        }
        return this.C.equals(c02163r.C);
    }
}
