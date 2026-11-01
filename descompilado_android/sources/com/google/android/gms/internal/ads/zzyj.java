package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzyj extends zzyi<Long> {
    zzyj(int i, String str, Long l) {
        super(i, str, l, null);
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ Long zza(Bundle bundle) {
        String strValueOf = String.valueOf(getKey());
        if (bundle.containsKey(strValueOf.length() != 0 ? "com.google.android.gms.ads.flag.".concat(strValueOf) : new String("com.google.android.gms.ads.flag."))) {
            String strValueOf2 = String.valueOf(getKey());
            return Long.valueOf(bundle.getLong(strValueOf2.length() != 0 ? "com.google.android.gms.ads.flag.".concat(strValueOf2) : new String("com.google.android.gms.ads.flag.")));
        }
        return zzpm();
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ void zza(SharedPreferences.Editor editor, Long l) {
        editor.putLong(getKey(), l.longValue());
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ Long zza(JSONObject jSONObject) {
        return Long.valueOf(jSONObject.optLong(getKey(), zzpm().longValue()));
    }

    @Override // com.google.android.gms.internal.ads.zzyi
    public final /* synthetic */ Long zza(SharedPreferences sharedPreferences) {
        return Long.valueOf(sharedPreferences.getLong(getKey(), zzpm().longValue()));
    }
}
