package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgg implements zzbgc {
    private final zzcuz zzfao;

    public zzbgg(zzcuz zzcuzVar) {
        this.zzfao = zzcuzVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbgc
    public final void zzk(Map<String, String> map) {
        String str = map.get("render_in_browser");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.zzfao.zzbd(Boolean.parseBoolean(str));
        } catch (Exception unused) {
            throw new IllegalStateException("Invalid render_in_browser state");
        }
    }
}
