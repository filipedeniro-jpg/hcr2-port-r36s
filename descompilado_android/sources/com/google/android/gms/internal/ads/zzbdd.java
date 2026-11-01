package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbdd implements zzadx<zzbbc> {
    private final /* synthetic */ zzbdb zzehr;

    zzbdd(zzbdb zzbdbVar) {
        this.zzehr = zzbdbVar;
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final /* synthetic */ void zza(zzbbc zzbbcVar, Map map) {
        if (map != null) {
            String str = (String) map.get("height");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                int i = Integer.parseInt(str);
                synchronized (this.zzehr) {
                    if (this.zzehr.zzegl != i) {
                        this.zzehr.zzegl = i;
                        this.zzehr.requestLayout();
                    }
                }
            } catch (Exception e) {
                zzatm.zzd("Exception occurred while getting webview content height", e);
            }
        }
    }
}
