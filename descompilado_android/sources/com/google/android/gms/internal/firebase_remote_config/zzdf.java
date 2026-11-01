package com.google.android.gms.internal.firebase_remote_config;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class zzdf extends zzax {

    @zzcc
    private String appName;

    @zzcc
    private Map<String, String> entries;

    @zzcc
    private List<zzdd> experimentDescriptions;

    @zzcc
    private String state;

    public final Map<String, String> getEntries() {
        return this.entries;
    }

    public final List<zzdd> zzcf() {
        return this.experimentDescriptions;
    }

    public final String getState() {
        return this.state;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzax
    /* JADX INFO: renamed from: zza */
    public final /* synthetic */ zzax zzb(String str, Object obj) {
        return (zzdf) zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzax
    /* JADX INFO: renamed from: zza */
    public final /* synthetic */ zzax clone() {
        return (zzdf) clone();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzax, com.google.android.gms.internal.firebase_remote_config.zzby
    /* JADX INFO: renamed from: zzb */
    public final /* synthetic */ zzby clone() {
        return (zzdf) clone();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzax, com.google.android.gms.internal.firebase_remote_config.zzby
    public final /* synthetic */ zzby zzb(String str, Object obj) {
        return (zzdf) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzax, com.google.android.gms.internal.firebase_remote_config.zzby, java.util.AbstractMap
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (zzdf) super.clone();
    }
}
