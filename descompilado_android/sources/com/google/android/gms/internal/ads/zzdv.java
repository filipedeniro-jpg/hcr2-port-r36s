package com.google.android.gms.internal.ads;

import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdv extends zzce<Integer, Object> {
    public Long zzxo;
    public Boolean zzxp;
    public Boolean zzxq;

    public zzdv() {
    }

    public zzdv(String str) {
        zzan(str);
    }

    @Override // com.google.android.gms.internal.ads.zzce
    protected final void zzan(String str) {
        HashMap mapZzao = zzao(str);
        if (mapZzao != null) {
            this.zzxo = (Long) mapZzao.get(0);
            this.zzxp = (Boolean) mapZzao.get(1);
            this.zzxq = (Boolean) mapZzao.get(2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzce
    protected final HashMap<Integer, Object> zzbw() {
        HashMap<Integer, Object> map = new HashMap<>();
        map.put(0, this.zzxo);
        map.put(1, this.zzxp);
        map.put(2, this.zzxq);
        return map;
    }
}
