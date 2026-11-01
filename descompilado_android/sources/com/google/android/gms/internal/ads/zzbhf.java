package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbhf {
    @Nullable
    public static JSONObject zza(zzcvb zzcvbVar) {
        try {
            return new JSONObject(zzcvbVar.zzdkm);
        } catch (JSONException unused) {
            return null;
        }
    }
}
