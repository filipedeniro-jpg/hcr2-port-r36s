package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinEventParameters;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaei implements zzadx<Object> {
    private final zzaeh zzcxt;

    public static void zza(zzbbc zzbbcVar, zzaeh zzaehVar) {
        zzbbcVar.zza("/reward", new zzaei(zzaehVar));
    }

    private zzaei(zzaeh zzaehVar) {
        this.zzcxt = zzaehVar;
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final void zza(Object obj, Map<String, String> map) {
        String str = map.get("action");
        if ("grant".equals(str)) {
            zzaqd zzaqdVar = null;
            try {
                int i = Integer.parseInt(map.get(AppLovinEventParameters.REVENUE_AMOUNT));
                String str2 = map.get("type");
                if (!TextUtils.isEmpty(str2)) {
                    zzaqdVar = new zzaqd(str2, i);
                }
            } catch (NumberFormatException e) {
                zzatm.zzd("Unable to parse reward amount.", e);
            }
            this.zzcxt.zza(zzaqdVar);
            return;
        }
        if ("video_start".equals(str)) {
            this.zzcxt.zzqv();
        } else if ("video_complete".equals(str)) {
            this.zzcxt.zzqw();
        }
    }
}
