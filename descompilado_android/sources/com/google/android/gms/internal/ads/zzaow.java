package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import io.fabric.sdk.android.services.network.HttpRequest;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaow extends zzaoy {
    private final Object lock = new Object();

    @Nullable
    private SharedPreferences zzdnb;
    private final zzahv<JSONObject, JSONObject> zzdnc;
    private final Context zzzc;

    public zzaow(Context context, zzahv<JSONObject, JSONObject> zzahvVar) {
        this.zzzc = context.getApplicationContext();
        this.zzdnc = zzahvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaoy
    public final zzdcp<Void> zztf() {
        synchronized (this.lock) {
            if (this.zzdnb == null) {
                this.zzdnb = this.zzzc.getSharedPreferences("google_ads_flags_meta", 0);
            }
        }
        if (com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis() - this.zzdnb.getLong("js_last_update", 0L) < ((Long) zzuo.zzoj().zzd(zzyt.zzcno)).longValue()) {
            return zzdcf.zzah(null);
        }
        return zzdcf.zzb(this.zzdnc.zzi(zzx(this.zzzc)), new zzczs(this) { // from class: com.google.android.gms.internal.ads.zzaoz
            private final zzaow zzdnf;

            {
                this.zzdnf = this;
            }

            @Override // com.google.android.gms.internal.ads.zzczs
            public final Object apply(Object obj) {
                return this.zzdnf.zzf((JSONObject) obj);
            }
        }, zzawx.zzdwb);
    }

    public static JSONObject zzx(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("js", zzawv.zzwk().zzbnh);
            jSONObject.put("mf", zzuo.zzoj().zzd(zzyt.zzcnp));
            jSONObject.put("cl", "258870853");
            jSONObject.put("rapid_rc", "dev");
            jSONObject.put("rapid_rollup", HttpRequest.METHOD_HEAD);
            jSONObject.put("admob_module_version", 20360);
            jSONObject.put("dynamite_local_version", ModuleDescriptor.MODULE_VERSION);
            jSONObject.put("dynamite_version", DynamiteModule.getRemoteVersion(context, ModuleDescriptor.MODULE_ID));
            jSONObject.put("container_version", 12451009);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    final /* synthetic */ Void zzf(JSONObject jSONObject) {
        zzyt.zza(this.zzzc, 1, jSONObject);
        this.zzdnb.edit().putLong("js_last_update", com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis()).apply();
        return null;
    }
}
