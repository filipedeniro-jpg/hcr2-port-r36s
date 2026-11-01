package com.google.android.gms.internal.ads;

import android.content.Context;
import com.ironsource.sdk.constants.Constants;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzadz implements zzadx<Object> {
    private final Context zzlk;

    public zzadz(Context context) {
        this.zzlk = context;
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final void zza(Object obj, Map<String, String> map) {
        if (com.google.android.gms.ads.internal.zzp.zzkw().zzaa(this.zzlk)) {
            String str = map.get(Constants.ParametersKeys.EVENT_NAME);
            String str2 = map.get("eventId");
            byte b = -1;
            int iHashCode = str.hashCode();
            if (iHashCode != 94399) {
                if (iHashCode != 94401) {
                    if (iHashCode == 94407 && str.equals("_ai")) {
                        b = 1;
                    }
                } else if (str.equals("_ac")) {
                    b = 0;
                }
            } else if (str.equals("_aa")) {
                b = 2;
            }
            if (b == 0) {
                com.google.android.gms.ads.internal.zzp.zzkw().zzh(this.zzlk, str2);
                return;
            }
            if (b == 1) {
                com.google.android.gms.ads.internal.zzp.zzkw().zzi(this.zzlk, str2);
            } else if (b == 2) {
                com.google.android.gms.ads.internal.zzp.zzkw().zzk(this.zzlk, str2);
            } else {
                zzatm.zzes("logScionEvent gmsg contained unsupported eventName");
            }
        }
    }
}
