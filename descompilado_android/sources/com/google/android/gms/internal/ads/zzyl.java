package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzyl extends zzyi<String> {
    zzyl(int i, String str, String str2) {
        super(i, str, str2, null);
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ String zza(Bundle bundle) {
        String strValueOf = String.valueOf(getKey());
        if (bundle.containsKey(strValueOf.length() != 0 ? "com.google.android.gms.ads.flag.".concat(strValueOf) : new String("com.google.android.gms.ads.flag."))) {
            String strValueOf2 = String.valueOf(getKey());
            return bundle.getString(strValueOf2.length() != 0 ? "com.google.android.gms.ads.flag.".concat(strValueOf2) : new String("com.google.android.gms.ads.flag."));
        }
        return zzpm();
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ void zza(SharedPreferences.Editor editor, String str) {
        editor.putString(getKey(), str);
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ String zza(JSONObject jSONObject) {
        return jSONObject.optString(getKey(), zzpm());
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ String zza(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(getKey(), zzpm());
    }
}
