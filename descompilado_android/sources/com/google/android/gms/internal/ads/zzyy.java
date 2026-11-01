package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build;
import com.facebook.appevents.AppEventsConstants;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzyy {
    private String zzbnh;
    private String zzcts = (String) zzuo.zzoj().zzd(zzyt.zzcif);
    private Map<String, String> zzctv = new LinkedHashMap();
    private Context zzlk;

    public zzyy(Context context, String str) {
        this.zzlk = null;
        this.zzbnh = null;
        this.zzlk = context;
        this.zzbnh = str;
        this.zzctv.put("s", "gmob_sdk");
        this.zzctv.put("v", "3");
        this.zzctv.put("os", Build.VERSION.RELEASE);
        this.zzctv.put("sdk", Build.VERSION.SDK);
        Map<String, String> map = this.zzctv;
        com.google.android.gms.ads.internal.zzp.zzjy();
        map.put("device", zzatv.zzvj());
        this.zzctv.put("app", context.getApplicationContext() != null ? context.getApplicationContext().getPackageName() : context.getPackageName());
        Map<String, String> map2 = this.zzctv;
        com.google.android.gms.ads.internal.zzp.zzjy();
        map2.put("is_lite_sdk", zzatv.zzax(context) ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        Future<zzaot> futureZzw = com.google.android.gms.ads.internal.zzp.zzkj().zzw(this.zzlk);
        try {
            this.zzctv.put("network_coarse", Integer.toString(futureZzw.get().zzdmg));
            this.zzctv.put("network_fine", Integer.toString(futureZzw.get().zzdmh));
        } catch (Exception e) {
            com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "CsiConfiguration.CsiConfiguration");
        }
    }

    final String zzps() {
        return this.zzcts;
    }

    final Context getContext() {
        return this.zzlk;
    }

    final String zzlb() {
        return this.zzbnh;
    }

    final Map<String, String> zzpt() {
        return this.zzctv;
    }
}
