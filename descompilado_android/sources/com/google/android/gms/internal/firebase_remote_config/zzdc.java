package com.google.android.gms.internal.firebase_remote_config;

import io.fabric.sdk.android.services.network.HttpRequest;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdc extends zzdb<zzdf> {

    @zzcc
    private String namespace;

    @zzcc
    private String project;

    protected zzdc(zzcz zzczVar, String str, String str2, zzdg zzdgVar) {
        super(zzczVar.zzgt.zzgu, HttpRequest.METHOD_POST, "v1/projects/{project}/namespaces/{namespace}:fetch", zzdgVar, zzdf.class);
        this.project = (String) zzch.checkNotNull(str, "Required parameter project must be specified.");
        this.namespace = (String) zzch.checkNotNull(str2, "Required parameter namespace must be specified.");
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzdb
    /* JADX INFO: renamed from: zzg */
    public final /* synthetic */ zzdb<zzdf> zzb(String str, Object obj) {
        return (zzdc) zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzdb, com.google.android.gms.internal.firebase_remote_config.zzn
    /* JADX INFO: renamed from: zzd */
    public final /* synthetic */ zzn zzb(String str, Object obj) {
        return (zzdc) zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzdb, com.google.android.gms.internal.firebase_remote_config.zzn, com.google.android.gms.internal.firebase_remote_config.zzf
    /* JADX INFO: renamed from: zzc */
    public final /* synthetic */ zzf zzb(String str, Object obj) {
        return (zzdc) zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzdb, com.google.android.gms.internal.firebase_remote_config.zzn, com.google.android.gms.internal.firebase_remote_config.zzf, com.google.android.gms.internal.firebase_remote_config.zzby
    public final /* synthetic */ zzby zzb(String str, Object obj) {
        return (zzdc) super.zzb(str, obj);
    }
}
