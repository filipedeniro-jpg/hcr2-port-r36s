package com.google.android.gms.internal.ads;

import java.util.HashSet;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcth implements zzdvi<zzcrd<JSONObject>> {
    public static zzcrd<JSONObject> zza(Object obj, zzcru zzcruVar, zzcsp zzcspVar, zzdvc<zzcro> zzdvcVar, zzdvc<zzcry> zzdvcVar2, zzdvc<zzcsc> zzdvcVar3, zzdvc<zzcsl> zzdvcVar4, zzdvc<zzcsq> zzdvcVar5, zzdvc<zzcsu> zzdvcVar6, zzdvc<zzctj> zzdvcVar7, Executor executor) {
        HashSet hashSet = new HashSet();
        hashSet.add((zzcsh) obj);
        hashSet.add(zzcruVar);
        hashSet.add(zzcspVar);
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrr)).booleanValue()) {
            hashSet.add(zzdvcVar.get());
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrs)).booleanValue()) {
            hashSet.add(zzdvcVar2.get());
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrt)).booleanValue()) {
            hashSet.add(zzdvcVar3.get());
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcru)).booleanValue()) {
            hashSet.add(zzdvcVar4.get());
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrx)).booleanValue()) {
            hashSet.add(zzdvcVar5.get());
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrz)).booleanValue()) {
            hashSet.add(zzdvcVar6.get());
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcsa)).booleanValue()) {
            hashSet.add(zzdvcVar7.get());
        }
        return (zzcrd) zzdvo.zza(new zzcrd(executor, hashSet), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        throw new NoSuchMethodError();
    }
}
