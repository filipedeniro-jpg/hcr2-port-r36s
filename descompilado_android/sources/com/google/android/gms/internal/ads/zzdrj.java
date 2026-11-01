package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdrj implements zzdrg {
    zzdrj() {
    }

    @Override // com.google.android.gms.internal.ads.zzdrg
    public final Map<?, ?> zzan(Object obj) {
        return (zzdrh) obj;
    }

    @Override // com.google.android.gms.internal.ads.zzdrg
    public final zzdre<?, ?> zzas(Object obj) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.ads.zzdrg
    public final Map<?, ?> zzao(Object obj) {
        return (zzdrh) obj;
    }

    @Override // com.google.android.gms.internal.ads.zzdrg
    public final boolean zzap(Object obj) {
        return !((zzdrh) obj).isMutable();
    }

    @Override // com.google.android.gms.internal.ads.zzdrg
    public final Object zzaq(Object obj) {
        ((zzdrh) obj).zzaxe();
        return obj;
    }

    @Override // com.google.android.gms.internal.ads.zzdrg
    public final Object zzar(Object obj) {
        return zzdrh.zzban().zzbao();
    }

    @Override // com.google.android.gms.internal.ads.zzdrg
    public final Object zze(Object obj, Object obj2) {
        zzdrh zzdrhVarZzbao = (zzdrh) obj;
        zzdrh zzdrhVar = (zzdrh) obj2;
        if (!zzdrhVar.isEmpty()) {
            if (!zzdrhVarZzbao.isMutable()) {
                zzdrhVarZzbao = zzdrhVarZzbao.zzbao();
            }
            zzdrhVarZzbao.zza(zzdrhVar);
        }
        return zzdrhVarZzbao;
    }

    @Override // com.google.android.gms.internal.ads.zzdrg
    public final int zzb(int i, Object obj, Object obj2) {
        zzdrh zzdrhVar = (zzdrh) obj;
        if (zzdrhVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzdrhVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
