package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzym extends zzyi<Float> {
    zzym(int i, String str, Float f) {
        super(i, str, f, null);
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ Float zza(Bundle bundle) {
        String strValueOf = String.valueOf(getKey());
        if (bundle.containsKey(strValueOf.length() != 0 ? "com.google.android.gms.ads.flag.".concat(strValueOf) : new String("com.google.android.gms.ads.flag."))) {
            String strValueOf2 = String.valueOf(getKey());
            return Float.valueOf(bundle.getFloat(strValueOf2.length() != 0 ? "com.google.android.gms.ads.flag.".concat(strValueOf2) : new String("com.google.android.gms.ads.flag.")));
        }
        return zzpm();
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ void zza(SharedPreferences.Editor editor, Float f) {
        editor.putFloat(getKey(), f.floatValue());
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ Float zza(JSONObject jSONObject) {
        return Float.valueOf((float) jSONObject.optDouble(getKey(), zzpm().floatValue()));
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ Float zza(SharedPreferences sharedPreferences) {
        return Float.valueOf(sharedPreferences.getFloat(getKey(), zzpm().floatValue()));
    }
}
