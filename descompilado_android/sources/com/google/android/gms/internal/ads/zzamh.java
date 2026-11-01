package com.google.android.gms.internal.ads;

import com.my.target.aa;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzamh {
    private final zzbbc zzcyx;
    private final boolean zzdfp;
    private final String zzdfq;

    public zzamh(zzbbc zzbbcVar, Map<String, String> map) {
        this.zzcyx = zzbbcVar;
        this.zzdfq = map.get(aa.d.bg);
        if (map.containsKey(aa.d.bf)) {
            this.zzdfp = Boolean.parseBoolean(map.get(aa.d.bf));
        } else {
            this.zzdfp = true;
        }
    }

    public final void execute() {
        int iZzvm;
        if (this.zzcyx == null) {
            zzatm.zzeu("AdWebView is null");
            return;
        }
        if ("portrait".equalsIgnoreCase(this.zzdfq)) {
            com.google.android.gms.ads.internal.zzp.zzka();
            iZzvm = 7;
        } else if ("landscape".equalsIgnoreCase(this.zzdfq)) {
            com.google.android.gms.ads.internal.zzp.zzka();
            iZzvm = 6;
        } else {
            iZzvm = this.zzdfp ? -1 : com.google.android.gms.ads.internal.zzp.zzka().zzvm();
        }
        this.zzcyx.setRequestedOrientation(iZzvm);
    }
}
