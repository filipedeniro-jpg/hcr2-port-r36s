package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class zzn<T> extends zzf<T> {
    private final Object zzaf;

    /* JADX WARN: Illegal instructions before constructor call */
    protected zzn(zzl zzlVar, String str, String str2, Object obj, Class<T> cls) {
        zzav zzavVarZzab = null;
        if (obj != null) {
            zzavVarZzab = new zzav(zzlVar.zzl(), obj).zzab(((zzay) zzlVar.zze()).zzar().isEmpty() ? null : "data");
        }
        super(zzlVar, str, str2, zzavVarZzab, cls);
        this.zzaf = obj;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzf
    /* JADX INFO: renamed from: zzm, reason: merged with bridge method [inline-methods] */
    public zzl zzf() {
        return (zzl) super.zzf();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzf, com.google.android.gms.internal.firebase_remote_config.zzby
    /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public zzn<T> zzb(String str, Object obj) {
        return (zzn) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzf
    /* JADX INFO: renamed from: zzc */
    public /* synthetic */ zzf zzb(String str, Object obj) {
        return (zzn) zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzf
    protected final /* synthetic */ IOException zza(zzac zzacVar) {
        return zze.zza(((zzl) zzf()).zzl(), zzacVar);
    }
}
