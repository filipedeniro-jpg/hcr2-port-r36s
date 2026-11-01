package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzavj extends zzav {
    private final /* synthetic */ byte[] zzdub;
    private final /* synthetic */ Map zzduc;
    private final /* synthetic */ zzawi zzdud;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzavj(zzave zzaveVar, int i, String str, zzab zzabVar, zzy zzyVar, byte[] bArr, Map map, zzawi zzawiVar) {
        super(i, str, zzabVar, zzyVar);
        this.zzdub = bArr;
        this.zzduc = map;
        this.zzdud = zzawiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzq
    public final byte[] zzf() throws zzb {
        byte[] bArr = this.zzdub;
        return bArr == null ? super.zzf() : bArr;
    }

    @Override // com.google.android.gms.internal.ads.zzq
    public final Map<String, String> getHeaders() throws zzb {
        Map<String, String> map = this.zzduc;
        return map == null ? super.getHeaders() : map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzav
    /* JADX INFO: renamed from: zzh */
    public final void zza(String str) {
        this.zzdud.zzep(str);
        super.zza(str);
    }

    @Override // com.google.android.gms.internal.ads.zzav, com.google.android.gms.internal.ads.zzq
    protected final /* synthetic */ void zza(String str) {
        zza(str);
    }
}
