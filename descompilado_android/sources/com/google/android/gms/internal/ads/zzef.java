package com.google.android.gms.internal.ads;

import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzef extends zzce<Integer, Long> {
    public Long zzyq;
    public Long zzyr;
    public Long zzys;
    public Long zzyt;
    public Long zzyu;
    public Long zzyv;
    public Long zzyw;
    public Long zzyx;
    public Long zzyy;
    public Long zzyz;
    public Long zzza;

    public zzef() {
    }

    public zzef(String str) {
        zzan(str);
    }

    @Override // com.google.android.gms.internal.ads.zzce
    protected final void zzan(String str) {
        HashMap mapZzao = zzao(str);
        if (mapZzao != null) {
            this.zzyq = (Long) mapZzao.get(0);
            this.zzyr = (Long) mapZzao.get(1);
            this.zzys = (Long) mapZzao.get(2);
            this.zzyt = (Long) mapZzao.get(3);
            this.zzyu = (Long) mapZzao.get(4);
            this.zzyv = (Long) mapZzao.get(5);
            this.zzyw = (Long) mapZzao.get(6);
            this.zzyx = (Long) mapZzao.get(7);
            this.zzyy = (Long) mapZzao.get(8);
            this.zzyz = (Long) mapZzao.get(9);
            this.zzza = (Long) mapZzao.get(10);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzce
    protected final HashMap<Integer, Long> zzbw() {
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(0, this.zzyq);
        map.put(1, this.zzyr);
        map.put(2, this.zzys);
        map.put(3, this.zzyt);
        map.put(4, this.zzyu);
        map.put(5, this.zzyv);
        map.put(6, this.zzyw);
        map.put(7, this.zzyx);
        map.put(8, this.zzyy);
        map.put(9, this.zzyz);
        map.put(10, this.zzza);
        return map;
    }
}
