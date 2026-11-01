package com.google.android.gms.ads.internal;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzahv;
import com.google.android.gms.internal.ads.zzaic;
import com.google.android.gms.internal.ads.zzasy;
import com.google.android.gms.internal.ads.zzatm;
import com.google.android.gms.internal.ads.zzawv;
import com.google.android.gms.internal.ads.zzawx;
import com.google.android.gms.internal.ads.zzaxb;
import com.google.android.gms.internal.ads.zzdcf;
import com.google.android.gms.internal.ads.zzdcp;
import com.google.android.gms.internal.ads.zzuo;
import com.google.android.gms.internal.ads.zzyt;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zze {
    private long zzbkt = 0;
    private Context zzlk;

    public final void zza(Context context, zzawv zzawvVar, String str, @Nullable Runnable runnable) {
        zza(context, zzawvVar, true, null, str, null, runnable);
    }

    public final void zza(Context context, zzawv zzawvVar, String str, zzasy zzasyVar) {
        zza(context, zzawvVar, false, zzasyVar, zzasyVar != null ? zzasyVar.zztx() : null, str, null);
    }

    @VisibleForTesting
    private final void zza(Context context, zzawv zzawvVar, boolean z, @Nullable zzasy zzasyVar, String str, @Nullable String str2, @Nullable Runnable runnable) {
        if (zzp.zzkf().elapsedRealtime() - this.zzbkt < DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS) {
            zzatm.zzeu("Not retrying to fetch app settings");
            return;
        }
        this.zzbkt = zzp.zzkf().elapsedRealtime();
        boolean z2 = true;
        if (zzasyVar != null) {
            if (!(zzp.zzkf().currentTimeMillis() - zzasyVar.zztu() > ((Long) zzuo.zzoj().zzd(zzyt.zzcoq)).longValue()) && zzasyVar.zztv()) {
                z2 = false;
            }
        }
        if (z2) {
            if (context == null) {
                zzatm.zzeu("Context not provided to fetch application settings");
                return;
            }
            if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
                zzatm.zzeu("App settings could not be fetched. Required parameters missing");
                return;
            }
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
            this.zzlk = applicationContext;
            zzahv zzahvVarZza = zzp.zzkl().zzb(this.zzlk, zzawvVar).zza("google.afma.config.fetchAppSettings", zzaic.zzdan, zzaic.zzdan);
            try {
                JSONObject jSONObject = new JSONObject();
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put("app_id", str);
                } else if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put("ad_unit_id", str2);
                }
                jSONObject.put("is_init", z);
                jSONObject.put("pn", context.getPackageName());
                zzdcp zzdcpVarZzi = zzahvVarZza.zzi(jSONObject);
                zzdcp zzdcpVarZzb = zzdcf.zzb(zzdcpVarZzi, zzd.zzbks, zzawx.zzdwb);
                if (runnable != null) {
                    zzdcpVarZzi.addListener(runnable, zzawx.zzdwb);
                }
                zzaxb.zza(zzdcpVarZzb, "ConfigLoader.maybeFetchNewAppSettings");
            } catch (Exception e) {
                zzatm.zzc("Error requesting application settings", e);
            }
        }
    }
}
