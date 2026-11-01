package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgf implements zzbgc {
    private final Context zzlk;

    public zzbgf(Context context) {
        this.zzlk = context;
    }

    @Override // com.google.android.gms.internal.ads.zzbgc
    public final void zzk(Map<String, String> map) {
        CookieManager cookieManagerZzbc;
        String str = map.get("cookie");
        if (TextUtils.isEmpty(str) || (cookieManagerZzbc = com.google.android.gms.ads.internal.zzp.zzka().zzbc(this.zzlk)) == null) {
            return;
        }
        cookieManagerZzbc.setCookie("googleads.g.doubleclick.net", str);
    }
}
