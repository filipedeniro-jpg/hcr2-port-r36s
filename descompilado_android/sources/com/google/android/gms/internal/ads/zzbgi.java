package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgi implements zzbgc {
    private final zzato zzdqz;

    public zzbgi(zzato zzatoVar) {
        this.zzdqz = zzatoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbgc
    public final void zzk(Map<String, String> map) {
        String str = map.get(Constants.ParametersKeys.KEY);
        String str2 = map.get("value");
        if ("auto_collect_location".equals(str)) {
            this.zzdqz.zzaj(Boolean.parseBoolean(str2));
        }
    }
}
