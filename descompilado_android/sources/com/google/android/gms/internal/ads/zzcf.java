package com.google.android.gms.internal.ads;

import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcf extends zzce<Integer, Object> {
    public String zzno;
    public long zznp;
    public String zznq;
    public String zznr;
    public String zzns;

    public zzcf(String str) {
        this();
        zzan(str);
    }

    public zzcf() {
        this.zzno = "E";
        this.zznp = -1L;
        this.zznq = "E";
        this.zznr = "E";
        this.zzns = "E";
    }

    @Override // com.google.android.gms.internal.ads.zzce
    protected final void zzan(String str) {
        HashMap mapZzao = zzao(str);
        if (mapZzao != null) {
            this.zzno = mapZzao.get(0) == null ? "E" : (String) mapZzao.get(0);
            this.zznp = mapZzao.get(1) == null ? -1L : ((Long) mapZzao.get(1)).longValue();
            this.zznq = mapZzao.get(2) == null ? "E" : (String) mapZzao.get(2);
            this.zznr = mapZzao.get(3) == null ? "E" : (String) mapZzao.get(3);
            this.zzns = mapZzao.get(4) != null ? (String) mapZzao.get(4) : "E";
        }
    }

    @Override // com.google.android.gms.internal.ads.zzce
    protected final HashMap<Integer, Object> zzbw() {
        HashMap<Integer, Object> map = new HashMap<>();
        map.put(0, this.zzno);
        map.put(4, this.zzns);
        map.put(3, this.zznr);
        map.put(2, this.zznq);
        map.put(1, Long.valueOf(this.zznp));
        return map;
    }
}
