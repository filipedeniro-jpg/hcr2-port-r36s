package com.google.android.gms.internal.ads;

import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdj extends zzce<Integer, Long> {
    public long zzxe;
    public long zzxf;

    public zzdj() {
        this.zzxe = -1L;
        this.zzxf = -1L;
    }

    public zzdj(String str) {
        this();
        zzan(str);
    }

    @Override // com.google.android.gms.internal.ads.zzce
    protected final HashMap<Integer, Long> zzbw() {
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(0, Long.valueOf(this.zzxe));
        map.put(1, Long.valueOf(this.zzxf));
        return map;
    }

    @Override // com.google.android.gms.internal.ads.zzce
    protected final void zzan(String str) {
        HashMap mapZzao = zzao(str);
        if (mapZzao != null) {
            this.zzxe = ((Long) mapZzao.get(0)).longValue();
            this.zzxf = ((Long) mapZzao.get(1)).longValue();
        }
    }
}
