package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzayq {
    public final boolean zzdyw;
    public final int zzdyx;
    public final int zzdyy;
    public final int zzdyz;
    public final String zzdza;
    public final int zzdzb;
    public final int zzdzc;
    public final int zzdzd;
    public final int zzdze;
    public final boolean zzdzf;
    public final int zzdzg;

    public zzayq(String str) {
        JSONObject jSONObject = null;
        if (str != null) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException unused) {
            }
        }
        this.zzdyw = zza(jSONObject, "aggressive_media_codec_release", zzyt.zzcho);
        this.zzdyx = zzb(jSONObject, "byte_buffer_precache_limit", zzyt.zzcgv);
        this.zzdyy = zzb(jSONObject, "exo_cache_buffer_size", zzyt.zzchc);
        this.zzdyz = zzb(jSONObject, "exo_connect_timeout_millis", zzyt.zzcgr);
        this.zzdza = zzc(jSONObject, "exo_player_version", zzyt.zzcgq);
        this.zzdzb = zzb(jSONObject, "exo_read_timeout_millis", zzyt.zzcgs);
        this.zzdzc = zzb(jSONObject, "load_check_interval_bytes", zzyt.zzcgt);
        this.zzdzd = zzb(jSONObject, "player_precache_limit", zzyt.zzcgu);
        this.zzdze = zzb(jSONObject, "socket_receive_buffer_size", zzyt.zzcgw);
        this.zzdzf = zza(jSONObject, "use_cache_data_source", zzyt.zzcpj);
        this.zzdzg = zzb(jSONObject, "min_retry_count", zzyt.zzcgy);
    }

    private static boolean zza(JSONObject jSONObject, String str, zzyi<Boolean> zzyiVar) {
        return zza(jSONObject, str, ((Boolean) zzuo.zzoj().zzd(zzyiVar)).booleanValue());
    }

    private static boolean zza(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject != null) {
            try {
                return jSONObject.getBoolean(str);
            } catch (JSONException unused) {
            }
        }
        return z;
    }

    private static int zzb(JSONObject jSONObject, String str, zzyi<Integer> zzyiVar) {
        if (jSONObject != null) {
            try {
                return jSONObject.getInt(str);
            } catch (JSONException unused) {
            }
        }
        return ((Integer) zzuo.zzoj().zzd(zzyiVar)).intValue();
    }

    private static String zzc(JSONObject jSONObject, String str, zzyi<String> zzyiVar) {
        if (jSONObject != null) {
            try {
                return jSONObject.getString(str);
            } catch (JSONException unused) {
            }
        }
        return (String) zzuo.zzoj().zzd(zzyiVar);
    }
}
