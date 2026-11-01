package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzadd implements zzadx<Object> {
    private final zzadg zzcwp;

    public zzadd(zzadg zzadgVar) {
        this.zzcwp = zzadgVar;
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final void zza(Object obj, Map<String, String> map) {
        if (this.zzcwp == null) {
            return;
        }
        String str = map.get("name");
        if (str == null) {
            zzatm.zzet("Ad metadata with no name parameter.");
            str = "";
        }
        Bundle bundleZzb = null;
        if (map.containsKey("info")) {
            try {
                bundleZzb = zzb(new JSONObject(map.get("info")));
            } catch (JSONException e) {
                zzatm.zzc("Failed to convert ad metadata to JSON.", e);
            }
        }
        if (bundleZzb == null) {
            zzatm.zzes("Failed to convert ad metadata to Bundle.");
        } else {
            this.zzcwp.zza(str, bundleZzb);
        }
    }

    private static Bundle zzb(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        Iterator<String> itKeys = jSONObject.keys();
        Bundle bundle = new Bundle();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Object obj = jSONObject.get(next);
            if (obj != null) {
                if (obj instanceof Boolean) {
                    bundle.putBoolean(next, ((Boolean) obj).booleanValue());
                } else if (obj instanceof Double) {
                    bundle.putDouble(next, ((Double) obj).doubleValue());
                } else if (obj instanceof Integer) {
                    bundle.putInt(next, ((Integer) obj).intValue());
                } else if (obj instanceof Long) {
                    bundle.putLong(next, ((Long) obj).longValue());
                } else if (obj instanceof String) {
                    bundle.putString(next, (String) obj);
                } else if (obj instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) obj;
                    if (jSONArray != null && jSONArray.length() != 0) {
                        int length = jSONArray.length();
                        int i = 0;
                        Object obj2 = null;
                        for (int i2 = 0; obj2 == null && i2 < length; i2++) {
                            obj2 = !jSONArray.isNull(i2) ? jSONArray.get(i2) : null;
                        }
                        if (obj2 == null) {
                            String strValueOf = String.valueOf(next);
                            zzatm.zzeu(strValueOf.length() != 0 ? "Expected JSONArray with at least 1 non-null element for key:".concat(strValueOf) : new String("Expected JSONArray with at least 1 non-null element for key:"));
                        } else if (obj2 instanceof JSONObject) {
                            Bundle[] bundleArr = new Bundle[length];
                            while (i < length) {
                                bundleArr[i] = !jSONArray.isNull(i) ? zzb(jSONArray.optJSONObject(i)) : null;
                                i++;
                            }
                            bundle.putParcelableArray(next, bundleArr);
                        } else if (obj2 instanceof Number) {
                            double[] dArr = new double[jSONArray.length()];
                            while (i < length) {
                                dArr[i] = jSONArray.optDouble(i);
                                i++;
                            }
                            bundle.putDoubleArray(next, dArr);
                        } else if (obj2 instanceof CharSequence) {
                            String[] strArr = new String[length];
                            while (i < length) {
                                strArr[i] = !jSONArray.isNull(i) ? jSONArray.optString(i) : null;
                                i++;
                            }
                            bundle.putStringArray(next, strArr);
                        } else if (obj2 instanceof Boolean) {
                            boolean[] zArr = new boolean[length];
                            while (i < length) {
                                zArr[i] = jSONArray.optBoolean(i);
                                i++;
                            }
                            bundle.putBooleanArray(next, zArr);
                        } else {
                            zzatm.zzeu(String.format("JSONArray with unsupported type %s for key:%s", obj2.getClass().getCanonicalName(), next));
                        }
                    }
                } else if (obj instanceof JSONObject) {
                    bundle.putBundle(next, zzb((JSONObject) obj));
                } else {
                    String strValueOf2 = String.valueOf(next);
                    zzatm.zzeu(strValueOf2.length() != 0 ? "Unsupported type for key:".concat(strValueOf2) : new String("Unsupported type for key:"));
                }
            }
        }
        return bundle;
    }
}
