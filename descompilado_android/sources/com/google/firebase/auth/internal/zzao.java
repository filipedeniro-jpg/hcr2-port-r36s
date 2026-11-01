package com.google.firebase.auth.internal;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzbq;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzao {
    private static final Logger zza = new Logger("JSONParser", new String[0]);

    @NonNull
    public static Map<String, Object> zza(String str) {
        Preconditions.checkNotEmpty(str);
        List<String> listZza = com.google.android.gms.internal.firebase_auth.zzan.zza('.').zza((CharSequence) str);
        if (listZza.size() < 2) {
            Logger logger = zza;
            String strValueOf = String.valueOf(str);
            logger.e(strValueOf.length() != 0 ? "Invalid idToken ".concat(strValueOf) : new String("Invalid idToken "), new Object[0]);
            return Collections.EMPTY_MAP;
        }
        try {
            Map<String, Object> mapZzb = zzb(new String(Base64Utils.decodeUrlSafeNoPadding(listZza.get(1)), "UTF-8"));
            return mapZzb == null ? Collections.EMPTY_MAP : mapZzb;
        } catch (UnsupportedEncodingException e) {
            zza.e("Unable to decode token", e, new Object[0]);
            return Collections.EMPTY_MAP;
        }
    }

    @Nullable
    public static Map<String, Object> zzb(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != JSONObject.NULL) {
                return zza(jSONObject);
            }
            return null;
        } catch (Exception e) {
            Log.d("JSONParser", "Failed to parse JSONObject into Map.");
            throw new zzbq(e);
        }
    }

    @VisibleForTesting
    private static Map<String, Object> zza(JSONObject jSONObject) throws JSONException {
        ArrayMap arrayMap = new ArrayMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Object objZza = jSONObject.get(next);
            if (objZza instanceof JSONArray) {
                objZza = zza((JSONArray) objZza);
            } else if (objZza instanceof JSONObject) {
                objZza = zza((JSONObject) objZza);
            }
            arrayMap.put(next, objZza);
        }
        return arrayMap;
    }

    @VisibleForTesting
    private static List<Object> zza(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object objZza = jSONArray.get(i);
            if (objZza instanceof JSONArray) {
                objZza = zza((JSONArray) objZza);
            } else if (objZza instanceof JSONObject) {
                objZza = zza((JSONObject) objZza);
            }
            arrayList.add(objZza);
        }
        return arrayList;
    }
}
