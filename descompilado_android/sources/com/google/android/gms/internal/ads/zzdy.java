package com.google.android.gms.internal.ads;

import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdy extends zzce<Integer, Long> {
    public Long zzyh;
    public Long zzyi;

    public zzdy() {
    }

    public zzdy(String str) {
        zzan(str);
    }

    @Override // com.google.android.gms.internal.ads.zzce
    protected final void zzan(String str) {
        HashMap mapZzao = zzao(str);
        if (mapZzao != null) {
            this.zzyh = (Long) mapZzao.get(0);
            this.zzyi = (Long) mapZzao.get(1);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzce
    protected final HashMap<Integer, Long> zzbw() {
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(0, this.zzyh);
        map.put(1, this.zzyi);
        return map;
    }
}
