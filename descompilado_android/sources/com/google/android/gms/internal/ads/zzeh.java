package com.google.android.gms.internal.ads;

import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzeh extends zzce<Integer, Long> {
    public Long zzzm;
    public Long zzzn;
    public Long zzzo;
    public Long zzzp;

    public zzeh() {
    }

    public zzeh(String str) {
        zzan(str);
    }

    @Override // com.google.android.gms.internal.ads.zzce
    protected final void zzan(String str) {
        HashMap mapZzao = zzao(str);
        if (mapZzao != null) {
            this.zzzm = (Long) mapZzao.get(0);
            this.zzzn = (Long) mapZzao.get(1);
            this.zzzo = (Long) mapZzao.get(2);
            this.zzzp = (Long) mapZzao.get(3);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzce
    protected final HashMap<Integer, Long> zzbw() {
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(0, this.zzzm);
        map.put(1, this.zzzn);
        map.put(2, this.zzzo);
        map.put(3, this.zzzp);
        return map;
    }
}
