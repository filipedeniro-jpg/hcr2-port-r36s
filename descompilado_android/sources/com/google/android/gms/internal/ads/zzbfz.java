package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbfz implements zzdbq<zzcvj, zzcvj> {
    private Map<String, zzbgc> zzfan;

    public zzbfz(Map<String, zzbgc> map) {
        this.zzfan = map;
    }

    @Override // com.google.android.gms.internal.ads.zzdbq
    public final /* synthetic */ zzdcp<zzcvj> zzf(zzcvj zzcvjVar) throws Exception {
        zzcvj zzcvjVar2 = zzcvjVar;
        for (zzcvg zzcvgVar : zzcvjVar2.zzgjp.zzgjn) {
            if (this.zzfan.containsKey(zzcvgVar.name)) {
                this.zzfan.get(zzcvgVar.name).zzk(zzcvgVar.zzgjk);
            }
        }
        return zzdcf.zzah(zzcvjVar2);
    }
}
