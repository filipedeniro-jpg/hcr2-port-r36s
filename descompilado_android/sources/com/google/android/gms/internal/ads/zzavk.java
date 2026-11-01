package com.google.android.gms.internal.ads;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzavk extends zzq<zzo> {
    private final Map<String, String> zzab;
    private final zzaxf<zzo> zzdue;
    private final zzawi zzduf;

    public zzavk(String str, zzaxf<zzo> zzaxfVar) {
        this(str, null, zzaxfVar);
    }

    private zzavk(String str, Map<String, String> map, zzaxf<zzo> zzaxfVar) {
        super(0, str, new zzavn(zzaxfVar));
        this.zzab = null;
        this.zzdue = zzaxfVar;
        this.zzduf = new zzawi();
        this.zzduf.zza(str, HttpRequest.METHOD_GET, null, null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.gms.internal.ads.zzq
    protected final zzz<zzo> zza(zzo zzoVar) {
        return zzz.zza(zzoVar, zzaq.zzb(zzoVar));
    }

    @Override // com.google.android.gms.internal.ads.zzq
    protected final /* synthetic */ void zza(zzo zzoVar) {
        zzo zzoVar2 = zzoVar;
        this.zzduf.zza(zzoVar2.zzab, zzoVar2.statusCode);
        zzawi zzawiVar = this.zzduf;
        byte[] bArr = zzoVar2.data;
        if (zzawi.isEnabled() && bArr != null) {
            zzawiVar.zzi(bArr);
        }
        this.zzdue.set(zzoVar2);
    }
}
