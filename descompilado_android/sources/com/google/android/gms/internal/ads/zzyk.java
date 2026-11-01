package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzyk extends zzyi<Integer> {
    zzyk(int i, String str, Integer num) {
        super(i, str, num, null);
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ Integer zza(Bundle bundle) {
        String strValueOf = String.valueOf(getKey());
        if (bundle.containsKey(strValueOf.length() != 0 ? "com.google.android.gms.ads.flag.".concat(strValueOf) : new String("com.google.android.gms.ads.flag."))) {
            String strValueOf2 = String.valueOf(getKey());
            return Integer.valueOf(bundle.getInt(strValueOf2.length() != 0 ? "com.google.android.gms.ads.flag.".concat(strValueOf2) : new String("com.google.android.gms.ads.flag.")));
        }
        return zzpm();
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ void zza(SharedPreferences.Editor editor, Integer num) {
        editor.putInt(getKey(), num.intValue());
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ Integer zza(JSONObject jSONObject) {
        return Integer.valueOf(jSONObject.optInt(getKey(), zzpm().intValue()));
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ Integer zza(SharedPreferences sharedPreferences) {
        return Integer.valueOf(sharedPreferences.getInt(getKey(), zzpm().intValue()));
    }
}
